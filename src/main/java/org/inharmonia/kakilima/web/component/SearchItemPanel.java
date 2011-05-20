package org.inharmonia.kakilima.web.component;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class SearchItemPanel extends Panel {

    public SearchItemPanel(String id) {
        super(id);

        Form searchItemForm = new Form("searchItemForm"){

            @Override
            protected void onSubmit() {
                info("Implement me!");
            }

        };

        searchItemForm.add(new TextField("keyword", new Model<String>("")));
        searchItemForm.add(new Button("searchButton", new Model("Search")));
        add(searchItemForm);
    }

}
