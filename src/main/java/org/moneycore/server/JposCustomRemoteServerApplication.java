package org.moneycore.server.service;

import org.jpos.q2.Q2;
import org.moneycore.server.controller.ServerController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = ServerController.class)
public class JposCustomRemoteServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JposCustomRemoteServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Q2 q2 = new Q2();
        q2.start();
    }
}
