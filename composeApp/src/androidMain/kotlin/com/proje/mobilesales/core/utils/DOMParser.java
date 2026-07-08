package com.proje.mobilesales.core.utils;

import android.util.Log;
import androidx.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParser {
    private static DOMParser instance;
    private static final Object lock = new Object();
    private DOMParser() {
    }
    public static DOMParser newInstance() {
        synchronized (lock) {
            try {
                if (instance == null) {
                    instance = new DOMParser();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return instance;
    }
    public Document parseXml(String str) throws IOException, SAXException, ParserConfigurationException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(Charset.forName(str.indexOf("ISO-8859-1") == -1 ? "ISO-8859-9" : "ISO-8859-1")));
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        try {
            newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            newInstance.setXIncludeAware(false);
            newInstance.setExpandEntityReferences(false);
        } catch (Exception e2) {
            Log.e("DOMParser", "parseXml", e2);
        }
        return newInstance.newDocumentBuilder().parse(byteArrayInputStream);
    }
    public Document parseXml(String str, String str2) throws IOException, SAXException, ParserConfigurationException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(Charset.forName(str2)));
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        try {
            newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            newInstance.setXIncludeAware(false);
            newInstance.setExpandEntityReferences(false);
        } catch (Exception e2) {
            Log.e("DOMParser", "parseXml", e2);
        }
        return newInstance.newDocumentBuilder().parse(byteArrayInputStream);
    }
    public NodeList getChildListByParentNode(Document document, String str) {
        return document.getElementsByTagName(str);
    }
    public String getElementTextByPath(Document document, String str) {
        try {
            Object evaluate = XPathFactory.newInstance().newXPath().compile(str).evaluate(document, XPathConstants.STRING);
            if (evaluate != null) {
                return evaluate.toString();
            }
            return null;
        } catch (Exception e2) {
            Log.e("XPATH", "getElementTextByPath" + str, e2);
            return null;
        }
    }
    public NodeList getNodesByPath(Document document, String str) {
        try {
            Object evaluate = XPathFactory.newInstance().newXPath().compile(str).evaluate(document, XPathConstants.STRING);
            if (evaluate != null) {
                return (NodeList) evaluate;
            }
            return null;
        } catch (Exception e2) {
            Log.e("XPATH", "getNodesByPath:" + str, e2);
            return null;
        }
    }
    public NodeList getChildListByParentNode(Node node) {
        if (node.hasChildNodes()) {
            return node.getChildNodes();
        }
        return null;
    }
    public NodeList getChildListByParentNode(Element element) {
        if (element.hasChildNodes()) {
            return element.getChildNodes();
        }
        return null;
    }
    public Node getChildNodeByNodeList(NodeList nodeList, String str) {
        Node node = null;
        for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
            node = nodeList.item(i2);
            if (node.getNodeName().equals(str)) {
                return node;
            }
        }
        return node;
    }
    public NodeList getNodeByTagName(Document document, String str) {
        return document.getElementsByTagName(str);
    }
    public Node getNodeByTagNamee(Node node, String str) {
        if (node != null && node.hasChildNodes()) {
            NodeList childNodes = node.getChildNodes();
            if (childNodes.getLength() > 0) {
                return childNodes.item(0).getNodeName().equals(str) ? node : getNodeByTagNamee(childNodes.item(0), str);
            }
        }
        return null;
    }
    public Node getParentNodeByChildName(Node node) {
        return node.getParentNode();
    }
}
