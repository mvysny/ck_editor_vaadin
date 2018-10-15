package com.github.ckeditor;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * A thin wrapper over the <a href="https://ckeditor.com/ckeditor-4/">CK Editor 4</a>.
 * @author mavi
 */
@JavaScript({ "vaadin://ckeditor/ckeditor.js", "ck_editor.js" })
public class CKEditor extends AbstractJavaScriptComponent {

    public CKEditor() {
        setWidth("800px");
        setHeight("400px");
        addFunction("onText", arguments -> {
            getState(false).text = arguments.getString(0);
            for (OnTextChanged consumer : onTextChanged) {
                consumer.onTextChanged(getText());
            }
        });
    }

    /**
     * Invoked when the text in the CK Editor changes.
     */
    public interface OnTextChanged extends Serializable {
        /**
         * Invoked when the text in the CK Editor changes.
         * @param newText the new text including HTML tags.
         */
        void onTextChanged(String newText);
    }

    /**
     * Invoked when the text in the CK Editor changes.
     * <p></p>
     * Note: unfortunately a JavaScript component can't notify Vaadin of value changes because of this
     * <a href="https://github.com/vaadin/framework/issues/11238">Vaadin limitation</a>. Therefore, you need to call {@link #flush()}
     * first, which will then notify all listeners registered here.
     */
    public final List<OnTextChanged> onTextChanged = new LinkedList<>();

    @Override
    protected CKEditorState getState() {
        return (CKEditorState) super.getState();
    }


    @Override
    protected CKEditorState getState(boolean markAsDirty) {
        return (CKEditorState) super.getState(markAsDirty);
    }

    /**
     * Returns the current text as present in the CK Editor.
     * <p></p>
     * Note! Because of <a href="https://github.com/vaadin/framework/issues/11238">Vaadin limitation</a> you need to call {@link #flush()}
     * to obtain the newest text.
     * @return the current HTML text.
     */
    public String getText() {
        return getState(false).text;
    }

    /**
     * Sets the text to the CK Editor.
     * @param text a html-based text, not null.
     */
    public void setText(String text) {
        getState().text = text;
        callFunction("setText", text);
    }

    /**
     * Flushes the client-side CK Editor contents and calls the {@link #onTextChanged} listeners. Workaround for
     * <a href="https://github.com/vaadin/framework/issues/11238">Vaadin limitation</a>.
     */
    public void flush() {
        callFunction("flush");
    }
}
