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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author agung
 */
@Entity
@Table(name = "app_mst_role")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppMstRole.findAll", query = "SELECT a FROM AppMstRole a"),
    @NamedQuery(name = "AppMstRole.findByAppMstRoleId", query = "SELECT a FROM AppMstRole a WHERE a.appMstRoleId = :appMstRoleId"),
    @NamedQuery(name = "AppMstRole.findByAppMstRoleName", query = "SELECT a FROM AppMstRole a WHERE a.appMstRoleName = :appMstRoleName"),
    @NamedQuery(name = "AppMstRole.findByAppMstRoleDesc", query = "SELECT a FROM AppMstRole a WHERE a.appMstRoleDesc = :appMstRoleDesc"),
    @NamedQuery(name = "AppMstRole.findByStatus", query = "SELECT a FROM AppMstRole a WHERE a.status = :status"),
    @NamedQuery(name = "AppMstRole.findByUserCreated", query = "SELECT a FROM AppMstRole a WHERE a.userCreated = :userCreated"),
    @NamedQuery(name = "AppMstRole.findByDateCreated", query = "SELECT a FROM AppMstRole a WHERE a.dateCreated = :dateCreated"),
    @NamedQuery(name = "AppMstRole.findByUserUpdated", query = "SELECT a FROM AppMstRole a WHERE a.userUpdated = :userUpdated"),
    @NamedQuery(name = "AppMstRole.findByDateUpdated", query = "SELECT a FROM AppMstRole a WHERE a.dateUpdated = :dateUpdated"),
    @NamedQuery(name = "AppMstRole.findByUserDeleted", query = "SELECT a FROM AppMstRole a WHERE a.userDeleted = :userDeleted"),
    @NamedQuery(name = "AppMstRole.findByDateDeleted", query = "SELECT a FROM AppMstRole a WHERE a.dateDeleted = :dateDeleted"),
    @NamedQuery(name = "AppMstRole.findByDeletedStatus", query = "SELECT a FROM AppMstRole a WHERE a.deletedStatus = :deletedStatus")})
public class AppMstRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "APP_MST_ROLE_ID")
    private Integer appMstRoleId;
    @Size(max = 255)
    @Column(name = "APP_MST_ROLE_NAME")
    private String appMstRoleName;
    @Size(max = 255)
    @Column(name = "APP_MST_ROLE_DESC")
    private String appMstRoleDesc;
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

    public AppMstRole() {
    }

    public AppMstRole(Integer appMstRoleId) {
        this.appMstRoleId = appMstRoleId;
    }

    public Integer getAppMstRoleId() {
        return appMstRoleId;
    }

    public void setAppMstRoleId(Integer appMstRoleId) {
        this.appMstRoleId = appMstRoleId;
    }

    public String getAppMstRoleName() {
        return appMstRoleName;
    }

    public void setAppMstRoleName(String appMstRoleName) {
        this.appMstRoleName = appMstRoleName;
    }

    public String getAppMstRoleDesc() {
        return appMstRoleDesc;
    }

    public void setAppMstRoleDesc(String appMstRoleDesc) {
        this.appMstRoleDesc = appMstRoleDesc;
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
        hash += (appMstRoleId != null ? appMstRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppMstRole)) {
            return false;
        }
        AppMstRole other = (AppMstRole) object;
        if ((this.appMstRoleId == null && other.appMstRoleId != null) || (this.appMstRoleId != null && !this.appMstRoleId.equals(other.appMstRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gov.app.web.model.AppMstRole[ appMstRoleId=" + appMstRoleId + " ]";
    }
    
}
