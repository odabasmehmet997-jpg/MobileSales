package javax.mail;

import javax.activation.DataSource;

public interface MultipartDataSource extends DataSource {
    BodyPart getBodyPart(int i2) throws MessagingException;
    int getCount();
}
