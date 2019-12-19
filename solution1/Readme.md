# Solution 1

[Upload Service] --> [NATS Server] --> [Alerting Service]

## Prerequsites

* Java 11 or newer.
* Apache maven (worked with 3.5.x)
* Docker (worked with 19.03)
* and Docker compose (https://docs.docker.com/compose/install/

## How to use
On the first start use 

## Working Dir
The description of build steps assumes current working directory (/solution1)

### Buils
The very first action is to buid everything. 

```
build.sh

```
When prerequsites are matched it will compile two projects (upload-service and alert-service) and produce docker images.
### Run localy

To run 3 Component docker-compose configuration is used.
Having docker-compose installed
```
docker-compose up -d

```
will start all 3 services.

### Testing 

Having runnig services files can be uploaded with cURL command like:
```
# assuming statements file in the current directory
curl --form file=@statements.csv http://localhost:8080/upload
```
Logs of the servives can be checked with `docker logs` or `docker-compose logs` see documentation

