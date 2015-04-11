package wto.repository;

import java.util.List;

import wto.model.User;

public interface UserRepository {
	public Integer create(User entity);
	public User read(Integer primaryKey);
	public void update(User entity);
	public void delete(Integer primaryKey);
	public List<User> readByUsername(String username);
	public List<User> readAll();
}
