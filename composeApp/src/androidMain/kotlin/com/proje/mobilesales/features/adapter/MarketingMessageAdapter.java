package com.proje.mobilesales.features.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.proje.mobilesales.R;
import java.util.ArrayList;
import java.util.List;
import org.zakariya.stickyheaders.SectioningAdapter;

public class MarketingMessageAdapter extends SectioningAdapter {
    private final Context mContext;
    List<String> marketingMessageList;
    List<Section> sections = new ArrayList();

    public boolean doesSectionHaveFooter(int i2) {
        return false;
    }

    public boolean doesSectionHaveHeader(int i2) {
        return true;
    }
    public int getNumberOfItemsInSection(int i2) {
        return 1;
    }

    public MarketingMessageAdapter(Context context) {
        this.mContext = context;
    }

    private static class Section {
        List<String> marketingMessages;

        private Section() {
            this.marketingMessages = new ArrayList();
        }
    }

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder {
        TextView titleTextView;

        public ItemViewHolder(View view) {
            super(view);
            this.titleTextView = view.findViewById(R.id.titleTextView);
        }
    }
    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        TextView titleTextView;

        public HeaderViewHolder(View view) {
            super(view);
            this.titleTextView = view.findViewById(R.id.titleTextView);
        }
    }
    public void setMarketMessage(List<String> list) {
        this.marketingMessageList = list;
        this.sections.clear();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Section section = new Section();
            section.marketingMessages.add(list.get(i2));
            this.sections.add(section);
        }
        notifyAllSectionsDataSetChanged();
    }
    public int getNumberOfSections() {
        return this.sections.size();
    }
    public ItemViewHolder onCreateItemViewHolder(ViewGroup viewGroup, int i2) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_market_message, viewGroup, false));
    }
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i2) {
        return new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_schedule_header, viewGroup, false));
    }
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder headerViewHolder, int i2, int i3) {
        HeaderViewHolder headerViewHolder2 = (HeaderViewHolder) headerViewHolder;
        headerViewHolder2.itemView.setBackgroundColor(-1);
        headerViewHolder2.titleTextView.setText(this.mContext.getResources().getStringArray(R.array.marketing_message_titles)[i2]);
    }
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder itemViewHolder, int i2, int i3, int i4) {
        ((ItemViewHolder) itemViewHolder).titleTextView.setText(this.sections.get(i2).marketingMessages.get(i3));
    }
}
