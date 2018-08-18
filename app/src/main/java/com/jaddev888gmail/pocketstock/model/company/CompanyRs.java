
package com.jaddev888gmail.pocketstock.model.company;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyRs implements Parcelable {

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("exchange")
    @Expose
    private String exchange;
    @SerializedName("industry")
    @Expose
    private String industry;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("CEO")
    @Expose
    private String cEO;
    @SerializedName("issueType")
    @Expose
    private String issueType;
    @SerializedName("sector")
    @Expose
    private String sector;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCEO() {
        return cEO;
    }

    public void setCEO(String cEO) {
        this.cEO = cEO;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.symbol);
        dest.writeString(this.companyName);
        dest.writeString(this.exchange);
        dest.writeString(this.industry);
        dest.writeString(this.website);
        dest.writeString(this.description);
        dest.writeString(this.cEO);
        dest.writeString(this.issueType);
        dest.writeString(this.sector);
        dest.writeStringList(this.tags);
    }

    public CompanyRs() {
    }

    protected CompanyRs(Parcel in) {
        this.symbol = in.readString();
        this.companyName = in.readString();
        this.exchange = in.readString();
        this.industry = in.readString();
        this.website = in.readString();
        this.description = in.readString();
        this.cEO = in.readString();
        this.issueType = in.readString();
        this.sector = in.readString();
        this.tags = in.createStringArrayList();
    }

    public static final Parcelable.Creator<CompanyRs> CREATOR = new Parcelable.Creator<CompanyRs>() {
        @Override
        public CompanyRs createFromParcel(Parcel source) {
            return new CompanyRs(source);
        }

        @Override
        public CompanyRs[] newArray(int size) {
            return new CompanyRs[size];
        }
    };
}
