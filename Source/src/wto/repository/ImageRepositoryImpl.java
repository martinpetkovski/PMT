package wto.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import wto.model.Image;
import wto.util.SessionHandler;

public class ImageRepositoryImpl implements ImageRepository {
	
	SessionHandler sh = new SessionHandler(Image.class);
	@Override
	public Integer create(Image entity) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		Integer imageID = null;
		try {
			tx = session.beginTransaction();
			imageID = (int)session.save(entity);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return imageID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Image read(Integer primaryKey) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<Image> images = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Image i WHERE i.idimage = :pk");
			q.setParameter("pk", primaryKey);
			images = q.list();
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if(images.size() != 1)
			return null;
		else
			return images.get(0);
	}

	@Override
	public void update(Image entity) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Image image = (Image)session.get(Image.class, entity.getIdimage());
			image.setTitle(entity.getTitle());
			session.update(image);
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
			Image image = (Image) session.get(Image.class, primaryKey);
			session.delete(image);
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
	public List<Image> readByUserId(int userId) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<Image> images = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Image i WHERE i.iduser = :uid");
			q.setParameter("uid", userId);
			images = q.list();
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return images;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> readAll() {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<Image> images = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Image");
			images = q.list();
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return images;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> readByQuery(String query) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<Image> images = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Image i WHERE i.title = :s");
			q.setParameter("s", query);
			images = q.list();
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return images;
	}

}
