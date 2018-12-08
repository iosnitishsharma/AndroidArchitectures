
package com.nitishsharma7.androidarchitectures.models.tagtoptracks;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attr_ implements Serializable
{

    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("perPage")
    @Expose
    private String perPage;
    @SerializedName("totalPages")
    @Expose
    private String totalPages;
    @SerializedName("total")
    @Expose
    private String total;
    private final static long serialVersionUID = 7221645532703133008L;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPerPage() {
        return perPage;
    }

    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
