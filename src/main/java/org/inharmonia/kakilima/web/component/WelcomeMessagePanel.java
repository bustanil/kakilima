package org.inharmonia.kakilima.web.component;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.inharmonia.kakilima.web.model.UserInfo;
import org.inharmonia.kakilima.web.page.LoginPage;
import org.inharmonia.kakilima.web.page.UserRegistrationPage;
import org.inharmonia.kakilima.web.session.KakilimaSession;

public class WelcomeMessagePanel extends Panel {

    public WelcomeMessagePanel(String id) {
        super(id);

        final KakilimaSession session = KakilimaSession.get();

        Label usernameLabel = new Label("fullname", new PropertyModel<UserInfo>(session, "userInfo.fullname")) {
            @Override
            public boolean isVisible() {
                return session.isAutheticated();
            }
        };
        add(usernameLabel);
        WebMarkupContainer notLoggedInMessage = new WebMarkupContainer("notLoggedInMessage") {
            @Override
            public boolean isVisible() {
                return !session.isAutheticated();
            }
        };
        notLoggedInMessage.add(new Link("loginLink") {
            @Override
            public void onClick() {
                setResponsePage(LoginPage.class);
            }
        });
        notLoggedInMessage.add(new Link("registerLink") {
            @Override
            public void onClick() {
                setResponsePage(UserRegistrationPage.class);
                setRedirect(true);
            }
        });
        add(notLoggedInMessage);
    }

}
