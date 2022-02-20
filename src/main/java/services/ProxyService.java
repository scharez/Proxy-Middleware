package services;


import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class ProxyService {

    @Path("proxyRequest")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response proxyRequest(@QueryParam("url") String url) {

        if(url.equals("")) {
            return genResponse(Response.Status.BAD_REQUEST, "Enter correct URL!");
        }

        return genResponse(Response.Status.OK, url);
    }

    private Response genResponse(Response.Status status, String msg) {

        CacheControl cc = new CacheControl();
        cc.setNoStore(true);

        return Response.status(status)
                .cacheControl(cc)
                .entity(msg)
                .build();
    }
}