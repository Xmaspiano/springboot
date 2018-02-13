//package com.springboot.system.thymeleaf.processor.attribute;
//
//import com.springboot.system.entity.secondDsE.Hrmresource;
//import com.springboot.system.thymeleaf.dialect.Processor;
//import com.springboot.system.util.MsgUtil;
//import com.springboot.system.util.MsgUtilNative;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.thymeleaf.Arguments;
//import org.thymeleaf.dom.Element;
//import org.thymeleaf.processor.IProcessor;
//import org.thymeleaf.processor.attr.AbstractAttributeModifierAttrProcessor;
//import org.thymeleaf.processor.attr.AbstractTextChildModifierAttrProcessor;
//import org.thymeleaf.standard.processor.attr.AbstractStandardSingleAttributeModifierAttrProcessor;
//
//import java.lang.reflect.Field;
//
//@Processor
//public class ScoreValueAttributeProcessor extends AbstractStandardSingleAttributeModifierAttrProcessor {
//    private final MsgUtil msgUtil = new MsgUtilNative(ScoreValueAttributeProcessor.class);
//
//    private static final String ATTRIBUTE_NAME = "value";
//    private static final int PRECEDENCE = 12000;
//
//    public ScoreValueAttributeProcessor() {
//        super(ATTRIBUTE_NAME);
//    }
//
//    public int getPrecedence() {
//        return PRECEDENCE;
//    }
//
//
////    @Override
////    protected String getText(Arguments arguments, Element element, String attributeName) {
////        String name = element.getAttributeValue(attributeName);
////        if (element.hasAttribute(attributeName)) {
////            element.removeAttribute(attributeName);
////        }
////
////        return getPrincipalElement(name);
////    }
//
//    private String getPrincipalElement(String methodName){
//        Hrmresource hrmresource = (Hrmresource) SecurityUtils.getSubject().getPrincipal();
//        Class aClass = Hrmresource.class;
//        String getValue = "";
//        try {
//            Field field = aClass.getDeclaredField(methodName);
//            field.setAccessible(true);
//
//            getValue = field.get(hrmresource).toString();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        return getValue;
//    }
//
//    public static IProcessor create() {
//        return new ScoreValueAttributeProcessor();
//    }
//
//    @Override
//    protected String getTargetAttributeName(Arguments arguments, Element element, String attributeName) {
//        String name = element.getAttributeValue(attributeName);
//        if (element.hasAttribute(attributeName)) {
//            element.removeAttribute(attributeName);
//        }
//
//        element.setAttribute("value",name);
//
//        System.out.println(arguments);
//        System.out.println(element);
//        System.out.println(attributeName);
//
//        return getPrincipalElement(name);
//    }
//
//    @Override
//    protected ModificationType getModificationType(Arguments arguments, Element element, String attributeName, String newAttributeName) {
//        System.out.println(arguments);
//        System.out.println(element);
//        System.out.println(attributeName);
//        System.out.println(newAttributeName);
//
//        return null;
//    }
//
//    @Override
//    protected boolean removeAttributeIfEmpty(Arguments arguments, Element element, String attributeName, String newAttributeName) {
//        return false;
//    }
//}
