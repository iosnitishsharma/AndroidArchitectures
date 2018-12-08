
package com.nitishsharma7.androidarchitectures.models.tagtoptracks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Streamable implements Serializable
{

    @SerializedName("#text")
    @Expose
    private String text;
    @SerializedName("fulltrack")
    @Expose
    private String fulltrack;
    private final static long serialVersionUID = 4999729686053633812L;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFulltrack() {
        return fulltrack;
    }

    public void setFulltrack(String fulltrack) {
        this.fulltrack = fulltrack;
    }

}
