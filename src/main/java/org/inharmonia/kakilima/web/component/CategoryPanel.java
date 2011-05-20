package org.inharmonia.kakilima.web.component;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.inharmonia.kakilima.base.domain.Category;
import org.inharmonia.kakilima.base.service.CategoryService;
import org.inharmonia.kakilima.web.page.ListItemPage;

import java.util.HashMap;
import java.util.List;

public class CategoryPanel extends Panel {

    @SpringBean
    private CategoryService categoryService;

    public CategoryPanel(String id) {
        super(id);
        List<Category> firstLevelCategories = categoryService.getFirstLevelCategories();
        ListView<Category> categoryListView = new ListView<Category>("category", firstLevelCategories) {

            @Override
            protected void populateItem(ListItem<Category> item) {
                HashMap<String, Long> parameterMap = new HashMap<String, Long>();
                parameterMap.put("categoryId", item.getModelObject().getId());
                Link categoryLink = new BookmarkablePageLink("categoryLink", ListItemPage.class, new PageParameters(parameterMap));
                categoryLink.add(new Label("categoryName", new PropertyModel(item.getModelObject(), "name")));
                item.add(categoryLink);
            }
            
        };
        add(categoryListView);
    }

}
