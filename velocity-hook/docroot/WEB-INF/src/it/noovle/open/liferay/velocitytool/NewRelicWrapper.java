package it.noovle.open.liferay.velocitytool;

import org.springframework.stereotype.Service;

import com.newrelic.api.agent.NewRelic;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@Service
public class NewRelicWrapper {
	
	private static final Log log = LogFactoryUtil.getLog(NewRelicWrapper.class);
	
	public NewRelicWrapper() {
		log.debug("NewRelicWrapper");
	}

	public String getBrowserTimingHeader() {
		log.debug("getBrowserTimingHeader");
		
		String newRelicSnippet = NewRelic.getBrowserTimingHeader();
		log.debug("newRelicSnippet: " + newRelicSnippet);
		
		return newRelicSnippet;
	}

	public String getBrowserTimingFooter() {
		log.debug("getBrowserTimingFooter");
		
		String newRelicSnippet = NewRelic.getBrowserTimingFooter();
		log.debug("newRelicSnippet: " + newRelicSnippet);
		
		return newRelicSnippet;
	}
}
