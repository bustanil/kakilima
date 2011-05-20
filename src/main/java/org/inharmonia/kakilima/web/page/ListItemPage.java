package org.inharmonia.kakilima.web.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.inharmonia.kakilima.base.domain.Item;
import org.inharmonia.kakilima.base.service.ShopService;

import java.util.List;

public class ListItemPage extends BasePage {

    @SpringBean
    ShopService shopService;

    public ListItemPage(PageParameters parameters) {
        super(parameters);
        Long categoryId = parameters.getLong("categoryId");
        List<Item> items = shopService.getItemsByCategoryId(categoryId);
        ListView<Item> itemListView = new ListView<Item>("items", items) {

            @Override
            protected void populateItem(ListItem<Item> item) {
                Label itemNameLabel = new Label("name", new PropertyModel<Item>(item.getModelObject(), "name"));
                Label descriptionLabel = new Label("description", new PropertyModel<Item>(item.getModelObject(), "description"));
                Label priceLabel = new Label("price", new PropertyModel<Item>(item.getModelObject(), "price"));
                Label priceStatusLabel = new Label("priceStatus", item.getModelObject().isNegotiable()? "(Negotiable)" : "");
                item.add(itemNameLabel);
                item.add(descriptionLabel);
                item.add(priceStatusLabel);
                item.add(priceLabel);
            }

        };
        add(itemListView);
    }
}
