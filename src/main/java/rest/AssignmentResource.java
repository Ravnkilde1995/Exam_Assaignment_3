package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AssignmentDTO;
import entities.Assignment;
import facades.AssignmentFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("assignment")
public class AssignmentResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final AssignmentFacade assignmentFacade = AssignmentFacade.getAssignmentFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello assignment\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allAssignments() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<Assignment> query = em.createQuery("select u from Assignment u", entities.Assignment.class);
            List<Assignment> assignments = query.getResultList();
            return "[" + assignments.size() + "]";
        } finally {
            em.close();
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response AssignToEvent(String content) {
        AssignmentDTO ad = GSON.fromJson(content, AssignmentDTO.class);
        AssignmentDTO adto = AssignmentFacade.assignToEvent(ad);
        return Response.ok(GSON.toJson(new Assignment(adto.getFamName(), adto.getInfo()))).build();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response deleteAssignment(@PathParam("id") long id, String input) throws Exception {
        //AssignmentDTO adto = GSON.fromJson(input, AssignmentDTO.class);
        assignmentFacade.removeAssignment(id);
        return Response.ok().build();
    }
}
