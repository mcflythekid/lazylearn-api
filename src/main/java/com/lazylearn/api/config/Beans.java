package com.lazylearn.api.config;

import com.lazylearn.api.unit.OxfordUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 4nha
 * Date: 2020-04-19
 */
@Configuration
public class Beans {

    @Bean
    public OxfordUnit oxfordUnit(){
        return new OxfordUnit();
    }
}
