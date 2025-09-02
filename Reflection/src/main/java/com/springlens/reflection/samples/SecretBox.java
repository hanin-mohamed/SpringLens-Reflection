package com.springlens.reflection.samples;

public class SecretBox {
    private final String code;

    private SecretBox() {
        this.code = "hidden";
    }
    public SecretBox(String code) {
        this.code = code;
    }

    @Override public String toString() {
        return "SecretBox{code=" + code + "}";
    }
}
