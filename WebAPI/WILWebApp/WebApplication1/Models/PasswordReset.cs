namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("PasswordReset")]
    public partial class PasswordReset
    {
        public int id { get; set; }

        [Required]
        [StringLength(255)]
        public string passwordCode { get; set; }
    }
}
