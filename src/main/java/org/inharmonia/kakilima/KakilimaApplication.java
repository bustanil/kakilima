package org.inharmonia.kakilima;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.inharmonia.kakilima.base.domain.Category;
import org.inharmonia.kakilima.base.domain.Item;
import org.inharmonia.kakilima.base.domain.Store;
import org.inharmonia.kakilima.base.domain.User;
import org.inharmonia.kakilima.base.service.CategoryService;
import org.inharmonia.kakilima.base.service.ShopService;
import org.inharmonia.kakilima.base.service.UserService;
import org.inharmonia.kakilima.web.page.HomePage;
import org.inharmonia.kakilima.web.session.KakilimaSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;

public class KakilimaApplication extends WebApplication {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;

    /**
     * Constructor
     */
    public KakilimaApplication() {
    }

    @Override
    protected void init() {
        super.init();
        addComponentInstantiationListener(new SpringComponentInjector(this));
        Category elektronik = new Category("Elektronik");
        categoryService.addCategory(elektronik);
        categoryService.addCategory(new Category("Ponsel"));
        categoryService.addCategory(new Category("Pakaian"));
        categoryService.addCategory(new Category("Mainan"));
        categoryService.addCategory(new Category("Komputer"));

        User bustanil = new User("street.programmer@gmail.com", "Bustanil Arifin", "demo1234");
        userService.addUser(bustanil);

        Store electronicStore = new Store();
        electronicStore.setName("Electronik Store");
        electronicStore.setDescription("My Electronik Store");
        shopService.addStore(electronicStore, bustanil.getEmail());

        shopService.addItem(new Item("AC LG Inverter", "AC hemat energi", false, 4000000d, elektronik, electronicStore));
        shopService.addItem(new Item("Kulkas Sharp", "Dinginnya ga nanggung-nanggung", true, 3000000d, elektronik, electronicStore));
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new KakilimaSession(request);
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
