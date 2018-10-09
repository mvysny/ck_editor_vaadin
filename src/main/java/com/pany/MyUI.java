package com.pany;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
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
@PreserveOnRefresh
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        button.setClickShortcut(ShortcutAction.KeyCode.Q, ShortcutAction.ModifierKey.ALT, ShortcutAction.ModifierKey.CTRL);


        
        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);

        layout.addComponent(new CKEditor("Foo"));

        setContent(layout);

/*
        new Thread() {
            @Override
            public void run() {
                System.out.println("Thread start");
                try {
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(2000);
                        access(() -> name.setValue(name.getValue() + "a"));
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                System.out.println("Thread end");
            }
        }.start();
*/
    }

}
