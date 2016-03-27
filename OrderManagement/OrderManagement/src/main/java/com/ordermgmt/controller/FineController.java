package com.ordermgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ordermgmt.model.Borrower;
import com.ordermgmt.service.FineService;

@RestController
@RequestMapping("/fine")
public class FineController {

	@Autowired
	FineService service;

	@RequestMapping(value = { "/search" }, method = RequestMethod.POST)
	public List<Borrower> search(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "maxResults", required = false, defaultValue = "10") int maxResults) {
		return service.search(pageNo, maxResults);
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public List<Borrower> search() {
		return service.search();
	}

	@RequestMapping(value = { "/refresh" }, method = RequestMethod.GET)
	public void refresh() {
		service.updateFines();
	}

	@RequestMapping(value = { "/settle" }, method = RequestMethod.GET)
	public void settle(@RequestParam(value = "cardNo", required = true) String cardNo) {
		service.settleFines(cardNo);
	}
}
