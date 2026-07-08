package cardtek.masterpass.results;

import cardtek.masterpass.data.MasterPassCard;
import java.io.Serializable;
import java.util.ArrayList;

public class GetCardsResult implements Serializable {
    private ArrayList<MasterPassCard> cards;
    private String refNo;
    public ArrayList<MasterPassCard> getCards() {
        return this.cards;
    }
    public String getRefNo() {
        return this.refNo;
    }
    public void setCards(ArrayList<MasterPassCard> arrayList) {
        this.cards = arrayList;
    }
    public void setRefNo(String str) {
        this.refNo = str;
    }
}
