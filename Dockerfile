FROM ubuntu:18.04
RUN apt-get update

RUN apt install openjdk-11-jdk
RUN apt-get install nodejs
RUN apt-get install npm

CMD [‘’/bin/bash’’, ‘’mvn spring-boot:run’’]
