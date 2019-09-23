package com.gov.app.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.app.web.common.DataTableDao;
import com.gov.app.web.common.DataTableRequest;
import com.gov.app.web.common.DataTableResult;
import com.gov.app.web.dao.AppMstCityDao;
import com.gov.app.web.model.GovTrxProgrammHeader;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gov.app.web.dao.AppMstVillageDao;
import com.gov.app.web.dao.AppMstDistrictDao;
import com.gov.app.web.dao.AppMstProposerDao;
import com.gov.app.web.dao.AppMstProvinceDao;
import com.gov.app.web.dao.GovTrxProgrammDetailDao;
import com.gov.app.web.dao.GovTrxProgrammHeaderDao;
import com.gov.app.web.model.AppMstCity;
import com.gov.app.web.model.AppMstDistrict;
import com.gov.app.web.model.AppMstProposer;
import com.gov.app.web.model.AppMstProvince;
import com.gov.app.web.model.AppMstVillage;
import com.gov.app.web.model.GovTrxProgrammDetail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/programm/header")
public class GovTrxProgrammHeaderController {

    @Autowired
    private AppMstDistrictDao daodistrict;

    @Autowired
    private AppMstCityDao daocity;

    @Autowired
    private AppMstVillageDao daovil;

    @Autowired
    private AppMstProvinceDao daoProv;

    @Autowired
    private GovTrxProgrammHeaderDao daoHead;

    @Autowired
    private GovTrxProgrammDetailDao daoDetail;

    @Autowired
    private AppMstProposerDao daoProposer;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    protected DataTableDao dataTableDao;

    /*
     * Add a new GovTrxprogrammHeader.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listVillage(HttpSession session,
                              HttpServletResponse response, HttpServletRequest request) {
        try {
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return "redirect:/login";
        }
        return "/admin/pages/gov_programm";
    }

    @RequestMapping(value = {"/modal2"}, method = RequestMethod.GET)
    public ModelAndView modal2(HttpSession session,
                         HttpServletResponse response, HttpServletRequest request, ModelAndView model) {
        ModelAndView mvc = new ModelAndView();
        List<GovTrxProgrammHeader> listHeader = daoHead.findAllGovTrxProgrammHeaders();
        List<AppMstVillage> listVillage = daovil.findAllAppMstVillages();
        List<AppMstProposer> listProposer = daoProposer.findAllAppMstProposers();

        mvc.addObject("listProposer", listProposer);
        mvc.addObject("listVillage", listVillage);
        mvc.addObject("listHeader", listHeader);

        mvc.setViewName("/admin/partialPages/modal_gov_programm2");
//        try {
//            int userId = Integer.parseInt(session.getAttribute("userid").toString());
//        } catch (Exception e) {
//            return "redirect:/login";
//        }
        return mvc;
    }

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, GovTrxProgrammHeader.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        List<GovTrxProgrammHeader> listHeader = daoHead.findAllGovTrxProgrammHeaders();
        List<AppMstVillage> listVillage = daovil.findAllAppMstVillages();
        List<AppMstProposer> listProposer = daoProposer.findAllAppMstProposers();

        if (params != null && params.containsKey("govTrxProgrammHeaderId")) {
            int id = Integer.parseInt(params.get("govTrxProgrammHeaderId").toString());
            GovTrxProgrammHeader data = daoHead.findById(id);
            List<GovTrxProgrammDetail> listDetail = daoDetail.findAllGovTrxProgrammDetailsByHeader(id);

            if (data != null) {
                mvc.addObject("programmHeader", data);
                mvc.addObject("programmDetail", listDetail);

            }
        }
        mvc.addObject("listProposer", listProposer);
        mvc.addObject("listVillage", listVillage);
        mvc.addObject("listHeader", listHeader);
        mvc.setViewName("/admin/partialPages/modal_gov_programm");
        return mvc;
    }
    @RequestMapping(value = {"/modal/{dataHeader}"}, method = RequestMethod.GET)
    public ModelAndView getModalEdit(
            @PathVariable(value = "dataHeader") Integer headerId,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        List<GovTrxProgrammHeader> listHeader = daoHead.findAllGovTrxProgrammHeaders();
        List<AppMstVillage> listVillage = daovil.findAllAppMstVillages();
        List<AppMstProposer> listProposer = daoProposer.findAllAppMstProposers();

        if (headerId != null) {
            int id = headerId;
            GovTrxProgrammHeader data = daoHead.findById(id);
            List<GovTrxProgrammDetail> listDetail = daoDetail.findAllGovTrxProgrammDetailsByHeader(id);

            if (data != null) {
                mvc.addObject("programmHeader", data);
                mvc.addObject("programmDetail", listDetail);

            }
        }
        mvc.addObject("listProposer", listProposer);
        mvc.addObject("listVillage", listVillage);
        mvc.addObject("listHeader", listHeader);
        mvc.setViewName("/admin/partialPages/modal_gov_programm2");
        return mvc;
    }


    /*
	 * Handling POST request for validating the city input and saving GovTrxProgrammHeader in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveGovTrxProgrammHeader(@RequestBody Map<String, Object> params,
                                                        @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        GovTrxProgrammHeader programmHeader = new GovTrxProgrammHeader();
        List<GovTrxProgrammDetail> listProgrammDetail = new ArrayList<>();
        GovTrxProgrammDetail programmDetail = new GovTrxProgrammDetail();
        Map<String, Object> dataUnit = new HashMap();
        Map<String, Object> dataIssuer = new HashMap();
        Map<String, Object> dataProposer = new HashMap();
        Map<String, Object> datePropos = new HashMap();
        Map<String, Object> letterNumber = new HashMap();

        Boolean status = Boolean.FALSE;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate, endDate, date_Propos;
        try {
            if (isSave == 1) {
                df.setLenient(false);
                startDate = df.parse(params.get("govTrxProgrammHeaderStartDate").toString());
                df.setLenient(false);
                endDate = df.parse(params.get("govTrxProgrammHeaderEndDate").toString());
                programmHeader.setGovTrxProgrammHeaderName(params.get("govTrxProgrammHeaderName").toString());
                programmHeader.setGovTrxProgrammHeaderStartDate(startDate);
                programmHeader.setGovTrxProgrammHeaderEndDate(endDate);
                programmHeader.setStatus(0);
                programmHeader.setDateCreated(new Date());
                programmHeader.setDeletedStatus(0);
                dataUnit = (Map<String, Object>) params.get("govTrxProgrammUnit");
                dataIssuer = (Map<String, Object>) params.get("govTrxProgrammDetailIssuer");
                dataProposer = (Map<String, Object>) params.get("proposerId");
                datePropos = (Map<String, Object>) params.get("datePropos");
                letterNumber = (Map<String, Object>) params.get("letterNumber");

                for (Map.Entry<String, Object> entry : dataUnit.entrySet()) {
                    programmDetail = new GovTrxProgrammDetail();
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    df.setLenient(false);
                    date_Propos = df.parse(datePropos.get(key).toString());
                    programmDetail.setDeletedStatus(0);
                    programmDetail.setStatus(1);
                    programmDetail.setGovTrxProgrammUnit(Integer.valueOf(value.toString()));
                    programmDetail.setGovTrxProgrammVillageId(Integer.parseInt(key));
                    programmDetail.setGovTrxProgrammDetailIssuer(dataIssuer.get(key).toString());
                    programmDetail.setProposerId(Integer.parseInt(dataProposer.get(key).toString()));
                    programmDetail.setDatePropos(date_Propos);
                    programmDetail.setLetterNumber(letterNumber.get(key).toString());
                    listProgrammDetail.add(programmDetail);
                }
                status = daoHead.saveHeaderandDetail(programmHeader, listProgrammDetail);
            } else if (isSave == 2) {
                startDate = df.parse(params.get("govTrxProgrammHeaderStartDate").toString());
                endDate = df.parse(params.get("govTrxProgrammHeaderEndDate").toString());
                int headerId = Integer.parseInt(params.get("govTrxProgrammHeaderId").toString());
                programmHeader = daoHead.findById(headerId);
                programmHeader.setGovTrxProgrammHeaderName(params.get("govTrxProgrammHeaderName").toString());
                programmHeader.setGovTrxProgrammHeaderStartDate(startDate);
                programmHeader.setGovTrxProgrammHeaderEndDate(endDate);
                programmHeader.setStatus(0);
                programmHeader.setDateCreated(new Date());
                programmHeader.setDeletedStatus(0);
                dataUnit = (Map<String, Object>) params.get("govTrxProgrammUnit");
                dataIssuer = (Map<String, Object>) params.get("govTrxProgrammDetailIssuer");
                dataProposer = (Map<String, Object>) params.get("proposerId");
                datePropos = (Map<String, Object>) params.get("datePropos");
                letterNumber = (Map<String, Object>) params.get("letterNumber");
                for (Map.Entry<String, Object> entry : dataUnit.entrySet()) {
                    programmDetail = new GovTrxProgrammDetail();
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    df.setLenient(false);
                    date_Propos = df.parse(datePropos.get(key).toString());
                    programmDetail.setDeletedStatus(0);
                    programmDetail.setStatus(1);
                    programmDetail.setGovTrxProgrammUnit(Integer.valueOf(value.toString()));
                    programmDetail.setGovTrxProgrammVillageId(Integer.parseInt(key));
                    programmDetail.setGovTrxProgrammDetailIssuer(dataIssuer.get(key).toString());
                    programmDetail.setProposerId(Integer.parseInt(dataProposer.get(key).toString()));
                    programmDetail.setDatePropos(date_Propos);
                    programmDetail.setLetterNumber(letterNumber.get(key).toString());
                    listProgrammDetail.add(programmDetail);
                }
                status = daoHead.updateHeaderandDetail(programmHeader, listProgrammDetail);
            } else if (isSave == 3) {
                programmHeader = new GovTrxProgrammHeader();
                int id = Integer.parseInt(params.get("govTrxProgrammHeaderId").toString());
                programmHeader = daoHead.findById(id);
                programmHeader.setDeletedStatus(1);
                programmHeader.setDateDeleted(new Date());
                status = daoHead.deleteGovTrxProgrammHeader(programmHeader);
            } else {
                programmHeader = new GovTrxProgrammHeader();
                int id = Integer.parseInt(params.get("govTrxProgrammHeaderId").toString());
                programmHeader = daoHead.findById(id);
                programmHeader.setStatus(1);
                programmHeader.setDateDeleted(new Date());
                status = daoHead.runningGovTrxProgrammHeader(programmHeader);
            }
            result.put("status", status);
        } catch (Exception e) {
            result.put("status", status);
        }

        return result;
    }
}
