package com.example.wdm.stock;

import com.sun.jdi.IntegerType;

import java.util.concurrent.Callable;

public class CallActor implements Callable {
    private String actorId;
    private StockActor actor;
    private Integer type;
    private Integer number;
    private Double price;

    public CallActor(String actorId, StockActor actor, Integer type){
        this.actorId = actorId;
        this.actor  = actor;
        this.type = type;
    }
    public CallActor(String actorId, StockActor actor, Integer type, Integer number){
        this.actorId = actorId;
        this.actor = actor;
        this.type = type;
        this.number = number;
    }

    public CallActor(String actorId, StockActor actor, Integer type, Double price){
        this.actorId = actorId;
        this.actor = actor;
        this.type = type;
        this.price = price;
    }


    @Override
    public Object call() throws Exception {
        actor.registerReminder();
        String result = "";

        System.out.println("callActor");
        switch (type) {
            case 1: {
                result = actor.createItem(price).block();
                break;
            }
            case 2:{
                result = actor.findItem().block().toString();
                break;
            }
            case 3:{
                result = actor.subtractStock(number).block().toString();
                break;
            }
            case 4:{
                System.out.println("3");
                result = actor.addStock(number).block().toString();
                System.out.println("4");
                break;
            }
        }
        return result;
    }
}
