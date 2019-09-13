namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class UserLoginDate
    {
        [Key]
        public int UserLoginDatesID { get; set; }

        public int UserID { get; set; }

        [Column("UserLoginDate", TypeName = "date")]
        public DateTime UserLoginDate1 { get; set; }

        public virtual User User { get; set; }
    }
}
