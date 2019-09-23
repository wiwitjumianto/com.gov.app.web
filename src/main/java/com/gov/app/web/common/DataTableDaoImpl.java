/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gov.app.web.common;

import com.gov.app.web.dao.AbstractDao;
import java.io.Serializable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 */
@Repository
public class DataTableDaoImpl
        extends AbstractDao<Serializable, Object>
        implements DataTableDao {

    @Override
    @Transactional
    public DataTableResult getResult(DataTableRequest param, Class<?> clazz) {
        return param.toDataSourceResult(getSession(), clazz);
    }
}
