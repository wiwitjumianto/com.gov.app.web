/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gov.app.web.common;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Syamsul Bachri
 */
public class DataTableResult implements Serializable{
	private long recordsTotal = 0; //before filtering
	private long recordsFiltered = 0; //after filtering
	private String draw;
	private List data;

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}	
}
