package org.simpleframework.xml.stream;

import org.xmlpull.p026v1.XmlPullParser;
import org.xmlpull.p026v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.Reader;


class PullProvider implements Provider {
    private final XmlPullParserFactory factory;

    public PullProvider() throws Exception {
        final XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
        factory = newInstance;
        newInstance.setNamespaceAware(true);
    }

    
    public EventReader provide(final InputStream inputStream) throws Exception {
        final XmlPullParser newPullParser = factory.newPullParser();
        if (null != inputStream) {
            newPullParser.setInput(inputStream, null);
        }
        return new PullReader(newPullParser);
    }

    
    public EventReader provide(final Reader reader) throws Exception {
        final XmlPullParser newPullParser = factory.newPullParser();
        if (null != reader) {
            newPullParser.setInput(reader);
        }
        return new PullReader(newPullParser);
    }
}
