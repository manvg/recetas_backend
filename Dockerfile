FROM eclipse-temurin:22-jdk AS buildstage 

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src
COPY Wallet_QHKV1HXJX2PK6K3Q /app/wallet

ENV TNS_ADMIN=/app/wallet

RUN mvn clean package

FROM eclipse-temurin:22-jdk 

COPY --from=buildstage /app/target/recetas_backend-0.0.1-SNAPSHOT.jar /app/recetas_backend.jar

COPY Wallet_QHKV1HXJX2PK6K3Q /app/wallet

ENV TNS_ADMIN=/app/wallet
EXPOSE 8084

ENTRYPOINT [ "java", "-jar","/app/recetas_backend.jar" ]