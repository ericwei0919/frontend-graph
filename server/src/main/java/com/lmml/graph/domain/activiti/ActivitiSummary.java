package com.lmml.graph.domain.activiti;

import com.lmml.graph.domain.authority.RbacUser;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activiti_obj_re")
public class ActivitiSummary {
    @Id
    private Long id;
    private Timestamp createTimestamp;
    private Timestamp updateTimestamp;
    private Long hostObjId;
    private String tableName;
    private String processInstanceId;
    private String activitiType;
    private String taskStatus;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = true)
    @JoinColumn(name = "assignee",referencedColumnName = "user_id", nullable = false)
    private RbacUser assignee;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = true)
    @JoinColumn(name = "previous_approver",referencedColumnName = "user_id", nullable = false)
    private RbacUser previousApprover;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "create_timestamp")
    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }


    @Column(name = "update_timestamp")
    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }


    @Column(name = "host_obj_id", nullable = false, updatable = false)
    public Long getHostObjId() {
        return hostObjId;
    }

    public void setHostObjId(Long hostObjId) {
        this.hostObjId = hostObjId;
    }


    @Column(name = "table_name", nullable = false, updatable = false)
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    @Column(name = "process_instance_id", nullable = false, updatable = false)
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }


    @Column(name = "activitiType", nullable = false, updatable = false)
    public String getActivitiType() {
        return activitiType;
    }

    public void setActivitiType(String activitiType) {
        this.activitiType = activitiType;
    }


    @Column(name = "task_status", nullable = false, updatable = false)
    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public RbacUser getAssignee() {
        return assignee;
    }

    public void setAssignee(RbacUser assignee) {
        this.assignee = assignee;
    }

    public RbacUser getPreviousApprover() {
        return previousApprover;
    }

    public void setPreviousApprover(RbacUser previousApprover) {
        this.previousApprover = previousApprover;
    }
}
