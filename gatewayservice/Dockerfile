FROM openjdk:8-jdk
ARG profile
ARG time_zone
ENV profile=$profile
ENV time_zone=$time_zone
VOLUME ["/tmp", "/tmp/config", "/tmp/logs"]
RUN ln -snf /usr/share/zoneinfo/$time_zone /etc/localtime && echo $time_zone > /etc/timezone
ADD target/geteway-docker.jar geteway-docker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom",  "-Dlog4j2.formatMsgNoLookups=true", "-Xmx1G", "-jar", "geteway-docker.jar","--spring.config.location=classpath:/application.yml,file:/tmp/config/application.yml"]
