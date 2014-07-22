package com.guava.eventbus.adapter;

import com.google.common.eventbus.Subscribe;
import com.outbidme.configuration.eventbus.IEventListener;;

public class EventListenerAdapter {
    private IEventListener wrappedListener;

    public EventListenerAdapter(IEventListener wrappedListener) {
        this.wrappedListener = wrappedListener;
    }

    @Subscribe
    public void handleEvent(Object message){
        wrappedListener.handle(message);
    }
}
