package com.example.c0423i1module3.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public  abstract  class RunnableCustom implements Runnable  {
     protected String  fieldName;
    protected String value ;
    protected String message;
    protected Map<String, String> errors;

    public void setValue(String value) {
        this.value = value;
    }
}
