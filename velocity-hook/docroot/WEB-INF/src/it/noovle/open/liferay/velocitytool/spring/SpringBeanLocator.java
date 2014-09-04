package it.noovle.open.liferay.velocitytool.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@Component
public class SpringBeanLocator implements ApplicationContextAware {
	private static final Log log = LogFactoryUtil
			.getLog(SpringBeanLocator.class);

	private static Map<String, Object> classMap = new HashMap<String, Object>();

	private static ApplicationContext ctx;

	/**
	 * Returns the bean of the specified full qualified class name.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String className) {
		try {
			log.debug("ClassName: " + className);
			Class<?> clazz = Class.forName(className);
			log.debug("Class: " + clazz);
			log.debug("ApplicationContext: " + ctx);
			Object classInstance = classMap.get(className);
			if (classInstance == null) {
				classInstance = clazz.newInstance();
				classMap.put(className, classInstance);
			}
			return (T) classInstance;
		} catch (BeansException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		log.debug("setApplicationContext: " + applicationContext);
		ctx = applicationContext;
	}

}
