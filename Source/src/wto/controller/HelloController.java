package wto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import wto.repository.UserRepositoryImpl;

@Controller
public class HelloController{
 
	@RequestMapping("/users")
	public String printHello(ModelMap model) {
		model.addAttribute("message", "Hello Spring MVC Framework!");
	
		return "hello";
	}
	
	@RequestMapping("/test")
	public String printTest(ModelMap model) {
		UserRepositoryImpl userRepository = new UserRepositoryImpl();
		model.addAttribute("message", "Username: " + userRepository.read(4).getUsername() + "<br>Pass: " + userRepository.read(4).getPassword() + "<br>Email: " + userRepository.read(4).getEmail() + "<br>Points: " + userRepository.read(4).getPoints() + "<br>Create Time: " + userRepository.read(4).getCreateTime());
	
		return "test";
	}

}