package javax.mail.search;

import java.util.Date;

public abstract class DateTerm extends ComparisonTerm {
    private static final long serialVersionUID = 4818873430063720043L;
    protected Date date;
    protected DateTerm(int i2, Date date2) {
        this.comparison = i2;
        this.date = date2;
    }
    public Date getDate() {
        return new Date(this.date.getTime());
    }
    public int getComparison() {
        return this.comparison;
    }
    public boolean match(Date date2) {
        switch (this.comparison) {
            case 1:
                return date2.before(this.date) || date2.equals(this.date);
            case 2:
                return date2.before(this.date);
            case 3:
                return date2.equals(this.date);
            case 4:
                return !date2.equals(this.date);
            case 5:
                return date2.after(this.date);
            case 6:
                return date2.after(this.date) || date2.equals(this.date);
            default:
                return false;
        }
    }
    public boolean equals(Object obj) {
        return (obj instanceof DateTerm) && ((DateTerm) obj).date.equals(this.date) && super.equals(obj);
    }
    public int hashCode() {
        return this.date.hashCode() + super.hashCode();
    }
}
