package wto.service;

import org.springframework.beans.factory.annotation.Autowired;
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
		return ur.readByUsername(username);
	}

	@Override
	public User getUserByNameAndFetch(String username) {
		 return ur.readByNameAndFetch(username);
	}

	@Override
	public User getUserByQuery(String username) {
		return ur.readByUsername(username);
	}
	
	@Override
	public void saveUser(User theUser) {
		ur.create(theUser);
	}
	
	@Override
	public boolean checkUID(String uid) {
		return ur.checkUID(uid);
	}

}
