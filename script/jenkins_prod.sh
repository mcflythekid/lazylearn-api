#!/bin/bash
/bin/cp -rf /var/lazylearn-api/config.properties src/main/resources/application.properties
mvn clean install -DskipTests
mv -f target/*.war /var/lib/tomcat8/webapps/lazylearn-api.war