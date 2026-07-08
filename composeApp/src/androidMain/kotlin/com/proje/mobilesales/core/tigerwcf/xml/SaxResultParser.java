package com.proje.mobilesales.core.tigerwcf.xml;

import android.util.Log;
import androidx.annotation.NonNull;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;

public class SaxResultParser<T> {
    private static final String TAG = "SaxResultParser";
    public List<T> tigerGetDataParser(@NonNull String str, Class<T> cls) {
        List<T> arrayList = new ArrayList<>();
        try {
            SAXParserFactory newInstance = SAXParserFactory.newInstance();
            try {
                newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
                newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
                newInstance.setXIncludeAware(false);
            } catch (Exception e2) {
                Log.e(TAG, "tigerGetDataParser: ", e2);
            }
            SAXParser newSAXParser = newInstance.newSAXParser();
            ResultHandler resultHandler = new ResultHandler();
            try {
                resultHandler.setaClass(cls);
                newSAXParser.parse(new InputSource(new StringReader(str)), resultHandler);
                arrayList = resultHandler.gettList();
                return arrayList;
            } catch (Exception e3) {
                Log.e(TAG, "tigerGetDataParser: ", e3);
                return arrayList;
            }
        } catch (Exception e4) {
            Log.e(TAG, "tigerGetDataParser: ", e4);
            return arrayList;
        }
    }
}
