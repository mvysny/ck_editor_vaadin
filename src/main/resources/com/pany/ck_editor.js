com_pany_CKEditor = function() {
    var self = this;
    var e = this.getElement();

    var textarea = document.createElement("textarea");
    e.appendChild(textarea);

    var editor = null;
    var delayedInit = null;

    this.updateState = function() {
        editor.setData(this.getState().text);
    };

    this.onStateChange = function() {
        if (delayedInit == null) {
            delayedInit = setTimeout(function() {
                editor = CKEDITOR.replace(textarea);
                self.updateState();
            }, 1000);
        } else if (editor != null) {
            this.updateState();
        }
    };

    this.onUnregister = function() {
        console.log("Unregistering CK Editor");
        if (delayedInit != null) {
            clearTimeout(delayedInit);
        }
        if (editor != null) {
            editor.destroy();
        }
    };
};
