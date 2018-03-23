<%@ include file="/WEB-INF/view/systemLayout/layout_system.jsp"%>
<style>
</style>
<script>
    $(function() {
        var h = $(window.top.document.getElementsByTagName("iframe")[0]).parent().height();
//        var w = $(window.top.document.getElementsByTagName("iframe")[0]).parent().width();
//        $("body").width(w - 16);
        $("body").height(h - 16);
        $("body").css("margin","8px");

        $("body").children("div.easyui-layout").layout({
            fit:true,
            minHeight:400
        });

//        $("body").children("div.easyui-layout").css("width","100%");
    });
</script>