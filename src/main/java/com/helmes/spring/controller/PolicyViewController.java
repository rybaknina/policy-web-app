package com.helmes.spring.controller;

import com.helmes.spring.model.Type;
import com.helmes.spring.service.CompanyService;
import com.helmes.spring.service.TypeService;
import com.helmes.spring.util.PaginationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.helmes.spring.model.Policy;
import com.helmes.spring.service.PolicyService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Controller
public class PolicyViewController {
    private static final int FIRST_PAGE_INDEX = 1;
    private static final Logger logger = LoggerFactory.getLogger(PolicyViewController.class);
    private final int MAX_RESULT = 10;
    private final int MAX_NAVIGATION_PAGE = 10;
    @Autowired
    private PolicyService policyService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = {"/","/policy"}, method = RequestMethod.GET)
    public String listReadPolicys(@RequestParam(value = "page", defaultValue = "1" , required = false) String pageStr,Model model) {
        model.addAttribute("policy", new Policy());
        fillPolicies(model, pageStr);
        return "policy";
    }
    @RequestMapping(value = "/policy/find")
    public String findPolicys(@ModelAttribute("policy") Policy p, @RequestParam(value = "page", defaultValue = "1" , required = false) String pageStr,
                              @RequestParam(value = "priceFrom", defaultValue = "0.0", required = false)  String priceFromStr,
                              @RequestParam(value = "priceTo", defaultValue = "0.0", required = false)  String priceToStr,
                              @RequestParam(value = "typef", required = false)  String typeStr,
                              @RequestParam(value = "activef", defaultValue = "false", required = false)  Boolean activeStr,
                              Model model) {
        validFind(pageStr, priceFromStr, priceToStr, typeStr, activeStr, model);
        model.addAttribute("policy", new Policy());
        return "policy";
    }

    @RequestMapping(value ="/viewbuy/{id}")
    public String getPolicy(@PathVariable("id") UUID id, HttpServletRequest request){
        Policy p = policyService.getPolicyById(id);
        request.setAttribute("policy", p);
        return "forward:/buy";
    }


    private void fillPolicies(Model model, String pageStr){
        int page = FIRST_PAGE_INDEX;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
            logger.info("ParseInt(page) had not been successfully, Exception Details="+pageStr);
        }
        PaginationResult<Policy> paginationResult //
                = policyService.listPolicys(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
        model.addAttribute("paginationResult", paginationResult);

        model.addAttribute("listReadPolicys", paginationResult.getList());
        model.addAttribute("typeList", typeService.getAllTypes());
        model.addAttribute("companyList", companyService.getAllCompanies());
    }

    private void validFind(String pageStr, String priceFromStr, String priceToStr, String typeStr, Boolean activeStr, Model model){
        int page = FIRST_PAGE_INDEX;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
            logger.info("ParseInt(page) had not been successfully, Exception Details="+pageStr);
        }
        BigDecimal priceFrom = new BigDecimal(0.0);
        BigDecimal priceTo = new BigDecimal(0.0);
        try {
            priceFrom = BigDecimal.valueOf(Double.parseDouble(priceFromStr));
        } catch (Exception e) {
            logger.info("BigDecimal.valueOf had not been successfully, Exception Details="+priceFromStr);
        }
        try {
            priceTo = BigDecimal.valueOf(Double.parseDouble(priceToStr));
        } catch (Exception e) {
            logger.info("BigDecimal.valueOf had not been successfully, Exception Details=" + priceToStr);
        }
        Type typef=null;
        if (!typeStr.isEmpty())
          typef = typeService.getTypeById(UUID.fromString(typeStr));
        /*Boolean activef = false;
        if (activeStr == "on") activef = true;*/

        Boolean activef = Boolean.valueOf(activeStr);
        List<Policy> policyList = policyService.findPolicys(page, MAX_RESULT, MAX_NAVIGATION_PAGE, priceFrom, priceTo, typef, activef);
        //model.addAttribute("paginationResult", paginationResult);

        model.addAttribute("listReadPolicys", policyList);
        model.addAttribute("typeList", typeService.getAllTypes());
        model.addAttribute("companyList", companyService.getAllCompanies());
    }
//    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
//    public String home(Model model) {
//        return "redirect:/policy";
//    }
}