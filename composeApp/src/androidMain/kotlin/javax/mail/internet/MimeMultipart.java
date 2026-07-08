package javax.mail.internet;

import sun.mail.util.ASCIIUtility;
import sun.mail.util.LineOutputStream;
import sun.mail.util.PropUtil;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessageAware;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.MultipartDataSource;

public class MimeMultipart extends Multipart {
    private boolean allowEmpty;
    private final boolean bmparse;
    private final boolean complete;
    protected DataSource ds;
    private final boolean ignoreExistingBoundaryParameter;
    private final boolean ignoreMissingBoundaryParameter;
    private final boolean ignoreMissingEndBoundary;
    protected boolean parsed;
    private String preamble;

    public MimeMultipart() {
        this("mixed");
    }

    public MimeMultipart(String str) {
        this.ds = null;
        this.parsed = true;
        this.complete = true;
        this.preamble = null;
        this.ignoreMissingEndBoundary = true;
        this.ignoreMissingBoundaryParameter = true;
        this.ignoreExistingBoundaryParameter = false;
        this.allowEmpty = false;
        this.bmparse = true;
        String uniqueBoundaryValue = UniqueValue.getUniqueBoundaryValue();
        ContentType contentType = new ContentType("multipart", str, null);
        contentType.setParameter("boundary", uniqueBoundaryValue);
        this.contentType = contentType.toString();
    }

    public MimeMultipart(DataSource dataSource) throws MessagingException {
        this.ds = null;
        this.parsed = true;
        this.complete = true;
        this.preamble = null;
        this.ignoreMissingEndBoundary = true;
        this.ignoreMissingBoundaryParameter = true;
        this.ignoreExistingBoundaryParameter = false;
        this.allowEmpty = false;
        this.bmparse = true;
        if (dataSource instanceof MessageAware) {
            setParent(((MessageAware) dataSource).getMessageContext().part());
        }
        if (dataSource instanceof MultipartDataSource) {
            setMultipartDataSource((MultipartDataSource) dataSource);
            return;
        }
        this.parsed = false;
        this.ds = dataSource;
        this.contentType = dataSource.getContentType();
    }

    public synchronized void setSubType(String str) throws MessagingException {
        ContentType contentType = new ContentType(this.contentType);
        contentType.setSubType(str);
        this.contentType = contentType.toString();
    }

    public synchronized int getCount() throws MessagingException {
        parse();
        return super.getCount();
    }

    public synchronized BodyPart getBodyPart(int i2) throws MessagingException {
        parse();
        return super.getBodyPart(i2);
    }

    public synchronized BodyPart getBodyPart(String str) throws MessagingException {
        parse();
        int count = getCount();
        for (int i2 = 0; i2 < count; i2++) {
            MimeBodyPart mimeBodyPart = (MimeBodyPart) getBodyPart(i2);
            String contentID = mimeBodyPart.getContentID();
            if (null != contentID && contentID.equals(str)) {
                return mimeBodyPart;
            }
        }
        return null;
    }

    public boolean removeBodyPart(BodyPart bodyPart) throws MessagingException {
        parse();
        return super.removeBodyPart(bodyPart);
    }

    public void removeBodyPart(int i2) throws MessagingException {
        parse();
        super.removeBodyPart(i2);
    }

    public synchronized void addBodyPart(BodyPart bodyPart) throws MessagingException {
        parse();
        super.addBodyPart(bodyPart);
    }

    public synchronized void addBodyPart(BodyPart bodyPart, int i2) throws MessagingException {
        parse();
        super.addBodyPart(bodyPart, i2);
    }

    public synchronized boolean isComplete() throws MessagingException {
        parse();
        return this.complete;
    }

    public synchronized String getPreamble() throws MessagingException {
        parse();
        return this.preamble;
    }

    public synchronized void setPreamble(String str) throws MessagingException {
        this.preamble = str;
    }

    
    public synchronized void updateHeaders() throws MessagingException {
        parse();
        for (int i2 = 0; i2 < this.parts.size(); i2++) {
            ((MimeBodyPart) this.parts.elementAt(i2)).updateHeaders();
        }
    }

    public synchronized void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        try {
            parse();
            String stringBuffer2 = "--" +
                    new ContentType(this.contentType).getParameter("boundary");
            LineOutputStream lineOutputStream = new LineOutputStream(outputStream);
            String str = this.preamble;
            if (null != str) {
                byte[] bytes = ASCIIUtility.getBytes(str);
                lineOutputStream.write(bytes);
                if (!(0 >= bytes.length || 13 == bytes[bytes.length - 1] || 10 == bytes[bytes.length - 1])) {
                    lineOutputStream.writeln();
                }
            }
            if (0 == parts.size()) {
                boolean booleanSystemProperty = PropUtil.getBooleanSystemProperty("mail.mime.multipart.allowempty", false);
                this.allowEmpty = booleanSystemProperty;
                if (booleanSystemProperty) {
                    lineOutputStream.writeln(stringBuffer2);
                    lineOutputStream.writeln();
                } else {
                    final String stringBuffer3 = "Empty multipart: " +
                            this.contentType;
                    throw new MessagingException(stringBuffer3);
                }
            } else {
                for (int i2 = 0; i2 < this.parts.size(); i2++) {
                    lineOutputStream.writeln(stringBuffer2);
                    ((MimeBodyPart) this.parts.elementAt(i2)).writeTo(outputStream);
                    lineOutputStream.writeln();
                }
            }
            final String stringBuffer4 = stringBuffer2 +
                    "--";
            lineOutputStream.writeln(stringBuffer4);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void parse() throws MessagingException {
        /*
            r23 = this;
            r1 = r23
            monitor-enter(r23)
            boolean r0 = r1.parsed     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r23)
            return
        L_0x0009:
            java.lang.String r0 = "mail.mime.multipart.ignoremissingendboundary"
            r2 = 1
            boolean r0 = com.sun.mail.util.PropUtil.getBooleanSystemProperty(r0, r2)     // Catch:{ all -> 0x003a }
            r1.ignoreMissingEndBoundary = r0     // Catch:{ all -> 0x003a }
            java.lang.String r0 = "mail.mime.multipart.ignoremissingboundaryparameter"
            boolean r0 = com.sun.mail.util.PropUtil.getBooleanSystemProperty(r0, r2)     // Catch:{ all -> 0x003a }
            r1.ignoreMissingBoundaryParameter = r0     // Catch:{ all -> 0x003a }
            java.lang.String r0 = "mail.mime.multipart.ignoreexistingboundaryparameter"
            r3 = 0
            boolean r0 = com.sun.mail.util.PropUtil.getBooleanSystemProperty(r0, r3)     // Catch:{ all -> 0x003a }
            r1.ignoreExistingBoundaryParameter = r0     // Catch:{ all -> 0x003a }
            java.lang.String r0 = "mail.mime.multipart.allowempty"
            boolean r0 = com.sun.mail.util.PropUtil.getBooleanSystemProperty(r0, r3)     // Catch:{ all -> 0x003a }
            r1.allowEmpty = r0     // Catch:{ all -> 0x003a }
            java.lang.String r0 = "mail.mime.multipart.bmparse"
            boolean r0 = com.sun.mail.util.PropUtil.getBooleanSystemProperty(r0, r2)     // Catch:{ all -> 0x003a }
            r1.bmparse = r0     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x003d
            r23.parsebm()     // Catch:{ all -> 0x003a }
            monitor-exit(r23)
            return
        L_0x003a:
            r0 = move-exception
            goto L_0x02b7
        L_0x003d:
            javax.activation.DataSource r0 = r1.ds     // Catch:{ Exception -> 0x0055 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ Exception -> 0x0055 }
            boolean r4 = r0 instanceof java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0055 }
            if (r4 != 0) goto L_0x0058
            boolean r4 = r0 instanceof java.io.BufferedInputStream     // Catch:{ Exception -> 0x0055 }
            if (r4 != 0) goto L_0x0058
            boolean r4 = r0 instanceof javax.mail.internet.SharedInputStream     // Catch:{ Exception -> 0x0055 }
            if (r4 != 0) goto L_0x0058
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0055 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x0055 }
            goto L_0x0059
        L_0x0055:
            r0 = move-exception
            goto L_0x02af
        L_0x0058:
            r4 = r0
        L_0x0059:
            boolean r0 = r4 instanceof javax.mail.internet.SharedInputStream     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0062
            r0 = r4
            javax.mail.internet.SharedInputStream r0 = (javax.mail.internet.SharedInputStream) r0     // Catch:{ all -> 0x003a }
            goto L_0x0063
        L_0x0062:
            r0 = 0
        L_0x0063:
            javax.mail.internet.ContentType r6 = new javax.mail.internet.ContentType     // Catch:{ all -> 0x003a }
            java.lang.String r7 = r1.contentType     // Catch:{ all -> 0x003a }
            r6.<init>(r7)     // Catch:{ all -> 0x003a }
            boolean r7 = r1.ignoreExistingBoundaryParameter     // Catch:{ all -> 0x003a }
            if (r7 != 0) goto L_0x0088
            java.lang.String r7 = "boundary"
            java.lang.String r6 = r6.getParameter(r7)     // Catch:{ all -> 0x003a }
            if (r6 == 0) goto L_0x0088
            java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch:{ all -> 0x003a }
            r7.<init>()     // Catch:{ all -> 0x003a }
            java.lang.String r8 = "--"
            r7.append(r8)     // Catch:{ all -> 0x003a }
            r7.append(r6)     // Catch:{ all -> 0x003a }
            java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x003a }
            goto L_0x0089
        L_0x0088:
            r6 = 0
        L_0x0089:
            if (r6 != 0) goto L_0x009c
            boolean r7 = r1.ignoreMissingBoundaryParameter     // Catch:{ all -> 0x003a }
            if (r7 != 0) goto L_0x009c
            boolean r7 = r1.ignoreExistingBoundaryParameter     // Catch:{ all -> 0x003a }
            if (r7 == 0) goto L_0x0094
            goto L_0x009c
        L_0x0094:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException     // Catch:{ all -> 0x003a }
            java.lang.String r2 = "Missing boundary parameter"
            r0.<init>(r2)     // Catch:{ all -> 0x003a }
            throw r0     // Catch:{ all -> 0x003a }
        L_0x009c:
            com.sun.mail.util.LineInputStream r7 = new com.sun.mail.util.LineInputStream     // Catch:{ IOException -> 0x00c3 }
            r7.<init>(r4)     // Catch:{ IOException -> 0x00c3 }
            r8 = 0
            r9 = 0
        L_0x00a3:
            java.lang.String r10 = r7.readLine()     // Catch:{ IOException -> 0x00c3 }
            r11 = 9
            r12 = 32
            if (r10 == 0) goto L_0x0136
            int r13 = r10.length()     // Catch:{ IOException -> 0x00c3 }
            int r13 = r13 - r2
        L_0x00b2:
            if (r13 < 0) goto L_0x00c6
            char r14 = r10.charAt(r13)     // Catch:{ IOException -> 0x00c3 }
            if (r14 == r12) goto L_0x00bd
            if (r14 == r11) goto L_0x00bd
            goto L_0x00c6
        L_0x00bd:
            int r13 = r13 + -1
            goto L_0x00b2
        L_0x00c0:
            r0 = move-exception
            goto L_0x02ab
        L_0x00c3:
            r0 = move-exception
            goto L_0x02a3
        L_0x00c6:
            int r13 = r13 + 1
            java.lang.String r10 = r10.substring(r3, r13)     // Catch:{ IOException -> 0x00c3 }
            r13 = 2
            if (r6 == 0) goto L_0x00f1
            boolean r14 = r10.equals(r6)     // Catch:{ IOException -> 0x00c3 }
            if (r14 == 0) goto L_0x00d6
            goto L_0x0136
        L_0x00d6:
            int r14 = r10.length()     // Catch:{ IOException -> 0x00c3 }
            int r15 = r6.length()     // Catch:{ IOException -> 0x00c3 }
            int r15 = r15 + r13
            if (r14 != r15) goto L_0x010f
            boolean r14 = r10.startsWith(r6)     // Catch:{ IOException -> 0x00c3 }
            if (r14 == 0) goto L_0x010f
            java.lang.String r14 = "--"
            boolean r14 = r10.endsWith(r14)     // Catch:{ IOException -> 0x00c3 }
            if (r14 == 0) goto L_0x010f
            r10 = 0
            goto L_0x0136
        L_0x00f1:
            int r14 = r10.length()     // Catch:{ IOException -> 0x00c3 }
            if (r14 <= r13) goto L_0x010f
            java.lang.String r14 = "--"
            boolean r14 = r10.startsWith(r14)     // Catch:{ IOException -> 0x00c3 }
            if (r14 == 0) goto L_0x010f
            int r14 = r10.length()     // Catch:{ IOException -> 0x00c3 }
            r15 = 4
            if (r14 <= r15) goto L_0x010d
            boolean r14 = allDashes(r10)     // Catch:{ IOException -> 0x00c3 }
            if (r14 == 0) goto L_0x010d
            goto L_0x010f
        L_0x010d:
            r6 = r10
            goto L_0x0136
        L_0x010f:
            int r11 = r10.length()     // Catch:{ IOException -> 0x00c3 }
            if (r11 <= 0) goto L_0x00a3
            if (r9 != 0) goto L_0x0122
            java.lang.String r9 = "line.separator"
            java.lang.String r11 = "\n"
            java.lang.String r9 = java.lang.System.getProperty(r9, r11)     // Catch:{ SecurityException -> 0x0120 }
            goto L_0x0122
        L_0x0120:
            java.lang.String r9 = "\n"
        L_0x0122:
            if (r8 != 0) goto L_0x012e
            java.lang.StringBuffer r8 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x00c3 }
            int r11 = r10.length()     // Catch:{ IOException -> 0x00c3 }
            int r11 = r11 + r13
            r8.<init>(r11)     // Catch:{ IOException -> 0x00c3 }
        L_0x012e:
            r8.append(r10)     // Catch:{ IOException -> 0x00c3 }
            r8.append(r9)     // Catch:{ IOException -> 0x00c3 }
            goto L_0x00a3
        L_0x0136:
            if (r8 == 0) goto L_0x013e
            java.lang.String r8 = r8.toString()     // Catch:{ IOException -> 0x00c3 }
            r1.preamble = r8     // Catch:{ IOException -> 0x00c3 }
        L_0x013e:
            if (r10 != 0) goto L_0x0151
            boolean r0 = r1.allowEmpty     // Catch:{ IOException -> 0x00c3 }
            if (r0 == 0) goto L_0x0149
            r4.close()     // Catch:{ IOException -> 0x0147 }
        L_0x0147:
            monitor-exit(r23)
            return
        L_0x0149:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException     // Catch:{ IOException -> 0x00c3 }
            java.lang.String r2 = "Missing start boundary"
            r0.<init>(r2)     // Catch:{ IOException -> 0x00c3 }
            throw r0     // Catch:{ IOException -> 0x00c3 }
        L_0x0151:
            byte[] r6 = com.sun.mail.util.ASCIIUtility.getBytes((java.lang.String) r6)     // Catch:{ IOException -> 0x00c3 }
            int r8 = r6.length     // Catch:{ IOException -> 0x00c3 }
            r9 = 0
            r15 = r3
            r13 = r9
        L_0x015a:
            if (r15 != 0) goto L_0x029b
            if (r0 == 0) goto L_0x0183
            long r9 = r0.getPosition()     // Catch:{ IOException -> 0x00c3 }
        L_0x0162:
            java.lang.String r16 = r7.readLine()     // Catch:{ IOException -> 0x00c3 }
            if (r16 == 0) goto L_0x016f
            int r17 = r16.length()     // Catch:{ IOException -> 0x00c3 }
            if (r17 <= 0) goto L_0x016f
            goto L_0x0162
        L_0x016f:
            if (r16 != 0) goto L_0x0181
            boolean r0 = r1.ignoreMissingEndBoundary     // Catch:{ IOException -> 0x00c3 }
            if (r0 == 0) goto L_0x0179
            r1.complete = r3     // Catch:{ IOException -> 0x00c3 }
            goto L_0x029b
        L_0x0179:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException     // Catch:{ IOException -> 0x00c3 }
            java.lang.String r2 = "missing multipart end boundary"
            r0.<init>(r2)     // Catch:{ IOException -> 0x00c3 }
            throw r0     // Catch:{ IOException -> 0x00c3 }
        L_0x0181:
            r5 = 0
            goto L_0x0189
        L_0x0183:
            javax.mail.internet.InternetHeaders r16 = r1.createInternetHeaders(r4)     // Catch:{ IOException -> 0x00c3 }
            r5 = r16
        L_0x0189:
            boolean r17 = r4.markSupported()     // Catch:{ IOException -> 0x00c3 }
            if (r17 == 0) goto L_0x0293
            if (r0 != 0) goto L_0x0199
            java.io.ByteArrayOutputStream r17 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00c3 }
            r17.<init>()     // Catch:{ IOException -> 0x00c3 }
            r3 = r17
            goto L_0x019e
        L_0x0199:
            long r13 = r0.getPosition()     // Catch:{ IOException -> 0x00c3 }
            r3 = 0
        L_0x019e:
            r18 = r2
            r19 = -1
            r20 = -1
        L_0x01a4:
            if (r18 == 0) goto L_0x0217
            int r11 = r8 + 1004
            r4.mark(r11)     // Catch:{ IOException -> 0x00c3 }
            r11 = 0
        L_0x01ac:
            if (r11 >= r8) goto L_0x01bf
            int r12 = r4.read()     // Catch:{ IOException -> 0x00c3 }
            byte r2 = r6[r11]     // Catch:{ IOException -> 0x00c3 }
            r2 = r2 & 255(0xff, float:3.57E-43)
            if (r12 == r2) goto L_0x01b9
            goto L_0x01bf
        L_0x01b9:
            int r11 = r11 + 1
            r2 = 1
            r12 = 32
            goto L_0x01ac
        L_0x01bf:
            if (r11 != r8) goto L_0x01fb
            int r2 = r4.read()     // Catch:{ IOException -> 0x00c3 }
            r11 = 45
            if (r2 != r11) goto L_0x01d5
            int r12 = r4.read()     // Catch:{ IOException -> 0x00c3 }
            if (r12 != r11) goto L_0x01d5
            r11 = 1
            r1.complete = r11     // Catch:{ IOException -> 0x00c3 }
            r12 = 0
        L_0x01d3:
            r15 = 1
            goto L_0x022e
        L_0x01d5:
            r11 = 32
            if (r2 == r11) goto L_0x01f6
            r12 = 9
            if (r2 != r12) goto L_0x01de
            goto L_0x01f6
        L_0x01de:
            r11 = 10
            if (r2 != r11) goto L_0x01e3
            goto L_0x01f4
        L_0x01e3:
            r12 = 13
            if (r2 != r12) goto L_0x01fb
            r2 = 1
            r4.mark(r2)     // Catch:{ IOException -> 0x00c3 }
            int r2 = r4.read()     // Catch:{ IOException -> 0x00c3 }
            if (r2 == r11) goto L_0x01f4
            r4.reset()     // Catch:{ IOException -> 0x00c3 }
        L_0x01f4:
            r12 = 0
            goto L_0x022e
        L_0x01f6:
            int r2 = r4.read()     // Catch:{ IOException -> 0x00c3 }
            goto L_0x01d5
        L_0x01fb:
            r4.reset()     // Catch:{ IOException -> 0x00c3 }
            if (r3 == 0) goto L_0x0217
            r11 = r19
            r2 = -1
            if (r11 == r2) goto L_0x0214
            r3.write(r11)     // Catch:{ IOException -> 0x00c3 }
            r12 = r20
            if (r12 == r2) goto L_0x020f
            r3.write(r12)     // Catch:{ IOException -> 0x00c3 }
        L_0x020f:
            r19 = r2
            r20 = r19
            goto L_0x0220
        L_0x0214:
            r12 = r20
            goto L_0x021c
        L_0x0217:
            r11 = r19
            r12 = r20
            r2 = -1
        L_0x021c:
            r19 = r11
            r20 = r12
        L_0x0220:
            int r11 = r4.read()     // Catch:{ IOException -> 0x00c3 }
            if (r11 >= 0) goto L_0x0254
            boolean r2 = r1.ignoreMissingEndBoundary     // Catch:{ IOException -> 0x00c3 }
            if (r2 == 0) goto L_0x024c
            r12 = 0
            r1.complete = r12     // Catch:{ IOException -> 0x00c3 }
            goto L_0x01d3
        L_0x022e:
            if (r0 == 0) goto L_0x0239
            java.io.InputStream r2 = r0.newStream(r9, r13)     // Catch:{ IOException -> 0x00c3 }
            javax.mail.internet.MimeBodyPart r2 = r1.createMimeBodyPartIs(r2)     // Catch:{ IOException -> 0x00c3 }
            goto L_0x0241
        L_0x0239:
            byte[] r2 = r3.toByteArray()     // Catch:{ IOException -> 0x00c3 }
            javax.mail.internet.MimeBodyPart r2 = r1.createMimeBodyPart(r5, r2)     // Catch:{ IOException -> 0x00c3 }
        L_0x0241:
            super.addBodyPart(r2)     // Catch:{ IOException -> 0x00c3 }
            r3 = r12
            r2 = 1
            r11 = 9
            r12 = 32
            goto L_0x015a
        L_0x024c:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException     // Catch:{ IOException -> 0x00c3 }
            java.lang.String r2 = "missing multipart end boundary"
            r0.<init>(r2)     // Catch:{ IOException -> 0x00c3 }
            throw r0     // Catch:{ IOException -> 0x00c3 }
        L_0x0254:
            r2 = 13
            r12 = 0
            if (r11 == r2) goto L_0x026a
            r2 = 10
            if (r11 != r2) goto L_0x025e
            goto L_0x026a
        L_0x025e:
            if (r3 == 0) goto L_0x0263
            r3.write(r11)     // Catch:{ IOException -> 0x00c3 }
        L_0x0263:
            r18 = r12
            r2 = 1
            r12 = 32
            goto L_0x01a4
        L_0x026a:
            if (r0 == 0) goto L_0x0274
            long r13 = r0.getPosition()     // Catch:{ IOException -> 0x00c3 }
            r21 = 1
            long r13 = r13 - r21
        L_0x0274:
            r2 = 13
            if (r11 != r2) goto L_0x0286
            r2 = 1
            r4.mark(r2)     // Catch:{ IOException -> 0x00c3 }
            int r2 = r4.read()     // Catch:{ IOException -> 0x00c3 }
            r12 = 10
            if (r2 != r12) goto L_0x028f
            r20 = r2
        L_0x0286:
            r19 = r11
            r2 = 1
            r12 = 32
            r18 = 1
            goto L_0x01a4
        L_0x028f:
            r4.reset()     // Catch:{ IOException -> 0x00c3 }
            goto L_0x0286
        L_0x0293:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException     // Catch:{ IOException -> 0x00c3 }
            java.lang.String r2 = "Stream doesn't support mark"
            r0.<init>(r2)     // Catch:{ IOException -> 0x00c3 }
            throw r0     // Catch:{ IOException -> 0x00c3 }
        L_0x029b:
            r4.close()     // Catch:{ IOException -> 0x029e }
        L_0x029e:
            r0 = 1
            r1.parsed = r0     // Catch:{ all -> 0x003a }
            monitor-exit(r23)
            return
        L_0x02a3:
            javax.mail.MessagingException r2 = new javax.mail.MessagingException     // Catch:{ all -> 0x00c0 }
            java.lang.String r3 = "IO Error"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x00c0 }
            throw r2     // Catch:{ all -> 0x00c0 }
        L_0x02ab:
            r4.close()     // Catch:{ IOException -> 0x02ae }
        L_0x02ae:
            throw r0     // Catch:{ all -> 0x003a }
        L_0x02af:
            javax.mail.MessagingException r2 = new javax.mail.MessagingException     // Catch:{ all -> 0x003a }
            java.lang.String r3 = "No inputstream from datasource"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x003a }
            throw r2     // Catch:{ all -> 0x003a }
        L_0x02b7:
            monitor-exit(r23)     // Catch:{ all -> 0x003a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeMultipart.parse():void");
    }

    private synchronized void parsebm() throws MessagingException {
        /*
            r28 = this;
            r1 = r28
            monitor-enter(r28)
            boolean r0 = r1.parsed     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r28)
            return
        L_0x0009:
            javax.activation.DataSource r0 = r1.ds     // Catch:{ Exception -> 0x0024 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ Exception -> 0x0024 }
            boolean r2 = r0 instanceof java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0024 }
            if (r2 != 0) goto L_0x0027
            boolean r2 = r0 instanceof java.io.BufferedInputStream     // Catch:{ Exception -> 0x0024 }
            if (r2 != 0) goto L_0x0027
            boolean r2 = r0 instanceof javax.mail.internet.SharedInputStream     // Catch:{ Exception -> 0x0024 }
            if (r2 != 0) goto L_0x0027
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0024 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0024 }
            goto L_0x0028
        L_0x0021:
            r0 = move-exception
            goto L_0x0371
        L_0x0024:
            r0 = move-exception
            goto L_0x0369
        L_0x0027:
            r2 = r0
        L_0x0028:
            boolean r0 = r2 instanceof javax.mail.internet.SharedInputStream     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0031
            r0 = r2
            javax.mail.internet.SharedInputStream r0 = (javax.mail.internet.SharedInputStream) r0     // Catch:{ all -> 0x0021 }
            goto L_0x0032
        L_0x0031:
            r0 = 0
        L_0x0032:
            javax.mail.internet.ContentType r4 = new javax.mail.internet.ContentType     // Catch:{ all -> 0x0021 }
            java.lang.String r5 = r1.contentType     // Catch:{ all -> 0x0021 }
            r4.<init>(r5)     // Catch:{ all -> 0x0021 }
            boolean r5 = r1.ignoreExistingBoundaryParameter     // Catch:{ all -> 0x0021 }
            if (r5 != 0) goto L_0x0057
            java.lang.String r5 = "boundary"
            java.lang.String r4 = r4.getParameter(r5)     // Catch:{ all -> 0x0021 }
            if (r4 == 0) goto L_0x0057
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ all -> 0x0021 }
            r5.<init>()     // Catch:{ all -> 0x0021 }
            java.lang.String r6 = "--"
            r5.append(r6)     // Catch:{ all -> 0x0021 }
            r5.append(r4)     // Catch:{ all -> 0x0021 }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x0021 }
            goto L_0x0058
        L_0x0057:
            r4 = 0
        L_0x0058:
            if (r4 != 0) goto L_0x006b
            boolean r5 = r1.ignoreMissingBoundaryParameter     // Catch:{ all -> 0x0021 }
            if (r5 != 0) goto L_0x006b
            boolean r5 = r1.ignoreExistingBoundaryParameter     // Catch:{ all -> 0x0021 }
            if (r5 == 0) goto L_0x0063
            goto L_0x006b
        L_0x0063:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException     // Catch:{ all -> 0x0021 }
            java.lang.String r2 = "Missing boundary parameter"
            r0.<init>(r2)     // Catch:{ all -> 0x0021 }
            throw r0     // Catch:{ all -> 0x0021 }
        L_0x006b:
            com.sun.mail.util.LineInputStream r5 = new com.sun.mail.util.LineInputStream     // Catch:{ IOException -> 0x0095 }
            r5.<init>(r2)     // Catch:{ IOException -> 0x0095 }
            r6 = 0
            r7 = 0
        L_0x0072:
            java.lang.String r8 = r5.readLine()     // Catch:{ IOException -> 0x0095 }
            r9 = 9
            r10 = 32
            r11 = 2
            r12 = 0
            r13 = 1
            if (r8 == 0) goto L_0x0107
            int r14 = r8.length()     // Catch:{ IOException -> 0x0095 }
            int r14 = r14 - r13
        L_0x0084:
            if (r14 < 0) goto L_0x0098
            char r15 = r8.charAt(r14)     // Catch:{ IOException -> 0x0095 }
            if (r15 == r10) goto L_0x008f
            if (r15 == r9) goto L_0x008f
            goto L_0x0098
        L_0x008f:
            int r14 = r14 + -1
            goto L_0x0084
        L_0x0092:
            r0 = move-exception
            goto L_0x0365
        L_0x0095:
            r0 = move-exception
            goto L_0x035d
        L_0x0098:
            int r14 = r14 + 1
            java.lang.String r8 = r8.substring(r12, r14)     // Catch:{ IOException -> 0x0095 }
            if (r4 == 0) goto L_0x00c2
            boolean r14 = r8.equals(r4)     // Catch:{ IOException -> 0x0095 }
            if (r14 == 0) goto L_0x00a7
            goto L_0x0107
        L_0x00a7:
            int r14 = r8.length()     // Catch:{ IOException -> 0x0095 }
            int r15 = r4.length()     // Catch:{ IOException -> 0x0095 }
            int r15 = r15 + r11
            if (r14 != r15) goto L_0x00e0
            boolean r14 = r8.startsWith(r4)     // Catch:{ IOException -> 0x0095 }
            if (r14 == 0) goto L_0x00e0
            java.lang.String r14 = "--"
            boolean r14 = r8.endsWith(r14)     // Catch:{ IOException -> 0x0095 }
            if (r14 == 0) goto L_0x00e0
            r8 = 0
            goto L_0x0107
        L_0x00c2:
            int r14 = r8.length()     // Catch:{ IOException -> 0x0095 }
            if (r14 <= r11) goto L_0x00e0
            java.lang.String r14 = "--"
            boolean r14 = r8.startsWith(r14)     // Catch:{ IOException -> 0x0095 }
            if (r14 == 0) goto L_0x00e0
            int r14 = r8.length()     // Catch:{ IOException -> 0x0095 }
            r15 = 4
            if (r14 <= r15) goto L_0x00de
            boolean r14 = allDashes(r8)     // Catch:{ IOException -> 0x0095 }
            if (r14 == 0) goto L_0x00de
            goto L_0x00e0
        L_0x00de:
            r4 = r8
            goto L_0x0107
        L_0x00e0:
            int r9 = r8.length()     // Catch:{ IOException -> 0x0095 }
            if (r9 <= 0) goto L_0x0072
            if (r7 != 0) goto L_0x00f3
            java.lang.String r7 = "line.separator"
            java.lang.String r9 = "\n"
            java.lang.String r7 = java.lang.System.getProperty(r7, r9)     // Catch:{ SecurityException -> 0x00f1 }
            goto L_0x00f3
        L_0x00f1:
            java.lang.String r7 = "\n"
        L_0x00f3:
            if (r6 != 0) goto L_0x00ff
            java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch:{ IOException -> 0x0095 }
            int r9 = r8.length()     // Catch:{ IOException -> 0x0095 }
            int r9 = r9 + r11
            r6.<init>(r9)     // Catch:{ IOException -> 0x0095 }
        L_0x00ff:
            r6.append(r8)     // Catch:{ IOException -> 0x0095 }
            r6.append(r7)     // Catch:{ IOException -> 0x0095 }
            goto L_0x0072
        L_0x0107:
            if (r6 == 0) goto L_0x010f
            java.lang.String r6 = r6.toString()     // Catch:{ IOException -> 0x0095 }
            r1.preamble = r6     // Catch:{ IOException -> 0x0095 }
        L_0x010f:
            if (r8 != 0) goto L_0x0122
            boolean r0 = r1.allowEmpty     // Catch:{ IOException -> 0x0095 }
            if (r0 == 0) goto L_0x011a
            r2.close()     // Catch:{ IOException -> 0x0118 }
        L_0x0118:
            monitor-exit(r28)
            return
        L_0x011a:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException     // Catch:{ IOException -> 0x0095 }
            java.lang.String r3 = "Missing start boundary"
            r0.<init>(r3)     // Catch:{ IOException -> 0x0095 }
            throw r0     // Catch:{ IOException -> 0x0095 }
        L_0x0122:
            byte[] r4 = com.sun.mail.util.ASCIIUtility.getBytes((java.lang.String) r4)     // Catch:{ IOException -> 0x0095 }
            int r6 = r4.length     // Catch:{ IOException -> 0x0095 }
            r7 = 256(0x100, float:3.59E-43)
            int[] r7 = new int[r7]     // Catch:{ IOException -> 0x0095 }
            r8 = r12
        L_0x012c:
            if (r8 >= r6) goto L_0x0137
            byte r14 = r4[r8]     // Catch:{ IOException -> 0x0095 }
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r8 = r8 + 1
            r7[r14] = r8     // Catch:{ IOException -> 0x0095 }
            goto L_0x012c
        L_0x0137:
            int[] r8 = new int[r6]     // Catch:{ IOException -> 0x0095 }
            r14 = r6
        L_0x013a:
            if (r14 <= 0) goto L_0x015d
            int r15 = r6 + -1
        L_0x013e:
            if (r15 < r14) goto L_0x0151
            byte r3 = r4[r15]     // Catch:{ IOException -> 0x0095 }
            int r16 = r15 - r14
            byte r9 = r4[r16]     // Catch:{ IOException -> 0x0095 }
            if (r3 != r9) goto L_0x0158
            int r3 = r15 + -1
            r8[r3] = r14     // Catch:{ IOException -> 0x0095 }
            int r15 = r15 + -1
            r9 = 9
            goto L_0x013e
        L_0x0151:
            if (r15 <= 0) goto L_0x0158
            int r15 = r15 + -1
            r8[r15] = r14     // Catch:{ IOException -> 0x0095 }
            goto L_0x0151
        L_0x0158:
            int r14 = r14 + -1
            r9 = 9
            goto L_0x013a
        L_0x015d:
            int r3 = r6 + -1
            r8[r3] = r13     // Catch:{ IOException -> 0x0095 }
            r14 = 0
            r9 = r12
            r17 = r14
        L_0x0166:
            if (r9 != 0) goto L_0x0355
            if (r0 == 0) goto L_0x0190
            long r14 = r0.getPosition()     // Catch:{ IOException -> 0x0095 }
        L_0x016e:
            java.lang.String r16 = r5.readLine()     // Catch:{ IOException -> 0x0095 }
            if (r16 == 0) goto L_0x017b
            int r19 = r16.length()     // Catch:{ IOException -> 0x0095 }
            if (r19 <= 0) goto L_0x017b
            goto L_0x016e
        L_0x017b:
            if (r16 != 0) goto L_0x018d
            boolean r0 = r1.ignoreMissingEndBoundary     // Catch:{ IOException -> 0x0095 }
            if (r0 == 0) goto L_0x0185
            r1.complete = r12     // Catch:{ IOException -> 0x0095 }
            goto L_0x0355
        L_0x0185:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException     // Catch:{ IOException -> 0x0095 }
            java.lang.String r3 = "missing multipart end boundary"
            r0.<init>(r3)     // Catch:{ IOException -> 0x0095 }
            throw r0     // Catch:{ IOException -> 0x0095 }
        L_0x018d:
            r20 = 0
            goto L_0x0196
        L_0x0190:
            javax.mail.internet.InternetHeaders r16 = r1.createInternetHeaders(r2)     // Catch:{ IOException -> 0x0095 }
            r20 = r16
        L_0x0196:
            boolean r16 = r2.markSupported()     // Catch:{ IOException -> 0x0095 }
            if (r16 == 0) goto L_0x034d
            if (r0 != 0) goto L_0x01a6
            java.io.ByteArrayOutputStream r16 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0095 }
            r16.<init>()     // Catch:{ IOException -> 0x0095 }
            r21 = r16
            goto L_0x01ac
        L_0x01a6:
            long r17 = r0.getPosition()     // Catch:{ IOException -> 0x0095 }
            r21 = 0
        L_0x01ac:
            byte[] r10 = new byte[r6]     // Catch:{ IOException -> 0x0095 }
            byte[] r13 = new byte[r6]     // Catch:{ IOException -> 0x0095 }
            r11 = r12
            r22 = 1
        L_0x01b3:
            int r12 = r6 + 1004
            r2.mark(r12)     // Catch:{ IOException -> 0x0095 }
            r23 = r3
            r12 = 0
            int r3 = readFully(r2, r10, r12, r6)     // Catch:{ IOException -> 0x0095 }
            if (r3 >= r6) goto L_0x01e2
            boolean r9 = r1.ignoreMissingEndBoundary     // Catch:{ IOException -> 0x0095 }
            if (r9 == 0) goto L_0x01da
            if (r0 == 0) goto L_0x01cb
            long r17 = r0.getPosition()     // Catch:{ IOException -> 0x0095 }
        L_0x01cb:
            r9 = 0
            r1.complete = r9     // Catch:{ IOException -> 0x0095 }
            r26 = r4
            r24 = r5
            r22 = r10
            r4 = r17
            r9 = 1
            r12 = 0
            goto L_0x0282
        L_0x01da:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException     // Catch:{ IOException -> 0x0095 }
            java.lang.String r3 = "missing multipart end boundary"
            r0.<init>(r3)     // Catch:{ IOException -> 0x0095 }
            throw r0     // Catch:{ IOException -> 0x0095 }
        L_0x01e2:
            r12 = r23
        L_0x01e4:
            if (r12 < 0) goto L_0x01f8
            r24 = r5
            byte r5 = r10[r12]     // Catch:{ IOException -> 0x0095 }
            r25 = r9
            byte r9 = r4[r12]     // Catch:{ IOException -> 0x0095 }
            if (r5 == r9) goto L_0x01f1
            goto L_0x01fc
        L_0x01f1:
            int r12 = r12 + -1
            r5 = r24
            r9 = r25
            goto L_0x01e4
        L_0x01f8:
            r24 = r5
            r25 = r9
        L_0x01fc:
            if (r12 >= 0) goto L_0x02dd
            r5 = 13
            r9 = 10
            if (r22 != 0) goto L_0x021b
            int r12 = r11 + -1
            byte r12 = r13[r12]     // Catch:{ IOException -> 0x0095 }
            if (r12 == r5) goto L_0x020c
            if (r12 != r9) goto L_0x021b
        L_0x020c:
            if (r12 != r9) goto L_0x0219
            r12 = 2
            if (r11 < r12) goto L_0x0219
            int r12 = r11 + -2
            byte r12 = r13[r12]     // Catch:{ IOException -> 0x0095 }
            if (r12 != r5) goto L_0x0219
            r12 = 2
            goto L_0x021c
        L_0x0219:
            r12 = 1
            goto L_0x021c
        L_0x021b:
            r12 = 0
        L_0x021c:
            if (r22 != 0) goto L_0x022a
            if (r12 <= 0) goto L_0x0221
            goto L_0x022a
        L_0x0221:
            r26 = r4
            r4 = r10
            r10 = r20
            r12 = r21
            goto L_0x02c9
        L_0x022a:
            if (r0 == 0) goto L_0x0239
            long r17 = r0.getPosition()     // Catch:{ IOException -> 0x0095 }
            r22 = r10
            long r9 = (long) r6     // Catch:{ IOException -> 0x0095 }
            long r17 = r17 - r9
            long r9 = (long) r12     // Catch:{ IOException -> 0x0095 }
            long r17 = r17 - r9
            goto L_0x023b
        L_0x0239:
            r22 = r10
        L_0x023b:
            int r9 = r2.read()     // Catch:{ IOException -> 0x0095 }
            r10 = 45
            if (r9 != r10) goto L_0x0252
            int r5 = r2.read()     // Catch:{ IOException -> 0x0095 }
            if (r5 != r10) goto L_0x0252
            r5 = 1
            r1.complete = r5     // Catch:{ IOException -> 0x0095 }
            r26 = r4
            r4 = r17
            r9 = 1
            goto L_0x0282
        L_0x0252:
            r5 = 32
            if (r9 == r5) goto L_0x025a
            r10 = 9
            if (r9 != r10) goto L_0x0266
        L_0x025a:
            r26 = r4
            r9 = r20
            r5 = r21
            r4 = r22
            r10 = 13
            goto L_0x02cd
        L_0x0266:
            r5 = 10
            if (r9 != r5) goto L_0x026b
            goto L_0x027c
        L_0x026b:
            r10 = 13
            if (r9 != r10) goto L_0x02c1
            r9 = 1
            r2.mark(r9)     // Catch:{ IOException -> 0x0095 }
            int r9 = r2.read()     // Catch:{ IOException -> 0x0095 }
            if (r9 == r5) goto L_0x027c
            r2.reset()     // Catch:{ IOException -> 0x0095 }
        L_0x027c:
            r26 = r4
            r4 = r17
            r9 = r25
        L_0x0282:
            if (r0 == 0) goto L_0x028d
            java.io.InputStream r3 = r0.newStream(r14, r4)     // Catch:{ IOException -> 0x0095 }
            javax.mail.internet.MimeBodyPart r3 = r1.createMimeBodyPartIs(r3)     // Catch:{ IOException -> 0x0095 }
            goto L_0x02af
        L_0x028d:
            int r11 = r11 - r12
            if (r11 <= 0) goto L_0x0297
            r12 = r21
            r10 = 0
            r12.write(r13, r10, r11)     // Catch:{ IOException -> 0x0095 }
            goto L_0x0299
        L_0x0297:
            r12 = r21
        L_0x0299:
            boolean r10 = r1.complete     // Catch:{ IOException -> 0x0095 }
            if (r10 != 0) goto L_0x02a5
            if (r3 <= 0) goto L_0x02a5
            r11 = r22
            r10 = 0
            r12.write(r11, r10, r3)     // Catch:{ IOException -> 0x0095 }
        L_0x02a5:
            byte[] r3 = r12.toByteArray()     // Catch:{ IOException -> 0x0095 }
            r10 = r20
            javax.mail.internet.MimeBodyPart r3 = r1.createMimeBodyPart(r10, r3)     // Catch:{ IOException -> 0x0095 }
        L_0x02af:
            super.addBodyPart(r3)     // Catch:{ IOException -> 0x0095 }
            r17 = r4
            r3 = r23
            r5 = r24
            r4 = r26
            r10 = 32
            r11 = 2
            r12 = 0
            r13 = 1
            goto L_0x0166
        L_0x02c1:
            r26 = r4
            r10 = r20
            r12 = r21
            r4 = r22
        L_0x02c9:
            r9 = r10
            r5 = r12
            r12 = 0
            goto L_0x02e4
        L_0x02cd:
            int r21 = r2.read()     // Catch:{ IOException -> 0x0095 }
            r22 = r4
            r20 = r9
            r9 = r21
            r4 = r26
            r21 = r5
            goto L_0x0252
        L_0x02dd:
            r26 = r4
            r4 = r10
            r9 = r20
            r5 = r21
        L_0x02e4:
            int r3 = r12 + 1
            byte r10 = r4[r12]     // Catch:{ IOException -> 0x0095 }
            r10 = r10 & 127(0x7f, float:1.78E-43)
            r10 = r7[r10]     // Catch:{ IOException -> 0x0095 }
            int r3 = r3 - r10
            r10 = r8[r12]     // Catch:{ IOException -> 0x0095 }
            int r3 = java.lang.Math.max(r3, r10)     // Catch:{ IOException -> 0x0095 }
            r10 = 2
            if (r3 >= r10) goto L_0x0323
            if (r0 != 0) goto L_0x0301
            r3 = 1
            if (r11 <= r3) goto L_0x0301
            int r3 = r11 + -1
            r12 = 0
            r5.write(r13, r12, r3)     // Catch:{ IOException -> 0x0095 }
        L_0x0301:
            r2.reset()     // Catch:{ IOException -> 0x0095 }
            r12 = r11
            r10 = 1
            r1.skipFully(r2, r10)     // Catch:{ IOException -> 0x0095 }
            r3 = 1
            if (r12 < r3) goto L_0x031b
            int r11 = r12 + -1
            byte r10 = r13[r11]     // Catch:{ IOException -> 0x0095 }
            r11 = 0
            r13[r11] = r10     // Catch:{ IOException -> 0x0095 }
            byte r10 = r4[r11]     // Catch:{ IOException -> 0x0095 }
            r13[r3] = r10     // Catch:{ IOException -> 0x0095 }
            r10 = 0
            r11 = 2
            goto L_0x033b
        L_0x031b:
            r3 = 0
            byte r10 = r4[r3]     // Catch:{ IOException -> 0x0095 }
            r13[r3] = r10     // Catch:{ IOException -> 0x0095 }
            r10 = 0
            r11 = 1
            goto L_0x033b
        L_0x0323:
            r12 = r11
            if (r12 <= 0) goto L_0x032d
            if (r0 != 0) goto L_0x032d
            r10 = 0
            r5.write(r13, r10, r12)     // Catch:{ IOException -> 0x0095 }
            goto L_0x032e
        L_0x032d:
            r10 = 0
        L_0x032e:
            r2.reset()     // Catch:{ IOException -> 0x0095 }
            long r11 = (long) r3     // Catch:{ IOException -> 0x0095 }
            r1.skipFully(r2, r11)     // Catch:{ IOException -> 0x0095 }
            r11 = r3
            r27 = r13
            r13 = r4
            r4 = r27
        L_0x033b:
            r21 = r5
            r20 = r9
            r12 = r10
            r22 = r12
            r3 = r23
            r5 = r24
            r9 = r25
            r10 = r4
            r4 = r26
            goto L_0x01b3
        L_0x034d:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException     // Catch:{ IOException -> 0x0095 }
            java.lang.String r3 = "Stream doesn't support mark"
            r0.<init>(r3)     // Catch:{ IOException -> 0x0095 }
            throw r0     // Catch:{ IOException -> 0x0095 }
        L_0x0355:
            r2.close()     // Catch:{ IOException -> 0x0358 }
        L_0x0358:
            r0 = 1
            r1.parsed = r0     // Catch:{ all -> 0x0021 }
            monitor-exit(r28)
            return
        L_0x035d:
            javax.mail.MessagingException r3 = new javax.mail.MessagingException     // Catch:{ all -> 0x0092 }
            java.lang.String r4 = "IO Error"
            r3.<init>(r4, r0)     // Catch:{ all -> 0x0092 }
            throw r3     // Catch:{ all -> 0x0092 }
        L_0x0365:
            r2.close()     // Catch:{ IOException -> 0x0368 }
        L_0x0368:
            throw r0     // Catch:{ all -> 0x0021 }
        L_0x0369:
            javax.mail.MessagingException r2 = new javax.mail.MessagingException     // Catch:{ all -> 0x0021 }
            java.lang.String r3 = "No inputstream from datasource"
            r2.<init>(r3, r0)     // Catch:{ all -> 0x0021 }
            throw r2     // Catch:{ all -> 0x0021 }
        L_0x0371:
            monitor-exit(r28)     // Catch:{ all -> 0x0021 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeMultipart.parsebm():void");
    }

    private static boolean allDashes(String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            if ('-' != str.charAt(i2)) {
                return false;
            }
        }
        return true;
    }

    private static int readFully(InputStream inputStream, byte[] bArr, int i2, int i3) throws IOException {
        int i4 = 0;
        if (0 == i3) {
            return 0;
        }
        while (0 < i3) {
            int read = inputStream.read(bArr, i2, i3);
            if (0 >= read) {
                break;
            }
            i2 += read;
            i4 += read;
            i3 -= read;
        }
        if (0 < i4) {
            return i4;
        }
        return -1;
    }

    private void skipFully(InputStream inputStream, long j2) throws IOException {
        while (0 < j2) {
            long skip = inputStream.skip(j2);
            if (0 < skip) {
                j2 -= skip;
            } else {
                throw new EOFException("can't skip");
            }
        }
    }

    
    public InternetHeaders createInternetHeaders(InputStream inputStream) throws MessagingException {
        return new InternetHeaders(inputStream);
    }

    
    public MimeBodyPart createMimeBodyPart(InternetHeaders internetHeaders, byte[] bArr) throws MessagingException {
        return new MimeBodyPart(internetHeaders, bArr);
    }

    
    public MimeBodyPart createMimeBodyPart(InputStream inputStream) throws MessagingException {
        return new MimeBodyPart(inputStream);
    }

    private MimeBodyPart createMimeBodyPartIs(InputStream inputStream) throws MessagingException {
        try {
            return createMimeBodyPart(inputStream);
        } finally {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }
}
