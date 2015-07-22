package de.training.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import de.training.entities.User;

@ManagedBean(name="firstController")
@RequestScoped
public class FirstController {
	
	public String test() {
		Logger logger = Logger.getLogger(FirstController.class.getName());
		logger.warning("FirstController->test aufgerufen :-)");
		
		// Open a database connection:
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("training-jpa");
        EntityManager em = emf.createEntityManager();
 
        // Store 2 User objects in the database:
        em.getTransaction().begin();
        logger.info("EntityManager is open: " + em.isOpen());
        User u = new User("Karl Schmidt", "email@adresse.de", "password");
        em.persist(u);
        u = new User("Stefan Kaiser", "emailadresse@provider.de", "mypassword");
        em.persist(u);
        em.getTransaction().commit();
 
        // Find the number of MyEntity objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(u) FROM User u");
        System.out.println("Total User: " + q1.getSingleResult());
 
        // Retrieve all the MyEntity objects from the database:
        TypedQuery<User> query =
            em.createQuery("SELECT p FROM User p", User.class);
        List<User> results = query.getResultList();
        for (User singleUser : results) {
            System.out.println(singleUser);
        }
 
        // Close the database connection:
        em.close();
        emf.close();
		
		return "index.xhtml";
	}
}
