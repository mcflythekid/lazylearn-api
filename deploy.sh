#!/bin/bash
/bin/cp -rf /mnt/c/lazylearn/config.properties src/main/resources/application.properties
mvn clean install -DskipTests
#mv -f target/*.war /var/lib/tomcat8/webapps/lazylearn-api.war
scp target/*.war cl@beathim:/var/lib/tomcat8/webapps/concu.war