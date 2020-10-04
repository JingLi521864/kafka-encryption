# Kafka Messaging with Encryption
 
In this project, CustomProducer.java asks input from the user and encrypts it to pass to CustomConsumer.java. The encryption part is done in the producer.

## Tools Used
1. [Apache Kafka](https://kafka.apache.org/)
2. [Zookeeper](https://zookeeper.apache.org/)
3. [SHA-256 Hash code by Geeks for Geeks](https://www.geeksforgeeks.org/sha-256-hash-in-java/#:~:text=In%20Cryptography%2C%20SHA%20is%20cryptographic,security)

## Prerequisities

* OpenJDK or JDK (8 or up)
* Apache Zookeeper, installed and working
* Apache Kafka, installed and working

## Start Zookeeper Service

Start Zookeeper service using this command 
```Powershell
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties 
```
## Start Kafka Service

Start Kafka service using this command in C:\kafka_VERSION folder
```Powershell
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

## Create the Topic

Create a Kafka topic using this command 

```PowerShell
.\bin\windows\kafka-topics.bat --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --create --topic test
```

## Compile and Build the Fat Jar File

Open PowerShell as Administrator in the root project folder, compile the code using Maven and create an executable jar file. Generated artificacts can be found in the new 'target' folder.

```PowerShell
mvn clean compile assembly:single
```

## Start Consumer

Open PowerShell as Administrator in the root project folder, start the original consumer app using topic test and group1 with:

```PowerShell
java -cp target/kafka-encryption-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.ncd.CustomConsumer test group1
```

## Start Producer

Open a new PowerShell as Administrator in the root project folder, start the Producer app using topic test:

```PowerShell
java -cp target/kafka-encryption-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.ncd.CustomProducer test

java -cp target/kafka-encryption-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.ncd.CustomProducer1 test
```

## Test Communications

1. Type some messages for the Producer.
1. Verify the messages are output by the Consumer.
1. The working communication should look like this:
![Working Demo](https://raw.githubusercontent.com/spsaroj/kafka-encryption/master/workingsc.PNG)
![Working Demo of Producer1](https://raw.githubusercontent.com/spsaroj/kafka-encryption/master/customProducer1.PNG)

## Repository

- Source: https://github.com/spsaroj/kafka-encryption

## Collaborators

- [Sagar Tiwari](https://github.com/005sagar)
- [Saroj Paudel](https://github.com/spsaroj)

