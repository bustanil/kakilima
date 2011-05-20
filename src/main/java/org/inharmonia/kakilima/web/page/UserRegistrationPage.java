package org.inharmonia.kakilima.web.page;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.inharmonia.kakilima.base.domain.User;
import org.inharmonia.kakilima.base.service.UserService;

public class UserRegistrationPage extends BasePage {

    private String fullname;
    private String email;
    private String password;
    private String repeatPassword;

    @SpringBean
    private UserService userService;

    public UserRegistrationPage() {

        Form userForm = new Form("userRegistrationForm", new CompoundPropertyModel(this)) {
            @Override
            protected void onSubmit() {
                boolean emailAvailable = userService.isEmailAvailable(email);
                if (emailAvailable) {
                    User user = new User(email, fullname, password);
                    userService.addUser(user);
                    getSession().info("Your registration is success. You can now login.");
                    setResponsePage(LoginPage.class);
                    setRedirect(true);
                }
                else {
                    error("Given email is already used");
                    email = null;
                }
            }
        };
        userForm.add(new Label("legend", "Register new user"));
        userForm.add(new Label("fullnameLabel", "Full Name"), new TextField<String>("fullname").setRequired(true));
        userForm.add(new Label("emailLabel", "Email"), new TextField<String>("email").setRequired(true).add(EmailAddressValidator.getInstance()));
        userForm.add(new Label("passwordLabel", "Password"), new PasswordTextField("password").setRequired(true));
        userForm.add(new Label("repeatPasswordLabel", "Repeat Password"), new PasswordTextField("repeatPassword").setRequired(true));
        userForm.add(new Button("registerButton", new Model<String>("Register")));
        add(userForm);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
