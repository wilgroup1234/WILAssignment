namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class Leaderboard
    {
        public int ID { get; set; }

        public int Score { get; set; }

        public int UserID { get; set; }

        public virtual User User { get; set; }
    }
}
