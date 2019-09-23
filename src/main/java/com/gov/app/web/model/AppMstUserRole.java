/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gov.app.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author agung
 */
@Entity
@Table(name = "app_mst_user_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppMstUserRole.findAll", query = "SELECT a FROM AppMstUserRole a"),
    @NamedQuery(name = "AppMstUserRole.findByAppMstUserRoleId", query = "SELECT a FROM AppMstUserRole a WHERE a.appMstUserRoleId = :appMstUserRoleId"),
    @NamedQuery(name = "AppMstUserRole.findByAppMstUserId", query = "SELECT a FROM AppMstUserRole a WHERE a.appMstUserId = :appMstUserId"),
    @NamedQuery(name = "AppMstUserRole.findByAppMstRoleId", query = "SELECT a FROM AppMstUserRole a WHERE a.appMstRoleId = :appMstRoleId"),
    @NamedQuery(name = "AppMstUserRole.findByStatus", query = "SELECT a FROM AppMstUserRole a WHERE a.status = :status"),
    @NamedQuery(name = "AppMstUserRole.findByUserCreated", query = "SELECT a FROM AppMstUserRole a WHERE a.userCreated = :userCreated"),
    @NamedQuery(name = "AppMstUserRole.findByDateCreated", query = "SELECT a FROM AppMstUserRole a WHERE a.dateCreated = :dateCreated"),
    @NamedQuery(name = "AppMstUserRole.findByUserUpdated", query = "SELECT a FROM AppMstUserRole a WHERE a.userUpdated = :userUpdated"),
    @NamedQuery(name = "AppMstUserRole.findByDateUpdated", query = "SELECT a FROM AppMstUserRole a WHERE a.dateUpdated = :dateUpdated"),
    @NamedQuery(name = "AppMstUserRole.findByUserDeleted", query = "SELECT a FROM AppMstUserRole a WHERE a.userDeleted = :userDeleted"),
    @NamedQuery(name = "AppMstUserRole.findByDateDeleted", query = "SELECT a FROM AppMstUserRole a WHERE a.dateDeleted = :dateDeleted"),
    @NamedQuery(name = "AppMstUserRole.findByDeletedStatus", query = "SELECT a FROM AppMstUserRole a WHERE a.deletedStatus = :deletedStatus")})
public class AppMstUserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "APP_MST_USER_ROLE_ID")
    private Integer appMstUserRoleId;
    @Column(name = "APP_MST_USER_ID")
    private Integer appMstUserId;
    @Column(name = "APP_MST_ROLE_ID")
    private Integer appMstRoleId;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "USER_CREATED")
    private Integer userCreated;
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Column(name = "USER_UPDATED")
    private Integer userUpdated;
    @Column(name = "DATE_UPDATED")
    @Temporal(TemporalType.DATE)
    private Date dateUpdated;
    @Column(name = "USER_DELETED")
    private Integer userDeleted;
    @Column(name = "DATE_DELETED")
    @Temporal(TemporalType.DATE)
    private Date dateDeleted;
    @Column(name = "DELETED_STATUS")
    private Integer deletedStatus;

    public AppMstUserRole() {
    }

    public AppMstUserRole(Integer appMstUserRoleId) {
        this.appMstUserRoleId = appMstUserRoleId;
    }

    public Integer getAppMstUserRoleId() {
        return appMstUserRoleId;
    }

    public void setAppMstUserRoleId(Integer appMstUserRoleId) {
        this.appMstUserRoleId = appMstUserRoleId;
    }

    public Integer getAppMstUserId() {
        return appMstUserId;
    }

    public void setAppMstUserId(Integer appMstUserId) {
        this.appMstUserId = appMstUserId;
    }

    public Integer getAppMstRoleId() {
        return appMstRoleId;
    }

    public void setAppMstRoleId(Integer appMstRoleId) {
        this.appMstRoleId = appMstRoleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Integer userCreated) {
        this.userCreated = userCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(Integer userUpdated) {
        this.userUpdated = userUpdated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Integer getUserDeleted() {
        return userDeleted;
    }

    public void setUserDeleted(Integer userDeleted) {
        this.userDeleted = userDeleted;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public Integer getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(Integer deletedStatus) {
        this.deletedStatus = deletedStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appMstUserRoleId != null ? appMstUserRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppMstUserRole)) {
            return false;
        }
        AppMstUserRole other = (AppMstUserRole) object;
        if ((this.appMstUserRoleId == null && other.appMstUserRoleId != null) || (this.appMstUserRoleId != null && !this.appMstUserRoleId.equals(other.appMstUserRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gov.app.web.model.AppMstUserRole[ appMstUserRoleId=" + appMstUserRoleId + " ]";
    }
    
}
