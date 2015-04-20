package wto.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wto.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	SessionFactory sf;
	
	public UserRepositoryImpl () {}
	
	public UserRepositoryImpl(SessionFactory sessionFactory) {
		this.sf = sessionFactory;
	}

	@Override
	@Transactional
	public Integer create(User entity) {
		Session session = sf.getCurrentSession();
		Integer userID = null;
		userID = (int)session.save(entity);
			
		return userID;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public User read(Integer primaryKey) {
		Session session = sf.getCurrentSession();
		List<User> users = null;
		Query q = session.createQuery("FROM User u WHERE u.iduser = :pk");
		q.setParameter("pk", primaryKey);
		users = q.list();
			
		if(users.size() != 1)
			return null;
		else
			return users.get(0);
	}

	@Override
	@Transactional
	public void update(User entity) {
		Session session = sf.getCurrentSession();
		User user = (User)session.get(User.class, entity.getIduser());
		user.setUsername(entity.getUsername());
		user.setEmail(entity.getEmail());
		user.setPassword(entity.getPassword());
		session.update(user);	
	}

	@Override
	@Transactional
	public void delete(Integer primaryKey) {
		Session session = sf.getCurrentSession();
		User user = (User) session.get(User.class, primaryKey);
		session.delete(user);		
	}

	@Override
	@Transactional
	public User readByUsername(String username) {
		Session session = sf.getCurrentSession();
		User user = null;
		Query q = session.createQuery("FROM User u WHERE u.username = :un");
		q.setParameter("un", username);
		user = (User) q.list().get(0);
		Hibernate.initialize(user.getUserRoles());
		return user;
	}
	
	@Transactional
	public User readByNameAndFetch(String username) {
		Session session = sf.getCurrentSession();
		User user = null;
		Query q = session.createQuery("FROM User u WHERE u.username = :un");
		q.setParameter("un", username);
		user = (User) q.list().get(0);
		
		Hibernate.initialize(user.getImages());
		Hibernate.initialize(user.getComments());
		
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> readAll() {
		Session session = sf.getCurrentSession();
		List<User> users = null;
		
		Query q = session.createQuery("FROM User");
		users = q.list();
			
		return users;
	}

}
