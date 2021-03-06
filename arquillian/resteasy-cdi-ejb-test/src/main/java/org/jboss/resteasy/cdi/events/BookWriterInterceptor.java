package org.jboss.resteasy.cdi.events;

import java.io.IOException;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;

/**
 * 
 * @author <a href="ron.sigal@jboss.com">Ron Sigal</a>
 * @version $Revision: 1.1 $
 *
 * Copyright Jul 23, 2012
 */
@Provider
@ServerInterceptor
public class BookWriterInterceptor implements WriterInterceptor
{
   @Inject @WriteIntercept Event<String> writeInterceptEvent;
   @Inject private Logger log;
   
   @Override
   public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException
   {
      log.info("*** Intercepting call in BookWriterInterceptor.write()");
      log.info("BookWriterInterceptor firing writeInterceptEvent");
      writeInterceptEvent.fire("writeInterceptEvent");
      context.proceed();
      log.info("*** Back from intercepting call in BookWriterInterceptor.write()");
   }

}

