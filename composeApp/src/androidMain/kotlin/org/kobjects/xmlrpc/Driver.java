package org.kobjects.xmlrpc;

import org.kobjects.xml.XmlReader;

import java.io.FileReader;
import java.nio.charset.StandardCharsets;

public class Driver {
    public static void main(String[] strArr) throws Exception {
        new XmlRpcParser(new XmlReader(new FileReader(strArr[0]))).parseResponse();
    }
}
