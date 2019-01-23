package com.helmes.spring.controller;

import com.helmes.spring.model.Buy;
import com.helmes.spring.model.Policy;
import com.helmes.spring.model.User;
import com.helmes.spring.service.*;
import com.helmes.spring.util.PaginationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Controller
public class BuyController {
    private static final int FIRST_PAGE_INDEX = 1;
    private final int MAX_RESULT = 10;
    private final int MAX_NAVIGATION_PAGE = 10;
    private static final Logger logger = LoggerFactory.getLogger(BuyController.class);
    @Autowired
    BuyService buyService;
    @Autowired
    private PolicyService policyService;
    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = {"/buy/{id}/{username}"}, method = RequestMethod.GET)
    public String getBuy(@PathVariable("id") UUID id, @PathVariable("username") String username, Model model){
        Buy buy = new Buy();
        Policy policy = policyService.getPolicyById(id);
        buy.setAmount(policy.getPrice().add(new BigDecimal(15.0)));
        User user = null;
        if (!username.equals("empty")){
           user = userService.findByUsername(username);
        }
        model.addAttribute("buy", buy);
        model.addAttribute("policy", policy);
        model.addAttribute("user", user);
        return "buy";
    }
    @RequestMapping(value = "/buysave/{policy.id}/{user.username}", method = RequestMethod.POST)
    public String saveBuy(@ModelAttribute("buy") Buy buy, @PathVariable("policy.id") UUID id_policy, @PathVariable("user.username") String username,  Model model){
        Policy policy = policyService.getPolicyById(id_policy);
        buy.setPolicy(policy);
        buy.setAmount(buy.getPolicy().getPrice().add(new BigDecimal(15.0)));
        User user = null;
        if (!username.equals("empty")){
            user = userService.findByUsername(username);

        }
        buy.setUser(user);
        buyService.saveBuy(buy);
        return "redirect:/";
    }


    @RequestMapping(value = { "/listBuy" })
    public String listBuy(@RequestParam(value = "page", defaultValue = "1" , required = false) String pageStr, Map<String, Object> map) {


        int page = FIRST_PAGE_INDEX;
        try {
            page = Integer.parseInt(pageStr);
        } catch (Exception e) {
            logger.info("ParseInt(page) had not been successfully, Exception Details="+pageStr);
        }
        PaginationResult<Buy> paginationResult //
                = buyService.listBuy(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
        map.put("buy", new Buy());
        map.put("paginationResult", paginationResult);
        map.put("buyList", paginationResult.getList());
        map.put("typeList", typeService.getAllTypes());
        map.put("companyList", companyService.getAllCompanies());

        return "listBuy";
    }
}
