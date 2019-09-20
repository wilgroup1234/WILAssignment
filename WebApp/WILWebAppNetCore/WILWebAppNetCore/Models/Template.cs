using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class Template
    {
        public Template()
        {
            DailyQuote = new HashSet<DailyQuote>();
        }

        public int TemplateId { get; set; }
        public string TemplateName { get; set; }

        public ICollection<DailyQuote> DailyQuote { get; set; }
    }
}
