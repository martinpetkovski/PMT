package wto.repository;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wto.model.ImageVote;

@Repository
public class ImageVoteRepositoryImpl implements ImageVoteRepository {
	
	@Autowired
	SessionFactory sf;
	
	ImageVoteRepositoryImpl() {}

	public ImageVoteRepositoryImpl(SessionFactory sessionFactory) {
		this.sf = sessionFactory;
	}

	@Override
	@Transactional
	public void create(ImageVote entity) {
		Session session = sf.getCurrentSession();
		ImageVote imageVote = null;
		
		imageVote = (ImageVote) session.createQuery("FROM ImageVote WHERE idimage = :idi AND iduser = :idu").setParameter("idi", entity.getIdimage()).setParameter("idu", entity.getIduser()).uniqueResult();
		if(imageVote == null){
			imageVote = entity;
		}
		else {
			imageVote.setIdimage(entity.getIdimage());
			imageVote.setIduser(entity.getIduser());
			imageVote.setVotetype(entity.isVotetype());
		}
		session.saveOrUpdate(imageVote);
	}

}
