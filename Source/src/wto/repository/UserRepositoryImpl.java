package wto.repository;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import wto.model.User;
import wto.util.SessionHandler;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	SessionHandler sh = new SessionHandler(User.class);
	
	@Override
	public Integer create(User entity) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		Integer userID = null;
		try {
			tx = session.beginTransaction();
			userID = (int)session.save(entity);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User read(Integer primaryKey) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<User> users = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM User u WHERE u.iduser = :pk");
			q.setParameter("pk", primaryKey);
			users = q.list();
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if(users.size() != 1)
			return null;
		else
			return users.get(0);
	}

	@Override
	public void update(User entity) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = (User)session.get(User.class, entity.getIduser());
			user.setUsername(entity.getUsername());
			user.setEmail(entity.getEmail());
			user.setPassword(entity.getPassword());
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Integer primaryKey) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = (User) session.get(User.class, primaryKey);
			session.delete(user);
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> readByUsername(String username) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<User> users = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM User u WHERE u.username LIKE ?");
			q.setString(0, "%"+username+"%");
			users = q.list();
			for(User user : users) {
				Hibernate.initialize(user.getImages());
				Hibernate.initialize(user.getComments());
			}
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}
	
	@SuppressWarnings("unchecked")
	public int readByCombination(String username, String password) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<User> users = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM User u WHERE u.username = :un AND u.password = :pw");
			q.setParameter("un", username);
			q.setParameter("pw", password);
			users = q.list();
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if(users.size() == 1)
			return -1;
		else
			return users.get(0).getIduser();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> readAll() {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<User> users = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM User");
			users = q.list();
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}

}
