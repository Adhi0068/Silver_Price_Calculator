package com.silver.controller;

import com.silver.service.silverService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class silverController {
    // http://localhost:8080/index.html

    private silverService silverService;

    public silverController(silverService silverService) {
        this.silverService = silverService;
    }

    @PostMapping("/setprice")
    public String setPrice(@RequestParam double price) {
        if (price < 0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        silverService.setSilverPrice(price);
        return "Silver price updated to ₹ " + price;
    }

    @GetMapping("/currentprice")
    public Map<String, Object> getCurrentPrice() {
        Map<String, Object> response = new HashMap<>();
        response.put("price", silverService.getSilverPrice());
        response.put("lastUpdated", silverService.getLastUpdated());
        return response;
    }

    // ---------- CALCULATE ----------
    @GetMapping("/calculate")
    public double calculate(@RequestParam double weight) {

        double silverPrice = silverService.getSilverPrice();

        if (silverPrice <= 0) {
            throw new RuntimeException("Please set silver price first");
        }
        if (weight < 0){
            throw new RuntimeException("Weight cannot be negative");
        }

        if (weight < 8) {
            return silverService.smallItems(silverPrice,weight);
        } else if (weight < 10) {
            return silverService.from8To10GramsItem(silverPrice,weight);
        } else if (weight < 30) {
            return silverService.from10To30GramsItem(silverPrice, weight);
        } else if (weight < 40) {
            return silverService.from30To40GramsItem(silverPrice, weight);
        } else if (weight < 50) {
            return silverService.from40To50GramsItem(silverPrice, weight);
        } else if (weight < 70) {
            return silverService.from50To70GramsItem(silverPrice, weight);
        } else {
            return silverService.itemOver70Grams(silverPrice, weight);
        }
    }
}
