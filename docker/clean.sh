#!/bin/sh

echo "clean mysql: "
rm -rfv ./mysql/data
rm -rfv ./mysql/log
rm -rfv ./mysql/initdb/*.sql
echo "clean redis: "
rm -rfv ./redis/data
rm -rfv ./redis/log
echo "clean nacos: "
rm -rfv ./nacos/logs
echo "clean nginx: "
rm -rfv ./nginx/logs
rm -rfv ./nginx/html/*