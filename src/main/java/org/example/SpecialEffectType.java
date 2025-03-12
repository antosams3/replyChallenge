package org.example;

public enum SpecialEffectType {
    A("A"), B("B"), C("C"), D("D"), E("E"), X("X");

    public int RE;
    public final String name;

    SpecialEffectType(String name) {
        this.name = name;
    }

    public int getRE(){
        return RE;
    }

    public String getName() {
        return name;
    }

    public void setRE(int RE) {
        this.RE = RE;
    }

}