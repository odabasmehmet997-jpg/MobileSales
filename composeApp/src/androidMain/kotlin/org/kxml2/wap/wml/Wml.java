package org.kxml2.wap.wml;

import androidx.core.os.EnvironmentCompat;
import com.example.privacy_policy_lib.core.utils.RetrofitServiceFactory;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import org.kxml2.wap.WbxmlParser;
import org.kxml2.wap.WbxmlSerializer;
import org.simpleframework.xml.strategy.Name;

public abstract class Wml {
  public static final String[] TAG_TABLE = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "a", "td", "tr", "table", "p", "postfield", "anchor", "access", "b", "big", "br", "card", "do", "em", "fieldset", "go", "head", "i", "img", "input", "meta", "noop", "prev", "onevent", "optgroup", "option", "refresh", "select", "small", "strong", null, "template", "timer", "u", "setvar", "wml"};
  public static final String[] ATTR_START_TABLE = {"accept-charset", "align=bottom", "align=center", "align=left", "align=middle", "align=right", "align=top", "alt", FirebaseAnalytics.Param.CONTENT, null, "domain", "emptyok=false", "emptyok=true", "format", "height", "hspace", "ivalue", "iname", null, Constants.ScionAnalytics.PARAM_LABEL, "localsrc", "maxlength", "method=get", "method=post", "mode=nowrap", "mode=wrap", "multiple=false", "multiple=true", "name", "newcontext=false", "newcontext=true", "onpick", "onenterbackward", "onenterforward", "ontimer", "optimal=false", "optimal=true", "path", null, null, null, "scheme", "sendreferer=false", "sendreferer=true", "size", "src", "ordered=true", "ordered=false", "tabindex", "title", "type", "type=accept", "type=delete", "type=help", "type=password", "type=onpick", "type=onenterbackward", "type=onenterforward", "type=ontimer", null, null, null, null, null, "type=options", "type=prev", "type=reset", "type=text", "type=vnd.", "href", "href=http://", "href=https://", "value", "vspace", "width", "xml:lang", null, "align", "columns", Name.LABEL, Name.MARK, "forua=false", "forua=true", "src=http://", "src=https://", "http-equiv", "http-equiv=Content-Type", "content=application/vnd.wap.wmlc;charset=", "http-equiv=Expires", null, null};
  public static final String[] ATTR_VALUE_TABLE = {".com/", ".edu/", ".net/", ".org/", "accept", "bottom", "clear", "delete", "help", RetrofitServiceFactory.HTTP, "http://www.", RetrofitServiceFactory.HTTPS, "https://www.", null, "middle", "nowrap", "onpick", "onenterbackward", "onenterforward", "ontimer", "options", "password", "reset", null, "text", "top", EnvironmentCompat.MEDIA_UNKNOWN, "wrap", "www."};

  public static WbxmlParser createParser() {
    WbxmlParser wbxmlParser = new WbxmlParser();
    wbxmlParser.setTagTable(0, TAG_TABLE);
    wbxmlParser.setAttrStartTable(0, ATTR_START_TABLE);
    wbxmlParser.setAttrValueTable(0, ATTR_VALUE_TABLE);
    return wbxmlParser;
  }

  public static WbxmlSerializer createSerializer() {
    WbxmlSerializer wbxmlSerializer = new WbxmlSerializer();
    wbxmlSerializer.setTagTable(0, TAG_TABLE);
    wbxmlSerializer.setAttrStartTable(0, ATTR_START_TABLE);
    wbxmlSerializer.setAttrValueTable(0, ATTR_VALUE_TABLE);
    return wbxmlSerializer;
  }
}
