package com.adalbertosn.uploadfilewebclient;

import com.adalbertosn.uploadfilewebclient.api.contoller.UploadController;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Properties;

@SpringBootApplication

public class UploadFileWebClientApplication {
    private Logger log = LoggerFactory.getLogger(UploadFileWebClientApplication.class);
    public static void main(String[] args) throws IOException {
        //Properties properties = new Properties();
        //properties.load(new BufferedReader(new FileReader("application.properties")));
        //properties.load(UploadFileWebClientApplication.class.getResourceAsStream("/com.adalbertosn.uploadfilewebclient/application.properties"));
        SpringApplication.run(UploadFileWebClientApplication.class, args);
        //System.out.println("contato.disco.raiz = " + properties.getProperty("contacto_disco_rais", "ES"));
        System.out.println("Ejecutando....");
    }

}
