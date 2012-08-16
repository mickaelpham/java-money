package eu.mpham;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class App {
	
	final static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
		
		logger.info("Entering application.");
		Foo foo = new Foo();
		foo.doIt();
		logger.info("Existing application.");
	}

}
