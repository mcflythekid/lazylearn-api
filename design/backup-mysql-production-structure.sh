#!/bin/bash
ssh cl@lazy mysqldump --no-data -u root -p lazylearn_api > mysql-no-data.sql