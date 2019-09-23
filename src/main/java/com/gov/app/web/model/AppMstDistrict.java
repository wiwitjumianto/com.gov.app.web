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
@Table(name = "app_mst_district")
@Where(clause = "DELETED_STATUS = 0")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppMstDistrict.findAll", query = "SELECT a FROM AppMstDistrict a"),
    @NamedQuery(name = "AppMstDistrict.findByAppMstDistrictId", query = "SELECT a FROM AppMstDistrict a WHERE a.appMstDistrictId = :appMstDistrictId"),
    @NamedQuery(name = "AppMstDistrict.findByAppMstCityId", query = "SELECT a FROM AppMstDistrict a WHERE a.appMstCityId = :appMstCityId"),
    @NamedQuery(name = "AppMstDistrict.findByAppMstProvinceId", query = "SELECT a FROM AppMstDistrict a WHERE a.appMstProvinceId = :appMstProvinceId"),
    @NamedQuery(name = "AppMstDistrict.findByStatus", query = "SELECT a FROM AppMstDistrict a WHERE a.status = :status"),
    @NamedQuery(name = "AppMstDistrict.findByUserCreated", query = "SELECT a FROM AppMstDistrict a WHERE a.userCreated = :userCreated"),
    @NamedQuery(name = "AppMstDistrict.findByDateCreated", query = "SELECT a FROM AppMstDistrict a WHERE a.dateCreated = :dateCreated"),
    @NamedQuery(name = "AppMstDistrict.findByUserUpdated", query = "SELECT a FROM AppMstDistrict a WHERE a.userUpdated = :userUpdated"),
    @NamedQuery(name = "AppMstDistrict.findByDateUpdated", query = "SELECT a FROM AppMstDistrict a WHERE a.dateUpdated = :dateUpdated"),
    @NamedQuery(name = "AppMstDistrict.findByUserDeleted", query = "SELECT a FROM AppMstDistrict a WHERE a.userDeleted = :userDeleted"),
    @NamedQuery(name = "AppMstDistrict.findByDateDeleted", query = "SELECT a FROM AppMstDistrict a WHERE a.dateDeleted = :dateDeleted"),
    @NamedQuery(name = "AppMstDistrict.findByDeletedStatus", query = "SELECT a FROM AppMstDistrict a WHERE a.deletedStatus = :deletedStatus")})
public class AppMstDistrict implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "APP_MST_DISTRICT_ID")
    private Integer appMstDistrictId;
    @Lob
    @Size(max = 500)
    @Column(name = "APP_MST_DISTRICT_NAME")
    private String appMstDistrictName;
    @Column(name = "APP_MST_CITY_ID")
    private Integer appMstCityId;
    @Formula("(select m.APP_MST_CITY_NAME from APP_MST_CITY m where m.APP_MST_CITY_ID = APP_MST_CITY_ID )")
    private String appMstCityName;
    @Column(name = "APP_MST_PROVINCE_ID")
    private Integer appMstProvinceId;
    @Formula("(select m.APP_MST_PROVINCE_NAME from APP_MST_PROVINCE m where m.APP_MST_PROVINCE_ID = APP_MST_PROVINCE_ID )")
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

    public AppMstDistrict() {
    }

    public AppMstDistrict(Integer appMstDistrictId) {
        this.appMstDistrictId = appMstDistrictId;
    }

    public Integer getAppMstDistrictId() {
        return appMstDistrictId;
    }

    public void setAppMstDistrictId(Integer appMstDistrictId) {
        this.appMstDistrictId = appMstDistrictId;
    }

    public String getAppMstDistrictName() {
        return appMstDistrictName;
    }

    public void setAppMstDistrictName(String appMstDistrictName) {
        this.appMstDistrictName = appMstDistrictName;
    }

    public String getAppMstCityName() {
        return appMstCityName;
    }

    public void setAppMstCityName(String appMstCityName) {
        this.appMstCityName = appMstCityName;
    }

    public String getAppMstProvinceName() {
        return appMstProvinceName;
    }

    public void setAppMstProvinceName(String appMstProvinceName) {
        this.appMstProvinceName = appMstProvinceName;
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
        hash += (appMstDistrictId != null ? appMstDistrictId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppMstDistrict)) {
            return false;
        }
        AppMstDistrict other = (AppMstDistrict) object;
        if ((this.appMstDistrictId == null && other.appMstDistrictId != null) || (this.appMstDistrictId != null && !this.appMstDistrictId.equals(other.appMstDistrictId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gov.app.web.model.AppMstDistrict[ appMstDistrictId=" + appMstDistrictId + " ]";
    }

}
