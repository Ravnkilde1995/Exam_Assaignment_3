package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AssignmentDTO;
import dtos.MemberDTO;
import entities.Assignment;
import entities.Member;
import facades.AssignmentFacade;
import facades.MemberFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("member")
public class MemberResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final MemberFacade memberFacade = MemberFacade.getMemberFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello Member\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allMembers() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<Member> query = em.createQuery("select u from Member u", entities.Member.class);
            List<Member> members = query.getResultList();
            return "[" + members.size() + "]";
        } finally {
            em.close();
        }
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addMember(String content) {
        MemberDTO md = GSON.fromJson(content, MemberDTO.class);
        MemberDTO mdto = MemberFacade.addMember(md);
        return Response.ok(GSON.toJson(new Member(mdto.getAddress(), mdto.getPhone(), mdto.getEmail(), mdto.getBirthday(), mdto.getAccount()))).build();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response removeMember(@PathParam("id") long id, String input) throws Exception {
        //AssignmentDTO adto = GSON.fromJson(input, AssignmentDTO.class);
        memberFacade.removeMember(id);
        return Response.ok().build();
    }
}