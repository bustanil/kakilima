package org.inharmonia.kakilima.web.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Bytes;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;
import org.apache.wicket.validation.validator.MinimumValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.inharmonia.kakilima.base.domain.Category;
import org.inharmonia.kakilima.base.domain.Item;
import org.inharmonia.kakilima.base.domain.Store;
import org.inharmonia.kakilima.base.service.CategoryService;
import org.inharmonia.kakilima.base.service.ShopService;
import org.inharmonia.kakilima.web.session.KakilimaSession;
import org.inharmonia.kakilima.web.validator.ImageUploadValidator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class SellItemPage extends BasePage {

    private FileUploadField fileUploadField;
    @SpringBean
    private ShopService shopService;
    @SpringBean
    private CategoryService categoryService;

    public SellItemPage(PageParameters parameters) {
        super(parameters);

        Form<Item> form = new Form<Item>("sellItemForm", new CompoundPropertyModel<Item>(new Item())) {
            @Override
            protected void onSubmit() {
                Item item = getModel().getObject();
                if(getWebSession().isAutheticated()){
                    shopService.addItem(item);
                    getModel().setObject(new Item());
                    info("SUCCESS!");
                }
                else {
                    getWebSession().error("Please login first to continue!");
                    getWebSession().setRedirectToPage(SellItemPage.this);
                    setResponsePage(LoginPage.class);
                    setRedirect(true);
                }
            }

        };
        List<Category> categories = categoryService.getFirstLevelCategories();
        List<Store> stores = shopService.getStoresByEmail(getWebSession().getUserInfo().getEmail());
        form.add(new DropDownChoice<Store>("store", stores, new IChoiceRenderer<Store>(){
            @Override
            public Object getDisplayValue(Store store) {
                return store.getName();
            }
            @Override
            public String getIdValue(Store store, int index) {
                return store.getId().toString();
            }
        }).setRequired(true));
        form.add(new DropDownChoice<Category>("category", categories, new IChoiceRenderer<Category>(){
            @Override
            public Object getDisplayValue(Category category) {
                return category.getName();
            }
            @Override
            public String getIdValue(Category category, int index) {
                return category.getId().toString();
            }
        }).setRequired(true));
        form.add(new TextField<String>("name").setRequired(true));
        form.add(new TextArea<String>("description").setRequired(true).add(StringValidator.maximumLength(1024)));
        form.add(new CheckBox("negotiable"));
        form.add((fileUploadField = new FileUploadField("image1", new Model<FileUpload>())).setRequired(true).add(new ImageUploadValidator("JPEG")) );
        form.add(new Button("button", new Model<String>("Submit")));
        form.add(new TextField<Double>("price").setRequired(true).add(new MinimumValidator<Double>(0.0d)));

        form.setMultiPart(true);
        form.setMaxSize(Bytes.kilobytes(100));

        add(form);
    }
}
