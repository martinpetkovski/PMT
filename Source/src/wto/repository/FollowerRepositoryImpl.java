package wto.repository;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wto.model.Follower;

@Repository
public class FollowerRepositoryImpl implements FollowerRepository {

	@Autowired
	SessionFactory sf;
	
	FollowerRepositoryImpl() {}

	public FollowerRepositoryImpl(SessionFactory sessionFactory) {
		this.sf = sessionFactory;
	}
	
	@Override
	@Transactional
	public boolean isFollowing(int follower, int followee) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("FROM Follower WHERE followerid = :so AND followeeid = :st");
		q.setParameter("so", follower);
		q.setParameter("st", followee);
		if(q.list().size() == 0)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public void create(Follower follower) {
		Session session = sf.getCurrentSession();
		Query q = session.createQuery("FROM Follower WHERE followerid = :so AND followeeid = :st");
		q.setParameter("so", follower.getFollowerid());
		q.setParameter("st", follower.getFolloweeid());
		if(q.list().size() == 0)
			session.save(follower);
		else
			session.delete(q.list().get(0));
	}

}
