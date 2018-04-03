package com.springboot.app.entity.secondDsE;

import com.springboot.common.entity.BaseIdEntity;
import com.springboot.system.util.Common;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/30 - 上午9:34
 * Created by IntelliJ IDEA.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Table(name = "WORKFLOW_REQUESTBASE")
public class RequestBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long requestid;
    String workflowid;
    String currentnodetype;
    String requestname;
    String creater;
    String createdate;
    Long mainrequestid;
}
