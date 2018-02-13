//package com.springboot.system.thymeleaf.processor.attribute;
//
//import com.springboot.system.thymeleaf.dialect.Processor;
//
//@Processor
//public class ConfigTag { //extends AbstractTextChildModifierAttrProcessor {
////    private final MsgUtil msgUtil = new MsgUtilNative(ConfigTag.class);
////
////    private static final String ATTRIBUTE_NAME = "info";
////    private static final int PRECEDENCE = 12000;
////
////    public ConfigTag() {
////        super(ATTRIBUTE_NAME);
////    }
////
////    public int getPrecedence() {
////        return PRECEDENCE;
////    }
////
////
////    @Override
////    protected String getText(Arguments arguments, Element element, String attributeName) {
////        String name = element.getAttributeValue(attributeName);
////        if (element.hasAttribute(attributeName)) {
////            element.removeAttribute(attributeName);
////        }
////
////        return new MsgUtilNative(ConfigTag.class).getMsg(name);
////    }
////
////    public static IProcessor create() {
////        return new ConfigTag();
////    }
//}
