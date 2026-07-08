package io.reactivex.internal.functions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.location.LocationRequestCompat;
import io.reactivex.Notification;
import io.reactivex.Scheduler;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.functions.*;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Timed;
import org.jetbrains.annotations.UnknownNullability;
import org.reactivestreams.Subscription;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public final class Functions {
    static final Function<Object, Object> IDENTITY = new Identity();
    public static final Runnable EMPTY_RUNNABLE = new EmptyRunnable();
    public static final Action EMPTY_ACTION = new EmptyAction();
    static final Consumer<Object> EMPTY_CONSUMER = new EmptyConsumer();
    public static final Consumer<Throwable> ERROR_CONSUMER = new ErrorConsumer();
    public static final Consumer<Throwable> ON_ERROR_MISSING = new OnErrorMissingConsumer();
    public static final LongConsumer EMPTY_LONG_CONSUMER = new EmptyLongConsumer();
    static final Predicate<Object> ALWAYS_TRUE = new TruePredicate();
    static final Predicate<Object> ALWAYS_FALSE = new FalsePredicate();
    static final Callable<Object> NULL_SUPPLIER = new NullCallable();
    static final Comparator<Object> NATURAL_COMPARATOR = new NaturalObjectComparator();
    public static final Consumer<Subscription> REQUEST_MAX = new MaxRequestSubscription();

    private Functions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T1, T2, R> Array2Func toFunction(final BiFunction<? super T1, ? super T2, ? extends R> biFunction) {
        ObjectHelper.requireNonNull(biFunction, "f is null");
        return new Array2Func(biFunction);
    }

    public static <T1, T2, T3, R> Array3Func toFunction(final Function3<T1, T2, T3, R> function3) {
        ObjectHelper.requireNonNull(function3, "f is null");
        return new Array3Func(function3);
    }

    public static <T1, T2, T3, T4, R> Array4Func toFunction(final Function4<T1, T2, T3, T4, R> function4) {
        ObjectHelper.requireNonNull(function4, "f is null");
        return new Array4Func(function4);
    }

    public static <T1, T2, T3, T4, T5, R> Array5Func toFunction(final Function5<T1, T2, T3, T4, T5, R> function5) {
        ObjectHelper.requireNonNull(function5, "f is null");
        return new Array5Func(function5);
    }

    public static <T1, T2, T3, T4, T5, T6, R> Array6Func toFunction(final Function6<T1, T2, T3, T4, T5, T6, R> function6) {
        ObjectHelper.requireNonNull(function6, "f is null");
        return new Array6Func(function6);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Array7Func toFunction(final Function7<T1, T2, T3, T4, T5, T6, T7, R> function7) {
        ObjectHelper.requireNonNull(function7, "f is null");
        return new Array7Func(function7);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Array8Func toFunction(final Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> function8) {
        ObjectHelper.requireNonNull(function8, "f is null");
        return new Array8Func(function8);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Array9Func toFunction(final Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> function9) {
        ObjectHelper.requireNonNull(function9, "f is null");
        return new Array9Func(function9);
    }

    public static <T> Function<T, T> identity() {
        return ( Function<T, T> ) Functions.IDENTITY;
    }

    public static <T> Consumer<T> emptyConsumer() {
        return ( Consumer<T> ) Functions.EMPTY_CONSUMER;
    }

    public static <T> Predicate<T> alwaysTrue() {
        return ( Predicate<T> ) Functions.ALWAYS_TRUE;
    }

    public static <T> Predicate<T> alwaysFalse() {
        return ( Predicate<T> ) Functions.ALWAYS_FALSE;
    }

    public static <T> Callable<T> nullSupplier() {
        return (Callable<T>) Functions.NULL_SUPPLIER;
    }

    public static <T> Comparator<T> naturalOrder() {
        return (Comparator<T>) Functions.NATURAL_COMPARATOR;
    }

    public static <T> Function<? super List<T>,?> listSorter(Comparator<? super T> comparator) {
        return null;
    }


    record JustValue<T, U>(U value) implements Callable<U>, Function<T, U> {

            public U call() throws Exception {
                return value;
            }
            public U apply(final Object t) throws Exception {
                return value;
            }
        }

    public static <T> Callable<T> justCallable(final T t) {
        return new JustValue(t);
    }

    public static <T, U> Function<T, U> justFunction(final U u) {
        return new JustValue(u);
    }

    record CastToClass<T, U>(Class<U> clazz) implements Function<T, U> {

            public U apply(final Object t) throws Exception {
                return clazz.cast(t);
            }
        }

    public static <T, U> Function<T, U> castFunction(final Class<U> cls) {
        return new CastToClass(cls);
    }

    record ArrayListCapacityCallable<T>(int capacity) implements Callable<List<T>> {

            public List<T> call() throws Exception {
                return new ArrayList(capacity);
            }
        }

    public static <T> Callable<List<T>> createArrayList(final int i2) {
        return new ArrayListCapacityCallable(i2);
    }

    record EqualsPredicate<T>(T value) implements Predicate<T> {

            public boolean test(final T t) throws Exception {
                return ObjectHelper.equals(t, value);
            }
        }

    public static <T> Predicate<T> equalsWith(final T t) {
        return new EqualsPredicate(t);
    }

    enum HashSetCallable implements Callable<Set<Object>> {
        INSTANCE;
        public Set<Object> call() throws Exception {
            return new HashSet();
        }
    }

    public static <T> HashSetCallable createHashSet() {
        return HashSetCallable.INSTANCE;
    }

    record NotificationOnNext<T>(Consumer<? super Notification<T>> onNotification) implements Consumer<T> {

            public void accept(final T t) throws Exception {
                onNotification.accept(Notification.createOnNext(t));
            }
        public void invoke(Object obj) {

        }
    }

    record NotificationOnError<T>(Consumer<? super Notification<T>> onNotification) implements Consumer<Throwable> {

            public void accept(final Throwable th) throws Exception {
                onNotification.accept(Notification.createOnError(th));
            }

        public void invoke(Object obj) {

        }
    }

    record NotificationOnComplete<T>(Consumer<? super Notification<T>> onNotification) implements Action {

            public void run() throws Exception {
                onNotification.accept(Notification.createOnComplete());
            }
        }

    public static <T> Consumer<T> notificationOnNext(final Consumer<? super Notification<T>> consumer) {
        return new NotificationOnNext(consumer);
    }

    public static <T> Consumer<Throwable> notificationOnError(final Consumer<? super Notification<T>> consumer) {
        return new NotificationOnError(consumer);
    }

    public static <T> Action notificationOnComplete(final Consumer<? super Notification<T>> consumer) {
        return new NotificationOnComplete(consumer);
    }

    record ActionConsumer<T>(Action action) implements Consumer<T> {

            public void accept(final T t) throws Exception {
                action.run();
            }

        public void invoke(Object obj) {

        }
    }

    public static <T> Consumer<T> actionConsumer(final Action action) {
        return new ActionConsumer(action);
    }

    record ClassFilter<T, U>(Class<U> clazz) implements Predicate<T> {

            public boolean test(final T t) throws Exception {
                return clazz.isInstance(t);
            }
        }

    public static <T, U> Predicate<T> isInstanceOf(final Class<U> cls) {
        return new ClassFilter(cls);
    }

    record BooleanSupplierPredicateReverse<T>(BooleanSupplier supplier) implements Predicate<T> {

            public boolean test(final T t) throws Exception {
                return !supplier.getAsBoolean();
            }
        }

    public static <T> Predicate<T> predicateReverseFor(final BooleanSupplier booleanSupplier) {
        return new BooleanSupplierPredicateReverse(booleanSupplier);
    }

    record TimestampFunction<T>(TimeUnit unit, Scheduler scheduler) implements Function<T, Timed<T>> {

        public boolean equals(@Nullable Object obj) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @NonNull
        @Override
        public String toString() {
            return "";
        }

        @Override
        public Timed<T> apply(Object t) throws Exception {
            return null;
        }
    }

    public static <T> Function<T, Timed<T>> timestampWith(final TimeUnit timeUnit, final Scheduler scheduler) {
        return new TimestampFunction(timeUnit, scheduler);
    }

    static final class ToMapKeySelector<K, T> implements BiConsumer<Map<K, T>, T> {
        private final Function<? super T, ? extends K> keySelector;

        ToMapKeySelector(final Function<? super T, ? extends K> function) {
            keySelector = function;
        }

        public void accept(final @UnknownNullability ? super U map, final Object t) throws Exception {
            map.put(keySelector.apply(t), t);
        }
    }

    public static <T, K> BiConsumer<Map<K, T>, T> toMapKeySelector(final Function<? super T, ? extends K> function) {
        return new ToMapKeySelector(function);
    }

    static final class ToMapKeyValueSelector<K, V, T> implements BiConsumer<Map<K, V>, T> {
        private final Function<? super T, ? extends K> keySelector;
        private final Function<? super T, ? extends V> valueSelector;

        ToMapKeyValueSelector(final Function<? super T, ? extends V> function, final Function<? super T, ? extends K> function2) {
            valueSelector = function;
            keySelector = function2;
        }

        public void accept(final @UnknownNullability ? super U map, final Object t) throws Exception {
            map.put(keySelector.apply(t), valueSelector.apply(t));
        }
    }

    public static <T, K, V> BiConsumer<Map<K, V>, T> toMapKeyValueSelector(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2) {
        return new ToMapKeyValueSelector(function2, function);
    }

    static final class ToMultimapKeyValueSelector<K, V, T> implements BiConsumer<Map<K, Collection<V>>, T> {
        private final Function<? super K, ? extends Collection<? super V>> collectionFactory;
        private final Function<? super T, ? extends K> keySelector;
        private final Function<? super T, ? extends V> valueSelector;


        ToMultimapKeyValueSelector(final Function<? super K, ? extends Collection<? super V>> function, final Function<? super T, ? extends V> function2, final Function<? super T, ? extends K> function3) {
            collectionFactory = function;
            valueSelector = function2;
            keySelector = function3;
        }

        public void accept(final @UnknownNullability ? super U map, final Object t) throws Exception {
            final K apply = keySelector.apply(t);
            Collection<? super V> collection = map.get(apply);
            if (null == collection) {
                collection = collectionFactory.apply(apply);
                map.put(apply, ( Collection<V> ) collection);
            }
            collection.add(valueSelector.apply(t));
        }
    }

    public static <T, K, V> BiConsumer<Map<K, Collection<V>>, T> toMultimapKeyValueSelector(final Function<? super T, ? extends K> function, final Function<? super T, ? extends V> function2, final Function<? super K, ? extends Collection<? super V>> function3) {
        return new ToMultimapKeyValueSelector(function3, function2, function);
    }

    enum NaturalComparator implements Comparator<Object> {
        INSTANCE;

        public int compare(final Object obj, final Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    public static <T> Comparator<T> naturalComparator() {
        return ( Comparator<T> ) NaturalComparator.INSTANCE;
    }


    static final class Identity implements Function<Object, Object> {
        public Object apply(final Object obj) {
            return obj;
        }

        Identity() {
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    static final class EmptyRunnable implements Runnable {
        public void run() {
        }

        EmptyRunnable() {
        }

        public String toString() {
            return "EmptyRunnable";
        }
    }

    static final class EmptyAction implements Action {
        public void run() {
        }

        EmptyAction() {
        }

        public String toString() {
            return "EmptyAction";
        }
    }

    static final class EmptyConsumer implements Consumer<Object> {
        public void accept(final Object obj) {
        }
        public Object invoke(Object obj) {

            return obj;
        }

        EmptyConsumer() {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    static final class ErrorConsumer implements Consumer<Throwable> {
        ErrorConsumer() {
        }
        public void accept(final Throwable th) {
            RxJavaPlugins.onError(th);
        }

        public Object invoke(Object obj) {

            return obj;
        }
    }

    static final class OnErrorMissingConsumer implements Consumer<Throwable> {
        OnErrorMissingConsumer() {
        }
        public void accept(final Throwable th) {
            RxJavaPlugins.onError(new OnErrorNotImplementedException(th));
        }

        public Object invoke(Object obj) {

            return obj;
        }
    }

    static final class EmptyLongConsumer implements LongConsumer {
        EmptyLongConsumer() {
        }
    }

    static final class TruePredicate implements Predicate<Object> {
        public boolean test(final Object obj) {
            return true;
        }

        TruePredicate() {
        }
    }

    static final class FalsePredicate implements Predicate<Object> {
        public boolean test(final Object obj) {
            return false;
        }

        FalsePredicate() {
        }
    }

    static final class NullCallable implements Callable<Object> {
        public Object call() {
            return null;
        }

        NullCallable() {
        }
    }

    static final class NaturalObjectComparator implements Comparator<Object>, Serializable {
        NaturalObjectComparator() {
        }
        public int compare(final Object obj, final Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    static final class MaxRequestSubscription implements Consumer<Subscription> {
        MaxRequestSubscription() {
        }

        public void accept(final Subscription subscription) throws Exception {
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }

        public Object invoke(Object obj) {

            return obj;
        }
    }
}
