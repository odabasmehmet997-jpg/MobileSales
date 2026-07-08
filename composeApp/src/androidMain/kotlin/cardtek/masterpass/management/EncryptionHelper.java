package cardtek.masterpass.management;

import android.util.Base64;
import cardtek.masterpass.attributes.MasterPassEditText;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

public class EncryptionHelper {
    aa masterpassCrypto = new aa();

    public String getEncData(MasterPassEditText masterPassEditText) throws Exception {
        aa aaVar = this.masterpassCrypto;
        byte[] bytes = masterPassEditText.getRawText().getBytes(StandardCharsets.US_ASCII);
        String str = aaVar.tq;
        String str2 = aaVar.tr;
        PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, Base64.decode(str, 0)), new BigInteger(Base64.decode(str2, 0))));
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, generatePublic);
        return aaVar.n(instance.doFinal(bytes));
    }
}
