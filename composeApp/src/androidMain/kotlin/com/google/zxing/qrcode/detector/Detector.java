package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Map;

public class Detector {
    private final BitMatrix image;
    private ResultPointCallback resultPointCallback;
    public Detector(BitMatrix bitMatrix) {
        this.image = bitMatrix;
    }
    public final DetectorResult detect(Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        ResultPointCallback resultPointCallback = map == null ? null : (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        this.resultPointCallback = resultPointCallback;
        return processFinderPatternInfo(new FinderPatternFinder(this.image, resultPointCallback).find(map));
    }
    protected final DetectorResult processFinderPatternInfo(FinderPatternInfo finderPatternInfo) throws NotFoundException, FormatException {
        AlignmentPattern alignmentPatternFindAlignmentInRegion;
        ResultPoint[] resultPointArr;
        FinderPattern topLeft = finderPatternInfo.getTopLeft();
        FinderPattern topRight = finderPatternInfo.getTopRight();
        FinderPattern bottomLeft = finderPatternInfo.getBottomLeft();
        float fCalculateModuleSize = calculateModuleSize(topLeft, topRight, bottomLeft);
        if (fCalculateModuleSize < 1.0f) {
            throw NotFoundException.getNotFoundInstance();
        }
        int r4 = computeDimension(topLeft, topRight, bottomLeft, fCalculateModuleSize);
        Version provisionalVersionForDimension = Version.getProvisionalVersionForDimension(r4);
        int dimensionForVersion = provisionalVersionForDimension.getDimensionForVersion() - 7;
        if (provisionalVersionForDimension.getAlignmentPatternCenters().length > 0) {
            float x = (topRight.getX() - topLeft.getX()) + bottomLeft.getX();
            float y = (topRight.getY() - topLeft.getY()) + bottomLeft.getY();
            float f2 = 1.0f - (3.0f / dimensionForVersion);
            int x2 = (int) (topLeft.getX() + ((x - topLeft.getX()) * f2));
            int y2 = (int) (topLeft.getY() + (f2 * (y - topLeft.getY())));
            for (int r6 = 4; r6 <= 16; r6 <<= 1) {
                try {
                    alignmentPatternFindAlignmentInRegion = findAlignmentInRegion(fCalculateModuleSize, x2, y2, r6);
                    break;
                } catch (NotFoundException unused) {
                }
            }
            alignmentPatternFindAlignmentInRegion = null;
        } else {
            alignmentPatternFindAlignmentInRegion = null;
        }
        BitMatrix bitMatrixSampleGrid = sampleGrid(this.image, createTransform(topLeft, topRight, bottomLeft, alignmentPatternFindAlignmentInRegion, r4), r4);
        if (alignmentPatternFindAlignmentInRegion == null) {
            resultPointArr = new ResultPoint[]{bottomLeft, topLeft, topRight};
        } else {
            resultPointArr = new ResultPoint[]{bottomLeft, topLeft, topRight, alignmentPatternFindAlignmentInRegion};
        }
        return new DetectorResult(bitMatrixSampleGrid, resultPointArr);
    }
    private static PerspectiveTransform createTransform(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int r22) {
        float x;
        float y;
        float f2;
        float f3 = r22 - 3.5f;
        if (resultPoint4 != null) {
            x = resultPoint4.getX();
            y = resultPoint4.getY();
            f2 = f3 - 3.0f;
        } else {
            x = (resultPoint2.getX() - resultPoint.getX()) + resultPoint3.getX();
            y = (resultPoint2.getY() - resultPoint.getY()) + resultPoint3.getY();
            f2 = f3;
        }
        return PerspectiveTransform.quadrilateralToQuadrilateral(3.5f, 3.5f, f3, 3.5f, f2, f2, 3.5f, f3, resultPoint.getX(), resultPoint.getY(), resultPoint2.getX(), resultPoint2.getY(), x, y, resultPoint3.getX(), resultPoint3.getY());
    }
    private static BitMatrix sampleGrid(BitMatrix bitMatrix, PerspectiveTransform perspectiveTransform, int r3) throws NotFoundException {
        return GridSampler.getInstance().sampleGrid(bitMatrix, r3, r3, perspectiveTransform);
    }
    private static int computeDimension(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, float f2) throws NotFoundException {
        int r1 = (MathUtils.round(ResultPoint.distance(resultPoint, resultPoint2) / f2) + MathUtils.round(ResultPoint.distance(resultPoint, resultPoint3) / f2)) / 2;
        int r2 = r1 + 7;
        int r3 = r2 & 3;
        if (r3 == 0) {
            return r1 + 8;
        }
        if (r3 == 2) {
            return r1 + 6;
        }
        if (r3 != 3) {
            return r2;
        }
        throw NotFoundException.getNotFoundInstance();
    }
    protected final float calculateModuleSize(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        return (calculateModuleSizeOneWay(resultPoint, resultPoint2) + calculateModuleSizeOneWay(resultPoint, resultPoint3)) / 2.0f;
    }
    private float calculateModuleSizeOneWay(ResultPoint resultPoint, ResultPoint resultPoint2) {
        float fSizeOfBlackWhiteBlackRunBothWays = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint.getX(), (int) resultPoint.getY(), (int) resultPoint2.getX(), (int) resultPoint2.getY());
        float fSizeOfBlackWhiteBlackRunBothWays2 = sizeOfBlackWhiteBlackRunBothWays((int) resultPoint2.getX(), (int) resultPoint2.getY(), (int) resultPoint.getX(), (int) resultPoint.getY());
        return Float.isNaN(fSizeOfBlackWhiteBlackRunBothWays) ? fSizeOfBlackWhiteBlackRunBothWays2 / 7.0f : Float.isNaN(fSizeOfBlackWhiteBlackRunBothWays2) ? fSizeOfBlackWhiteBlackRunBothWays / 7.0f : (fSizeOfBlackWhiteBlackRunBothWays + fSizeOfBlackWhiteBlackRunBothWays2) / 14.0f;
    }
    private float sizeOfBlackWhiteBlackRunBothWays(int r6, int r7, int r8, int r9) {
        float width;
        float height;
        float fSizeOfBlackWhiteBlackRun = sizeOfBlackWhiteBlackRun(r6, r7, r8, r9);
        int width2 = r6 - (r8 - r6);
        int height2 = 0;
        if (width2 < 0) {
            width = r6 / (r6 - width2);
            width2 = 0;
        } else if (width2 >= this.image.getWidth()) {
            width = ((this.image.getWidth() - 1) - r6) / (width2 - r6);
            width2 = this.image.getWidth() - 1;
        } else {
            width = 1.0f;
        }
        float f2 = r7;
        int r92 = (int) (f2 - ((r9 - r7) * width));
        if (r92 < 0) {
            height = f2 / (r7 - r92);
        } else if (r92 >= this.image.getHeight()) {
            height = ((this.image.getHeight() - 1) - r7) / (r92 - r7);
            height2 = this.image.getHeight() - 1;
        } else {
            height2 = r92;
            height = 1.0f;
        }
        return (fSizeOfBlackWhiteBlackRun + sizeOfBlackWhiteBlackRun(r6, r7, (int) (r6 + ((width2 - r6) * height)), height2)) - 1.0f;
    }
    private float sizeOfBlackWhiteBlackRun(int r18, int r19, int r20, int r21) {
        int r1;
        int r4;
        int r5;
        int r6;
        int r192;
        int r0;
        Detector detector;
        boolean z;
        int r02;
        int r3 = 1;
        boolean z2 = Math.abs(r21 - r19) > Math.abs(r20 - r18);
        if (z2) {
            r4 = r18;
            r1 = r19;
            r6 = r20;
            r5 = r21;
        } else {
            r1 = r18;
            r4 = r19;
            r5 = r20;
            r6 = r21;
        }
        int r7 = Math.abs(r5 - r1);
        int r8 = Math.abs(r6 - r4);
        int r10 = 2;
        int r9 = (-r7) / 2;
        int r12 = r1 < r5 ? 1 : -1;
        int r11 = r4 < r6 ? 1 : -1;
        int r52 = r5 + r12;
        int r13 = r1;
        int r14 = r4;
        int r15 = 0;
        while (true) {
            if (r13 == r52) {
                r192 = r52;
                r0 = r10;
                break;
            }
            int r2 = z2 ? r14 : r13;
            int r102 = z2 ? r13 : r14;
            if (r15 == r3) {
                z = z2;
                r02 = r3;
                r192 = r52;
                detector = this;
            } else {
                detector = this;
                z = z2;
                r192 = r52;
                r02 = 0;
            }
            if (false) {
                if (r15 == 2) {
                    return MathUtils.distance(r13, r14, r1, r4);
                }
                r15++;
            }
            r9 += r8;
            if (r9 > 0) {
                if (r14 == r6) {
                    r0 = 2;
                    break;
                }
                r14 += r11;
                r9 -= r7;
            }
            r13 += r12;
            r52 = r192;
            z2 = z;
            r3 = 1;
            r10 = 2;
        }
        return 0;
    }
    protected final AlignmentPattern findAlignmentInRegion(float f2, int r12, int r13, float f3) throws NotFoundException {
        int r14 = (int) (f3 * f2);
        int r4 = Math.max(0, r12 - r14);
        int r6 = Math.min(this.image.getWidth() - 1, r12 + r14) - r4;
        float f4 = 3.0f * f2;
        if (r6 < f4) {
            throw NotFoundException.getNotFoundInstance();
        }
        int r5 = Math.max(0, r13 - r14);
        int r7 = Math.min(this.image.getHeight() - 1, r13 + r14) - r5;
        if (r7 < f4) {
            throw NotFoundException.getNotFoundInstance();
        }
        return new AlignmentPatternFinder(this.image, r4, r5, r6, r7, f2, this.resultPointCallback).find();
    }
}
