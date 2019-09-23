package com.gov.app.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.app.web.common.DataTableDao;
import com.gov.app.web.common.DataTableRequest;
import com.gov.app.web.common.DataTableResult;
import com.gov.app.web.model.AppMstCity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gov.app.web.dao.AppMstCityDao;
import com.gov.app.web.dao.AppMstProvinceDao;
import com.gov.app.web.model.AppMstProvince;
import java.util.ArrayList;
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
@RequestMapping("/app/city")
public class AppMstCityController {

    @Autowired
    private AppMstCityDao daocity;

    @Autowired
    private AppMstProvinceDao daoProv;

    @Autowired
    private MessageSource messageSource;

    /*
	 * Add a new AppMstCity.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listCity(HttpSession session,
            HttpServletResponse response, HttpServletRequest request) {
        try {
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return "redirect:/login";
        }
        return "/app/pages/app_city";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstCity.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        List<AppMstProvince> dataProv = daoProv.findAllAppMstProvinces();
        mvc.addObject("listProvince", dataProv);
        if (params != null && params.containsKey("appMstCityId")) {
            int id = Integer.parseInt(params.get("appMstCityId").toString());
            AppMstCity data = daocity.findById(id);
            if (data != null) {
                mvc.addObject("cityList", data);
            }
        }
        mvc.setViewName("/app/partialPages/modal_app_city");
        return mvc;
    }


    /*
	 * Handling POST request for validating the city input and saving AppMstCity in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstCity(@RequestBody Map<String, Object> params,
            @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        AppMstCity dataCity = new AppMstCity();
        try {
            if (isSave == 1) {
                dataCity = new AppMstCity();
                dataCity.setAppMstCityName(params.get("appMstCityName").toString());
                dataCity.setAppMstProvinceId(Integer.parseInt(params.get("appMstProvinceId").toString()));
                dataCity.setStatus(Integer.parseInt(params.get("status").toString()));
                dataCity.setDeletedStatus(0);
                dataCity.setDateCreated(new Date());
                daocity.saveAppMstCity(dataCity);
            } else if (isSave == 2) {
                dataCity = new AppMstCity();
                int id = Integer.parseInt(params.get("appMstCityId").toString());
                dataCity = daocity.findById(id);
                dataCity.setAppMstCityName(params.get("appMstCityName").toString());
                dataCity.setAppMstProvinceId(Integer.parseInt(params.get("appMstProvinceId").toString()));
                dataCity.setStatus(Integer.parseInt(params.get("status").toString()));
                dataCity.setDeletedStatus(0);
                dataCity.setDateUpdated(new Date());
                daocity.saveOrUpdate(dataCity);
            } else {
                dataCity = new AppMstCity();
                int id = Integer.parseInt(params.get("appMstCityId").toString());
                dataCity = daocity.findById(id);
                dataCity.setDeletedStatus(1);
                dataCity.setDateDeleted(new Date());
                daocity.saveOrUpdate(dataCity);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = {"/getcitybyprovince"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editAppMstCity(
            @RequestBody Map<String, Object> params, ModelMap model) {
        int code;
        code = Integer.parseInt(params.get("appMstProvinceId").toString());
        Map<String, Object> dataResult = new HashMap<>();
        List<AppMstCity> listCity = new ArrayList<>();
        listCity = daocity.findAppMstCityByProvince(code);
        dataResult.put("listCityByprov", listCity);
        return dataResult;
    }
}
