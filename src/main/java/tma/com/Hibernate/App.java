package tma.com.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import tma.com.model.Customer;
import tma.com.model.CustomerGroup;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"tma.com"})
@EntityScan("tma.com.model")
@EnableJpaRepositories(basePackages="tma.com.repository")
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    	
    	/*
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitName");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        CustomerGroup customerGroup = new CustomerGroup();
        customerGroup.setName("test");
        entityManager.persist(customerGroup);
        
        Customer cus1 = new Customer();
        cus1.setName("kai");
        cus1.setAddress("abc");
        cus1.setCustomerGroup(customerGroup);
        
        Customer cus2 = new Customer();
        cus2.setName("ton");
        cus2.setAddress("cde");
        cus2.setCustomerGroup(customerGroup);
             
        entityManager.persist(cus1);
        entityManager.persist(cus2);
        
        entityManager.getTransaction().commit();
        System.out.println("--------- after insert -------------");
        entityManager.close();
        entityManagerFactory.close();
        */
    }
}
