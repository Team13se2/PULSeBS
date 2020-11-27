ROM ubuntu:latest
RUN apt-get update

RUN apt-get install -y openjdk-11-jdk
#nodejs
RUN apt-get install -y npm
RUN apt-get install -y git

RUN git clone https://github.com/Team13se2/PULSeBS.git && chmod +x PULSeBS/mvnw

WORKDIR PULSeBS/client-app
RUN npm install

EXPOSE 3000/tcp
EXPOSE 8081/tcp

WORKDIR /PULSeBS
CMD  ./mvnw spring-boot:run & ( cd client-app && npm start)
