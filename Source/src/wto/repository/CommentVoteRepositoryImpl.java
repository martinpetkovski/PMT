package wto.repository;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wto.model.CommentVote;

@Repository
public class CommentVoteRepositoryImpl implements CommentVoteRepository {
	@Autowired
	SessionFactory sf;
	
	CommentVoteRepositoryImpl() {}

	public CommentVoteRepositoryImpl(SessionFactory sessionFactory) {
		this.sf = sessionFactory;
	}

	@Override
	@Transactional
	public void create(CommentVote entity) {
		Session session = sf.getCurrentSession();
		CommentVote commentVote = null;
		
		commentVote = (CommentVote) session.createQuery("FROM CommentVote WHERE idcomment = :idi AND iduser = :idu").setParameter("idi", entity.getIdcomment()).setParameter("idu", entity.getIduser()).uniqueResult();
		if(commentVote == null){
			commentVote = entity;
		}
		else {
			commentVote.setIdcomment(entity.getIdcomment());
			commentVote.setIduser(entity.getIduser());
			commentVote.setVotetype(entity.isVotetype());
		}
		session.saveOrUpdate(commentVote);
	}
}
