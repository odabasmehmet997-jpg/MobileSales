package cardtek.masterpass.management;

/**
 * @param ta synthetic
 * @param tb synthetic
 */ /* loaded from: classes.dex */
record q(p tb, String ta) implements Runnable {
    @Override
    public void run() {
        this.tb.sZ().sz.sj.setText(this.tb.sZ().sO, this.ta);
    }
}
