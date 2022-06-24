package org.kosta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.model.dto.EMPVO;

@RestController //@Controller + @ResponseBody
@RequestMapping("/emp/*")
public class EmpRestController {

	Logger logger= LoggerFactory.getLogger(EmpRestController.class);
	
	@RequestMapping(value = "/emplist.do", produces = {"text/plain;charset=utf-8"})
	public String emplist() {
		return"��� ������ ��ȸ�մϴ�. ";
	}
	
	@RequestMapping(value = "/empdetail.do/{empid}/{message}", produces =  MediaType.APPLICATION_JSON_VALUE)
	
	public EMPVO empByid(@PathVariable int empid , @PathVariable String message) {
		logger.info("empid" + empid);
		EMPVO emp = new EMPVO();
		emp.setEmployee_id(empid);
		emp.setFirst_name("kimhyein");
		emp.setSalary(5000);
		//return empid+ "�� ������ ��ȸ�մϴ�. "+message;
		
		//Jackson�� Java�� ��ü�� JSON���� ������ش�. 
		return emp;
	}
	
}
