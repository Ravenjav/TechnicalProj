package com.my.diplom.entities;

public enum QuestionType {
    SINGLE("single", "text"),
    MULTILINE("multiline", "multiline"),
    RADIO("radio", "radio"),
    CHECKBOX("checkbox", "checkbox"),
    COMBOBOX("combobox", "combobox"),
    DATE("date", "date");

    private final String val;

    private final String out;
    private QuestionType(String val, String out){
        this.val = val;
        this.out = out;
    }
    public String getVal() {
        return val;
    }

    public String getOut() {
        return out;
    }
}
