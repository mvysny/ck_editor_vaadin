package com.pany;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        final CKEditorField editor = new CKEditorField();
        editor.setValue("A <b>really important</b> text that is <i>really</i> important");
        editor.setWidth("400px");

        Button button = new Button("Click Me");
        button.addClickListener(e -> layout.addComponent(new Label("Text: " + editor.getValue())));

        layout.setMargin(true);
        layout.setSpacing(true);

        layout.addComponent(editor);
        layout.addComponents(button);

        setContent(layout);
    }
}
