package com.xhh_study1.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;

@SpringBootApplication
/*public class CommunityApplication extends SpringBootServletInitializer {*/
public class CommunityApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

 /*   @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        // This will set to use COOKIE only
        servletContext.setSessionTrackingModes(
                Collections.singleton(SessionTrackingMode.COOKIE)
        );
        // This will prevent any JS on the page from accessing the
        // cookie - it will only be used/accessed by the HTTP transport
        // mechanism in use
        SessionCookieConfig sessionCookieConfig =
                servletContext.getSessionCookieConfig();
        sessionCookieConfig.setHttpOnly(true);
    }*/

}
