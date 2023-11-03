# Sử dụng hình ảnh cơ sở có chứa JDK 11
FROM openjdk:21-jdk

# Thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR của ứng dụng vào container
COPY jar/mystore.jar mystore.jar

# Cổng mà ứng dụng lắng nghe
EXPOSE 8080

# Lệnh chạy ứng dụng khi khởi chạy container
CMD ["java", "-jar", "app.jar"]