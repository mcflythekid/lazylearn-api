package com.lazylearn.api.controller;

import com.lazylearn.api.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/chart")
public class ChartController extends BaseController {

    @Autowired
    private ChartService chartService;

    @GetMapping("/get-user/{userId}")
    public Object userChart(@PathVariable("userId") String userId) throws Exception {
        authorizeUser(userId);
        return chartService.userChart(userId);
    }

    @GetMapping("/get-deck/{deckId}")
    public Object deckChart(@PathVariable("deckId") String deckId) throws Exception {
        authorizeDeck(deckId);
        return chartService.deckChart(deckId);
    }
}