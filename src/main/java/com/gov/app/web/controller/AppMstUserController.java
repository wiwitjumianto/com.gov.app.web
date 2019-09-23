package com.gov.app.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gov.app.web.common.DataTableDao;
import com.gov.app.web.common.DataTableRequest;
import com.gov.app.web.common.DataTableResult;
import com.gov.app.web.model.AppMstUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gov.app.web.dao.AppMstUserDao;
import com.gov.app.web.dao.AppMstVillageDao;
import com.gov.app.web.dao.AppTrxSessionDao;
import com.gov.app.web.dao.GovTrxProgrammDetailDao;
import com.gov.app.web.dao.GovTrxProgrammHeaderDao;
import com.gov.app.web.model.AppMstVillage;
import com.gov.app.web.model.AppTrxSession;
import com.gov.app.web.model.GovTrxProgrammDetail;
import com.gov.app.web.model.GovTrxProgrammHeader;
import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class AppMstUserController {

    @Autowired
    private AppMstUserDao dao;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AppMstVillageDao daovil;

    @Autowired
    private AppTrxSessionDao daoSession;

    @Autowired
    private GovTrxProgrammDetailDao daoDetail;

    @Autowired
    private GovTrxProgrammHeaderDao daoHeader;

    /*
	 * List all existing AppMstUsers.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initial(ModelMap model, HttpSession session,
            HttpServletResponse response, HttpServletRequest request) {
        try {
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
            daoSession.deleteAppTrxSessionByuserId(userId);
            session.invalidate();
        } catch (Exception e) {
            session.invalidate();
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String listAppMstUsers(ModelMap model) {
        return "/app/pages/login";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(ModelMap model, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        try {
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
            daoSession.deleteAppTrxSessionByuserId(userId);
            session.invalidate();
        } catch (Exception e) {
            session.invalidate();
            return "redirect:/login";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> printWelcome(
            @RequestBody Map<String, Object> params, HttpSession session,
            HttpServletResponse response, HttpServletRequest request,
            ModelMap model) {
        String uname, pwd;
        uname = params.get("username").toString();
        pwd = params.get("password").toString();
        Map<String, Object> dataLogin = new HashMap<>();
        try {
            int resultLogin = dao.login(uname, pwd);
            if (resultLogin != 0) {
                session.setAttribute("userid", resultLogin);
                dataLogin.put("status", Boolean.TRUE);
            } else {
                dataLogin.put("status", Boolean.FALSE);
            }
        } catch (Exception e) {
            dataLogin.put("status", Boolean.FALSE);
        }
        return dataLogin;
    }

    /*
	 * Add a new AppMstUser.
     */
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public ModelAndView newAppMstUser(HttpSession session,
            HttpServletResponse response, HttpServletRequest request) {
        ModelAndView mvc = new ModelAndView();

        try {
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
            AppMstUser dataUser = new AppMstUser();
            dataUser = dao.findById(userId);
            List<AppMstVillage> listVillage = daovil.findAllAppMstVillages();
            GovTrxProgrammHeader dataentityHeader = daoHeader.findGovTrxProgrammHeaderActive();
            List<GovTrxProgrammDetail> listDetail = daoDetail.findAllGovTrxProgrammDetails();
            mvc.addObject("listVillage", listVillage);
            mvc.addObject("listDetail", listDetail);
            mvc.addObject("dataHeader", dataentityHeader);
            if (dataUser.getAppMstUserRoleId() == BigDecimal.ONE) {
                session.setAttribute("isAdmin", 1);
            }else{
                session.setAttribute("isAdmin", 0);
            }
            mvc.setViewName("/admin/pages/gov_realization");
        } catch (Exception e) {
            mvc.setViewName("redirect:/login");
            return mvc;
        }
        return mvc;
    }

    @RequestMapping(value = {"/app/configuration"}, method = RequestMethod.GET)
    public String listUser(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        try {
            int userId = Integer.parseInt(session.getAttribute("userid").toString());
        } catch (Exception e) {
            return "redirect:/login";
        }
        return "/app/pages/app_user";
    }

    @Autowired
    protected DataTableDao dataTableDao;

    @RequestMapping(value = {"/app/configuring/user/datasource"}, method = RequestMethod.POST)
    @ResponseBody
    public DataTableResult getForDataTable(@RequestBody(required = false) DataTableRequest dataTable) {

        DataTableResult result = new DataTableResult();
        result = dataTableDao.getResult(dataTable, AppMstUser.class);
        return result;
    }

    @RequestMapping(value = {"/app/modal"}, method = RequestMethod.POST)
    public ModelAndView getModal(
            @RequestBody(required = false) Map<String, Object> params,
            HttpServletResponse response) throws JsonProcessingException {
        ModelAndView mvc = new ModelAndView();

        if (params != null && params.containsKey("appMstUserId")) {
            int id = Integer.parseInt(params.get("appMstUserId").toString());
            AppMstUser data = dao.findById(id);
            if (data != null) {
                mvc.addObject("userList", data);
            }
        }
        mvc.setViewName("/app/partialPages/modal_app_user");
        return mvc;
    }


    /*
	 * Handling POST request for validating the user input and saving AppMstUser in database.
     */
    @RequestMapping(value = {"/saveOrUpdate/{isSave}"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveAppMstUser(@RequestBody Map<String, Object> params,
            @PathVariable(value = "isSave") int isSave) {
        Map<String, Object> result = new HashMap<>();
        AppMstUser dataUser = new AppMstUser();
        try {
            if (isSave == 1) {
                dataUser = new AppMstUser();
                dataUser.setAppMstUserName(params.get("appMstUserName").toString());
                dataUser.setAppMstUserPassword(params.get("appMstUserPassword").toString());
                dataUser.setAppMstUserRoleId(new BigDecimal(params.get("appMstUserRoleId").toString()));
                dataUser.setStatus(new BigDecimal(params.get("status").toString()));
                dataUser.setDeletedStatus(BigDecimal.ZERO);
                dataUser.setDateCreated(new Date());
                dao.saveAppMstUser(dataUser);
            } else if (isSave == 2) {
                dataUser = new AppMstUser();
                int id = Integer.parseInt(params.get("appMstUserId").toString());
                dataUser = dao.findById(id);
                dataUser.setAppMstUserName(params.get("appMstUserName").toString());
                dataUser.setAppMstUserPassword(params.get("appMstUserPassword").toString());
                dataUser.setAppMstUserRoleId(new BigDecimal(params.get("appMstUserRoleId").toString()));
                dataUser.setStatus(new BigDecimal(params.get("status").toString()));
                dataUser.setDeletedStatus(BigDecimal.ZERO);
                dataUser.setDateUpdated(new Date());
                dao.saveOrUpdate(dataUser);
            } else {
                dataUser = new AppMstUser();
                int id = Integer.parseInt(params.get("appMstUserId").toString());
                dataUser = dao.findById(id);
                dataUser.setDeletedStatus(BigDecimal.ONE);
                dataUser.setDateDeleted(new Date());
                dao.saveOrUpdate(dataUser);
            }
            result.put("status", Boolean.TRUE);
        } catch (Exception e) {
            result.put("status", Boolean.FALSE);
        }

        return result;
    }
//
//
//    /*
//	 * Provide the existing AppMstUser for updating.
//     */
//    @RequestMapping(value = {"/edit-{code}-student"}, method = RequestMethod.GET)
//    public String editAppMstUser(@PathVariable String code, ModelMap model) {
//        AppMstUser student = service.findAppMstUserByCode(code);
//        model.addAttribute("student", student);
//        model.addAttribute("edit", true);
//        return "registration";
//    }
//
//    /*
//	 * Handling POST request for validating the user input and updating AppMstUser in database.
//     */
//    @RequestMapping(value = {"/edit-{code}-student"}, method = RequestMethod.POST)
//    public String updateAppMstUser(@Valid AppMstUser student, BindingResult result,
//            ModelMap model, @PathVariable String code) {
//
//        if (result.hasErrors()) {
//            return "registration";
//        }
//
//        if (!service.isAppMstUserCodeUnique(student.getId(), student.getCode())) {
//            FieldError codeError = new FieldError("AppMstUser", "code", messageSource.getMessage("non.unique.code", new String[]{student.getCode()}, Locale.getDefault()));
//            result.addError(codeError);
//            return "registration";
//        }
//
//        service.updateAppMstUser(student);
//
//        model.addAttribute("success", "AppMstUser " + student.getName() + " updated successfully");
//        return "success";
//    }
//
//    /*
//	 * Delete an AppMstUser by it's CODE value.
//     */
//    @RequestMapping(value = {"/delete-{code}-student"}, method = RequestMethod.GET)
//    public String deleteAppMstUser(@PathVariable String code) {
//        service.deleteAppMstUserByCode(code);
//        return "redirect:/list";
//    }
}
