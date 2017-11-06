package com.lmml.graph.domain.authority;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rbac_group")
public class RbacGroup {
    private Long groupId;
    private Timestamp createTimestamp;
    private String groupName;
    private String activitiId;


    @Column(name = "create_timestamp", nullable = false, updatable = false)
    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    @Id
    @Column(name = "group_id", nullable = false)
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "group_name", nullable = true, length = 255)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "activiti_id", nullable = true, length = 255)
    public String getActivitiId() {
        return activitiId;
    }

    public void setActivitiId(String activitiId) {
        this.activitiId = activitiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RbacGroup rbacGroup = (RbacGroup) o;
        if (groupId != null ? !groupId.equals(rbacGroup.groupId) : rbacGroup.groupId != null) return false;
        if (createTimestamp != null ? !createTimestamp.equals(rbacGroup.createTimestamp) : rbacGroup.createTimestamp != null)
        if (groupName != null ? !groupName.equals(rbacGroup.groupName) : rbacGroup.groupName != null) return false;
        if (activitiId != null ? !activitiId.equals(rbacGroup.activitiId) : rbacGroup.activitiId != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (createTimestamp != null ? createTimestamp.hashCode() : 0);
        result = 31 * result + (groupName != groupName ? groupName.hashCode() : 0);
        result = 31 * result + (activitiId != activitiId ? activitiId.hashCode() : 0);
        return result;
    }
}
