package org.simpleframework.xml.stream;


final class ProviderFactory {
    ProviderFactory() {
    }

    public static Provider getInstance() {
        try {
            try {
                return new StreamProvider();
            } catch (final Throwable unused) {
                return new DocumentProvider();
            }
        } catch (final Throwable unused2) {
            return new PullProvider();
        }
    }
}
