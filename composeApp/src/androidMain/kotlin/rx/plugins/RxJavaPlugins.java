package rx.plugins;
 
import io.reactivex.internal.util.EndConsumerHelper;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class RxJavaPlugins {
    private static final RxJavaPlugins INSTANCE = new RxJavaPlugins();
    static final RxJavaErrorHandler DEFAULT_ERROR_HANDLER = new RxJavaErrorHandler() {   };
    private final AtomicReference<RxJavaErrorHandler> errorHandler = new AtomicReference<>();
    private final AtomicReference<RxJavaObservableExecutionHook> observableExecutionHook = new AtomicReference<>();
    private final AtomicReference<RxJavaSingleExecutionHook> singleExecutionHook = new AtomicReference<>();
    private final AtomicReference<RxJavaCompletableExecutionHook> completableExecutionHook = new AtomicReference<>();
    private final AtomicReference<RxJavaSchedulersHook> schedulersHook = new AtomicReference<>();
    public static RxJavaPlugins getInstance() {
        return INSTANCE;
    } 
    RxJavaPlugins() {
    }
    public RxJavaErrorHandler getErrorHandler() {
        if (this.errorHandler.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaErrorHandler.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.errorHandler, null, DEFAULT_ERROR_HANDLER);
            } else {
                EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.errorHandler, null, (RxJavaErrorHandler) pluginImplementationViaProperty);
            }
        }
        return this.errorHandler.get();
    }
    public RxJavaObservableExecutionHook getObservableExecutionHook() {
        if (this.observableExecutionHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaObservableExecutionHook.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.observableExecutionHook, null, RxJavaObservableExecutionHookDefault.getInstance());
            } else {
                EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.observableExecutionHook, null, (RxJavaObservableExecutionHook) pluginImplementationViaProperty);
            }
        }
        return this.observableExecutionHook.get();
    }
    public RxJavaSingleExecutionHook getSingleExecutionHook() {
        if (this.singleExecutionHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaSingleExecutionHook.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.singleExecutionHook, null, RxJavaSingleExecutionHookDefault.getInstance());
            } else {
                EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.singleExecutionHook, null, (RxJavaSingleExecutionHook) pluginImplementationViaProperty);
            }
        }
        return this.singleExecutionHook.get();
    }
    public RxJavaCompletableExecutionHook getCompletableExecutionHook() {
        if (this.completableExecutionHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaCompletableExecutionHook.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.completableExecutionHook, null, new RxJavaCompletableExecutionHook() { // from class: rx.plugins.RxJavaPlugins.2
                });
            } else {
                EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.completableExecutionHook, null, (RxJavaCompletableExecutionHook) pluginImplementationViaProperty);
            }
        }
        return this.completableExecutionHook.get();
    }
    static Properties getSystemPropertiesSafe() {
        try {
            return System.getProperties();
        } catch (SecurityException unused) {
            return new Properties();
        }
    }
    static java.lang.Object getPluginImplementationViaProperty(java.lang.Class<?> r9, java.util.Properties r10) {

        throw new UnsupportedOperationException("Method not decompiled: rx.plugins.RxJavaPlugins.getPluginImplementationViaProperty(java.lang.Class, java.util.Properties):java.lang.Object");
    }
    public RxJavaSchedulersHook getSchedulersHook() {
        if (this.schedulersHook.get() == null) {
            Object pluginImplementationViaProperty = getPluginImplementationViaProperty(RxJavaSchedulersHook.class, getSystemPropertiesSafe());
            if (pluginImplementationViaProperty == null) {
                EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.schedulersHook, null, RxJavaSchedulersHook.getDefaultInstance());
            } else {
                EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.schedulersHook, null, (RxJavaSchedulersHook) pluginImplementationViaProperty);
            }
        }
        return this.schedulersHook.get();
    }
}
