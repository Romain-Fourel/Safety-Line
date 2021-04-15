package com.safetyline.apptest;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.safetyline.apptest.dao.DAO;
import com.safetyline.apptest.dao.User;


/**
 * The ajax call from angular doesn't works ! Thus, the saving profile feature is not available.
 * However, all DAOs are tested thanks to POSTMAN
 * @author romai
 *
 */

public class JettyServer {

	public static void main(String[] args) throws Exception {

		// Initialization of the Jetty server:
		Server server = new Server();

		ServerConnector connector = new ServerConnector(server);
		connector.setHost("0.0.0.0");
		connector.setPort(8080);
		connector.setIdleTimeout(30000);
		server.addConnector(connector);

		// Jersey configuration:
		ResourceConfig config = new ResourceConfig();

		config.packages(true, "com.safetyline.apptest.ws");
		config.register(JacksonFeature.class);
		config.register(LoggingFilter.class);
		config.register(CorsFilter.class);

		// Add a servlet handler for web services (/ws/*)
		ServletHolder servletHolder = new ServletHolder((Servlet) new ServletContainer(config));
		ServletContextHandler handlerWebServices = new ServletContextHandler(ServletContextHandler.SESSIONS);
		handlerWebServices.setContextPath("/ws");
		handlerWebServices.addServlet(servletHolder, "/*");

		// Activate handler
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		contexts.setHandlers(new Handler[] { handlerWebServices });
		server.setHandler(contexts);
		// Start server
		server.start();

		User user = new User();
		user.setName("jean");
		user.setPassword("password");
		
		user = DAO.getUserDao().addUser(user);

	}

}
