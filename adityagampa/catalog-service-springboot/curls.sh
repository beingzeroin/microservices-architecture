#!/bin/bash

echo -e "\n---- GET: /api/v1/catalog/brands ----"

curl -X GET "http://192.168.99.100:8088/api/v1/catalog/brands?pageSize=2&pageIndex=1" -H "accept: */*" -H "Content-Type: application/json" 

echo -e "\n---- GET: /api/v1/catalog/types ----"

curl -X GET "http://192.168.99.100:8088/api/v1/catalog/types?pageSize=2&pageIndex=1" -H "accept: */*" -H "Content-Type: application/json"

echo -e "\n---- GET: /api/v1/catalog/items ----"

curl -X GET "http://192.168.99.100:8088/api/v1/catalog/items?pageSize=2&pageIndex=1" -H "accept: */*" -H "Content-Type: application/json"

echo -e "\n---- GET: /api/v1/catalog/items/id ----"

curl -X GET "http://192.168.99.100:8088/api/v1/catalog/items/1" -H "accept: */*" -H "Content-Type: application/json"

echo -e "\n---- POST: /api/v1/catalog/items ----"

curl -X POST "http://192.168.99.100:8088/api/v1/catalog/items" -H "accept: */*" -H "Content-Type: application/json" -d "{\"availableStock\":10,\"catalogBrand\":{\"brand\":\"string\",\"id\":1},\"catalogType\":{\"id\":1,\"type\":\"string\"},\"deleted\":false,\"description\":\"string\",\"id\":3,\"isDeleted\":false,\"maxstockThreshold\":10,\"name\":\"string\",\"pictureFileName\":\"string\",\"pictureUri\":\"string\",\"price\":10,\"restockThreshold\":10}"

echo -e "\n---- PUT: /api/v1/catalog/items ----"

curl -X PUT "http://192.168.99.100:8088/api/v1/catalog/items" -H "accept: */*" -H "Content-Type: application/json" -d "{\"availableStock\":10,\"catalogBrand\":{\"brand\":\"string\",\"id\":1},\"catalogType\":{\"id\":1,\"type\":\"string\"},\"deleted\":false,\"description\":\"string\",\"id\":3,\"isDeleted\":false,\"maxstockThreshold\":10,\"name\":\"string\",\"pictureFileName\":\"string\",\"pictureUri\":\"string\",\"price\":15,\"restockThreshold\":10}"

echo -e "\n---- DELETE: /api/v1/catalog/items/id ----"

curl -X DELETE "http://192.168.99.100:8088/api/v1/catalog/items/1" -H "accept: */*" -H "Content-Type: application/json"

read var