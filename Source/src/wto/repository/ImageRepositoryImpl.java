package wto.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import wto.model.Image;
import wto.model.Tag;
import wto.model.User;
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

	@Override
	public Image read(Integer primaryKey) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		Image image = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Image as i WHERE i.idimage = :pk");
			q.setParameter("pk", primaryKey);
			image = (Image) q.list().get(0);
			Hibernate.initialize(image.getUser());
			Hibernate.initialize(image.getComments());
			Hibernate.initialize(image.getTags());
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return image;
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
	
	@Override
	public Image readByAddress(String address) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		Image image = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Image i WHERE i.address = :pk");
			q.setParameter("pk", address);
			image = (Image) q.list().get(0);
			Hibernate.initialize(image.getUser());
			Hibernate.initialize(image.getComments());
			Hibernate.initialize(image.getTags());
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return image;
	}
	
	@Override
	public List<String> readNextPrev(Date id, int points, String order) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<String> prevnext = new ArrayList<String>();
		try {
			tx = session.beginTransaction();
			Query q;
			
			if(order.equals("bypoints")) {
				q = session.createQuery("SELECT address FROM Image i WHERE i.points <= :idm AND i.createTime <> :idm2 ORDER BY i.points DESC");
				q.setParameter("idm", points);
				q.setParameter("idm2", id);
			}
			else if(order.equals("byrandom")) {
				q = session.createQuery("SELECT address FROM Image i ORDER BY rand()");
			}
			else {
				q = session.createQuery("SELECT address FROM Image i WHERE i.createTime < :idm ORDER BY i.createTime DESC");
				q.setParameter("idm", id);
			}

			if(q.list().isEmpty())
				prevnext.add("notExist");
			else
				prevnext.add((String)q.list().get(0));
			
			if(order.equals("bypoints")) {
				q = session.createQuery("SELECT address FROM Image i WHERE i.points >= :idm AND i.createTime <> :idm2 ORDER BY i.points");
				q.setParameter("idm", points);
				q.setParameter("idm2", id);
			}
			else if(order.equals("byrandom")) {
				q = session.createQuery("SELECT address FROM Image i ORDER BY rand()");
			}
			else {
				q = session.createQuery("SELECT address FROM Image i WHERE i.createTime > :idm ORDER BY i.createTime");
				q.setParameter("idm", id);
			}
			
			if(q.list().isEmpty())
				prevnext.add("notExist");
			else
				prevnext.add((String)q.list().get(0));
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return prevnext;
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
	public List<Image> readByUsername(String username, String order) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<User> users = null;
		List<Image> images = new ArrayList<Image>();
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM User u WHERE u.username = :uid" + order);
			q.setParameter("uid", username);
			users = q.list();
			for(User user : users) {
				Hibernate.initialize(user.getImages());
				List<Image> temps = user.getImages();
				for(Image temp : temps ) {
					images.add(temp);
				}
			}
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
	public List<Image> readAll(String order) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<Image> images = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Image i " + order);
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
	public List<Image> readByQuery(String query, String order) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<Image> images = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Image i WHERE i.title LIKE :sb " + order);
			q.setParameter("sb", "%" + query + "%");
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
	public List<Image> readByTag(String query, String order) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<Tag> tags = null;
		List<Image> images = new ArrayList<Image>();
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Tag t WHERE t.content = :sb " + order);
			q.setParameter("sb", query);
			tags = q.list();
			for(Tag tag : tags) {
				Hibernate.initialize(tag.getImage());
				images.add(tag.getImage());
			}
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

	@Override
	public String randomImage() {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		String image = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("SELECT address FROM Image i ORDER BY rand()").setMaxResults(1);
			image = (String) q.uniqueResult();
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return image;
	}

}
