<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>

<%--<%@ taglib uri="/MyTag" prefix="i"%>--%>
<%--<%pageContext.setAttribute("AppId",request.getServletPath());%>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/view/systemLayout/layout_system_iframe.jsp"%>
</head>
<style>
    .e-icon{
        top: 6px;
    }px
</style>
<%-- 调用方法script --%>
<script>

</script>
<body>
<div class="easyui-layout">
    <div id="table-dept"  title="<i class='icon-save'/><m:info name='机构管理'/>&nbsp;"></div>
</div>
</body>
<%--初始化Html Script--%>
<script>
    $(function() {
        //初始化treegrid数据
        $('#table-dept').treegrid({
            title:"<i class='icon-save'/>&nbsp;<m:info name='机构管理'/>",
            url: '/hrmdepartment/date_treegrid.json',
            loadMsg:"<m:info name='数据加载中...'/>",
            idField: 'id',
            treeField: 'departmentname',
            fitColumns:true,
            fit:true,
//            striped:true,
            rownumbers:true,
            columns: [[
                {field: 'id', title: "<m:info name='主键'/>", width: 180, hidden:true},
                {field: 'departmentname', title: "<m:info name='部门名称'/>", width: 180},
                {field: 'parentid', title: "<m:info name='父菜单ID'/>", width: 80},
                {field: 'subcompanyid1', title: "<m:info name='集团ID'/>", width: 80},
                {field: 'canceled', title: "<m:info name='是否启用'/>", width: 60, align: 'right'}
            ]],
            onDblClickRow:function(data){
                actionOver("edit")
            }
        });

        //初始化上级目录tree数据
        $('#tree-dept').tree({
            url:"/common/tag/dept/dept_tree.json?parentid=1",
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
                var row = $('#table-dept').treegrid('find',data.id);
                if(row) {
                    $("#code").textbox("setValue", row.code);
                }

                $('#parentid').val(data.id);
            }
        });
    });
</script>
</html>