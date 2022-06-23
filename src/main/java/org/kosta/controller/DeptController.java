package org.kosta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.model.dto.DeptDTO;
import com.kosta.myapp.model.DeptService;
import com.kosta.myapp.model.TestDAO;

@Controller
public class DeptController {
	Logger logger = LoggerFactory.getLogger(DeptController.class);

	@Autowired //a가 b를 사용하면 a는 b를 의존함. 의존관계를 없애고자한다. spring이 b를 생성해서 injection하도록함. (b가 외부에서 생성되어 injection하도록한다.) 
	DeptService dservice;
	
//	@ExceptionHandler(Exception.class) //모든 예외처리를 원한다면 Exception.class 써주면 됨
//	public String processException(Exception ex) {
//		ex.printStackTrace();
//		logger.info("오류 : "+ex.getMessage());
//		return "error/errorPage500";
//	}
//	
	
	@RequestMapping(value =  "/dept/deptList.do", method = RequestMethod.GET)
	public void deptList(Model model, HttpServletRequest request) {
		//exception test를 위해 추가
//		int a=10/0;
		
		//return 페이지와 요청 페이지가 동일하면 return하지 않아도 됨.
		//DB에서 조회는 추가할 예정임
		logger.info("모두 조회합니다.");
		List<DeptDTO> dlist = dservice.selectAll();
		
		Map<String, ?> flashMap =   
		         RequestContextUtils.getInputFlashMap(request);
		String resultMessage =null;
		if(flashMap !=null ) {
			flashMap.get("message");
			resultMessage= (String)flashMap.get("resultMessage");
		}
		model.addAttribute("resultMessage", resultMessage);
		model.addAttribute("deptLists", dlist);
	}
	@RequestMapping(value =  "/dept/deptDelete.do", method = RequestMethod.GET)
	public String deptDelete(int deptid, RedirectAttributes redirectAttr) {
		
		int result = dservice.deptDelete(deptid);
		logger.info(deptid+"를 "+result+"건 삭제합니다.");
		redirectAttr.addFlashAttribute("resultMessage", result+"건 삭제");
		return "redirect:/dept/deptList.do";
	}
	@RequestMapping(value =  "/dept/deptInsert.do", method = RequestMethod.GET)
	public String deptInsertGet() {
		logger.info("입력페이지를 보여줍니다.");
		return "dept/deptInsert";
	}
	@RequestMapping(value =  "/dept/deptInsert.do", method = RequestMethod.POST)
	public String deptInsertPOST( DeptDTO dept, RedirectAttributes redirectAttr) {
		logger.info("입력 : "+dept.toString());
		int result = dservice.deptInsert(dept);
		redirectAttr.addFlashAttribute("resultMessage", result+"건 입력");
		return "redirect:/dept/deptList.do"; //homecontroller의 주소가 '/'임. 오해말것.  @RequestMapping(value = "/", method = RequestMethod.GET)
	}
	

	@RequestMapping(value =  "/dept/deptUpdate.do", method = RequestMethod.GET)
	public String deptUpdateGet(int deptid, Model model) {
		//상세보기는 부서번호로 정보를 조회한 후 페이지보이기
		logger.info("부서상세보기 입니다. ");
		DeptDTO dept = dservice.selectById(deptid);
		model.addAttribute("dept", dept);
		return "dept/deptDetail";
	}
	
	@RequestMapping(value =  "/dept/deptUpdate.do", method = RequestMethod.POST)
	public String deptUpdatePOST( DeptDTO dept, RedirectAttributes redirectAttr) {//new DeptDTO(); dept.setDepartment_id(request.getParameter("department_id"))
		logger.info("수정 : "+dept.toString());
		int result = dservice.deptUpdate(dept);
		redirectAttr.addFlashAttribute("resultMessage", result+"건 수정");
		return "redirect:/dept/deptList.do"; //homecontroller의 주소가 /임. 오해말것.  @RequestMapping(value = "/", method = RequestMethod.GET)
	}
}
