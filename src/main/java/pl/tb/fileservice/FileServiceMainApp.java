package pl.tb.fileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class FileServiceMainApp {

    public static void main(String[] args) {
        SpringApplication.run(FileServiceMainApp.class, args);
    }

}