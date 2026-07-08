package sun.mail.handlers;

import javax.activation.ActivationDataFlavor;

public class text_html extends sun.mail.handlers.text_plain {
    static   Class classjavalangString;
    private static final ActivationDataFlavor myDF;
    static {
        Class cls = classjavalangString;
        if (null == cls) {
            cls = ("java.lang.String").getClass()   ;
            classjavalangString = cls;
        }
        myDF = new ActivationDataFlavor(cls, "text/html", "HTML String");
    }
    public ActivationDataFlavor getDF() {
        return myDF;
    }
}
