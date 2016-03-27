package com.ordermgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ordermgmt.model.Borrower;
import com.ordermgmt.service.BorrowerService;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

	@Autowired
	BorrowerService service;

	@RequestMapping(value = { "/", "/search" }, method = RequestMethod.POST)
	public List<Borrower> search(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "maxResults", required = false, defaultValue = "10") int maxResults,
			@RequestBody Borrower borrower) {
		return service.search(pageNo, maxResults, borrower);
	}

	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	public Borrower save(@RequestBody Borrower borrower) {
		System.out.println("Before save  : " + borrower);
		borrower = service.save(borrower);
		System.out.println("After save  : " + borrower);
		return borrower;
	}
}
