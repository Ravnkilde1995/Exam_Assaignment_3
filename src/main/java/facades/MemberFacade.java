package facades;

import dtos.MemberDTO;
import entities.Dinnerevent;
import entities.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MemberFacade {
    private static MemberFacade instance;
    private static EntityManagerFactory emf;

    public MemberFacade() {
    }

    public static MemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // create method
    public static MemberDTO addMember(MemberDTO md) {
        //add a member
        Member m = new Member(md.getAccount(), md.getPhone(), md.getEmail(), md.getBirthday(), md.getAddress());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MemberDTO(m);
    }
    // Delete function
    public void removeMember(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Member member = em.find(Member.class, id);
        em.remove(member);
        em.getTransaction().commit();
        em.close();
    }
}
