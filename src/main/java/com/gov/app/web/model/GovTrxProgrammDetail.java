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

/**
 *
 * @author agung
 */
@Entity
@Table(name = "gov_trx_programm_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GovTrxProgDetail.findAll", query = "SELECT g FROM GovTrxProgrammDetail g")
})
public class GovTrxProgrammDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GOV_TRX_PROG_DETAIL_ID")
    private Integer govTrxProgrammDetailId;
    @Column(name = "GOV_TRX_PROG_HEADER_ID")
    private Integer govTrxProgrammHeaderId;
    @Column(name = "GOV_TRX_PROG_VILLAGE_ID")
    private Integer govTrxProgrammVillageId;
    @Column(name = "GOV_TRX_PROG_UNIT")
    private Integer govTrxProgrammUnit;
    @Formula("(select COUNT(m.GOV_TRX_REALIZATION_VILLAGE_ID) from GOV_TRX_REALIZATION m "
            + "where m.GOV_TRX_REALIZATION_VILLAGE_ID = GOV_TRX_PROG_VILLAGE_ID "
            + "AND m.GOV_TRX_PROG_HEADER_ID = GOV_TRX_PROG_HEADER_ID)")
    private Integer govTrxProgrammUnitRealization;
    @Formula("(select COUNT(m.GOV_TRX_REALIZATION_PK_PB) from GOV_TRX_REALIZATION m "
            + "where m.GOV_TRX_REALIZATION_VILLAGE_ID = GOV_TRX_PROG_VILLAGE_ID "
            + "AND m.GOV_TRX_PROG_HEADER_ID = GOV_TRX_PROG_HEADER_ID AND m.GOV_TRX_REALIZATION_PK_PB=1)")
    private Integer govTrxSumPk;
    @Formula("(select COUNT(m.GOV_TRX_REALIZATION_PK_PB) from GOV_TRX_REALIZATION m "
            + "where m.GOV_TRX_REALIZATION_VILLAGE_ID = GOV_TRX_PROG_VILLAGE_ID "
            + "AND m.GOV_TRX_PROG_HEADER_ID = GOV_TRX_PROG_HEADER_ID AND m.GOV_TRX_REALIZATION_PK_PB=0)")
    private Integer govTrxSumPb;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "DELETED_STATUS")
    private Integer deletedStatus;
    @Lob
    @Size(max = 500)
    @Column(name = "GOV_TRX_PROG_DETAIL_ISSUER")
    private String govTrxProgrammDetailIssuer;
    @Column(name = "PROPOSER_ID")
    private Integer proposerId;
    @Formula("(select m.APP_MST_PROPOSER_NAME from APP_MST_PROPOSER m where m.APP_MST_PROPOSER_ID = PROPOSER_ID )")
    private String proposerName;
    @Column(name = "DATE_PROPOS")
    @Temporal(TemporalType.DATE)
    private Date datePropos;
    @Size(max = 100)
    @Column(name = "LETTER_NUMBER")
    private String letterNumber;

    public GovTrxProgrammDetail() {
    }

    public Integer getProposerId() {
        return proposerId;
    }

    public void setProposerId(Integer ProposerId) {
        this.proposerId = ProposerId;
    }

    public String getProposerName() {
        return proposerName;
    }

    public void setProposerName(String proposerName) {
        this.proposerName = proposerName;
    }

    public Date getDatePropos() {
        return datePropos;
    }

    public void setDatePropos(Date datePropos) {
        this.datePropos = datePropos;
    }

    public String getLetterNumber() {
        return letterNumber;
    }

    public void setLetterNumber(String letterNumber) {
        this.letterNumber = letterNumber;
    }

    public Integer getGovTrxSumPk() {
        return govTrxSumPk;
    }

    public void setGovTrxSumPk(Integer govTrxSumPk) {
        this.govTrxSumPk = govTrxSumPk;
    }

    public Integer getGovTrxSumPb() {
        return govTrxSumPb;
    }

    public void setGovTrxSumPb(Integer govTrxSumPb) {
        this.govTrxSumPb = govTrxSumPb;
    }

    public GovTrxProgrammDetail(Integer govTrxProgrammDetailId) {
        this.govTrxProgrammDetailId = govTrxProgrammDetailId;
    }

    public Integer getGovTrxProgrammDetailId() {
        return govTrxProgrammDetailId;
    }

    public void setGovTrxProgrammDetailId(Integer govTrxProgrammDetailId) {
        this.govTrxProgrammDetailId = govTrxProgrammDetailId;
    }

    public Integer getGovTrxProgrammHeaderId() {
        return govTrxProgrammHeaderId;
    }

    public void setGovTrxProgrammHeaderId(Integer govTrxProgrammHeaderId) {
        this.govTrxProgrammHeaderId = govTrxProgrammHeaderId;
    }

    public Integer getGovTrxProgrammVillageId() {
        return govTrxProgrammVillageId;
    }

    public void setGovTrxProgrammVillageId(Integer govTrxProgrammVillageId) {
        this.govTrxProgrammVillageId = govTrxProgrammVillageId;
    }

    public Integer getGovTrxProgrammUnitRealization() {
        return govTrxProgrammUnitRealization;
    }

    public void setGovTrxProgrammUnitRealization(Integer govTrxProgrammUnitRealization) {
        this.govTrxProgrammUnitRealization = govTrxProgrammUnitRealization;
    }

    public Integer getGovTrxProgrammUnit() {
        return govTrxProgrammUnit;
    }

    public void setGovTrxProgrammUnit(Integer govTrxProgrammUnit) {
        this.govTrxProgrammUnit = govTrxProgrammUnit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(Integer deletedStatus) {
        this.deletedStatus = deletedStatus;
    }

    public String getGovTrxProgrammDetailIssuer() {
        return govTrxProgrammDetailIssuer;
    }

    public void setGovTrxProgrammDetailIssuer(String govTrxProgrammDetailIssuer) {
        this.govTrxProgrammDetailIssuer = govTrxProgrammDetailIssuer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (govTrxProgrammDetailId != null ? govTrxProgrammDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GovTrxProgrammDetail)) {
            return false;
        }
        GovTrxProgrammDetail other = (GovTrxProgrammDetail) object;
        if ((this.govTrxProgrammDetailId == null && other.govTrxProgrammDetailId != null) || (this.govTrxProgrammDetailId != null && !this.govTrxProgrammDetailId.equals(other.govTrxProgrammDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gov.app.web.model.GovTrxProgrammDetail[ govTrxProgrammDetailId=" + govTrxProgrammDetailId + " ]";
    }

}
