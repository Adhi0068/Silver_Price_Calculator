package com.silver.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Component
public class silverPriceStore {

    private static final String FILE_NAME = "silver-price.txt";

    private double price = 0;
    private LocalDateTime lastUpdated;

    @PostConstruct
    public void loadFromFile() {
        try {
            Path path = Path.of(FILE_NAME);

            if (Files.exists(path)) {
                String data = Files.readString(path);
                String[] parts = data.split("\\|");

                price = Double.parseDouble(parts[0]);
                lastUpdated = LocalDateTime.parse(parts[1]);
            }
        } catch (Exception e) {
            price = 0;
            lastUpdated = null;
        }
    }

    public void save(double price) {
        this.price = price;
        this.lastUpdated = LocalDateTime.now();

        try {
            String data = price + "|" + lastUpdated;
            Files.writeString(Path.of(FILE_NAME), data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save silver price");
        }
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
}
