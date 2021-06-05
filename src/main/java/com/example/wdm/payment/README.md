dapr run --components-path ./components/actors --app-id demoactorservice --app-port 3000 -- java -jar target/spring-boot-0.0.1-SNAPSHOT.jar com.example.wdm.payment.PaymentActorService -p 3000

dapr run --components-path ./components/actors --app-id demoactorclient -- java -jar target/spring-boot-0.0.1-SNAPSHOT.jar com.example.wdm.WdmApplication

----------------------------------------------

curl -X POST 127.0.0.1:8080/payment/create_user

curl -X GET 127.0.0.1:8080/payment/find_user/{user_id}

curl -X POST 127.0.0.1:8080/payment/add_funds/{user_id}/{amount}

curl -X POST 127.0.0.1:8080/payment/pay/{user_id}/{order_id}/{amount}