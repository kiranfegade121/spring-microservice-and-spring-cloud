package com.training.temp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.temp.model.Option;

@Controller
public class OptionController {
	
	@ResponseBody
	@RequestMapping("/dataEleRspnsIds")
	public List<Option> getElementIds() {
		
		List<Option> options = Arrays.asList(new Option("1-ABC", "3456"), 
				                                             new Option("2-PQR", "7899"),
				                                             new Option("4567", "2345"),
				                                             new Option("4566", "9933"),
				                                             new Option("45", "5512"),
				                                             new Option("1237", "6789"));
		
		return options;
	}
	
	@ResponseBody
	@GetMapping("/getShortName/{id}")
	public String getShortName(@PathVariable String id) {
		if (id.equals("2345"))
			return "Hello";
		else if(id.equals("9933"))
			return "How r u?";
		else if(id.equals("5512"))
			return "Bye";
		else if(id.equals("6789"))
			return "This is a statement";
		return "--";
	}
}
