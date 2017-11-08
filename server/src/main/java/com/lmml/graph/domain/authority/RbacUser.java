package com.lmml.graph.domain.authority;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "rbac_user")
public class RbacUser {
    private Long userId;
    private Timestamp createTimestamp;
    private String userName;
    private String loginId;
    private String password;

    @Id
    @Column(name = "user_id", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "create_timestamp", nullable = false, updatable = false)
    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "login_id", nullable = false, length = 255)
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RbacUser rbacUser = (RbacUser) o;
        if (userId != null ? !userId.equals(rbacUser.userId) : rbacUser.userId != null) return false;
        if (createTimestamp != null ? !createTimestamp.equals(rbacUser.createTimestamp) : rbacUser.createTimestamp != null) if (userName != null ? !userName.equals(rbacUser.userName) : rbacUser.userName != null) return false;
        if (loginId != null ? !loginId.equals(rbacUser.loginId) : rbacUser.loginId != null) return false;
        if (password != null ? !password.equals(rbacUser.password) : rbacUser.password != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (createTimestamp != null ? createTimestamp.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (loginId != null ? loginId.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

}
