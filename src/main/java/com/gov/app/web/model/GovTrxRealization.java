/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gov.app.web.model;

import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author agung
 */
@Entity
@Table(name = "gov_trx_realization")
@Where(clause = "DELETED_STATUS = 0")
@XmlRootElement
public class GovTrxRealization implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GOV_TRX_RLZ_ID")
    private Integer govTrxRealiszationId;
    @Column(name = "GOV_TRX_RLZ_VILLAGE_ID")
    private Integer govTrxRealizationVillageId;
    @Size(max = 100)
    @Column(name = "GOV_TRX_RLZ_RECEIVER_NAME")
    private String govTrxRealizationReceiverName;
    @Size(max = 16)
    @Column(name = "GOV_TRX_RLZ_RECEIVER_NIK")
    private String govTrxRealizationReceiverNik;
    @Column(name = "GOV_TRX_RLZ_RECEIVER_GENDER")
    private Integer govTrxRealizationReceiverGender;
    @Lob
    @Size(max = 500)
    @Column(name = "GOV_TRX_RLZ_RECEIVER_ADDRESS")
    private String govTrxRealizationReceiverAddress;
    @Column(name = "GOV_TRX_RLZ_VALUE")
    private BigDecimal govTrxRealizationValue;
    @Size(max = 500)
    @Column(name = "GOV_TRX_RLZ_PK_PB")
    private String govTrxRealizationPkPb;
    @Column(name = "GOV_TRX_PROG_HEADER_ID")
    private Integer govTrxProgrammHeaderId;
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

    public GovTrxRealization() {
    }

    public GovTrxRealization(Integer govTrxRealiszationId) {
        this.govTrxRealiszationId = govTrxRealiszationId;
    }

    public Integer getGovTrxRealiszationId() {
        return govTrxRealiszationId;
    }

    public void setGovTrxRealiszationId(Integer govTrxRealiszationId) {
        this.govTrxRealiszationId = govTrxRealiszationId;
    }

    public Integer getGovTrxRealizationVillageId() {
        return govTrxRealizationVillageId;
    }

    public void setGovTrxRealizationVillageId(Integer govTrxRealizationVillageId) {
        this.govTrxRealizationVillageId = govTrxRealizationVillageId;
    }

    public String getGovTrxRealizationReceiverName() {
        return govTrxRealizationReceiverName;
    }

    public void setGovTrxRealizationReceiverName(String govTrxRealizationReceiverName) {
        this.govTrxRealizationReceiverName = govTrxRealizationReceiverName;
    }

    public String getGovTrxRealizationReceiverNik() {
        return govTrxRealizationReceiverNik;
    }

    public void setGovTrxRealizationReceiverNik(String govTrxRealizationReceiverNik) {
        this.govTrxRealizationReceiverNik = govTrxRealizationReceiverNik;
    }

    public Integer getGovTrxRealizationReceiverGender() {
        return govTrxRealizationReceiverGender;
    }

    public void setGovTrxRealizationReceiverGender(Integer govTrxRealizationReceiverGender) {
        this.govTrxRealizationReceiverGender = govTrxRealizationReceiverGender;
    }

    public String getGovTrxRealizationReceiverAddress() {
        return govTrxRealizationReceiverAddress;
    }

    public void setGovTrxRealizationReceiverAddress(String govTrxRealizationReceiverAddress) {
        this.govTrxRealizationReceiverAddress = govTrxRealizationReceiverAddress;
    }

    public BigDecimal getGovTrxRealizationValue() {
        return govTrxRealizationValue;
    }

    public void setGovTrxRealizationValue(BigDecimal govTrxRealizationValue) {
        this.govTrxRealizationValue = govTrxRealizationValue;

    }

    public Integer getGovTrxProgrammHeaderId() {
        return govTrxProgrammHeaderId;
    }

    public void setGovTrxProgrammHeaderId(Integer govTrxProgrammHeaderId) {
        this.govTrxProgrammHeaderId = govTrxProgrammHeaderId;
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

    public String getGovTrxRealizationPkPb() {
        return govTrxRealizationPkPb;
    }

    public void setGovTrxRealizationPkPb(String govTrxRealizationPkPb) {
        this.govTrxRealizationPkPb = govTrxRealizationPkPb;
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
        hash += (govTrxRealiszationId != null ? govTrxRealiszationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GovTrxRealization)) {
            return false;
        }
        GovTrxRealization other = (GovTrxRealization) object;
        if ((this.govTrxRealiszationId == null && other.govTrxRealiszationId != null) || (this.govTrxRealiszationId != null && !this.govTrxRealiszationId.equals(other.govTrxRealiszationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gov.app.web.model.GovTrxRealization[ govTrxRealiszationId=" + govTrxRealiszationId + " ]";
    }

}
