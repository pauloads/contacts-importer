FROM openjdk:17
COPY target/contacts-importer-0.0.1-SNAPSHOT.jar /usr/src/contacts-importer/
WORKDIR /usr/src/contacts-importer
ENTRYPOINT ["java","-jar","contacts-importer-0.0.1-SNAPSHOT.jar"]