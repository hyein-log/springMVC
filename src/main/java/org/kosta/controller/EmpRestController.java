package org.kosta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.model.dto.EMPVO;
import com.kosta.model.dto.JobVO;
import com.kosta.myapp.model.EMPService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/emp/*") // @RequestMapping => @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
public class EmpRestController {

	@Autowired
	EMPService empService;

	Logger logger = LoggerFactory.getLogger(EmpRestController.class);

	@GetMapping(value = "/hello.do", produces = "text/plain;charset=utf-8")
	public String hello() {
		return "hello 하이";
	}

	@GetMapping(value = "/emplist.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EMPVO> emplist() {
		List<EMPVO> emplist = empService.selectAll();
		return emplist;
	}

	@GetMapping(value = "/empdetail.do/{empid}", produces = "application/json")
	public EMPVO empByid(@PathVariable int empid) {
		logger.info("empid" + empid);
		EMPVO emp = empService.selectById(empid);
		// return empid+ "의 직원을 조회합니다. "+message;

		// Jackson이 Java의 객체를 JSON으로 만들어준다.
		return emp;
	}

	@GetMapping(value = "/selectByManager.do/{manager_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EMPVO> selectByManager(@PathVariable int manager_id) {
		List<EMPVO> emplist = empService.selectByManager(manager_id);
		return emplist;
	}

	@GetMapping(value = "/joblist.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<JobVO> selectJobAll() {
		List<JobVO> emplist = empService.selectJobAll();
		return emplist;
	}

	@GetMapping(value = "/mnglist.do", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EMPVO> selectManagerAll() {
		List<EMPVO> emplist = empService.selectManagerAll();
		return emplist;
	}

	@GetMapping(value = "/selectByDept.do/{deptid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EMPVO> selectByDept(@PathVariable int deptid) {
		List<EMPVO> emplist = empService.selectByDept(deptid);
		return emplist;
	}

	@RequestMapping(value = "/selectByJob.do/{job_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EMPVO> selectByJob(@PathVariable String job_id) {
		List<EMPVO> emplist = empService.selectByJob(job_id);
		return emplist;
	}

	@GetMapping(value = "/selectByCondition.do/{deptid}/{job_id}/{sal}/{hire_date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EMPVO> selectByCondition(@PathVariable String job_id, @PathVariable int deptid,
			@PathVariable double sal, @PathVariable String hire_date) {
		List<EMPVO> emplist = empService.selectByCondition(deptid, job_id, sal, hire_date);
		return emplist;
	}

	@GetMapping(value = "/emailDup.do/{empEmail}")
	public String selectByEmailDuplication(@PathVariable String empEmail) {
		int emp = empService.selectByEmailDuplication(empEmail);
		return emp + "건";
	}

	@GetMapping(value = "/selectByEmail.do/{empEmail}", produces = "application/json")
	public EMPVO selectByEmail(@PathVariable String empEmail) {
		EMPVO emp = empService.selectByEmail(empEmail);
		return emp;
	}

	// 입력 => @PostMapping = @RequestMapping + method = RequestMethod.POST
	@PostMapping(value = "/insert.do", consumes = "application/json")
	public String insert(@RequestBody EMPVO emp) {
		int result = empService.empInsert(emp);
		return result + "";
	}

	@PutMapping(value = "/empupdate.do", consumes = "application/json")
	public String update(@RequestBody EMPVO emp) {
		logger.info( "수정할 데이터:" + emp);
		int result = empService.empUpdate(emp);
		return result+"";
	}

	@DeleteMapping(value = "/empdelete.do/{empid}")
	public String delete(@PathVariable int empid) {
		int result = empService.empDelete(empid);
		return result + "";
	}
}
