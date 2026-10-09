package tn.esprit.atelierrest1.resources;

import tn.esprit.atelierrest1.entities.Option;
import tn.esprit.atelierrest1.services.OptionService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/options")
public class OptionResource {

    private final OptionService optionService = new OptionService();

    // GET /rest/options
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOptions(
            @QueryParam("domaine") String domaine) {

        List<Option> options;

        if (domaine == null || domaine.trim().isEmpty()) {
            options = optionService.getAllOptions();
        } else {
            options = optionService.getOptionsByDomaine(domaine);
        }

        return Response.ok(options).build();
    }

    // GET /rest/options/{id}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptionById(@PathParam("id") int id) {

        Option option = optionService.getOptionById(id);

        if (option == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(option).build();
    }

    // POST /rest/options
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOption(Option option) {

        if (option == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        boolean added = optionService.addOption(option);

        if (!added) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.ok(option).build();
    }

    // PUT /rest/options/{id}
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOption(
            @PathParam("id") int id,
            Option option) {

        if (option == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Option updatedOption = optionService.updateOption(id, option);

        if (updatedOption == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updatedOption).build();
    }

    // DELETE /rest/options/{id}
    @DELETE
    @Path("/{id}")
    public Response deleteOption(@PathParam("id") int id) {

        boolean deleted = optionService.deleteOption(id);

        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }
}