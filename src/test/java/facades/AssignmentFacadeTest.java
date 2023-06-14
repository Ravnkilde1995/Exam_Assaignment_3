package facades;

import entities.Assignment;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class AssignmentFacadeTest {

    private static EntityManagerFactory emf;
    private static AssignmentFacade facade;

    public AssignmentFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = AssignmentFacade.getAssignmentFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Assignment.deleteAllRows").executeUpdate();
            em.persist(new Assignment("Jensen","info@info"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }


    // TODO: Delete or change this method
    @Test
    public void testAFacadeMethod() throws Exception {
        assertEquals(1, facade.getAssignmentCount());
    }

    @Test
    public void create() throws Exception {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Assignment.deleteAllRows").executeUpdate();
            Assignment a1 = new Assignment("Hansen","info@info");
            Assignment a2 = new Assignment("Petersen","info@info");
            em.persist(a1);
            em.persist(a2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void update() throws Exception {

        /*BoatDTO bdto = new BoatDTO();
        bdto =

        assertEquals(1, facade.updateBoat(1, bdto));
        */
    }


}
