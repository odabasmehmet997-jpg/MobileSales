package org.greenrobot.eventbus;

public record SubscriberExceptionEvent(EventBus eventBus, Throwable throwable, Object causingEvent,
                                       Object causingSubscriber) {
}
