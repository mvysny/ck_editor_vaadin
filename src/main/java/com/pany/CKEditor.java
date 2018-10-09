package com.pany;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * @author mavi
 */
@JavaScript({ "vaadin://ckeditor/ckeditor.js", "ck_editor.js" })
public class CKEditor extends AbstractJavaScriptComponent {

    public CKEditor(String text) {
        getState().text = text;
        setWidth("800px");
        setHeight("400px");
    }

    @Override
    protected CKEditorState getState() {
        return (CKEditorState) super.getState();
    }


    @Override
    protected CKEditorState getState(boolean markAsDirty) {
        return (CKEditorState) super.getState(markAsDirty);
    }

    public String getText() {
        return getState(false).text;
    }

    public void setText(String text) {
        getState().text = text;
    }
}
