#!/bin/sh

echo "[Information] clean up the project target generation path"
echo ""

cd ..
mvn clean package -Dmaven.test.skip=true