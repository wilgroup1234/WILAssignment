namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class CustomGoal
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public CustomGoal()
        {
            CustomUserGoals = new HashSet<CustomUserGoal>();
        }

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

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<CustomUserGoal> CustomUserGoals { get; set; }
    }
}
