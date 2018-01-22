<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>OA辅助管理系统</title>
    <%@include file="layout_system.jsp"%>

</head>
<style>
    .right-float {
        float:right;
    }
    .left-float {
        float:left;
    }
    .hid-overflow {
        /*overflow:scroll;*/
        overflow:hidden;
    }
    .error{
        color: red;
    }
</style>
<%@ include file="loading_system.jsp"%>
<div id="dialog-login" title="登陆" class="easyui-dialog" style="width:330px;height:220px;padding:5px;"
     data-options="modal:true,closed:true,resizable:false,buttons:button_dialog,draggable:false,closable:false">
    <div id="error" class="error" style="height:15px;">${error}</div>
    <form id="form1" action="/login" method="post">
        <table style="width: 100%">
            <tr>
                <td rowspan="2">
                    <img class="easyui-linkbutton" src="/init/yellowman.png" style="height: 80px">
                </td>
                <td></td>
                <td><input class="easyui-textbox" type="text" id="username" name="username"
                           data-options="prompt:'用户名',iconCls:'icon-man',required:false" value="<shiro:principal property="loginid" />"/></td>
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
                    $("#error").html("");
                    $("#dialog-login").dialog('close');
                }else{
                    $("#error").html(result.error);
                }
            }
        });
    }
</script>
<body id="cc" class="easyui-layout">
<div data-options="region:'north',border:false" style="height:50px;padding:0px" >
    <div id="numm" class="easyui-meun left-float" style="padding:5px;">
        <img class="easyui-linkbutton" src="/init/yellowman.png" style="height: 40px">
        <a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="addTabIndex()"><m:info name='首页'/></a>
        <a id="menuList" href="#" class="easyui-menubutton" data-options="menu:'#mm1'"><m:info name='菜单'/></a>
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm2'"><m:info name='收藏夹'/></a>
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm3'"><m:info name='关于'/></a>
    </div>

    <div class="easyui-meun right-float" style="padding:5px;">
        Hello, <span style="color: #00bbee">
            <shiro:principal property="lastname" />&nbsp;
        </span>
        <%--<a href="#" class="easyui-menubutton" data-options="menu:'#mm5'"><m:info name='系统</a>--%>
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm4'"><m:info name='设置'/></a>
        <img class="easyui-linkbutton" src="/init/yellowman.png" style="height: 40px">
    </div>

    <div id="mm1" style="width:150px;">

    </div>

    <div id="mm2" style="width:100px;">

    </div>

    <div id="mm3" class="menu-content" style="background:#f0f0f0;padding:10px;text-align:left">
        <p style="font-size:14px;color:#444;">Try jQuery EasyUI to build your modern, interactive, javascript applications.</p>
    </div>

    <div id="mm4" style="width:100px;">
        <div onclick="window.location.href = '/logout' "><m:info name='退出'/></div>
    </div>

    <%--<div id="mm5" style="width:100px;">--%>
    <%--<div onclick="addTab('<m:info name='组织机构管理'/>','/system/dept/dept')"><m:info name='收藏'/></div>--%>
    <%--</div>--%>
</div>
<div class="hid-overflow" data-options="region:'west',split:true,collapsed:false,title:'<m:info name='菜单目录'/>'" style="width:200px;" >
    <%@ include file="home/left.jsp"%>
</div >
<div data-options="region:'center',border:false" >
    <div id="body" class="easyui-tabs" style="height:100%">
        <%-- tab --%>
        <div class="hid-overflow" title="<m:info name='首页'/>" closable="true">
            <iframe scrolling="auto" frameborder="0"  src="/index" style="width:100%;height:100%;"></iframe>
        </div>

    </div>
</div>
<div id="SuperWin"></div>
<%--<div data-options="region:'south',border:false" style="height: 23px;">--%>
<%--<div id="div_json" class="footer" style="text-align-all: center">Copyright © RDIFramework.NET V2.9, All Rights Reserved</div>--%>
<%--</div>--%>
</body>
<script type="text/javascript">
    $(function(){
        $(document).bind('contextmenu',function(e){
            e.preventDefault();
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        });
        var menu_list = getCookie("MENU_LIST");
        if(menu_list != null && menu_list != ""){
            var jsonObj = JSON.parse(menu_list);
            for(var i = 0; i<jsonObj.length; i++){
                if(jsonObj[i] != null) {
                    if(i == 0) {
                        addTabByClick(jsonObj[i]);
                    }else if(jsonObj[i].title && jsonObj[i].url){
                        addTab(jsonObj[i]);
                    }else{
                        alert(jsonObj[i].title +" && "+ jsonObj[i].url);//!!!!!!!!!!!!!!!!!!!!!!!!
                    }
                    change_favarite_icon(jsonObj[i].favarite, jsonObj[i].title);
                }
            }
        }
        $('#body').tabs({
            tools:[{
                text:'<i class="fa fa-star-o" />',
                width:26,
//                iconCls: 'e-icon icon-star-empty icon-large',
                handler:function(){
                    var tab = $('#body').tabs('getSelected');
                    clear_favarite(tab);
                }
            },{
                text:'<i class="fa fa-cogs" />',
                width:26,
//                iconCls: 'e-icon icon-cogs icon-large',
                handler:function(){
                    alert("洪荒之力用完啦...");
                }
            }],
            onBeforeClose: function(title){
                var jsonObj = JSON.parse(getCookie("MENU_LIST"));
                removeJsonMenu(jsonObj, title);
                save_menu_list(jsonObj);
                if(jsonObj == ''){
                    $('#menuList').menubutton({text: '<m:info name='菜单'/>'});
                    show_menu_data(1);
                }
            },
            onSelect: function(title, index){
                menu_list = JSON.parse(getCookie("MENU_LIST"));
                var jsonObj = getJsonMenu(menu_list, title);
                ajax_getTreeTagById(jsonObj.id);
            }
        });
        if(jsonObj.length == 0) {
            show_menu_data(1);
//            addTabIndex();
        }
        refush_favarite();
    });
    //页面渲染完成
    $.parser.onComplete = function(){
        loading_close(1000);
    }
    function clear_favarite(tab){
        if (tab){
            var title = tab.panel('options').title;
            var tab_title = $("li.tabs-selected").find($(".tabs-title")).text();
            var flag = tab_title == title;//判断表头信息和注册表单是否相等
            change_favarite_icon(flag, title);
            change_favarite(title, flag);
        }
    }
    function refush_favarite(){
        //收藏夹初始化
        $.ajax({
            url: "/favarite/menu_favarite_url.json",
            type:"POST",
            success: function(data){
                $('#mm2').empty();
                $.each(data.rows, function(i, item) {
                    $('#mm2').menu('appendItem', {
                        text: item.text,
                        id:item.id,
                        onclick: function(){
                            if(item.children == null){
                                ajax_getTreeTagById(item.id);
                            }
                        }
                    });
                });
            }
        });
    }
    function change_favarite_icon(flag, title){
        if(flag) {
            $("li.tabs-selected").find($(".tabs-title")).html('<i id="title" class="fa fa-star"/>&nbsp;'+title);
        }else{
            $("li.tabs-selected").find($(".tabs-title")).html(title);
        }
    }
    function change_favarite(title, flag){
        var menu_list = getCookie("MENU_LIST");
        if(menu_list != null && menu_list != ""){
            var jsonObj = JSON.parse(menu_list);
            for(var i = 0; i<jsonObj.length; i++){
                if(jsonObj[i] != null && jsonObj[i].title == title) {
                    jsonObj[i].favarite = flag;
                    $.ajax({
                        url: "/favarite/check",
                        data:{menuid:jsonObj[i].id, title:jsonObj[i].title, flag:jsonObj[i].favarite},
                        type:"POST",
                        success: function(data){
                            refush_favarite();//刷新收藏夹
                        }
                    });
                }
            }
            save_menu_list(jsonObj);
        }
    }
    function addTabByClick(jsonObj){
        var title, id, url, parentId, parentName;
        title = jsonObj.title == null?jsonObj.title:jsonObj.title;
        id = jsonObj.id;
        url = jsonObj.url;
        parentId = jsonObj.parentId;
        parentName = jsonObj.parentName;

        if(parentName == '首页'){
            $('#menuList').menubutton({text: '<m:info name='菜单'/>'});
        }else {
            $('#menuList').menubutton({text: parentName});
        }
        jsonObj.favariteFlag = $('#mm2').menu('findItem', title) != null;
        show_menu_data(parentId);
        set_menu_remenber(jsonObj);
        addTab(jsonObj);
        if(jsonObj.favariteFlag){
            change_favarite_icon(jsonObj.favariteFlag, title);
        }
    }

    function addTabIndex(){
        var jsonObj = new Object();
        jsonObj.title = "首页";
        jsonObj.url = "/index";
        jsonObj.id = "1";
        jsonObj.parentId = "";
        jsonObj.parentName = "";
        jsonObj.favariteFlag = "";
        addTabByClick(jsonObj);
    }


    function addTab(jsonObj){
        var title, url, id;
        title = jsonObj.title;
        url = jsonObj.url;
        id = jsonObj.id;

//        parentName = parentName==null?title:parentName;
        if ($('#body').tabs('exists', title)){
            $('#body').tabs('select', title);
        } else if (title) {
            var content = '<iframe scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;height:100%;"></iframe>';
            $('#body').tabs('add',{
                title:title,
                content:content,
                id:id,
                fit:true,
                closable:true,
                iconCls:'',
//                toolPosition:'left',
            });
            $('#body').tabs('getTab', title).css('overflow', 'hidden');
        }
    }
    function set_menu_remenber(jsonObj){
        var menu_name,menu_id,menu_url,menu_parentId,menu_parentName,menu_favarite

        menu_name = jsonObj.title;
        menu_id = jsonObj.id;
        menu_url = jsonObj.url;
        menu_parentId = jsonObj.parentId;
        menu_parentName = jsonObj.parentName;
        menu_favarite = jsonObj.favariteFlag;

        menu_favarite = menu_favarite==null?false:menu_favarite;
        if(getCookie("MENU_LIST") == null || getCookie("MENU_LIST") == ""){
            var jsonObj = [{title:menu_name, id:menu_id, url:menu_url, parentId:menu_parentId, parentName:menu_parentName, favarite:menu_favarite}];
        }else{
            var jsonObj = JSON.parse(getCookie("MENU_LIST"));
            if(getJsonMenu(jsonObj, menu_name) == null && menu_name !=""){
                jsonObj[jsonObj.length] = {
                    title:menu_name,
                    id:menu_id,
                    url:menu_url,
                    parentId:menu_parentId,
                    parentName:menu_parentName,
                    favarite:menu_favarite
                };
            }else{
                return;
            }
        }
        save_menu_list(jsonObj);
    }
    function set_menu_favarite(menu_index, is_favarite){
        if(getCookie("MENU_FAVARITE") == null || getCookie("MENU_FAVARITE") == ""){
            var jsonObj = [{index:menu_index, favarite:is_favarite}];
        }else{
            var jsonObj = JSON.parse(getCookie("MENU_FAVARITE"));
            if(getJsonMenu(jsonObj, menu_index) == null){
                jsonObj[jsonObj.length] = {
                    index:menu_index,
                    favarite:is_favarite
                };
            }else{
                return;
            }
        }
        save_menu_list(jsonObj);
    }
    function save_menu_list(jsonObj){
        var jsonTab = "MENU_LIST="+JSON.stringify(jsonObj);
        document.cookie= jsonTab;
//        $.ajax({
//            url: "/home/savetab?jsonTab="+jsonTab,
//            type:"POST",
//            success: function(data){
//
//            }
//        });
    }
    function getJsonMenu(jsonObj,title){
        for(var i = 0; i<jsonObj.length; i++){
            if(jsonObj[i] != null &&jsonObj[i].title == title){
                return jsonObj[i];
            }
        }
        return null;
    }
    function removeJsonMenu(jsonObj,title){
//        var index = -1;
        for(var i = 0; i<jsonObj.length; i++){
            if(jsonObj[i] != null && jsonObj[i].title == title){
                jsonObj.splice(i,1);
                return;
//                delete jsonObj[i];
            }
        }
//        jsonObj.remove()
    }
    function getCookie(c_name)
    {
        if (document.cookie.length>0)
        {
            c_start=document.cookie.indexOf(c_name + "=")
            if (c_start!=-1)
            {
                c_start=c_start + c_name.length+1
                c_end=document.cookie.indexOf(";",c_start)
                if (c_end==-1) c_end=document.cookie.length
                return unescape(document.cookie.substring(c_start,c_end))
            }
        }
        return "[]"
    }
    function show_menu_data(pid){
        pid = pid == undefined?0:pid;
        $.ajax({
            url: "/menu/tag/menu_tree.json?parentid="+pid,
            type:"POST",
            success: function(data){
                if(data.rows) {
                    $('#mm1').empty();
                    set_menu_data(data.rows);
                }
            }
        });
    }
    //第一层
    function set_menu_data(data, target){
        $.each(data, function(i, item) {
            $('#mm1').menu('appendItem', {
                parent:target,
                text: item.text,
                id:item.id,
                onclick: function(){
                    if(item.children == null){
                        ajax_getTreeTagById(item.id);
                    }
                }
            });
            if(item.children != null){
                var m_it = $('#mm1').menu('findItem', item.text).target;
                set_menu_data(item.children, m_it);
            }
        });
    }
</script>
</html>