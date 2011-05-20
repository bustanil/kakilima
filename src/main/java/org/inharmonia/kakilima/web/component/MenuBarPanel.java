package org.inharmonia.kakilima.web.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.inharmonia.kakilima.web.page.ListStorePage;
import org.inharmonia.kakilima.web.page.LoginPage;
import org.inharmonia.kakilima.web.page.SellItemPage;
import org.inharmonia.kakilima.web.session.KakilimaSession;

public class MenuBarPanel extends Panel {

    public MenuBarPanel(String id) {
        super(id);
        Link sellLink = new Link("sellLink") {
            @Override
            public void onClick() {
                setResponsePage(SellItemPage.class);
            }
        };
        sellLink.add(new Label("sellLinkLabel", "Sell"));
        Link storeLink = new Link("storeLink") {
            @Override
            public void onClick() {
                setResponsePage(ListStorePage.class);
            }
        };
        storeLink.add(new Label("storeLinkLabel", "My Stores"));
        Link logoutLink = new Link("logoutLink") {
            @Override
            public void onClick() {
                KakilimaSession.get().invalidate();
                setResponsePage(LoginPage.class);
            }

            @Override
            public boolean isVisible() {
                return KakilimaSession.get().isAutheticated();
            }
        };
        logoutLink.add(new Label("logoutLinkLabel", "Logout"));
        
        add(storeLink);
        add(sellLink);
        add(logoutLink);
    }
}
