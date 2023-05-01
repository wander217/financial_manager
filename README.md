# Financial Project
## Đây là project phần view thông tin thẻ tín dụng
### Database
~~~
    MS SQL Server
    Để thiết lập database mới chỉnh sửa thông tin ở src/main/java/dao/MSSQLServerConfig.java
    Sau đó thực hiện chạy file src/main/java/test/DBMaker.java để tạo Schema và các bảng
~~~
### Java Environment
~~~
    Java 8
    Tombcat 9.0.71
    JDBC jre8
~~~
### Chạy project
~~~
    1. Khởi động server
    2. Khởi chạy src/main/java/controller/CardInfoServlet.java
~~~
Khi đó trang web sẽ ở địa chỉ
~~~
    http:://localhost:8080/financial_manager/card_info
~~~
