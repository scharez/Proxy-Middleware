import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import utils.ConsoleColor;
import utils.Constants;

import java.io.IOException;
import java.net.URI;

public class Main {

    private static org.glassfish.grizzly.http.server.HttpServer startServer() {

        final ResourceConfig rc = new ResourceConfig().packages("services", "filter").register(MultiPartFeature.class);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(Constants.BASE_URI), rc);
    }

    public static void main(String[] args) {

        final org.glassfish.grizzly.http.server.HttpServer server = startServer();
        server.getServerConfiguration().addHttpHandler(new StaticHttpHandler("public"), "/");

        System.out.println(ConsoleColor.green() + "ProxyMiddleware-Server started!" + ConsoleColor.reset());
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.shutdown();
    }
}