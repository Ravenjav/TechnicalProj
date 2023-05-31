package com.softarex.technical_proj.entities;

public enum QuestionType {
    SINGLE("single"),
    MULTILINE("multiline"),
    RADIO("radio"),
    CHECKBOX("checkbox"),
    COMBOBOX("combobox"),
    DATE("date");

    private final String val;
    private QuestionType(String val){
        this.val = val;
    }
    public String getVal() {
        return val;
    }
}
