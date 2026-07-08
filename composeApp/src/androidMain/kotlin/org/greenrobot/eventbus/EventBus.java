package org.greenrobot.eventbus;

import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

public class EventBus {
    static volatile EventBus defaultInstance;
    private final AsyncPoster asyncPoster;
    private final BackgroundPoster backgroundPoster;
    private final ThreadLocal<PostingThreadState> currentPostingThreadState;
    private final boolean eventInheritance;
    private final ExecutorService executorService;
    private final int indexCount;
    private final boolean logNoSubscriberMessages;
    private final boolean logSubscriberExceptions;
    private final Logger logger;
    private final Poster mainThreadPoster;
    private final MainThreadSupport mainThreadSupport;
    private final boolean sendNoSubscriberEvent;
    private final boolean sendSubscriberExceptionEvent;
    private final Map<Class<?>, Object> stickyEvents;
    private final SubscriberMethodFinder subscriberMethodFinder;
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType;
    private final boolean throwSubscriberException;
    private final Map<Object, List<Class<?>>> typesBySubscriber;
    private static final EventBusBuilder DEFAULT_BUILDER = new EventBusBuilder();
    private static final Map<Class<?>, List<Class<?>>> eventTypesCache = new HashMap();
    class C31391 extends ThreadLocal<PostingThreadState> {
        C31391() {
        }

        protected PostingThreadState initialValue() {
            return new PostingThreadState();
        }
    }
    public static EventBus getDefault() {
        EventBus eventBus = EventBus.defaultInstance;
        if (null == eventBus) {
            synchronized (EventBus.class) {
                eventBus = defaultInstance;
                if (eventBus == null) {
                    eventBus = new EventBus();
                    defaultInstance = eventBus;
                }
            }
        }
        return eventBus;
    }
    public EventBus() {
        this(EventBus.DEFAULT_BUILDER);
    }
    EventBus(final EventBusBuilder eventBusBuilder) {
        currentPostingThreadState = new ThreadLocal<PostingThreadState>() {
            void C31391() {
            }
            protected PostingThreadState initialValue() {
                return new PostingThreadState();
            }
        };
        logger = eventBusBuilder.getLogger();
        subscriptionsByEventType = new HashMap();
        typesBySubscriber = new HashMap();
        stickyEvents = new ConcurrentHashMap();
        final MainThreadSupport mainThreadSupport = eventBusBuilder.getMainThreadSupport();
        this.mainThreadSupport = mainThreadSupport;
        mainThreadPoster = null != mainThreadSupport ? mainThreadSupport.createPoster(this) : null;
        backgroundPoster = new BackgroundPoster(this);
        asyncPoster = new AsyncPoster(this);
        final List<SubscriberInfoIndex> list = eventBusBuilder.subscriberInfoIndexes;
        indexCount = null != list ? list.size() : 0;
        subscriberMethodFinder = new SubscriberMethodFinder(eventBusBuilder.subscriberInfoIndexes, eventBusBuilder.strictMethodVerification, eventBusBuilder.ignoreGeneratedIndex);
        logSubscriberExceptions = eventBusBuilder.logSubscriberExceptions;
        logNoSubscriberMessages = eventBusBuilder.logNoSubscriberMessages;
        sendSubscriberExceptionEvent = eventBusBuilder.sendSubscriberExceptionEvent;
        sendNoSubscriberEvent = eventBusBuilder.sendNoSubscriberEvent;
        throwSubscriberException = eventBusBuilder.throwSubscriberException;
        eventInheritance = eventBusBuilder.eventInheritance;
        executorService = eventBusBuilder.executorService;
    }
    public void register(final Object obj) {
        final List<SubscriberMethod> findSubscriberMethods = subscriberMethodFinder.findSubscriberMethods(obj.getClass());
        synchronized (this) {
            try {
                final Iterator<SubscriberMethod> it = findSubscriberMethods.iterator();
                while (it.hasNext()) {
                    this.subscribe(obj, it.next());
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
    private void subscribe(final Object obj, final SubscriberMethod subscriberMethod) {
        final Class<?> cls = subscriberMethod.eventType;
        final Subscription subscription = new Subscription(obj, subscriberMethod);
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = subscriptionsByEventType.get(cls);
        if (null == copyOnWriteArrayList) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            subscriptionsByEventType.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(subscription)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        }
        final int size = copyOnWriteArrayList.size();
        for (int i2 = 0; i2 <= size; i2++) {
            if (i2 == size || subscriberMethod.priority > copyOnWriteArrayList.get(i2).subscriberMethod.priority) {
                copyOnWriteArrayList.add(i2, subscription);
                break;
            }
        }
        List<Class<?>> list = typesBySubscriber.get(obj);
        if (null == list) {
            list = new ArrayList<>();
            typesBySubscriber.put(obj, list);
        }
        list.add(cls);
        if (subscriberMethod.sticky) {
            if (eventInheritance) {
                for (final Map.Entry<Class<?>, Object> entry : stickyEvents.entrySet()) {
                    if (cls.isAssignableFrom(entry.getKey())) {
                        this.checkPostStickyEventToSubscription(subscription, entry.getValue());
                    }
                }
                return;
            }
            this.checkPostStickyEventToSubscription(subscription, stickyEvents.get(cls));
        }
    }
    private void checkPostStickyEventToSubscription(final Subscription subscription, final Object obj) {
        if (null != obj) {
            this.postToSubscription(subscription, obj, this.isMainThread());
        }
    }
    private boolean isMainThread() {
        final MainThreadSupport mainThreadSupport = this.mainThreadSupport;
        return null == mainThreadSupport || mainThreadSupport.isMainThread();
    }
    private void unsubscribeByEventType(final Object obj, final Class<?> cls) {
        final CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = subscriptionsByEventType.get(cls);
        if (null != copyOnWriteArrayList) {
            int size = copyOnWriteArrayList.size();
            int i2 = 0;
            while (i2 < size) {
                final Subscription subscription = copyOnWriteArrayList.get(i2);
                if (subscription.subscriber == obj) {
                    subscription.active = false;
                    copyOnWriteArrayList.remove(i2);
                    i2--;
                    size--;
                }
                i2++;
            }
        }
    }
    public synchronized void unregister(final Object obj) {
        try {
            final List<Class<?>> list = typesBySubscriber.get(obj);
            if (null != list) {
                final Iterator<Class<?>> it = list.iterator();
                while (it.hasNext()) {
                    this.unsubscribeByEventType(obj, it.next());
                }
                typesBySubscriber.remove(obj);
            } else {
                logger.log(Level.WARNING, "Subscriber to unregister was not registered before: " + obj.getClass());
            }
        } catch (final Throwable th) {
            throw th;
        }
    }
    public void post(final Object obj) {
        final PostingThreadState postingThreadState = currentPostingThreadState.get();
        final List<Object> list = postingThreadState.eventQueue;
        list.add(obj);
        if (postingThreadState.isPosting) {
            return;
        }
        postingThreadState.isMainThread = this.isMainThread();
        postingThreadState.isPosting = true;
        if (postingThreadState.canceled) {
            throw new EventBusException("Internal error. Abort state was not reset");
        }
        while (true) {
            try {
                if (list.isEmpty()) {
                    return;
                } else {
                    this.postSingleEvent(list.remove(0), postingThreadState);
                }
            } finally {
                postingThreadState.isPosting = false;
                postingThreadState.isMainThread = false;
            }
        }
    }
    public void postSticky(final Object obj) {
        synchronized (stickyEvents) {
            stickyEvents.put(obj.getClass(), obj);
        }
        this.post(obj);
    }
    private void postSingleEvent(final Object obj, final PostingThreadState postingThreadState) throws Error {
        boolean postSingleEventForEventType;
        final Class<?> cls = obj.getClass();
        if (eventInheritance) {
            final List<Class<?>> lookupAllEventTypes = EventBus.lookupAllEventTypes(cls);
            final int size = lookupAllEventTypes.size();
            postSingleEventForEventType = false;
            for (int i2 = 0; i2 < size; i2++) {
                postSingleEventForEventType |= this.postSingleEventForEventType(obj, postingThreadState, lookupAllEventTypes.get(i2));
            }
        } else {
            postSingleEventForEventType = this.postSingleEventForEventType(obj, postingThreadState, cls);
        }
        if (postSingleEventForEventType) {
            return;
        }
        if (logNoSubscriberMessages) {
            logger.log(Level.FINE, "No subscribers registered for event " + cls);
        }
        if (!sendNoSubscriberEvent || NoSubscriberEvent.class == cls || SubscriberExceptionEvent.class == cls) {
            return;
        }
        this.post(new NoSubscriberEvent(this, obj));
    }
    private boolean postSingleEventForEventType(final Object obj, final PostingThreadState postingThreadState, final Class<?> cls) {
        final CopyOnWriteArrayList<Subscription> copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = subscriptionsByEventType.get(cls);
        }
        if (null == copyOnWriteArrayList || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        final Iterator<Subscription> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            final Subscription next = it.next();
            postingThreadState.event = obj;
            postingThreadState.subscription = next;
            try {
                this.postToSubscription(next, obj, postingThreadState.isMainThread);
                if (postingThreadState.canceled) {
                    return true;
                }
            } finally {
                postingThreadState.event = null;
                postingThreadState.subscription = null;
                postingThreadState.canceled = false;
            }
        }
        return true;
    }
    enum C31402 {
        ;
        static final int[] SwitchMaporggreenroboteventbusThreadMode;

        static {
            final int[] iArr = new int[ThreadMode.values().length];
            SwitchMaporggreenroboteventbusThreadMode = iArr;
            try {
                iArr[ThreadMode.POSTING.ordinal()] = 1;
            } catch (final NoSuchFieldError unused) {
            }
            try {
                C31402.SwitchMaporggreenroboteventbusThreadMode[ThreadMode.MAIN.ordinal()] = 2;
            } catch (final NoSuchFieldError unused2) {
            }
            try {
                C31402.SwitchMaporggreenroboteventbusThreadMode[ThreadMode.MAIN_ORDERED.ordinal()] = 3;
            } catch (final NoSuchFieldError unused3) {
            }
            try {
                C31402.SwitchMaporggreenroboteventbusThreadMode[ThreadMode.BACKGROUND.ordinal()] = 4;
            } catch (final NoSuchFieldError unused4) {
            }
            try {
                C31402.SwitchMaporggreenroboteventbusThreadMode[ThreadMode.ASYNC.ordinal()] = 5;
            } catch (final NoSuchFieldError unused5) {
            }
        }
    }
    private void postToSubscription(final Subscription subscription, final Object obj, final boolean z) {
        final int i2 = C31402.SwitchMaporggreenroboteventbusThreadMode[subscription.subscriberMethod.threadMode.ordinal()];
        if (1 == i2) {
            this.invokeSubscriber(subscription, obj);
            return;
        }
        if (2 == i2) {
            if (z) {
                this.invokeSubscriber(subscription, obj);
                return;
            } else {
                mainThreadPoster.enqueue(subscription, obj);
                return;
            }
        }
        if (3 == i2) {
            final Poster poster = mainThreadPoster;
            if (null != poster) {
                poster.enqueue(subscription, obj);
                return;
            } else {
                this.invokeSubscriber(subscription, obj);
                return;
            }
        }
        if (4 == i2) {
            if (z) {
                backgroundPoster.enqueue(subscription, obj);
                return;
            } else {
                this.invokeSubscriber(subscription, obj);
                return;
            }
        }
        if (5 == i2) {
            asyncPoster.enqueue(subscription, obj);
            return;
        }
        throw new IllegalStateException("Unknown thread mode: " + subscription.subscriberMethod.threadMode);
    }
    private static List<Class<?>> lookupAllEventTypes(final Class<?> cls) {
        List<Class<?>> list;
        final Map<Class<?>, List<Class<?>>> map = EventBus.eventTypesCache;
        synchronized (map) {
            try {
                list = map.get(cls);
                if (null == list) {
                    list = new ArrayList<>();
                    for (Class<?> cls2 = cls; null != cls2; cls2 = cls2.getSuperclass()) {
                        list.add(cls2);
                        EventBus.addInterfaces(list, cls2.getInterfaces());
                    }
                    EventBus.eventTypesCache.put(cls, list);
                }
            } catch (final Throwable th) {
                throw th;
            }
        }
        return list;
    }
    static void addInterfaces(final List<Class<?>> list, final Class<?>[] clsArr) {
        for (final Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                EventBus.addInterfaces(list, cls.getInterfaces());
            }
        }
    }
    void invokeSubscriber(final PendingPost pendingPost) {
        final Object obj = pendingPost.event;
        final Subscription subscription = pendingPost.subscription;
        PendingPost.releasePendingPost(pendingPost);
        if (subscription.active) {
            this.invokeSubscriber(subscription, obj);
        }
    }
    void invokeSubscriber(final Subscription subscription, final Object obj) {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, obj);
        } catch (final IllegalAccessException e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        } catch (final InvocationTargetException e3) {
            this.handleSubscriberException(subscription, obj, e3.getCause());
        }
    }
    private void handleSubscriberException(final Subscription subscription, final Object obj, final Throwable th) {
        if (obj instanceof SubscriberExceptionEvent subscriberExceptionEvent) {
            if (logSubscriberExceptions) {
                final Logger logger = this.logger;
                final Level level = Level.SEVERE;
                logger.log(level, "SubscriberExceptionEvent subscriber " + subscription.subscriber.getClass() + " threw an exception", th);
                this.logger.log(level, "Initial event " + subscriberExceptionEvent.causingEvent() + " caused exception in " + subscriberExceptionEvent.causingSubscriber(), subscriberExceptionEvent.throwable());
                return;
            }
            return;
        }
        if (throwSubscriberException) {
            throw new EventBusException("Invoking subscriber failed", th);
        }
        if (logSubscriberExceptions) {
            logger.log(Level.SEVERE, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + subscription.subscriber.getClass(), th);
        }
        if (sendSubscriberExceptionEvent) {
            this.post(new SubscriberExceptionEvent(this, th, obj, subscription.subscriber));
        }
    }
    static final class PostingThreadState {
        boolean canceled;
        Object event;
        final List<Object> eventQueue = new ArrayList();
        boolean isMainThread;
        boolean isPosting;
        Subscription subscription;

        PostingThreadState() {
        }
    }
    ExecutorService getExecutorService() {
        return executorService;
    }
    public Logger getLogger() {
        return logger;
    }
    public String toString() {
        return "EventBus[indexCount=" + indexCount + ", eventInheritance=" + eventInheritance + "]";
    }
}
