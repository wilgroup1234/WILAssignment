package com.a17001922.wil_app.dailyQuote;

//This class is an Object used to send/ receive data from the API
public class DailyObject
{
    //_____________Declarations_________________
    protected int TemplateID;
    protected String QuoteText;
    protected String YoutubeLink;
    protected int Views;

    //_____________Get and Set methods_________________
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
