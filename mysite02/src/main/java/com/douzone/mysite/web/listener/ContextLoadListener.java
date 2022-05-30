package com.douzone.mysite.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    	ServletContext sc = sce.getServletContext();
    	String contextConfigLocation = sc.getInitParameter("contextConfigLocation");
    	
    	System.out.println("Application starts...." + contextConfigLocation);
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	
    }
	
}
