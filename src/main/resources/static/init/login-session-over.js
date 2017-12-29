hookAjax({
    //hook callbacks
    onreadystatechange:function(xhr){
        if(xhr.readyState == 4 && xhr.status == 999){
            if($("#dialog-login") && $('#username') && $("#dialog-login").parent().is(":hidden") ) {
                $("#dialog-login").dialog('open');
                $('#password').textbox('textbox').focus();
            }
            return false;
        }
    },
    onload:function(xhr){
        // console.log("onload called: %O",xhr)
    },
    //hook function
    open:function(arg,xhr){
        // console.log("open called: method:%s,url:%s,async:%s",arg[0],arg[1],arg[2])
    }
});