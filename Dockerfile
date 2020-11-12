FROM ubuntu:18.04
RUN apt-get update

RUN apt-get install -y openjdk-11-jdk
RUN apt-get install -y nodejs
RUN apt-get install -y npm

CMD ["/bin/bash", "mvn spring-boot:run"]
