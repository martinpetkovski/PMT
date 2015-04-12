package wto.service;

import java.util.List;

import wto.model.Comment;
import wto.model.Image;
import wto.model.User;
import wto.repository.UserRepositoryImpl;

public class UserServiceImpl implements UserService {
	UserRepositoryImpl ur = new UserRepositoryImpl();
	ImageServiceImpl img = new ImageServiceImpl();
	CommentServiceImpl cmt = new CommentServiceImpl();

	@Override
	public User getUserById(Integer userId) {
		return ur.read(userId);
	}

	@Override
	public User getUserByName(String username) {
		List<User> usr = ur.readByUsername(username);
		if(usr.size() != 1)
			return null;
		else
			return usr.get(0);
	}

	@Override
	public User getUserByCredentials(String username, String password) {
		int id = ur.readByCombination(username, password);
		if(id != -1)
			return ur.read(id);
		else
			return null;
	}

	@Override
	public List<User> getUserByQuery(String username) {
		return ur.readByUsername(username);
	}

	@Override
	public List<Image> getImagesByUserId(Integer userId) {
		return img.getImagesByUserId(userId);
	}

	@Override
	public List<Comment> getCommentsByUserId(Integer userId) {
		return cmt.getCommentByUser(userId);
	}

}
