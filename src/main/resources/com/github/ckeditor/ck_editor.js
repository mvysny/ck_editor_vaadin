com_github_ckeditor_CKEditor = function() {
    var self = this;
    var e = this.getElement();

    var textarea = document.createElement("textarea");
    e.appendChild(textarea);

    var editor = null;
    // In some cases the CK Editor initializes so slowly that an unfortunate timing of JS event loop would have us call CKEDITOR.replace
    // before CKEDITOR is fully initialized, resulting in random error that some field is 'undefined'.
    // We therefore need to wait a bit (until all JSs are fully loaded) and only then we can call CKEDITOR.replace().
    // We will use the setTimeout() method to do that.
    var delayedInit = null;

    // we cannot store the text into the CKEditorState, since setting the text to the self.getState().text does not work since that's
    // not propagated to the server-side. Read the docs on the flush() method for more details.
    //
    // Therefore, we can't simply update the text from the state in the onStateChange() method to the editor itself, since
    // the text there is old and would overwrite our changes. Therefore, we have a setText() call that sets the text to the editor
    // (or to this variable if the editor is not yet initialized because of delayedInit).
    var text = "";

    this.updateState = function() {
        // in the future you can poll self.getState() (which is of type CKEditorState) and update `editor`
        // Currently there is nothing to update, so this function is kept empty.
    };

    this.onStateChange = function() {
        if (delayedInit == null) {
            delayedInit = setTimeout(function() {
                editor = CKEDITOR.replace(textarea, {
                    resize_enabled: false
                });
                self.updateState();
                editor.setData(self.getState().text);
                delayedInit = setTimeout(function() {
                    editor.resize(400, 300);
                }, 100);
            }, 100);
        } else if (editor != null) {
            this.updateState();
        }
    };

    this.onUnregister = function() {
        console.log("Unregistering CK Editor");
        if (delayedInit != null) {
            clearTimeout(delayedInit);
            delayedInit = null;
        }
        if (editor != null) {
            editor.destroy();
            editor = null;
        }
    };

    // setting the text to the self.getState().text on every value change will not work as expected because the state is not propagated
    // to the server-side. The only way would be to fire a value change event after every keystroke, which is an overkill.
    // firing those events delayed could cause old text to be grabbed from the component if the server-side code reads it prior event is fired.
    // The typical solution is to fire a non-immediate events but that's not possible because of https://github.com/vaadin/framework/issues/11238
    //
    // The workaround here is to have the server call this method. This method will read the newest editor state and will call a Vaadin
    // onText() callback with the newest text.
    this.flush = function() {
        self.onText(editor.getData());
    };

    this.setText = function(newtext) {
        text = newtext;
        if (editor != null) {
            editor.setData(text);
        }
    }
};
