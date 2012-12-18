package com.wmp.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;


/** 
 * Start Jetty Server(Servlet container)  
 * Dependencies: 
 *   jetty.jar, jetty-util.jar, servlet-api-2.5.6.jar 
 *   (/jsp-2.1) core.jar, jsp-2.1.jar, jsp-api-2.1.jar 
 * @author louis 
 */  
public class JettyServer {  
    static Map<Integer, Server> portServers = new HashMap<Integer, Server>();  
    static Map<Integer, List<WebAppContext>> webAppContextLists = new HashMap<Integer, List<WebAppContext>>();  
  
    public static void main(String args[]) throws Exception {  
        //http://localhost:8080/web-sample2  
       /* deployWebApp("C://Users//xiaolij//Desktop//say//say520//say520//say520.war", "/say520", 8080);  
  
        startServer();  */
    	
//    	 String jetty_home = System.getProperty("jetty.home","..");
    	 
         Server server = new Server(8080);
  
         WebAppContext webapp = new WebAppContext();
         webapp.setContextPath("/");
//         webapp.setDescriptor("C:/Work/myworkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/say520/WEB-INF/web.xml");  
//         webapp.setResourceBase("C:/Work/myworkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/say520");
         webapp.setDescriptor("C:/Private/PrivateDocs/Project/Say520/say520/say520/WebContent/WEB-INF/web.xml");  
         webapp.setResourceBase("C:/Private/PrivateDocs/Project/Say520/say520/say520/WebContent/");
         
//         webapp.setDescriptor("C:/say520/WebContent/WEB-INF/web.xml");  
//         webapp.setResourceBase("C:/say520/WebContent/");  
//         webapp.setWar(war)

         webapp.setParentLoaderPriority(true); 
         server.setHandler(webapp);
  
         server.start();
         server.join();
    }  
  
   
 
}  
