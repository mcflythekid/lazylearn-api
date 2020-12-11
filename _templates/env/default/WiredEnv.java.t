---
to: src/main/java/com/lazylearn/api/config/env/WiredEnv.java
---
// Generated
package com.lazylearn.api.config.env;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WiredEnv {

    //db
    private String dbUrl = "<%= db.url %>";
    private String dbUsername = "<%= db.username %>";
    private String dbPassword = "<%= db.password %>";
    private boolean dbShowSql = <%= db.show_sql %>;
    private String dbDriver = "<%= db.driver %>";
    private String dbDialect = "<%= db.dialect %>";
    private Integer dbPoolSize = <%= db.pool_size %>;

    //jwf
    private String jwtSecret = "<%= jwt_secret %>";

    //file
    private String fileUpload = "<%= file.absolute_dir %>";
    private String fileUrl = "<%= file.url %>";

    private String resetPassword = "<%= reset_password %>";

    private String mailServer = "<%= mail.server %>";
    private Integer mailPort = <%= mail.port %>;

    private boolean telegramEnabled = <%= telegram.enabled %>;
}
