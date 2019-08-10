package com.a16003776.webapitestapp;

public class DailyQuote
{
    private int TemplateID;

    private String QuoteText;

    private String YoutubeLink;

    public int getTemplateID()
    {
        return TemplateID;
    }

    public void setTemplateID(int templateID)
    {
        TemplateID = templateID;
    }

    public String getQuoteText()
    {
        return QuoteText;
    }

    public void setQuoteText(String quoteText)
    {
        QuoteText = quoteText;
    }

    public String getYoutubeLink()
    {
        return YoutubeLink;
    }

    public void setYoutubeLink(String youtubeLink)
    {
        YoutubeLink = youtubeLink;
    }
}
