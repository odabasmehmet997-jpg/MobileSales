package com.sun.mail.util;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;

class SocksSupport {
    SocksSupport() {
    }
    public static Socket getSocket(String str, int i2) {
        if (null == str || 0 == str.length()) {
            return new Socket(Proxy.NO_PROXY);
        }
        return new Socket(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(str, i2)));
    }
}
