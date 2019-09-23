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
import com.gov.app.web.dao.AppMstProvinceDao;
import com.gov.app.web.dao.GovTrxProgrammDetailDao;
import com.gov.app.web.dao.GovTrxProgrammHeaderDao;
import com.gov.app.web.dao.GovTrxRealizationDao;
import com.gov.app.web.model.AppMstCity;
import com.gov.app.web.model.AppMstDistrict;
import com.gov.app.web.model.AppMstProvince;
import com.gov.app.web.model.AppMstVillage;
import com.gov.app.web.model.GovTrxProgrammDetail;
import com.gov.app.web.model.GovTrxRealization;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/realization")
public class GovTrxRealizationController {

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
    private GovTrxRealizationDao daoRealization;

    @Autowired
    private MessageSource messageSource;

    /*
	 * Add a new GovTrxprogrammHeader.
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listVillage(ModelMap model) {
//        List<AppMstVillage> listVillage = daovil.findAllAppMstVillages();
//        List<GovTrxProgrammDetail> listDetail = daoDetail.findAllGovTrxProgrammDetails();
//        model.addAttribute("listVillage", listVillage);
//        model.addAttribute("listDetail", listDetail);
        return "redirect:/home";
    }

    @RequestMapping(value = {"/viewdetail/{id}/{headerId}"}, method = RequestMethod.GET)
    public String view(ModelMap model, @PathVariable(value = "id") int vilId,
            @PathVariable(value = "headerId") int headerId) {
        model.addAttribute("villageId", vilId);
        AppMstVillage datavillage = new AppMstVillage();
        datavillage = daovil.findById(vilId);
        model.addAttribute("villageName", datavillage.getAppMstVillageName());
        List<GovTrxRealization> dataList = daoRealization.findAllGovTrxRealizationsByVillage(vilId, headerId);
        model.addAttribute("dataList", dataList);
        return "/admin/partialPages/modal_gov_realization_view";
    }

    @RequestMapping(value = {"/viewdetail/village/{id}/{headerId}"}, method = RequestMethod.GET)
    public String detailVillage(ModelMap model, @PathVariable(value = "id") int vilId,
            @PathVariable(value = "headerId") int headerId) {
        model.addAttribute("villageId", vilId);
        List<GovTrxRealization> dataList = daoRealization.findAllGovTrxRealizationsByVillage(vilId, headerId);
        model.addAttribute("dataList", dataList);
        model.addAttribute("headerId", headerId);
        return "/admin/partialPages/modal_gov_realization_view";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {
        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, GovTrxProgrammDetail.class);
        return result;
    }

    @RequestMapping(value = {"/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        List<AppMstProvince> dataProv = daoProv.findAllAppMstProvinces();
        mvc.addObject("listProvince", dataProv);
        mvc.addObject("headerId", Integer.parseInt(params.get("headerId").toString()));
        mvc.setViewName("/admin/partialPages/modal_gov_realization");
        return mvc;
    }

    @RequestMapping(value = {"/modaledit/{realizationId}"}, method = RequestMethod.POST)
    public ModelAndView getModalEdit(@RequestBody(required = false) Map<String, Object> params,
            @PathVariable(value = "realizationId") int realId,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();
        GovTrxRealization realizationentity = new GovTrxRealization();
        realizationentity = daoRealization.findById(realId);
        mvc.addObject("data", realizationentity);
        mvc.addObject("villageId", params.get("villageId"));
        mvc.addObject("headerId", params.get("headerId"));
        mvc.setViewName("/admin/partialPages/modal_edit_realization");
        return mvc;
    }

    @RequestMapping(value = {"/getdataviewdetail/{headerId}"}, method = RequestMethod.POST)
    public Map<String, Object> viewDetail(
            @RequestBody(required = false) Map<String, Object> params,
            @PathVariable(value = "headerId") int headerId,
            HttpServletResponse response) throws JsonProcessingException {

        Map<String, Object> dataResult = new HashMap();
        int villageid = Integer.parseInt(params.get("villageId").toString());
        ModelAndView mvc = new ModelAndView();
        List<GovTrxRealization> dataRealization = daoRealization.findAllGovTrxRealizationsByVillage(villageid, headerId);
        AppMstVillage vilage = new AppMstVillage();
        vilage = daovil.findById(villageid);
        dataResult.put("listRealisasi", dataRealization);
        dataResult.put("villageEntity", vilage);
        return dataResult;
    }

    @RequestMapping(value = {"/saveOrUpdate/{type}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveGovTrxProgrammHeader(@RequestBody Map<String, Object> params,
            @PathVariable(value = "type") int type) {
        Map<String, Object> result = new HashMap<>();
        GovTrxRealization realization = new GovTrxRealization();
        Boolean status = Boolean.FALSE;
        Map<String, Object> dataUnit = new HashMap();
        Map<String, Object> dataIssuer = new HashMap();
        try {
            if (type == 1) {
                realization.setGovTrxRealizationReceiverName(params.get("govRealizationReceiverName").toString());
                realization.setGovTrxRealizationReceiverNik(params.get("govRealizationReceiverNik").toString());
                realization.setGovTrxRealizationReceiverGender(Integer.parseInt(params.get("govRealizationReceiverGender").toString()));
                realization.setGovTrxRealizationVillageId(Integer.parseInt(params.get("govTrxProgrammVillageId").toString()));
                realization.setGovTrxRealizationValue(new BigDecimal(params.get("govRealizationValue").toString()));
                realization.setGovTrxRealizationReceiverAddress(params.get("govRealizationReceiverAddress").toString());
                realization.setGovTrxRealizationPkPb(params.get("govRealizationPkPb").toString());
                realization.setGovTrxProgrammHeaderId(Integer.parseInt(params.get("govTrxProgrammHeaderId").toString()));
                realization.setStatus(Integer.parseInt(params.get("status").toString()));
                realization.setDateCreated(new Date());
                realization.setDeletedStatus(0);

                List data = daoRealization.findAllGovTrxRealizationsByNIK(
                        realization.getGovTrxRealizationReceiverNik(),
                        realization.getGovTrxProgrammHeaderId());

                GovTrxProgrammDetail dataDetail = daoDetail.findGovTrxProgrammDetailByvillage(
                        realization.getGovTrxRealizationVillageId(),
                        realization.getGovTrxProgrammHeaderId());

                if (data.size() <= 0) {
                    if (dataDetail.getGovTrxProgrammUnitRealization() < dataDetail.getGovTrxProgrammUnit()) {
                        daoRealization.saveGovTrxRealization(realization);
                    } else {
                        result.put("message", 1);
                    }

                } else {
                    result.put("message", 0);
                }

                result.put("status", true);
            } else {
                int headerId = Integer.parseInt(params.get("govTrxRealizationId").toString());
                realization = daoRealization.findById(headerId);
                List data = new ArrayList();
                if (!realization.getGovTrxRealizationReceiverNik().equals(params.get("govRealizationReceiverNik").toString())) {
                    data = daoRealization.findAllGovTrxRealizationsByNIK(realization.getGovTrxRealizationReceiverNik(),
                            realization.getGovTrxProgrammHeaderId());
                }
                realization.setGovTrxRealizationReceiverName(params.get("govRealizationReceiverName").toString());
                realization.setGovTrxRealizationReceiverNik(params.get("govRealizationReceiverNik").toString());
                realization.setGovTrxRealizationReceiverGender(Integer.parseInt(params.get("govRealizationReceiverGender").toString()));
                realization.setGovTrxRealizationValue(new BigDecimal(params.get("govRealizationValue").toString()));
                realization.setGovTrxRealizationReceiverAddress(params.get("govRealizationReceiverAddress").toString());
                realization.setGovTrxRealizationPkPb(params.get("govRealizationPkPb").toString());
                realization.setStatus(Integer.parseInt(params.get("status").toString()));
                realization.setDateUpdated(new Date());
                realization.setDeletedStatus(0);

                if (data.size() <= 0) {
                    daoRealization.saveOrUpdate(realization);
                } else {
                    result.put("message", 0);
                }
                result.put("status", true);
            }

        } catch (Exception e) {
            result.put("status", false);
        }

        return result;
    }
}
