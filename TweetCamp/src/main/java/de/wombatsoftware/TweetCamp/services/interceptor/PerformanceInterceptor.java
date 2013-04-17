package de.wombatsoftware.TweetCamp.services.interceptor;

import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import de.wombatsoftware.TweetCamp.qualifier.PerformanceLog;
import de.wombatsoftware.TweetCamp.services.interceptor.binding.Performance;

@Interceptor
@Performance
public class PerformanceInterceptor {

    @Inject
    private Logger logger;

    @Inject
    @PerformanceLog
    private Event<String> logEvent;
    
    @Inject
    @PerformanceLog
    private Event<Long> logLongEvent;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {
	long before = System.currentTimeMillis();

	Object o = ic.proceed();

	long after = System.currentTimeMillis();

//	logger.info("Duration of method call to " + ic.getMethod() + ": " + (after - before) + "ms");
	logEvent.fire("Duration of method call to " + ic.getMethod() + ": " + (after - before) + "ms");
	logLongEvent.fire((after - before));

	return o;
    }
}