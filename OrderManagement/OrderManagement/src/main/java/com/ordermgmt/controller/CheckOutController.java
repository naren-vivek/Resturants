package com.ordermgmt.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ordermgmt.model.BookCopy;
import com.ordermgmt.model.BookLoan;
import com.ordermgmt.model.bean.CheckOutSearchBean;
import com.ordermgmt.service.CheckOutService;

@RestController
@RequestMapping("/chkout")
public class CheckOutController {

	@Autowired
	CheckOutService service;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/search" }, method = RequestMethod.POST)
	public List<BookCopy> search(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
			@RequestParam(value = "maxResults", required = false, defaultValue = "10") int maxResults,
			@RequestBody CheckOutSearchBean bean) {
		return service.search(pageNo, maxResults, bean);
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
	public List<BookCopy> search(@RequestBody CheckOutSearchBean checkOutSearchBean) {
		return service.search(checkOutSearchBean);
	}

	@RequestMapping(value = { "/checkout" }, method = RequestMethod.POST)
	public ResponseEntity<String> checkOut(@RequestParam(value = "cardNo", required = true) String cardNo,
			@RequestBody BookCopy bookCopy) {
		ResponseEntity<String> responseEntity = null;

		if (!service.isValidBorrower(cardNo)) {
			responseEntity = new ResponseEntity<String>(
					messageSource.getMessage("invalid.borrower.cardno", new String[] { cardNo }, Locale.getDefault()),
					HttpStatus.UNPROCESSABLE_ENTITY);
			return responseEntity;
		}

		if (service.noOfCheckOuts(cardNo) == 3) {
			responseEntity = new ResponseEntity<String>(
					messageSource.getMessage("max.checkout", new String[] { cardNo }, Locale.getDefault()),
					HttpStatus.UNPROCESSABLE_ENTITY);
			return responseEntity;
		}

		BookLoan loan = service.checkOut(bookCopy, cardNo);

		responseEntity = new ResponseEntity<String>(
				messageSource.getMessage("success.checkout",
						new String[] { cardNo, Integer.toString(loan.getLoanId()) }, Locale.getDefault()),
				HttpStatus.OK);
		return responseEntity;
	}
}
