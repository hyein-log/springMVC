package org.kosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.myapp.model.DeptService;
import com.kosta.myapp.model.EMPService;

@Controller
public class EmpController {

	@Autowired
	EMPService empservice;
	@Autowired
	DeptService deptservice;
	
	@GetMapping("/emp/emptest.do")
	public String emptestView(Model model) {
		model.addAttribute("mlist", empservice.selectManagerAll());
		model.addAttribute("dlist", deptservice.selectAll());
		model.addAttribute("jlist", empservice.selectJobAll());
		return "emp/empForm";
	}
}
