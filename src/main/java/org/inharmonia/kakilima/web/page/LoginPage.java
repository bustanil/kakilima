package org.inharmonia.kakilima.web.page;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.inharmonia.kakilima.base.domain.User;
import org.inharmonia.kakilima.base.service.UserService;
import org.inharmonia.kakilima.web.constant.CookieNames;
import org.inharmonia.kakilima.web.util.CookiesUtil;
import org.inharmonia.kakilima.web.model.UserInfo;

public class LoginPage extends BasePage {

    @SpringBean
    private UserService userService;


    public LoginPage() {
        Form<UserInfo> loginForm = new StatelessForm<UserInfo>("loginForm", new CompoundPropertyModel<UserInfo>(new UserInfo())) {

            @Override
            protected void onSubmit() {
                UserInfo userInfo = getModel().getObject();
                User user = userService.validateUser(userInfo.getEmail(), userInfo.getPassword());
                if (user != null) {
                    userInfo.setFullname(user.getFullname());
                    getWebSession().setUserInfo(userInfo);
                    if (userInfo.isRememberMe()) {
                        CookiesUtil.setCookie(CookieNames.UID, userInfo.getEmail());
                    }
                    if (getWebSession().getRedirectToPage() != null) {
                        goToRedirectPage();
                    } else {
                        setResponsePage(HomePage.class);
                    }
                } else {
                    error("Invalid user and/or password");
                }
            }

        };
        loginForm.add(new TextField<String>("email").setRequired(true));
        loginForm.add(new PasswordTextField("password").setRequired(true));
        loginForm.add(new CheckBox("rememberMe"));
        loginForm.add(new Button("loginButton", new Model<String>("Login")));
        loginForm.add(new Label("registerText", "Don't have an account yet? Register"));
        loginForm.add(new Link("registerLink"){
            @Override
            public void onClick() {
                setResponsePage(UserRegistrationPage.class);
                setRedirect(true);
            }
        }.add(new Label("registerLinkLabel", "here")));
        add(loginForm);
    }

}
