namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Gratitude")]
    public partial class Gratitude
    {
        public int GratitudeID { get; set; }

        [Column(TypeName = "date")]
        public DateTime GratitudeDate { get; set; }

        [Required]
        [StringLength(255)]
        public string GratitudeItems { get; set; }

        public int UserID { get; set; }

        public virtual User User { get; set; }
    }
}
