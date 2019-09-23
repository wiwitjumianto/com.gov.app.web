package com.gov.app.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.app.web.common.DataTableDao;
import com.gov.app.web.common.DataTableRequest;
import com.gov.app.web.common.DataTableResult;
import com.gov.app.web.model.AppMstProvince;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gov.app.web.dao.AppMstProvinceDao;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app/province")
public class AppMstProvinceController {

    @Autowired
    private AppMstProvinceDao daoprov;

    @Autowired
    private MessageSource messageSource;

    /*
	 * Add a new AppMstProvince.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listProvince(HttpSession session,
            HttpServletResponse response, HttpServletRequest request) {
        try {
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
            if (userId <= 0) {
                session.invalidate();
                return "redirect:/login";
            }
        } catch (Exception e) {
            return "redirect:/login";
        }

        return "/app/pages/app_province";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstProvince.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();

        if (params != null && params.containsKey("appMstProvinceId")) {
            int id = Integer.parseInt(params.get("appMstProvinceId").toString());
            AppMstProvince data = daoprov.findById(id);
            if (data != null) {
                mvc.addObject("provinceList", data);
            }
        }
        mvc.setViewName("/app/partialPages/modal_app_province");
        return mvc;
    }


    /*
	 * Handling POST request for validating the province input and saving AppMstProvince in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstProvince(@RequestBody Map<String, Object> params,
            @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        AppMstProvince dataProvince = new AppMstProvince();
        try {
            if (isSave == 1) {
                dataProvince = new AppMstProvince();
                dataProvince.setAppMstProvinceName(params.get("appMstProvinceName").toString());
                dataProvince.setStatus(Integer.parseInt(params.get("status").toString()));
                dataProvince.setDeletedStatus(0);
                dataProvince.setDateCreated(new Date());
                daoprov.saveAppMstProvince(dataProvince);
            } else if (isSave == 2) {
                dataProvince = new AppMstProvince();
                int id = Integer.parseInt(params.get("appMstProvinceId").toString());
                dataProvince = daoprov.findById(id);
                dataProvince.setAppMstProvinceName(params.get("appMstProvinceName").toString());
                dataProvince.setStatus(Integer.parseInt(params.get("status").toString()));
                dataProvince.setDeletedStatus(0);
                dataProvince.setDateUpdated(new Date());
                daoprov.saveOrUpdate(dataProvince);
            } else {
                dataProvince = new AppMstProvince();
                int id = Integer.parseInt(params.get("appMstProvinceId").toString());
                dataProvince = daoprov.findById(id);
                dataProvince.setDeletedStatus(1);
                dataProvince.setDateDeleted(new Date());
                daoprov.saveOrUpdate(dataProvince);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
}
