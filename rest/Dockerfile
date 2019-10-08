FROM java:8
WORKDIR greenFolder
ADD waitForMysql.sh .
ADD /target/rest.jar rest.jar
EXPOSE 8080
CMD java -jar rest.jar
