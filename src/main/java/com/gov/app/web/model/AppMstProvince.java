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
import javax.persistence.Lob;
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
@Table(name = "app_mst_province")
@Where(clause = "DELETED_STATUS = 0")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppMstProvince.findAll", query = "SELECT a FROM AppMstProvince a"),
    @NamedQuery(name = "AppMstProvince.findByAppMstProvinceId", query = "SELECT a FROM AppMstProvince a WHERE a.appMstProvinceId = :appMstProvinceId"),
    @NamedQuery(name = "AppMstProvince.findByStatus", query = "SELECT a FROM AppMstProvince a WHERE a.status = :status"),
    @NamedQuery(name = "AppMstProvince.findByUserCreated", query = "SELECT a FROM AppMstProvince a WHERE a.userCreated = :userCreated"),
    @NamedQuery(name = "AppMstProvince.findByDateCreated", query = "SELECT a FROM AppMstProvince a WHERE a.dateCreated = :dateCreated"),
    @NamedQuery(name = "AppMstProvince.findByUserUpdated", query = "SELECT a FROM AppMstProvince a WHERE a.userUpdated = :userUpdated"),
    @NamedQuery(name = "AppMstProvince.findByDateUpdated", query = "SELECT a FROM AppMstProvince a WHERE a.dateUpdated = :dateUpdated"),
    @NamedQuery(name = "AppMstProvince.findByUserDeleted", query = "SELECT a FROM AppMstProvince a WHERE a.userDeleted = :userDeleted"),
    @NamedQuery(name = "AppMstProvince.findByDateDeleted", query = "SELECT a FROM AppMstProvince a WHERE a.dateDeleted = :dateDeleted"),
    @NamedQuery(name = "AppMstProvince.findByDeletedStatus", query = "SELECT a FROM AppMstProvince a WHERE a.deletedStatus = :deletedStatus")})
public class AppMstProvince implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "APP_MST_PROVINCE_ID")
    private Integer appMstProvinceId;
    @Lob
    @Size(max = 500)
    @Column(name = "APP_MST_PROVINCE_NAME")
    private String appMstProvinceName;
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

    public AppMstProvince() {
    }

    public AppMstProvince(Integer appMstProvinceId) {
        this.appMstProvinceId = appMstProvinceId;
    }

    public Integer getAppMstProvinceId() {
        return appMstProvinceId;
    }

    public void setAppMstProvinceId(Integer appMstProvinceId) {
        this.appMstProvinceId = appMstProvinceId;
    }

    public String getAppMstProvinceName() {
        return appMstProvinceName;
    }

    public void setAppMstProvinceName(String appMstProvinceName) {
        this.appMstProvinceName = appMstProvinceName;
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
        hash += (appMstProvinceId != null ? appMstProvinceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppMstProvince)) {
            return false;
        }
        AppMstProvince other = (AppMstProvince) object;
        if ((this.appMstProvinceId == null && other.appMstProvinceId != null) || (this.appMstProvinceId != null && !this.appMstProvinceId.equals(other.appMstProvinceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gov.app.web.model.AppMstProvince[ appMstProvinceId=" + appMstProvinceId + " ]";
    }
    
}
