package wto.service;

import org.springframework.stereotype.Service;

import wto.model.User;

@Service
public interface UserService {
	public User getUserById(Integer userId);
	public User getUserByName(String username);
	public User getUserByNameAndFetch(String username);
	public User getUserByQuery(String username);
	public void saveUser(User theUser);
}
