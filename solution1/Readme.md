# Solution 1

[Upload Service] --> [NATS Server] --> [Alerting Service]

## Prerequsites
Targes system should have
* Java 11 or newer.
* Apache maven for convinience
* Docker 
* and latest Docker compose (https://docs.docker.com/compose/install/

## How to use
On the first start use 

## Working Dir
The description of build steps assumes current working directory (solution1)

### Buils
The very first action is to buid everything. 

```
build.sh

```
When Prerequsites matched it will compile/tes two projects (upload-service and alert-service) and produece dockerfiles
### Run localy

To run 3 Component docker-compose configuration is used.
Having docker-compose installed
```
docker-compose up -d

```
will start all 3 services.

### Testing 

Having runnig services, files can be uploaded with cURL like:
```
curl --form file=@statements3.csv http://localhost:8080/upload
```

