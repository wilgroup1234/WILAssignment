namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class UserCV
    {
        public int UserCVID { get; set; }

        public int UserID { get; set; }

        public int CVID { get; set; }

        public virtual CV CV { get; set; }

        public virtual User User { get; set; }
    }
}
