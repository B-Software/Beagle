package org.bsoftware.beagle.server.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tika.Tika;
import org.bsoftware.beagle.server.dto.ErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.util.unit.DataSize;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Properties;

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
     * Autowired Environment object
     * Used for get values from property file
     */
    private final Environment environment;

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

    /**
     * @return DataSource object as bean
     */
    @Bean
    public DataSource dataSource()
    {
        return DataSourceBuilder
                .create()
                .url("jdbc:mysql://" + environment.getProperty("datasource.host") + "/" + environment.getProperty("datasource.database") + "?serverTimezone=UTC&allowLoadLocalInfile=true")
                .username(environment.getProperty("datasource.username"))
                .password(environment.getProperty("datasource.password"))
                .build();
    }

    /**
     * @return LocalContainerEntityManagerFactoryBean object as bean
     */
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(DataSource dataSource)
    {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        Properties properties = new Properties();

        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setPackagesToScan("org.bsoftware.beagle.server.entities");
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.globally_quoted_identifiers", "true");
        properties.setProperty("hibernate.dialect", "org.bsoftware.beagle.server.dialects.MySQL8MyISAMDialect");

        localContainerEntityManagerFactoryBean.setJpaProperties(properties);

        return localContainerEntityManagerFactoryBean;
    }

    /**
     * @return MultipartProperties object as bean
     */
    @Bean
    public MultipartProperties multipartProperties()
    {
        MultipartProperties multipartProperties = new MultipartProperties();

        multipartProperties.setMaxFileSize(DataSize.ofMegabytes(1024));
        multipartProperties.setMaxRequestSize(DataSize.ofMegabytes(1024));

        return multipartProperties;
    }

    /**
     * Used for autowiring necessary objects
     *
     * @param environment autowired Environment object
     */
    @Autowired
    public BeanConfiguration(Environment environment)
    {
        this.environment = environment;
    }
}