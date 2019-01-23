package com.helmes.spring.controller;

import java.util.Map;
import java.util.UUID;


import com.helmes.spring.model.Policy;
import com.helmes.spring.service.CompanyService;
import com.helmes.spring.service.PolicyService;
import com.helmes.spring.service.TypeService;
import com.helmes.spring.util.PaginationResult;
import com.helmes.spring.validator.PolicyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller

public class PolicyEditController {
	private static final int FIRST_PAGE_INDEX = 1;
	private static final Logger logger = LoggerFactory.getLogger(PolicyEditController.class);
	private final int MAX_RESULT = 10;
	private final int MAX_NAVIGATION_PAGE = 10;
	@Autowired
	private PolicyService policyService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private PolicyValidator policyValidator;
	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = { "/listPolicys" })
	public String listPolicys(@RequestParam(value = "page", defaultValue = "1" , required = false) String pageStr, Map<String, Object> map) {


		int page = FIRST_PAGE_INDEX;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
			logger.info("ParseInt(page) had not been successfully, Exception Details="+pageStr);
		}
		PaginationResult<Policy> paginationResult //
				= policyService.listPolicys(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
		map.put("policy", new Policy());
		map.put("paginationResult", paginationResult);
		map.put("policyList", paginationResult.getList());
		map.put("typeList", typeService.getAllTypes());
		map.put("companyList", companyService.getAllCompanies());

		return "listPolicys";
	}

	@RequestMapping("/get/{id}")
	public String getPolicy(@PathVariable("id") UUID id, Map<String, Object> map) {

		Policy policy = policyService.getPolicyById(id);

		map.put("policy", policy);
		map.put("typeList", typeService.getAllTypes());
		map.put("companyList", companyService.getAllCompanies());
		return "policyForm";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePolicy(@ModelAttribute("policy") @Valid Policy policy,
			BindingResult result, Map<String, Object> map) {
		policyValidator.validate(policy, result);
		if (result.hasErrors()) {
			return "policyForm"; // не знаю, как остаться в попап форме
		}
		policyService.addPolicy(policy);
		return "redirect:/listPolicys";
	}

	@RequestMapping("/delete/{id}")
	public String deletePolicy(@PathVariable("id") UUID id) {

		policyService.removePolicy(id);

		return "redirect://listPolicys";
	}
}
