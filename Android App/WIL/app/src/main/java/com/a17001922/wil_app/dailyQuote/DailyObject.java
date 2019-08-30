package com.a17001922.wil_app.dailyQuote;

public class DailyObject {
    protected int TemplateID;
    protected String QuoteText;
    protected String YoutubeLink;
    protected int Views;

    public int getTemplateID() {
        return TemplateID;
    }

    public void setTemplateID(int templateID) {
        TemplateID = templateID;
    }

    public String getQuoteText() {
        return QuoteText;
    }

    public void setQuoteText(String quoteText) {
        QuoteText = quoteText;
    }

    public String getYoutubeLink() {
        return YoutubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        YoutubeLink = youtubeLink;
    }

    public int getViews() {
        return Views;
    }

    public void setViews(int views) {
        Views = views;
    }
}
