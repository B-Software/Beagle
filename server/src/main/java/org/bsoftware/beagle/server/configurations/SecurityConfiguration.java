package org.bsoftware.beagle.server.configurations;

import org.bsoftware.beagle.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * SecurityConfiguration used for web security configuration
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    /**
     * Autowired UserService object
     * Used for getting user information
     */
    private final UserService userService;

    /**
     * Autowired BCryptPasswordEncoder object
     * Used for password encryption
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Autowired AuthenticationEntryPoint object
     * Used customizing unauthorized exception
     */
    private final AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * Autowired AccessDeniedHandler object
     * Used customizing access denied exception
     */
    private final AccessDeniedHandler accessDeniedHandler;

    /**
     * Configures authentication builder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * Configures http security
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/user").hasAuthority("ROLE_ANONYMOUS")
                .antMatchers(HttpMethod.PUT,"/api/user").hasAuthority("ROLE_ANONYMOUS")
                .anyRequest()
                .authenticated();

        httpSecurity
                .csrf()
                .disable();

        httpSecurity
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);

        httpSecurity
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    /**
     * @return AuthenticationManager object as bean
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager() throws Exception
    {
        return super.authenticationManager();
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param userService autowired UserService object
     * @param bCryptPasswordEncoder autowired BCryptPasswordEncoder object
     * @param authenticationEntryPoint autowired AuthenticationEntryPoint object
     * @param accessDeniedHandler autowired AccessDeniedHandler object
     */
    @Autowired
    public SecurityConfiguration(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationEntryPoint authenticationEntryPoint, AccessDeniedHandler accessDeniedHandler)
    {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }
}