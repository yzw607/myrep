package com.wmp.util;


import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class OneWebApp
{
    public static void main(String[] args)
        throws Exception
    {
        String jetty_default=new java.io.File("./start.jar").exists()?".":"../..";;
        String jetty_home = System.getProperty("jetty.home",jetty_default);

        Server server = new Server();
        
        Connector connector=new SelectChannelConnector();
        connector.setPort(Integer.getInteger("jetty.port",8080).intValue());
        server.setConnectors(new Connector[]{connector});
        
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(jetty_home+"/webapps/test");
        webapp.setDefaultsDescriptor(jetty_home+"/etc/webdefault.xml");
        
        server.setHandler(webapp);
        
        server.start();
        server.join();
    }
}
