namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class CustomGoal
    {
        [Key]
        public int GoalID { get; set; }

        [Required]
        [StringLength(255)]
        public string GoalName { get; set; }

        [Required]
        [StringLength(255)]
        public string GoalDescription { get; set; }

        [Column(TypeName = "date")]
        public DateTime? FinishDate { get; set; }
    }
}
