package wto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import wto.model.User;

@Service
public interface UserService {
	public User getUserById(Integer userId);
	public User getUserByName(String username);
	public User getUserByCredentials(String username, String password);
	public List<User> getUserByQuery(String username);
}
