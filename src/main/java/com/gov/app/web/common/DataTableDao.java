/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gov.app.web.common;



/**
 *
 *
 */
public interface DataTableDao {
    public DataTableResult getResult(DataTableRequest param, Class<?> clazz);
}
