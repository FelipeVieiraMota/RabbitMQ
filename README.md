#### To run this project be sure to use Java 17

#### Use this curl to send a message

curl --location 'http://localhost:8081/orders' \
--header 'Content-Type: application/json' \
--data '{
"customerName": "John Doe",
"product": "Laptop",
"quantity": 1,
"status": "CREATED"
}'

#### Use this curl to retrieve all created data

curl --location 'http://localhost:8082/deliveries'
