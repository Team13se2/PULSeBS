FROM ubuntu:latest
RUN apt-get update

RUN apt-get install -y openjdk-11-jdk ;\
 apt-get install -y npm ;\
 apt-get install -y git ;\
 git clone https://github.com/Team13se2/PULSeBS.git ;\
 chmod +x PULSeBS/mvnw ;\
 cd PULSeBS/client-app ;\
 npm install

EXPOSE 8081/tcp
EXPOSE 3000/tcp

WORKDIR /PULSeBS
CMD  ./mvnw spring-boot:run & ( cd client-app ; npm start)
