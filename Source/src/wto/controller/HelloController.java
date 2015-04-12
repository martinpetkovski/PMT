package wto.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wto.model.Comment;
import wto.model.Image;
import wto.model.User;
import wto.repository.UserRepositoryImpl;
import wto.service.UserServiceImpl;

@Controller
public class HelloController{
	
	@RequestMapping(value = "/user/{userName}/images", method = RequestMethod.GET)
    public String userImagesPage(@PathVariable("userName") String userName, Model model) {
        UserServiceImpl usi = new UserServiceImpl();
        
        User theUser = usi.getUserByName(userName);
        List<Image> theImages = usi.getImagesByUserId(theUser.getIduser());
        List<Comment> theComments = usi.getCommentsByUserId(theUser.getIduser());
        
        model.addAttribute("UserName",theUser.getUsername());
		model.addAttribute("UserPoints", theUser.getPoints());
		model.addAttribute("ImageNumber", theImages.size());
		model.addAttribute("CommentNumber", theComments.size());
		
		model.addAttribute("Images", theImages);
        return "userImages";
    }
	
	@RequestMapping(value = "/user/{userName}/comments", method = RequestMethod.GET)
    public String userCommentsPage(@PathVariable("userName") String userName, Model model) {
        UserServiceImpl usi = new UserServiceImpl();
        
        User theUser = usi.getUserByName(userName);
        List<Image> theImages = usi.getImagesByUserId(theUser.getIduser());
        List<Comment> theComments = usi.getCommentsByUserId(theUser.getIduser());
        
        model.addAttribute("UserName",theUser.getUsername());
		model.addAttribute("UserPoints", theUser.getPoints());
		model.addAttribute("ImageNumber", theImages.size());
		model.addAttribute("CommentNumber", theComments.size());
		
		model.addAttribute("Comments", theComments);
        return "userComments";
    }
	
	@RequestMapping("/test")
	public String printTest(ModelMap model) {
		UserRepositoryImpl userRepository = new UserRepositoryImpl();
		model.addAttribute("message", "Username: " + userRepository.read(4).getUsername() + "<br>Pass: " + userRepository.read(4).getPassword() + "<br>Email: " + userRepository.read(4).getEmail() + "<br>Points: " + userRepository.read(4).getPoints() + "<br>Create Time: " + userRepository.read(4).getCreateTime());
	
		return "test";
	}

}