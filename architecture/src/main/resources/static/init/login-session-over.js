hookAjax({
    //hook callbacks
    onreadystatechange:function(xhr){
        if(xhr.readyState == 4 && xhr.status == 999){
            if(window.top.$("#dialog-login") && window.top.$('#username')
                && window.top.$("#dialog-login").parent().is(":hidden") ) {
                window.top.$("#dialog-login").dialog('open');
                window.top.$('#password').textbox('textbox').focus();
            }
            return false;
        }

        if(xhr.readyState == 4 && xhr.status == 500){
            // console.log(xhr.responseText);
            //修复未选中TAB中提示框显示位置异常的问，并不是最优解，后期需要更新！！！
            var data = $.parseJSON(xhr.responseText);
            $.messager.alert("error",data.message,"error");

            if($(document.body).width() == "0"){
                $(document.body).width($(window.top.$('#body').tabs('getSelected')).width()-16);

                var messager = $(".messager-window");
                var shadow = $(".window-shadow");
                var left = ($(document.body).width() - messager.width()-16)/2;
                var top = 112;

                console.log("left"+left+"top"+top);

                messager.css("left",left);
                messager.css("top",top);
                shadow.css("left",left);
                shadow.css("top",top);

                $(document.body).width("");
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