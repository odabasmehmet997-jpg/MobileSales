package com.proje.mobilesales.core.reportparser;

import android.util.Log;
import java.io.StringReader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;



public class ReportDesignerParser {
    private static final String TAG = "com.proje.mobilesales.core.reportparser.ReportDesignerParser";

    public Report parseReportXml(String str) {
        Report report = null;
        try {
            final int indexOf = str.indexOf("<GRIDLAYOUT>");
            if (-1 != indexOf) {
                str = str.substring(0, indexOf) + str.substring(indexOf).replace("&lt;", "<").replace("&gt;", ">").replace("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "").replace("<?xml version=\"1.0\" encoding=\"utf-16\"?>", "");
            }
            final SAXParserFactory newInstance = SAXParserFactory.newInstance();
            try {
                newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
                newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
                newInstance.setXIncludeAware(false);
            } catch (final Exception e2) {
                Log.e(ReportDesignerParser.TAG, "parseReportDesigner: ", e2);
            }
            final SAXParser newSAXParser = newInstance.newSAXParser();
            final ReportDesignHandler reportDesignHandler = new ReportDesignHandler();
            try {
                newSAXParser.parse(new InputSource(new StringReader(str)), reportDesignHandler);
                report = reportDesignHandler.getReport();
                return report;
            } catch (final Exception e3) {
                Log.e(ReportDesignerParser.TAG, "parseReportDesigner: ", e3);
                return null;
            }
        } catch (final Exception e4) {
            Log.e(ReportDesignerParser.TAG, "parseReportDesigner: ", e4);
            return report;
        }
    }
}
