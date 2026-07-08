package javax.mail.internet;

import sun.mail.util.ASCIIUtility;
import sun.mail.util.FolderClosedIOException;
import sun.mail.util.MessageRemovedIOException;
import sun.mail.util.MimeUtil;
import sun.mail.util.PropUtil;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.util.SharedByteArrayInputStream;
import org.springframework.http.HttpHeaders;

public class MimeMessage extends Message implements MimePart {
    private static final Flags answeredFlag = new Flags(Flags.Flag.ANSWERED);
    private static final MailDateFormat mailDateFormat = new MailDateFormat();
    Object cachedContent;
    protected byte[] content;
    protected InputStream contentStream;
    protected DataHandler dh;
    protected Flags flags;
    protected InternetHeaders headers;
    protected boolean modified;
    protected boolean saved;
    private boolean strict;

    public int getLineCount() throws MessagingException {
        return -1;
    }

    public Date getReceivedDate() throws MessagingException {
        return null;
    }

    public MimeMessage(Session session) {
        super(session);
        this.saved = false;
        this.strict = true;
        this.modified = true;
        this.headers = new InternetHeaders();
        this.flags = new Flags();
        initStrict();
    }

    public MimeMessage(Session session, InputStream inputStream) throws MessagingException {
        super(session);
        this.modified = false;
        this.saved = false;
        this.strict = true;
        this.flags = new Flags();
        initStrict();
        parse(inputStream);
        this.saved = true;
    }

    public MimeMessage(MimeMessage mimeMessage) throws MessagingException {
        super(mimeMessage.session);
        ByteArrayOutputStream byteArrayOutputStream;
        this.modified = false;
        this.saved = false;
        this.strict = true;
        Flags flags2 = mimeMessage.getFlags();
        this.flags = flags2;
        if (null == flags2) {
            this.flags = new Flags();
        }
        int size = mimeMessage.getSize();
        if (0 < size) {
            byteArrayOutputStream = new ByteArrayOutputStream(size);
        } else {
            byteArrayOutputStream = new ByteArrayOutputStream();
        }
        try {
            this.strict = mimeMessage.strict;
            mimeMessage.writeTo(byteArrayOutputStream);
            byteArrayOutputStream.close();
            SharedByteArrayInputStream sharedByteArrayInputStream = new SharedByteArrayInputStream(byteArrayOutputStream.toByteArray());
            parse(sharedByteArrayInputStream);
            sharedByteArrayInputStream.close();
            this.saved = true;
        } catch (IOException e2) {
            throw new MessagingException("IOException while copying message", e2);
        }
    }

    protected MimeMessage(Folder folder, int i2) {
        super(folder, i2);
        this.modified = false;
        this.saved = false;
        this.strict = true;
        this.flags = new Flags();
        this.saved = true;
        initStrict();
    }

    protected MimeMessage(Folder folder, InputStream inputStream, int i2) throws MessagingException {
        this(folder, i2);
        initStrict();
        parse(inputStream);
    }

    protected MimeMessage(Folder folder, InternetHeaders internetHeaders, byte[] bArr, int i2) throws MessagingException {
        this(folder, i2);
        this.headers = internetHeaders;
        this.content = bArr;
        initStrict();
    }

    private void initStrict() {
        Session session = this.session;
        if (null != session) {
            this.strict = PropUtil.getBooleanSessionProperty(session, "mail.mime.address.strict", true);
        }
    }

    
    public void parse(InputStream inputStream) throws MessagingException {
        if (!(inputStream instanceof ByteArrayInputStream) && !(inputStream instanceof BufferedInputStream) && !(inputStream instanceof SharedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        this.headers = createInternetHeaders(inputStream);
        if (inputStream instanceof final SharedInputStream sharedInputStream) {
            this.contentStream = sharedInputStream.newStream(sharedInputStream.getPosition(), -1);
        } else {
            try {
                this.content = ASCIIUtility.getBytes(inputStream);
            } catch (IOException e2) {
                throw new MessagingException("IOException", e2);
            }
        }
        this.modified = false;
    }

    public Address[] getFrom() throws MessagingException {
        Address[] addressHeader = getAddressHeader(HttpHeaders.FROM);
        return null == addressHeader ? getAddressHeader("Sender") : addressHeader;
    }

    public void setFrom(Address address) throws MessagingException {
        if (null == address) {
            removeHeader(HttpHeaders.FROM);
        } else {
            setHeader(HttpHeaders.FROM, address.toString());
        }
    }

    public void setFrom() throws MessagingException {
        try {
            InternetAddress _getLocalAddress = InternetAddress._getLocalAddress(this.session);
            if (null != _getLocalAddress) {
                setFrom(_getLocalAddress);
                return;
            }
            throw new MessagingException("No From address");
        } catch (Exception e2) {
            throw new MessagingException("No From address", e2);
        }
    }

    public void addFrom(Address[] addressArr) throws MessagingException {
        addAddressHeader(HttpHeaders.FROM, addressArr);
    }

    public Address getSender() throws MessagingException {
        Address[] addressHeader = getAddressHeader("Sender");
        if (null == addressHeader || 0 == addressHeader.length) {
            return null;
        }
        return addressHeader[0];
    }

    public void setSender(Address address) throws MessagingException {
        if (null == address) {
            removeHeader("Sender");
        } else {
            setHeader("Sender", address.toString());
        }
    }

    public static class RecipientType extends Message.RecipientType {
        public static final RecipientType NEWSGROUPS = new RecipientType("Newsgroups");
        private static final long serialVersionUID = -5468290701714395543L;

        protected RecipientType(String str) {
            super(str);
        }

        
        public Object readResolve() throws ObjectStreamException {
            if ("Newsgroups".equals(type)) {
                return NEWSGROUPS;
            }
            return super.readResolve();
        }
    }

    public Address[] getRecipients(Message.RecipientType recipientType) throws MessagingException {
        if (recipientType != RecipientType.NEWSGROUPS) {
            return getAddressHeader(getHeaderName(recipientType));
        }
        String header = getHeader("Newsgroups", ",");
        if (null == header) {
            return null;
        }
        return NewsAddress.parse(header);
    }

    public Address[] getAllRecipients() throws MessagingException {
        Address[] allRecipients = super.getAllRecipients();
        Address[] recipients = getRecipients(RecipientType.NEWSGROUPS);
        if (null == recipients) {
            return allRecipients;
        }
        if (null == allRecipients) {
            return recipients;
        }
        Address[] addressArr = new Address[(allRecipients.length + recipients.length)];
        System.arraycopy(allRecipients, 0, addressArr, 0, allRecipients.length);
        System.arraycopy(recipients, 0, addressArr, allRecipients.length, recipients.length);
        return addressArr;
    }

    public void setRecipients(Message.RecipientType recipientType, Address[] addressArr) throws MessagingException {
        if (recipientType != RecipientType.NEWSGROUPS) {
            setAddressHeader(getHeaderName(recipientType), addressArr);
        } else if (null == addressArr || 0 == addressArr.length) {
            removeHeader("Newsgroups");
        } else {
            setHeader("Newsgroups", NewsAddress.toString(addressArr));
        }
    }

    public void setRecipients(Message.RecipientType recipientType, String str) throws MessagingException {
        if (recipientType != RecipientType.NEWSGROUPS) {
            setAddressHeader(getHeaderName(recipientType), null == str ? null : InternetAddress.parse(str));
        } else if (null == str || 0 == str.length()) {
            removeHeader("Newsgroups");
        } else {
            setHeader("Newsgroups", str);
        }
    }

    public void addRecipients(Message.RecipientType recipientType, Address[] addressArr) throws MessagingException {
        if (recipientType == RecipientType.NEWSGROUPS) {
            String newsAddress = NewsAddress.toString(addressArr);
            if (null != newsAddress) {
                addHeader("Newsgroups", newsAddress);
                return;
            }
            return;
        }
        addAddressHeader(getHeaderName(recipientType), addressArr);
    }

    public void addRecipients(Message.RecipientType recipientType, String str) throws MessagingException {
        if (recipientType != RecipientType.NEWSGROUPS) {
            addAddressHeader(getHeaderName(recipientType), InternetAddress.parse(str));
        } else if (null != str && 0 != str.length()) {
            addHeader("Newsgroups", str);
        }
    }

    public Address[] getReplyTo() throws MessagingException {
        Address[] addressHeader = getAddressHeader("Reply-To");
        return (null == addressHeader || 0 == addressHeader.length) ? getFrom() : addressHeader;
    }

    public void setReplyTo(Address[] addressArr) throws MessagingException {
        setAddressHeader("Reply-To", addressArr);
    }

    private Address[] getAddressHeader(String str) throws MessagingException {
        String header = getHeader(str, ",");
        if (null == header) {
            return null;
        }
        return InternetAddress.parseHeader(header, this.strict);
    }

    private void setAddressHeader(String str, Address[] addressArr) throws MessagingException {
        String internetAddress = InternetAddress.toString(addressArr);
        if (null == internetAddress) {
            removeHeader(str);
        } else {
            setHeader(str, internetAddress);
        }
    }

    private void addAddressHeader(String str, Address[] addressArr) throws MessagingException {
        if (null != addressArr && 0 != addressArr.length) {
            Address[] addressHeader = getAddressHeader(str);
            if (!(null == addressHeader || 0 == addressHeader.length)) {
                Address[] addressArr2 = new Address[(addressHeader.length + addressArr.length)];
                System.arraycopy(addressHeader, 0, addressArr2, 0, addressHeader.length);
                System.arraycopy(addressArr, 0, addressArr2, addressHeader.length, addressArr.length);
                addressArr = addressArr2;
            }
            String internetAddress = InternetAddress.toString(addressArr);
            if (null != internetAddress) {
                setHeader(str, internetAddress);
            }
        }
    }

    public String getSubject() throws MessagingException {
        String header = getHeader("Subject", null);
        if (null == header) {
            return null;
        }
        try {
            return MimeUtility.decodeText(MimeUtility.unfold(header));
        } catch (UnsupportedEncodingException unused) {
            return header;
        }
    }

    public void setSubject(String str) throws MessagingException {
        setSubject(str, (String) null);
    }

    public void setSubject(String str, String str2) throws MessagingException {
        if (null == str) {
            removeHeader("Subject");
            return;
        }
        try {
            setHeader("Subject", MimeUtility.fold(9, MimeUtility.encodeText(str, str2, (String) null)));
        } catch (UnsupportedEncodingException e2) {
            throw new MessagingException("Encoding error", e2);
        }
    }

    public Date getSentDate() throws MessagingException {
        Date parse;
        String header = getHeader(HttpHeaders.DATE, null);
        if (null != header) {
            try {
                MailDateFormat mailDateFormat2 = mailDateFormat;
                synchronized (mailDateFormat2) {
                    parse = mailDateFormat2.parse(header);
                }
                return parse;
            } catch (ParseException unused) {
            }
        }
        return null;
    }

    public void setSentDate(Date date) throws MessagingException {
        if (null == date) {
            removeHeader(HttpHeaders.DATE);
            return;
        }
        MailDateFormat mailDateFormat2 = mailDateFormat;
        synchronized (mailDateFormat2) {
            setHeader(HttpHeaders.DATE, mailDateFormat2.format(date));
        }
    }

    public int getSize() throws MessagingException {
        byte[] bArr = this.content;
        if (null != bArr) {
            return bArr.length;
        }
        InputStream inputStream = this.contentStream;
        if (null == inputStream) {
            return -1;
        }
        try {
            int available = inputStream.available();
            if (0 < available) {
                return available;
            }
            return -1;
        } catch (IOException unused) {
            return -1;
        }
    }

    public String getContentType() throws MessagingException {
        String cleanContentType = MimeUtil.cleanContentType(this, getHeader(HttpHeaders.CONTENT_TYPE, null));
        return null == cleanContentType ? "text/plain" : cleanContentType;
    }

    public boolean isMimeType(String str) throws MessagingException {
        return MimeBodyPart.isMimeType(this, str);
    }

    public String getDisposition() throws MessagingException {
        return MimeBodyPart.getDisposition(this);
    }

    public void setDisposition(String str) throws MessagingException {
        MimeBodyPart.setDisposition(this, str);
    }

    public String getEncoding() throws MessagingException {
        return MimeBodyPart.getEncoding(this);
    }

    public String getContentID() throws MessagingException {
        return getHeader("Content-Id", null);
    }

    public void setContentID(String str) throws MessagingException {
        if (null == str) {
            removeHeader("Content-ID");
        } else {
            setHeader("Content-ID", str);
        }
    }

    public String getContentMD5() throws MessagingException {
        return getHeader("Content-MD5", null);
    }

    public void setContentMD5(String str) throws MessagingException {
        setHeader("Content-MD5", str);
    }

    public String getDescription() throws MessagingException {
        return MimeBodyPart.getDescription(this);
    }

    public void setDescription(String str) throws MessagingException {
        setDescription(str, (String) null);
    }

    public void setDescription(String str, String str2) throws MessagingException {
        MimeBodyPart.setDescription(this, str, str2);
    }

    public String[] getContentLanguage() throws MessagingException {
        return MimeBodyPart.getContentLanguage(this);
    }

    public void setContentLanguage(String[] strArr) throws MessagingException {
        MimeBodyPart.setContentLanguage(this, strArr);
    }

    public String getMessageID() throws MessagingException {
        return getHeader("Message-ID", null);
    }

    public String getFileName() throws MessagingException {
        return MimeBodyPart.getFileName(this);
    }

    public void setFileName(String str) throws MessagingException {
        MimeBodyPart.setFileName(this, str);
    }

    private String getHeaderName(Message.RecipientType recipientType) throws MessagingException {
        if (recipientType == Message.RecipientType.TO) {
            return "To";
        }
        if (recipientType == Message.RecipientType.CC) {
            return "Cc";
        }
        if (recipientType == Message.RecipientType.BCC) {
            return "Bcc";
        }
        if (recipientType == RecipientType.NEWSGROUPS) {
            return "Newsgroups";
        }
        throw new MessagingException("Invalid Recipient Type");
    }

    public InputStream getInputStream() throws IOException, MessagingException {
        return getDataHandler().getInputStream();
    }

    
    public InputStream getContentStream() throws MessagingException {
        InputStream inputStream = this.contentStream;
        if (null != inputStream) {
            return ((SharedInputStream) inputStream).newStream(0, -1);
        }
        if (null != content) {
            return new SharedByteArrayInputStream(this.content);
        }
        throw new MessagingException("No MimeMessage content");
    }

    public InputStream getRawInputStream() throws MessagingException {
        return getContentStream();
    }

    public synchronized DataHandler getDataHandler() throws MessagingException {
        try {
            if (null == dh) {
                this.dh = new MimeBodyPart.MimePartDataHandler(new MimePartDataSource(this));
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.dh;
    }

    public Object getContent() throws IOException, MessagingException {
        Object obj = this.cachedContent;
        if (null != obj) {
            return obj;
        }
        try {
            Object content2 = getDataHandler().getContent();
            if (MimeBodyPart.cacheMultipart && (((content2 instanceof Multipart) || (content2 instanceof Message)) && !(null == content && null == contentStream))) {
                this.cachedContent = content2;
                if (content2 instanceof MimeMultipart) {
                    ((MimeMultipart) content2).parse();
                }
            }
            return content2;
        } catch (FolderClosedIOException e2) {
            throw new FolderClosedException(e2.getFolder(), e2.getMessage());
        } catch (MessageRemovedIOException e3) {
            throw new MessageRemovedException(e3.getMessage());
        }
    }

    public synchronized void setDataHandler(DataHandler dataHandler) throws MessagingException {
        this.dh = dataHandler;
        this.cachedContent = null;
        MimeBodyPart.invalidateContentHeaders(this);
    }

    public void setContent(Object obj, String str) throws MessagingException {
        if (obj instanceof Multipart) {
            setContent((Multipart) obj);
        } else {
            setDataHandler(new DataHandler(obj, str));
        }
    }

    public void setText(String str) throws MessagingException {
        setText(str, (String) null);
    }

    public void setText(String str, String str2) throws MessagingException {
        MimeBodyPart.setText(this, str, str2, "plain");
    }

    public void setText(String str, String str2, String str3) throws MessagingException {
        MimeBodyPart.setText(this, str, str2, str3);
    }

    public void setContent(Multipart multipart) throws MessagingException {
        setDataHandler(new DataHandler(multipart, multipart.getContentType()));
        multipart.setParent(this);
    }

    public Message reply(boolean z) throws MessagingException {
        MimeMessage createMimeMessage = createMimeMessage(this.session);
        String header = getHeader("Subject", null);
        if (null != header) {
            if (!header.regionMatches(true, 0, "Re: ", 0, 4)) {
                final String stringBuffer = "Re: " +
                        header;
                header = stringBuffer;
            }
            createMimeMessage.setHeader("Subject", header);
        }
        Address[] replyTo = getReplyTo();
        Message.RecipientType recipientType = Message.RecipientType.TO;
        createMimeMessage.setRecipients(recipientType, replyTo);
        if (z) {
            Vector vector = new Vector();
            InternetAddress localAddress = InternetAddress.getLocalAddress(this.session);
            if (null != localAddress) {
                vector.addElement(localAddress);
            }
            Session session = this.session;
            String property = null != session ? session.getProperty("mail.alternates") : null;
            boolean z2 = false;
            if (null != property) {
                eliminateDuplicates(vector, InternetAddress.parse(property, false));
            }
            Session session2 = this.session;
            if (null != session2) {
                z2 = PropUtil.getBooleanSessionProperty(session2, "mail.replyallcc", false);
            }
            eliminateDuplicates(vector, replyTo);
            Address[] eliminateDuplicates = eliminateDuplicates(vector, getRecipients(recipientType));
            if (null != eliminateDuplicates && 0 < eliminateDuplicates.length) {
                if (z2) {
                    createMimeMessage.addRecipients(Message.RecipientType.CC, eliminateDuplicates);
                } else {
                    createMimeMessage.addRecipients(recipientType, eliminateDuplicates);
                }
            }
            Message.RecipientType recipientType2 = Message.RecipientType.CC;
            Address[] eliminateDuplicates2 = eliminateDuplicates(vector, getRecipients(recipientType2));
            if (null != eliminateDuplicates2 && 0 < eliminateDuplicates2.length) {
                createMimeMessage.addRecipients(recipientType2, eliminateDuplicates2);
            }
            RecipientType recipientType3 = RecipientType.NEWSGROUPS;
            Address[] recipients = getRecipients(recipientType3);
            if (null != recipients && 0 < recipients.length) {
                createMimeMessage.setRecipients((Message.RecipientType) recipientType3, recipients);
            }
        }
        String header2 = getHeader("Message-Id", null);
        if (null != header2) {
            createMimeMessage.setHeader("In-Reply-To", header2);
        }
        String header3 = getHeader("References", " ");
        if (null == header3) {
            header3 = getHeader("In-Reply-To", " ");
        }
        if (null == header2) {
            header2 = header3;
        } else if (null != header3) {
            final String stringBuffer2 = MimeUtility.unfold(header3) +
                    " " +
                    header2;
            header2 = stringBuffer2;
        }
        if (null != header2) {
            createMimeMessage.setHeader("References", MimeUtility.fold(12, header2));
        }
        try {
            setFlags(answeredFlag, true);
        } catch (MessagingException unused) {
        }
        return createMimeMessage;
    }

    private Address[] eliminateDuplicates(Vector vector, Address[] addressArr) {
        Address[] addressArr2;
        boolean z;
        if (null == addressArr) {
            return null;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < addressArr.length; i3++) {
            int i4 = 0;
            while (true) {
                if (i4 >= vector.size()) {
                    z = false;
                    break;
                } else if (vector.elementAt(i4).equals(addressArr[i3])) {
                    i2++;
                    addressArr[i3] = null;
                    z = true;
                    break;
                } else {
                    i4++;
                }
            }
            if (!z) {
                vector.addElement(addressArr[i3]);
            }
        }
        if (0 == i2) {
            return addressArr;
        }
        if (addressArr instanceof InternetAddress[]) {
            addressArr2 = new InternetAddress[(addressArr.length - i2)];
        } else {
            addressArr2 = new Address[(addressArr.length - i2)];
        }
        int i5 = 0;
        for (Address address : addressArr) {
            if (null != address) {
                addressArr2[i5] = address;
                i5++;
            }
        }
        return addressArr2;
    }

    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        writeTo(outputStream, (String[]) null);
    }

    public void writeTo(OutputStream r4, String[] r5) throws IOException, MessagingException {
        /*
            r3 = this;
            boolean r0 = r3.saved
            if (r0 != 0) goto L_0x0007
            r3.saveChanges()
        L_0x0007:
            boolean r0 = r3.modified
            if (r0 == 0) goto L_0x000f
            javax.mail.internet.MimeBodyPart.writeTo(r3, r4, r5)
            return
        L_0x000f:
            java.util.Enumeration r5 = r3.getNonMatchingHeaderLines(r5)
            com.sun.mail.util.LineOutputStream r0 = new com.sun.mail.util.LineOutputStream
            r0.<init>(r4)
        L_0x0018:
            boolean r1 = r5.hasMoreElements()
            if (r1 == 0) goto L_0x0028
            java.lang.Object r1 = r5.nextElement()
            java.lang.String r1 = (java.lang.String) r1
            r0.writeln(r1)
            goto L_0x0018
        L_0x0028:
            r0.writeln()
            byte[] r5 = r3.content
            if (r5 != 0) goto L_0x0050
            r5 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r5]
            java.io.InputStream r0 = r3.getContentStream()     // Catch:{ all -> 0x0048 }
        L_0x0037:
            int r1 = r0.read(r5)     // Catch:{ all -> 0x0042 }
            if (r1 <= 0) goto L_0x0044
            r2 = 0
            r4.write(r5, r2, r1)     // Catch:{ all -> 0x0042 }
            goto L_0x0037
        L_0x0042:
            r4 = move-exception
            goto L_0x004a
        L_0x0044:
            r0.close()
            goto L_0x0053
        L_0x0048:
            r4 = move-exception
            r0 = 0
        L_0x004a:
            if (r0 == 0) goto L_0x004f
            r0.close()
        L_0x004f:
            throw r4
        L_0x0050:
            r4.write(r5)
        L_0x0053:
            r4.flush()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeMessage.writeTo(java.io.OutputStream, java.lang.String[]):void");
    }

    public String[] getHeader(String str) throws MessagingException {
        return this.headers.getHeader(str);
    }

    public String getHeader(String str, String str2) throws MessagingException {
        return this.headers.getHeader(str, str2);
    }

    public void setHeader(String str, String str2) throws MessagingException {
        this.headers.setHeader(str, str2);
    }

    public void addHeader(String str, String str2) throws MessagingException {
        this.headers.addHeader(str, str2);
    }

    public void removeHeader(String str) throws MessagingException {
        this.headers.removeHeader(str);
    }

    public Enumeration getAllHeaders() throws MessagingException {
        return this.headers.getAllHeaders();
    }

    public Enumeration getMatchingHeaders(String[] strArr) throws MessagingException {
        return this.headers.getMatchingHeaders(strArr);
    }

    public Enumeration getNonMatchingHeaders(String[] strArr) throws MessagingException {
        return this.headers.getNonMatchingHeaders(strArr);
    }

    public void addHeaderLine(String str) throws MessagingException {
        this.headers.addHeaderLine(str);
    }

    public Enumeration getAllHeaderLines() throws MessagingException {
        return this.headers.getAllHeaderLines();
    }

    public Enumeration getMatchingHeaderLines(String[] strArr) throws MessagingException {
        return this.headers.getMatchingHeaderLines(strArr);
    }

    public Enumeration getNonMatchingHeaderLines(String[] strArr) throws MessagingException {
        return this.headers.getNonMatchingHeaderLines(strArr);
    }

    public synchronized Flags getFlags() throws MessagingException {
        return (Flags) this.flags.clone();
    }

    public synchronized boolean isSet(Flags.Flag flag) throws MessagingException {
        return this.flags.contains(flag);
    }

    public synchronized void setFlags(Flags flags2, boolean z) throws MessagingException {
        if (z) {
            try {
                this.flags.add(flags2);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        } else {
            this.flags.remove(flags2);
        }
    }

    public void saveChanges() throws MessagingException {
        this.modified = true;
        this.saved = true;
        updateHeaders();
    }

    
    public void updateMessageID() throws MessagingException {
        final String stringBuffer = "<" +
                UniqueValue.getUniqueMessageIDValue(this.session) +
                ">";
        setHeader("Message-ID", stringBuffer);
    }

    public synchronized void updateHeaders() throws MessagingException {
        /*
            r3 = this;
            monitor-enter(r3)
            javax.mail.internet.MimeBodyPart.updateHeaders(r3)     // Catch:{ all -> 0x002c }
            java.lang.String r0 = "MIME-Version"
            java.lang.String r1 = "1.0"
            r3.setHeader(r0, r1)     // Catch:{ all -> 0x002c }
            r3.updateMessageID()     // Catch:{ all -> 0x002c }
            java.lang.Object r0 = r3.cachedContent     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0030
            javax.activation.DataHandler r0 = new javax.activation.DataHandler     // Catch:{ all -> 0x002c }
            java.lang.Object r1 = r3.cachedContent     // Catch:{ all -> 0x002c }
            java.lang.String r2 = r3.getContentType()     // Catch:{ all -> 0x002c }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x002c }
            r3.dh = r0     // Catch:{ all -> 0x002c }
            r0 = 0
            r3.cachedContent = r0     // Catch:{ all -> 0x002c }
            r3.content = r0     // Catch:{ all -> 0x002c }
            java.io.InputStream r1 = r3.contentStream     // Catch:{ all -> 0x002c }
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch:{ IOException -> 0x002e }
            goto L_0x002e
        L_0x002c:
            r0 = move-exception
            goto L_0x0032
        L_0x002e:
            r3.contentStream = r0     // Catch:{ all -> 0x002c }
        L_0x0030:
            monitor-exit(r3)
            return
        L_0x0032:
            monitor-exit(r3)     // Catch:{ all -> 0x002c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeMessage.updateHeaders():void");
    }

    
    public InternetHeaders createInternetHeaders(InputStream inputStream) throws MessagingException {
        return new InternetHeaders(inputStream);
    }

    
    public MimeMessage createMimeMessage(Session session) throws MessagingException {
        return new MimeMessage(session);
    }
}
