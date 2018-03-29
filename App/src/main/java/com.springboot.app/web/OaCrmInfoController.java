package com.springboot.app.web;

import com.alibaba.druid.util.StringUtils;
import com.springboot.app.entity.secondDsE.OaCrmInfo;
import com.springboot.app.service.OaCrmInfoService;
import com.springboot.common.model.EffectRow;
import com.springboot.common.model.PageParam;
import com.springboot.common.util.CommonUtil;
import com.springboot.common.util.MsgUtil;
import com.springboot.common.util.MsgUtilNative;
import com.springboot.system.util.AjaxMsgUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = {"/app/crm"})
public class OaCrmInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OaCrmInfoController.class);
    private final MsgUtil msgUtil = new MsgUtilNative(OaCrmInfoController.class);

    @Autowired
    OaCrmInfoService oaCrmInfoService;

    @RequestMapping
    public String index(){
        return "app/OaCrmInfo";
    }

    @RequestMapping("/date_treegrid.json")
    @ResponseBody
//    @RequiresPermissions("app.crm:view")
    public Map getData(PageParam pageParam, String sreachname){
        Map jsonMap = new HashMap(16);
        Specification<OaCrmInfo> spec = new Specification() {
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
                if(!StringUtils.isEmpty(sreachname)){
                    predicates.add(cb.like(root.get("name").as(String.class),"%"+sreachname+"%"));
                    predicates.add(cb.like(root.get("gysbh").as(String.class),"%"+sreachname+"%"));
                }
                if(predicates.size()>0){
                    return cb.and(
                            cb.or(predicates.toArray(new Predicate[predicates.size()]))
                    );
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };

        //分页信息
        Pageable pageable = new PageRequest(pageParam.getPage()-1,pageParam.getRows());
        Page<OaCrmInfo> page = oaCrmInfoService.findAll(spec, pageable);

        jsonMap.put("total",page.getTotalElements());
        jsonMap.put("rows", CommonUtil.conversionByList(page.getContent()));
        return jsonMap;
    }

    @RequestMapping(value = "/save" )
    @ResponseBody
//    @RequiresPermissions("app.crm:save")
    public Map saveInfo(@RequestBody EffectRow<OaCrmInfo> effectRow){
        List<OaCrmInfo> oaCrmInfoList = new ArrayList<OaCrmInfo>();
        delete(effectRow.getDeleted());
        oaCrmInfoList.addAll(effectRow.getUpdated());
        oaCrmInfoList.addAll(effectRow.getInserted());
        oaCrmInfoService.save(oaCrmInfoList);

        return AjaxMsgUtil.ajaxMsg(AjaxMsgUtil.SUCCESS, msgUtil.getMsg("saveInfo.success"));
    }

//    @RequiresPermissions("app.crm:delete")
    private void delete(List<OaCrmInfo> oaCrmInfoList){
        oaCrmInfoService.delete(oaCrmInfoList);
    }
}
