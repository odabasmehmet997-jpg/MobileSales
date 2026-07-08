package com.google.zxing.oned.rss.expanded.decoders;

record BlockParsedResult(DecodedInformation decodedInformation, boolean finished) {
    BlockParsedResult(final boolean z) {
        this(null, z);
    }

}
