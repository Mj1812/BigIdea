package restapi;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import restapi.services.AuthenticationService;

public class RestServer {
    //region Constructor
    public static void main(String[] args) {
        ResourceConfig config = new ResourceConfig();
        config.packages("restapi.services");
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(config));

        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(8090);
        ServletContextHandler contextHandler = new ServletContextHandler(server, "/*");
        contextHandler.addServlet(servletHolder, "/*");

        try {
            System.out.println("Initializing Jetty RestServer...");
            server.start();
            System.out.println("Jetty server initialized");
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    //endregion
}
