package wto.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionHandler {
	private SessionFactory sessionFactory;
	private ServiceRegistry serviceRegistry;
	
	@SuppressWarnings("rawtypes")
	private void createSessionFactory(Class cls) {
		
		Configuration config = new Configuration().addPackage("wto.model").addAnnotatedClass(cls);
		config.configure();
		try {
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory " + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}
	
	@SuppressWarnings("rawtypes")
	public SessionHandler(Class cls) {
		this.createSessionFactory(cls);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}	
}
