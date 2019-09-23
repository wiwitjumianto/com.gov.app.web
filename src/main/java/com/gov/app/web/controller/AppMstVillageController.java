package com.gov.app.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.app.web.common.DataTableDao;
import com.gov.app.web.common.DataTableRequest;
import com.gov.app.web.common.DataTableResult;
import com.gov.app.web.dao.AppMstCityDao;
import com.gov.app.web.model.AppMstVillage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gov.app.web.dao.AppMstVillageDao;
import com.gov.app.web.dao.AppMstDistrictDao;
import com.gov.app.web.dao.AppMstProvinceDao;
import com.gov.app.web.model.AppMstCity;
import com.gov.app.web.model.AppMstDistrict;
import com.gov.app.web.model.AppMstProvince;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app/village")
public class AppMstVillageController {

    @Autowired
    private AppMstDistrictDao daodistrict;

    @Autowired
    private AppMstCityDao daocity;

    @Autowired
    private AppMstVillageDao daovil;

    @Autowired
    private AppMstProvinceDao daoProv;

    @Autowired
    private MessageSource messageSource;

    /*
	 * Add a new AppMstVillage.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listVillage(HttpSession session,
            HttpServletResponse response, HttpServletRequest request) {
        try {
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return "redirect:/login";
        }
        return "/app/pages/app_village";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstVillage.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        List<AppMstProvince> dataProv = daoProv.findAllAppMstProvinces();
        List<AppMstCity> dataCity = daocity.findAllAppMstCitys();
        List<AppMstDistrict> dataDistrict = daodistrict.findAllAppMstDistricts();
        mvc.addObject("listProvince", dataProv);
        mvc.addObject("listCity", dataCity);
        mvc.addObject("listDistrict", dataDistrict);
        if (params != null && params.containsKey("appMstVillageId")) {
            int id = Integer.parseInt(params.get("appMstVillageId").toString());
            AppMstVillage data = daovil.findById(id);
            if (data != null) {
                mvc.addObject("listVillage", data);
            }
        }
        mvc.setViewName("/app/partialPages/modal_app_village");
        return mvc;
    }

    @RequestMapping(value = {"/getbyid"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editAppMstCity(
            @RequestBody Map<String, Object> params, ModelMap model) {
        int code;
        code = Integer.parseInt(params.get("appMstVillageId").toString());
        Map<String, Object> dataResult = new HashMap<>();
        AppMstVillage vilageEntity = new AppMstVillage();
        vilageEntity = daovil.findById(code);
        dataResult.put("villageEntity", vilageEntity);
        return dataResult;
    }


    /*
	 * Handling POST request for validating the city input and saving AppMstVillage in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstVillage(@RequestBody Map<String, Object> params,
            @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        AppMstVillage dataVillage = new AppMstVillage();
        try {
            if (isSave == 1) {
                dataVillage = new AppMstVillage();
                dataVillage.setAppMstVillageName(params.get("appMstVillageName").toString());
                dataVillage.setAppMstProvinceId(Integer.parseInt(params.get("appMstProvinceId").toString()));
                dataVillage.setAppMstCityId(Integer.parseInt(params.get("appMstCityId").toString()));
                dataVillage.setAppMstDistrictId(Integer.parseInt(params.get("appMstDistrictId").toString()));
                dataVillage.setStatus(Integer.parseInt(params.get("status").toString()));
                dataVillage.setDeletedStatus(0);
                dataVillage.setDateCreated(new Date());
                daovil.saveAppMstVillage(dataVillage);
            } else if (isSave == 2) {
                dataVillage = new AppMstVillage();
                int id = Integer.parseInt(params.get("appMstVillageId").toString());
                dataVillage = daovil.findById(id);
                dataVillage.setAppMstVillageName(params.get("appMstVillageName").toString());
                dataVillage.setAppMstProvinceId(Integer.parseInt(params.get("appMstProvinceId").toString()));
                dataVillage.setAppMstCityId(Integer.parseInt(params.get("appMstCityId").toString()));
                dataVillage.setAppMstDistrictId(Integer.parseInt(params.get("appMstDistrictId").toString()));
                dataVillage.setStatus(Integer.parseInt(params.get("status").toString()));
                dataVillage.setDeletedStatus(0);
                dataVillage.setDateUpdated(new Date());
                daovil.saveOrUpdate(dataVillage);
            } else {
                dataVillage = new AppMstVillage();
                int id = Integer.parseInt(params.get("appMstVillageId").toString());
                dataVillage = daovil.findById(id);
                dataVillage.setDeletedStatus(1);
                dataVillage.setDateDeleted(new Date());
                daovil.saveOrUpdate(dataVillage);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = {"/getvillagebydistrict"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getByDistrict(
            @RequestBody Map<String, Object> params, ModelMap model) {
        int code;
        code = Integer.parseInt(params.get("appMstDistrictId").toString());
        Map<String, Object> dataResult = new HashMap<>();
        List<AppMstVillage> listDistrict = new ArrayList<>();
        listDistrict = daovil.findAppMstVillageByDistrict(code);
        dataResult.put("listVillageByDistrict", listDistrict);
        return dataResult;
    }
}
