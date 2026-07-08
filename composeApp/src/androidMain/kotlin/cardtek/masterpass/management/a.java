package cardtek.masterpass.management;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import androidx.exifinterface.media.ExifInterface;
import cardtek.masterpass.util.MasterPassInfo;
import com.google.android.material.timepicker.TimeModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import i.b;
import i.c;
import i.g;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.Adler32;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/* loaded from: classes.dex */
public final class a {
    private final b se;
    private final Gson sf = new GsonBuilder().create();
    private final Context sg;

    public a(Context context) {
        this.sg = context;
        g gVar = new g();
        this.se = gVar;
        gVar.e("*.masterpassturkiye.com");
        this.se.i();
    }

    public static boolean b(int i2, int i3) {
        if (i2 > 0 && i2 <= 12) {
            try {
                String format = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2));
                String valueOf = String.valueOf(i3);
                if (valueOf.length() == 2) {
                    valueOf = "20".concat(valueOf);
                }
                String str = valueOf + format;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
                return Integer.valueOf(simpleDateFormat.format(simpleDateFormat.parse(str))).intValue() - Integer.valueOf(simpleDateFormat.format(Calendar.getInstance().getTime())).intValue() >= 0;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static String c(int i2, int i3) {
        String valueOf = String.valueOf(i3);
        String format = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i2));
        if (valueOf.length() == 4) {
            valueOf = valueOf.substring(2, 4);
        }
        return valueOf + format;
    }

    private static byte[] g(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i2 = 0; i2 < length; i2 += 2) {
            bArr[i2 / 2] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i2 + 1), 16));
        }
        return bArr;
    }

    public static boolean h(String str) throws Exception {
        String systemID = MasterPassInfo.getSystemID();
        SecretKeySpec secretKeySpec = new SecretKeySpec(g(MasterPassInfo.getSystemKey()), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
        instance.init(2, secretKeySpec);
        String[] split = new String(instance.doFinal(g(systemID))).split("\\.");
        ByteBuffer wrap = ByteBuffer.wrap(new byte[str.length()]);
        wrap.put(str.getBytes());
        String m = m(MessageDigest.getInstance("SHA-256").digest(wrap.array()));
        Adler32 adler32 = new Adler32();
        adler32.update(MasterPassInfo.getSystemKey().getBytes());
        return split[0].equalsIgnoreCase(MasterPassInfo.getClientID()) && split[1].equalsIgnoreCase(m.substring(0, 14)) && split[2].equalsIgnoreCase(Long.toHexString(adler32.getValue()));
    }

    private static String m(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b2 : bArr) {
            stringBuffer.append("0123456789ABCDEF".charAt((b2 & 240) >> 4));
            stringBuffer.append("0123456789ABCDEF".charAt(b2 & 15));
        }
        return stringBuffer.toString();
    }

    public String a(Object obj, String str) throws Exception {
        b bVar = this.se;
        StringBuilder sb = new StringBuilder();
        String url = MasterPassInfo.getUrl();
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        sb.append(url);
        sb.append(str);
        c d2 = bVar.d(sb.toString());
        d2.f(this.sf.toJson(obj));
        d2.put("clientId", MasterPassInfo.getClientID());
        d2.put("sendSms", MasterPassInfo.getSendSms());
        d2.put("sendSmsLanguage", MasterPassInfo.getLanguage());
        d2.put("dateTime", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Calendar.getInstance().getTime()));
        String string = Settings.Secure.getString(this.sg.getContentResolver(), "android_id");
        String str2 = Build.MANUFACTURER;
        int length = str2.length();
        String str3 = Build.MODEL;
        int length2 = length + str3.length();
        String str4 = Build.PRODUCT;
        ByteBuffer wrap = ByteBuffer.wrap(new byte[length2 + str4.length() + string.length()]);
        wrap.put(str2.getBytes());
        wrap.put(str3.getBytes());
        wrap.put(str4.getBytes());
        wrap.put(string.getBytes());
        d2.put("fP", m(MessageDigest.getInstance("SHA-256").digest(wrap.array())));
        d2.put("version", "2.2.9");
        d2.put("clientType", ExifInterface.GPS_MEASUREMENT_2D);
        d2.j();
        return this.se.a(d2).rR;
    }
}
