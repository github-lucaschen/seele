#!/bin/sh

cp -v ../sql/*.sql ./mysql/initdb
cp -v ../seele-gateway/target/seele-gateway.jar ./seele/gateway/jar
cp -v ../seele-service/seele-base/seele-base-service/target/seele-base-service.jar ./seele/service/base/jar