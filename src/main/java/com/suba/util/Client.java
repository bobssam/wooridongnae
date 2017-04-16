package com.suba.util;

import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.platform.Verticle;

public class Client extends Verticle {

    @Override
    public void start() {
        EventBus eventBus = vertx.eventBus();
        for(int i = 0 ; i < 100 ; i++) {
        	System.out.println(i);
            eventBus.publish("hello", "message " + i);
        }
    }
}
