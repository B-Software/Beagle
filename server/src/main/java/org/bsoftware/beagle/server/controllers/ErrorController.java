package org.bsoftware.beagle.server.controllers;

import org.bsoftware.beagle.server.services.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ErrorController displays error pages of Beagle application
 *
 * @author Rudolf Barbu
 * @version 1.0.2
 */
@Controller
@RequestMapping(value = "/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController
{
    private final ErrorService errorService;

    /**
     * Get request to display error page, which corresponds status code
     *
     * @param httpServletResponse used for providing response data
     * @return String name of html template
     */
    @GetMapping
    public ResponseEntity<?> getError(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest)
    {
        return errorService.getError(httpServletResponse, httpServletRequest).wrap();
    }

    /**
     * Returns error path
     *
     * @return String error path
     */
    @Override
    public String getErrorPath()
    {
        return "/error";
    }

    @Autowired
    public ErrorController(ErrorService errorService)
    {
        this.errorService = errorService;
    }
}