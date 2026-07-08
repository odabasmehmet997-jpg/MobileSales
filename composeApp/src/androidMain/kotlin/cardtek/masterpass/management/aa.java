package cardtek.masterpass.management;

/* loaded from: classes.dex */
final class aa {
    String tq = "9hnFOje6sFnFg9qaxOKSD/ydV+AIhegveghj3qxDzgY3TkWhQX2skHxsrArx3fHXFSGS/teh2SVcl7wn5CDgdCuV7TxTximV9Cy27tt7H73T5PSkqpNWUNqB52PKcHRpADL2pq9ygCzFA5TCr6XJRQqZDm+WmjhXHIvJ44ESXSvuw0ivkZ1zdP8Q3D4LQ2dWbOkprW6jI6R1pnfrQcILQtROguilPdUjNNknOU/K3wk=";
    String tr = "Aw==";
    private final char[] ts = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    
    public String n(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b2 = bArr[i2];
            int i3 = i2 * 2;
            char[] cArr2 = this.ts;
            cArr[i3] = cArr2[(b2 & 255) >>> 4];
            cArr[i3 + 1] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }
}
