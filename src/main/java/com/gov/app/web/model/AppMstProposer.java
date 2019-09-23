/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gov.app.web.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Where;

/**
 *
 * @author agung
 */
@Entity
@Table(name = "app_mst_proposer")
@Where(clause = "DELETED_STATUS = 0")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppMstProposer.findAll", query = "SELECT a FROM AppMstProposer a WHERE a.deletedStatus=0")
})
public class AppMstProposer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "APP_MST_PROPOSER_ID")
    private Integer appMstProposerId;
    @Size(max = 100)
    @Column(name = "APP_MST_PROPOSER_NAME")
    private String appMstProposerName;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "DELETED_STATUS")
    private Integer deletedStatus;

    public AppMstProposer() {
    }

    public AppMstProposer(Integer appMstProposerId) {
        this.appMstProposerId = appMstProposerId;
    }

    public Integer getAppMstProposerId() {
        return appMstProposerId;
    }

    public void setAppMstProposerId(Integer appMstProposerId) {
        this.appMstProposerId = appMstProposerId;
    }

    public String getAppMstProposerName() {
        return appMstProposerName;
    }

    public void setAppMstProposerName(String appMstProposerName) {
        this.appMstProposerName = appMstProposerName;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appMstProposerId != null ? appMstProposerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppMstProposer)) {
            return false;
        }
        AppMstProposer other = (AppMstProposer) object;
        if ((this.appMstProposerId == null && other.appMstProposerId != null) || (this.appMstProposerId != null && !this.appMstProposerId.equals(other.appMstProposerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gov.app.web.model.AppMstProposer[ appMstProposerId=" + appMstProposerId + " ]";
    }
    
}
