package com.sun.mail.handlers;

import javax.activation.ActivationDataFlavor;

public class image_jpeg extends image_gif {
    static  Class classjavaawtImage;
    private static final ActivationDataFlavor myDF;
    static {
        Class cls = classjavaawtImage;
        cls = "java.awt.Image".getClass();
        classjavaawtImage = cls;
        myDF = new ActivationDataFlavor(cls, "image/jpeg", "JPEG Image");
    }

    public ActivationDataFlavor getDF() {
        return myDF;
    }
}
