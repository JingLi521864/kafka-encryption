package edu.nwmissouri.ncd;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;


/**
 * Created by sunilpatil on 12/28/15. Modified by Denise Case on 10/29/2019.
 */
public class CustomProducer {
  private static Scanner in;

  public static void main(String[] argv) throws Exception {
    if (argv.length != 1) {
      System.err.println("Please specify 1 parameter (the name of the topic)");
      System.exit(-1);
    }
    String topicName = argv[0];
    in = new Scanner(System.in);
    System.out.println("Thank you for providing the topic " + topicName + "\n");
    System.out.println("Enter message (type exit to quit).\n");

    // Configure the Producer
    Properties configProperties = new Properties();
    configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.ByteArraySerializer");
    configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer");
    System.out.println("The configuration properties are: " + configProperties.toString());
    System.out.println("\nWill use this configuration to create a producer.\n");

    org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);

    // Custom Producer

    String line = in.nextLine();
    while (!line.equals("exit")) {
      ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, encryption(getSHA(line)));   //call encryption method
      producer.send(rec);
      line = in.nextLine();
    }

    in.close();
    producer.close();

  }

  private static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  

        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 

  private static String encryption(byte[] hash) {
   BigInteger number = new BigInteger(1, hash);     // Convert byte array into signum representation  

     StringBuilder hexString = new StringBuilder(number.toString(16));     // Convert message digest into hex value  

   while (hexString.length() < 32)     // Pad with leading zeros 

   {  
       hexString.insert(0, '0');  
   }  

   return hexString.toString();  
  }
}

