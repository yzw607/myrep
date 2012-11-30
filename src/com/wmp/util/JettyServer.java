package com.wmp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.nio.SelectChannelConnector;
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
        //http://localhost:8088/CxfHello/HelloWorld?wsdl  
        deployWebApp("web", "/CxfHello", 8088);  
  
        //http://localhost:8080/web-sample1  
        deployWebApp("C:/temp/web-sample1.war", "/web-sample1", 8080);  
  
        //http://localhost:8080/web-sample2  
        deployWebApp("C:/temp/web-sample2/web", "/web-sample2", 8080);  
  
        startServer();  
    }  
  
    static void startServer() throws Exception {  
        long startTime = System.currentTimeMillis();  
  
        for (Integer port : portServers.keySet()) {  
            Server server = portServers.get(port);  
  
            List<WebAppContext> webAppContexts = webAppContextLists.get(port);  
            Handler[] handlers1 = webAppContexts.toArray(new Handler[webAppContexts.size()]);  
  
            //Add DefaultHandler  
            Handler[] handlers = new Handler[handlers1.length + 1];  
            System.arraycopy(handlers1, 0, handlers, 0, handlers1.length);  
            handlers[handlers.length - 1] = new DefaultHandler();  
  
            HandlerCollection handlerCollection = new HandlerCollection();  
            handlerCollection.setHandlers(handlers);  
            server.setHandler(handlerCollection);  
  
            server.start();  
        }  
  
        System.out.printf("Server started in %.3f seconds. %n", ((double) (System.currentTimeMillis() - startTime)) / 1000);  
    }  
  
    static void deployWebApp(String war, String contextPath, int port) throws Exception {  
        Server server = portServers.get(port);  
          
        //1) Set Connector/port  
        if (server == null) {  
            server = new Server();  
            SelectChannelConnector connector = new SelectChannelConnector();  
            connector.setPort(port);  
            server.setConnectors(new Connector[] { connector });  
              
            portServers.put(port, server);  
        }  
  
        //2) Set Context 'Context Path' and 'War'  
        WebAppContext webAppContext = new WebAppContext();  
        webAppContext.setContextPath(contextPath);  
        //It can be war file's path or webapp directory  
        webAppContext.setWar(war);  
  
        List<WebAppContext> webAppContexts = webAppContextLists.get(port);  
        if (webAppContexts == null) {  
            webAppContexts = new ArrayList<WebAppContext>();  
            webAppContextLists.put(port, webAppContexts);  
        }  
        webAppContexts.add(webAppContext);  
          
        System.out.printf("[%s] is ready...%n", contextPath);  
    }  
}  
