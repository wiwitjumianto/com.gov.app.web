/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gov.app.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author agung
 */
@Entity
@Table(name = "APP_TRX_SESSION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppTrxSession.findAll", query = "SELECT a FROM AppTrxSession a")
    , @NamedQuery(name = "AppTrxSession.findByAppTrxSessionId", query = "SELECT a FROM AppTrxSession a WHERE a.appTrxSessionId = :appTrxSessionId")
    , @NamedQuery(name = "AppTrxSession.findByAppTrxUserId", query = "SELECT a FROM AppTrxSession a WHERE a.appTrxUserId = :appTrxUserId")
    , @NamedQuery(name = "AppTrxSession.findByAppTrxDateCreated", query = "SELECT a FROM AppTrxSession a WHERE a.appTrxDateCreated = :appTrxDateCreated")
    , @NamedQuery(name = "AppTrxSession.findByAppTrxDateDeleted", query = "SELECT a FROM AppTrxSession a WHERE a.appTrxDateDeleted = :appTrxDateDeleted")
    , @NamedQuery(name = "AppTrxSession.findByDeletedStatus", query = "SELECT a FROM AppTrxSession a WHERE a.deletedStatus = :deletedStatus")})
public class AppTrxSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableGenerator(
            name = "appTrxSession",
            table = "app_trx_seq",
            pkColumnName = "seq_name",
            valueColumnName = "seq_value",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "appTrxSession")

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "APP_TRX_SESSION_ID")
    private BigDecimal appTrxSessionId;


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "APP_TRX_SESSION_ID")
//    private BigDecimal APP_TRX_SESSION_ID;
    @Basic(optional = false)
    @Column(name = "APP_TRX_USER_ID")
    private BigDecimal appTrxUserId;
    @Basic(optional = false)
    @Column(name = "APP_TRX_DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appTrxDateCreated;
    @Basic(optional = false)
    @Column(name = "APP_TRX_DATE_DELETED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appTrxDateDeleted;
    @Basic(optional = false)
    @Column(name = "DELETED_STATUS")
    private BigDecimal deletedStatus;

    public AppTrxSession() {
    }

    public AppTrxSession(BigDecimal appTrxSessionId) {
        this.appTrxSessionId = appTrxSessionId;
    }

    public AppTrxSession(BigDecimal appTrxSessionId, BigDecimal appTrxUserId, Date appTrxDateCreated, Date appTrxDateDeleted, BigDecimal deletedStatus) {
        this.appTrxSessionId = appTrxSessionId;
        this.appTrxUserId = appTrxUserId;
        this.appTrxDateCreated = appTrxDateCreated;
        this.appTrxDateDeleted = appTrxDateDeleted;
        this.deletedStatus = deletedStatus;
    }

    public BigDecimal getAppTrxSessionId() {
        return appTrxSessionId;
    }

    public void setAppTrxSessionId(BigDecimal appTrxSessionId) {
        this.appTrxSessionId = appTrxSessionId;
    }

    public BigDecimal getAppTrxUserId() {
        return appTrxUserId;
    }

    public void setAppTrxUserId(BigDecimal appTrxUserId) {
        this.appTrxUserId = appTrxUserId;
    }

    public Date getAppTrxDateCreated() {
        return appTrxDateCreated;
    }

    public void setAppTrxDateCreated(Date appTrxDateCreated) {
        this.appTrxDateCreated = appTrxDateCreated;
    }

    public Date getAppTrxDateDeleted() {
        return appTrxDateDeleted;
    }

    public void setAppTrxDateDeleted(Date appTrxDateDeleted) {
        this.appTrxDateDeleted = appTrxDateDeleted;
    }

    public BigDecimal getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(BigDecimal deletedStatus) {
        this.deletedStatus = deletedStatus;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (appTrxSessionId != null ? appTrxSessionId.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof AppTrxSession)) {
//            return false;
//        }
//        AppTrxSession other = (AppTrxSession) object;
//        if ((this.appTrxSessionId == null && other.appTrxSessionId != null) || (this.appTrxSessionId != null && !this.appTrxSessionId.equals(other.appTrxSessionId))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "com.gov.app.web.model.AppTrxSession[ appTrxSessionId=" + appTrxSessionId + " ]";
    }
    
}
