<%@page contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/view/systemLayout/layout_system_iframe.jsp"%>
</head>
<style>
    .e-icon{
        top: 6px;
    }
</style>
<%-- 调用方法script --%>
<script>

</script>
<body>
<table id="table-shiro-resources" title="<i class='icon-save'/>&nbsp;<m:info name='资源编辑'/>" style="width:100%;height:400px"></table>
<div id="dialog-shiro-resources" title="" class="easyui-dialog" style="width:500px;height:400px;"
     data-options="left:360,top:70,closed:true,resizable:false,modal:true,buttons:button_dialog">
    <form id="form1-shiro-resources" method="post">
        <input type="hidden" id="id" name="id" value=""/>
        <input type="hidden" id="available" name="available" value="0"/>
        <%--<input type="hidden" id="resourcesId" name="resourcesId" value="1"/>--%>
        <table style="width: 100%;height:70px;">
            <tr>
                <td><m:info name='资源路径'/></td>
                <td><input class="easyui-textbox" type="text" id="realName" name="realName" data-options="required:true"/></td>
                <td><m:info name='资源名称'/></td>
                <td><input class="easyui-textbox" id="name" name="name" data-options="required:true"/></td>
            </tr>
            <tr>
                <td><m:info name='方法名称'/></td>
                <td><input class="easyui-textbox" type="text" id="method" name="method" data-options="required:true"/></td>
                <td><m:info name='shiro权限名称'/></td>
                <td><input class="easyui-textbox" id="shiroAuth" name="shiroAuth" data-options="required:true"/></td>
            </tr>
            <tr>
                <td><m:info name='是否锁定'/></td>
                <td>
                    <a id="btn" href="#" class="easyui-linkbutton"
                       data-options="toggle:true,selected:false" onclick="clickLife()">
                        <m:info name='未锁定'/>
                    </a>
                </td>
            </tr>
        </table>
        <div style="margin:10px 0 10px 0;"/>
    </form>
    <%-- 表单选择插件 --%>
    <%--<div class="easyui-panel" title="角色选择" style="height:236px;"  data-options="border:false">--%>
    <%--<ul id="resources_tree"></ul>--%>
    <%--</div>--%>
</div>
</body>
<%--初始化Html Script--%>
<script>
    $(function() {
        //初始化treegrid数据
        $('#table-shiro-resources').datagrid({
            title:"<i class='icon-save'/>&nbsp;<m:info name='资源编辑'/>",
            url: '/shiroResources/date_grid.json',
            loadMsg:"<m:info name='数据加载中...'/>",
            fitColumns:true,
//            fit:true,
//            resizable:true,
//            fixed:true,
//            striped:true,
            rownumbers:true,
            singleSelect:true,

            toolbar:toolbar,
            columns: [[
                {field: 'id', title: "<m:info name='主键'/>", width: 80, hidden:true},
                {field: 'realName', title: "<m:info name='文件路径'/>", width: 220},
                {field: 'name', title: "<m:info name='名称'/>", width: 80},
                {field: 'method', title: "<m:info name='方法'/>", width: 80},
                {field: 'shiroAuth', title: "<m:info name='授权标识'/>", width: 80},
                {field: 'available', title: "<m:info name='是否启用'/>", width: 60}
            ]],
            onDblClickRow:function(data){
                actionOver("edit")
            }
        });

        //初始化选择插件

    });

    //定义treegrid工具栏
    var toolbar = [{
        text:"<i class='icon-plus'/>&nbsp;<m:info name='新增'/>",
        <%--<shiro:lacksPermission name="resources:save:add">disabled:true,</shiro:lacksPermission>--%>
//        iconCls: 'e-icon icon-plus',
        handler: function(){
            actionOver("add");
        }
    },{
        text:"<i class='icon-pencil'/>&nbsp;<m:info name='修改'/>",
        <%--<shiro:lacksPermission name="resources:save:edit">disabled:true,</shiro:lacksPermission>--%>
//        iconCls: 'e-icon icon-pencil',
        handler: function(){
            actionOver("edit");
        }
    },{
        text:"<i class='icon-remove'/>&nbsp;<m:info name='删除'/>",
        <%--<shiro:lacksPermission name="resources:delete">disabled:true,</shiro:lacksPermission>--%>
//        iconCls: 'e-icon icon-remove',
        handler: function(){
            actionOver("delete");
        }
    },'-'];

    //定义dialog对话框按钮
    var button_dialog = [{
        text:"<i class='icon-ok'/>&nbsp;<m:info name='确认'/>",
//        iconCls: 'e-icon icon-ok',
        handler: function(){
            submitApply();
            parent._left_TreeReload();
        }
    },{
        text:"<i class='icon-remove'/>&nbsp;<m:info name='取消'/>",
//        iconCls: 'e-icon icon-remove',
        handler:function(){
            actionOver("colse");
        }
    }];

    //定义页面操作集合
    function actionOver(code){
        switch(code){
            case "colse":
                $('#dialog-shiro-resources').dialog('close');
                break;
            case "reload":
                $('#table-shiro-resources').datagrid("reload");
                break;
            case "add":
            <shiro:hasPermission name="resources:save:add">
                addChangeresourcesTree();
                $('#dialog-shiro-resources').dialog({title: "<m:info name='新增资源'/>"});
                $('#dialog-shiro-resources').dialog('open');
                break;
                </shiro:hasPermission>
            case "edit":
            <shiro:hasPermission name="resources:save:edit">
                if(editChangeresourcesTree()) {
                    $('#dialog-shiro-resources').dialog({title: "<m:info name='修改资源'/>"});
                    $('#dialog-shiro-resources').dialog('open');
                }else{
                    $.messager.alert('Warning',"<m:info name='请选择...'/>");
                }
                break;
                </shiro:hasPermission>
            case "delete":
            <shiro:hasPermission name="resources:delete">
                deleteRow();
                break;
                </shiro:hasPermission>
            default:
                return;
        }
    }
</script>
<script>
    //form1确认提交
    function submitApply(){
        $('#form1-shiro-resources').form('submit', {
            url:"resources/save",
            onSubmit: function(){

            },
            success:function(data){
                var data = JSON.parse(data);
                if(data.status) {
                    actionOver("reload");
                    actionOver("colse");
                }else{
                    $.messager.alert('Warning',data.message);
                }
            }
        });
    }

    //修改treegrid数据
    function editChangeresourcesTree(){
        return ChangeresourcesTree(false);
    }

    function addChangeresourcesTree(){
        ChangeresourcesTree(true);
    }

    function ChangeresourcesTree(idFlag){
        var row = $('#table-shiro-resources').datagrid('getSelected');
        if(row != null) {//有选择资料,将资料值初始化到表单
            if(idFlag){//新增时id制空
                row.id = "";
            }
            setFormRow(row);
            return true;
        }else{//未选择资料,默认初始化值
            setFormRow();
            return false;
        }
    }
    //删除treegrid数据
    function deleteRow() {
        var row = $('#table-shiro-resources').treegrid('getSelected');
        if (row != null) {
            $.ajax({
                url: "resources/delete?id=" + row.id,
                type: "POST",
                success: function (data) {
                    if (data.status) {
                        actionOver("reload");
                        actionOver("colse");
                    } else {
                        $.messager.alert('Warning', data.message);
                    }
                }
            });
        } else {
            $.messager.alert('Warning', "<m:info name='请选择...'/>");
        }
    }

    //开关按钮点击切换
    function clickLife(){
        if($("#btn").linkbutton("options").selected){
            changeLife(0);
        }else{
            changeLife(1);
        }
    }
    //开关按钮赋值
    function changeLife(val){
        if(val){
            $("#btn").linkbutton({
                selected:true,
                text:"<i class='icon-ban-circle'/>&nbsp;<m:info name='已锁定'/>"
//                iconCls:'e-icon icon-ban-circle'
            });
            $("#available").val(1);
        }else{
            $("#btn").linkbutton({
                selected:false,
                text:"<i class='icon-ok'/>&nbsp;<m:info name='未锁定'/>"
//                iconCls:'e-icon icon-ok'
            });
            $("#available").val(0);
        }
    }
    //表单赋值
    function setFormRow(data){
        if(data == null){
            $("#id").val("");
            $("#realName").textbox("setValue","");
            $("#name").textbox("setValue","");
            $("#method").textbox("setValue","");
            $("#shiroAuth").textbox("setValue","");
            changeLife(0);
        }else{
            $("#id").val(data.id);
            $("#realName").textbox("setValue",data.realName);
            $("#name").textbox("setValue",data.name);
            $("#method").textbox("setValue",data.method);
            $("#shiroAuth").textbox("setValue",data.shiroAuth);
            changeLife(data.available);
        }
    }
</script>
</html>