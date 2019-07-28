namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class CustomUserGoal
    {
        [Key]
        public int UserGoalID { get; set; }

        public int UserID { get; set; }

        public int GoalID { get; set; }

        public virtual Goal Goal { get; set; }

        public virtual User User { get; set; }
    }
}
