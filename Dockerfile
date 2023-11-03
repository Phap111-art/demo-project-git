# Sử dụng hình ảnh cơ sở có chứa JDK 17
FROM openjdk:21

# Sao chép file JAR của ứng dụng vào container
ADD jar/mystore.jar mystore.jar

EXPOSE 8080
# Lệnh chạy ứng dụng khi khởi chạy container
CMD ["java", "-jar", "mystore.jar"]