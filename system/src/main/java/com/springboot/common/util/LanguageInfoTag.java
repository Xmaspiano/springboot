package com.springboot.common.util;

import com.springboot.system.util.MsgUtilNative;
import lombok.Data;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@Data
public class LanguageInfoTag extends TagSupport {

    private String name;
//    private CacheManager cacheManager;
//    private WebApplicationContext webApplicationContext;

    public LanguageInfoTag(){
        super();
//        webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
//        cacheManager = (CacheManager) webApplicationContext.getBean("springCacheManager");
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = this.pageContext.getOut();
            if(name == null) {
                name = "未初始化名称";
            }
            out.print(new MsgUtilNative(LanguageInfoTag.class).getMsg(name));
        }catch(Exception e) {
            e.printStackTrace();
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;

    }


    @Override
    public int doEndTag() throws JspException {

        return EVAL_PAGE;
    }
}
