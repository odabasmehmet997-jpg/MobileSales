package com.proje.mobilesales.features.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.activity.MarketingMessageViewActivity;
import com.proje.mobilesales.features.todo.model.database.TodoInfoDb;
import com.proje.mobilesales.features.todo.view.activity.TodoReadActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.zakariya.stickyheaders.SectioningAdapter;

public class ScheduleRcyclerviewAdapter extends SectioningAdapter {
    private final Context mContext;
    List<Section> sections = new ArrayList();
    Set<String> todoBegDates = new HashSet();
    List<TodoInfoDb> todoInfoDbList;
    public boolean doesSectionHaveFooter(int i2) {
        return false;
    }

    public boolean doesSectionHaveHeader(int i2) {
        return true;
    }

    private static class Section {
        ArrayList<TodoInfoDb> todoInfoDbs;

        private Section() {
            this.todoInfoDbs = new ArrayList<>();
        }
    }

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder implements View.OnClickListener {
        ImageView imgType;
        TextView todoBegHour;
        TextView todoNote;
        TextView todoTitle;

        public ItemViewHolder(View view) {
            super(view);
            this.todoTitle = view.findViewById(R.id.todoTitle);
            this.todoNote = view.findViewById(R.id.todoNote);
            this.todoBegHour = view.findViewById(R.id.todoBegHour);
            this.imgType = view.findViewById(R.id.imgType);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            int sectionForAdapterPosition = ScheduleRcyclerviewAdapter.this.getSectionForAdapterPosition(getAdapterPosition());
            int positionOfItemInSection = ScheduleRcyclerviewAdapter.this.getPositionOfItemInSection(sectionForAdapterPosition, getAdapterPosition());
            ScheduleRcyclerviewAdapter.this.getAdapterPositionForSectionItem(sectionForAdapterPosition, positionOfItemInSection);
            try {
                if (ScheduleRcyclerviewAdapter.this.sections.get(sectionForAdapterPosition).todoInfoDbs.get(positionOfItemInSection).isToDo.equals("FALSE")) {
                    ScheduleRcyclerviewAdapter.this.mContext.startActivity(new Intent(ScheduleRcyclerviewAdapter.this.mContext, MarketingMessageViewActivity.class).putExtra(IntentExtraName.EXTRAS_MESSAGE_REF, ScheduleRcyclerviewAdapter.this.sections.get(sectionForAdapterPosition).todoInfoDbs.get(positionOfItemInSection).logicalRef).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                } else if (ScheduleRcyclerviewAdapter.this.sections.get(sectionForAdapterPosition).todoInfoDbs.get(positionOfItemInSection).isToDo.equals("TRUE")) {
                    ScheduleRcyclerviewAdapter.this.mContext.startActivity(new Intent(ScheduleRcyclerviewAdapter.this.mContext, TodoReadActivity.class).putExtra(IntentExtraName.EXTRAS_MESSAGE_REF, ScheduleRcyclerviewAdapter.this.sections.get(sectionForAdapterPosition).todoInfoDbs.get(positionOfItemInSection).logicalRef).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        TextView titleTextView;

        public HeaderViewHolder(View view) {
            super(view);
            this.titleTextView = view.findViewById(R.id.titleTextView);
        }
    }

    public ScheduleRcyclerviewAdapter(Context context) {
        this.mContext = context;
    }

    public void addDistinctDate() {
        this.todoBegDates.clear();
        Iterator<TodoInfoDb> it = this.todoInfoDbList.iterator();
        while (it.hasNext()) {
            this.todoBegDates.add(getDate(it.next().begDate));
        }
    }

    public void setTodos(List<TodoInfoDb> list) {
        if (list != null) {
            this.todoInfoDbList = list;
            this.sections.clear();
            addDistinctDate();
            for (String str : this.todoBegDates) {
                Section section = new Section();
                for (TodoInfoDb todoInfoDb : list) {
                    if (getDate(todoInfoDb.begDate).equals(getDate(str))) {
                        section.todoInfoDbs.add(todoInfoDb);
                    }
                }
                this.sections.add(section);
            }
        } else {
            this.sections.clear();
            this.todoBegDates.clear();
        }
        notifyAllSectionsDataSetChanged();
    }

    private String getDate(String str) {
        return str.substring(0, 10);
    }

    private String getHour(String str) {
        return str.substring(11, 16);
    }

    public int getNumberOfSections() {
        List<Section> list = this.sections;
        if (list != null) {
            return list.size();
        }
        return 0;
    }
    public int getNumberOfItemsInSection(int i2) {
        if (getNumberOfSections() <= 0 || this.sections.get(i2).todoInfoDbs == null) {
            return 0;
        }
        return this.sections.get(i2).todoInfoDbs.size();
    }

    @Override // org.zakariya.stickyheaders.SectioningAdapter
    public ItemViewHolder onCreateItemViewHolder(ViewGroup viewGroup, int i2) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_schedule_todo, viewGroup, false));
    }

    @Override // org.zakariya.stickyheaders.SectioningAdapter
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup, int i2) {
        return new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_schedule_header, viewGroup, false));
    }

    @Override // org.zakariya.stickyheaders.SectioningAdapter
    @SuppressLint({"SetTextI18n"})
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder itemViewHolder, int i2, int i3, int i4) {
        Section section = getSection(i2);
        if (section == null) {
            return;
        }
        TodoInfoDb todoInfoDb = section.todoInfoDbs.get(i3);
        ItemViewHolder itemViewHolder2 = (ItemViewHolder) itemViewHolder;
        if (todoInfoDb.isToDo.equals("TRUE")) {
            itemViewHolder2.todoTitle.setTextColor(-4776932);
            int i5 = todoInfoDb.priority;
            if (i5 == 0) {
                itemViewHolder2.imgType.setBackgroundResource(R.drawable.high);
            } else if (i5 == 1) {
                itemViewHolder2.imgType.setBackgroundResource(R.drawable.medium);
            } else if (i5 == 2) {
                itemViewHolder2.imgType.setBackgroundResource(R.drawable.low);
            }
        } else {
            itemViewHolder2.imgType.setVisibility(View.GONE);
            itemViewHolder2.todoTitle.setTextColor(-1092784);
        }
        itemViewHolder2.todoTitle.setText(todoInfoDb.getDesc());
        itemViewHolder2.todoNote.setText(todoInfoDb.note);
        itemViewHolder2.todoBegHour.setText(getHour(todoInfoDb.begDate));
    }

    private Section getSection(int i2) {
        if (this.sections == null || getNumberOfSections() <= i2) {
            return null;
        }
        return this.sections.get(i2);
    }

    @Override // org.zakariya.stickyheaders.SectioningAdapter
    @SuppressLint({"SetTextI18n"})
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder headerViewHolder, int i2, int i3) {
        HeaderViewHolder headerViewHolder2 = (HeaderViewHolder) headerViewHolder;
        headerViewHolder2.itemView.setBackgroundColor(-1);
        Section section = getSection(i2);
        if (section != null && section.todoInfoDbs.size() >= 1) {
            headerViewHolder2.titleTextView.setText(getDate(section.todoInfoDbs.get(0).begDate) + " (" + getNumberOfItemsInSection(i2) + ")");
        }
    }
}
