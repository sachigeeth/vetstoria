FROM openjdk:8-jdk
ARG profile
ARG time_zone
ENV profile=$profile
ENV time_zone=$time_zone
VOLUME ["/tmp", "/tmp/config", "/tmp/logs"]
RUN ln -snf /usr/share/zoneinfo/$time_zone /etc/localtime && echo $time_zone > /etc/timezone
ADD target/operation-docker.jar operation-docker.jar
EXPOSE 9999:9999
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Xmx3G",  "-Dlog4j2.formatMsgNoLookups=true", "-Dspring.profiles.active=${profile}","-jar","operation-docker.jar"]

