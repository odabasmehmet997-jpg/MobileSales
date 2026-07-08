package com.proje.mobilesales.core.tigerwcf.xml;

import android.util.Log;
import androidx.annotation.NonNull;
import com.proje.mobilesales.features.model.GenericData;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
public class SaxGenericResultParser<T> {
    private static final String TAG = "SaxGenericResultParser";
    public List<GenericData> tigerGetDataParser(String str) {
        List<GenericData> arrayList = new ArrayList<>();
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
            GenericResultHandler genericResultHandler = new GenericResultHandler();
            try {
                newSAXParser.parse(new InputSource(new StringReader(str)), genericResultHandler);
                arrayList = genericResultHandler.gettList();
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
