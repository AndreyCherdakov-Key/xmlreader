package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
For purposes of this challenge, please see the attached example file (Request.xml).

The system must allow the following:

-Reading request files from file system folder every one hour       Done
-Duration and folder location can be changed                        How changed? When started application in the console or in the Applications.properties
-On memory database to hold the fresh data                          H2:mem


You need to provide:

-Rest API to present products by InsuredId as following:
--Result must be in JSON format                                                             Ok
--Result should include all products related to same InsuredId grouped by SourceCompany     Write Only one WS, result only list of products
-A scheduled process to read data each hour from folder


Technical requirements and guidelines:

Backend : Spring Boot
Submission must be a day before the interview.
Submission can be in github repository.
If you have any major concerns feel free to reach out
 */

@SpringBootApplication
@EnableScheduling
public class XMLReaderApp {
    public static void main(String[] args) {
        SpringApplication.run(XMLReaderApp.class, args);
    }
}
