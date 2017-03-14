package br.com.easy_task.app.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/* ***
 * 		Nesta classe, criaremos o método que será gerenciado pelo Spring e criará 
 * 		o EntityManager usado em nosso DAO. Ela também terá as configurações de 
 * 		banco de dados e algumas outras propriedades importantes.
 * */

@EnableTransactionManagement
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource, Properties additionalProperties) {
		
		LocalContainerEntityManagerFactoryBean factoryBean = 
				new LocalContainerEntityManagerFactoryBean();
		
		factoryBean.setPackagesToScan("br.com.easy_task.app.model");
		factoryBean.setDataSource(dataSource);
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		
		factoryBean.setJpaProperties(additionalProperties);
		
		return factoryBean;
	}
	
	@Bean
	@Profile("dev")
	public Properties additionalProperties() {
		
		Properties propsJpa = new Properties();
		propsJpa.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		propsJpa.setProperty("hibernate.show_sql", "true");
		propsJpa.setProperty("hibernate.hbm2ddl.auto", "update");
		
		return propsJpa;
	}
	
	@Bean
	@Profile("dev")
	public DataSource dataSource(){
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("dev123");
		dataSource.setUrl("jdbc:mysql://localhost:3306/easy-task");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		return dataSource;
	}
	
	/*
	 * Note que adicionamos a anotação @EnableTransactionManagement. Assim o Spring ativa o 
	 * gerenciamento de transações e já reconhece o TransactionManager. 
	 * 
	 * */
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
}
