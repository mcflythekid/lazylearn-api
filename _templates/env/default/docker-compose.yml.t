---
to: docker/default/docker-compose.yml
---
version: '3.5'
services:
  <%= docker.name %>:
    restart: always
    container_name: <%= docker.name %>
    user: ':<%= gid %>'
    image: openjdk:8
    ports:
      - <%= port %>:<%= port %>
    volumes:
      - <%= file.absolute_dir_host %>/:<%= file.absolute_dir %>/
      - ../../target/app.jar:/app/app.jar
      - <%= log.absolute_dir_host %>:<%= log.absolute_dir %>
    command: bash -c "mkdir -p <%= log.absolute_dir %> && chmod -R g+w <%= log.absolute_dir %> && java -Duser.timezone=Asia/Ho_Chi_Minh -Xmx<%= memory %> -jar /app/app.jar 2>><%= log.absolute_dir %>/stderr.log"
networks:
  default:
    external:
      name: <%= docker.network %>