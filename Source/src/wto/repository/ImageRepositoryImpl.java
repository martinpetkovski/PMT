package wto.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wto.model.Image;
import wto.model.User;

@Repository
public class ImageRepositoryImpl implements ImageRepository {
	
	@Autowired
	SessionFactory sf;
	
	private int IMAGES_PER_PAGE = 12;
	
	public ImageRepositoryImpl() {}
	
	public ImageRepositoryImpl(SessionFactory sessionFactory) {
		this.sf = sessionFactory;
	}

	@Override
	@Transactional
	public int create(Image entity) {
		Session session = sf.getCurrentSession();
		session.save(entity);
		
		return entity.getIdimage();
	}

	@Override
	@Transactional
	public Image read(Integer primaryKey) {
		Session session = sf.getCurrentSession();
		Image image = null;
		Query q = session.createQuery("FROM Image as i WHERE i.idimage = :pk");
		q.setParameter("pk", primaryKey);
		image = (Image) q.list().get(0);
		Hibernate.initialize(image.getUser());
		Hibernate.initialize(image.getComments());
		Hibernate.initialize(image.getTags());
			
		return image;
	}

	@Override
	@Transactional
	public void update(Image entity) {
		Session session = sf.getCurrentSession();
		
		Image image = (Image)session.get(Image.class, entity.getIdimage());
		image.setTitle(entity.getTitle());
		session.update(image);
			
	}

	@Override
	@Transactional
	public void delete(Integer primaryKey) {
		Session session = sf.getCurrentSession();
		Image image = (Image) session.get(Image.class, primaryKey);
		session.delete(image);
	}
	
	@Override
	@Transactional
	public Image readByAddress(String address) {
		Session session = sf.getCurrentSession();
		Image image = null;
	
		Query q = session.createQuery("FROM Image i WHERE i.address = :pk");
		q.setParameter("pk", address);
		image = (Image) q.list().get(0);
		Hibernate.initialize(image.getUser());
		Hibernate.initialize(image.getComments());
		Hibernate.initialize(image.getTags());
			
		return image;
	}
	
	@Override
	@Transactional
	public List<String> readNextPrev(Date id, int points, String order) {
		Session session =sf.getCurrentSession();
		List<String> prevnext = new ArrayList<String>();
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
				
			
		return prevnext;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Image> readByUserId(int userId) {
		Session session = sf.getCurrentSession();
		List<Image> images = null;
		
		Query q = session.createQuery("FROM Image i WHERE i.iduser = :uid");
		q.setParameter("uid", userId);
		images = q.list();
			
		return images;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Image> readByUsername(String username, String order, int page) {
		Session session = sf.getCurrentSession();
		List<User> users = null;
		List<Image> images = new ArrayList<Image>();
		
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
			
		return images;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Image> readByFollowers(int userid, int page) {
		Session session = sf.getCurrentSession();
		List<Image> images = null;
		
		SQLQuery q = session.createSQLQuery("SELECT image.idimage, image.iduser, image.title, image.address, image.content, image.points, image.create_time FROM image, user, follower WHERE image.iduser = user.iduser AND follower.followerid = :uid AND follower.followeeid = image.iduser ORDER BY image.create_time DESC");
		q.addEntity(Image.class);
		q.setParameter("uid", userid);
		q.setFirstResult(page * this.IMAGES_PER_PAGE);
		q.setMaxResults(this.IMAGES_PER_PAGE);
		images = q.list();
		for(Image image : images) {
			Hibernate.initialize(image.getUser());
		}
			
		return images;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Image> readAll(int selectionFlag, String criteria, String order, int page) {
		Session session = sf.getCurrentSession();
		List<Image> images = null;
		SQLQuery q;
		
		/*
			selectionFlag 
				0 = noquery
				1 = user
				2 = comment
				3 = tag
				4 = title
				5 = all
			queryCriteria
				1 = iduser
				2 = iduser // notworking
				3 = query
				4 = query
				5 = query
		 */
		
		if(selectionFlag == 1) {
			q = session.createSQLQuery("SELECT * FROM Image i WHERE i.iduser = :u " + order);
			q.setParameter("u", Integer.parseInt(criteria));
		}
		else if(selectionFlag == 2) {
			q = session.createSQLQuery("SELECT i.idimage, i.iduser, i.title, i.address, i.content, i.points, i.create_time FROM Image i, Comment c WHERE c.idimage = i.idimage AND c.iduser = :u " + order);
			q.setParameter("u", Integer.parseInt(criteria));
		}
		else if(selectionFlag == 3) {
			q = session.createSQLQuery("SELECT i.idimage, i.iduser, i.title, i.address, i.content, i.points, i.create_time FROM Tag t, Image i WHERE t.idimage = i.idimage AND t.content = :u " + order);
			q.setParameter("u", criteria);
		}
		else if(selectionFlag == 4) {
			q = session.createSQLQuery("SELECT * FROM Image i WHERE i.title LIKE :u " + order);
			q.setParameter("u", "%"+criteria+"%");
		}
		else if(selectionFlag == 5) {
			q = session.createSQLQuery("SELECT i.idimage, i.iduser, i.title, i.address, i.content, i.points, i.create_time FROM Tag t, Image i, User u WHERE u.iduser = i.iduser AND t.idimage = i.idimage AND (t.content = :sb OR i.title LIKE :sbp OR u.username LIKE :sbp) " + order);
			q.setParameter("sb", criteria);
			q.setParameter("sbp", "%" + criteria + "%");
		}
		else
			q = session.createSQLQuery("SELECT * FROM Image i " + order);
		
		q.addEntity(Image.class);
		q.setFirstResult(page * this.IMAGES_PER_PAGE);
		q.setMaxResults(this.IMAGES_PER_PAGE);
		images = q.list();
		for(Image image : images) {
			Hibernate.initialize(image.getUser());
		}
			
		return images;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Image> readByQuery(String query, String order, int page) {
		Session session = sf.getCurrentSession();
		List<Image> images = null;
		SQLQuery q = session.createSQLQuery("SELECT * FROM Image i WHERE i.title LIKE :sb " + order);
		q.setParameter("sb", "%" + query + "%");
		q.addEntity(Image.class);
		q.setFirstResult(page * this.IMAGES_PER_PAGE);
		q.setMaxResults(this.IMAGES_PER_PAGE);
		images = q.list();
		for(Image image : images) {
			Hibernate.initialize(image.getUser());
		}
			
		return images;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Image> readByTag(String query, String order, int page) {
		Session session = sf.getCurrentSession();
		List<Image> images = new ArrayList<Image>();
		SQLQuery q = session.createSQLQuery("SELECT i.idimage, i.iduser, i.title, i.address, i.content, i.points, i.create_time FROM Tag t, Image i WHERE t.idimage = i.idimage AND t.content = :sb " + order);
		q.setParameter("sb", query);
		q.addEntity(Image.class);
		q.setFirstResult(page * this.IMAGES_PER_PAGE);
		q.setMaxResults(this.IMAGES_PER_PAGE);
		images = q.list();
		for(Image image : images) {
			Hibernate.initialize(image.getUser());
		}
		
		return images;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Image> readByAll(String query, String order, int page) {
		Session session = sf.getCurrentSession();
		List<Image> images = new ArrayList<Image>();
		SQLQuery q = session.createSQLQuery("SELECT DISTINCT i.idimage, i.iduser, i.title, i.address, i.content, i.points, i.create_time FROM Tag t, Image i, User u WHERE u.iduser = i.iduser AND t.idimage = i.idimage AND (t.content = :sb OR i.title LIKE :sbp OR u.username LIKE :sbp) " + order);
		q.setParameter("sb", query);
		q.setParameter("sbp", "%" + query + "%");
		q.addEntity(Image.class);
		q.setFirstResult(page * this.IMAGES_PER_PAGE);
		q.setMaxResults(this.IMAGES_PER_PAGE);
		images = q.list();
		for(Image image : images) {
			Hibernate.initialize(image.getUser());
		}
		
		return images;
	}

	@Override
	@Transactional
	public String randomImage() {
		Session session = sf.getCurrentSession();
		String image = null;
		
		Query q = session.createQuery("SELECT address FROM Image i ORDER BY rand()").setMaxResults(1);
		image = (String) q.uniqueResult();
			
		return image;
	}

	@Override
	@Transactional
	public int numberOfImages(int selectionFlag, String criteria) {
		Session session = sf.getCurrentSession();		
		SQLQuery q;
		
		if(selectionFlag == 1) {
			q = session.createSQLQuery("SELECT COUNT(*) FROM Image i WHERE i.iduser = :u ");
			q.setParameter("u", Integer.parseInt(criteria));
		}
		else if(selectionFlag == 2) {
			q = session.createSQLQuery("SELECT COUNT(*) FROM Image i, Comment c WHERE c.idimage = i.idimage AND c.iduser = :u ");
			q.setParameter("u", Integer.parseInt(criteria));
		}
		else if(selectionFlag == 3) {
			q = session.createSQLQuery("SELECT COUNT(*) FROM Tag t, Image i WHERE t.idimage = i.idimage AND t.content = :u ");
			q.setParameter("u", criteria);
		}
		else if(selectionFlag == 4) {
			q = session.createSQLQuery("SELECT COUNT(*) FROM Image i WHERE i.title LIKE :u ");
			q.setParameter("u", "%"+criteria+"%");
		}
		else if(selectionFlag == 5) {
			q = session.createSQLQuery("SELECT COUNT(DISTINCT i.idimage) FROM Tag t, Image i, User u WHERE u.iduser = i.iduser AND t.idimage = i.idimage AND (t.content = :sb OR i.title LIKE :sbp OR u.username LIKE :sbp) ");
			q.setParameter("sb", criteria);
			q.setParameter("sbp", "%" + criteria + "%");
		}
		else
			q = session.createSQLQuery("SELECT COUNT(*) FROM Image i ");
		
		return Integer.parseInt(q.uniqueResult().toString());
	}

}
