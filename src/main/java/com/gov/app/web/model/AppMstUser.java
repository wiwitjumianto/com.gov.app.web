/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gov.app.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.hibernate.annotations.Where;

/**
 *
 * @author agung
 */
@Entity
@Table(name = "app_mst_user")
@Where(clause = "DELETED_STATUS = 0")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppMstUser.findAll", query = "SELECT a FROM AppMstUser a"),
    @NamedQuery(name = "AppMstUser.findByAppMstUserId", query = "SELECT a FROM AppMstUser a WHERE a.appMstUserId = :appMstUserId"),
    @NamedQuery(name = "AppMstUser.findByAppMstUserName", query = "SELECT a FROM AppMstUser a WHERE a.appMstUserName = :appMstUserName"),
    @NamedQuery(name = "AppMstUser.findByAppMstUserPassword", query = "SELECT a FROM AppMstUser a WHERE a.appMstUserPassword = :appMstUserPassword"),
    @NamedQuery(name = "AppMstUser.findByStatus", query = "SELECT a FROM AppMstUser a WHERE a.status = :status"),
    @NamedQuery(name = "AppMstUser.findByUserCreated", query = "SELECT a FROM AppMstUser a WHERE a.userCreated = :userCreated"),
    @NamedQuery(name = "AppMstUser.findByDateCreated", query = "SELECT a FROM AppMstUser a WHERE a.dateCreated = :dateCreated"),
    @NamedQuery(name = "AppMstUser.findByUserUpdated", query = "SELECT a FROM AppMstUser a WHERE a.userUpdated = :userUpdated"),
    @NamedQuery(name = "AppMstUser.findByDateUpdated", query = "SELECT a FROM AppMstUser a WHERE a.dateUpdated = :dateUpdated"),
    @NamedQuery(name = "AppMstUser.findByUserDeleted", query = "SELECT a FROM AppMstUser a WHERE a.userDeleted = :userDeleted"),
    @NamedQuery(name = "AppMstUser.findByDateDeleted", query = "SELECT a FROM AppMstUser a WHERE a.dateDeleted = :dateDeleted"),
    @NamedQuery(name = "AppMstUser.findByDeletedStatus", query = "SELECT a FROM AppMstUser a WHERE a.deletedStatus = :deletedStatus")})
public class AppMstUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "APP_MST_USER_ID")
    private BigDecimal appMstUserId;
    @Column(name = "APP_MST_USER_ROLE_ID")
    private BigDecimal appMstUserRoleId;
    @Size(max = 255)
    @Column(name = "APP_MST_USER_NAME")
    private String appMstUserName;
    @Size(max = 255)
    @Column(name = "APP_MST_USER_PASSWORD")
    private String appMstUserPassword;
    @Column(name = "STATUS")
    private BigDecimal status;
    @Column(name = "USER_CREATED")
    private BigDecimal userCreated;
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Column(name = "USER_UPDATED")
    private BigDecimal userUpdated;
    @Column(name = "DATE_UPDATED")
    @Temporal(TemporalType.DATE)
    private Date dateUpdated;
    @Column(name = "USER_DELETED")
    private BigDecimal userDeleted;
    @Column(name = "DATE_DELETED")
    @Temporal(TemporalType.DATE)
    private Date dateDeleted;
    @Column(name = "DELETED_STATUS")
    private BigDecimal deletedStatus;

    public AppMstUser() {
    }

    public AppMstUser(BigDecimal appMstUserId) {
        this.appMstUserId = appMstUserId;
    }

    public BigDecimal getAppMstUserId() {
        return appMstUserId;
    }

    public BigDecimal getAppMstUserRoleId() {
        return appMstUserRoleId;
    }

    public void setAppMstUserRoleId(BigDecimal appMstUserRoleId) {
        this.appMstUserRoleId = appMstUserRoleId;
    }

    public void setAppMstUserId(BigDecimal appMstUserId) {
        this.appMstUserId = appMstUserId;
    }

    public String getAppMstUserName() {
        return appMstUserName;
    }

    public void setAppMstUserName(String appMstUserName) {
        this.appMstUserName = appMstUserName;
    }

    public String getAppMstUserPassword() {
        return appMstUserPassword;
    }

    public void setAppMstUserPassword(String appMstUserPassword) {
        this.appMstUserPassword = appMstUserPassword;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public BigDecimal getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(BigDecimal userCreated) {
        this.userCreated = userCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigDecimal getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(BigDecimal userUpdated) {
        this.userUpdated = userUpdated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public BigDecimal getUserDeleted() {
        return userDeleted;
    }

    public void setUserDeleted(BigDecimal userDeleted) {
        this.userDeleted = userDeleted;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public BigDecimal getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(BigDecimal deletedStatus) {
        this.deletedStatus = deletedStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appMstUserId != null ? appMstUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppMstUser)) {
            return false;
        }
        AppMstUser other = (AppMstUser) object;
        if ((this.appMstUserId == null && other.appMstUserId != null) || (this.appMstUserId != null && !this.appMstUserId.equals(other.appMstUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gov.app.web.model.AppMstUser[ appMstUserId=" + appMstUserId + " ]";
    }

}
