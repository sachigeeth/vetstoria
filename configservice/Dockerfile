FROM openjdk:8-jdk
ARG profile
ARG time_zone
ENV profile=$profile
ENV time_zone=$time_zone
VOLUME ["/tmp", "/tmp/config", "/tmp/logs"]
RUN ln -snf /usr/share/zoneinfo/$time_zone /etc/localtime && echo $time_zone > /etc/timezone
ADD target/config-docker.jar config-docker.jar
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dlog4j2.formatMsgNoLookups=true", "-Xmx500m", "-jar","config-docker.jar"]
