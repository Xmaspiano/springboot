<%@ page language="java" pageEncoding="UTF-8" %>
<div id="menu-accordion-left" class="easyui-accordion" data-options="multiple:true,border:false,selected:true" style="width:100%;height:auto;">
</div>
<script>
    $(function(){
        _left_getMenuDate();
    });
    function _left_getMenuDate(){
        var tempMenuVo = $('#menu-accordion-left').accordion('panels');
        var length_menu = tempMenuVo.length;
//        this.selectsed;
        $.ajax({
            url: "/menu/tag/menu_tree.json",
            type:"POST",
            success: function(data){
                var firstSelected = $("#menu-accordion-left").accordion('getSelections');
                if(firstSelected) {
                    var selectedID = new Array(firstSelected.length);
                    var selectedTree = new Array();

                    for(var i =0; i<firstSelected.length; i++){
                        selectedID[i] = firstSelected[i].panel("options").id;
                        if($("#" + selectedID[i] + "_menu").tree('getSelected')) {
                            obj = new Object();
                            obj.menuid = selectedID[i];
                            obj.id = $("#" + selectedID[i] + "_menu").tree('getSelected').id;
                            selectedTree[i] = obj;
                        }
                    }
                }
                //清理空目录表单
                for(var i =0; i<length_menu; i++){
                    $('#menu-accordion-left').accordion('remove', tempMenuVo[0].panel("options").title);
                }

                $.each(data.rows, function(i, item) {
                    var selected = false;

                    for(var i =0; i<selectedID.length; i++){
                        if(selectedID[i] == item.id) {
                            selected = true;
                            break;
                        }
                    }

                    $('#menu-accordion-left').accordion('add', {
                        title: item.text,
                        id:item.id,
                        content: "<ul id=\""+item.id+"_menu\">",
                        selected:selected
                    });
                    if(item.children != null) {
                        parent._left_setTreeDate(item.id, JSON.stringify(item.children));
                    }
                });

                for(var i =0; i<selectedTree.length; i++){
                    var node = $("#"+selectedTree[i].menuid+"_menu").tree('find', selectedTree[i].id);
                    $("#"+selectedTree[i].menuid+"_menu").tree('select', node.target);
                }
            }
        });
    }
    function _left_setTreeDate(id, valTree) {
        var datajson = JSON.parse(valTree);
        $("#"+id+"_menu").tree({
            animate:true,
            data:datajson,
            onClick: function(node){
                ajax_getTreeTagById(node.id);
            }
        });
    }
    function ajax_getTreeTagById(id){
        $.ajax({
            url: "/menu/tag/menu_tree_url.json?id="+id,
            type:"POST",
            success: function(data, status, xhr){
                if(data.url) {
                    addTabByClick(data);
                }
            }
        });
    }
    function _left_TreeReload(){
        _left_getMenuDate();
//        $('#menu-accordion-left').accordion('reload');
    }
</script>