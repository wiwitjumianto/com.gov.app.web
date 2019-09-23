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
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

/**
 *
 * @author agung
 */
@Entity
@Table(name = "app_mst_city")
@Where(clause = "DELETED_STATUS = 0")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppMstCity.findAll", query = "SELECT a FROM AppMstCity a")
})
public class AppMstCity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "APP_MST_CITY_ID")
    private Integer appMstCityId;
    @Column(name = "APP_MST_PROVINCE_ID")
    private Integer appMstProvinceId;
    @Formula("(select m.APP_MST_PROVINCE_NAME from APP_MST_PROVINCE m where m.APP_MST_PROVINCE_ID = APP_MST_PROVINCE_ID )")
    private String appMstProvinceName;
    @Lob
    @Size(max = 500)
    @Column(name = "APP_MST_CITY_NAME")
    private String appMstCityName;
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

    public AppMstCity() {
    }

    public AppMstCity(Integer appMstCityId) {
        this.appMstCityId = appMstCityId;
    }

    public Integer getAppMstCityId() {
        return appMstCityId;
    }

    public void setAppMstCityId(Integer appMstCityId) {
        this.appMstCityId = appMstCityId;
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

    public String getAppMstCityName() {
        return appMstCityName;
    }

    public void setAppMstCityName(String appMstCityName) {
        this.appMstCityName = appMstCityName;
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
        hash += (appMstCityId != null ? appMstCityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppMstCity)) {
            return false;
        }
        AppMstCity other = (AppMstCity) object;
        if ((this.appMstCityId == null && other.appMstCityId != null) || (this.appMstCityId != null && !this.appMstCityId.equals(other.appMstCityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gov.app.web.model.AppMstCity[ appMstCityId=" + appMstCityId + " ]";
    }
    
}
