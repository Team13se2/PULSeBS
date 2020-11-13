FROM ubuntu:18.04
RUN apt-get update

RUN apt-get install -y openjdk-11-jdk
RUN apt-get install -y nodejs
RUN apt-get install -y npm
RUN apt-get install -y maven
RUN apt-get install -y git
RUN git clone https://github.com/Team13se2/PULSeBS.git

EXPOSE 8081

CMD cd PULSeBS; mvn spring-boot:run
