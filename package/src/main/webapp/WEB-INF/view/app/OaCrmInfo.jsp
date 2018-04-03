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
    <div id="table-oacrminfo" title="<i class='icon-save'/><m:info name='供应商管理'/>&nbsp;"></div>
</div>
<div id="tb-OaCrmInfo" style="padding:5px;height:auto">
    <a href="#" class="easyui-linkbutton" data-option="plain:true" onclick="actionOver('add')">
        <i class="fa fa-plus">&nbsp;<m:info name='新增'/></i></a>
    <a href="#" class="easyui-linkbutton" data-option="plain:true" onclick="actionOver('save')">
        <i class="fa fa-save">&nbsp;<m:info name='保存'/></i></a>
    <a href="#" class="easyui-linkbutton" data-option="plain:true" onclick="actionOver('delete')">
        <i class="fa fa-trash-o">&nbsp;<m:info name='删除'/></i>
    </a>
    <a href="#" class="easyui-linkbutton" data-option="plain:true" onclick="actionOver('reject')">
        <i class="fa fa-reply"></i>
    </a>
    <input id="sreachname" name="sreachname" class="easyui-searchbox" style="width:160px;"
           data-options="searcher:search,prompt:'Please Input Value'"/>
</div>
</body>
<%--初始化Html Script--%>
<script>
    var __date={
        idName:"table-oacrminfo",
        tollIdName:"tb-OaCrmInfo",
        validateField:[
            "name",
            "gysbh",
            "ekor"
        ],
        datagrid:{
            title:"<i class='icon-save'/>&nbsp;<m:info name='供应商管理'/>",
            url: '/app/crm/date_treegrid.json',
            method:"POST",
            loadMsg:"<m:info name='数据加载中...'/>",
            pageSize:30,
            columns:[[
                {field: 'ck-table-oacrminfo', checkbox:true},
                {field: 'id', title: "<m:info name='主键'/>", width: 180, hidden:true},
                {field: 'name', title: "<m:info name='名称'/>", width: 180,
                    editor:{type:'validatebox',options:{ required:true, validType:'length[1,100]'} }
                },
                {field: 'gysbh', title: "<m:info name='编号'/>", width: 80,
                    editor:{type:'validatebox',options:{ required:true, validType:'length[6,10]'} }
                },
                {field: 'ekor', title: "<m:info name='ekor'/>", width: 60, align: 'right',
                    editor:{type:'validatebox',options:{ required:true, validType:'length[4,10]'} }
                }
            ]],
            idField:"id",
            queryParams: {
                sreachname:""
            }
        },

    }

    var $targetTag = $("#"+__date.idName);
    var $targetToolTag = $("#"+__date.tollIdName);

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
            onDblClickRow:onDblClickRow,
            onClickRow:onClickRow
        });

        $targetToolTag.children("span.searchbox").css("float","right")
    });

    //定义页面操作集合
    function actionOver(code){
        switch(code){
            case "add":
            <%--<shiro:hasPermission name="--:save">--%>
                append();
                break;
            <%--</shiro:hasPermission>--%>
            case "save":
            <%--<shiro:hasPermission name="--:save">--%>
                accept();
                break;
            <%--</shiro:hasPermission>--%>
            case "delete":
            <%--<shiro:hasPermission name="--:delete">--%>
                removeit();
                break;
            <%--</shiro:hasPermission>--%>
            case "reject":
                reject();
                break;
            default:
                return false;
        }
    }

    function search(value,name){
        $targetTag.datagrid('options').queryParams[name] = value;
        $targetTag.datagrid("reload");
    }
</script>
<script type="text/javascript">
    var editIndex = undefined;
    function endEditing(){
        if (editIndex == undefined){return true}
        $targetTag.datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    }
    function onClickRow(index){
        if (editIndex != index){
            if (endEditing()){
                $targetTag.datagrid('selectRow', index);
                editIndex = index;
            } else {
                $targetTag.datagrid('selectRow', editIndex);
            }
        }
    }

    function onDblClickRow(index){
        onClickRow(index);
        $targetTag.datagrid('selectRow', index)
            .datagrid('beginEdit', index);
        $targetTag.datagrid('getEditors',index)[0].target.focus();
    }

    //增行
    function append(){
        if (endEditing()){
            $targetTag.datagrid('appendRow',{});
            editIndex = $targetTag.datagrid('getRows').length-1;
            $targetTag.datagrid('selectRow', editIndex)
                .datagrid('beginEdit', editIndex);
        }
    }
    //删除
    function removeit(){
        var checkedRows = $targetTag.datagrid('getSelected');
        var checkedIndex =  $targetTag.datagrid('getRowIndex',checkedRows);

        $targetTag.datagrid('cancelEdit', checkedIndex)
            .datagrid('deleteRow', checkedIndex);
        editIndex = undefined;
    }
    //保存
    function accept(){
        clearEmpty();

        var effectRow = new Object();
        effectRow["inserted"] = $targetTag.datagrid('getChanges', 'inserted');
        effectRow["deleted"] = $targetTag.datagrid('getChanges', 'deleted');
        effectRow["updated"] = $targetTag.datagrid('getChanges', 'updated');

        var rows = $targetTag.datagrid('getRows');
        var validateRowFlag = true;
        for(var i=0; i<rows.length; i++) {
            if (!$targetTag.datagrid('validateRow', i)) {
                validateRowFlag = false;
            }else{
                $targetTag.datagrid('endEdit', i);
            }
        }
        if(!validateRowFlag){
            $.messager.alert("提示", "保存失败，请输入完整信息！！！", "info");
            return false;
        }

        if(effectRow["inserted"].length >0 || effectRow["deleted"].length >0 || effectRow["updated"].length >0) {
            $.ajax({
                url: "/app/crm/save",
                data: JSON.stringify(effectRow),
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    if (data.status) {
                        if (endEditing()) {
                            $targetTag.datagrid('acceptChanges');
                        }
                        $.messager.alert('信息提示', "保存成功...", 'info');
                    } else {
                        $.messager.alert('信息提示', data.message, "Warning");
                    }
                    getChanges();
                }
            });
        }else {
            getChanges();
        }
    }
    //还原
    function reject(){
        $targetTag.datagrid('rejectChanges');
        editIndex = undefined;
    }

    function getChanges(){
        var rows = $targetTag.datagrid('getChanges');
        $.messager.show({
            title:"保存信息",
            msg:rows.length+" rows are changed!",
            timeout:1800
        });
    }

    function clearEmpty() {
        clearEmptyCommon(__date);
    }


    function clearEmptyCommon(data) {
        endEditing();
        var rows = $targetTag.datagrid('getChanges');
        var deleteIndex= new Array();

        out:
            for(var i=0; i<rows.length; i++){
                var rowIndex = $targetTag.datagrid('getRowIndex',rows[i]);

                for(var j=0; j<data.validateField.length; j++){
                    var ed = $targetTag.datagrid('getEditor', {index:rowIndex,field:data.validateField[j]});
                    if(!ed || $(ed.target).val() != ""){
                        $targetTag.datagrid('endEdit', rowIndex);
                        continue out;
                    }
                }
                deleteIndex.push(rowIndex);
            }

        for(var rowIndex=0; rowIndex<deleteIndex.length; rowIndex++) {
            var moveIndex = deleteIndex[rowIndex] - rowIndex;
            $targetTag.datagrid('cancelEdit', moveIndex)
                .datagrid('deleteRow', moveIndex);
        }

        deleteIndex = null;
    }
</script>
</html>