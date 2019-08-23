namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class Streak
    {
        public int StreakID { get; set; }

        public int UserID { get; set; }

        public int StreakLength { get; set; }

        public virtual User User { get; set; }
    }
}
