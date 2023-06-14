package tester;

import entities.Assignment;
import entities.Dinnerevent;
import entities.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        //Tester JPA relationerne og om der bliver persistet rigtig i databasen.

        Assignment a1 = new Assignment("Jensen","info@info");
        Assignment a2 = new Assignment("Hansen","info@info");
        Assignment a3 = new Assignment("Petersen","info@info");

        Member m1 = new Member("test vej 1", 28912050, "r@r.com", "051195", "jensen0205");
        Member m2 = new Member("test vej 2", 28912051, "b@b.com", "031299", "hansen0205");
        Member m3 = new Member("test vej 3", 28912052, "c@c.com", "040590", "petersen0205");

        // tester members og tilmeldte
        a1.addMembers(m1);
        a2.addMembers(m2);
        a3.addMembers(m3);


        //tester på events der har tilmeldte
        Dinnerevent de1 = new Dinnerevent("ONS 14-06 18.00", "Folkehuset Absalon", "Brændende kærlighed", 120);
        Dinnerevent de2 = new Dinnerevent("TORS 15-06 18.00", "Folkehuset Absalon", "Hakkedrenge m bløde løg", 110);

        de1.addAssignments(a1);
        de1.addAssignments(a2);
        de2.addAssignments(a3);



        em.getTransaction().begin();
        em.persist(a1);
        em.persist(a2);
        em.persist(a3);
        em.persist(de1);
        em.persist(de2);
        em.getTransaction().commit();
        em.close();


    }
}
