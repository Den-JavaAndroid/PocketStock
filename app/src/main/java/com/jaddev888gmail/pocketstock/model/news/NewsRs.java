
package com.jaddev888gmail.pocketstock.model.news;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsRs implements Parcelable {

    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("related")
    @Expose
    private String related;
    @SerializedName("image")
    @Expose
    private String image;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.datetime);
        dest.writeString(this.headline);
        dest.writeString(this.source);
        dest.writeString(this.url);
        dest.writeString(this.summary);
        dest.writeString(this.related);
        dest.writeString(this.image);
    }

    public NewsRs() {
    }

    protected NewsRs(Parcel in) {
        this.datetime = in.readString();
        this.headline = in.readString();
        this.source = in.readString();
        this.url = in.readString();
        this.summary = in.readString();
        this.related = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<NewsRs> CREATOR = new Parcelable.Creator<NewsRs>() {
        @Override
        public NewsRs createFromParcel(Parcel source) {
            return new NewsRs(source);
        }

        @Override
        public NewsRs[] newArray(int size) {
            return new NewsRs[size];
        }
    };
}
