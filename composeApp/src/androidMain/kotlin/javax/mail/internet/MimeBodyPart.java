package javax.mail.internet;

import sun.mail.util.ASCIIUtility;
import sun.mail.util.FolderClosedIOException;
import sun.mail.util.LineOutputStream;
import sun.mail.util.MessageRemovedIOException;
import sun.mail.util.MimeUtil;
import sun.mail.util.PropUtil;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.FolderClosedException;
import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;

import org.springframework.http.HttpHeaders;

public class MimeBodyPart extends BodyPart implements MimePart {
    static final boolean cacheMultipart = PropUtil.getBooleanSystemProperty("mail.mime.cachemultipart", true);
    private static final boolean decodeFileName = PropUtil.getBooleanSystemProperty("mail.mime.decodefilename", false);
    private static final boolean encodeFileName = PropUtil.getBooleanSystemProperty("mail.mime.encodefilename", false);
    private static final boolean ignoreMultipartEncoding = PropUtil.getBooleanSystemProperty("mail.mime.ignoremultipartencoding", true);
    private static final boolean setContentTypeFileName = PropUtil.getBooleanSystemProperty("mail.mime.setcontenttypefilename", true);
    private static final boolean setDefaultTextCharset = PropUtil.getBooleanSystemProperty("mail.mime.setdefaulttextcharset", true);
    private Object cachedContent;
    protected byte[] content;
    protected InputStream contentStream;
    protected DataHandler dh;
    protected InternetHeaders headers;

    public int getLineCount() throws MessagingException {
        return -1;
    }

    public MimeBodyPart() {
        this.headers = new InternetHeaders();
    }

    public MimeBodyPart(InputStream inputStream) throws MessagingException {
        if (!(inputStream instanceof ByteArrayInputStream) && !(inputStream instanceof BufferedInputStream) && !(inputStream instanceof SharedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        this.headers = new InternetHeaders(inputStream);
        if (inputStream instanceof final SharedInputStream sharedInputStream) {
            this.contentStream = sharedInputStream.newStream(sharedInputStream.getPosition(), -1);
            return;
        }
        try {
            this.content = ASCIIUtility.getBytes(inputStream);
        } catch (IOException e2) {
            throw new MessagingException("Error reading input stream", e2);
        }
    }

    public MimeBodyPart(InternetHeaders internetHeaders, byte[] bArr) throws MessagingException {
        this.headers = internetHeaders;
        this.content = bArr;
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
        return isMimeType(this, str);
    }

    public String getDisposition() throws MessagingException {
        return getDisposition(this);
    }

    public void setDisposition(String str) throws MessagingException {
        setDisposition(this, str);
    }

    public String getEncoding() throws MessagingException {
        return getEncoding(this);
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

    public String[] getContentLanguage() throws MessagingException {
        return getContentLanguage(this);
    }

    public void setContentLanguage(String[] strArr) throws MessagingException {
        setContentLanguage(this, strArr);
    }

    public String getDescription() throws MessagingException {
        return getDescription(this);
    }

    public void setDescription(String str) throws MessagingException {
        setDescription(str, (String) null);
    }

    public void setDescription(String str, String str2) throws MessagingException {
        setDescription(this, str, str2);
    }

    public String getFileName() throws MessagingException {
        return getFileName(this);
    }

    public void setFileName(String str) throws MessagingException {
        setFileName(this, str);
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
            return new ByteArrayInputStream(this.content);
        }
        throw new MessagingException("No MimeBodyPart content");
    }

    public InputStream getRawInputStream() throws MessagingException {
        return getContentStream();
    }

    public DataHandler getDataHandler() throws MessagingException {
        if (null == dh) {
            this.dh = new MimePartDataHandler(new MimePartDataSource(this));
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
            if (cacheMultipart && (((content2 instanceof Multipart) || (content2 instanceof Message)) && !(null == content && null == contentStream))) {
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

    public void setDataHandler(DataHandler dataHandler) throws MessagingException {
        this.dh = dataHandler;
        this.cachedContent = null;
        invalidateContentHeaders(this);
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
        setText(this, str, str2, "plain");
    }

    public void setText(String str, String str2, String str3) throws MessagingException {
        setText(this, str, str2, str3);
    }

    public void setContent(Multipart multipart) throws MessagingException {
        setDataHandler(new DataHandler(multipart, multipart.getContentType()));
        multipart.setParent(this);
    }

    public void attachFile(File file) throws IOException, MessagingException {
        FileDataSource fileDataSource = new FileDataSource(file);
        setDataHandler(new DataHandler((DataSource) fileDataSource));
        setFileName(fileDataSource.getName());
    }

    public void attachFile(String str) throws IOException, MessagingException {
        attachFile(new File(str));
    }

    public void saveFile(File r5) throws IOException, MessagingException {
        /*
            r4 = this;
            r0 = 0
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0027 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x0027 }
            r2.<init>(r5)     // Catch:{ all -> 0x0027 }
            r1.<init>(r2)     // Catch:{ all -> 0x0027 }
            java.io.InputStream r0 = r4.getInputStream()     // Catch:{ all -> 0x001e }
            r5 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r5]     // Catch:{ all -> 0x001e }
        L_0x0013:
            int r2 = r0.read(r5)     // Catch:{ all -> 0x001e }
            if (r2 <= 0) goto L_0x0020
            r3 = 0
            r1.write(r5, r3, r2)     // Catch:{ all -> 0x001e }
            goto L_0x0013
        L_0x001e:
            r5 = move-exception
            goto L_0x0029
        L_0x0020:
            r0.close()     // Catch:{ IOException -> 0x0023 }
        L_0x0023:
            r1.close()     // Catch:{ IOException -> 0x0026 }
        L_0x0026:
            return
        L_0x0027:
            r5 = move-exception
            r1 = r0
        L_0x0029:
            if (r0 == 0) goto L_0x002e
            r0.close()     // Catch:{ IOException -> 0x002e }
        L_0x002e:
            if (r1 == 0) goto L_0x0033
            r1.close()     // Catch:{ IOException -> 0x0033 }
        L_0x0033:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeBodyPart.saveFile(java.io.File):void");
    }

    public void saveFile(String str) throws IOException, MessagingException {
        saveFile(new File(str));
    }

    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        writeTo(this, outputStream, (String[]) null);
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

    
    public void updateHeaders() throws MessagingException {
        updateHeaders(this);
        if (null != cachedContent) {
            this.dh = new DataHandler(this.cachedContent, getContentType());
            this.cachedContent = null;
            this.content = null;
            InputStream inputStream = this.contentStream;
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
            }
            this.contentStream = null;
        }
    }

    static boolean isMimeType(MimePart mimePart, String str) throws MessagingException {
        try {
            return new ContentType(mimePart.getContentType()).match(str);
        } catch (ParseException unused) {
            return mimePart.getContentType().equalsIgnoreCase(str);
        }
    }

    static void setText(MimePart mimePart, String str, String str2, String str3) throws MessagingException {
        if (null == str2) {
            if (1 != MimeUtility.checkAscii(str)) {
                str2 = MimeUtility.getDefaultMIMECharset();
            } else {
                str2 = "us-ascii";
            }
        }
        final String stringBuffer = "text/" +
                str3 +
                "; charset=" +
                MimeUtility.quote(str2, HeaderTokenizer.MIME);
        mimePart.setContent(str, stringBuffer);
    }

    static String getDisposition(MimePart mimePart) throws MessagingException {
        String header = mimePart.getHeader(HttpHeaders.CONTENT_DISPOSITION, null);
        if (null == header) {
            return null;
        }
        return new ContentDisposition(header).getDisposition();
    }

    static void setDisposition(MimePart mimePart, String str) throws MessagingException {
        if (null == str) {
            mimePart.removeHeader(HttpHeaders.CONTENT_DISPOSITION);
            return;
        }
        String header = mimePart.getHeader(HttpHeaders.CONTENT_DISPOSITION, null);
        if (null != header) {
            ContentDisposition contentDisposition = new ContentDisposition(header);
            contentDisposition.setDisposition(str);
            str = contentDisposition.toString();
        }
        mimePart.setHeader(HttpHeaders.CONTENT_DISPOSITION, str);
    }

    static String getDescription(MimePart mimePart) throws MessagingException {
        String header = mimePart.getHeader("Content-Description", null);
        if (null == header) {
            return null;
        }
        try {
            return MimeUtility.decodeText(MimeUtility.unfold(header));
        } catch (UnsupportedEncodingException unused) {
            return header;
        }
    }

    static void setDescription(MimePart mimePart, String str, String str2) throws MessagingException {
        if (null == str) {
            mimePart.removeHeader("Content-Description");
            return;
        }
        try {
            mimePart.setHeader("Content-Description", MimeUtility.fold(21, MimeUtility.encodeText(str, str2, (String) null)));
        } catch (UnsupportedEncodingException e2) {
            throw new MessagingException("Encoding error", e2);
        }
    }

    static String getFileName(MimePart mimePart) throws MessagingException {
        String cleanContentType;
        String header = mimePart.getHeader(HttpHeaders.CONTENT_DISPOSITION, null);
        String parameter = null != header ? new ContentDisposition(header).getParameter("filename") : null;
        if (null == parameter && null != (cleanContentType = MimeUtil.cleanContentType(mimePart, mimePart.getHeader(HttpHeaders.CONTENT_TYPE, null)))) {
            try {
                parameter = new ContentType(cleanContentType).getParameter("name");
            } catch (ParseException unused) {
            }
        }
        if (!decodeFileName || null == parameter) {
            return parameter;
        }
        try {
            return MimeUtility.decodeText(parameter);
        } catch (UnsupportedEncodingException e2) {
            throw new MessagingException("Can't decode filename", e2);
        }
    }

    static void setFileName(MimePart mimePart, String str) throws MessagingException {
        String cleanContentType;
        if (encodeFileName && null != str) {
            try {
                str = MimeUtility.encodeText(str);
            } catch (UnsupportedEncodingException e2) {
                throw new MessagingException("Can't encode filename", e2);
            }
        }
        String header = mimePart.getHeader(HttpHeaders.CONTENT_DISPOSITION, null);
        if (null == header) {
            header = Part.ATTACHMENT;
        }
        ContentDisposition contentDisposition = new ContentDisposition(header);
        contentDisposition.setParameter("filename", str);
        mimePart.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        if (setContentTypeFileName && null != (cleanContentType = MimeUtil.cleanContentType(mimePart, mimePart.getHeader(HttpHeaders.CONTENT_TYPE, null)))) {
            try {
                ContentType contentType = new ContentType(cleanContentType);
                contentType.setParameter("name", str);
                mimePart.setHeader(HttpHeaders.CONTENT_TYPE, contentType.toString());
            } catch (ParseException unused) {
            }
        }
    }

    static String[] getContentLanguage(MimePart mimePart) throws MessagingException {
        String header = mimePart.getHeader(HttpHeaders.CONTENT_LANGUAGE, null);
        if (null == header) {
            return null;
        }
        HeaderTokenizer headerTokenizer = new HeaderTokenizer(header, HeaderTokenizer.MIME);
        Vector vector = new Vector();
        while (true) {
            HeaderTokenizer.Token next = headerTokenizer.next();
            int type = next.type();
            if (-4 == type) {
                break;
            } else if (-1 == type) {
                vector.addElement(next.value());
            }
        }
        if (0 == vector.size()) {
            return null;
        }
        String[] strArr = new String[vector.size()];
        vector.copyInto(strArr);
        return strArr;
    }

    static void setContentLanguage(MimePart mimePart, String[] strArr) throws MessagingException {
        StringBuffer stringBuffer = new StringBuffer(strArr[0]);
        int length = 18 + strArr[0].length();
        for (int i2 = 1; i2 < strArr.length; i2++) {
            stringBuffer.append(',');
            int i3 = length + 1;
            if (76 < i3) {
                stringBuffer.append("\r\n\t");
                i3 = 8;
            }
            stringBuffer.append(strArr[i2]);
            length = i3 + strArr[i2].length();
        }
        mimePart.setHeader(HttpHeaders.CONTENT_LANGUAGE, stringBuffer.toString());
    }

    static String getEncoding(MimePart mimePart) throws MessagingException {
        HeaderTokenizer.Token next;
        int type;
        String header = mimePart.getHeader("Content-Transfer-Encoding", null);
        if (null == header) {
            return null;
        }
        String trim = header.trim();
        if ("7bit".equalsIgnoreCase(trim) || "8bit".equalsIgnoreCase(trim) || "quoted-printable".equalsIgnoreCase(trim) || "binary".equalsIgnoreCase(trim) || "base64".equalsIgnoreCase(trim)) {
            return trim;
        }
        HeaderTokenizer headerTokenizer = new HeaderTokenizer(trim, HeaderTokenizer.MIME);
        do {
            next = headerTokenizer.next();
            type = next.type();
            if (-4 == type) {
                return trim;
            }
        } while (-1 != type);
        return next.value();
    }

    static void setEncoding(MimePart mimePart, String str) throws MessagingException {
        mimePart.setHeader("Content-Transfer-Encoding", str);
    }

    static String restrictEncoding(MimePart mimePart, String str) throws MessagingException {
        String contentType;
        if (!ignoreMultipartEncoding || null == str || "7bit".equalsIgnoreCase(str) || "8bit".equalsIgnoreCase(str) || "binary".equalsIgnoreCase(str) || null == (contentType = mimePart.getContentType())) {
            return str;
        }
        try {
            ContentType contentType2 = new ContentType(contentType);
            if (contentType2.match("multipart/*")) {
                return null;
            }
            if (!contentType2.match("message/*") || PropUtil.getBooleanSystemProperty("mail.mime.allowencodedmessages", false)) {
                return str;
            }
            return null;
        } catch (ParseException unused) {
        }
        return contentType;
    }

    static void updateHeaders(MimePart r9) throws MessagingException {
        /*
            java.lang.String r0 = "charset"
            java.lang.String r1 = "Content-Type"
            javax.activation.DataHandler r2 = r9.getDataHandler()
            if (r2 != 0) goto L_0x000b
            return
        L_0x000b:
            java.lang.String r3 = r2.getContentType()     // Catch:{ IOException -> 0x0038 }
            java.lang.String[] r4 = r9.getHeader(r1)     // Catch:{ IOException -> 0x0038 }
            r5 = 0
            r6 = 1
            if (r4 != 0) goto L_0x0019
            r4 = r6
            goto L_0x001a
        L_0x0019:
            r4 = r5
        L_0x001a:
            javax.mail.internet.ContentType r7 = new javax.mail.internet.ContentType     // Catch:{ IOException -> 0x0038 }
            r7.<init>(r3)     // Catch:{ IOException -> 0x0038 }
            java.lang.String r8 = "multipart/*"
            boolean r8 = r7.match((java.lang.String) r8)     // Catch:{ IOException -> 0x0038 }
            if (r8 == 0) goto L_0x0087
            boolean r5 = r9 instanceof javax.mail.internet.MimeBodyPart     // Catch:{ IOException -> 0x0038 }
            if (r5 == 0) goto L_0x003b
            r5 = r9
            javax.mail.internet.MimeBodyPart r5 = (javax.mail.internet.MimeBodyPart) r5     // Catch:{ IOException -> 0x0038 }
            java.lang.Object r5 = r5.cachedContent     // Catch:{ IOException -> 0x0038 }
            if (r5 == 0) goto L_0x0033
            goto L_0x0050
        L_0x0033:
            java.lang.Object r5 = r2.getContent()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0050
        L_0x0038:
            r9 = move-exception
            goto L_0x00fb
        L_0x003b:
            boolean r5 = r9 instanceof javax.mail.internet.MimeMessage     // Catch:{ IOException -> 0x0038 }
            if (r5 == 0) goto L_0x004c
            r5 = r9
            javax.mail.internet.MimeMessage r5 = (javax.mail.internet.MimeMessage) r5     // Catch:{ IOException -> 0x0038 }
            java.lang.Object r5 = r5.cachedContent     // Catch:{ IOException -> 0x0038 }
            if (r5 == 0) goto L_0x0047
            goto L_0x0050
        L_0x0047:
            java.lang.Object r5 = r2.getContent()     // Catch:{ IOException -> 0x0038 }
            goto L_0x0050
        L_0x004c:
            java.lang.Object r5 = r2.getContent()     // Catch:{ IOException -> 0x0038 }
        L_0x0050:
            boolean r8 = r5 instanceof javax.mail.internet.MimeMultipart     // Catch:{ IOException -> 0x0038 }
            if (r8 == 0) goto L_0x005b
            javax.mail.internet.MimeMultipart r5 = (javax.mail.internet.MimeMultipart) r5     // Catch:{ IOException -> 0x0038 }
            r5.updateHeaders()     // Catch:{ IOException -> 0x0038 }
        L_0x0059:
            r5 = r6
            goto L_0x0090
        L_0x005b:
            javax.mail.MessagingException r9 = new javax.mail.MessagingException     // Catch:{ IOException -> 0x0038 }
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x0038 }
            r0.<init>()     // Catch:{ IOException -> 0x0038 }
            java.lang.String r1 = "MIME part of type \""
            r0.append(r1)     // Catch:{ IOException -> 0x0038 }
            r0.append(r3)     // Catch:{ IOException -> 0x0038 }
            java.lang.String r1 = "\" contains object of type "
            r0.append(r1)     // Catch:{ IOException -> 0x0038 }
            java.lang.Class r1 = r5.getClass()     // Catch:{ IOException -> 0x0038 }
            java.lang.String r1 = r1.getName()     // Catch:{ IOException -> 0x0038 }
            r0.append(r1)     // Catch:{ IOException -> 0x0038 }
            java.lang.String r1 = " instead of MimeMultipart"
            r0.append(r1)     // Catch:{ IOException -> 0x0038 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x0038 }
            r9.<init>(r0)     // Catch:{ IOException -> 0x0038 }
            throw r9     // Catch:{ IOException -> 0x0038 }
        L_0x0087:
            java.lang.String r8 = "message/rfc822"
            boolean r8 = r7.match((java.lang.String) r8)     // Catch:{ IOException -> 0x0038 }
            if (r8 == 0) goto L_0x0090
            goto L_0x0059
        L_0x0090:
            boolean r6 = r2 instanceof javax.mail.internet.MimeBodyPart.MimePartDataHandler     // Catch:{ IOException -> 0x0038 }
            if (r6 == 0) goto L_0x0095
            return
        L_0x0095:
            if (r5 != 0) goto L_0x00d6
            java.lang.String r5 = "Content-Transfer-Encoding"
            java.lang.String[] r5 = r9.getHeader(r5)     // Catch:{ IOException -> 0x0038 }
            if (r5 != 0) goto L_0x00a6
            java.lang.String r2 = javax.mail.internet.MimeUtility.getEncoding((javax.activation.DataHandler) r2)     // Catch:{ IOException -> 0x0038 }
            setEncoding(r9, r2)     // Catch:{ IOException -> 0x0038 }
        L_0x00a6:
            if (r4 == 0) goto L_0x00d6
            boolean r2 = setDefaultTextCharset     // Catch:{ IOException -> 0x0038 }
            if (r2 == 0) goto L_0x00d6
            java.lang.String r2 = "text/*"
            boolean r2 = r7.match((java.lang.String) r2)     // Catch:{ IOException -> 0x0038 }
            if (r2 == 0) goto L_0x00d6
            java.lang.String r2 = r7.getParameter(r0)     // Catch:{ IOException -> 0x0038 }
            if (r2 != 0) goto L_0x00d6
            java.lang.String r2 = r9.getEncoding()     // Catch:{ IOException -> 0x0038 }
            if (r2 == 0) goto L_0x00cb
            java.lang.String r3 = "7bit"
            boolean r2 = r2.equalsIgnoreCase(r3)     // Catch:{ IOException -> 0x0038 }
            if (r2 == 0) goto L_0x00cb
            java.lang.String r2 = "us-ascii"
            goto L_0x00cf
        L_0x00cb:
            java.lang.String r2 = javax.mail.internet.MimeUtility.getDefaultMIMECharset()     // Catch:{ IOException -> 0x0038 }
        L_0x00cf:
            r7.setParameter(r0, r2)     // Catch:{ IOException -> 0x0038 }
            java.lang.String r3 = r7.toString()     // Catch:{ IOException -> 0x0038 }
        L_0x00d6:
            if (r4 == 0) goto L_0x00fa
            java.lang.String r0 = "Content-Disposition"
            r2 = 0
            java.lang.String r0 = r9.getHeader(r0, r2)     // Catch:{ IOException -> 0x0038 }
            if (r0 == 0) goto L_0x00f7
            javax.mail.internet.ContentDisposition r2 = new javax.mail.internet.ContentDisposition     // Catch:{ IOException -> 0x0038 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0038 }
            java.lang.String r0 = "filename"
            java.lang.String r0 = r2.getParameter(r0)     // Catch:{ IOException -> 0x0038 }
            if (r0 == 0) goto L_0x00f7
            java.lang.String r2 = "name"
            r7.setParameter(r2, r0)     // Catch:{ IOException -> 0x0038 }
            java.lang.String r3 = r7.toString()     // Catch:{ IOException -> 0x0038 }
        L_0x00f7:
            r9.setHeader(r1, r3)     // Catch:{ IOException -> 0x0038 }
        L_0x00fa:
            return
        L_0x00fb:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException
            java.lang.String r1 = "IOException updating headers"
            r0.<init>(r1, r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeBodyPart.updateHeaders(javax.mail.internet.MimePart):void");
    }

    static void invalidateContentHeaders(MimePart mimePart) throws MessagingException {
        mimePart.removeHeader(HttpHeaders.CONTENT_TYPE);
        mimePart.removeHeader("Content-Transfer-Encoding");
    }

    static void writeTo(MimePart mimePart, OutputStream outputStream, String[] strArr) throws IOException, MessagingException {
        LineOutputStream lineOutputStream;
        if (outputStream instanceof LineOutputStream) {
            lineOutputStream = (LineOutputStream) outputStream;
        } else {
            lineOutputStream = new LineOutputStream(outputStream);
        }
        Enumeration nonMatchingHeaderLines = mimePart.getNonMatchingHeaderLines(strArr);
        while (nonMatchingHeaderLines.hasMoreElements()) {
            lineOutputStream.writeln((String) nonMatchingHeaderLines.nextElement());
        }
        lineOutputStream.writeln();
        InputStream inputStream = null;
        try {
            if (mimePart.getDataHandler() instanceof MimePartDataHandler) {
                if (mimePart instanceof MimeBodyPart) {
                    inputStream = ((MimeBodyPart) mimePart).getContentStream();
                } else if (mimePart instanceof MimeMessage) {
                    inputStream = ((MimeMessage) mimePart).getContentStream();
                }
            }
            if (null != inputStream) {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (0 >= read) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                }
            } else {
                outputStream = MimeUtility.encode(outputStream, restrictEncoding(mimePart, mimePart.getEncoding()));
                mimePart.getDataHandler().writeTo(outputStream);
            }
            if (null != inputStream) {
                inputStream.close();
            }
            outputStream.flush();
        } catch (Throwable th) {
            if (null != inputStream) {
                inputStream.close();
            }
            throw th;
        }
    }

    static class MimePartDataHandler extends DataHandler {
        public MimePartDataHandler(DataSource dataSource) {
            super(dataSource);
        }
    }
}
