package com.sun.mail.smtp;

import androidx.webkit.ProxyConfig;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.MailLogger;
import java.util.Properties;
import java.util.logging.Level;
import javax.mail.MessagingException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.sasl.RealmCallback;
import javax.security.sasl.RealmChoiceCallback;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;

public class SMTPSaslAuthenticator implements SaslAuthenticator {
    private final String host;
    private final MailLogger logger;
    private final String name;
    private final SMTPTransport pr;
    private final Properties props;

    public SMTPSaslAuthenticator(SMTPTransport sMTPTransport, String str, Properties properties, MailLogger mailLogger, String str2) {
        this.pr = sMTPTransport;
        this.name = str;
        this.props = properties;
        this.logger = mailLogger;
        this.host = str2;
    }

    public boolean authenticate(String[] strArr, final String str, String str2, final String str3, final String str4) throws MessagingException {
        String string;
        int iSimpleCommand;
        String str5;
        byte[] bArrEvaluateChallenge;
        if (this.logger.isLoggable(Level.FINE)) {
            this.logger.fine("SASL Mechanisms:");
            for (String str6 : strArr) {
                MailLogger mailLogger = this.logger;
                String stringBuffer = " " +
                        str6;
                mailLogger.fine(stringBuffer);
            }
            this.logger.fine("");
        }
        try {
            SaslClient saslClientCreateSaslClient = Sasl.createSaslClient(strArr, str2, this.name, this.host, this.props, new CallbackHandler() {
                public void handle(Callback[] callbackArr) {
                    if (SMTPSaslAuthenticator.this.logger.isLoggable(Level.FINE)) {
                        MailLogger mailLogger2 = SMTPSaslAuthenticator.this.logger;
                        String stringBuffer2 = "SASL callback length: " +
                                callbackArr.length;
                        mailLogger2.fine(stringBuffer2);
                    }
                    for (int i2 = 0; i2 < callbackArr.length; i2++) {
                        if (SMTPSaslAuthenticator.this.logger.isLoggable(Level.FINE)) {
                            MailLogger mailLogger3 = SMTPSaslAuthenticator.this.logger;
                            String stringBuffer3 = "SASL callback " +
                                    i2 +
                                    ": " +
                                    callbackArr[i2];
                            mailLogger3.fine(stringBuffer3);
                        }
                        Callback callback = callbackArr[i2];
                        if (callback instanceof NameCallback) {
                            ((NameCallback) callback).setName(str3);
                        } else if (callback instanceof PasswordCallback) {
                            ((PasswordCallback) callback).setPassword(str4.toCharArray());
                        } else if (callback instanceof RealmCallback) {
                            RealmCallback realmCallback = (RealmCallback) callback;
                            String defaultText = str;
                            if (defaultText == null) {
                                defaultText = realmCallback.getDefaultText();
                            }
                            realmCallback.setText(defaultText);
                        } else if (callback instanceof RealmChoiceCallback) {
                            RealmChoiceCallback realmChoiceCallback = (RealmChoiceCallback) callback;
                            if (str == null) {
                                realmChoiceCallback.setSelectedIndex(realmChoiceCallback.getDefaultChoice());
                            } else {
                                String[] choices = realmChoiceCallback.getChoices();
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= choices.length) {
                                        break;
                                    }
                                    if (choices[i3].equals(str)) {
                                        realmChoiceCallback.setSelectedIndex(i3);
                                        break;
                                    }
                                    i3++;
                                }
                            }
                        }
                    }
                }
            });
            if (saslClientCreateSaslClient == null) {
                this.logger.fine("No SASL support");
                return false;
            }
            if (this.logger.isLoggable(Level.FINE)) {
                MailLogger mailLogger2 = this.logger;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("SASL client ");
                stringBuffer2.append(saslClientCreateSaslClient.getMechanismName());
                mailLogger2.fine(stringBuffer2.toString());
            }
            try {
                String mechanismName = saslClientCreateSaslClient.getMechanismName();
                if (saslClientCreateSaslClient.hasInitialResponse()) {
                    byte[] bArrEncode = BASE64EncoderStream.encode(saslClientCreateSaslClient.evaluateChallenge(new byte[0]));
                    string = ASCIIUtility.toString(bArrEncode, 0, bArrEncode.length);
                } else {
                    string = null;
                }
                if (string != null) {
                    SMTPTransport sMTPTransport = this.pr;
                    String stringBuffer3 = "AUTH " +
                            mechanismName +
                            " " +
                            string;
                    iSimpleCommand = sMTPTransport.simpleCommand(stringBuffer3);
                } else {
                    SMTPTransport sMTPTransport2 = this.pr;
                    String stringBuffer4 = "AUTH " +
                            mechanismName;
                    iSimpleCommand = sMTPTransport2.simpleCommand(stringBuffer4);
                }
                if (iSimpleCommand == 530) {
                    this.pr.startTLS();
                    if (string != null) {
                        SMTPTransport sMTPTransport3 = this.pr;
                        String stringBuffer5 = "AUTH " +
                                mechanismName +
                                " " +
                                string;
                        iSimpleCommand = sMTPTransport3.simpleCommand(stringBuffer5);
                    } else {
                        SMTPTransport sMTPTransport4 = this.pr;
                        String stringBuffer6 = "AUTH " +
                                mechanismName;
                        iSimpleCommand = sMTPTransport4.simpleCommand(stringBuffer6);
                    }
                }
                if (iSimpleCommand == 235) {
                    return true;
                }
                if (iSimpleCommand != 334) {
                    return false;
                }
                boolean z = false;
                while (!z) {
                    if (iSimpleCommand == 334) {
                        try {
                            if (saslClientCreateSaslClient.isComplete()) {
                                bArrEvaluateChallenge = null;
                            } else {
                                byte[] bytes = ASCIIUtility.getBytes(responseText(this.pr));
                                if (bytes.length > 0) {
                                    bytes = BASE64DecoderStream.decode(bytes);
                                }
                                if (this.logger.isLoggable(Level.FINE)) {
                                    MailLogger mailLogger3 = this.logger;
                                    String stringBuffer7 = "SASL challenge: " +
                                            ASCIIUtility.toString(bytes, 0, bytes.length) +
                                            " :";
                                    mailLogger3.fine(stringBuffer7);
                                }
                                bArrEvaluateChallenge = saslClientCreateSaslClient.evaluateChallenge(bytes);
                            }
                            if (bArrEvaluateChallenge == null) {
                                this.logger.fine("SASL: no response");
                                iSimpleCommand = this.pr.simpleCommand(ProxyConfig.MATCH_ALL_SCHEMES);
                            } else {
                                if (this.logger.isLoggable(Level.FINE)) {
                                    MailLogger mailLogger4 = this.logger;
                                    String stringBuffer8 = "SASL response: " +
                                            ASCIIUtility.toString(bArrEvaluateChallenge, 0, bArrEvaluateChallenge.length) +
                                            " :";
                                    mailLogger4.fine(stringBuffer8);
                                }
                                iSimpleCommand = this.pr.simpleCommand(BASE64EncoderStream.encode(bArrEvaluateChallenge));
                            }
                        } catch (Exception e2) {
                            this.logger.log(Level.FINE, "SASL Exception", e2);
                            z = true;
                        }
                    }
                    z = true;
                }
                if (!saslClientCreateSaslClient.isComplete() || (str5 = (String) saslClientCreateSaslClient.getNegotiatedProperty("javax.security.sasl.qop")) == null || (!str5.equalsIgnoreCase("auth-int") && !str5.equalsIgnoreCase("auth-conf"))) {
                    return true;
                }
                this.logger.fine("SASL Mechanism requires integrity or confidentiality");
                return false;
            } catch (Exception e3) {
                this.logger.log(Level.FINE, "SASL AUTHENTICATE Exception", e3);
                return false;
            }
        } catch (SaslException e4) {
            this.logger.log(Level.FINE, "Failed to create SASL client: ", e4);
            return false;
        }
    }

    private static final String responseText(final com.sun.mail.smtp.SMTPTransport sMTPTransport) {
        final String strTrim = sMTPTransport.getLastServerResponse().trim();
        if (strTrim.length() > 4) {
            return strTrim.substring(4);
        }
        return "";
    }
}
