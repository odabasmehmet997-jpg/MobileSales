package com.proje.mobilesales.core.expr;

import com.proje.mobilesales.BuildConfig;
import java.util.HashMap;
import java.util.Vector;

public class Parser {
    private static final String operatorChars = "*/+-^<>=,()";

    private static final Variable f1183pi;
    private static final String[] procs1;
    private static final String[] procs2;
    private static final int[] rators1;
    private static final int[] rators2;
    private HashMap allowedVariables = null;
    Scanner tokens = null;
    private Token token = null;

    static {
        Variable make = Variable.make("pi");
        f1183pi = make;
        make.setValue(3.141592653589793d);
        procs1 = new String[]{"abs", "acos", "asin", "atan", "ceil", "cos", "exp", "floor", "log", "round", "sin", "sqrt", "tan"};
        rators1 = new int[]{100, 101, 102, 103, 104, 105, 106, 107, 108, 110, 111, 112, 113};
        procs2 = new String[]{"atan2", "max", "min"};
        rators2 = new int[]{5, 6, 7};
    }

    public static Expr parse(String str) throws SyntaxException {
        return new Parser().parseString(str);
    }

    public void allow(Variable variable) {
        if (this.allowedVariables == null) {
            HashMap hashMap = new HashMap();
            this.allowedVariables = hashMap;
            Variable variable2 = f1183pi;
            hashMap.put(variable2, variable2);
        }
        if (variable != null) {
            this.allowedVariables.put(variable, variable);
        }
    }

    public Expr parseString(String str) throws SyntaxException {
        this.tokens = new Scanner(str, operatorChars);
        return reparse();
    }

    private Expr reparse() throws SyntaxException {
        this.tokens.index = -1;
        nextToken();
        Expr parseExpr = parseExpr(0);
        if (this.token.ttype == -2) {
            return parseExpr;
        }
        throw error("Incomplete expression", 0, null);
    }

    private void nextToken() {
        this.token = this.tokens.nextToken();
    }

    private Expr parseExpr(int i2) throws SyntaxException {
        int i3;
        Expr parseFactor = parseFactor();
        while (true) {
            Token token = this.token;
            int i4 = token.ttype;
            int i5 = 21;
            int i6 = 20;
            if (i4 != -7) {
                i3 = 11;
                if (i4 != -6) {
                    if (i4 != -5) {
                        if (i4 != 42) {
                            if (i4 == 43) {
                                i3 = 0;
                            } else if (i4 == 45) {
                                i3 = 1;
                            } else if (i4 == 47) {
                                i3 = 3;
                            } else if (i4 != 94) {
                                switch (i4) {
                                    case 60:
                                        i3 = 8;
                                        break;
                                    case 61:
                                        i3 = 10;
                                        break;
                                    case 62:
                                        i3 = 13;
                                        break;
                                    default:
                                        if (i4 != -4 || !token.sval.equals("and")) {
                                            Token token2 = this.token;
                                            if (token2.ttype == -4 && token2.sval.equals("or")) {
                                                i6 = 10;
                                                i5 = 11;
                                                i3 = 15;
                                                break;
                                            }
                                        } else {
                                            i6 = 5;
                                            i5 = 6;
                                            i3 = 14;
                                            break;
                                        }
                                        break;
                                }
                            } else {
                                i5 = 50;
                                i3 = 4;
                                i6 = 50;
                            }
                            i5 = 31;
                            i6 = 30;
                        } else {
                            i3 = 2;
                        }
                        i5 = 41;
                        i6 = 40;
                    } else {
                        i3 = 9;
                    }
                }
            } else {
                i3 = 12;
            }
            if (i6 >= i2) {
                nextToken();
                parseFactor = Expr.makeApp2(i3, parseFactor, parseExpr(i5));
            }
        }
    }

    private Expr parseFactor() throws SyntaxException {
        Token token = this.token;
        int i2 = token.ttype;
        if (i2 != -4) {
            if (i2 == -3) {
                Expr makeLiteral = Expr.makeLiteral(token.nval);
                nextToken();
                return makeLiteral;
            }
            if (i2 == -2) {
                throw error("Expected a factor", 2, null);
            }
            if (i2 != 40) {
                if (i2 == 45) {
                    nextToken();
                    return Expr.makeApp1(109, parseExpr(35));
                }
                throw error("Expected a factor", 1, null);
            }
            nextToken();
            Expr parseExpr = parseExpr(0);
            expect(41);
            return parseExpr;
        }
        int i3 = 0;
        while (true) {
            String[] strArr = procs1;
            if (i3 >= strArr.length) {
                int i4 = 0;
                while (true) {
                    String[] strArr2 = procs2;
                    if (i4 < strArr2.length) {
                        if (strArr2[i4].equals(this.token.sval)) {
                            nextToken();
                            expect(40);
                            Expr parseExpr2 = parseExpr(0);
                            expect(44);
                            Expr parseExpr3 = parseExpr(0);
                            expect(41);
                            return Expr.makeApp2(rators2[i4], parseExpr2, parseExpr3);
                        }
                        i4++;
                    } else {
                        if (this.token.sval.equals("if")) {
                            nextToken();
                            expect(40);
                            Expr parseExpr4 = parseExpr(0);
                            expect(44);
                            Expr parseExpr5 = parseExpr(0);
                            expect(44);
                            Expr parseExpr6 = parseExpr(0);
                            expect(41);
                            return Expr.makeIfThenElse(parseExpr4, parseExpr5, parseExpr6);
                        }
                        Variable make = Variable.make(this.token.sval);
                        HashMap hashMap = this.allowedVariables;
                        if (hashMap != null && hashMap.get(make) == null) {
                            throw error("Unknown variable", 4, null);
                        }
                        nextToken();
                        return make;
                    }
                }
            } else {
                if (strArr[i3].equals(this.token.sval)) {
                    nextToken();
                    expect(40);
                    Expr parseExpr7 = parseExpr(0);
                    expect(41);
                    return Expr.makeApp1(rators1[i3], parseExpr7);
                }
                i3++;
            }
        }
    }

    private SyntaxException error(String str, int i2, String str2) {
        return new SyntaxException(str, this, i2, str2);
    }

    private void expect(int i2) throws SyntaxException {
        if (this.token.ttype != i2) {
            char c2 = (char) i2;
            String sb = "'" +
                    c2 +
                    "' expected";
            throw error(sb, 3, "" + c2);
        }
        nextToken();
    }

    boolean tryCorrections() {
        return tryInsertions() || tryDeletions() || trySubstitutions();
    }

    private boolean tryInsertions() {
        Token token;
        Scanner scanner = this.tokens;
        Vector vector = scanner.tokens;
        int i2 = scanner.index;
        while (true) {
            if (i2 < 0) {
                return false;
            }
            if (i2 < vector.size()) {
                token = (Token) vector.elementAt(i2);
            } else {
                String input = this.tokens.getInput();
                token = new Token(-2, 0.0d, input, input.length(), input.length());
            }
            for (Token token2 : possibleInsertions(token)) {
                vector.insertElementAt(token2, i2);
                try {
                    reparse();
                    return true;
                } catch (SyntaxException unused) {
                    vector.removeElementAt(i2);
                }
            }
            i2--;
        }
    }

    private boolean tryDeletions() {
        Scanner scanner = this.tokens;
        Vector vector = scanner.tokens;
        for (int i2 = scanner.index; i2 >= 0; i2--) {
            if (vector.size() > i2) {
                Object elementAt = vector.elementAt(i2);
                vector.remove(i2);
                try {
                    reparse();
                    return true;
                } catch (SyntaxException unused) {
                    vector.insertElementAt(elementAt, i2);
                }
            }
        }
        return false;
    }

    private boolean trySubstitutions() {
        Scanner scanner = this.tokens;
        Vector vector = scanner.tokens;
        int i2 = scanner.index;
        while (true) {
            if (i2 < 0) {
                return false;
            }
            if (vector.size() > i2) {
                Token token = (Token) vector.elementAt(i2);
                for (Token token2 : possibleSubstitutions(token)) {
                    vector.setElementAt(token2, i2);
                    try {
                        reparse();
                        return true;
                    } catch (SyntaxException unused) {
                    }
                }
                vector.setElementAt(token, i2);
            }
            i2--;
        }
    }

    private Token[] possibleInsertions(Token token) {
        Token[] tokenArr = new Token[17 + procs1.length + procs2.length];
        int i2 = 0;
        tokenArr[0] = new Token(-3, 1.0d, BuildConfig.NETSIS_DEMO_PASSWORD, token);
        int i3 = 1;
        int i4 = 0;
        while (i4 < 11) {
            char charAt = operatorChars.charAt(i4);
            tokenArr[i3] = new Token(charAt, 0.0d, Character.toString(charAt), token);
            i4++;
            i3++;
        }
        int i5 = i3 + 1;
        tokenArr[i3] = new Token(-4, 0.0d, "x", token);
        int i6 = 0;
        while (true) {
            String[] strArr = procs1;
            if (i6 >= strArr.length) {
                break;
            }
            tokenArr[i5] = new Token(-4, 0.0d, strArr[i6], token);
            i6++;
            i5++;
        }
        while (true) {
            String[] strArr2 = procs2;
            if (i2 < strArr2.length) {
                tokenArr[i5] = new Token(-4, 0.0d, strArr2[i2], token);
                i2++;
                i5++;
            } else {
                tokenArr[i5] = new Token(-5, 0.0d, "<=", token);
                tokenArr[i5 + 1] = new Token(-6, 0.0d, "<>", token);
                tokenArr[i5 + 2] = new Token(-7, 0.0d, ">=", token);
                tokenArr[i5 + 3] = new Token(-4, 0.0d, "if", token);
                return tokenArr;
            }
        }
    }

    private Token[] possibleSubstitutions(Token token) {
        return possibleInsertions(token);
    }
}
