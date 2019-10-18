namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("DailyQuote")]
    public partial class DailyQuote
    {
        [Key]
        public int QuoteID { get; set; }

        [Column(TypeName = "date")]
        public DateTime QuoteDate { get; set; }

        [Required]
        [StringLength(255)]
        public string QuoteText { get; set; }

        [Required]
        [StringLength(255)]
        public string YoutubeLink { get; set; }

        public int TemplateID { get; set; }

        public int Views { get; set; }

        public virtual Template Template { get; set; }
    }
}
