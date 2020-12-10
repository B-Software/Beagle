package org.bsoftware.beagle.server.controllers;

import org.bsoftware.beagle.server.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StatisticsController displays responses from rest API
 *
 * @author Rudolf Barbu
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/api/statistics")
public class StatisticsController
{
    /**
     * Autowired StatisticsService object
     * Used for getting server statistics
     */
    @Autowired
    private StatisticsService statisticsService;

    /**
     * Get server statistics
     *
     * @return ResponseEntity to servlet
     */
    @GetMapping
    public ResponseEntity<?> getStatistics()
    {
        return new ResponseEntity<>(statisticsService.getStatistics(), HttpStatus.OK);
    }
}