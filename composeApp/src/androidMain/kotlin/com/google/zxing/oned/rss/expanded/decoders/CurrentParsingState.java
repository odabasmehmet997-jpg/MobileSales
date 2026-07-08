package com.google.zxing.oned.rss.expanded.decoders;

final class CurrentParsingState {
    private State encoding = State.NUMERIC;
    private int position;

    private enum State {
        NUMERIC,
        ALPHA,
        ISO_IEC_646
    }

    CurrentParsingState() {
    }

    
    public int getPosition() {
        return position;
    }

    
    public void setPosition(final int i2) {
        position = i2;
    }

    
    public void incrementPosition(final int i2) {
        position += i2;
    }

    
    public boolean isAlpha() {
        return State.ALPHA == this.encoding;
    }

    
    public boolean isIsoIec646() {
        return State.ISO_IEC_646 == this.encoding;
    }

    
    public void setNumeric() {
        encoding = State.NUMERIC;
    }

    
    public void setAlpha() {
        encoding = State.ALPHA;
    }

    
    public void setIsoIec646() {
        encoding = State.ISO_IEC_646;
    }
}
