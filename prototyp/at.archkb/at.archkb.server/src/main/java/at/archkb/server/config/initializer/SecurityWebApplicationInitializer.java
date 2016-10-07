package at.archkb.server.config.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        // Needed for csrf protection and upload --> means that even unauthenticated users can upload stuff to the temporary folder,
        // But cannot persist them --> common practice (the tmp folder should be secured on the server)
        // http://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html#csrf-multipartfilter
//        insertFilters(servletContext, new MultipartFilter());
    }
}
