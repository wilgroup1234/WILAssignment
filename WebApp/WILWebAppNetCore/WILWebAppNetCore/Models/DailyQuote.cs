using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class DailyQuote
    {
        public int QuoteId { get; set; }
        public DateTime QuoteDate { get; set; }
        public string QuoteText { get; set; }
        public string YoutubeLink { get; set; }
        public int TemplateId { get; set; }
        public int Views { get; set; }
    }
}
