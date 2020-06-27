#!/bin/bash

echo ---- POST: /api/v1/register ----

curl -X POST "http://192.168.99.100:8089/api/v1/register" -H "accept: */*" -H "Content-Type: application/json" -d "{\"email\":\"aemail@gmail.com\", \"password\":\"apass\", \"username\":\"auser\"}"

echo ---- POST: /api/v1/login ----

curl -X POST "http://192.168.99.100:8089/api/v1/login" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"apass\", \"username\": \"auser\"}"


read var