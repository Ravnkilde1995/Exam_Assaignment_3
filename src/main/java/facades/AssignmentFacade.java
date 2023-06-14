package facades;

import dtos.AssignmentDTO;
import entities.Assignment;
import entities.Dinnerevent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AssignmentFacade {

    private static AssignmentFacade instance;
    private static EntityManagerFactory emf;

    public AssignmentFacade() {
    }

    public static AssignmentFacade getAssignmentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AssignmentFacade();
        }
        return instance;
    }

    private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // create method
    public static AssignmentDTO assignToEvent(AssignmentDTO ad) {
        //Assign to event
        Assignment a = new Assignment(ad.getFamName(), ad.getInfo());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new AssignmentDTO(a);
    }

    // Delete function
    public AssignmentDTO removeAssignment(long id) {
        EntityManager em = emf.createEntityManager();
        Assignment assignment = em.find(Assignment.class, id);

        try {
            em.getTransaction().begin();
            em.remove(assignment);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new AssignmentDTO(assignment);
    }
}
