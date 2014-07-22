package com.guava.eventbus.adapter;

import com.google.common.eventbus.EventBus;
import com.outbidme.configuration.eventbus.IEventBus;
import com.outbidme.configuration.eventbus.IEventListener;

public class EventBusAdapter implements IEventBus {

    private EventBus eventBus = null;

    public EventBusAdapter() {
        this.eventBus = new EventBus();
    }

    @Override
    public void post(Object message) {
        eventBus.post(message);
    }

    @Override
    public void register(IEventListener listener) {
        final EventListenerAdapter listenerWrapper = new EventListenerAdapter(listener);
        eventBus.register(listenerWrapper);
    }

}
