package com.alex.develop.SpringRestApiExample;

import com.alex.develop.SpringRestApiExample.grpc.GreetingServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        runGrpcServer();
    }

    private static void runGrpcServer() {
        try {
            final GreetingServer server = new GreetingServer();
            server.start();
            server.blockUntilShutdown();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
