package com.alex.develop.SpringRestApiExample;

import com.alex.develop.SpringRestApiExample.grpc.UserDatabaseServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        runUserDatabaseServer();
    }

    private static void runUserDatabaseServer() {
        try {
            final UserDatabaseServer server = new UserDatabaseServer();
            server.start();
            server.blockUntilShutdown();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
