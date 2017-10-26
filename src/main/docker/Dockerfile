#基础镜像
FROM hub.c.163.com/wuxukun/maven-aliyun:3-jdk-8

VOLUME /tmp

ADD look-0.0.1-SNAPSHOT.jar app.jar

#容器构建时执行的命令
RUN bash -c 'touch /app.jar'

#容器暴露端口
EXPOSE 8888

#容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
