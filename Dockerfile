FROM openjdk:17
EXPOSE 8080
ADD target/springboot-blog-rest-apis.jar springboot-blog-rest-apis.jar
ENTRYPOINT [ "java","-jar","springboot-blog-rest-apis.jar" ]