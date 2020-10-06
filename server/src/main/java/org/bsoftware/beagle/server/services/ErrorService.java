package org.bsoftware.beagle.server.services;

import org.bsoftware.beagle.server.assets.ResponseEntityWrapperAsset;
import org.bsoftware.beagle.server.dto.implementation.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ErrorService displays error pages of Beagle application
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@Service
public class ErrorService
{
    /**
     * Return error response to servlet
     *
     * @param httpServletResponse current response
     * @param httpServletRequest current request
     * @return ResponseEntityWrapperAsset object
     */
    @SuppressWarnings(value = "ConstantConditions")
    public ResponseEntityWrapperAsset<?> getError(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest)
    {
        RequestAttributes requestAttributes = new ServletRequestAttributes(httpServletRequest);

        return new ResponseEntityWrapperAsset<>(new ResponseDto(requestAttributes.getAttribute("javax.servlet.error.message", 0).toString()), HttpStatus.resolve(httpServletResponse.getStatus()));
    }
}