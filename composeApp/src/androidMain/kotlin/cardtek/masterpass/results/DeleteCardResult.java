package cardtek.masterpass.results;

import java.io.Serializable;

public class DeleteCardResult implements Serializable {
    private String refNo;
    public String getRefNo() {
        return this.refNo;
    }
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }
}
