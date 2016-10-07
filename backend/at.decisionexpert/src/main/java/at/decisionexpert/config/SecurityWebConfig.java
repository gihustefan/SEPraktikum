package at.decisionexpert.config;

import at.decisionexpert.business.user.UserBusiness;
import at.decisionexpert.config.repository.CustomCookieCsrfTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserBusiness userBusiness;

    public SecurityWebConfig() {
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userBusiness).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().authorizeRequests().anyRequest().permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .and().csrf().csrfTokenRepository(csrfTokenRepository());
        http.csrf().disable();

        // Adding utf8 filter before spring security filter chain
        CharacterEncodingFilter utf8Filter = new CharacterEncodingFilter();
        utf8Filter.setEncoding("UTF-8");
        utf8Filter.setForceEncoding(true);
        http.addFilterBefore(utf8Filter, CsrfFilter.class);
    }

    public CsrfTokenRepository csrfTokenRepository() {
        CustomCookieCsrfTokenRepository repository = new CustomCookieCsrfTokenRepository("/");
        // Need to set the httponly cookie value to false -> otherwise Angular (or for that matter Javascript)
        // cannot access the cookie -> result is that the necessary X-XCSRF-TOKEN header could not be set if httpOnly = true
        repository.setCookieHttpOnly(false);

        return repository;
    }

    /**
     * We do not want the server to set the location to a fixed destination (e.g. /#login) in the
     * response header of the logout request (the logoutSuccessUrl). Therefore we need a
     * Customized Logout Success Handler that just uses the referer location as the logoutSuccessUrl.
     * <p>
     * <p>
     * See: http://stackoverflow.com/questions/11454729/spring-security-3-1-redirect-after-logout
     *
     * @author Rainer
     */
    private class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
        public CustomLogoutSuccessHandler() {
            super.setUseReferer(true);
        }
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
