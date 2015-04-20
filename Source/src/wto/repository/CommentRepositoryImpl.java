package wto.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wto.model.Comment;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

	@Autowired
	SessionFactory sf;
	
	public CommentRepositoryImpl(){}
	
	public CommentRepositoryImpl(SessionFactory sessionFactory) {
		this.sf = sessionFactory;
	}

	@Override
	@Transactional
	public Integer create(Comment entity) {
		Session session = sf.getCurrentSession();
		Integer commentID = null;
		commentID = (int)session.save(entity);
		return commentID;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Comment read(Integer primaryKey) {
		Session session = sf.getCurrentSession();
		List<Comment> comments = null;
		Query q = session.createQuery("FROM Comment u WHERE u.idcomment = :pk");
		q.setParameter("pk", primaryKey);
		comments = q.list();
		
		if(comments.size() != 1)
			return null;
		else
			return comments.get(0);
	}

	@Override
	@Transactional
	public void update(Comment entity) {
		Session session = sf.getCurrentSession();
		
		Comment comment = (Comment)session.get(Comment.class, entity.getIdcomment());
		comment.setContent(entity.getContent());
		session.update(comment);
		
	}

	@Override
	@Transactional
	public void delete(Integer primaryKey) {
		Session session = sf.getCurrentSession();
		Comment comment = (Comment) session.get(Comment.class, primaryKey);
		session.delete(comment);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Comment> readByImageId(Integer imageId) {
		Session session = sf.getCurrentSession();
		List<Comment> comments = null;
		Query q = session.createQuery("FROM Comment c WHERE c.idimage = :iid");
		q.setParameter("iid", imageId);
		comments = q.list();
		return comments;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Comment> readByUserId(Integer userId) {
		Session session = sf.getCurrentSession();
		List<Comment> comments = null;
		Query q = session.createQuery("FROM Comment c WHERE c.iduser = :iid");
		q.setParameter("iid", userId);
		comments = q.list();
		
		return comments;
	}

}
