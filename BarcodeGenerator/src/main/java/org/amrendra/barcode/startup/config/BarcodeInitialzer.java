package org.amrendra.barcode.startup.config;

import javax.servlet.HttpConstraintElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletSecurityElement;
import javax.servlet.annotation.ServletSecurity;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class BarcodeInitialzer implements WebApplicationInitializer
{

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    private static final String DISPATCHER_SERVLET_MAPPING = "/*";

    @Override
    public void onStartup( ServletContext servletContext ) throws ServletException
    {
        WebApplicationContext context = getContext();
        servletContext.addListener( new ContextLoaderListener( context ) );
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet( DISPATCHER_SERVLET_NAME,
                                                                            new DispatcherServlet( context ) );
        dispatcher.setLoadOnStartup( 1 );
        dispatcher.addMapping( DISPATCHER_SERVLET_MAPPING );

        // FORCE HTTPS
        HttpConstraintElement forceHttpContraints = new HttpConstraintElement( ServletSecurity.TransportGuarantee.CONFIDENTIAL );
        ServletSecurityElement servletSecurity = new ServletSecurityElement( forceHttpContraints );
        dispatcher.setServletSecurity( servletSecurity );

    }

    private AnnotationConfigWebApplicationContext getContext()
    {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation( "org.amrendra.barcode" );
        context.register( BarcodeConfig.class );
        return context;
    }
}
