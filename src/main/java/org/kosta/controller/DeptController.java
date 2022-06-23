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

	@Autowired //a�� b�� ����ϸ� a�� b�� ������. �������踦 ���ְ����Ѵ�. spring�� b�� �����ؼ� injection�ϵ�����. (b�� �ܺο��� �����Ǿ� injection�ϵ����Ѵ�.) 
	DeptService dservice;
	
//	@ExceptionHandler(Exception.class) //��� ����ó���� ���Ѵٸ� Exception.class ���ָ� ��
//	public String processException(Exception ex) {
//		ex.printStackTrace();
//		logger.info("���� : "+ex.getMessage());
//		return "error/errorPage500";
//	}
//	
	
	@RequestMapping(value =  "/dept/deptList.do", method = RequestMethod.GET)
	public void deptList(Model model, HttpServletRequest request) {
		//exception test�� ���� �߰�
//		int a=10/0;
		
		//return �������� ��û �������� �����ϸ� return���� �ʾƵ� ��.
		//DB���� ��ȸ�� �߰��� ������
		logger.info("��� ��ȸ�մϴ�.");
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
		logger.info(deptid+"�� "+result+"�� �����մϴ�.");
		redirectAttr.addFlashAttribute("resultMessage", result+"�� ����");
		return "redirect:/dept/deptList.do";
	}
	@RequestMapping(value =  "/dept/deptInsert.do", method = RequestMethod.GET)
	public String deptInsertGet() {
		logger.info("�Է��������� �����ݴϴ�.");
		return "dept/deptInsert";
	}
	@RequestMapping(value =  "/dept/deptInsert.do", method = RequestMethod.POST)
	public String deptInsertPOST( DeptDTO dept, RedirectAttributes redirectAttr) {
		logger.info("�Է� : "+dept.toString());
		int result = dservice.deptInsert(dept);
		redirectAttr.addFlashAttribute("resultMessage", result+"�� �Է�");
		return "redirect:/dept/deptList.do"; //homecontroller�� �ּҰ� '/'��. ���ظ���.  @RequestMapping(value = "/", method = RequestMethod.GET)
	}
	

	@RequestMapping(value =  "/dept/deptUpdate.do", method = RequestMethod.GET)
	public String deptUpdateGet(int deptid, Model model) {
		//�󼼺���� �μ���ȣ�� ������ ��ȸ�� �� ���������̱�
		logger.info("�μ��󼼺��� �Դϴ�. ");
		DeptDTO dept = dservice.selectById(deptid);
		model.addAttribute("dept", dept);
		return "dept/deptDetail";
	}
	
	@RequestMapping(value =  "/dept/deptUpdate.do", method = RequestMethod.POST)
	public String deptUpdatePOST( DeptDTO dept, RedirectAttributes redirectAttr) {//new DeptDTO(); dept.setDepartment_id(request.getParameter("department_id"))
		logger.info("���� : "+dept.toString());
		int result = dservice.deptUpdate(dept);
		redirectAttr.addFlashAttribute("resultMessage", result+"�� ����");
		return "redirect:/dept/deptList.do"; //homecontroller�� �ּҰ� /��. ���ظ���.  @RequestMapping(value = "/", method = RequestMethod.GET)
	}
}
