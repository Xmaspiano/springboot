<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@page contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
<%
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    String APPID = this.getClass().getName();
    LOGGER.info(APPID);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="../layout_system.jsp"%>
</head>
<body>
<div id="mainLayout" class="easyui-layout" style="width:100%;height:500px;">
    <div data-options="region:'west',split:true,title:'<m:info name='权限路径' />',collapsible:false" style="width:200px;">
        <div class="easyui-panel" data-options="fix:true,border:false">
            <ul id="menu_tree"></ul>
        </div>
    </div>
    <div data-options="region:'center',title:'<m:info name='权限编辑' />',collapsible:false">
        <form id="form1" method="post">
            <input type="hidden" id="menuid" name="menuid" value=""/>
        <table data-options="border:false" id="table-resourcesmenu" ></table>
        </form>
    </div>
    <div data-options="region:'east',split:true,collapsible:false" title="<m:info name='权限路径' />" style="width:230px;">
        <table data-options="fix:true,border:false" id="table-resources-view" ></table>
    </div>
</div>
</body>
<script>
    $(function() {
        //初始化treegrid数据
        $('#table-resources-view').datagrid({
            url: '/resources/date_grid.json',
            loadMsg:"<m:info name='数据加载中...'/>",
            fitColumns:true,
            rownumbers:true,
//            singleSelect:true,
            checkOnSelect:true,
            selectOnCheck:true,
            toolbar:toolbar_view,
            columns: [[
                {field: 'ck1', checkbox:true},
                {field: 'id', title: "<m:info name='主键'/>", width: 80, hidden:true},
                {field: 'keyname', title: "<m:info name='keyname'/>", width: 80, hidden:true},
                {field: 'realName', title: "<m:info name='文件路径'/>", width: 220, hidden:true},
                {field: 'name', title: "<m:info name='名称'/>", width: 80, hidden:true},
                {field: 'method', title: "<m:info name='方法'/>", width: 80, hidden:true},
                {field: 'shiroAuth', title: "<m:info name='授权标识'/>", width: 80},
                {field: 'available', title: "<m:info name='是否启用'/>", width: 60, hidden:true}
            ]],
            onDblClickRow:function(data){
                actionOver("add_elecment");
            }
        });

        $('#table-resourcesmenu').datagrid({
            url: '/resourcesmenu/date_grid.json',
            loadMsg:"<m:info name='数据加载中...'/>",
            fitColumns:true,
            rownumbers:true,
//            singleSelect:true,
            checkOnSelect:true,
            selectOnCheck:true,
            toolbar:toolbar,
            queryParams: {
                menuid: $("menuid").val()
            },
            columns: [[
                {field: 'ck2', checkbox:true},
                {field: 'id', title: "<m:info name='主键'/>", width: 80, hidden:true},
                {field: 'keyname', title: "<m:info name='keyname'/>", width: 80, hidden:true},
                {field: 'realName', title: "<m:info name='文件路径'/>", width: 220},
                {field: 'name', title: "<m:info name='名称'/>", width: 80},
                {field: 'method', title: "<m:info name='方法'/>", width: 80},
                {field: 'shiroAuth', title: "<m:info name='授权标识'/>", width: 80},
                {field: 'available', title: "<m:info name='是否启用'/>", width: 60}
            ]],
            onDblClickRow:function(data){
                actionOver("remove_elecment")
            }
        });

        $('#menu_tree').tree({
            url:"/menu/tag/menu_tree.json?parentid=-1",
            type:"POST",
            lines:true,
            loadFilter: function(data){
                if (data.rows){
                    return data.rows;
                } else {
                    return data;
                }
            },
            onClick:function(data){//tree菜单点击事件
                $("#menuid").val(data.id);
                $('#table-resourcesmenu').datagrid('reload',{menuid: data.id});
            }
        });
    });
    //定义treegrid工具栏
    var toolbar_view = [{
        text:"<i class='fa fa-plus'/>&nbsp;<m:info name='添加'/>",
        <%--<shiro:lacksPermission name="resources:save:edit">disabled:true,</shiro:lacksPermission>--%>
        handler: function(){
            actionOver("add_elecment");
        }
    },{
        text:"<i class='icon-remove'/>&nbsp;<m:info name='同步全部'/>",
        <%--<shiro:lacksPermission name="resources:synchronous">disabled:true,</shiro:lacksPermission>--%>
//        iconCls: 'e-icon icon-remove',
        handler: function(){
            actionOver("synchronous");
        }
    },'-'];

    //定义treegrid工具栏
    var toolbar = [{
        text:"<i class='icon-pencil'/>&nbsp;<m:info name='保存'/>",
        <%--<shiro:lacksPermission name="resources:save:edit">disabled:true,</shiro:lacksPermission>--%>
//        iconCls: 'e-icon icon-pencil',
        handler: function(){
            actionOver("save");
        }
    },{
        text:"<i class='icon-pencil'/>&nbsp;<m:info name='移除'/>",
        <%--<shiro:lacksPermission name="resources:save:edit">disabled:true,</shiro:lacksPermission>--%>
//        iconCls: 'e-icon icon-pencil',
        handler: function(){
            actionOver("remove_elecment");
        }
    },'-'];

    //定义页面操作集合
    function actionOver(code) {
        switch (code) {
            case "save":
                save()
                break;
            case "remove_elecment":
                remove_elecment()
                break;
            case "add_elecment":
                add_elecment();
                break;
            case "synchronous":
            <%--<shiro:hasPermission name="resources:synchronous">--%>
                synchronousRow();
                break;
            default:
                $.messager.alert('信息提示','未定义操作...','info');
                return;
        }
    }

    function save(){
        var effectRow = new Object();
        effectRow["menuid"] = $('#menuid').val();
        effectRow["inserted"] = jQuery('#table-resourcesmenu').datagrid('getChanges', 'inserted');
        effectRow["deleted"] = jQuery('#table-resourcesmenu').datagrid('getChanges', 'deleted');
        effectRow["updated"] = jQuery('#table-resourcesmenu').datagrid('getChanges', 'updated');

//        $('#form1').form('submit', {
        $.ajax({
            url:"resourcesmenu/save",//+$('#form1').serialize(),
            data:JSON.stringify(effectRow),
            type: "POST",
            dataType: "json",
            contentType:"application/json",
            onSubmit: function(){
                console.log(JSON.stringify(effectRow));
            },
            success: function (data) {
                if (data.status) {
                    $('#table-resourcesmenu').datagrid('reload',{menuid: $("#menuid").val()});
                    $.messager.alert('信息提示', "保存成功...", 'info');
                } else {
                    $.messager.alert('Warning', data.message);
                }
            }
        });
    }

    function remove_elecment(){
        var rows = $('#table-resourcesmenu').datagrid('getSelections');
        //获取datagrid选中行
        for (var i = 0; i < rows.length; i++) {
            var index = $('#table-resourcesmenu').datagrid('getRowIndex',rows[i]);
            $('#table-resourcesmenu').datagrid('deleteRow', index);
        }
    }

    function add_elecment(){
        if($("#menuid").val() == ""){
            $.messager.alert('信息提示','请选择目录...','info');
            return false;
        }

        var rows = $('#table-resources-view').datagrid('getSelections');
        //获取datagrid选中行
        for (var i = 0; i < rows.length; i++) {
            var index = $('#table-resourcesmenu').datagrid('getRowIndex',rows[i]);
            if(index < 0 && avil(rows[i])) {
                $('#table-resourcesmenu').datagrid('appendRow', rows[i]);
            }
        }
    }

    //同步数据
    function synchronousRow() {
        $.ajax({
            url: "resources/synchronous?id=-1&keyname=0",
            type: "POST",
            success: function (data) {
                if (data.status) {
                    $('#table-resources-view').datagrid("reload");
                } else {
                    $.messager.alert('Warning', data.message);
                }
            }
        });
    }

    function avil(row_i){
        var rows = $('#table-resourcesmenu').datagrid('getRows');
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].keyname == row_i.keyname){
                return false;
            }
        }
        return true;
    }
</script>
</html>