package com.proje.mobilesales.features.adapter;

import android.os.Bundle;



public interface IListRecyclerView {
    String STATE_CARD_VIEW_ENABLED = "state:cardViewEnabled";

    boolean isCardViewEnabled();

    void restoreState(Bundle bundle);

    Bundle saveState();

    void setCardViewEnabled(boolean z);
}
