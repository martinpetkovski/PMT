package wto.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import wto.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	private SessionFactory sessionFactory;
	private ServiceRegistry serviceRegistry;
	
	private void createSessionFactory() {
		Configuration config = new Configuration().addPackage("wto.model").addAnnotatedClass(User.class);
		config.configure();
		try {
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
			} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory " + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}
	
	public UserRepositoryImpl() {
		this.createSessionFactory();
	}
	
	@Override
	public Integer create(User entity) {
		Session session = sessionFactory.openSession();
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
		Session session = sessionFactory.openSession();
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
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = (User)session.get(User.class, entity.getIduser());
			user.setUsername(entity.getUsername());
			user.setEmail(entity.getEmail());
			user.setPassword(entity.getPassword());
			user.setPoints(entity.getPoints());
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
		Session session = sessionFactory.openSession();
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
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<User> users = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM User u WHERE u.username LIKE ?");
			q.setString(0, "%"+username+"%");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> readAll() {
		Session session = sessionFactory.openSession();
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
