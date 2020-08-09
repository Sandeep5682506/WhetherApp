package com.example.mywhether;

import com.google.gson.annotations.SerializedName;

public class Example {
    @SerializedName("main") //in the jason file there is name of file main as main
    Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
