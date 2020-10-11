package org.bsoftware.beagle.server.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tika.Tika;
import org.bsoftware.beagle.server.dto.ErrorDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BeanConfiguration provides bean configuration for classes, which are not components
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Configuration
public class BeanConfiguration
{
    /**
     * @return Tika object as bean
     */
    @Bean
    public Tika tika()
    {
        return new Tika();
    }

    /**
     * @return BCryptPasswordEncoder object as bean
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return AuthenticationEntryPoint object as bean
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint()
    {
        return (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authenticationException) ->
        {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorDto(authenticationException)));
        };
    }

    /**
     * @return AccessDeniedHandler object as bean
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler()
    {
        return (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException accessDeniedException) ->
        {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorDto(accessDeniedException)));
        };
    }
}