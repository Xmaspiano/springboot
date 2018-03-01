<%--<%@ page import="com.SpringOS.util.MessageKey" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String loaclPath = "views.system.user.user.";
//    String form1_username = MessageKey.getMsg(loaclPath+"form1.username");
//    String form1_password = MessageKey.getMsg(loaclPath+"form1.password");
//    String dialog_button_apply = MessageKey.getMsg(loaclPath+"dialog.button.apply");
//    String dialog_button_cancel = MessageKey.getMsg(loaclPath+"dialog.button.cancel");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <style>.error{color:red;}</style>
    <%@include file="systemLayout/layout_system.jsp"%>
    <style>
        .e-icon{
            /*top: 6px;*/
            margin-right: 1px;
        }
        .textbox-addon-right{
            /*right: 0px;*/
            margin-top: 4px;
            margin-right: 3px;
        }
    </style>
</head>
<body>
<div ng-app="">
    <p>名字 : <input type="text" ng-model="name"></p>
    <h1>Hello {{name}}</h1>
</div>
this is view index
<a href="/oalink?url=/wui/main.jsp" target="_blank">OA link</a>
</body>
</html>