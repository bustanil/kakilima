package org.inharmonia.kakilima.web.page;

import org.apache.log4j.Logger;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.inharmonia.kakilima.web.component.CategoryPanel;
import org.inharmonia.kakilima.web.component.MenuBarPanel;
import org.inharmonia.kakilima.web.component.SearchItemPanel;
import org.inharmonia.kakilima.web.component.WelcomeMessagePanel;
import org.inharmonia.kakilima.web.session.KakilimaSession;

/**
 * Homepage
 */
public abstract class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(BasePage.class);

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 *
	 * @param parameters
	 *            Page parameters
	 */
    public BasePage(final PageParameters parameters) {
        initPage();
    }

    public BasePage(){
        initPage();
    }

    private void initPage() {
        add(new Label("pageTitle", getTitle()));
        add(new WelcomeMessagePanel("welcomeMessagePanel"));
        add(new SearchItemPanel("searchItemPanel"));
        add(new CategoryPanel("categoryPanel"));
        add(new MenuBarPanel("menubarPanel"));
        add(new FeedbackPanel("feedbackPanel"));
    }

    protected String getTitle(){
        return "Kakilima.com";
    }

    public KakilimaSession getWebSession(){
        return KakilimaSession.get();
    }

    protected void goToRedirectPage() {
        LOGGER.debug("Going from " + getClassRelativePath() + " to " + getWebSession().getRedirectToPage().getClassRelativePath());
        setResponsePage(getWebSession().getRedirectToPage());
        getWebSession().setRedirectToPage(null);
    }
}
