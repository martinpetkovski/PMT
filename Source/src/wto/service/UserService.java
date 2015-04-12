package wto.service;

import java.util.List;

import wto.model.Comment;
import wto.model.Image;
import wto.model.User;

public interface UserService {
	public User getUserById(Integer userId);
	public User getUserByName(String username);
	public User getUserByCredentials(String username, String password);
	public List<User> getUserByQuery(String username);
	public List<Image> getImagesByUserId(Integer userId);
	public List<Comment> getCommentsByUserId(Integer userId);
}
