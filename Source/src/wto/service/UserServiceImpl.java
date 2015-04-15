package wto.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import wto.model.Comment;
import wto.model.Image;
import wto.model.User;
import wto.repository.UserRepositoryImpl;

public class UserServiceImpl implements UserService {
	UserRepositoryImpl ur = new UserRepositoryImpl();
	ImageServiceImpl img = new ImageServiceImpl();
	CommentServiceImpl cmt = new CommentServiceImpl();

	@Override
	@Transactional
	public User getUserById(Integer userId) {
		return ur.read(userId);
	}

	@Override
	@Transactional
	public User getUserByName(String username) {
		List<User> usr = ur.readByUsername(username);
		if(usr.size() != 1)
			return null;
		else
			return usr.get(0);
	}

	@Override
	@Transactional
	public User getUserByCredentials(String username, String password) throws UsernameNotFoundException {
		User user = ur.readByCombination(username, password);
		return user;
	}

	@Override
	@Transactional
	public List<User> getUserByQuery(String username) {
		return ur.readByUsername(username);
	}

	@Override
	@Transactional
	public List<Image> getImagesByUserId(Integer userId) {
		return img.getImagesByUserId(userId);
	}

	@Override
	@Transactional
	public List<Comment> getCommentsByUserId(Integer userId) {
		return cmt.getCommentByUser(userId);
	}

}
