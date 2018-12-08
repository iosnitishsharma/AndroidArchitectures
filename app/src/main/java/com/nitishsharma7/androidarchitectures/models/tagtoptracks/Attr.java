
package com.nitishsharma7.androidarchitectures.models.tagtoptracks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Attr implements Serializable
{

    @SerializedName("rank")
    @Expose
    private String rank;
    private final static long serialVersionUID = 5157390476138113012L;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
