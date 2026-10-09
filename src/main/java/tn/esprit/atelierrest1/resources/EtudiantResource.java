package tn.esprit.atelierrest1.resources;

import tn.esprit.atelierrest1.entities.Etudiant;
import tn.esprit.atelierrest1.entities.EtudiantsXML;
import tn.esprit.atelierrest1.services.EtudiantService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/etudiants")
public class EtudiantResource {

    private final EtudiantService etudiantService = new EtudiantService();

    // GET /rest/etudiants
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEtudiants() {

        List<Etudiant> etudiants = etudiantService.getAllEtudiants();

        return Response.ok(etudiants).build();
    }

    // GET /rest/etudiants/{id}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantById(@PathParam("id") String id) {

        Etudiant etudiant = etudiantService.getEtudiantById(id);

        if (etudiant == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(etudiant).build();
    }

    // POST /rest/etudiants
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEtudiant(Etudiant etudiant) {

        if (etudiant == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        boolean added = etudiantService.addEtudiant(etudiant);

        if (!added) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.ok(etudiant).build();
    }

    // PUT /rest/etudiants/{id}
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(
            @PathParam("id") String id,
            Etudiant etudiant) {

        if (etudiant == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        boolean updated = etudiantService.updateEtudiant(id, etudiant);

        if (!updated) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Etudiant updatedEtudiant = etudiantService.getEtudiantById(id);

        return Response.ok(updatedEtudiant).build();
    }

    // DELETE /rest/etudiants/{id}
    @DELETE
    @Path("/{id}")
    public Response deleteEtudiant(@PathParam("id") String id) {

        boolean deleted = etudiantService.deleteEtudiant(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }

    // GET /rest/etudiants/option?codeOption=1
    @GET
    @Path("/option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsByOption(
            @QueryParam("codeOption") int codeOption) {

        List<Etudiant> etudiants =
                etudiantService.getEtudiantsByOption(codeOption);

        return Response.ok(new EtudiantsXML(etudiants)).build();
    }
}