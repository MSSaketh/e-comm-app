# pull image of jdk
FROM java:8-jre

# copy jar file to docker file system
ADD ./target/product-cart-0.0.1-SNAPSHOT.jar /usr/app/product-cart-0.0.1-SNAPSHOT.jar

# java -jar <jar fileName>
WORKDIR usr/app
ENTRYPOINT ["java","-jar", "product-cart-0.0.1-SNAPSHOT.jar"]
EXPOSE 9073
