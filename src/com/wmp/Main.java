package com.wmp;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class Main extends JPanel {
	private JPanel webBrowserPanel;

	private JWebBrowser webBrowser;

	private String url;

	static Map<Integer, Server> portServers = new HashMap<Integer, Server>();
	static Map<Integer, List<WebAppContext>> webAppContextLists = new HashMap<Integer, List<WebAppContext>>();

	public Main() {

	}

	public Main(String url) {
		super(new BorderLayout());
		this.url = url;
		webBrowserPanel = new JPanel(new BorderLayout());
		webBrowser = new JWebBrowser();
		webBrowser.navigate(url);
		webBrowser.setButtonBarVisible(false);
		webBrowser.setMenuBarVisible(false);
		webBrowser.setBarsVisible(false);
		webBrowser.setStatusBarVisible(false);
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		add(webBrowserPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) throws Exception {
		String classPath = System.getProperty("user.dir");
		Main main = new Main();
		main.startJetty(classPath);

		// System.out.println(classPath);
		// final String url = classPath + "/login.jsp";
		final String url = "http://localhost:8080/login.jsp";
//		final String url = "mail.163.com";
		final String imgUrl = classPath + "/ico.jpg";
		final String title = "Say520";
		
		
//        java.net.URI uri = java.net.URI.create("http://localhost:8080/entry.jsp"); 
//		java.net.URI uri = java.net.URI.create("http://localhost:8080/login.jsp"); 
//        //获取当前系统桌面扩展
//        java.awt.Desktop dp = java.awt.Desktop.getDesktop();
//        //判断系统桌面是否支持要执行的功能
//        if(dp.isSupported(java.awt.Desktop.Action.BROWSE)){
//            //获取系统默认浏览器打开链接
//            dp.browse(uri);    
//        }
		

		UIUtils.setPreferredLookAndFeel();
		NativeInterface.open();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame(title);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(new Main(url), BorderLayout.CENTER);
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				Image img = Toolkit.getDefaultToolkit().getImage(imgUrl);
				frame.setIconImage(img);
				frame.setLocationByPlatform(true);
				frame.setSize(800, 600);
				// frame.setUndecorated(true);
				frame.setVisible(true);
			}
		});
		NativeInterface.runEventPump();
	}

	public void startJetty(String rootPath) throws Exception {
		// http://localhost:8080/web-sample2
		/*
		 * deployWebApp(
		 * "C://Users//xiaolij//Desktop//say//say520//say520//say520.war",
		 * "/say520", 8080);
		 * 
		 * startServer();
		 */

		// String jetty_home = System.getProperty("jetty.home","..");

		Server server = new Server(8080);

		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		// webapp.setDescriptor("C:/Work/myworkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/say520/WEB-INF/web.xml");
		// webapp.setResourceBase("C:/Work/myworkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/say520");
		// webapp.setDescriptor("C:/Private/PrivateDocs/Project/Say520/say520/say520/WebContent/WEB-INF/web.xml");
		// webapp.setResourceBase("C:/Private/PrivateDocs/Project/Say520/say520/say520/WebContent/");

		webapp.setDescriptor(rootPath + "/WebContent/WEB-INF/web.xml");
		webapp.setResourceBase(rootPath + "/WebContent/");

		// webapp.setDescriptor("C:/say520/WebContent/WEB-INF/web.xml");
		// webapp.setResourceBase("C:/say520/WebContent/");
		// webapp.setWar(war)

		webapp.setParentLoaderPriority(true);
		server.setHandler(webapp);

		server.start();
//		server.join();
	}
}
