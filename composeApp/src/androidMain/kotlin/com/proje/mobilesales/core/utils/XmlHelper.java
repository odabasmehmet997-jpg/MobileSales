package com.proje.mobilesales.core.utils;

import kotlin.jvm.internal.Intrinsics;
import org.w3c.dom.Element;

public final class XmlHelper {
    public String getXmlLine(String fieldName, String str) {
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        if (str == null || Intrinsics.areEqual(str, "") || Intrinsics.areEqual(str, "null")) {
            return "";
        }
        return '<' + fieldName + '>' + str + "</" + fieldName + '>';
    }
    public String getElement(Element element, String str) {
        Intrinsics.checkNotNullParameter(element, "element");
        if (element.getElementsByTagName(str).item(0) == null) {
            return "";
        }
        String textContent = element.getElementsByTagName(str).item(0).getTextContent();
        Intrinsics.checkNotNullExpressionValue(textContent, "getTextContent(...)");
        return textContent;
    }
    public float getElementFloat(Element element, String str) {
        Intrinsics.checkNotNullParameter(element, "element");
        try {
            return Float.parseFloat(getElement(element, str));
        } catch (Exception unused) {
            return 0.0f;
        }
    }
}
