FROM hub.c.163.com/wuxukun/maven-aliyun:3-jdk-8

VOLUME /tmp

ADD look-0.0.1-SNAPSHOT.jar app.jar

RUN bash -c 'touch /app.jar'

EXPOSE 8088

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
