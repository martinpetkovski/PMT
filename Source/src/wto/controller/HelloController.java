package wto.controller;

import java.util.List;
import java.util.Set;

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
import wto.service.ImageServiceImpl;
import wto.service.UserServiceImpl;

@Controller
public class HelloController{
	
	@RequestMapping(value = "/user/{userName}/images", method = RequestMethod.GET)
    public String userImagesPage(@PathVariable("userName") String userName, Model model) {
        UserServiceImpl userService = new UserServiceImpl();
        
        User theUser = userService.getUserByName(userName);
        Set<Comment> comments = theUser.getComments();
        Set<Image> images = theUser.getImages();
        
        model.addAttribute("UserName",theUser.getUsername());
		model.addAttribute("UserPoints", theUser.getPoints());
		model.addAttribute("ImageNumber", images.size());
		model.addAttribute("CommentNumber", comments.size());
		
		model.addAttribute("Images", images);
        return "userImages";
    }
	
	@RequestMapping(value = "/user/{userName}/comments", method = RequestMethod.GET)
    public String userCommentsPage(@PathVariable("userName") String userName, Model model) {
        UserServiceImpl userService = new UserServiceImpl();
        
        User theUser = userService.getUserByName(userName);
        Set<Comment> comments = theUser.getComments();
        Set<Image> images = theUser.getImages();
        
        model.addAttribute("UserName",theUser.getUsername());
		model.addAttribute("UserPoints", theUser.getPoints());
		model.addAttribute("ImageNumber", images.size());
		model.addAttribute("CommentNumber", comments.size());
		
		model.addAttribute("Comments", comments);
        return "userComments";
    }
	
	@RequestMapping(value = "/image/{imageId}", method = RequestMethod.GET)
    public String imagePage(@PathVariable("imageId") Integer imageId, Model model) {
        ImageServiceImpl imageService = new ImageServiceImpl();
        
        Image theImage = imageService.getImageById(imageId);
        
        model.addAttribute("ImageLocation", theImage.getContent());
        model.addAttribute("ImagePoints", theImage.getPoints());
        model.addAttribute("ImageTitle", theImage.getTitle());
        
        model.addAttribute("Comments", theImage.getComments());
        return "image";
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	 public String indexPage(Model model) {
        ImageServiceImpl imageService = new ImageServiceImpl();
        
        List<Image> images = imageService.getAllImages();
        
        model.addAttribute("Images", images);
        
        return "index";
    }
	
	@RequestMapping("/test")
	public String printTest(ModelMap model) {
		UserRepositoryImpl userRepository = new UserRepositoryImpl();
		model.addAttribute("message", "Username: " + userRepository.read(4).getUsername() + "<br>Pass: " + userRepository.read(4).getPassword() + "<br>Email: " + userRepository.read(4).getEmail() + "<br>Points: " + userRepository.read(4).getPoints() + "<br>Create Time: " + userRepository.read(4).getCreateTime());
	
		return "test";
	}

}