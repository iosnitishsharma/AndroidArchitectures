
package com.nitishsharma7.androidarchitectures.models.taginfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TagInfoResponse implements Serializable
{

    @SerializedName("tag")
    @Expose
    private Tag tag;
    private final static long serialVersionUID = -5248200971922493863L;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

}
