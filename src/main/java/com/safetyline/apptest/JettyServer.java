package com.safetyline.apptest;

import java.util.ArrayList;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.safetyline.apptest.dao.DAO;
import com.safetyline.apptest.dao.Movie;
import com.safetyline.apptest.dao.User;

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

		// ------------ TODO: delete this part below-------------------//

		ResourceHandler handlerPortal = new ResourceHandler();
		handlerPortal.setResourceBase("src/main/webapp");
		handlerPortal.setDirectoriesListed(false);

		ContextHandler handlerPortalCtx = new ContextHandler();
		handlerPortalCtx.setContextPath("/");
		handlerPortalCtx.setHandler(handlerPortal);

		// ------------ TODO: delete this part above------------------//

		// Add a servlet handler for web services (/ws/*)
		ServletHolder servletHolder = new ServletHolder((Servlet) new ServletContainer(config));
		ServletContextHandler handlerWebServices = new ServletContextHandler(ServletContextHandler.SESSIONS);
		handlerWebServices.setContextPath("/ws");
		handlerWebServices.addServlet(servletHolder, "/*");

		// Activate handlers
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		contexts.setHandlers(new Handler[] { handlerWebServices });
		server.setHandler(contexts);

		// Start server
		server.start();

		User user = new User();
		user.setName("jean");
		user.setHashPassword("password");
		user.setMovies(new ArrayList<Movie>());
		DAO.getUserDao().addUser(user);

	}

}
