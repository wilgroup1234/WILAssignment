namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class UserStep
    {
        [Key]
        public int UserStepsID { get; set; }

        [Column(TypeName = "date")]
        public DateTime UserStepsDate { get; set; }

        public int Steps { get; set; }

        public int UserID { get; set; }

        public virtual User User { get; set; }
    }
}
