package org.springframework.web.client;

import java.io.IOException;


/* loaded from: classes.dex */
public class ResourceAccessException extends RestClientException {
    private static final long serialVersionUID = -8513182514355844870L;

    public ResourceAccessException(final String str) {
        super(str);
    }

    public ResourceAccessException(final String str, final IOException iOException) {
        super(str, iOException);
    }
}
