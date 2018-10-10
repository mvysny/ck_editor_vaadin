package com.pany;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author mavi
 */
@JavaScript({ "vaadin://ckeditor/ckeditor.js", "ck_editor.js" })
public class CKEditor extends AbstractJavaScriptComponent {

    public CKEditor() {
        setWidth("800px");
        setHeight("400px");
        addFunction("onText", arguments -> {
            getState(false).text = arguments.getString(0);
            for (Consumer<String> consumer : onTextChanged) {
                consumer.accept(getText());
            }
        });
    }

    public final List<Consumer<String>> onTextChanged = new LinkedList<>();

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
        callFunction("setText", text);
    }

    public void flush() {
        callFunction("flush");
    }
}
