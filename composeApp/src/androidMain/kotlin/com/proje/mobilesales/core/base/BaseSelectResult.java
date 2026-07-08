package com.proje.mobilesales.core.base;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.base.BaseResult;
import com.proje.mobilesales.core.enums.ProcessType;
import com.proje.mobilesales.core.utils.WorTableNames;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseSelectResult<T> extends BaseResult {
    public static final Parcelable.Creator<BaseSelectResult> CREATOR = new Parcelable.Creator<BaseSelectResult>() {

        public BaseSelectResult createFromParcel(Parcel parcel) {
            return new BaseSelectResult(parcel);
        }

        public BaseSelectResult[] newArray(int i2) {
            return new BaseSelectResult[i2];
        }
    };
    private List<T> mDataList;
    private boolean mDatabaseSave;
    private String mDeleteCondition;
    private String mOrderBy;
    private ProcessType mProcessType;
    private String mSql;
    private String mStatus;
    private boolean mTableDelete;
    public int describeContents() {
        return 0;
    }
    protected BaseSelectResult(SelectBuilder selectBuilder) {
        super(selectBuilder);
        this.mTableDelete = true;
        this.mDatabaseSave = false;
        setSql(selectBuilder.mSql);
        setOrderBy(selectBuilder.mOrderBy);
        setTableDelete(selectBuilder.mTableDelete);
        setDeleteCondition(selectBuilder.mDeleteCondition);
        setDatabaseSave(selectBuilder.mDatabaseSave);
        setProcessType(selectBuilder.mProcessType);
    }
    public String getSql() {
        return this.mSql;
    }
    public void setSql(String str) {
        this.mSql = str;
    }
    public String getOrderBy() {
        return this.mOrderBy;
    }
    public void setOrderBy(String str) {
        this.mOrderBy = str;
    }
    public List<T> getDataList() {
        if (this.mDataList == null) {
            this.mDataList = new ArrayList();
        }
        return this.mDataList;
    }
    public void setDataList(List<T> list) {
        this.mDataList = list;
    }
    public boolean isTableDelete() {
        return this.mTableDelete;
    }
    public void setTableDelete(boolean z) {
        this.mTableDelete = z;
    }
    public String getDeleteCondition() {
        return this.mDeleteCondition;
    }
    public void setDeleteCondition(String str) {
        this.mDeleteCondition = str;
    }
    public boolean isDatabaseSave() {
        return this.mDatabaseSave;
    }
    public void setDatabaseSave(boolean z) {
        this.mDatabaseSave = z;
    }
    public ProcessType getProcessType() {
        return this.mProcessType;
    }
    public void setProcessType(ProcessType processType) {
        this.mProcessType = processType;
    }
    public String getStatus() {
        return this.mStatus;
    }
    public void setStatus(String str) {
        this.mStatus = str;
    }
    public BaseSelectResult() {
        this.mTableDelete = true;
        this.mDatabaseSave = false;
    }
    public String getSqlWithWorCheck() {
        String str = this.mSql;
        if (this.mProcessType == ProcessType.GETWORTABLES) {
            return str;
        }
        Matcher matcher = Pattern.compile("WOR_\\s*(\\w+)").matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            if (!WorTableNames.getInstance().containsTableName(matcher.group())) {
                String str2 = "SELECT '" + group + "' NOTFOUNDTABLE WHERE 1=2";
                this.mOrderBy = "";
                return str2;
            }
        }
        return str;
    }
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeString(this.mSql);
        parcel.writeString(this.mOrderBy);
        parcel.writeList(this.mDataList);
        parcel.writeByte(this.mTableDelete ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mDatabaseSave ? (byte) 1 : (byte) 0);
        ProcessType processType = this.mProcessType;
        parcel.writeInt(processType == null ? -1 : processType.ordinal());
        parcel.writeString(this.mStatus);
    }
    protected BaseSelectResult(Parcel parcel) {
        super(parcel);
        this.mTableDelete = true;
        this.mDatabaseSave = false;
        this.mSql = parcel.readString();
        this.mOrderBy = parcel.readString();
        ArrayList arrayList = new ArrayList();
        this.mDataList = arrayList;
        parcel.readList(arrayList, arrayList.getClass().getClassLoader());
        this.mTableDelete = parcel.readByte() != 0;
        this.mDatabaseSave = parcel.readByte() != 0;
        int readInt = parcel.readInt();
        this.mProcessType = readInt == -1 ? null : ProcessType.values()[readInt];
        this.mStatus = parcel.readString();
    }
    public static abstract class SelectBuilder<V extends BaseSelectResult, S extends SelectBuilder<V, S>> extends BaseResult.Builder<BaseSelectResult, SelectBuilder<V, S>> {
        private boolean mDatabaseSave;
        private String mDeleteCondition;
        private String mOrderBy;
        private ProcessType mProcessType;
        private String mSql;
        private boolean mTableDelete;

        protected SelectBuilder() {
        }

        public S withSql(String str) {
            this.mSql = str;
            return (S) this;
        }

        public S withOrderBy(String str) {
            this.mOrderBy = str;
            return (S) this;
        }

        public S withTableDelete(boolean z) {
            this.mTableDelete = z;
            return (S) this;
        }

        public S withDeleteCondition(String str) {
            this.mDeleteCondition = str;
            return (S) this;
        }

        public S withDatabaseSave(boolean z) {
            this.mDatabaseSave = z;
            return (S) this;
        }

        public S withProcessType(ProcessType processType) {
            this.mProcessType = processType;
            return (S) this;
        }
    }
}
