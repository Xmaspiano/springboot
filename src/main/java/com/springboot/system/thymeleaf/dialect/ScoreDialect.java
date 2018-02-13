//package com.springboot.system.thymeleaf.dialect;
//
//import com.springboot.common.config.Thymeleaf.Dialect;
//import com.springboot.system.PackageUtil;
//import org.thymeleaf.dialect.AbstractDialect;
//import org.thymeleaf.processor.IProcessor;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//@Dialect
//public class ScoreDialect extends AbstractDialect {
//    public static final String PREFIX = "score";
//    private static final Set<IProcessor> processors = new HashSet();
//
//    @Override
//    public String getPrefix() {
//        return PREFIX;
//    }
//
//    public Set<IProcessor> getProcessors() {
//        return Collections.unmodifiableSet(processors);
//    }
//
//    static {
//        Processor processor;
//        String processorName;
//        Class<IProcessor> iProcessorClass;
//        try {
//            for (Class clas : new PackageUtil().getClasses("com.springboot")) {
//                if(IProcessor.class.isAssignableFrom(clas)){
//                    iProcessorClass = clas;
//                    processor = iProcessorClass.getAnnotation(Processor.class);
//                    if (processor != null) {
//                        processorName = processor.name();
//                        if (PREFIX.equals(processorName)) {
//                            processors.add(iProcessorClass.newInstance());
//                        }
//                    }
//                }
//            }
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
