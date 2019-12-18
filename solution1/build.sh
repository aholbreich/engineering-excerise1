#/bin/bash
#
# See Reamde.md for prerequites

echo "Building Alerting Service"
mvn -f ./alerting-service/ clean install

echo "Building Upload Service"
mvn -f ./upload-service/ clean install
