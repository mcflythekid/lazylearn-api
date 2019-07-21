---
to: docker/default/docker-compose.yml
---
version: '3.5'
services:
  <%= docker.name %>:
    user: ':<%= gid %>'
    image: openjdk:8
    ports:
      - <%= port %>:8080
    volumes:
      - ../../target/app.jar:/app/app.jar
      - <%= log.absolute_dir %>:<%= log.absolute_dir_in_docker_container %>
    command: bash -c "mkdir -p <%= log.absolute_dir_in_docker_container %> && chmod -R g+w <%= log.absolute_dir_in_docker_container %> && java -Duser.timezone=Asia/Ho_Chi_Minh -Xmx<%= memory %> -jar /app/app.jar 2>><%= log.absolute_dir_in_docker_container %>/stderr.log"
networks:
  default:
    external:
      name: <%= docker.network %>