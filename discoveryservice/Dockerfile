FROM openjdk:8-jdk
ARG profile
ARG time_zone
ENV profile=$profile
ENV time_zone=$time_zone
VOLUME ["/tmp", "/tmp/config", "/tmp/logs"]
RUN ln -snf /usr/share/zoneinfo/$time_zone /etc/localtime && echo $time_zone > /etc/timezone
ADD target/discovery-docker.jar discovery-docker.jar
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dlog4j2.formatMsgNoLookups=true", "-Xmx500m", "-Dspring.profiles.active=${profile}","-jar","discovery-docker.jar"]
