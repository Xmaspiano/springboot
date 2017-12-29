<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%pageContext.setAttribute("AppId",request.getServletPath());%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-sstore" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />

    <title>Title</title>

    <script type="text/javascript" src="/init/jquery-easyui-1.5.3/jquery.min.js"></script>
    <script type="text/javascript" src="/init/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/init/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>

    <link rel="stylesheet" type="text/css" href="/init/jquery-easyui-1.5.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/init/jquery-easyui-1.5.3/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/init/jquery-easyui-1.5.3/themes/color.css">
    <link rel="stylesheet" type="text/css" href="/init/font-awesome-4.7.0/css/font-awesome.min.css">
    <style>
        a:focus{outline:none;}
        .error{
            color: red;
        }
    </style>
</head>
<body>
<div id="dialog-login" title="登陆" class="easyui-dialog" style="width:330px;height:220px;padding:5px;"
     data-options="closed:true,resizable:false,buttons:button_dialog,draggable:false,closable:false">
    <div id="error" class="error" style="height:15px;">${error}</div>
    <form id="form1" action="/login" method="post">
        <table style="width: 100%">
            <tr>
                <td rowspan="2">
                    <img class="easyui-linkbutton" src="/init/yellowman.png" style="height: 80px">
                </td>
                <td></td>
                <td><input class="easyui-textbox" type="text" id="username" name="username"
                           data-options="prompt:'用户名',iconCls:'icon-man',required:false" value="${username}"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input class="easyui-textbox" type="password" id="password" name="password"
                           data-options="prompt:'******',iconCls:'icon-lock',required:false" value=""/></td>
            </tr>
            <tr>
                <td colspan="2">自动登录：</td>
                <td><input type="checkbox" id="rememberMe" name="rememberMe" value="true"></td>
            </tr>
        </table>
    </form>
</div>
</body>
<script>
    //页面渲染完成 <%= pageContext.getAttribute("AppId")%>

    $.parser.onComplete = function(){
        if (window.top != window.self) {
                window.top.location = window.location;
        }else {
            $("#dialog-login").dialog('open');
            $('#username').textbox('textbox').focus();
        }
    };

    $(window).keyup(function(event){
        if( !$("#dialog-login").parent().is(":hidden")) {
            switch (event.keyCode) {
                case 13://enter
                    if ($("#username").val() == "") {
                        $("#error").html("用户名不能为空");
                        return false;
                    }
                    if ($("#password").val() == "") {
                        $("#error").html("密码不能为空");
                        return false;
                    }
                    _ajax_Login();
                    break;
            }
        }
    });
    //定义dialog对话框按钮
    var button_dialog = [{
        text:'<i class="fa fa-check fa-lg"/>&nbsp;提交',
        handler:_ajax_Login
    }];

    function _ajax_Login(){
        $.ajax({
            type: 'post',
            url: 'login',
            data: $("#form1").serialize(),
            success: function(result) {
                $("#password").val("");
                if(result.status){
                    window.top.location = result.forword;
                }else{
                    $("#error").html(result.error);
                }
            }
        });
    }
</script>
</html>