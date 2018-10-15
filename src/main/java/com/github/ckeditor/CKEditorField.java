package com.github.ckeditor;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Wraps {@link CKEditor} in a full-blown field. The field shows a read-only preview of the contents
 * and allows you to edit the contents in a pop-up modal window.
 * @author mavi
 */
public class CKEditorField extends CustomField<String> {

    private final HorizontalLayout root = new HorizontalLayout();
    private final Label readOnlyView = new Label("", ContentMode.HTML);
    private final Button edit = new Button();

    public CKEditorField() {
        setWidth("100%");
        root.setWidth("100%");
        readOnlyView.setWidth("100%");
        root.addComponent(readOnlyView);
        root.setComponentAlignment(readOnlyView, Alignment.MIDDLE_LEFT);
        root.setExpandRatio(readOnlyView, 1f);
        root.addComponent(edit);
        edit.setIcon(FontAwesome.EDIT);
        edit.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        edit.addClickListener(e -> openEditWindow());
    }

    private void openEditWindow() {
        final Window window = new Window("Edit");
        window.setModal(true);
        window.setResizable(true);
        window.setClosable(true);
        final VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        final CKEditor editor = new CKEditor();
        editor.setText(getValue());
        content.addComponent(editor);
        content.setExpandRatio(editor, 1f);

        final HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.setMargin(true);
        buttonBar.setSpacing(true);
        final Button ok = new Button("Ok", e -> editor.flush());
        final Button cancel = new Button("Cancel", e -> window.close());
        buttonBar.addComponents(ok, cancel);
        content.addComponent(buttonBar);
        content.setComponentAlignment(buttonBar, Alignment.MIDDLE_RIGHT);

        window.center();
        window.setContent(content);
        window.setWidth("700px");
        window.setHeight("430px");
        UI.getCurrent().addWindow(window);
        editor.onTextChanged.add(text -> {
            String trimmedText = text.trim();
            if (trimmedText.startsWith("<p>") && trimmedText.endsWith("</p>")) {
                trimmedText = trimmedText.substring(3, trimmedText.length() - 4);
            }
            setValue(trimmedText);
            window.close();
        });
    }

    @Override
    protected void setInternalValue(String newValue) {
        super.setInternalValue(newValue);
        readOnlyView.setValue(getInternalValue());
    }

    @Override
    protected Component initContent() {
        final Panel panel = new Panel(root);
        panel.setWidth("100%");
        return panel;
    }

    @Override
    public Class<? extends String> getType() {
        return String.class;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        edit.setVisible(!readOnly);
    }
}
