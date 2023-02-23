FROM amd64/eclipse-temurin:11
RUN mkdir /opt/app
COPY target/japp-1.jar /opt/app

CMD ["java", "-jar", "/opt/app/japp-1.jar"]

EXPOSE 8080
