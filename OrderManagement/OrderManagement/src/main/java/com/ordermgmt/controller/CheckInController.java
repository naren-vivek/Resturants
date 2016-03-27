package com.ordermgmt.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ordermgmt.model.BookLoan;
import com.ordermgmt.model.bean.CheckInSearchBean;
import com.ordermgmt.service.CheckInService;

@RestController
@RequestMapping("chkin")
public class CheckInController {

	@Autowired
	CheckInService service;

	@RequestMapping(value = { "/search" }, method = RequestMethod.POST)
	public List<BookLoan> search(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "maxResults", required = false, defaultValue = "10") int maxResults,
			@RequestBody CheckInSearchBean checkInSearchBean) {
		return service.search(pageNo, maxResults, checkInSearchBean);
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public List<BookLoan> search(@RequestBody CheckInSearchBean checkInSearchBean) {
		return service.search(checkInSearchBean);
	}

	@RequestMapping(value = { "/checkin" }, method = RequestMethod.POST)
	public ResponseEntity<String> checkIn(@RequestBody BookLoan bookLoan, HttpServletResponse response) {
		service.checkin(bookLoan);
		return null;
	}

}
