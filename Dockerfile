# Dockerfile에서 환경 변수를 설정하는 방법
FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=build/libs/your-app.jar
COPY ${JAR_FILE} app.jar

# spring.profiles.active를 prod로 설정
ENV SPRING_PROFILES_ACTIVE=prod

# JAR 파일을 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
