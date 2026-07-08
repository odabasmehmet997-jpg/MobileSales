package com.github.mikephil.charting.animation;

public enum Easing {
    ;

    public static EasingFunction getEasingFunctionFromOption(EasingOption easingOption) {
        return switch (C11971.f806xe0165a6e[easingOption.ordinal()]) {
            case 2 -> EasingFunctions.EaseInQuad;
            case 3 -> EasingFunctions.EaseOutQuad;
            case 4 -> EasingFunctions.EaseInOutQuad;
            case 5 -> EasingFunctions.EaseInCubic;
            case 6 -> EasingFunctions.EaseOutCubic;
            case 7 -> EasingFunctions.EaseInOutCubic;
            case 8 -> EasingFunctions.EaseInQuart;
            case 9 -> EasingFunctions.EaseOutQuart;
            case 10 -> EasingFunctions.EaseInOutQuart;
            case 11 -> EasingFunctions.EaseInSine;
            case 12 -> EasingFunctions.EaseOutSine;
            case 13 -> EasingFunctions.EaseInOutSine;
            case 14 -> EasingFunctions.EaseInExpo;
            case 15 -> EasingFunctions.EaseOutExpo;
            case 16 -> EasingFunctions.EaseInOutExpo;
            case 17 -> EasingFunctions.EaseInCirc;
            case 18 -> EasingFunctions.EaseOutCirc;
            case 19 -> EasingFunctions.EaseInOutCirc;
            case 20 -> EasingFunctions.EaseInElastic;
            case 21 -> EasingFunctions.EaseOutElastic;
            case 22 -> EasingFunctions.EaseInOutElastic;
            case 23 -> EasingFunctions.EaseInBack;
            case 24 -> EasingFunctions.EaseOutBack;
            case 25 -> EasingFunctions.EaseInOutBack;
            case 26 -> EasingFunctions.EaseInBounce;
            case 27 -> EasingFunctions.EaseOutBounce;
            case 28 -> EasingFunctions.EaseInOutBounce;
            default -> EasingFunctions.Linear;
        };
    }

    public enum EasingOption {
        Linear,
        EaseInQuad,
        EaseOutQuad,
        EaseInOutQuad,
        EaseInCubic,
        EaseOutCubic,
        EaseInOutCubic,
        EaseInQuart,
        EaseOutQuart,
        EaseInOutQuart,
        EaseInSine,
        EaseOutSine,
        EaseInOutSine,
        EaseInExpo,
        EaseOutExpo,
        EaseInOutExpo,
        EaseInCirc,
        EaseOutCirc,
        EaseInOutCirc,
        EaseInElastic,
        EaseOutElastic,
        EaseInOutElastic,
        EaseInBack,
        EaseOutBack,
        EaseInOutBack,
        EaseInBounce,
        EaseOutBounce,
        EaseInOutBounce
    }

    public enum C11971 {
        ;
        public static final int[] f806xe0165a6e;

        static {
            int[] iArr = new int[EasingOption.values ().length];
            f806xe0165a6e = iArr;
            try {
                iArr[EasingOption.Linear.ordinal ()] = 1;
            } catch (NoSuchFieldError ignored) {
            }
            try {
                f806xe0165a6e[EasingOption.EaseInQuad.ordinal ()] = 2;
            } catch (NoSuchFieldError ignored) {
            }
            try {
                f806xe0165a6e[EasingOption.EaseOutQuad.ordinal ()] = 3;
            } catch (NoSuchFieldError unused3) {
                throw new RuntimeException(unused3);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInOutQuad.ordinal ()] = 4;
            } catch (NoSuchFieldError unused4) {
                throw new RuntimeException(unused4);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInCubic.ordinal ()] = 5;
            } catch (NoSuchFieldError unused5) {
                throw new RuntimeException(unused5);
            }
            try {
                f806xe0165a6e[EasingOption.EaseOutCubic.ordinal ()] = 6;
            } catch (NoSuchFieldError unused6) {
                throw new RuntimeException(unused6);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInOutCubic.ordinal ()] = 7;
            } catch (NoSuchFieldError unused7) {
                throw new RuntimeException(unused7);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInQuart.ordinal ()] = 8;
            } catch (NoSuchFieldError unused8) {
                throw new RuntimeException(unused8);
            }
            try {
                f806xe0165a6e[EasingOption.EaseOutQuart.ordinal ()] = 9;
            } catch (NoSuchFieldError unused9) {
                throw new RuntimeException(unused9);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInOutQuart.ordinal ()] = 10;
            } catch (NoSuchFieldError unused10) {
                throw new RuntimeException(unused10);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInSine.ordinal ()] = 11;
            } catch (NoSuchFieldError unused11) {
                throw new RuntimeException(unused11);
            }
            try {
                f806xe0165a6e[EasingOption.EaseOutSine.ordinal ()] = 12;
            } catch (NoSuchFieldError unused12) {
                throw new RuntimeException(unused12);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInOutSine.ordinal ()] = 13;
            } catch (NoSuchFieldError unused13) {
                throw new RuntimeException(unused13);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInExpo.ordinal ()] = 14;
            } catch (NoSuchFieldError unused14) {
                throw new RuntimeException(unused14);
            }
            try {
                f806xe0165a6e[EasingOption.EaseOutExpo.ordinal ()] = 15;
            } catch (NoSuchFieldError unused15) {
                throw new RuntimeException(unused15);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInOutExpo.ordinal ()] = 16;
            } catch (NoSuchFieldError unused16) {
                throw new RuntimeException(unused16);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInCirc.ordinal ()] = 17;
            } catch (NoSuchFieldError unused17) {
                throw new RuntimeException(unused17);
            }
            try {
                f806xe0165a6e[EasingOption.EaseOutCirc.ordinal ()] = 18;
            } catch (NoSuchFieldError unused18) {
                throw new RuntimeException(unused18);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInOutCirc.ordinal ()] = 19;
            } catch (NoSuchFieldError unused19) {
                throw new RuntimeException(unused19);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInElastic.ordinal ()] = 20;
            } catch (NoSuchFieldError unused20) {
                throw new RuntimeException(unused20);
            }
            try {
                f806xe0165a6e[EasingOption.EaseOutElastic.ordinal ()] = 21;
            } catch (NoSuchFieldError unused21) {
                throw new RuntimeException(unused21);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInOutElastic.ordinal ()] = 22;
            } catch (NoSuchFieldError unused22) {
                throw new RuntimeException(unused22);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInBack.ordinal ()] = 23;
            } catch (NoSuchFieldError unused23) {
                throw new RuntimeException(unused23);
            }
            try {
                f806xe0165a6e[EasingOption.EaseOutBack.ordinal ()] = 24;
            } catch (NoSuchFieldError unused24) {
                throw new RuntimeException(unused24);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInOutBack.ordinal ()] = 25;
            } catch (NoSuchFieldError unused25) {
                throw new RuntimeException(unused25);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInBounce.ordinal ()] = 26;
            } catch (NoSuchFieldError unused26) {
                throw new RuntimeException(unused26);
            }
            try {
                f806xe0165a6e[EasingOption.EaseOutBounce.ordinal ()] = 27;
            } catch (NoSuchFieldError unused27) {
                throw new RuntimeException(unused27);
            }
            try {
                f806xe0165a6e[EasingOption.EaseInOutBounce.ordinal ()] = 28;
            } catch (NoSuchFieldError unused28) {
                throw new RuntimeException(unused28);
            }
        }
    }

    public enum EasingFunctions {
        ;
        public static final EasingFunction Linear = f -> f;
        public static final EasingFunction EaseInQuad = f -> f * f;
        public static final EasingFunction EaseOutQuad = f -> (-f) * (f - 2.0f);
        public static final EasingFunction EaseInOutQuad = f -> {
            float f2 = f / 0.5f;
            if (1.0f > f2) {
                return 0.5f * f2 * f2;
            }
            float f3 = f2 - 1.0f;
            return ((f3 * (f3 - 2.0f)) - 1.0f) * -0.5f;
        };
        public static final EasingFunction EaseInCubic = f -> f * f * f;
        public static final EasingFunction EaseOutCubic = f -> {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2) + 1.0f;
        };
        public static final EasingFunction EaseInOutCubic = f -> {
            float f2 = f / 0.5f;
            if (1.0f > f2) {
                return 0.5f * f2 * f2 * f2;
            }
            float f3 = f2 - 2.0f;
            return ((f3 * f3 * f3) + 2.0f) * 0.5f;
        };
        public static final EasingFunction EaseInQuart = f -> f * f * f * f;
        public static final EasingFunction EaseOutQuart = f -> {
            float f2 = f - 1.0f;
            return -((((f2 * f2) * f2) * f2) - 1.0f);
        };
        public static final EasingFunction EaseInOutQuart = f -> {
            float f2 = f / 0.5f;
            if (1.0f > f2) {
                return 0.5f * f2 * f2 * f2 * f2;
            }
            float f3 = f2 - 2.0f;
            return ((((f3 * f3) * f3) * f3) - 2.0f) * -0.5f;
        };
        public static final EasingFunction EaseInSine = f -> (-((float) Math.cos (f * 1.5707963267948966d))) + 1.0f;
        public static final EasingFunction EaseOutSine = f -> (float) Math.sin (f * 1.5707963267948966d);
        public static final EasingFunction EaseInOutSine = f -> (((float) Math.cos (f * 3.141592653589793d)) - 1.0f) * -0.5f;
        public static final EasingFunction EaseInExpo = f -> {
            if (0.0f == f) {
                return 0.0f;
            }
            return (float) Math.pow (2.0d, (f - 1.0f) * 10.0f);
        };
        public static final EasingFunction EaseOutExpo = f -> {
            if (1.0f == f) {
                return 1.0f;
            }
            return -((float) Math.pow (2.0d, (f + 1.0f) * -10.0f));
        };
        public static final EasingFunction EaseInOutExpo = f -> {
            float f2;
            if (0.0f == f) {
                return 0.0f;
            }
            if (1.0f == f) {
                return 1.0f;
            }
            float f3 = f / 0.5f;
            if (1.0f > f3) {
                f2 = (float) Math.pow (2.0d, (f3 - 1.0f) * 10.0f);
            } else {
                f2 = (-((float) Math.pow (2.0d, (f3 - 1.0f) * -10.0f))) + 2.0f;
            }
            return f2 * 0.5f;
        };
        public static final EasingFunction EaseInCirc = f -> -(((float) Math.sqrt (1.0f - (f * f))) - 1.0f);
        public static final EasingFunction EaseOutCirc = f -> {
            float f2 = f - 1.0f;
            return (float) Math.sqrt (1.0f - (f2 * f2));
        };
        public static final EasingFunction EaseInOutCirc = f -> {
            float sqrt;
            float f2 = 0.5f;
            float f3 = f / 0.5f;
            if (1.0f > f3) {
                sqrt = ((float) Math.sqrt (1.0f - (f3 * f3))) - 1.0f;
                f2 = -0.5f;
            } else {
                float f4 = f3 - 2.0f;
                sqrt = ((float) Math.sqrt (1.0f - (f4 * f4))) + 1.0f;
            }
            return sqrt * f2;
        };
        public static final EasingFunction EaseInElastic = f -> {
            if (0.0f == f) {
                return 0.0f;
            }
            if (1.0f == f) {
                return 1.0f;
            }
            float f2 = f - 1.0f;
            return -(((float) Math.pow (2.0d, 10.0f * f2)) * ((float) Math.sin (((f2 - (0.047746483f * ((float) (Math.PI / 2.0)))) * 6.283185307179586d) / 0.3f)));
        };
        public static final EasingFunction EaseOutElastic = f -> {
            if (0.0f == f) {
                return 0.0f;
            }
            if (1.0f == f) {
                return 1.0f;
            }
            return (((float) Math.pow (2.0d, -10.0f * f)) * ((float) Math.sin (((f - (0.047746483f * ((float) (Math.PI / 2.0)))) * 6.283185307179586d) / 0.3f))) + 1.0f;
        };
        public static final EasingFunction EaseInOutElastic = f -> {
            if (0.0f == f) {
                return 0.0f;
            }
            float f2 = f / 0.5f;
            if (2.0f == f2) {
                return 1.0f;
            }
            final float asin = 0.07161973f * ((float) (Math.PI / 2.0));
            if (1.0f > f2) {
                float f3 = f2 - 1.0f;
                return ((float) Math.pow (2.0d, 10.0f * f3)) * ((float) Math.sin ((((f3) - asin) * 6.283185307179586d) / 0.45000002f)) * -0.5f;
            }
            float f4 = f2 - 1.0f;
            return (((float) Math.pow (2.0d, -10.0f * f4)) * ((float) Math.sin ((((f4) - asin) * 6.283185307179586d) / 0.45000002f)) * 0.5f) + 1.0f;
        };
        public static final EasingFunction EaseInBack = f -> f * f * ((f * 2.70158f) - 1.70158f);
        public static final EasingFunction EaseOutBack = f -> {
            float f2 = f - 1.0f;
            return (f2 * f2 * ((f2 * 2.70158f) + 1.70158f)) + 1.0f;
        };
        public static final EasingFunction EaseInOutBack = f -> {
            float f2 = f / 0.5f;
            if (1.0f > f2) {
                return f2 * f2 * ((3.5949094f * f2) - 2.5949094f) * 0.5f;
            }
            float f3 = f2 - 2.0f;
            return ((f3 * f3 * ((3.5949094f * f3) + 2.5949094f)) + 2.0f) * 0.5f;
        };
        public static final EasingFunction EaseOutBounce = f -> {
            if (0.36363637f > f) {
                return 7.5625f * f * f;
            }
            if (0.72727275f > f) {
                float f2 = f - 0.54545456f;
                return (7.5625f * f2 * f2) + 0.75f;
            } else if (0.90909094f > f) {
                float f3 = f - 0.8181818f;
                return (7.5625f * f3 * f3) + 0.9375f;
            } else {
                float f4 = f - 0.95454544f;
                return (7.5625f * f4 * f4) + 0.984375f;
            }
        };
        public static final EasingFunction EaseInBounce = f -> 1.0f - EasingFunctions.EaseOutBounce.getInterpolation (1.0f - f);
        public static final EasingFunction EaseInOutBounce = f -> {
            if (0.5f > f) {
                return EasingFunctions.EaseInBounce.getInterpolation (f * 2.0f) * 0.5f;
            }
            return (EasingFunctions.EaseOutBounce.getInterpolation ((f * 2.0f) - 1.0f) * 0.5f) + 0.5f;
        };

    }
}
