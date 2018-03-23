package com.springboot.system.auth.repository.firstDs;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**  
 *    
 *   
 * @author XmasPiano  
 * @date 2018/3/1 上午10:23
 * @param   
 * @return   
 */  
public class AuthRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public Set<String> fingKeyNameByUser(Long userid, Set<Long> groupid, Set<Long> deptid, Set<Long> jobid){
        boolean hasGroupid = groupid.size() > 0;
        boolean hasDeptid = deptid.size() > 0;
        boolean hasJobid = jobid.size() > 0;

        StringBuilder hql = new StringBuilder("select keyname From Auth where ");
        hql.append(" (user_id=:userid) ");

        if(hasGroupid){
            hql.append(" or ");
            hql.append(" (group_id=:groupid) ");
        }

        if(hasDeptid){
            hql.append(" or ");
            hql.append(" (organization_id=:deptid) ");
        }

        if(hasJobid){
            hql.append(" or ");
            hql.append(" (job_id=:jobid) ");
        }

        Query q = em.createQuery(hql.toString());

        q.setParameter("userid",userid);

        if(hasGroupid){
            q.setParameter("groupid",groupid);
        }

        if(hasDeptid){
            q.setParameter("deptid",deptid);
        }

        if(hasJobid){
            q.setParameter("jobid",jobid);
        }

        List<String> keyNames = q.getResultList();

        Set<String> rtnSet = new HashSet();
        keyNames.forEach(set -> rtnSet.add(set));
        return rtnSet;
    }
}
