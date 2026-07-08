package com.google.firebase.components;

import android.util.Log;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.dynamicloading.ComponentLoader;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import io.reactivex.internal.util.EndConsumerHelper;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Map.*;

public class ComponentRuntime extends AbstractComponentContainer implements ComponentLoader {
    private static final Provider<Set<Object>> EMPTY_PROVIDER = new Provider() {
        public Object get() {
            return Collections.emptySet();
        }
    };
    private final Map<Component<?>, Provider<?>> components;
    private final AtomicReference<Boolean> eagerComponentsInitializedWith;
    private final EventBus eventBus;
    private final Map<Class<?>, Provider<?>> lazyInstanceMap;
    private final Map<Class<?>, LazySet<?>> lazySetMap;
    private final List<Provider<ComponentRegistrar>> unprocessedRegistrarProviders;

    public static ComponentRegistrar lambdatoProviders1(final ComponentRegistrar componentRegistrar) {
        return componentRegistrar;
    }
    public   Object get(final Class cls) {
        return super.get(cls);
    }
    public  Set setOf(final Class cls) {
        return super.setOf(cls);
    }

    @Deprecated
    public ComponentRuntime(final Executor executor, final Iterable<ComponentRegistrar> iterable, final Component<?>... componentArr) {
        this(executor, ComponentRuntime.toProviders(iterable), Arrays.asList(componentArr));
    }

    public static Builder builder(final Executor executor) {
        return new Builder(executor);
    }

    private ComponentRuntime(final Executor executor, final Iterable<Provider<ComponentRegistrar>> iterable, final Collection<Component<?>> collection) {
        components = new HashMap();
        lazyInstanceMap = new HashMap();
        lazySetMap = new HashMap();
        eagerComponentsInitializedWith = new AtomicReference<>();
        final EventBus eventBus = new EventBus(executor);
        this.eventBus = eventBus;
        final ArrayList arrayList = new ArrayList();
        arrayList.add(Component.of(eventBus, EventBus.class, Subscriber.class, Publisher.class));
        arrayList.add(Component.of(this, ComponentLoader.class));
        for (final Component<?> component : collection) {
            if (null != component) {
                arrayList.add(component);
            }
        }
        unprocessedRegistrarProviders = ComponentRuntime.iterableToList(iterable);
      this.discoverComponents(arrayList);
    }

    private void discoverComponents(final List<Component<?>> list) {
        final ArrayList arrayList = new ArrayList();
        synchronized (this) {
            final Iterator<Provider<ComponentRegistrar>> it = unprocessedRegistrarProviders.iterator();
            while (it.hasNext()) {
                try {
                    final ComponentRegistrar componentRegistrar = it.next().get();
                    if (null != componentRegistrar) {
                        list.addAll(componentRegistrar.getComponents());
                        it.remove();
                    }
                } catch (final InvalidRegistrarException e2) {
                    it.remove();
                    Log.w("ComponentDiscovery", "Invalid component registrar.", e2);
                }
            }
            if (components.isEmpty()) {
                CycleDetector.detect(list);
            } else {
                final ArrayList arrayList2 = new ArrayList(components.keySet());
                arrayList2.addAll(list);
                CycleDetector.detect(arrayList2);
            }
            for (Component<?> component : list) {
                components.put(component, new Lazy(new Provider() {
                    public Object get() {
                        return this.f0.lambdaDiscoverComponents0(component);
                    }
                }));
            }
            arrayList.addAll(this.processInstanceComponents(list));
            arrayList.addAll(this.processSetComponents());
          this.processDependencies();
        }
        final Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((Runnable) it2.next()).run();
        }
      this.maybeInitializeEagerComponents();
    }
    public Object lambdadiscoverComponents0(final Component component) {
        return component.getFactory().create(new RestrictedComponentContainer(component, this));
    }

    private void maybeInitializeEagerComponents() {
        final Boolean bool = eagerComponentsInitializedWith.get();
        if (null != bool) {
          this.doInitializeEagerComponents(components, bool.booleanValue());
        }
    }

    private static Iterable<Provider<ComponentRegistrar>> toProviders(final Iterable<ComponentRegistrar> iterable) {
        final ArrayList arrayList = new ArrayList();
        for (ComponentRegistrar componentRegistrar : iterable) {
            arrayList.add(new Provider() {
                public Object get() {
                    return lambdatoProviders1(componentRegistrar);
                }
            });
        }
        return arrayList;
    }

    private static <T> List<T> iterableToList(final Iterable<T> iterable) {
        final ArrayList arrayList = new ArrayList();
        final Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    private List<Runnable> processInstanceComponents(final List<Component<?>> list) {
        final ArrayList arrayList = new ArrayList();
        for (final Component<?> component : list) {
            if (component.isValue()) {
                Provider<?> provider = components.get(component);
                for (final Class<?> cls : component.getProvidedInterfaces()) {
                    if (!lazyInstanceMap.containsKey(cls)) {
                        lazyInstanceMap.put(cls, provider);
                    } else {
                        OptionalProvider optionalProvider = (OptionalProvider) lazyInstanceMap.get(cls);
                        arrayList.add(new Runnable() {
                            public void run() {
                                optionalProvider.set(provider);
                            }
                        });
                    }
                }
            }
        }
        return arrayList;
    }

    private List<Runnable> processSetComponents() {
        final ArrayList arrayList = new ArrayList();
        final HashMap map = new HashMap();
        for (final Map.Entry<Component<?>, Provider<?>> entry : components.entrySet()) {
            final Component<?> key = entry.getKey();
            if (!key.isValue()) {
                final Provider<?> value = entry.getValue();
                for (final Class<? super Object> cls : key.getProvidedInterfaces()) {
                    if (!map.containsKey(cls)) {
                        map.put(cls, new HashSet());
                    }
                    ((Set) map.get(cls)).add(value);
                }
            }
        }
      map.forEach((key, value) -> {
        if (!lazySetMap.containsKey(key)) {
          lazySetMap.put((Class) key, LazySet.fromCollection((Collection) value));
        } else {
          LazySet<?> lazySet = lazySetMap.get(key);
          for (Object provider : (Set) value) {
            arrayList.add(new Runnable() {
              public void run() {
                lazySet.add(provider);
              }
            });
          }
        }
      });
        return arrayList;
    }
    public synchronized <T> Provider<T> getProvider(final Class<T> cls) {
        Preconditions.checkNotNull(cls, "Null interface requested.");
        return (Provider) lazyInstanceMap.get(cls);
    }
    public <T> Deferred<T> getDeferred(final Class<T> cls) {
        final Provider<T> provider = this.getProvider(cls);
        if (null == provider) {
            return OptionalProvider.empty();
        }
        if (provider instanceof OptionalProvider) {
            return (OptionalProvider) provider;
        }
        return OptionalProvider.of(provider);
    }
    public synchronized <T> Provider<Set<T>> setOfProvider(final Class<T> cls) {
        final LazySet<?> lazySet = lazySetMap.get(cls);
        if (null != lazySet) {
            return lazySet;
        }
        return (Provider<Set<T>>) ComponentRuntime.EMPTY_PROVIDER;
    }

    public void initializeEagerComponents(final boolean z) {
        final HashMap map;
        if (EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(eagerComponentsInitializedWith, null, Boolean.valueOf(z))) {
            synchronized (this) {
                map = new HashMap(components);
            }
          this.doInitializeEagerComponents(map, z);
        }
    }

    private void doInitializeEagerComponents(final Map<Component<?>, Provider<?>> map, final boolean z) {
        for (final Map.Entry<Component<?>, Provider<?>> entry : map.entrySet()) {
            final Component<?> key = entry.getKey();
            final Provider<?> value = entry.getValue();
            if (key.isAlwaysEager() || (key.isEagerInDefaultApp() && z)) {
                value.get();
            }
        }
        eventBus.enablePublishingAndFlushPending();
    }

    @Override // com.google.firebase.dynamicloading.ComponentLoader
    public void discoverComponents() {
        synchronized (this) {
            try {
                if (unprocessedRegistrarProviders.isEmpty()) {
                    return;
                }
              this.discoverComponents(new ArrayList());
            } catch (final Throwable th) {
                throw th;
            }
        }
    }

    @RestrictTo(RestrictTo.Scope.TESTS)
    @VisibleForTesting
    public void initializeAllComponentsForTests() {
        final Iterator<Provider<?>> it = components.values().iterator();
        while (it.hasNext()) {
            it.next().get();
        }
    }

    private void processDependencies() {
        for (final Component<?> component : components.keySet()) {
            for (final Dependency dependency : component.getDependencies()) {
                if (dependency.isSet() && !lazySetMap.containsKey(dependency.getInterface())) {
                    lazySetMap.put(dependency.getInterface(), LazySet.fromCollection(Collections.emptySet()));
                } else if (lazyInstanceMap.containsKey(dependency.getInterface())) {
                    continue;
                } else {
                    if (dependency.isRequired()) {
                        throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", component, dependency.getInterface()));
                    }
                    if (!dependency.isSet()) {
                        lazyInstanceMap.put(dependency.getInterface(), OptionalProvider.empty());
                    }
                }
            }
        }
    }

    public static final class Builder {
        private final Executor defaultExecutor;
        private final List<Provider<ComponentRegistrar>> lazyRegistrars = new ArrayList();
        private final List<Component<?>> additionalComponents = new ArrayList();

        /*  INFO: Access modifiers changed from: private */
        public static ComponentRegistrar lambdaaddComponentRegistrar0(final ComponentRegistrar componentRegistrar) {
            return componentRegistrar;
        }

        Builder(final Executor executor) {
            defaultExecutor = executor;
        }

        public Builder addLazyComponentRegistrars(final Collection<Provider<ComponentRegistrar>> collection) {
            lazyRegistrars.addAll(collection);
            return this;
        }

        public Builder addComponentRegistrar(ComponentRegistrar componentRegistrar) {
            lazyRegistrars.add(new Provider() { // from class: com.google.firebase.components.ComponentRuntimeBuilderExternalSyntheticLambda0
                @Override // com.google.firebase.inject.Provider
                public Object get() {
                    return lambdaaddComponentRegistrar0(componentRegistrar);
                }
            });
            return this;
        }

        public Builder addComponent(final Component<?> component) {
            additionalComponents.add(component);
            return this;
        }

        public ComponentRuntime build() {
            return new ComponentRuntime(defaultExecutor, lazyRegistrars, additionalComponents);
        }
    }
}
