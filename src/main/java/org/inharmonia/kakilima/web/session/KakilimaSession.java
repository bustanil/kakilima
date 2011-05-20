package org.inharmonia.kakilima.web.session;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.inharmonia.kakilima.web.model.UserInfo;

public class KakilimaSession extends WebSession {

    private UserInfo userInfo;
    private Page redirectToPage;

    public KakilimaSession(Request request) {
        super(request);
    }

    public UserInfo getUserInfo() {
        if(userInfo == null){
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public boolean isAutheticated(){
        return getUserInfo().getEmail() != null;
    }

    public static KakilimaSession get(){
        return (KakilimaSession) Session.get();
    }

    public void setRedirectToPage(Page page){
        this.redirectToPage = page;
    }

    public Page getRedirectToPage() {
        return redirectToPage;
    }
}
