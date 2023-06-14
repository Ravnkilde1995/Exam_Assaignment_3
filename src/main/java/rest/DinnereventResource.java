package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AssignmentDTO;
import dtos.DinnereventDTO;
import entities.Assignment;
import entities.Dinnerevent;
import facades.AssignmentFacade;
import facades.DinnereventFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("Event")
public class DinnereventResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final DinnereventFacade dinnereventFacade = DinnereventFacade.getDinnereventFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello Event\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allEvents() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<Dinnerevent> query = em.createQuery("select u from Dinnerevent u", entities.Dinnerevent.class);
            List<Dinnerevent> dinnerevents = query.getResultList();
            return "[" + dinnerevents.size() + "]";
        } finally {
            em.close();
        }
    }

    // TO DO: return liste af alle dinner events..
   /* @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("events")
    public List<DinnereventDTO> getAllEvents() {
        EntityManager em = EMF.createEntityManager();
        TypedQuery<Dinnerevent> query = em.createQuery("SELECT d FROM Dinnerevent d", Dinnerevent.class);
        List<Dinnerevent> d = query.getResultList();
        em.close();
        return DinnereventDTO.getDtos(d);
    }*/

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createEvent(String content) {
        DinnereventDTO dd = GSON.fromJson(content, DinnereventDTO.class);
        DinnereventDTO ddto = DinnereventFacade.createEvent(dd);
        return Response.ok(GSON.toJson(new Dinnerevent(ddto.getTime(), ddto.getLocation(), ddto.getDish(), ddto.getPrice()))).build();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response deleteEvent(@PathParam("id") long id, String input) throws Exception {
        //AssignmentDTO adto = GSON.fromJson(input, AssignmentDTO.class);
        dinnereventFacade.removeEvent(id);
        return Response.ok().build();
    }
}
