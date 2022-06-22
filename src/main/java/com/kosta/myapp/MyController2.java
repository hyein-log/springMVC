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
//일반 클래스 방식 -> POJO 방식
	
	Logger logger = LoggerFactory.getLogger(MyController2.class);
	
	@RequestMapping("/spring/test1.do")
	public String method1() {
		
		return "2022-06-22/test1";
	}
	
	//@ModelAttribute("user") --> default이름을 변경할 수 있다.
	//@ModelAttribute 이름이 생략되면 view에서 사용시 UserVO userVO와 같이 사용한다.
	
	@RequestMapping(value = "/spring/test2.do", method = RequestMethod.POST)
	public String method2(Model model, String userid, String userpass,@ModelAttribute("user") UserVO user,
		HttpServletRequest request, HttpSession session) {
		
		String path = request.getRealPath("."); //경로를 알고싶을 떄 사용. 권장하지 않지만 안되지는 않음.
		String path2 = session.getServletContext().getRealPath("."); //경로알고싶을 때 사용. 정석 방법.
		String uri = request.getRequestURI();
		logger.info(path);
		logger.info(path2);
		logger.info(uri);
		session.setAttribute("user", user);
		logger.info(user.toString());
		logger.info(userid);
		logger.info(userpass); //System.out.println()과 비슷함
		model.addAttribute("userid", userid);
		model.addAttribute("userpass", userpass);
		return "2022-06-22/test2";
	}
	
	/*
	 * return 값
	 * ModelAndView, Model, String, void
	 * String : view이름, @ResponseBody와 같이 사용하면 document에 출력할 문자
	 * ModelAndView : data + view이름
	 * Model : 메서드 파라메터로 정의하고 같은 addAttribute()로 설정. view이름 String으로 return
	 * void : 요청이름에서 맨 앞의 슬래시와 확장자를 제외한 나머지 부분을 뷰 이름으로 사용
	 */
	
	//return값이 ModelAndView.
	@RequestMapping("/spring/test3.do")
	public ModelAndView method3(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", "value");
		model.addAttribute("key", "value");
		mv.setViewName("test3");
		return mv;
	}
	
	//return 값이 void.
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

		//default로 요청주소와 같은 이름의 jsp로 forward된다.
		
	}
	//return 값이 String.
	@RequestMapping("/2022-06-22/test4.do")
	public String method5(Model model) {
		model.addAttribute("key", "value5");
		//return "2022-06-22/test4";  --> default로 요청주소와 같은 이름의 jsp로 forward된다.
		return "redirect:/2022-06-22/test3.do"; //주소창이 변경된다. 
	}
	
	@RequestMapping("/2022-06-22/test5.do")
	public String method6(Model model, RedirectAttributes redirectAttr) {
			redirectAttr.addFlashAttribute("subject","SpringFrameWork" );
			redirectAttr.addFlashAttribute("car",new Car("포르쉐", 12000, "white") );
			
		model.addAttribute("key", "value5");
		//return "2022-06-22/test4";  --> default로 요청주소와 같은 이름의 jsp로 forward된다.
		return "redirect:/2022-06-22/test3.do"; //주소창이 변경된다. 
	}
	
}
