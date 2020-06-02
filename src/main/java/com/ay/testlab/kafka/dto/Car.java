package com.ay.testlab.kafka.dto;


import java.io.Serializable;


public class Car {

    private String color;
    private Integer countDoors;
    private Integer horses;

    public Car(){}

    public Car(String color, Integer countDoors, Integer horses) {
        this.color = color;
        this.countDoors = countDoors;
        this.horses = horses;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCountDoors() {
        return countDoors;
    }

    public void setCountDoors(Integer countDoors) {
        this.countDoors = countDoors;
    }

    public Integer getHorses() {
        return horses;
    }

    public void setHorses(Integer horses) {
        this.horses = horses;
    }


}
