package com.helmes.spring.controller;

import com.helmes.spring.service.TypeService;
import com.helmes.spring.util.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.helmes.spring.model.Policy;
import com.helmes.spring.service.PolicyService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Controller
public class PolicyController {
    private final int MAX_RESULT = 5;
    private final int MAX_NAVIGATION_PAGE = 10;
    @Autowired
    private PolicyService policyService;

    @Autowired
    private TypeService typeService;


    @RequestMapping(value = "/policys", method = RequestMethod.GET)
    public String listPolicys(@RequestParam(value = "page", defaultValue = "1" , required = false) String pageStr,Model model) {
        model.addAttribute("policy", new Policy());
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        PaginationResult<Policy> paginationResult //
                = policyService.listPolicys(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
        model.addAttribute("paginationResult", paginationResult);
        fillPolicies(model, paginationResult.getList());
        //    List<Policy> list = this.policyService.listPolicys(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
        return "policy";
    }
    @RequestMapping(value = "/policy/find")
    public String findPolicys(@ModelAttribute("policy") Policy p, @RequestParam(value = "pricef", defaultValue = "0.0", required = false)  String priceStr, Model model) {
        BigDecimal pricef = new BigDecimal(0.0);
        try {
            pricef = BigDecimal.valueOf(Double.parseDouble(priceStr));
        } catch (Exception e) {
        }
        List<Policy> list = policyService.findPolicys(pricef, p.getTypef(), p.getActivef());
        model.addAttribute("policy", new Policy());

        fillPolicies(model, list);
        return "policy";
    }
    //For add and update policy both
    @RequestMapping(value= "/policy/add", method = RequestMethod.POST)
    public String addPolicy(@ModelAttribute("policy") @Valid Policy p, BindingResult result,@RequestParam(value = "page", defaultValue = "1", required = false)  String pageStr, Model model){
        if (result.hasErrors()) {
            int page = 1;
            try {
                page = Integer.parseInt(pageStr);
            } catch (Exception e) {
            }
            PaginationResult<Policy> paginationResult //
                    = policyService.listPolicys(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
            model.addAttribute("paginationResult", paginationResult);
            fillPolicies(model, paginationResult.getList());
            return "policy";
        }
        if(p.getId() == 0){
            //new policy, add it
            policyService.addPolicy(p);
        }else{
            //existing policy, call update
            policyService.updatePolicy(p);
        }
        return "redirect:/policys";
    }

    @RequestMapping("/remove/{id}")
    public String removePolicy(@PathVariable("id") int id){

        policyService.removePolicy(id);
        return "redirect:/policys";
    }

    @RequestMapping("/edit/{id}")
    public String editPolicy(@PathVariable("id") int id,@RequestParam(value = "page", defaultValue = "1", required = false)  String pageStr, Model model){
        Policy p = policyService.getPolicyById(id);
        model.addAttribute("policy", p);
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
        }
        PaginationResult<Policy> paginationResult //
                = policyService.listPolicys(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
        model.addAttribute("paginationResult", paginationResult);
        fillPolicies(model, paginationResult.getList());
        return "policy";
    }

    private void fillPolicies(Model model, List<Policy> list){
   //     List<Policy> list = this.policyService.listPolicys();
    //    List<Policy> list = this.policyService.findPolicys(p.getPricef(), p.getTypef(), p.getActivef());
   /*     int startpage = (int) (page - 5 > 0?page - 5:1);
        int endpage = startpage + 10;
        model.addAttribute("startpage",startpage);
        model.addAttribute("endpage",endpage);*/
        Map<String, String> mapTypeNames = typeService.getMapTypes();
        for(Policy policy: list){
            String type = policy.getType();
            if (!type.trim().isEmpty()){
                policy.setTypename(mapTypeNames.get(type));
                model.addAttribute("typename", policy.getTypename());}
            else model.addAttribute("typename","");
        }

        model.addAttribute("listPolicys", list);
        model.addAttribute("typeList", typeService.getAllTypes());
    }

}