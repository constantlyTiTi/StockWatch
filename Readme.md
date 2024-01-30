## Introduction
Developed a high-performance backend system using Spring Boot integrated with Apache Kafka for enhanced real-time processing capabilities.

Established and configured an Apache Kafka environment seamlessly using Docker containers, ensuring a robust and scalable foundation for data processing.

Constructed a dynamic frontend utilizing Next.js, Node.js, and WebSocket technologies to create an interactive and responsive user interface.

Implemented real-time data retrieval from third-party APIs, enhancing the application's ability to provide up-to-the-minute information for an improved user experience.

## Limitation
1. There are limitations of API per second since it is using free real time data API. Right now, only 8 Stocks are supported.
2. The Company news can be retrieved from search box by Symbol (retrieve last 5 months news)


## Run locally
### back-end:
1. establish kafka environment by docker file, setup zookeeper, schema-registry, kafka broker:<br>
 cd Backend/docker -> run "docker compose up" (if your docker is v2)
2. go to root folder, the java version is 17<br>
 mvn install -> run the project

### front-end:
1. cd bff folder run "node server.js"
2. cd root folder run "npm install" to install all dependencies -> "npm run dev"
