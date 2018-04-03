package com.springboot.app.web;

import com.alibaba.druid.util.StringUtils;
import com.springboot.app.entity.secondDsE.RequestBase;
import com.springboot.app.entity.secondDsE.WorkflowBase;
import com.springboot.app.model.RequestBasePage;
import com.springboot.app.model.RequestBaseSreachPage;
import com.springboot.app.service.RequestBaseService;
import com.springboot.app.service.WorkflowBaseService;
import com.springboot.common.util.CommonUtil;
import com.springboot.common.util.MsgUtil;
import com.springboot.common.util.MsgUtilNative;
import com.springboot.system.util.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/23 - 上午11:37
 * Created by IntelliJ IDEA.
 */
@Controller
@RequestMapping(value = {"/app/request"})
public class RequestBaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestBaseController.class);
    private final MsgUtil msgUtil = new MsgUtilNative(RequestBaseController.class);

    @Autowired
    RequestBaseService requestBaseService;
    @Autowired
    WorkflowBaseService workflowBaseService;
    @Autowired
    RequestBasePage requestBasePage;
    @Autowired
    Common common;

    @RequestMapping
    public String index(){
        return "app/RequestBase";
    }

    @RequestMapping("/date_treegrid.json")
    @ResponseBody
//    @RequiresPermissions("app.request:view")
    public Map getData(RequestBaseSreachPage requestBaseSreachPage){
        Map jsonMap = new HashMap(16);
        List<RequestBasePage> requestBasePages;
        if(!StringUtils.isEmpty(requestBaseSreachPage.getOrderid())){
            List<RequestBase> requestBases = requestBaseService.findByOrderId(requestBaseSreachPage.getOrderid());
            requestBasePages = requestBasePage.changeByEntitys(requestBases);
            jsonMap.put("total",requestBases.size());
        }else{

            Specification<RequestBase> spec = new Specification() {
                /**
                 * Creates a WHERE clause for a query of the referenced entity in form of a {@link Predicate} for the given
                 * {@link Root} and {@link CriteriaQuery}.
                 *
                 * @param root
                 * @param query
                 * @param cb
                 * @return a {@link Predicate}, may be {@literal null}.
                 */
                @Override
                public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                    List<Predicate> predicates = new ArrayList<>();

                    Specification<WorkflowBase> spec_subcompany = new Specification() {
                        List<Predicate> predicates_subcompany = new ArrayList<>();
                        @Override
                        public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                            predicates_subcompany.add(cb.or(
                                    cb.like(root.get("workflowname").as(String.class),"%采购%"),
                                    cb.like(root.get("workflowname").as(String.class),"%领用%")
                            ));
                            predicates_subcompany.add(cb.equal(root.get("isvalid").as(String.class), 1));

                            if(!StringUtils.isEmpty(requestBaseSreachPage.getSubcompanyid())){
                                predicates_subcompany.add(cb.equal(root.get("subcompanyid").as(String.class),
                                        requestBaseSreachPage.getSubcompanyid()));
                            }

                            return cb.and(predicates_subcompany.toArray(new Predicate[0]));
                        }
                    };

                    List<WorkflowBase> workflowIds = workflowBaseService.findAll(spec_subcompany);

                    if(workflowIds.size()>0) {
                        CriteriaBuilder.In in = cb.in(root.get("workflowid"));
                        for(int i = 0; i<workflowIds.size(); i++){
                            in.value(String.valueOf(workflowIds.get(i).getId()));
                        }
                        predicates.add(in);
                    }

                    //创建时间 起始时间范围
                    if(!StringUtils.isEmpty(requestBaseSreachPage.getStrDate())){
                        predicates.add(cb.greaterThanOrEqualTo(root.get("createdate").as(String.class),
                                requestBaseSreachPage.getStrDate()));
                    }
                    //创建时间 结束时间范围
                    if(!StringUtils.isEmpty(requestBaseSreachPage.getEndDate())){
                        predicates.add(cb.lessThanOrEqualTo(root.get("createdate").as(String.class),
                                requestBaseSreachPage.getEndDate()));
                    }
                    return cb.and(predicates.toArray(new Predicate[0]));
                }
            };

            //分页信息
            Pageable pageable = new PageRequest(requestBaseSreachPage.getPage()-1,requestBaseSreachPage.getRows());
            Page<RequestBase> page = requestBaseService.findAll(spec, pageable);
            jsonMap.put("total",page.getTotalElements());

            requestBasePages = requestBasePage.changeByEntitys(page.getContent());
        }

        requestBasePages.forEach( requestBasePage -> {
            requestBasePage.setLasterName(common.getNameById(Long.valueOf(requestBasePage.getCreater())));
        } );

        jsonMap.put("rows", CommonUtil.conversionByList(requestBasePages));
        return jsonMap;
    }
}
