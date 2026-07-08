package org.simpleframework.xml.transform;

import java.net.URL;


class URLTransform implements Transform<URL> {
    URLTransform() {
    }

    @Override // org.simpleframework.xml.transform.Transform
    public URL read(final String str) throws Exception {
        return new URL(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final URL url) throws Exception {
        return url.toString();
    }
}
