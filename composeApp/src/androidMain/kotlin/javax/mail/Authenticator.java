package javax.mail;

import java.net.InetAddress;

public abstract class Authenticator {
    private int requestingPort;
    private String requestingPrompt;
    private String requestingProtocol;
    private InetAddress requestingSite;
    private String requestingUserName;

    
    public PasswordAuthentication getPasswordAuthentication() {
        return null;
    }

    private void reset() {
        this.requestingSite = null;
        this.requestingPort = -1;
        this.requestingProtocol = null;
        this.requestingPrompt = null;
        this.requestingUserName = null;
    }

    
    public final PasswordAuthentication requestPasswordAuthentication(InetAddress inetAddress, int i2, String str, String str2, String str3) {
        reset();
        this.requestingSite = inetAddress;
        this.requestingPort = i2;
        this.requestingProtocol = str;
        this.requestingPrompt = str2;
        this.requestingUserName = str3;
        return getPasswordAuthentication();
    }

    
    public final InetAddress getRequestingSite() {
        return this.requestingSite;
    }

    
    public final int getRequestingPort() {
        return this.requestingPort;
    }

    
    public final String getRequestingProtocol() {
        return this.requestingProtocol;
    }

    
    public final String getRequestingPrompt() {
        return this.requestingPrompt;
    }

    
    public final String getDefaultUserName() {
        return this.requestingUserName;
    }
}
