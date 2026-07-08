package org.greenrobot.eventbus;

public record NoSubscriberEvent(EventBus eventBus, Object originalEvent) {
}
