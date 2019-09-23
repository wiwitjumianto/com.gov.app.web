package com.gov.app.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.app.web.common.DataTableDao;
import com.gov.app.web.common.DataTableRequest;
import com.gov.app.web.common.DataTableResult;
import com.gov.app.web.dao.AppMstCityDao;
import com.gov.app.web.model.AppMstDistrict;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gov.app.web.dao.AppMstDistrictDao;
import com.gov.app.web.dao.AppMstDistrictDao;
import com.gov.app.web.dao.AppMstProvinceDao;
import com.gov.app.web.model.AppMstCity;
import com.gov.app.web.model.AppMstProvince;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app/district")
public class AppMstDistrictController {

    @Autowired
    private AppMstDistrictDao daodistrict;

    @Autowired
    private AppMstCityDao daocity;

    @Autowired
    private AppMstProvinceDao daoProv;

    @Autowired
    private MessageSource messageSource;

    /*
	 * Add a new AppMstDistrict.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listDistrict(HttpSession session,
            HttpServletResponse response, HttpServletRequest request) {
        try {
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return "redirect:/login";
        }
        return "/app/pages/app_district";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstDistrict.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {

        ModelAndView mvc = new ModelAndView();
        List<AppMstProvince> dataProv = daoProv.findAllAppMstProvinces();
        List<AppMstCity> dataCity = daocity.findAllAppMstCitys();
        mvc.addObject("listProvince", dataProv);
        mvc.addObject("listCity", dataCity);
        if (params != null && params.containsKey("appMstDistrictId")) {
            int id = Integer.parseInt(params.get("appMstDistrictId").toString());
            AppMstDistrict data = daodistrict.findById(id);
            if (data != null) {
                mvc.addObject("listDistrict", data);
            }
        }
        mvc.setViewName("/app/partialPages/modal_app_district");
        return mvc;
    }


    /*
	 * Handling POST request for validating the district input and saving AppMstDistrict in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstDistrict(@RequestBody Map<String, Object> params,
            @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        AppMstDistrict dataDistrict = new AppMstDistrict();
        try {
            if (isSave == 1) {
                dataDistrict = new AppMstDistrict();
                dataDistrict.setAppMstDistrictName(params.get("appMstDistrictName").toString());
                dataDistrict.setAppMstProvinceId(Integer.parseInt(params.get("appMstProvinceId").toString()));
                dataDistrict.setAppMstCityId(Integer.parseInt(params.get("appMstCityId").toString()));
                dataDistrict.setStatus(Integer.parseInt(params.get("status").toString()));
                dataDistrict.setDeletedStatus(0);
                dataDistrict.setDateCreated(new Date());
                daodistrict.saveAppMstDistrict(dataDistrict);
            } else if (isSave == 2) {
                dataDistrict = new AppMstDistrict();
                int id = Integer.parseInt(params.get("appMstDistrictId").toString());
                dataDistrict = daodistrict.findById(id);
                dataDistrict.setAppMstDistrictName(params.get("appMstDistrictName").toString());
                dataDistrict.setAppMstProvinceId(Integer.parseInt(params.get("appMstProvinceId").toString()));
                dataDistrict.setAppMstCityId(Integer.parseInt(params.get("appMstCityId").toString()));
                dataDistrict.setStatus(Integer.parseInt(params.get("status").toString()));
                dataDistrict.setDeletedStatus(0);
                dataDistrict.setDateUpdated(new Date());
                daodistrict.saveOrUpdate(dataDistrict);
            } else {
                dataDistrict = new AppMstDistrict();
                int id = Integer.parseInt(params.get("appMstDistrictId").toString());
                dataDistrict = daodistrict.findById(id);
                dataDistrict.setDeletedStatus(1);
                dataDistrict.setDateDeleted(new Date());
                daodistrict.saveOrUpdate(dataDistrict);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }

    @RequestMapping(value = {"/getdistrictbycity"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editAppMstCity(
            @RequestBody Map<String, Object> params, ModelMap model) {
        int code;
        code = Integer.parseInt(params.get("appMstCityId").toString());
        Map<String, Object> dataResult = new HashMap<>();
        List<AppMstDistrict> listDistrict = new ArrayList<>();
        listDistrict = daodistrict.findAppMstDistrictByCity(code);
        dataResult.put("listDistrictByCity", listDistrict);
        return dataResult;
    }
//
//
//    /*
//	 * Provide the existing AppMstDistrict for updating.
//     */
//    @RequestMapping(value = {"/edit-{code}-student"}, method = RequestMethod.GET)
//    public String editAppMstDistrict(@PathVariable String code, ModelMap model) {
//        AppMstDistrict student = service.findAppMstDistrictByCode(code);
//        model.addAttribute("student", student);
//        model.addAttribute("edit", true);
//        return "registration";
//    }
//
//    /*
//	 * Handling POST request for validating the user input and updating AppMstDistrict in database.
//     */
//    @RequestMapping(value = {"/edit-{code}-student"}, method = RequestMethod.POST)
//    public String updateAppMstDistrict(@Valid AppMstDistrict student, BindingResult result,
//            ModelMap model, @PathVariable String code) {
//
//        if (result.hasErrors()) {
//            return "registration";
//        }
//
//        if (!service.isAppMstDistrictCodeUnique(student.getId(), student.getCode())) {
//            FieldError codeError = new FieldError("AppMstDistrict", "code", messageSource.getMessage("non.unique.code", new String[]{student.getCode()}, Locale.getDefault()));
//            result.addError(codeError);
//            return "registration";
//        }
//
//        service.updateAppMstDistrict(student);
//
//        model.addAttribute("success", "AppMstDistrict " + student.getName() + " updated successfully");
//        return "success";
//    }
//
//    /*
//	 * Delete an AppMstDistrict by it's CODE value.
//     */
//    @RequestMapping(value = {"/delete-{code}-student"}, method = RequestMethod.GET)
//    public String deleteAppMstDistrict(@PathVariable String code) {
//        service.deleteAppMstDistrictByCode(code);
//        return "redirect:/list";
//    }
}
