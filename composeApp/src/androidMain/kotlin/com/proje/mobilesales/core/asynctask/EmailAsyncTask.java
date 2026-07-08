package com.proje.mobilesales.core.asynctask;

import android.net.TrafficStats;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.AesEncryption;
import sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailAsyncTask extends Authenticator {
    private final boolean _auth;
    private String _body;
    private final boolean _debuggable;
    private final String _domain;
    private final String _from;
    private final String _host;
    private final Multipart _multipart;
    private String[] _others;
    private String _pass;
    private final String _port;
    private final boolean _sendHtmlContent;
    private final String _sport;
    private final String _sslType;
    private String _subject;
    public ArrayList<String> _to;
    private String _user;
    private String tryDecryptPassword(String str) {
        try {
            return new AesEncryption().decrypt(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }
    public EmailAsyncTask(boolean z) {
        ISqlHelper logoSqlHelper = ErpCreator.getInstance().getmBaseErp().getLogoSqlHelper();
        this._sendHtmlContent = z;
        this._host = logoSqlHelper.getEmailParamValue("23");
        this._port = logoSqlHelper.getEmailParamValue("31");
        this._sslType = logoSqlHelper.getEmailParamValue("30");
        this._sport = "587";
        this._from = logoSqlHelper.getEmailParamValue("24");
        this._user = logoSqlHelper.getEmailParamValue("25");
        this._pass = tryDecryptPassword(logoSqlHelper.getEmailParamValue("26"));
        this._domain = logoSqlHelper.getEmailParamValue("27");
        this._to = new ArrayList<>();
        set_subject(logoSqlHelper.getEmailParamValue("29"));
        this._body = "";
        this._debuggable = false;
        this._auth = true;
        this._multipart = new MimeMultipart();
        if (this._sendHtmlContent) {
            return;
        }
        MailcapCommandMap mailcapCommandMap = getMailcapCommandMap();
        CommandMap.setDefaultCommandMap(mailcapCommandMap);
    }
    private static MailcapCommandMap getMailcapCommandMap() {
        MailcapCommandMap mailcapCommandMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mailcapCommandMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mailcapCommandMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mailcapCommandMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mailcapCommandMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mailcapCommandMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        return mailcapCommandMap;
    }
    public EmailAsyncTask(String str, String str2, boolean z) {
        this(z);
        this._user = str;
        this._pass = str2;
    }
    public boolean send() throws Exception {
        Properties _setProperties = _setProperties();
        if (this._user.isEmpty() || this._pass.isEmpty() || this._to.size() <= 0
                || this._from.isEmpty() || get_subject().isEmpty() || this._body.isEmpty()) {
            return false;
        }
        MimeMessage mimeMessage = new MimeMessage(Session.getInstance(_setProperties, this));
        mimeMessage.setFrom(new InternetAddress(this._from));
        InternetAddress[] internetAddressArr = new InternetAddress[this._to.size()];
        for (int i2 = 0; i2 < this._to.size(); i2++) {
            internetAddressArr[i2] = new InternetAddress(this._to.get(i2));
        }
        mimeMessage.setRecipients(Message.RecipientType.TO, internetAddressArr);
        mimeMessage.setSubject(get_subject());
        mimeMessage.setSentDate(new Date());
        if (!this._sendHtmlContent) {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText(this._body);
            this._multipart.addBodyPart(mimeBodyPart);
            mimeMessage.setContent(this._multipart);
        } else {
            mimeMessage.setContent(this._body, "text/html; charset=utf-8");
        }
        TrafficStats.setThreadStatsTag((int) Thread.currentThread().getId());
        Transport.send(mimeMessage);
        return true;
    }
    public void addAttachment(String str, String str2) throws Exception {
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setDataHandler(new DataHandler(new FileDataSource(str)));
        mimeBodyPart.setFileName(str2);
        this._multipart.addBodyPart(mimeBodyPart);
    }
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this._user, this._pass);
    }
    private Properties _setProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", this._host);
        String str = this._sslType;
        if (str != null && !str.isEmpty() && !this._sslType.equals("0")) {
            try {
                MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
                mailSSLSocketFactory.setTrustAllHosts(true);
                properties.put("mail.smtp.ssl.socketFactory", mailSSLSocketFactory);
            } catch (GeneralSecurityException e2) {
                e2.printStackTrace();
            }
            String str2 = this._sslType;
            str2.hashCode();
            if (str2.equals(BuildConfig.NETSIS_DEMO_PASSWORD)) {
                properties.put("mail.smtp.port", this._port.isEmpty() ? "465" : this._port);
                properties.put("mail.smtp.socketFactory.port", this._port.isEmpty() ? "465" : this._port);
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                if (!TextUtils.isEmpty(this._port) && this._port.equals("587")) {
                    properties.put("mail.smtp.starttls.enable", "true");
                }
            } else if (str2.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                properties.put("mail.smtp.port", this._port.isEmpty() ? "587" : this._port);
                properties.put("mail.smtp.starttls.enable", "true");
            }
        }
        if (this._debuggable) {
            properties.put("mail.debug", "true");
        }
        if (this._auth) {
            properties.put("mail.smtp.auth", "true");
        }
        return properties;
    }
    public String getBody() {
        return this._body;
    }
    public void setBody(String str) {
        this._body = str;
    }
    public ArrayList<String> get_to() {
        return this._to;
    }
    public void set_others(String[] strArr) {
        this._others = strArr;
    }
    public String get_subject() {
        return this._subject;
    }
    public void set_subject(String str) {
        this._subject = str;
    }
}
