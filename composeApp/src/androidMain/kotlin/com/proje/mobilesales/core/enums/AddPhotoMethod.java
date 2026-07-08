package com.proje.mobilesales.core.enums;

public final class AddPhotoMethod {
    public static final Companion Companion;
    private final int value;
    public static final AddPhotoMethod GALLERY = new AddPhotoMethod(0);
    public static final AddPhotoMethod CAMERA = new AddPhotoMethod(1);

    public static AddPhotoMethod[] values() {
        return new AddPhotoMethod[]{GALLERY, CAMERA};
    }
    public int getValue() {
        return this.value;
    }
    private AddPhotoMethod(int value) {
        this.value = value;
    }
    static {
        Companion = new Companion();
    }
    public static final class Companion {
        public Companion() {
        }
        public AddPhotoMethod fromAddPhotoMethod(int r3) {
            for (AddPhotoMethod addPhotoMethod : values()) {
                if (addPhotoMethod.getValue() == r3) {
                    return addPhotoMethod;
                }
            }
            return CAMERA;
        }
    }
}
