package okhttp3.internal.platform.android;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import static kotlin.jvm.internal.Intrinsics.*;

public final class StandardAndroidSocketAdapter extends AndroidSocketAdapter {
    public static final Companion Companion = new Companion(null);
    private final Class<?> paramClass;
    private final Class<? super SSLSocketFactory> sslSocketFactoryClass;
    public StandardAndroidSocketAdapter(final Class<? super SSLSocket> sslSocketClass, final Class<?> sslSocketFactoryClass, final Class<?> paramClass) {
        try {
            super(sslSocketClass);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Intrinsics.checkNotNullParameter(sslSocketClass, "sslSocketClass");
        Intrinsics.checkNotNullParameter(sslSocketFactoryClass, "sslSocketFactoryClass");
        Intrinsics.checkNotNullParameter(paramClass, "paramClass");
        this.sslSocketFactoryClass = (Class<? super SSLSocketFactory>) sslSocketFactoryClass;
        this.paramClass = paramClass;
    }
    public boolean matchesSocketFactory(final SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        return sslSocketFactoryClass.isInstance(sslSocketFactory);
    }
    public X509TrustManager trustManager(final SSLSocketFactory sslSocketFactory) throws IllegalAccessException, SecurityException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        final Object fieldOrNull;
        try {
            fieldOrNull = Util.readFieldOrNull(sslSocketFactory, paramClass, "sslParameters");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        checkNotNull(fieldOrNull);
        final X509TrustManager x509TrustManager;
        try {
            x509TrustManager = Util.readFieldOrNull(fieldOrNull, X509TrustManager.class, "x509TrustManager");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        try {
            return null == x509TrustManager ? Util.readFieldOrNull(fieldOrNull, X509TrustManager.class, "trustManager") : x509TrustManager;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static SocketAdapter buildIfSupporteddefault(final Companion companion, String str, final int i2, final Object obj) {
            if (0 != (i2 & 1)) {
                str = "com.android.org.conscrypt";
            }
            try {
                return companion.buildIfSupported(str);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public SocketAdapter buildIfSupported(final String packageName) throws ClassNotFoundException {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            try {
                final Class<?> cls = Class.forName(stringPlus(packageName, ".OpenSSLSocketImpl"));
                final Class<?> cls2 = Class.forName(stringPlus(packageName, ".OpenSSLSocketFactoryImpl"));
                final Class<?> paramsClass = Class.forName(stringPlus(packageName, ".SSLParametersImpl"));
                checkNotNullExpressionValue(paramsClass, "paramsClass");
                return new StandardAndroidSocketAdapter((Class<? super SSLSocket>) cls, cls2, paramsClass);
            } catch (final Exception e2) {
                Platform.Companion.get().log("unable to load android socket classes", 5, e2);
                return null;
            }
        }
    }
}
