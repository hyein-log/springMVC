package com.kosta.myapp;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.myapp.vo.Car;
import com.kosta.myapp.vo.UserVO;

@Controller
public class MyController2 {
//�Ϲ� Ŭ���� ��� -> POJO ���
	
	Logger logger = LoggerFactory.getLogger(MyController2.class);
	
	@RequestMapping("/spring/test1.do")
	public String method1() {
		
		return "2022-06-22/test1";
	}
	
	//@ModelAttribute("user") --> default�̸��� ������ �� �ִ�.
	//@ModelAttribute �̸��� �����Ǹ� view���� ���� UserVO userVO�� ���� ����Ѵ�.
	
	@RequestMapping(value = "/spring/test2.do", method = RequestMethod.POST)
	public String method2(Model model, String userid, String userpass,@ModelAttribute("user") UserVO user,
		HttpServletRequest request, HttpSession session) {
		
		String path = request.getRealPath("."); //��θ� �˰���� �� ���. �������� ������ �ȵ����� ����.
		String path2 = session.getServletContext().getRealPath("."); //��ξ˰���� �� ���. ���� ���.
		String uri = request.getRequestURI();
		logger.info(path);
		logger.info(path2);
		logger.info(uri);
		session.setAttribute("user", user);
		logger.info(user.toString());
		logger.info(userid);
		logger.info(userpass); //System.out.println()�� �����
		model.addAttribute("userid", userid);
		model.addAttribute("userpass", userpass);
		return "2022-06-22/test2";
	}
	
	/*
	 * return ��
	 * ModelAndView, Model, String, void
	 * String : view�̸�, @ResponseBody�� ���� ����ϸ� document�� ����� ����
	 * ModelAndView : data + view�̸�
	 * Model : �޼��� �Ķ���ͷ� �����ϰ� ���� addAttribute()�� ����. view�̸� String���� return
	 * void : ��û�̸����� �� ���� �����ÿ� Ȯ���ڸ� ������ ������ �κ��� �� �̸����� ���
	 */
	
	//return���� ModelAndView.
	@RequestMapping("/spring/test3.do")
	public ModelAndView method3(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", "value");
		model.addAttribute("key", "value");
		mv.setViewName("test3");
		return mv;
	}
	
	//return ���� void.
	@RequestMapping("/2022-06-22/test3.do")
	public void method4(Model model, HttpServletRequest request) {
		model.addAttribute("key", "value");
		Map<String, ?> flashMap =   
		         RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null ) {
			flashMap.get("message");
			String subject = (String)flashMap.get("subject");
			Car mycar = (Car)flashMap.get("car");
			System.out.println(subject);
			System.out.println(mycar);
			model.addAttribute("subjectkey", subject);
			model.addAttribute("car", mycar);
			}

		//default�� ��û�ּҿ� ���� �̸��� jsp�� forward�ȴ�.
		
	}
	//return ���� String.
	@RequestMapping("/2022-06-22/test4.do")
	public String method5(Model model) {
		model.addAttribute("key", "value5");
		//return "2022-06-22/test4";  --> default�� ��û�ּҿ� ���� �̸��� jsp�� forward�ȴ�.
		return "redirect:/2022-06-22/test3.do"; //�ּ�â�� ����ȴ�. 
	}
	
	@RequestMapping("/2022-06-22/test5.do")
	public String method6(Model model, RedirectAttributes redirectAttr) {
			redirectAttr.addFlashAttribute("subject","SpringFrameWork" );
			redirectAttr.addFlashAttribute("car",new Car("������", 12000, "white") );
			
		model.addAttribute("key", "value5");
		//return "2022-06-22/test4";  --> default�� ��û�ּҿ� ���� �̸��� jsp�� forward�ȴ�.
		return "redirect:/2022-06-22/test3.do"; //�ּ�â�� ����ȴ�. 
	}
	
}
