package facades;

import dtos.DinnereventDTO;
import entities.Dinnerevent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class DinnereventFacade {

    private static DinnereventFacade instance;
    private static EntityManagerFactory emf;

    public DinnereventFacade() {
    }

    public static DinnereventFacade getAssignmentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DinnereventFacade();
        }
        return instance;
    }

    private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // create method
    public static DinnereventDTO createEvent(DinnereventDTO dd) {
        //create an event
        Dinnerevent d = new Dinnerevent(dd.getTime(), dd.getLocation(), dd.getDish(), dd.getPrice());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new DinnereventDTO(d);
    }

    // get all method
    public List<DinnereventDTO> getAllEvents() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Dinnerevent> query = em.createQuery("SELECT d FROM Dinnerevent d", Dinnerevent.class);
        List<Dinnerevent> d = query.getResultList();
        em.close();
        return DinnereventDTO.getDtos(d);
    }

    // update method
    public DinnereventDTO updateEvents(long id, DinnereventDTO ddto) {
        EntityManager em = emf.createEntityManager();
        //find id
        Dinnerevent event = em.find(Dinnerevent.class, id);
        //set new attributes
        event.setTime(ddto.getTime());
        event.setLocation(ddto.getLocation());
        event.setDish(ddto.getDish());
        event.setPrice(ddto.getPrice());
        //update changes
        try {
            em.getTransaction().begin();
            em.merge(event);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new DinnereventDTO(event);

    }

    // Delete function
    public void removeEvent(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Dinnerevent event = em.find(Dinnerevent.class, id);
        em.remove(event);
        em.getTransaction().commit();
        em.close();
    }

}
