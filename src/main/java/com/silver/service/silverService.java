package com.silver.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class silverService {

    private final silverPriceStore priceStore;

    public silverService(silverPriceStore priceStore) {
        this.priceStore = priceStore;
    }

    // ---- PRICE ACCESS ----

    public void setSilverPrice(double price) {
        priceStore.save(price);
    }

    public double getSilverPrice() {
        return priceStore.getPrice();
    }

    public LocalDateTime getLastUpdated() {
        return priceStore.getLastUpdated();
    }


    //---------- CALCULATE ----------
    public double smallItems(double silverPrice, double weight){
        //for every 1 gram, we will charge 65
//        double price =  (silverPrice * weight) + (weight * 65);
        double price =  (silverPrice * weight) + 65;
        return price;
    }
    public double from8To10GramsItem(double silverPrice, double weight){
        //for every 1 gram, we will add 25
//        double price = (silverPrice * weight) + (weight * 25);
        double price = (silverPrice * weight) + 25;
        return price;
    }
    public double from10To30GramsItem(double silverPrice, double weight){
        // including making charge ~ 550
        double price = (silverPrice * weight) + 500;
        return price;
    }
    public double from30To40GramsItem(double silverPrice, double weight){
        //making charge per gram (~20 per gram)
        double price = (silverPrice * weight) + (weight * 20);
        return price;
    }
    public double from40To50GramsItem(double silverPrice, double weight){
        //making charge per gram (~18 per gram)
        double price = (silverPrice * weight) + (weight * 18);
        return price;
    }
    public double from50To70GramsItem(double silverPrice, double weight){
        //making charge per gram (~15 per gram)
        double price = (silverPrice * weight) + (weight * 15);
        return price;
    }
    public double itemOver70Grams(double silverPrice, double weight){
        //making charge per gram (~12 per gram)
        double price = (silverPrice * weight) + (weight * 12);
        return price;
    }
}
