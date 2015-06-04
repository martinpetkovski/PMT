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
	public void create(User entity) {
		Session session = sf.getCurrentSession();
		session.save(entity);
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

		Hibernate.initialize(user.getComments());
		Hibernate.initialize(user.getImages());
		
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
	
	@Override
	@Transactional
	public boolean checkUID(String uid) {
		Session session = sf.getCurrentSession();
			
		if(session.createSQLQuery("SELECT * FROM User WHERE uid = :uid AND enabled = 0").setParameter("uid", uid).uniqueResult() != null) {
			session.createSQLQuery("UPDATE user SET enabled = 1 WHERE uid = :uid").setParameter("uid", uid).executeUpdate();
			session.createSQLQuery("INSERT INTO user_roles VALUES(NULL, (SELECT u.iduser FROM User u WHERE uid = :uid), 'ROLE_USER')").setParameter("uid", uid).executeUpdate();
			return true;
		}
		else
			return false;
		
	}

}
