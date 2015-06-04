package wto.repository;

import java.util.List;

import wto.model.User;

public interface UserRepository {
	public void create(User entity);
	public User read(Integer primaryKey);
	public void update(User entity);
	public void delete(Integer primaryKey);
	public User readByUsername(String username);
	public User readByNameAndFetch(String username);
	public List<User> readAll();
	public boolean checkUID(String uid);
}
