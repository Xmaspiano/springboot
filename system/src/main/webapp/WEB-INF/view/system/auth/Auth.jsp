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
    <%@ include file="/WEB-INF/view/systemLayout/layout_system_iframe.jsp"%>
</head>
<body style="width:100%;height:100%">
<div id="mainLayout-auth" class="easyui-layout" style="width:98%;height:380px;" >
    <div data-options="region:'west',split:true,title:'<m:info name='权限路径' />',collapsible:false" style="width:200px;">
        <div class="easyui-panel" data-options="fix:true,border:false">
            <ul id="menu_tree-auth"></ul>
        </div>
    </div>
    <div data-options="region:'center',title:'<m:info name='权限编辑' />',collapsible:false">
        <form id="form1" method="post">
            <input type="hidden" id="menuid" name="menuid" value=""/>

            <input type="hidden" id="deptid" name="deptid" value="${deptid}"/>
            <input type="hidden" id="roleid" name="roleid" value="${roleid}"/>
            <input type="hidden" id="userid" name="userid" value="${userid}"/>
            <input type="hidden" id="groupid" name="groupid" value="${groupid}"/>
            <input type="hidden" id="typeAuth" name="typeAuth" value="${typeAuth}"/>
            <table data-options="fix:true,border:false" id="table-auth" ></table>
        </form>
    </div>
</div>
</body>
<script>
    var editIndex = undefined;

    $(function() {
        var hei = $('#_auth_Iframe', parent.document).height() - 10;
        $('#mainLayout-auth').layout("resize",{height:hei});

        $('#table-auth').datagrid({
            url: '/auth/date_grid.json',
            loadMsg:"<m:info name='数据加载中...'/>",
            fitColumns:true,
            rownumbers:true,
//            singleSelect:true,
            checkOnSelect:true,
            selectOnCheck:true,
            toolbar:toolbar,
            queryParams: {
                menuid: $("#menuid").val(),
                deptid: $("#deptid").val(),
                roleid: $("#roleid").val(),
                userid: $("#userid").val(),
                groupid: $("#groupid").val(),
                typeAuth: $("#typeAuth").val()
            },
            columns: [[
                {field: 'ck', checkbox:true},
                {field: 'id', title: "<m:info name='主键'/>", width: 80, hidden:true},
                {field: 'keyname', title: "<m:info name='keyname'/>", width: 80, hidden:true},
                {field: 'name', title: "<m:info name='名称'/>", width: 80},
                {field: 'shiroAuth', title: "<m:info name='授权标识'/>", width: 80},
                {field: 'realName', title: "<m:info name='文件路径'/>", width: 220},
                {field: 'method', title: "<m:info name='方法'/>", width: 80},
                {field: 'checked', title: "<m:info name='是否授权'/>", width: 60, align:'center',align:'center'}
            ]],
            onCheck:onCheck,
            onUncheck:onUncheck,
            onSelectAll:onSelectAll,
            onUnselectAll:onUnselectAll
        });

        $('#menu_tree-auth').tree({
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
                $('#table-auth').datagrid('reload',{
                    menuid: data.id,
                    deptid: $("#deptid").val(),
                    roleid: $("#roleid").val(),
                    userid: $("#userid").val(),
                    groupid: $("#groupid").val(),
                    typeAuth: $("#typeAuth").val()
                });
            }
        });
    });

    //定义treegrid工具栏
    var toolbar = [{
        text:"<i class='icon-pencil'/>&nbsp;<m:info name='保存'/>",
        <%--<shiro:lacksPermission name="resources:save:edit">disabled:true,</shiro:lacksPermission>--%>
//        iconCls: 'e-icon icon-pencil',
        handler: function(){
            actionOver("save");
        }
    },'-'];

    //定义页面操作集合
    function actionOver(code) {
        switch (code) {
            case "save":
                save()
                break;
            default:
                $.messager.alert('信息提示','未定义操作...','info');
                return;
        }
    }

    function save(){
        var effectRow = new Object();

        effectRow["menuid"] = $('#menuid').val();
        effectRow["deptid"] = $('#deptid').val();
        effectRow["roleid"] = $('#roleid').val();
        effectRow["userid"] = $('#userid').val();
        effectRow["groupid"] = $('#groupid').val();
        effectRow["typeAuth"] = $('#typeAuth').val();

        var rows = $('#table-auth').datagrid('getRows');
        var checked = [];
        $.each(rows, function(i, item) {
            if(item['checked']){
                checked.push(item);
            }
        });
        effectRow["checked"] = checked;

        $.ajax({
            url:"auth/save",
            data:JSON.stringify(effectRow),
            type: "POST",
            dataType: "json",
            contentType:"application/json",
            onSubmit: function(){
                console.log(JSON.stringify(effectRow));
            },
            success: function (data) {
                if (data.status) {
                    $.messager.alert('信息提示', "保存成功...", 'info');
                } else {
                    $.messager.alert('Warning', data.message);
                }
            }
        });
    }

    function endEditing(){
        if (editIndex == undefined){return true}
        if ($('#table-auth').datagrid('validateRow', editIndex)){
            $('#table-auth').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }
    function onCheck(index){
        $('#table-auth').datagrid('beginEdit', index);
        $('#table-auth').datagrid('getRows')[index]['checked'] = true;
        $('#table-auth').datagrid('endEdit', index);
        $('#table-auth').datagrid('refreshRow',index);

    }

    function onUncheck(index){
        $('#table-auth').datagrid('beginEdit', index);
        $('#table-auth').datagrid('getRows')[index]['checked'] = false;
        $('#table-auth').datagrid('endEdit', index);
        $('#table-auth').datagrid('refreshRow',index);
    }

    function onSelectAll(){
        var rows = $('#table-auth').datagrid('getRows');
        $.each(rows, function(i) {
            onCheck(i);
        });
    }

    function onUnselectAll(){
        var rows = $('#table-auth').datagrid('getRows');
        $.each(rows, function(i) {
            onUncheck(i);
        });
    }

</script>
</html>