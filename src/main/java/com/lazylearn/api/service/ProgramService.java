package com.lazylearn.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMaxStepOfDeck(String deckId) {
        final String format = "select ifnull(max(step), 0) from card where deckid = '%s'";
        int step = jdbcTemplate.queryForObject(String.format(format, deckId), Integer.class);
        return step;
    }

    public int getMaxStepOfUser(String userId) {
        final String format = "select ifnull(max(step), 0) from card where userid = '%s'";
        int step = jdbcTemplate.queryForObject(String.format(format, userId), Integer.class);
        return step;
    }

    public String getLabel(int stepValue) {
        return "Deck #" + (stepValue + 1);
    }

}
