package com.proje.mobilesales.core.tigerwcf;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.SafeType;
import java.io.Serializable;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@SafeType
@Root

public class REPORTXML implements Serializable {
    private static final long serialVersionUID = 1;

    @ElementList(inline = EmbeddingCompat.DEBUG)
    public List<REPORTLINE> reportLines;

    public List<REPORTLINE> getResultLine() {
        return this.reportLines;
    }

    public void setResultLine(List<REPORTLINE> list) {
        this.reportLines = list;
    }
}
