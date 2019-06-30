// Generated
package com.lazylearn.api.config.env;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WiredEnv {

    //db
    private String dbUrl = "jdbc:mysql://lazylearn.com:20000/lazylearn?useSSL=false&amp;useUnicode=yes&amp;characterEncoding=UTF-8";
    private String dbUsername = "root";
    private String dbPassword = "12345678";
    private boolean dbShowSql = false;
    private String dbDriver = "com.mysql.jdbc.Driver";
    private String dbDialect = "org.hibernate.dialect.MySQLDialect";
    private Integer dbPoolSize = 10;

    //jwf
    private String jwtSecret = "conkmeo";

    //file
    private String fileUpload = "d:/data-lazylearn-tmp";
    private String fileUrl = "http://localhost:8888/file";

    private String resetPassword = "https://local.lazylearn.com/auth/reset-password.php?id=%s";
}
