<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/view/systemLayout/layout_system_iframe.jsp"%>
</head>
<body>
<div class="easyui-layout">
    <div id="table-requestbase"></div>
</div>
<div id="tb-requestbase" style="padding:5px;height:auto">
    <%--<a href="#" class="easyui-linkbutton" data-option="plain:true" >--%>
    <%--<i class="fa fa-plus">&nbsp;<m:info name='新增'/></i></a>--%>
    <form id="form-RequestBase"></form>
    <input class="easyui-combobox"
           name="subcompanyid"
           id="subcompanyid"
           data-options="
                        url:'/common/tag/dept/getSubCompany.json',
                        method:'post',
                        valueField:'id',
                        textField:'departmentname',
                        panelHeight:'auto',
                        value:1
                "
           style="width:180px;">
    创建时间: <input class="easyui-datebox" name="strDate" id="strDate" style="width:100px">
    - <input class="easyui-datebox" name="endDate" id="endDate" style="width:100px">
    <input id="orderid" name="orderid" class="easyui-searchbox" style="width:160px;"
           data-options="searcher:search,prompt:'Please Input Value'"/>
</div>
</body>
<%--初始化Html Script--%>
<script>
    var $targetTag = $("#table-requestbase");
    var $targetToolTag = $("#tb-requestbase");

    var __date={
        idName:"table-requestbase",
        tollIdName:"tb-requestbase",
        datagrid:{
            title:"<i class='icon-save'/>&nbsp;<m:info name='采购流程查询'/>",
            url: '/app/request/date_treegrid.json',
            method:"POST",
            loadMsg:"<m:info name='数据加载中...'/>",
            pageSize:30,
            columns:[[
                {field: 'requestid', title: "<m:info name='请求ID'/>", width: 60},
                {field: 'currentnodetype', title: "<m:info name='状态'/>", width: 40},
                {field: 'requestname', title: "<m:info name='流程申请名称'/>"},
                {field: 'creater', title: "<m:info name='创建人'/>", width: 80, hidden:true},
                {field: 'lasterName', title: "<m:info name='创建人'/>", width: 80},
                {field: 'createdate', title: "<m:info name='创建时间'/>", width: 80},
                {field: 'mainrequestid', title: "<m:info name='父请求ID'/>", width: 60, align: 'right'}
            ]],
            idField:"id",
            queryParams: {
                subcompanyid:"1",
                creater:"",
                strDate:"",
                endDate:"",
                orderid:"",
            }
        },

    }

    $(function() {
        //初始化treegrid数据
        $targetTag.datagrid({
            title: __date.datagrid.title,
            url: __date.datagrid.url,
            pagination:true,
            method: __date.datagrid.method,
            loadMsg: __date.datagrid.loadMsg,
            fitColumns:true,
            fit:true,
            pageSize:__date.datagrid.pageSize,
            toolbar: "#"+__date.tollIdName,
            //            striped:true,
            rownumbers:true,
            singleSelect:true,
            idField:__date.datagrid.idField,
            queryParams: __date.datagrid.queryParams,
            columns: __date.datagrid.columns,
            onDblClickRow:onDblClickRow
        });

        $targetToolTag.children("span.searchbox").css("float","right")
    });

    function search(value,name){
        var subcompanyid = $("#subcompanyid").combobox("getValue");
        var strDate = $("#strDate").datebox("getValue");
        var endDate = $("#endDate").datebox("getValue");

        $targetTag.datagrid('options').queryParams["subcompanyid"] = subcompanyid;
        $targetTag.datagrid('options').queryParams["strDate"] = strDate;
        $targetTag.datagrid('options').queryParams["endDate"] = endDate;

        $targetTag.datagrid('options').queryParams[name] = value;
        $targetTag.datagrid("reload");
    }

    function onDblClickRow(rowIndex, rowData) {
        var url = "/workflow/request/ViewRequest.jsp?requestid="+rowData.requestid+"&isovertime=1";
        window.open("/oalink?url="+url);
    }
</script>
</html>