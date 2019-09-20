using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class Streaks
    {
        public int StreakId { get; set; }
        public int UserId { get; set; }
        public int StreakLength { get; set; }

        public Users User { get; set; }
    }
}
