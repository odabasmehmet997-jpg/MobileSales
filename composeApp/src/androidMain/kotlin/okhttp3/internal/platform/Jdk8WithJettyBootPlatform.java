package okhttp3.internal.platform;

import androidx.core.os.EnvironmentCompat;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import javax.net.ssl.SSLSocket;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static kotlin.jvm.internal.Intrinsics.*;

public final class Jdk8WithJettyBootPlatform extends Platform {
    public static final Companion Companion = new Companion(null);
    private final Class<?> clientProviderClass;
    private final Method getMethod;
    private final Method putMethod;
    private final Method removeMethod;
    private final Class<?> serverProviderClass;
    public Jdk8WithJettyBootPlatform(final Method putMethod, final Method getMethod, final Method removeMethod, final Class<?> clientProviderClass, final Class<?> serverProviderClass) {
        Intrinsics.checkNotNullParameter(putMethod, "putMethod");
        Intrinsics.checkNotNullParameter(getMethod, "getMethod");
        Intrinsics.checkNotNullParameter(removeMethod, "removeMethod");
        Intrinsics.checkNotNullParameter(clientProviderClass, "clientProviderClass");
        Intrinsics.checkNotNullParameter(serverProviderClass, "serverProviderClass");
        this.putMethod = putMethod;
        this.getMethod = getMethod;
        this.removeMethod = removeMethod;
        this.clientProviderClass = clientProviderClass;
        this.serverProviderClass = serverProviderClass;
    }
    public void configureTlsExtensions(final SSLSocket sslSocket, final String str, final List<Protocol> protocols) throws IllegalArgumentException {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        try {
            putMethod.invoke(null, sslSocket, Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{clientProviderClass, serverProviderClass}, new AlpnProvider(Platform.Companion.alpnProtocolNames(protocols))));
        } catch (final IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError("failed to set ALPN", e2);
        }
    }
    public void afterHandshake(final SSLSocket sslSocket) throws IllegalArgumentException {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        try {
            removeMethod.invoke(null, sslSocket);
        } catch (final IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError("failed to remove ALPN", e2);
        }
    }
    public String getSelectedProtocol(final SSLSocket sslSocket) throws IllegalArgumentException {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        try {
            final InvocationHandler invocationHandler = Proxy.getInvocationHandler(Objects.requireNonNull(getMethod.invoke(null, sslSocket)));
            if (null == invocationHandler) {
                throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider");
            }
            final AlpnProvider alpnProvider = (AlpnProvider) invocationHandler;
            if (!alpnProvider.getUnsupported() && null == alpnProvider.getSelected()) {
                logdefault(this, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", 0, null, 6, null);
                return null;
            }
            if (alpnProvider.getUnsupported()) {
                return null;
            }
            return alpnProvider.getSelected();
        } catch (final IllegalAccessException e2) {
            throw new AssertionError("failed to get ALPN selected protocol", e2);
        } catch (final InvocationTargetException e3) {
            throw new AssertionError("failed to get ALPN selected protocol", e3);
        }
    }
    private static final class AlpnProvider implements InvocationHandler {
        private final List<String> protocols;
        private String selected;
        private boolean unsupported;

        public AlpnProvider(final List<String> protocols) {
            Intrinsics.checkNotNullParameter(protocols, "protocols");
            this.protocols = protocols;
        }
        public boolean getUnsupported() {
            return unsupported;
        }
        public void setUnsupported(final boolean z) {
            unsupported = z;
        }
        public String getSelected() {
            return selected;
        }
        public void setSelected(final String str) {
            selected = str;
        }
        public Object invoke(final Object proxy, final Method method, Object[] objArr) throws Throwable {
            Intrinsics.checkNotNullParameter(proxy, "proxy");
            Intrinsics.checkNotNullParameter(method, "method");
            if (null == objArr) {
                objArr = new Object[0];
            }
            final String name = method.getName();
            final Class<?> returnType = method.getReturnType();
            if (areEqual(name, "supports") && areEqual(Boolean.TYPE, returnType)) {
                return Boolean.TRUE;
            }
            if (areEqual(name, "unsupported") && areEqual(Void.TYPE, returnType)) {
                unsupported = true;
                return null;
            }
            if (areEqual(name, "protocols") && 0 == objArr.length) {
                return protocols;
            }
            if ((areEqual(name, "selectProtocol") || areEqual(name, "select")) && areEqual(String.class, returnType) && 1 == objArr.length) {
                final Object obj = objArr[0];
                if (obj instanceof List list) {
                    if (null != obj) {
                        final int size = list.size();
                        if (0 <= size) {
                            int i2 = 0;
                            while (true) {
                                final int i3 = i2 + 1;
                                final Object obj2 = list.get(i2);
                                if (null == obj2) {
                                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                                }
                                final String str = (String) obj2;
                                if (protocols.contains(str)) {
                                    selected = str;
                                    return str;
                                }
                                if (i2 == size) {
                                    break;
                                }
                                i2 = i3;
                            }
                        }
                        final String str2 = protocols.get(0);
                        selected = str2;
                        return str2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<*>");
                }
            }
            if ((areEqual(name, "protocolSelected") || areEqual(name, "selected")) && 1 == objArr.length) {
                final Object obj3 = objArr[0];
                if (null == obj3) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                selected = (String) obj3;
                return null;
            }
            return method.invoke(this, Arrays.copyOf(objArr, objArr.length));
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Platform buildIfSupported() {
            final String jvmVersion = System.getProperty("java.specification.version", EnvironmentCompat.MEDIA_UNKNOWN);
            try {
                checkNotNullExpressionValue(jvmVersion, "jvmVersion");
                if (9 <= Integer.parseInt(jvmVersion)) {
                    return null;
                }
            } catch (final NumberFormatException ignored) {
            }
            try {
                final Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN", true, null);
                final Class<?> cls2 = Class.forName(stringPlus("org.eclipse.jetty.alpn.ALPN", "Provider"), true, null);
                final Class<?> clientProviderClass = Class.forName(stringPlus("org.eclipse.jetty.alpn.ALPN", "ClientProvider"), true, null);
                final Class<?> serverProviderClass = Class.forName(stringPlus("org.eclipse.jetty.alpn.ALPN", "ServerProvider"), true, null);
                final Method putMethod = cls.getMethod("put", SSLSocket.class, cls2);
                final Method getMethod = cls.getMethod("get", SSLSocket.class);
                final Method removeMethod = cls.getMethod("remove", SSLSocket.class);
                checkNotNullExpressionValue(putMethod, "putMethod");
                checkNotNullExpressionValue(getMethod, "getMethod");
                checkNotNullExpressionValue(removeMethod, "removeMethod");
                checkNotNullExpressionValue(clientProviderClass, "clientProviderClass");
                checkNotNullExpressionValue(serverProviderClass, "serverProviderClass");
                return new Jdk8WithJettyBootPlatform(putMethod, getMethod, removeMethod, clientProviderClass, serverProviderClass);
            } catch (final ClassNotFoundException | NoSuchMethodException unused2) {
                return null;
            }
        }
    }
}
