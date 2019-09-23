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
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/reporting")
public class GovReportingController {

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
    @RequestMapping(value = {"/listreport"}, method = RequestMethod.GET)
    public String listVillage(ModelMap model, HttpSession session,
            HttpServletResponse response, HttpServletRequest request) {
        try {
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
            List<AppMstVillage> listVillage = daovil.findAllAppMstVillages();
            List<GovTrxProgrammDetail> listDetail = daoDetail.findAllGovTrxProgrammDetails();
            model.addAttribute("listVillage", listVillage);
            model.addAttribute("listDetail", listDetail);
        } catch (Exception e) {
            return "redirect:/login";
        }

        return "/reporting/pages/gov_reporting_base";
    }

    @RequestMapping(value = {"/gettablerealisasi/{headerId}"}, method = RequestMethod.GET)
    public String newAppMstUser(ModelMap model, @PathVariable("headerId") int headerId) {
        ModelAndView mvc = new ModelAndView();
        List<Integer> villageId = new ArrayList<>();
        villageId.add(-1);
        List<GovTrxProgrammDetail> listDetail = daoDetail.findAllGovTrxProgrammDetailsByHeader(headerId);
        for (int i = 0; i < listDetail.size(); i++) {
            villageId.add(listDetail.get(i).getGovTrxProgrammVillageId());
        }
        List<AppMstVillage> listVillage = daovil.findByVillageList(villageId);
        GovTrxProgrammHeader dataentityHeader = daoHead.findById(headerId);
        model.addAttribute("listVillage", listVillage);
        model.addAttribute("listDetail", listDetail);
        model.addAttribute("dataHeader", dataentityHeader);

        return "/reporting/partialPages/modal_realisasi_programm_table";
    }

    @RequestMapping(value = {"/getsumprovince/{headerId}"}, method = RequestMethod.GET)
    public String getSumProvince(ModelMap model, @PathVariable("headerId") int headerId) {
        ModelAndView mvc = new ModelAndView();
        List<Integer> villageId = new ArrayList<>();
        villageId.add(-1);
        List<GovTrxProgrammDetail> listDetail = daoDetail.findAllGovTrxProgrammDetailsByHeader(headerId);
        for (int i = 0; i < listDetail.size(); i++) {
            villageId.add(listDetail.get(i).getGovTrxProgrammVillageId());
        }
        List<AppMstVillage> listVillage = daovil.findByVillageList(villageId);
        GovTrxProgrammHeader dataentityHeader = daoHead.findById(headerId);
        List<Integer> provinceIdList = daovil.findByDistictProvince(villageId);
        List<AppMstProvince> listProvince = new ArrayList();
        AppMstProvince provinceEntity = new AppMstProvince();
        for (int i = 0; i < provinceIdList.size(); i++) {
            provinceEntity = new AppMstProvince();
            provinceEntity.setAppMstProvinceId(provinceIdList.get(i));
            listProvince.add(provinceEntity);
        }
        model.addAttribute("listVillage", listVillage);
        model.addAttribute("listIdProvince", listProvince);
        model.addAttribute("listDetail", listDetail);
        model.addAttribute("dataHeader", dataentityHeader);
        return "/reporting/partialPages/modal_report_sum_province";
    }

    @RequestMapping(value = {"/gettablereceiver/{headerId}"}, method = RequestMethod.GET)
    public String getReceiverTableV2(@RequestBody(required = false) Map<String, Object> param,
            ModelMap model, @PathVariable("headerId") int headerId) {
        List<AppMstVillage> listVillage = new ArrayList();
        String returnString = null;
        List<Integer> villageId = new ArrayList<>();
        villageId.add(-1);
        List<GovTrxRealization> listReceiver = new ArrayList<>();
        listReceiver = daoRealization.findAllGovTrxRealizationsByHeader(headerId);
        for (GovTrxRealization govTrxRealization : listReceiver) {
            villageId.add(govTrxRealization.getGovTrxRealizationVillageId());
        }
        GovTrxProgrammHeader header = daoHead.findById(headerId);
        listVillage = daovil.findByVillageList(villageId);
        model.addAttribute("listVillage", listVillage);
        model.addAttribute("listReceiver", listReceiver);
        model.addAttribute("dataHeader", header);
        returnString = "/reporting/partialPages/modal_detail_program_province";
        return returnString;
    }

    @RequestMapping(value = {"/gettablereceiver/{headerId}/{locationLevel}"}, method = RequestMethod.POST)
    public String getReceiverTable(@RequestBody(required = false) Map<String, Object> param,
            ModelMap model, @PathVariable("headerId") int headerId, @PathVariable(value = "locationLevel") int locLevel) {
        List<AppMstVillage> listVillage = new ArrayList();
        List<Integer> villageId = new ArrayList();
        villageId.add(-1);
        String returnString = null;
        int tp = 0;
        switch (locLevel) {
            case 1:
                List<Integer> listProvinceId = new ArrayList();
                List dataList = (List) param.get("provinceId");
                for (int i = 0; i < dataList.size(); i++) {
                    tp = Integer.parseInt(dataList.get(i).toString());
                    listProvinceId.add(tp);
                }
                model.addAttribute("provinceId", listProvinceId);
                listVillage = daovil.findByProvinceList(listProvinceId);
                returnString = "/reporting/partialPages/modal_detail_program_province";
                break;
            case 2:
                List<Integer> listCityId = new ArrayList();
                dataList = (List) param.get("cityId");
                for (int i = 0; i < dataList.size(); i++) {
                    tp = Integer.parseInt(dataList.get(i).toString());
                    listCityId.add(tp);
                }
                listVillage = daovil.findByCityList(listCityId);
                returnString = "/reporting/partialPages/modal_detail_program_province";
                break;
            case 3:
                List<Integer> listDistrictId = new ArrayList();
                dataList = (List) param.get("districtId");
                for (int i = 0; i < dataList.size(); i++) {
                    tp = Integer.parseInt(dataList.get(i).toString());
                    listDistrictId.add(tp);
                }
                listVillage = daovil.findByDistrictList(listDistrictId);
                returnString = "/reporting/partialPages/modal_detail_program_province";
                break;
            case 4:
                List<Integer> villageIdTp = new ArrayList();
                dataList = (List) param.get("villageId");
                for (int i = 0; i < dataList.size(); i++) {
                    tp = Integer.parseInt(dataList.get(i).toString());
                    villageIdTp.add(tp);
                }
                listVillage = daovil.findByVillageList(villageIdTp);
                returnString = "/reporting/partialPages/modal_detail_program_province";
                break;
        }
        for (int i = 0; i < listVillage.size(); i++) {
            tp = Integer.parseInt(listVillage.get(i).getAppMstVillageId().toString());
            villageId.add(tp);
        }
        List<GovTrxRealization> listRealization = daoRealization.findAllGovTrxRealizationsByVillageList(villageId, headerId);
        GovTrxProgrammHeader dataentityHeader = daoHead.findById(headerId);
        model.addAttribute("listVillage", listVillage);
        model.addAttribute("listRealization", listRealization);
        model.addAttribute("dataHeader", dataentityHeader);

        return returnString;
    }

    @RequestMapping(value = {"/getpartialpage/{reportType}"}, method = RequestMethod.GET)
    public String getPartialPageReporting(ModelMap model, @PathVariable(value = "reportType") int type) {
        List<GovTrxProgrammHeader> listHeader = new ArrayList();
        listHeader = daoHead.findAllGovTrxProgrammHeaders();
        model.addAttribute("listHeader", listHeader);
//        if (type == 1) {
//            return "/reporting/partialPages/modal_programm_header";
//        } else if (type == 2) {
//            return "/reporting/partialPages/modal_location_level";
//        }
        return "/reporting/partialPages/modal_programm_header";
    }//

    @RequestMapping(value = {"/getpartialpagelocation/{locType}"}, method = RequestMethod.GET)
    public String getPartialPageReportingLocation(ModelMap model, @PathVariable(value = "locType") int type) {
        List<AppMstProvince> dataList = daoProv.findAllAppMstProvinces();
        model.addAttribute("listProvince", dataList);
        if (type == 1) {
            return "/reporting/partialPages/modal_location_province";
        } else if (type == 2) {
            return "/reporting/partialPages/modal_location_city";
        } else if (type == 3) {
            return "/reporting/partialPages/modal_location_district";
        } else if (type == 4) {
            return "/reporting/partialPages/modal_location_village";
        }
        return "/reporting/partialPages/modal_programm_header";
    }

    @RequestMapping(value = "/download/programdetail/{headerId}", method = RequestMethod.GET)
    public void downloadDetailProgramm(HttpServletRequest request, HttpServletResponse response,
            @PathVariable(value = "headerId") int headerId) throws IOException {

        List<GovTrxProgrammDetail> listDetail = daoDetail.findAllGovTrxProgrammDetailsByHeader(headerId);

        List<Integer> villageId = new ArrayList<>();
        villageId.add(-1);
        for (int i = 0; i < listDetail.size(); i++) {
            villageId.add(listDetail.get(i).getGovTrxProgrammVillageId());
        }
        List<Integer> listCityId = daovil.findByDistictCity(villageId);

        List<AppMstVillage> villageList = daovil.findByVillageList(villageId);
        GovTrxProgrammHeader dataentityHeader = daoHead.findById(headerId);

        XSSFWorkbook workbook = new XSSFWorkbook();

        response.setHeader("Content-Disposition", "attachment; filename=report_" + dataentityHeader.getGovTrxProgrammHeaderName() + "_" + new Date() + ".xlsx");
        response.setContentType("application/vnd.ms-excel");

        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFSheet sheet;
        XSSFCell cell1;
        XSSFRow row1;

        sheet = wb.createSheet("Sheet 1");

        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

        XSSFCellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);

        XSSFFormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

        String cellFormula = "";

        int rowNum = 0;
        int colnum = 0;
        XSSFRow row;

        //Header table sheet DJP Position
        row = sheet.createRow(rowNum++);

        XSSFCell cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("No");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Provinsi");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Kota/Kabupaten");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Kecamatan");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Desa/Kelurahan");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jumlah Usulan Unit");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jumlah Terealisasi");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jumlah Belum Terealisasi");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jumlah PK");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jumlah PB");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Instansi Pengusul");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nama Pengusul");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Tanggal Mengusulkan");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nomor Surat");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date_propos;

        String cityName = "";
        int sumVillage = 0, sumUnit = 0, sumRealization = 0, sumUnRealization = 0;
        int rw = 0;
        int minrow = 0;
        for (int j = 0; j < listCityId.size(); j++) {
            int unit = 0, realization = 0, unrealization = 0, sumPk = 0, sumPb = 0;
            for (int k = 0; k < villageList.size(); k++) {
                if (listCityId.get(j) == villageList.get(k).getAppMstCityId()) {
                    cityName = villageList.get(k).getAppMstCityName();
                    for (int l = 0; l < listDetail.size(); l++) {
                        if (listDetail.get(l).getGovTrxProgrammVillageId() == villageList.get(k).getAppMstVillageId()) {

                            row1 = getRow(sheet, rw + 1);
                            for (int i = 0; i < 14; i++) {
                                sheet.autoSizeColumn(i);
                                switch (i) {
                                    case 0:
                                        cell1 = row1.createCell(i);
                                        cell1.setCellValue(rw - minrow);
                                        break;
                                    case 1:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(villageList.get(k).getAppMstProvinceName());
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                    case 2:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(villageList.get(k).getAppMstCityName());
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                    case 3:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(villageList.get(k).getAppMstDistrictName());
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                    case 4:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(villageList.get(k).getAppMstVillageName());
                                            sumVillage++;
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                    case 5:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(listDetail.get(l).getGovTrxProgrammUnit());
                                            unit += listDetail.get(l).getGovTrxProgrammUnit();
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                    case 6:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(listDetail.get(l).getGovTrxProgrammUnitRealization());
                                            realization += listDetail.get(l).getGovTrxProgrammUnitRealization();
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                    case 7:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(listDetail.get(l).getGovTrxProgrammUnit() - listDetail.get(l).getGovTrxProgrammUnitRealization());
                                            unrealization += (listDetail.get(l).getGovTrxProgrammUnit() - listDetail.get(l).getGovTrxProgrammUnitRealization());
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                    case 8:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(listDetail.get(l).getGovTrxSumPk());
                                            sumPk += listDetail.get(l).getGovTrxSumPk();
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                    case 9:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(listDetail.get(l).getGovTrxSumPb());
                                            sumPb += listDetail.get(l).getGovTrxSumPb();
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                    case 10:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(listDetail.get(l).getProposerName());
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                    case 11:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(listDetail.get(l).getGovTrxProgrammDetailIssuer());
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                    case 12:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(df.format(listDetail.get(l).getDatePropos()));
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                    case 13:
                                        cell1 = row1.createCell(i);
                                        try {
                                            cell1.setCellValue(listDetail.get(l).getLetterNumber());
                                        } catch (Exception e) {
                                            cell1.setCellValue("-");
                                        }
                                        break;
                                }
                            }
                            rw++;
                        }
                    }
                }
            }
            row = getRow(sheet, rw + 1);
            colnum = 0;
            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);
            cell.setCellValue("Jumlah Untuk Kabupaten/Kota " + cityName);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);
            cell.setCellValue(unit);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);
            cell.setCellValue(realization);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);
            cell.setCellValue(unrealization);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);
            cell.setCellValue(sumPk);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);
            cell.setCellValue(sumPb);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);

            cell = row.createCell(colnum++);
            cell.setCellStyle(cellStyle2);
            rw++;
            minrow++;
        }

        wb.write(response.getOutputStream());
    }

    @RequestMapping(value = "/download/receiver/{headerId}", method = RequestMethod.GET)
    public void downloadReceiverData(HttpServletRequest request, HttpServletResponse response,
            @PathVariable(value = "headerId") int headerId) throws IOException {

        List<AppMstVillage> listVillage = new ArrayList();
        List<Integer> villageId = new ArrayList<>();
        List<GovTrxRealization> listReceiver = new ArrayList<>();
        listReceiver = daoRealization.findAllGovTrxRealizationsByHeader(headerId);
        for (GovTrxRealization govTrxRealization : listReceiver) {
            villageId.add(govTrxRealization.getGovTrxRealizationVillageId());
        }
        GovTrxProgrammHeader header = daoHead.findById(headerId);
        listVillage = daovil.findByVillageList(villageId);

        XSSFWorkbook workbook = new XSSFWorkbook();

        response.setHeader("Content-Disposition", "attachment; filename=report_receiver_" + header.getGovTrxProgrammHeaderName() + "_" + new Date() + ".xlsx");
        response.setContentType("application/vnd.ms-excel");

        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFSheet sheet;
        XSSFCell cell1;
        XSSFRow row1;

        sheet = wb.createSheet("Sheet 1");

        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

        XSSFCellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);

        XSSFFormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

        String cellFormula = "";

        int rowNum = 0;
        int colnum = 0;
        XSSFRow row;

        //Header table sheet DJP Position
        row = sheet.createRow(rowNum++);

        XSSFCell cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("No");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Provinsi");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Kota/Kabupaten");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Kecamatan");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Desa/Kelurahan");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nama Penerima");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("NIK");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jenis Kelamin");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Alamat");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Nilai Bantuan");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Keterangan");

        String tp = "";
        int sumVillage = 0, sumUnit = 0, sumRealization = 0, sumUnRealization = 0;

        int roww = 0;

        for (int x = 0; x < listReceiver.size(); x++) {
            for (int j = 0; j < listVillage.size(); j++) {
                if (listVillage.get(j).getAppMstVillageId() == listReceiver.get(x).getGovTrxRealizationVillageId()) {
                    roww++;
                    row1 = getRow(sheet, x + 1);
                    for (int i = 0; i < 11; i++) {
                        sheet.autoSizeColumn(i);
                        switch (i) {
                            case 0:
                                cell1 = row1.createCell(i);
                                cell1.setCellValue(roww);
                                break;
                            case 1:
                                cell1 = row1.createCell(i);
                                try {
                                    cell1.setCellValue(listVillage.get(j).getAppMstProvinceName());
                                } catch (Exception e) {
                                    cell1.setCellValue("-");
                                }
                                break;
                            case 2:
                                cell1 = row1.createCell(i);
                                try {
                                    cell1.setCellValue(listVillage.get(j).getAppMstCityName());
                                } catch (Exception e) {
                                    cell1.setCellValue("-");
                                }
                                break;
                            case 3:
                                cell1 = row1.createCell(i);
                                try {
                                    cell1.setCellValue(listVillage.get(j).getAppMstDistrictName());
                                } catch (Exception e) {
                                    cell1.setCellValue("-");
                                }
                                break;
                            case 4:
                                cell1 = row1.createCell(i);
                                try {
                                    cell1.setCellValue(listVillage.get(j).getAppMstVillageName());
                                    sumVillage++;
                                } catch (Exception e) {
                                    cell1.setCellValue("-");
                                }
                                break;
                            case 5:
                                cell1 = row1.createCell(i);
                                try {
                                    cell1.setCellValue(listReceiver.get(x).getGovTrxRealizationReceiverName());
                                } catch (Exception e) {
                                    cell1.setCellValue("-");
                                }
                                break;
                            case 6:
                                cell1 = row1.createCell(i);
                                try {
                                    cell1.setCellValue(listReceiver.get(x).getGovTrxRealizationReceiverNik());
                                } catch (Exception e) {
                                    cell1.setCellValue("-");
                                }
                                break;
                            case 7:
                                cell1 = row1.createCell(i);
                                try {
                                    if (listReceiver.get(x).getGovTrxRealizationReceiverGender() == 1) {
                                        cell1.setCellValue("Laki-Laki");
                                    } else {
                                        cell1.setCellValue("Perempuan");
                                    }
                                } catch (Exception e) {
                                    cell1.setCellValue("-");
                                }
                                break;
                            case 8:
                                cell1 = row1.createCell(i);
                                try {
                                    cell1.setCellValue(listReceiver.get(x).getGovTrxRealizationReceiverAddress());
                                } catch (Exception e) {
                                    cell1.setCellValue("-");
                                }
                                break;
                            case 9:
                                cell1 = row1.createCell(i);
                                try {
                                    cell1.setCellValue(listReceiver.get(x).getGovTrxRealizationValue().toString());
                                } catch (Exception e) {
                                    cell1.setCellValue("-");
                                }
                                break;
                            case 10:
                                cell1 = row1.createCell(i);
                                try {
                                    if (listReceiver.get(x).getGovTrxRealizationPkPb().equals("1")) {
                                        cell1.setCellValue("PK");
                                    } else {
                                        cell1.setCellValue("PB");
                                    }
                                } catch (Exception e) {
                                    cell1.setCellValue("-");
                                }
                                break;
                        }
                    }
                }
            }

        }

        wb.write(response.getOutputStream());
    }

    @RequestMapping(value = "/download/sumprov/{headerId}", method = RequestMethod.GET)
    public void downloadRSumProvData(HttpServletRequest request, HttpServletResponse response,
            @PathVariable(value = "headerId") int headerId) throws IOException {

        List<Integer> villageId = new ArrayList<>();
        villageId.add(-1);
        List<GovTrxProgrammDetail> listDetail = daoDetail.findAllGovTrxProgrammDetailsByHeader(headerId);
        for (int i = 0; i < listDetail.size(); i++) {
            villageId.add(listDetail.get(i).getGovTrxProgrammVillageId());
        }
        List<AppMstVillage> listVillage = daovil.findByVillageList(villageId);
        GovTrxProgrammHeader dataentityHeader = daoHead.findById(headerId);
        List<Integer> provinceIdList = daovil.findByDistictProvince(villageId);
        List<AppMstProvince> listProvince = new ArrayList();
        AppMstProvince provinceEntity = new AppMstProvince();
        for (int i = 0; i < provinceIdList.size(); i++) {
            provinceEntity = new AppMstProvince();
            provinceEntity.setAppMstProvinceId(provinceIdList.get(i));
            listProvince.add(provinceEntity);
        }

        XSSFWorkbook workbook = new XSSFWorkbook();

        response.setHeader("Content-Disposition", "attachment; filename=report_jumlah_realisasi_" + dataentityHeader.getGovTrxProgrammHeaderName() + "_" + new Date() + ".xlsx");
        response.setContentType("application/vnd.ms-excel");

        XSSFWorkbook wb = new XSSFWorkbook();

        XSSFSheet sheet;
        XSSFCell cell1;
        XSSFRow row1;

        sheet = wb.createSheet("Sheet 1");

        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

        XSSFCellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);

        XSSFFormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

        String cellFormula = "";

        int rowNum = 0;
        int colnum = 0;
        XSSFRow row;

        //Header table sheet DJP Position
        row = sheet.createRow(rowNum++);

        XSSFCell cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("No");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Provinsi");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jumlah Unit");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jumlah Unit Terealisasi");

        cell = row.createCell(colnum++);
        cell.setCellStyle(cellStyle2);
        cell.setCellValue("Jumlah Unit Belum Terealisasi");

        String tp = "";
        int sumVillage = 0, sumUnit = 0, sumRealization = 0, sumUnRealization = 0;
        int j = 0;
        for (; j < listProvince.size(); j++) {
            String provinceName = "";
            int unit = 0, realization = 0, unRealization = 0;

            for (int x = 0; x < listVillage.size(); x++) {
                if (listVillage.get(x).getAppMstProvinceId() == listProvince.get(j).getAppMstProvinceId()) {
                    provinceName = listVillage.get(x).getAppMstProvinceName();
                    for (int i = 0; i < listDetail.size(); i++) {
                        if (listVillage.get(x).getAppMstVillageId() == listDetail.get(i).getGovTrxProgrammVillageId()) {
                            unit += listDetail.get(i).getGovTrxProgrammUnit();
                            realization += listDetail.get(i).getGovTrxProgrammUnitRealization();
                            unRealization += (listDetail.get(i).getGovTrxProgrammUnit() - listDetail.get(i).getGovTrxProgrammUnitRealization());
                        }
                    }
                }
            }
            row1 = getRow(sheet, j + 1);
            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
                switch (i) {
                    case 0:
                        cell1 = row1.createCell(i);
                        cell1.setCellValue(j + 1);
                        break;
                    case 1:
                        cell1 = row1.createCell(i);
                        try {
                            cell1.setCellValue(provinceName);
                        } catch (Exception e) {
                            cell1.setCellValue("-");
                        }
                        break;
                    case 2:
                        cell1 = row1.createCell(i);
                        try {
                            cell1.setCellValue(unit);
                        } catch (Exception e) {
                            cell1.setCellValue("-");
                        }
                        break;
                    case 3:
                        cell1 = row1.createCell(i);
                        try {
                            cell1.setCellValue(realization);
                        } catch (Exception e) {
                            cell1.setCellValue("-");
                        }
                        break;
                    case 4:
                        cell1 = row1.createCell(i);
                        try {
                            cell1.setCellValue(unRealization);
                            sumVillage++;
                        } catch (Exception e) {
                            cell1.setCellValue("-");
                        }
                        break;
                }
            }

        }

        wb.write(response.getOutputStream());
    }

    private XSSFRow getRow(XSSFSheet sheet, int rowNum) {
        int maxRow = sheet.getPhysicalNumberOfRows();
        if (maxRow > 0 && rowNum < maxRow) {
            return sheet.getRow(rowNum);
        } else {
            return sheet.createRow(rowNum);
        }
    }
}
