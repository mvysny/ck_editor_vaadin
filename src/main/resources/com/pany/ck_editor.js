com_pany_CKEditor = function() {
    var e = this.getElement();
    var eid = "editor" + this.getConnectorId();
    e.innerHTML = "<textarea id=\"" + eid + "\">";

    var editor;

    setTimeout(function() { CKEDITOR.replace(eid); }, 2000);

    console.log("CK Editor attached to textarea eid=" + eid);

    this.onStateChange = function() {
        // editor.setData(this.getState().text);
    };

    this.onUnregister = function() {
        console.log("Unregistering CK Editor");
        // editor.destroy();
    };
};
