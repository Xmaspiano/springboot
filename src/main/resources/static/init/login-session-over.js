hookAjax({
    //hook callbacks
    onreadystatechange:function(xhr){
        if(xhr.readyState == 4 && xhr.status == 999){
            if(top.$("#dialog-login") && top.$('#username') && top.$("#dialog-login").parent().is(":hidden") ) {
                top.$("#dialog-login").dialog('open');
                top.$('#password').textbox('textbox').focus();
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