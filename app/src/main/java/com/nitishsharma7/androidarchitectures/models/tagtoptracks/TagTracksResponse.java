
package com.nitishsharma7.androidarchitectures.models.tagtoptracks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TagTracksResponse implements Serializable
{

    @SerializedName("tracks")
    @Expose
    private Tracks tracks;
    private final static long serialVersionUID = 6329751730920862603L;

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

}
