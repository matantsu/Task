package test.project1;

public class ApiResult {
    private String value;
    private String lexical;

    public ApiResult(String value, String lexical) {
        this.value = value;
        this.lexical = lexical;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLexical() {
        return lexical;
    }

    public void setLexical(String lexical) {
        this.lexical = lexical;
    }
}
