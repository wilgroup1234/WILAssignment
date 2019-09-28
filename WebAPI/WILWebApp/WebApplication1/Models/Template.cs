namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Template")]
    public partial class Template
    {
        public int TemplateID { get; set; }

        [Required]
        [StringLength(255)]
        public string TemplateName { get; set; }
    }
}
