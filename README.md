# spring-kafka-demo
Spring Kafka Producer and Consumer demo for:

- String messages
- Json messages

## Requirements
- Zookeper running on default port **2181**
- Kafka server running on default port **9092**

_Tutorial for windows: https://dzone.com/articles/running-apache-kafka-on-windows-os_

## Endpoints:

```
curl -L -X GET 'http://localhost:8081/message/hello'
```

```
curl -L -X POST 'http://localhost:8081/orders' \
-H 'Content-Type: application/json' \
--data-raw '{
	"id": "newOrderId",
	"status": "CREATED",
	"price": 125.50
}'
```