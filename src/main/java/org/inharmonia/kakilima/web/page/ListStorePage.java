package org.inharmonia.kakilima.web.page;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.inharmonia.kakilima.base.domain.Store;
import org.inharmonia.kakilima.base.service.ShopService;

import java.util.List;

public class ListStorePage extends BasePage {

    @SpringBean
    private ShopService shopService;

    public ListStorePage() {
        super();
        add(new Label("myStoresLabel", "My Stores"));
        final List<Store> stores = shopService.getStoresByEmail(getWebSession().getUserInfo().getEmail());
        WebMarkupContainer emptyStoreContainer = new WebMarkupContainer("emptyStoreContainer"){
            @Override
            public boolean isVisible() {
                return stores.isEmpty();
            }
        };
        emptyStoreContainer.add(new Label("emptyStore1", "You haven't got any store yet. "));
        emptyStoreContainer.add(new Label("emptyStore2", "Add one "));
        emptyStoreContainer.add(new Link("addStoreLink"){
            @Override
            public void onClick() {
                // TODO link to add store page    
            }

        }.add(new Label("addStoreLinkLabel", "here")));
        WebMarkupContainer myStoreContainer = new WebMarkupContainer("myStoreContainer"){
            @Override
            public boolean isVisible() {
                return !stores.isEmpty();
            }
        };

        myStoreContainer.add(new Label("nameTableHeader", "Name"));
        myStoreContainer.add(new Label("descriptionTableHeader", "Description"));

        ListView<Store> storeItemListView = new ListView<Store>("storeItem", stores) {
            @Override
            protected void populateItem(ListItem<Store> item) {
                item.add(new Link("editStoreLink") {
                    @Override
                    public void onClick() {
                        // TODO link to edit store page by store id
                    }
                }.add(new Label("name", item.getModelObject().getName())));
                item.add(new Label("description", item.getModelObject().getDescription()));
            }
        };
        myStoreContainer.add(storeItemListView);
        add(emptyStoreContainer);
        add(myStoreContainer);
    }
}
