package javax.mail.search;

public abstract class IntegerComparisonTerm extends ComparisonTerm {
    private static final long serialVersionUID = -6963571240154302484L;
    protected int number;

    protected IntegerComparisonTerm(int i2, int i3) {
        this.comparison = i2;
        this.number = i3;
    }

    public int getNumber() {
        return this.number;
    }

    public int getComparison() {
        return this.comparison;
    }

    
    public boolean match(int i2) {
        switch (this.comparison) {
            case 1:
                return i2 <= this.number;
            case 2:
                return i2 < this.number;
            case 3:
                return i2 == this.number;
            case 4:
                return i2 != this.number;
            case 5:
                return i2 > this.number;
            case 6:
                return i2 >= this.number;
            default:
                return false;
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof IntegerComparisonTerm) && ((IntegerComparisonTerm) obj).number == this.number && super.equals(obj);
    }

    public int hashCode() {
        return this.number + super.hashCode();
    }
}
