package com.lazylearn.api.config.env;

import lombok.Data;

@Data
public class WiredEnv {
    //db
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private boolean dbShowSql;
    private String dbDriver;
    private String dbDialect;
    private Integer dbPoolSize;

    //jwf
    private String jwtSecret;

    //file
    private String fileUpload;
    private String fileUrl;

    private String resetPassword;

    private String mailServer;
    private Integer mailPort;

    private boolean telegramEnabled;
}
