package com.wmp.util;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hsqldb.Server;

/**
 * 启动HSQL服务器实例
 * 
 */
public class HsqlServerListener implements ServletContextListener {
	private void startHsqlServer(String dbPath, String dbName, int port) {
		Server server = new Server();
		// 0表示第0个数据库，Server模式可以启动10个数据库
		server.setDatabasePath(0, dbPath + dbName);
		server.setDatabaseName(0, dbName);
		server.setPort(port);
		server.setSilent(true);
		server.start();
		System.out.println("HSQLDB Server started!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// 类路径
		String classpath = Thread.currentThread().getContextClassLoader()
				.getResource(".").getPath();
		File file = new File(classpath);
		String dbPath = arg0.getServletContext().getRealPath("/") + "\\WEB-INF\\db\\";
		String dbName = "mydb";
		int port = 2011;
		System.out.println();
		new HsqlServerListener().startHsqlServer(dbPath, dbName, port);

	}
}