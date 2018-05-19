#!/bin/bash
/bin/cp -rf /opt/lazylearn-api/application.properties ./src/main/resources/application.properties
mvn clean install
#mv