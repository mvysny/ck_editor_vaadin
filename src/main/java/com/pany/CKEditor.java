package com.pany;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import elemental.json.JsonArray;

/**
 * @author mavi
 */
@JavaScript({ "vaadin://ckeditor/ckeditor.js", "ck_editor.js" })
public class CKEditor extends AbstractJavaScriptComponent {

    private String text;

    public CKEditor() {
        setWidth("800px");
        setHeight("400px");
        addFunction("onText", new JavaScriptFunction() {
            @Override
            public void call(JsonArray arguments) {
                text = arguments.getString(0);
                for (Consumer<String> consumer : onTextChanged) {
                    consumer.accept(getText());
                }
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
        return text;
    }

    public void setText(String text) {
        callFunction("setText", text);
    }

    public void flush() {
        callFunction("flush");
    }
}
