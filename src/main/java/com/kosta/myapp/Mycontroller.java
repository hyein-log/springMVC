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
@RequestMapping("/hello") //class level �ۼ� //�ּҰ����� ���������� ���� �κ��� �־ ó���� �� ���� -> /hello/my1 , /hello/my2
public class Mycontroller {

	@RequestMapping("/my1") //function level �ۼ�
	public String test1(Model model) {
		model.addAttribute("major", "��ǻ�Ͱ���");
		model.addAttribute("phone", "010-1234-5678");
		model.addAttribute("car",new Car("������", 12000, "white"));
		return "myjsp1";
	}
	//value ->String[] org.springframework.web.bind.annotation.RequestMapping.value() : {}
	//�迭�̱⿡ ��ȣ�� �̿��ؼ� ���� �ּ� ������ ������
	@RequestMapping( value = {"/my2", "/my3"} , method = RequestMethod.GET)
	public ModelAndView test2() { //ModelAndView ���� �����Ͱ� model��ü�� view������ ����.
		ModelAndView mv = new ModelAndView();
		mv.addObject("major", "��ǻ�Ͱ���2");
		mv.addObject("phone", "010-1234-1234");
		mv.addObject("car",new Car("������", 12000, "black"));
		mv.setViewName("myjsp1");
		return mv;
	}
	@RequestMapping(value = "/my2", method = RequestMethod.POST)
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("major", "�������");
		mv.addObject("phone", "010-1234-1234");
		mv.addObject("car",new Car("���", 32000, "yellow"));
		mv.setViewName("myjsp1");
		return mv;
	}
	
	@ResponseBody //response.getwriter().append("aaaa")
	@RequestMapping(
			value = "/param.do", 
			params ={"userid=hi", "userpass", "!email"}, //userid�� ������ hi�� �;��ϰ� userpass�� ������ ���� ������� email�� ����ȵ� 
			method = RequestMethod.GET)
	public String test4() {
		System.out.println("��û�� ����");
		return "paramResult";
	}
	
	@ResponseBody //���乮���� body�� �׳� ����������
	@RequestMapping(value = "/param2.do")
	public String test4(String userid, int userpass, String email, Date birthday) { //requset.getParameter�� ������
		System.out.println("��û�� ����");
		System.out.println(userid);
		System.out.println(userpass);
		System.out.println(email);
		System.out.println(birthday);
		return "paramResult2";
	}
	@ResponseBody //���乮���� body�� �׳� ����������
	@RequestMapping(value = "/param3.do")
	public String test5(
			@RequestParam("userid" ) String id,  //���� ����ϴ� ������ �̸��� param�� �̸��� ������ @RequestParam("paramname")�� �Ƚᵵ �� //������ �ٸ��� ����ϰ� ���� ��쿡�� ����������
			//���� �� �ʿ��� param���� �ƴ϶�� required = false �ָ� �� �⺻�� true��
			int userpass, //���� param�� �̸��� ���� �� param�� �̸��� �ٸ��ٸ� ������ �߻������� ������ null���� ����
			String email, 
			Date birthday) { //requset.getParameter�� ������
		System.out.println("param3 ��û�� ����");
		System.out.println(id);
		System.out.println(userpass);
		System.out.println(email);
		System.out.println(birthday);
		return "paramResult2";
	}
	
	@ResponseBody
	@RequestMapping("/param4.do")
	public String test6(Car car , String email, Date birthday) { //��ü�� ������ set�� �ڵ������� ������
		System.out.println(car);
		System.out.println(email);
		System.out.println(birthday);
		return "car����";
	}
	
	@ResponseBody
	@RequestMapping("/param5.do")
	public String test7(@RequestParam Map<String, String> cart) {
		//�Ķ�����̸��� �Ű������̸��� ��ġ���� ���� ��� �������� ����
		for(String key :cart.keySet()) {
			System.out.println(key +"-->"+cart.get(key));
		}
		return "map test";
	}
	
	//JavaBeans ��ü�� view�� ������ ����
	@RequestMapping("/param6.do")
	//view(jsp)���� �Ѱ��ְ� �ʹٸ� @ModelAttribute�����
	//@ModelAttribute�� �ڹٺ󽺱���� �̿��ؼ� view���� ��ü�� ������
	public String test8(@ModelAttribute Car car , String email, Date birthday, Model model) { //��ü�� ������ set�� �ڵ������� ������
		System.out.println(car);
		System.out.println(email);
		System.out.println(birthday);
		model.addAttribute("title", "@ModelAttribute����");
		model.addAttribute("email", email);
		model.addAttribute("birthday", birthday); 
		//�ٸ� ���� @ModelAttribute �� �ȵǱ� ������ model�� �ϳ� ���� addAttribute����� jsp�� �� ������ ������
		return "carInfo"; //jsp�� �Ѱ��ٰ���
	}
}
