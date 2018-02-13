//package com.springboot.system.thymeleaf.processor.element;
//
//import com.springboot.system.thymeleaf.dialect.Processor;
//import com.springboot.system.thymeleaf.processor.attribute.ConfigTag;
//import com.springboot.system.util.MsgUtil;
//import com.springboot.system.util.MsgUtilNative;
//import org.thymeleaf.Arguments;
//import org.thymeleaf.dom.Element;
//import org.thymeleaf.processor.element.AbstractTextChildModifierElementProcessor;
//
//@Processor
//public class ConElementTag { //extends AbstractTextChildModifierElementProcessor {
////    private final MsgUtil msgUtil = new MsgUtilNative(ConfigTag.class);
////
////    private static final String ATTRIBUTE_NAME = "info";
////    private static final int PRECEDENCE = 12000;
////
////    public ConElementTag() {
////        super(ATTRIBUTE_NAME);
////    }
////
////    @Override
////    protected String getText(Arguments arguments, Element element) {
////        String name = element.getAttributeValue("name");
////        if (element.hasAttribute("name")) {
////            element.removeAttribute("name");
////        }
////
////        return new MsgUtilNative(ConfigTag.class).getMsg(name);
////    }
////
////    @Override
////    public int getPrecedence() {
////        return PRECEDENCE;
////    }
//}
