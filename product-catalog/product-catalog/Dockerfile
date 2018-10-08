# pull image of jdk
FROM java:9.0.4-jre

# copy jar file to docker file system
ADD ./target/product-catalog-0.0.1-SNAPSHOT.jar /usr/app/product-catalog-0.0.1-SNAPSHOT.jar

# java -jar <jar fileName>
WORKDIR usr/app
ENTRYPOINT ["java","-jar", "product-catalog-0.0.1-SNAPSHOT.jar"]
EXPOSE 9072
