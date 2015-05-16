package wto.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wto.model.Image;
import wto.model.Tag;
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
	public List<Image> readByUsername(String username, String order) {
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
	public List<Image> readAll(String order, int page) {
		Session session = sf.getCurrentSession();
		List<Image> images = null;
		
		Query q = session.createQuery("FROM Image i " + order);
		q.setFirstResult(page * this.IMAGES_PER_PAGE);
		q.setMaxResults(this.IMAGES_PER_PAGE);
		images = q.list();
			
		return images;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Image> readByQuery(String query, String order) {
		Session session = sf.getCurrentSession();
		List<Image> images = null;
		Query q = session.createQuery("FROM Image i WHERE i.title LIKE :sb " + order);
		q.setParameter("sb", "%" + query + "%");
		images = q.list();
			
		return images;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Image> readByTag(String query, String order) {
		Session session = sf.getCurrentSession();
		List<Tag> tags = null;
		List<Image> images = new ArrayList<Image>();
		Query q = session.createQuery("FROM Tag t WHERE t.content = :sb " + order);
		q.setParameter("sb", query);
		tags = q.list();
		for(Tag tag : tags) {
			Hibernate.initialize(tag.getImage());
			images.add(tag.getImage());
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
	public int numberOfImages() {
		Session session = sf.getCurrentSession();		
		Query q = session.createQuery("SELECT COUNT(*) FROM Image i");
		return Integer.parseInt(q.uniqueResult().toString());
	}

}
