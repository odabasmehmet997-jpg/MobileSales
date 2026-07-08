package com.proje.mobilesales.core.annotation;

import com.proje.mobilesales.core.sql.SqlLiteVariable;

public @interface Column {
    boolean isAllSame() default true;
    boolean isAllSameMappingName() default false;
    String name();
    ColumnProperty netsis() default @ColumnProperty;
    String netsisName() default "";
    ColumnProperty shared() default @ColumnProperty;
    enum ColumnValueTypes {
        INTEGER(SqlLiteVariable._INTEGER),
        NVARCHAR(SqlLiteVariable._NVARCHAR),
        VARCHAR(SqlLiteVariable._VARCHAR),
        TEXT(SqlLiteVariable._TEXT),
        DOUBLE(SqlLiteVariable._DOUBLE),
        REAL(SqlLiteVariable._REAL),
        BLOB(SqlLiteVariable._BLOB);
        private final String mType;
        ColumnValueTypes(String str) {
            this.mType = str;
        }
        public String getType() {
            return this.mType;
        }
    }
}
