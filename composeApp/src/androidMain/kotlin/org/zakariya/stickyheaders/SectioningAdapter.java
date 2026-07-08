package org.zakariya.stickyheaders;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SectioningAdapter extends RecyclerView.Adapter<SectioningAdapter.ViewHolder> {
    public static final int NO_POSITION = -1;
    private static final String TAG = "SectioningAdapter";
    public static final int TYPE_FOOTER = 3;
    public static final int TYPE_GHOST_HEADER = 1;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 2;
    private Handler mainThreadHandler;
    private int[] sectionIndicesByAdapterPosition;
    private ArrayList<Section> sections;
    private int totalNumberOfItems;
    private final HashMap<Integer, Boolean> collapsedSections = new HashMap<>();
    private HashMap<Integer, SectionSelectionState> selectionStateBySection = new HashMap<>();
    public static class FooterViewHolder extends ViewHolder {
        public boolean isFooter() {
            return true;
        }
    }
    public interface SelectionVisitor {
        void onVisitSelectedFooter(int i2);
        void onVisitSelectedSection(int i2);
        void onVisitSelectedSectionItem(int i2, int i3);
    }
    public static int unmaskBaseViewType(final int i2) {
        return i2 & 255;
    }
    public static int unmaskUserViewType(final int i2) {
        return (i2 >> 8) & 255;
    }
    public boolean doesSectionHaveFooter(final int i2) {
        return false;
    }
    public boolean doesSectionHaveHeader(final int i2) {
        return false;
    }
    public int getNumberOfItemsInSection(final int i2) {
        return 0;
    }
    public int getNumberOfSections() {
        return 0;
    }
    public int getSectionFooterUserType(final int i2) {
        return 0;
    }
    public int getSectionHeaderUserType(final int i2) {
        return 0;
    }
    public int getSectionItemUserType(final int i2, final int i3) {
        return 0;
    }
    public void onBindFooterViewHolder(final FooterViewHolder footerViewHolder, final int i2, final int i3) {
    }
    public void onBindGhostHeaderViewHolder(final GhostHeaderViewHolder ghostHeaderViewHolder, final int i2) {
    }
    public void onBindHeaderViewHolder(final HeaderViewHolder headerViewHolder, final int i2, final int i3) {
    }
    public void onBindItemViewHolder(final ItemViewHolder itemViewHolder, final int i2, final int i3, final int i4) {
    }
    public FooterViewHolder onCreateFooterViewHolder(final ViewGroup viewGroup, final int i2) {
        return null;
    }
    public HeaderViewHolder onCreateHeaderViewHolder(final ViewGroup viewGroup, final int i2) {
        return null;
    }
    public ItemViewHolder onCreateItemViewHolder(final ViewGroup viewGroup, final int i2) {
        return null;
    }
    private static class Section {
        int adapterPosition;
        boolean hasFooter;
        boolean hasHeader;
        int length;
        int numberOfItems;

        private Section() {
        }
    }
    private static class SectionSelectionState {
        boolean footer;
        SparseBooleanArray items;
        boolean section;

        private SectionSelectionState() {
            items = new SparseBooleanArray();
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private int numberOfItemsInSection;
        private int section;
        public ViewHolder() {
            this(null);
        }
        public boolean isFooter() {
            return false;
        }
        public boolean isGhostHeader() {
            return false;
        }
        public boolean isHeader() {
            return false;
        }
        public ViewHolder(final View view) {
            super(view);
        }
        public int getItemViewBaseType() {
            return unmaskBaseViewType(this.getItemViewType());
        }
        public int getItemViewUserType() {
            return unmaskUserViewType(this.getItemViewType());
        }
        public int getSection() {
            return section;
        }
        public void setSection(final int i2) {
            section = i2;
        }
        public int getNumberOfItemsInSection() {
            return numberOfItemsInSection;
        }
        void setNumberOfItemsInSection(final int i2) {
            numberOfItemsInSection = i2;
        }
    }
    public static class ItemViewHolder extends ViewHolder {
        private int positionInSection;
        public ItemViewHolder(final View view) {
            super(view);
        }
        public int getPositionInSection() {
            return positionInSection;
        }
        public void setPositionInSection(final int i2) {
            positionInSection = i2;
        }
    }
    public static class HeaderViewHolder extends ViewHolder {
        public boolean isHeader() {
            return true;
        }
        public HeaderViewHolder(final View view) {
            super(view);
        }
    }
    public static class GhostHeaderViewHolder extends ViewHolder {
        public boolean isGhostHeader() {
            return true;
        }
        public GhostHeaderViewHolder(final View view) {
            super(view);
        }
    }
    public GhostHeaderViewHolder onCreateGhostHeaderViewHolder(final ViewGroup viewGroup) {
        return new GhostHeaderViewHolder(new View(viewGroup.getContext()));
    }
    public int getSectionForAdapterPosition(final int i2) {
        if (null == this.sections) {
            this.buildSectionIndex();
        }
        if (0 == getItemCount()) {
            return -1;
        }
        if (0 > i2 || i2 >= this.getItemCount()) {
            throw new IndexOutOfBoundsException("adapterPosition " + i2 + " is not in range of items represented by adapter");
        }
        return sectionIndicesByAdapterPosition[i2];
    }
    public int getPositionOfItemInSection(final int i2, final int i3) {
        if (null == this.sections) {
            this.buildSectionIndex();
        }
        if (0 > i2) {
            throw new IndexOutOfBoundsException("sectionIndex " + i2 + " < 0");
        }
        if (i2 >= sections.size()) {
            throw new IndexOutOfBoundsException("sectionIndex " + i2 + " >= sections.size (" + sections.size() + ")");
        }
        final Section section = sections.get(i2);
        final int i4 = i3 - section.adapterPosition;
        if (i4 <= section.length) {
            return section.hasHeader ? i4 - 2 : i4;
        }
        throw new IndexOutOfBoundsException("adapterPosition: " + i3 + " is beyond sectionIndex: " + i2 + " length: " + section.length);
    }
    private int getAdapterPosition(final int i2, final int i3) {
        if (null == this.sections) {
            this.buildSectionIndex();
        }
        if (0 > i2) {
            throw new IndexOutOfBoundsException("sectionIndex " + i2 + " < 0");
        }
        if (i2 >= sections.size()) {
            throw new IndexOutOfBoundsException("sectionIndex " + i2 + " >= sections.size (" + sections.size() + ")");
        }
        return i3 + sections.get(i2).adapterPosition;
    }
    public int getAdapterPositionForSectionHeader(final int i2) {
        if (this.doesSectionHaveHeader(i2)) {
            return this.getAdapterPosition(i2, 0);
        }
        return -1;
    }
    public int getAdapterPositionForSectionGhostHeader(final int i2) {
        if (this.doesSectionHaveHeader(i2)) {
            return this.getAdapterPosition(i2, 1);
        }
        return -1;
    }
    public int getAdapterPositionForSectionItem(final int i2, final int i3) {
        if (this.doesSectionHaveHeader(i2)) {
            return this.getAdapterPosition(i2, i3) + 2;
        }
        return this.getAdapterPosition(i2, i3);
    }
    public int getAdapterPositionForSectionFooter(final int i2) {
        if (!this.doesSectionHaveFooter(i2)) {
            return -1;
        }
        final Section section = sections.get(i2);
        return (section.adapterPosition + section.length) - 1;
    }
    public void setSectionIsCollapsed(final int i2, final boolean z) {
        final boolean z2 = this.isSectionCollapsed(i2) != z;
        collapsedSections.put(Integer.valueOf(i2), Boolean.valueOf(z));
        if (z2) {
            if (null == this.sections) {
                this.buildSectionIndex();
            }
            final int i3 = sections.get(i2).numberOfItems;
            if (z) {
                this.notifySectionItemRangeRemoved(i2, 0, i3, false);
            } else {
                this.notifySectionItemRangeInserted(i2, 0, i3, false);
            }
        }
    }
    public boolean isSectionCollapsed(final int i2) {
        if (collapsedSections.containsKey(Integer.valueOf(i2))) {
            return collapsedSections.get(Integer.valueOf(i2)).booleanValue();
        }
        return false;
    }
    private SectionSelectionState getSectionSelectionState(final int i2) {
        final SectionSelectionState sectionSelectionState = selectionStateBySection.get(Integer.valueOf(i2));
        if (null != sectionSelectionState) {
            return sectionSelectionState;
        }
        final SectionSelectionState sectionSelectionState2 = new SectionSelectionState();
        selectionStateBySection.put(Integer.valueOf(i2), sectionSelectionState2);
        return sectionSelectionState2;
    }
    public void clearSelection(final boolean z) {
        final HashMap hashMap = z ? new HashMap(selectionStateBySection) : null;
        selectionStateBySection = new HashMap<>();
        if (z) {
            for (final Object num : hashMap.keySet()) {
                final int intValue =(Integer) num;
                final SectionSelectionState sectionSelectionState = (SectionSelectionState) hashMap.get(num);
                if (sectionSelectionState.section) {
                    this.notifySectionDataSetChanged(intValue);
                } else {
                    final int size = sectionSelectionState.items.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        if (sectionSelectionState.items.valueAt(i2)) {
                            this.notifySectionItemChanged(intValue, sectionSelectionState.items.keyAt(i2));
                        }
                    }
                    if (sectionSelectionState.footer) {
                        this.notifySectionFooterChanged(intValue);
                    }
                }
            }
        }
    }
    public void clearSelection() {
        this.clearSelection(true);
    }
    public boolean isSelectionEmpty() {
        for (final Integer num : selectionStateBySection.keySet()) {
            num.intValue();
            final SectionSelectionState sectionSelectionState = selectionStateBySection.get(num);
            if (sectionSelectionState.section) {
                return false;
            }
            final int size = sectionSelectionState.items.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (sectionSelectionState.items.valueAt(i2)) {
                    return false;
                }
            }
            if (sectionSelectionState.footer) {
                return false;
            }
        }
        return true;
    }
    public int getSelectedItemCount() {
        int i2 = 0;
        for (final Integer num : selectionStateBySection.keySet()) {
            final int intValue = num.intValue();
            final SectionSelectionState sectionSelectionState = selectionStateBySection.get(num);
            if (sectionSelectionState.section) {
                i2 += this.getNumberOfItemsInSection(intValue);
                if (this.doesSectionHaveFooter(intValue)) {
                    i2++;
                }
            } else {
                final int size = sectionSelectionState.items.size();
                for (int i3 = 0; i3 < size; i3++) {
                    if (sectionSelectionState.items.valueAt(i3)) {
                        i2++;
                    }
                }
                if (sectionSelectionState.footer) {
                    i2++;
                }
            }
        }
        return i2;
    }
    public void traverseSelection(final SelectionVisitor selectionVisitor) {
        final ArrayList<Integer> arrayList = new ArrayList(selectionStateBySection.keySet());
        Collections.sort(arrayList, Collections.reverseOrder());
        for (final Integer num : arrayList) {
            final int intValue = num.intValue();
            final SectionSelectionState sectionSelectionState = selectionStateBySection.get(num);
            if (null != sectionSelectionState) {
                if (sectionSelectionState.section) {
                    selectionVisitor.onVisitSelectedSection(intValue);
                } else {
                    if (sectionSelectionState.footer) {
                        selectionVisitor.onVisitSelectedFooter(intValue);
                    }
                    for (int size = sectionSelectionState.items.size() - 1; 0 <= size; size--) {
                        if (sectionSelectionState.items.valueAt(size)) {
                            selectionVisitor.onVisitSelectedSectionItem(intValue, sectionSelectionState.items.keyAt(size));
                        }
                    }
                }
            }
        }
    }
    public void setSectionSelected(final int i2, final boolean z) {
        final SectionSelectionState sectionSelectionState = this.getSectionSelectionState(i2);
        if (sectionSelectionState.section != z) {
            sectionSelectionState.section = z;
            sectionSelectionState.items.clear();
            final int numberOfItemsInSection = this.getNumberOfItemsInSection(i2);
            for (int i3 = 0; i3 < numberOfItemsInSection; i3++) {
                sectionSelectionState.items.put(i3, z);
            }
            if (this.doesSectionHaveFooter(i2)) {
                sectionSelectionState.footer = z;
            }
            this.notifySectionDataSetChanged(i2);
        }
    }
    public void toggleSectionSelected(final int i2) {
        this.setSectionSelected(i2, !this.isSectionSelected(i2));
    }
    public boolean isSectionSelected(final int i2) {
        return this.getSectionSelectionState(i2).section;
    }
    public void setSectionItemSelected(final int i2, final int i3, final boolean z) {
        final SectionSelectionState sectionSelectionState = this.getSectionSelectionState(i2);
        if (sectionSelectionState.section || z == sectionSelectionState.items.get(i3)) {
            return;
        }
        sectionSelectionState.items.put(i3, z);
        this.notifySectionItemChanged(i2, i3);
    }
    public void toggleSectionItemSelected(final int i2, final int i3) {
        this.setSectionItemSelected(i2, i3, !this.isSectionItemSelected(i2, i3));
    }
    public boolean isSectionItemSelected(final int i2, final int i3) {
        final SectionSelectionState sectionSelectionState = this.getSectionSelectionState(i2);
        return sectionSelectionState.section || sectionSelectionState.items.get(i3);
    }
    public void setSectionFooterSelected(final int i2, final boolean z) {
        final SectionSelectionState sectionSelectionState = this.getSectionSelectionState(i2);
        if (sectionSelectionState.section || sectionSelectionState.footer == z) {
            return;
        }
        sectionSelectionState.footer = z;
        this.notifySectionFooterChanged(i2);
    }
    public void toggleSectionFooterSelection(final int i2) {
        this.setSectionFooterSelected(i2, !this.isSectionFooterSelected(i2));
    }
    public boolean isSectionFooterSelected(final int i2) {
        final SectionSelectionState sectionSelectionState = this.getSectionSelectionState(i2);
        return sectionSelectionState.section || sectionSelectionState.footer;
    }
    public void notifyAllSectionsDataSetChanged() {
        this.buildSectionIndex();
        this.notifyDataSetChanged();
        collapsedSections.clear();
        selectionStateBySection.clear();
    }
    public void notifySectionDataSetChanged(final int i2) {
        if (null == this.sections) {
            this.buildSectionIndex();
            this.notifyAllSectionsDataSetChanged();
        } else {
            this.buildSectionIndex();
            final Section section = sections.get(i2);
            this.notifyItemRangeChanged(section.adapterPosition, section.length);
        }
        this.getSectionSelectionState(i2).items.clear();
    }
    public void notifySectionItemRangeInserted(final int i2, final int i3, final int i4) {
        this.notifySectionItemRangeInserted(i2, i3, i4, true);
    }
    private void notifySectionItemRangeInserted(final int i2, final int i3, final int i4, final boolean z) {
        if (null == this.sections) {
            this.buildSectionIndex();
            this.notifyAllSectionsDataSetChanged();
        } else {
            this.buildSectionIndex();
            final Section section = sections.get(i2);
            if (i3 > section.numberOfItems) {
                throw new IndexOutOfBoundsException("itemIndex adapterPosition: " + i3 + " exceeds sectionIndex numberOfItems: " + section.numberOfItems);
            }
            this.notifyItemRangeInserted(section.adapterPosition + (section.hasHeader ? i3 + 2 : i3), i4);
        }
        if (z) {
            this.updateSectionItemRangeSelectionState(i2, i3, i4);
        }
    }
    public void notifySectionItemRangeRemoved(final int i2, final int i3, final int i4) {
        this.notifySectionItemRangeRemoved(i2, i3, i4, true);
    }
    private void notifySectionItemRangeRemoved(final int i2, final int i3, final int i4, final boolean z) {
        final ArrayList<Section> arrayList = sections;
        if (null == arrayList) {
            this.buildSectionIndex();
            this.notifyAllSectionsDataSetChanged();
        } else {
            final Section section = arrayList.get(i2);
            final int i5 = section.numberOfItems;
            if (i3 > i5) {
                throw new IndexOutOfBoundsException("itemIndex adapterPosition: " + i3 + " exceeds sectionIndex numberOfItems: " + section.numberOfItems);
            }
            if (i3 + i4 > i5) {
                throw new IndexOutOfBoundsException("itemIndex adapterPosition: " + i3 + i4 + " exceeds sectionIndex numberOfItems: " + section.numberOfItems);
            }
            this.notifyItemRangeRemoved(section.adapterPosition + (section.hasHeader ? i3 + 2 : i3), i4);
            this.buildSectionIndex();
        }
        if (z) {
            this.updateSectionItemRangeSelectionState(i2, i3, -i4);
        }
    }
    public void notifySectionItemChanged(final int i2, int i3) {
        if (null == this.sections) {
            this.buildSectionIndex();
            this.notifyAllSectionsDataSetChanged();
            return;
        }
        this.buildSectionIndex();
        final Section section = sections.get(i2);
        if (i3 >= section.numberOfItems) {
            throw new IndexOutOfBoundsException("itemIndex adapterPosition: " + i3 + " exceeds sectionIndex numberOfItems: " + section.numberOfItems);
        }
        if (section.hasHeader) {
            i3 += 2;
        }
        this.notifyItemChanged(section.adapterPosition + i3);
    }
    public void notifySectionItemInserted(final int i2, final int i3) {
        if (null == this.sections) {
            this.buildSectionIndex();
            this.notifyAllSectionsDataSetChanged();
        } else {
            this.buildSectionIndex();
            final Section section = sections.get(i2);
            this.notifyItemInserted(section.adapterPosition + (section.hasHeader ? i3 + 2 : i3));
        }
        this.updateSectionItemRangeSelectionState(i2, i3, 1);
    }
    public void notifySectionItemRemoved(final int i2, final int i3) {
        if (null == this.sections) {
            this.buildSectionIndex();
            this.notifyAllSectionsDataSetChanged();
        } else {
            this.buildSectionIndex();
            final Section section = sections.get(i2);
            this.notifyItemRemoved(section.adapterPosition + (section.hasHeader ? i3 + 2 : i3));
        }
        this.updateSectionItemRangeSelectionState(i2, i3, -1);
    }
    public void notifySectionInserted(final int i2) {
        if (null == this.sections) {
            this.buildSectionIndex();
            this.notifyAllSectionsDataSetChanged();
        } else {
            this.buildSectionIndex();
            final Section section = sections.get(i2);
            this.notifyItemRangeInserted(section.adapterPosition, section.length);
        }
        this.updateCollapseAndSelectionStateForSectionChange(i2, 1);
    }
    public void notifySectionRemoved(final int i2) {
        final ArrayList<Section> arrayList = sections;
        if (null == arrayList) {
            this.buildSectionIndex();
            this.notifyAllSectionsDataSetChanged();
        } else {
            final Section section = arrayList.get(i2);
            this.buildSectionIndex();
            this.notifyItemRangeRemoved(section.adapterPosition, section.length);
        }
        this.updateCollapseAndSelectionStateForSectionChange(i2, -1);
    }
    public void notifySectionFooterInserted(final int i2) {
        if (null == this.sections) {
            this.buildSectionIndex();
            this.notifyAllSectionsDataSetChanged();
            return;
        }
        this.buildSectionIndex();
        if (!sections.get(i2).hasFooter) {
            throw new IllegalArgumentException("notifySectionFooterInserted: adapter implementation reports that section " + i2 + " does not have a footer");
        }
        this.notifyItemInserted((sections.get(i2).adapterPosition + sections.get(i2).length) - 1);
    }
    public void notifySectionFooterRemoved(final int i2) {
        if (null == this.sections) {
            this.buildSectionIndex();
            this.notifyAllSectionsDataSetChanged();
            return;
        }
        this.buildSectionIndex();
        final Section section = sections.get(i2);
        if (!section.hasFooter) {
            throw new IllegalArgumentException("notifySectionFooterRemoved: adapter implementation reports that section " + i2 + " has a footer");
        }
        this.notifyItemRemoved(section.adapterPosition + section.length);
    }
    public void notifySectionFooterChanged(final int i2) {
        if (null == this.sections) {
            this.buildSectionIndex();
            this.notifyAllSectionsDataSetChanged();
            return;
        }
        this.buildSectionIndex();
        if (!sections.get(i2).hasFooter) {
            throw new IllegalArgumentException("notifySectionFooterChanged: adapter implementation reports that section " + i2 + " does not have a footer");
        }
        this.notifyItemChanged((sections.get(i2).adapterPosition + sections.get(i2).length) - 1);
    }
    private void post(final Runnable runnable) {
        if (null == this.mainThreadHandler) {
            mainThreadHandler = new Handler(Looper.getMainLooper());
        }
        mainThreadHandler.post(runnable);
    }
    private void buildSectionIndex() {
        int i2;
        sections = new ArrayList<>();
        final int numberOfSections = this.getNumberOfSections();
        int i3 = 0;
        for (int i4 = 0; i4 < numberOfSections; i4++) {
            final Section section = new Section();
            section.adapterPosition = i3;
            section.hasHeader = this.doesSectionHaveHeader(i4);
            section.hasFooter = this.doesSectionHaveFooter(i4);
            if (this.isSectionCollapsed(i4)) {
                section.length = 0;
                section.numberOfItems = this.getNumberOfItemsInSection(i4);
            } else {
                final int numberOfItemsInSection = this.getNumberOfItemsInSection(i4);
                section.numberOfItems = numberOfItemsInSection;
                section.length = numberOfItemsInSection;
            }
            if (section.hasHeader) {
                section.length += 2;
            }
            if (section.hasFooter) {
                section.length++;
            }
            sections.add(section);
            i3 += section.length;
        }
        totalNumberOfItems = i3;
        sectionIndicesByAdapterPosition = new int[i3];
        final int numberOfSections2 = this.getNumberOfSections();
        final int i5 = 0;
        for (int i6 = 0; i6 < numberOfSections2; i6++) {
            final Section section2 = sections.get(i6);
            int i7 = 0;
            while (true) {
                i2 = section2.length;
                if (i7 < i2) {
                    sectionIndicesByAdapterPosition[i5 + i7] = i6;
                    i7++;
                }
            }
        }
    }
    private void updateSectionItemRangeSelectionState(final int i2, final int i3, final int i4) {
        final SectionSelectionState sectionSelectionState = this.getSectionSelectionState(i2);
        final SparseBooleanArray clone = sectionSelectionState.items.clone();
        sectionSelectionState.items.clear();
        final int size = clone.size();
        for (int i5 = 0; i5 < size; i5++) {
            final int keyAt = clone.keyAt(i5);
            if (0 <= i4 || keyAt < i3 || keyAt >= i3 - i4) {
                final int i6 = keyAt >= i3 ? keyAt + i4 : keyAt;
                if (clone.get(keyAt)) {
                    sectionSelectionState.items.put(i6, true);
                }
            }
        }
    }
    private void updateCollapseAndSelectionStateForSectionChange(final int i2, final int i3) {
        final HashMap hashMap = new HashMap(collapsedSections);
        collapsedSections.clear();
        for (final Object num : hashMap.keySet()) {
            int intValue;
            intValue = (Integer) num;
            if (0 <= i3 || intValue != i2) {
                if (intValue >= i2) {
                    intValue += i3;
                }
                collapsedSections.put(Integer.valueOf(intValue), (Boolean) hashMap.get(num));
            }
        }
        final HashMap hashMap2 = new HashMap(selectionStateBySection);
        selectionStateBySection.clear();
        for (final Object num2 : hashMap2.keySet()) {
            int intValue2 = (Integer) num2;
            if (0 <= i3 || intValue2 != i2) {
                if (intValue2 >= i2) {
                    intValue2 += i3;
                }
                selectionStateBySection.put(Integer.valueOf(intValue2), (SectionSelectionState) hashMap2.get(num2));
            }
        }
    }
    public int getItemCount() {
        if (null == this.sections) {
            this.buildSectionIndex();
        }
        return totalNumberOfItems;
    }
    public int getItemViewType(final int i2) {
        final int sectionHeaderUserType;
        if (null == this.sections) {
            this.buildSectionIndex();
        }
        if (0 > i2) {
            throw new IndexOutOfBoundsException("adapterPosition (" + i2 + ") cannot be < 0");
        }
        if (i2 >= this.getItemCount()) {
            throw new IndexOutOfBoundsException("adapterPosition (" + i2 + ")  cannot be > getItemCount() (" + this.getItemCount() + ")");
        }
        final int sectionForAdapterPosition = this.getSectionForAdapterPosition(i2);
        final Section section = sections.get(sectionForAdapterPosition);
        int i3 = i2 - section.adapterPosition;
        final int itemViewBaseType = this.getItemViewBaseType(section, i3);
        if (0 == itemViewBaseType) {
            sectionHeaderUserType = this.getSectionHeaderUserType(sectionForAdapterPosition);
            if (0 > sectionHeaderUserType || 255 < sectionHeaderUserType) {
                throw new IllegalArgumentException("Custom header view type (" + sectionHeaderUserType + ") must be in range [0,255]");
            }
        } else if (2 == itemViewBaseType) {
            if (section.hasHeader) {
                i3 -= 2;
            }
            sectionHeaderUserType = this.getSectionItemUserType(sectionForAdapterPosition, i3);
            if (0 > sectionHeaderUserType || 255 < sectionHeaderUserType) {
                throw new IllegalArgumentException("Custom item view type (" + sectionHeaderUserType + ") must be in range [0,255]");
            }
        } else if (3 != itemViewBaseType) {
            sectionHeaderUserType = 0;
        } else {
            sectionHeaderUserType = this.getSectionFooterUserType(sectionForAdapterPosition);
            if (0 > sectionHeaderUserType || 255 < sectionHeaderUserType) {
                throw new IllegalArgumentException("Custom footer view type (" + sectionHeaderUserType + ") must be in range [0,255]");
            }
        }
        return ((sectionHeaderUserType & 255) << 8) | (itemViewBaseType & 255);
    }
    public int getItemViewBaseType(final int i2) {
        return SectioningAdapter.unmaskBaseViewType(this.getItemViewType(i2));
    }
    public int getItemViewUserType(final int i2) {
        return SectioningAdapter.unmaskUserViewType(this.getItemViewType(i2));
    }
    int getItemViewBaseType(final Section section, final int i2) {
        final boolean z = section.hasHeader;
        if (z && section.hasFooter) {
            if (0 == i2) {
                return 0;
            }
            if (1 == i2) {
                return 1;
            }
            return i2 == section.length - 1 ? 3 : 2;
        }
        if (!z) {
            return (section.hasFooter && i2 == section.length - 1) ? 3 : 2;
        }
        if (0 == i2) {
            return 0;
        }
        return 1 == i2 ? 1 : 2;
    }
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i2) {
        final int unmaskBaseViewType = SectioningAdapter.unmaskBaseViewType(i2);
        final int unmaskUserViewType = SectioningAdapter.unmaskUserViewType(i2);
        if (0 == unmaskBaseViewType) {
            return this.onCreateHeaderViewHolder(viewGroup, unmaskUserViewType);
        }
        if (1 == unmaskBaseViewType) {
            return this.onCreateGhostHeaderViewHolder(viewGroup);
        }
        if (2 == unmaskBaseViewType) {
            return this.onCreateItemViewHolder(viewGroup, unmaskUserViewType);
        }
        if (3 == unmaskBaseViewType) {
            return this.onCreateFooterViewHolder(viewGroup, unmaskUserViewType);
        }
        throw new IndexOutOfBoundsException("unrecognized viewType: " + i2 + " does not correspond to TYPE_ITEM, TYPE_HEADER or TYPE_FOOTER");
    }
    public void onBindViewHolder(final ViewHolder viewHolder, final int i2) {
        final int sectionForAdapterPosition = this.getSectionForAdapterPosition(i2);
        viewHolder.setSection(sectionForAdapterPosition);
        viewHolder.setNumberOfItemsInSection(this.getNumberOfItemsInSection(sectionForAdapterPosition));
        this.tagViewHolderItemView(viewHolder, sectionForAdapterPosition, i2);
        final int unmaskBaseViewType = SectioningAdapter.unmaskBaseViewType(viewHolder.getItemViewType());
        final int unmaskUserViewType = SectioningAdapter.unmaskUserViewType(viewHolder.getItemViewType());
        if (0 == unmaskBaseViewType) {
            this.onBindHeaderViewHolder((HeaderViewHolder) viewHolder, sectionForAdapterPosition, unmaskUserViewType);
            return;
        }
        if (1 == unmaskBaseViewType) {
            this.onBindGhostHeaderViewHolder((GhostHeaderViewHolder) viewHolder, sectionForAdapterPosition);
            return;
        }
        if (2 == unmaskBaseViewType) {
            final ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
            final int positionOfItemInSection = this.getPositionOfItemInSection(sectionForAdapterPosition, i2);
            itemViewHolder.setPositionInSection(positionOfItemInSection);
            this.onBindItemViewHolder(itemViewHolder, sectionForAdapterPosition, positionOfItemInSection, unmaskUserViewType);
            return;
        }
        if (3 == unmaskBaseViewType) {
            this.onBindFooterViewHolder((FooterViewHolder) viewHolder, sectionForAdapterPosition, unmaskUserViewType);
            return;
        }
        throw new IllegalArgumentException("unrecognized viewType: " + unmaskBaseViewType + " does not correspond to TYPE_ITEM, TYPE_HEADER, TYPE_GHOST_HEADER or TYPE_FOOTER");
    }
    void tagViewHolderItemView(final ViewHolder viewHolder, final int i2, final int i3) {
        viewHolder.itemView.setTag(id.sectioning_adapter_tag_key_view_viewholder, viewHolder);
    }
}
