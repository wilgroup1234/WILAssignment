namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class SecurityQuestion
    {
        public int ID { get; set; }

        [Required]
        [StringLength(255)]
        public string Question { get; set; }

        [Required]
        [StringLength(255)]
        public string Answer { get; set; }

        public int UserID { get; set; }

        public virtual User User { get; set; }
    }
}
