package org.greenrobot.eventbus;

final class Subscription {
    volatile boolean active = true;
    final Object subscriber;
    final SubscriberMethod subscriberMethod;
    Subscription(final Object obj, final SubscriberMethod subscriberMethod) {
        subscriber = obj;
        this.subscriberMethod = subscriberMethod;
    }
    public boolean equals(final Object obj) {
        if (!(obj instanceof Subscription subscription)) {
            return false;
        }
        return subscriber == subscription.subscriber && subscriberMethod.equals(subscription.subscriberMethod);
    }
    public int hashCode() {
        return subscriber.hashCode() + subscriberMethod.methodString.hashCode();
    }
}
