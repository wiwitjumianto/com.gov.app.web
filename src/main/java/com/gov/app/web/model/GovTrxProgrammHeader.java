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
@Table(name = "gov_trx_programm_header")
@Where(clause = "DELETED_STATUS = 0")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GovTrxProgHeader.findAll", query = "SELECT g FROM GovTrxProgrammHeader g")
    ,
    @NamedQuery(name = "GovTrxProgHeader.findByGovTrxProgHeaderId", query = "SELECT g FROM GovTrxProgrammHeader g WHERE g.govTrxProgrammHeaderId = :govTrxProgrammHeaderId")
    ,
    @NamedQuery(name = "GovTrxProgHeader.findByGovTrxProgHeaderStartDate", query = "SELECT g FROM GovTrxProgrammHeader g WHERE g.govTrxProgrammHeaderStartDate = :govTrxProgrammHeaderStartDate")
    ,
    @NamedQuery(name = "GovTrxProgHeader.findByGovTrxProgHeaderEndDate", query = "SELECT g FROM GovTrxProgrammHeader g WHERE g.govTrxProgrammHeaderEndDate = :govTrxProgrammHeaderEndDate")
    ,
    @NamedQuery(name = "GovTrxProgHeader.findByStatus", query = "SELECT g FROM GovTrxProgrammHeader g WHERE g.status = :status")
    ,
    @NamedQuery(name = "GovTrxProgHeader.findByUserCreated", query = "SELECT g FROM GovTrxProgrammHeader g WHERE g.userCreated = :userCreated")
    ,
    @NamedQuery(name = "GovTrxProgHeader.findByDateCreated", query = "SELECT g FROM GovTrxProgrammHeader g WHERE g.dateCreated = :dateCreated")
    ,
    @NamedQuery(name = "GovTrxProgHeader.findByUserUpdated", query = "SELECT g FROM GovTrxProgrammHeader g WHERE g.userUpdated = :userUpdated")
    ,
    @NamedQuery(name = "GovTrxProgHeader.findByDateUpdated", query = "SELECT g FROM GovTrxProgrammHeader g WHERE g.dateUpdated = :dateUpdated")
    ,
    @NamedQuery(name = "GovTrxProgHeader.findByUserDeleted", query = "SELECT g FROM GovTrxProgrammHeader g WHERE g.userDeleted = :userDeleted")
    ,
    @NamedQuery(name = "GovTrxProgHeader.findByDateDeleted", query = "SELECT g FROM GovTrxProgrammHeader g WHERE g.dateDeleted = :dateDeleted")
    ,
    @NamedQuery(name = "GovTrxProgHeader.findByDeletedStatus", query = "SELECT g FROM GovTrxProgrammHeader g WHERE g.deletedStatus = :deletedStatus")})
public class GovTrxProgrammHeader implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GOV_TRX_PROG_HEADER_ID")
    private Integer govTrxProgrammHeaderId;
    @Lob
    @Size(max = 500)
    @Column(name = "GOV_TRX_PROG_HEADER_NAME")
    private String govTrxProgrammHeaderName;
    @Column(name = "GOV_TRX_PROG_HEADER_START_DATE")
    @Temporal(TemporalType.DATE)
    private Date govTrxProgrammHeaderStartDate;
    @Column(name = "GOV_TRX_PROG_HEADER_END_DATE")
    @Temporal(TemporalType.DATE)
    private Date govTrxProgrammHeaderEndDate;
    @Formula("(select COUNT(m.GOV_TRX_PROG_VILLAGE_ID) from GOV_TRX_PROG_DETAIL m where m.GOV_TRX_PROG_HEADER_ID = GOV_TRX_PROG_HEADER_ID AND m.DELETED_STATUS=0)")
    private String govTrxProgrammSumVillage;
    @Formula("(select SUM(m.GOV_TRX_PROG_UNIT) from GOV_TRX_PROG_DETAIL m where m.GOV_TRX_PROG_HEADER_ID = GOV_TRX_PROG_HEADER_ID and m.deleted_status=0 )")
    private String govTrxProgrammSumUnit;
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
    @Lob
    @Size(max = 500)
    @Column(name = "GOV_TRX_PROG_HEADER_ISSUER")
    private String govTrxProgrammHeaderIssuer;

    public GovTrxProgrammHeader() {
    }

    public GovTrxProgrammHeader(Integer govTrxProgrammHeaderId) {
        this.govTrxProgrammHeaderId = govTrxProgrammHeaderId;
    }

    public Integer getGovTrxProgrammHeaderId() {
        return govTrxProgrammHeaderId;
    }

    public String getGovTrxProgrammSumVillage() {
        return govTrxProgrammSumVillage;
    }

    public void setGovTrxProgrammSumVillage(String govTrxProgrammSumVillage) {
        this.govTrxProgrammSumVillage = govTrxProgrammSumVillage;
    }

    public String getGovTrxProgrammSumUnit() {
        return govTrxProgrammSumUnit;
    }

    public void setGovTrxProgrammSumUnit(String govTrxProgrammSumUnit) {
        this.govTrxProgrammSumUnit = govTrxProgrammSumUnit;
    }

    public void setGovTrxProgrammHeaderId(Integer govTrxProgrammHeaderId) {
        this.govTrxProgrammHeaderId = govTrxProgrammHeaderId;
    }

    public String getGovTrxProgrammHeaderName() {
        return govTrxProgrammHeaderName;
    }

    public void setGovTrxProgrammHeaderName(String govTrxProgrammHeaderName) {
        this.govTrxProgrammHeaderName = govTrxProgrammHeaderName;
    }

    public Date getGovTrxProgrammHeaderStartDate() {
        return govTrxProgrammHeaderStartDate;
    }

    public void setGovTrxProgrammHeaderStartDate(Date govTrxProgrammHeaderStartDate) {
        this.govTrxProgrammHeaderStartDate = govTrxProgrammHeaderStartDate;
    }

    public Date getGovTrxProgrammHeaderEndDate() {
        return govTrxProgrammHeaderEndDate;
    }

    public void setGovTrxProgrammHeaderEndDate(Date govTrxProgrammHeaderEndDate) {
        this.govTrxProgrammHeaderEndDate = govTrxProgrammHeaderEndDate;
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

    public String getGovTrxProgrammHeaderIssuer() {
        return govTrxProgrammHeaderIssuer;
    }

    public void setGovTrxProgrammHeaderIssuer(String govTrxProgrammHeaderIssuer) {
        this.govTrxProgrammHeaderIssuer = govTrxProgrammHeaderIssuer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (govTrxProgrammHeaderId != null ? govTrxProgrammHeaderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GovTrxProgrammHeader)) {
            return false;
        }
        GovTrxProgrammHeader other = (GovTrxProgrammHeader) object;
        if ((this.govTrxProgrammHeaderId == null && other.govTrxProgrammHeaderId != null) || (this.govTrxProgrammHeaderId != null && !this.govTrxProgrammHeaderId.equals(other.govTrxProgrammHeaderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gov.app.web.model.GovTrxProgrammHeader[ govTrxProgrammHeaderId=" + govTrxProgrammHeaderId + " ]";
    }

}
