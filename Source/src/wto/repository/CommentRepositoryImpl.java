package wto.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import wto.model.Comment;
import wto.util.SessionHandler;

public class CommentRepositoryImpl implements CommentRepository {

	SessionHandler sh = new SessionHandler(Comment.class);
	
	@Override
	public Integer create(Comment entity) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		Integer commentID = null;
		try {
			tx = session.beginTransaction();
			commentID = (int)session.save(entity);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commentID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Comment read(Integer primaryKey) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<Comment> comments = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Comment u WHERE u.idcomment = :pk");
			q.setParameter("pk", primaryKey);
			comments = q.list();
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if(comments.size() != 1)
			return null;
		else
			return comments.get(0);
	}

	@Override
	public void update(Comment entity) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Comment comment = (Comment)session.get(Comment.class, entity.getIdcomment());
			comment.setContent(entity.getContent());
			session.update(comment);
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
			Comment comment = (Comment) session.get(Comment.class, primaryKey);
			session.delete(comment);
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
	public List<Comment> readByImageId(Integer imageId) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<Comment> comments = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Comment c WHERE c.idimage = :iid");
			q.setParameter("iid", imageId);
			comments = q.list();
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return comments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> readByUserId(Integer userId) {
		Session session = sh.getSessionFactory().openSession();
		Transaction tx = null;
		List<Comment> comments = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("FROM Comment c WHERE c.iduser = :iid");
			q.setParameter("iid", userId);
			comments = q.list();
			tx.commit();
		} catch(HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return comments;
	}

}
