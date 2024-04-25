
# DistributedSystems-JMS
**Author:** Vincenzo Barbato
**Date:** 03/2024

This project simulates a distributed shop application based on JMS.

## Documentation

Javadoc documentation can be found in the folder: **JMS/javadoc**
To view the project documentation on the open browser click on: **index.html**

## Setup environment

To run ***.jar** files you needJDK Development Kit 21.

To manage project you need JavaIDE (we use eclipse with javaSE-17).

Download apache-activemq-6.0.1 on [ActiveMQ](https://activemq.apache.org/components/classic/documentation/download-archives)

If you use eclipse IDE :

 - Go to: project->Build Path-> Configure build path
	In classpath add external jar:
	- Go to folder: apache-activemq-6.0.1
	   import activemq-6.0.1.jar
	- Go to folder: lib/optional
	   import all file with **log4j** in the name

The project use port **61616** therefore is necessary keep free it or change it.

## Run application with *.jar files

This is a simulation of distributed shop application based on 1 server and 3 clients.

First one we need to open 5 terminal in the folder where there are ***.jar** files.

In the first terminal we run the broker:
```
java -jar Broker.jar
```

In the second terminal we run the broker:
```
java -jar Server.jar
```

In the others 3 terminal we run the clients:

```
java -jar Client.jar
```
