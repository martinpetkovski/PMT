package wto.util;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan("wto")
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter{
 
    @Bean
    public SessionFactory sessionFactory() {
            LocalSessionFactoryBuilder builder = 
		new LocalSessionFactoryBuilder(dataSource());
            builder.scanPackages("wto")
                      .addProperties(getHibernateProperties());
 
                return builder.buildSessionFactory();
    }
 
	private Properties getHibernateProperties() {
            Properties prop = new Properties();
            prop.put("hibernate.dialect", 
                "org.hibernate.dialect.MySQLDialect");
            return prop;
    }
 
	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {
 
		BasicDataSource ds = new BasicDataSource();
	        ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/wto");
		ds.setUsername("root");
		ds.setPassword("space2001");
		return ds;
	}
 
	@Bean
    public HibernateTransactionManager txManager() {
            return new HibernateTransactionManager(sessionFactory());
    }
 
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver 
                             = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	    registry.addResourceHandler("/i/**").addResourceLocations("/i/");
	}
	
	@Bean(name="filterMultipartResolver")
	public CommonsMultipartResolver filterMultipartResolver() {
	    CommonsMultipartResolver filterMultipartResolver = new CommonsMultipartResolver();
	    filterMultipartResolver.setMaxUploadSize(1024*1024);
	    return filterMultipartResolver;
	}
}