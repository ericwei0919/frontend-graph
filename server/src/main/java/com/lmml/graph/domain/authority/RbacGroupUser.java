package com.lmml.graph.domain.authority;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rbac_group_user_re")
public class RbacGroupUser {

    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = true)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id", nullable = false)
    private RbacUser user;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = true)
    @JoinColumn(name = "group_id",referencedColumnName = "group_id", nullable = false)
    private RbacGroup group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RbacUser getUser() {
        return user;
    }

    public void setUser(RbacUser user) {
        this.user = user;
    }

    public RbacGroup getGroup() {
        return group;
    }

    public void setGroup(RbacGroup group) {
        this.group = group;
    }
}
