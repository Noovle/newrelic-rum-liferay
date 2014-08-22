package it.noovle.open.liferay.newrelic.spring;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.XmlWebApplicationContext;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * This context listener initializes a new ApplicationContext and replaces the default BeanLocator by a
 * VelocityBeanLocator, allowing $utilLocator to retrieve any bean registered on the classpath of the web application
 * this context listener is invoked for.
 * 
 * To enable the context listener, configure the following in the web.xml of an empty hook project:
 * 
 * <pre>
 * <listener>
 *     <listener-class>be.aca.literay.spring.VelocityBeanLocatorContextListener</listener-class>
 * </listener>
 * 
 * <context-param>
 *     <param-name>contextConfigLocation</param-name>
 *     <param-value>classpath*:applicationContext.xml</param-value>
 * </context-param>
 * </pre>
 * 
 * Note: the contextConfigLocation value can point to any context location.
 */
public class VelocityBeanLocatorContextListener implements ServletContextListener {
	
	private static final Log log = LogFactoryUtil.getLog(VelocityBeanLocatorContextListener.class);

	private static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

	private XmlWebApplicationContext ctx;

	public VelocityBeanLocatorContextListener() {
		log.debug("");
		ctx = new XmlWebApplicationContext();
	}

	public void contextInitialized(ServletContextEvent sce) {
		initializeContext(sce);
		String servletContext = sce.getServletContext().getContextPath().substring(1);
		log.debug("servletContext: " + servletContext);
		PortletBeanLocatorUtil.setBeanLocator(servletContext, new VelocityBeanLocator());
	}

	private void initializeContext(ServletContextEvent sce) {
		ctx.setServletContext(sce.getServletContext());
		ctx.setConfigLocation(sce.getServletContext().getInitParameter(CONTEXT_CONFIG_LOCATION));
		ctx.refresh();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		ctx.close();
	}

}