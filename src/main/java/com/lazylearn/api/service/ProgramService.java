package com.lazylearn.api.service;

import com.lazylearn.api.config.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getCardStepEnd(String programId){
        final String format = "select max(step) + 1 from staticstep where programid = '%s'";
        int ccc = jdbcTemplate.queryForObject(String.format(format, programId), Integer.class);
        return ccc;
    }

    public int getCardStepEndForUser(){
        final String sql = "select max(step) + 1 from staticstep";
        int ccc = jdbcTemplate.queryForObject(sql, Integer.class);
        return ccc;
    }

    public String getLabel(String programId, int stepValue){

        if (stepValue == Consts.CARD_STEP_BEGIN){
            return Consts.CARD_STEP_BEGIN__LABEL;
        }

        if (stepValue >= getCardStepEnd(programId)){
            return Consts.CARD_STEP_END__LABEL;
        }

        final String format = "select name from staticstep where programid = '%s' and step = %s";
        String ccc = jdbcTemplate.queryForObject(String.format(format, programId, stepValue), String.class);
        return ccc;
    }

    public String getLabelForUser(int stepValue){

        if (stepValue == Consts.CARD_STEP_BEGIN){
            return Consts.CARD_STEP_BEGIN__LABEL;
        }

        if (stepValue >= getCardStepEndForUser()){
            return Consts.CARD_STEP_END__LABEL;
        }

        return "REP" + stepValue;
    }

    public Integer getDays(String programId, int stepValue){
        final String format = "select days from staticstep where programid = '%s' and step = %s";
        Integer ccc = jdbcTemplate.queryForObject(String.format(format, programId, stepValue), Integer.class);
        return ccc;
    }
}
