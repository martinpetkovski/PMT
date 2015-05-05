package wto.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wto.model.Tag;

@Repository
public class TagRepositoryImpl implements TagRepository {

	@Autowired
	SessionFactory sf;
	
	TagRepositoryImpl() {}

	public TagRepositoryImpl(SessionFactory sessionFactory) {
		this.sf = sessionFactory;
	}
	
	@Override
	@Transactional
	public void create(List<Tag> entities) {
		Session session = sf.getCurrentSession();
		for(Tag entity : entities)
			session.save(entity);
	}

}
