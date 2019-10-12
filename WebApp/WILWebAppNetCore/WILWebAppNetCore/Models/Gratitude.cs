using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class Gratitude
    {
        public int GratitudeId { get; set; }
        public DateTime GratitudeDate { get; set; }
        public string GratitudeItems { get; set; }
        public int UserId { get; set; }

        public Users User { get; set; }
    }
}
