package com.kosta.myapp;

import java.sql.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.myapp.vo.Car;

@Controller
@RequestMapping("/hello") //class level 작성 //주소값에서 공통적으로 들어가는 부분을 넣어서 처리할 수 있음 -> /hello/my1 , /hello/my2
public class Mycontroller {

	@RequestMapping("/my1") //function level 작성
	public String test1(Model model) {
		model.addAttribute("major", "컴퓨터공학");
		model.addAttribute("phone", "010-1234-5678");
		model.addAttribute("car",new Car("포르쉐", 12000, "white"));
		return "myjsp1";
	}
	//value ->String[] org.springframework.web.bind.annotation.RequestMapping.value() : {}
	//배열이기에 괄호를 이용해서 여러 주소 매핑이 가능함
	@RequestMapping( value = {"/my2", "/my3"} , method = RequestMethod.GET)
	public ModelAndView test2() { //ModelAndView 리턴 데이터가 model객체와 view정보가 담긴다.
		ModelAndView mv = new ModelAndView();
		mv.addObject("major", "컴퓨터공학2");
		mv.addObject("phone", "010-1234-1234");
		mv.addObject("car",new Car("포르쉐", 12000, "black"));
		mv.setViewName("myjsp1");
		return mv;
	}
	@RequestMapping(value = "/my2", method = RequestMethod.POST)
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("major", "생명공학");
		mv.addObject("phone", "010-1234-1234");
		mv.addObject("car",new Car("페라리", 32000, "yellow"));
		mv.setViewName("myjsp1");
		return mv;
	}
	
	@ResponseBody //response.getwriter().append("aaaa")
	@RequestMapping(
			value = "/param.do", 
			params ={"userid=hi", "userpass", "!email"}, //userid는 무조건 hi가 와야하고 userpass는 무엇이 오든 상관없고 email은 오면안됨 
			method = RequestMethod.GET)
	public String test4() {
		System.out.println("요청을 받음");
		return "paramResult";
	}
	
	@ResponseBody //응답문서에 body가 그냥 찍히도록함
	@RequestMapping(value = "/param2.do")
	public String test4(String userid, int userpass, String email, Date birthday) { //requset.getParameter와 동일함
		System.out.println("요청을 받음");
		System.out.println(userid);
		System.out.println(userpass);
		System.out.println(email);
		System.out.println(birthday);
		return "paramResult2";
	}
	@ResponseBody //응답문서에 body가 그냥 찍히도록함
	@RequestMapping(value = "/param3.do")
	public String test5(
			@RequestParam("userid" ) String id,  //내가 사용하는 변수의 이름과 param의 이름이 같으면 @RequestParam("paramname")을 안써도 됨 //하지만 다르게 사용하고 싶을 경우에는 사용해줘야함
			//만약 꼭 필요한 param값이 아니라면 required = false 주면 됨 기본은 true임
			int userpass, //만약 param의 이름과 내가 쓴 param의 이름이 다르다면 오류가 발생하지는 않지만 null값이 들어옴
			String email, 
			Date birthday) { //requset.getParameter와 동일함
		System.out.println("param3 요청을 받음");
		System.out.println(id);
		System.out.println(userpass);
		System.out.println(email);
		System.out.println(birthday);
		return "paramResult2";
	}
	
	@ResponseBody
	@RequestMapping("/param4.do")
	public String test6(Car car , String email, Date birthday) { //객체로 받으면 set을 자동적으로 다해줌
		System.out.println(car);
		System.out.println(email);
		System.out.println(birthday);
		return "car받음";
	}
	
	@ResponseBody
	@RequestMapping("/param5.do")
	public String test7(@RequestParam Map<String, String> cart) {
		//파라메터이름과 매개변수이름이 일치하지 않은 경우 생략하지 말기
		for(String key :cart.keySet()) {
			System.out.println(key +"-->"+cart.get(key));
		}
		return "map test";
	}
	
	//JavaBeans 객체로 view에 전달이 가능
	@RequestMapping("/param6.do")
	//view(jsp)한테 넘겨주고 싶다면 @ModelAttribute쓰면됨
	//@ModelAttribute는 자바빈스기술을 이용해서 view에게 객체를 전달함
	public String test8(@ModelAttribute Car car , String email, Date birthday, Model model) { //객체로 받으면 set을 자동적으로 다해줌
		System.out.println(car);
		System.out.println(email);
		System.out.println(birthday);
		model.addAttribute("title", "@ModelAttribute연습");
		model.addAttribute("email", email);
		model.addAttribute("birthday", birthday); 
		//다른 것은 @ModelAttribute 가 안되기 때문에 model을 하나 만들어서 addAttribute해줘야 jsp에 값 전달이 가능함
		return "carInfo"; //jsp로 넘겨줄거임
	}
}
