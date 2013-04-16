package de.wombatsoftware.TweetCamp.producer;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {   
    @Produces
    public Logger createLogger(InjectionPoint ip) {
	return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
    }
}