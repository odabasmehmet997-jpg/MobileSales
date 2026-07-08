package com.proje.mobilesales.core.netsis;

public class NetsisTokenService {
    private static NetsisTokenService instance;
    private static final Object lock = new Object();
    private NetsisRestTokenApi mRestTokenApi;

    private NetsisTokenService(String str) {
        createApiService(str);
    }

    public static NetsisTokenService getInstance(String str) {
        synchronized (lock) {
            try {
                if (instance == null) {
                    instance = new NetsisTokenService(str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return instance;
    }

    public static NetsisTokenService changeInstance(String str) {
        NetsisTokenService netsisTokenService;
        synchronized (lock) {
            netsisTokenService = new NetsisTokenService(str);
            instance = netsisTokenService;
        }
        return netsisTokenService;
    }

    private void createApiService(String str) {
        this.mRestTokenApi = new NetsisRestTokenFactory.Impl().create(str, NetsisRestTokenApi.class);
    }

    public NetsisRestTokenApi getRestTokenApi() {
        return this.mRestTokenApi;
    }
}
