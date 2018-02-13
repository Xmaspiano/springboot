//package com.springboot.system.thymeleaf.dialect;
//
//import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
//import com.springboot.common.config.Thymeleaf.Dialect;
//import org.thymeleaf.processor.IProcessor;
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//@Dialect
//public class ShiroDialectEx extends ShiroDialect {
//    private ShiroDialect shiroDialect = new ShiroDialect();
//    private static final Set<IProcessor> processors = new HashSet();
//
//    public Set<IProcessor> getProcessors() {
//        Set<IProcessor> iProcessors = shiroDialect.getProcessors();
//        if(processors.size() > 0) {
//            iProcessors.addAll(Collections.unmodifiableSet(processors));
//        }
//        return iProcessors;
//    }
//
//    static {
//
//    }
//}
