package wto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import wto.model.User;
import wto.repository.UserRepository;

@Service("UserService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository ur;

	public UserServiceImpl() {}
	
	public UserServiceImpl(UserRepository userRepository) {
		this.ur = userRepository;
	}

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
	public User getUserByCredentials(String username, String password) throws UsernameNotFoundException {
		User user = ur.readByCombination(username, password);
		return user;
	}

	@Override
	public List<User> getUserByQuery(String username) {
		return ur.readByUsername(username);
	}

}
