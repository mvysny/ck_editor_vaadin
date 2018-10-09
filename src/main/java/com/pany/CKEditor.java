package com.pany;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * @author mavi
 */
@JavaScript({ "ck_editor.js", "https://cdn.ckeditor.com/4.8.0/standard-all/ckeditor.js" })
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
}