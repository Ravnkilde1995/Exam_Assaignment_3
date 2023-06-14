package rest;

import entities.Assignment;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AssignmentResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    Assignment a1, a2, a3, a4;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();

        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        a1 = new Assignment("Hansen","h@h.com");
        a2 = new Assignment("Jensen","je@h.com");
        a3 = new Assignment("Petersen","p@h.com");
        a4 = new Assignment("Jakobsen","ja@h.com");
        try {
            em.getTransaction().begin();
            //Delete existing Boats to get a "fresh" database
            em.createNamedQuery("Assignment.deleteAllRows").executeUpdate();
            em.persist(a1);
            em.persist(a2);
            em.persist(a3);
            em.persist(a4);

            //System.out.println("Saved test data to database");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given()
                .when()
                .get("/assignment")
                .then()
                .statusCode(200);
    }

    // Rest assured test that verifies that the endpoint returns the number of rows in the Boat table.
    @Test
    public void testCount() throws Exception {
        given()
                .contentType("application/json")
                .get("/assignment").then()
                .assertThat()
                .statusCode(200).body("size()", org.hamcrest.Matchers.is(1));
    }

    @Test
    public void testDummyMsg() throws Exception {
        given()
                .contentType("application/json")
                .get("/assignment/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Hello assignment"));
    }

    // Rest assured test that verifies that the endpoint returns the correct Assignment.
    @Test
    public void testGetById() throws Exception {
        given()
                .contentType("application/json")
                .get("/assignment/{id}", a1.getId()).then()
                .assertThat()
                .body("famName", equalTo("Hansen"))
                .body("info", equalTo("h@h.com"));

    }
}