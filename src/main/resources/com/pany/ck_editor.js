com_pany_CKEditor = function() {
    var self = this;
    var e = this.getElement();

    var textarea = document.createElement("textarea");
    e.appendChild(textarea);

    var editor = null;
    var delayedInit = null;
    var text = "";

    this.updateState = function() {
    };

    this.onStateChange = function() {
        if (delayedInit == null) {
            delayedInit = setTimeout(function() {
                editor = CKEDITOR.replace(textarea);
                self.updateState();
                editor.setData(text);
            }, 1000);
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
