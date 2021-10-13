FROM adoptopenjdk/openjdk11
LABEL com.ecommerce.shop="shop"
EXPOSE 8082
COPY target/shop-0.0.1-SNAPSHOT.jar /app.jar
CMD java -jar app.jar