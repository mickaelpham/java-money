package eu.mpham;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class App {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(App.class);
		logger.debug("Hello World!");

		// print internal state
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
	}

}
