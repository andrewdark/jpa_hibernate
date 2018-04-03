package ua.pp.darknsoft.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {
    @ComponentScan("ua.pp.darknsoft.dao"),
    @ComponentScan("ua.pp.darknsoft.service")
})
public class AppConfig {

  @Autowired
  private Environment env;

  @Bean
  public DataSource getDataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(env.getProperty("db.driver"));
    dataSource.setUrl(env.getProperty("db.url"));
    dataSource.setUsername(env.getProperty("db.username"));
    dataSource.setPassword(env.getProperty("db.password"));
    dataSource.setInitialSize(3);
    dataSource.setMaxTotal(5);

    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean emf() {
    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    emf.setDataSource(getDataSource());
    emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    //emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);

    Properties properties = new Properties();
    properties.put("dialect", "org.hibernate.dialect.MySQL5Dialect");
    properties.put("hibernate.max_fetch_depth",3);
    properties.put("hibernate.jdbc.fetch_size",50);
    properties.put("hibernate.jdbc.batch_size",10);
    properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    //properties.put("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));

    emf.setJpaProperties(properties);
    emf.setPackagesToScan("ua.pp.darknsoft");
    return emf;
  }

  @Bean
  public JpaTransactionManager getTransactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(emf().getObject());
    return transactionManager;
  }
}
