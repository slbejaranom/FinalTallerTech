package com.tallertech.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tallertech.app.entity.Status;
import com.tallertech.app.service.MiscellaneousService;

@RestController
@RequestMapping("/api/miscellaneous")
@CrossOrigin(origins="*")
public class MiscellaneousController {

	@Autowired
	MiscellaneousService miscellaneousService;
	
	@GetMapping
	public Status getProperty() {
		return miscellaneousService.getStatistics();
	}
}
