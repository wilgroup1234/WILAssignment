namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class Goal
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Goal()
        {
            CustomUserGoals = new HashSet<CustomUserGoal>();
            UserGoals = new HashSet<UserGoal>();
        }

        public int GoalID { get; set; }

        [Required]
        [StringLength(255)]
        public string GoalName { get; set; }

        [Required]
        [StringLength(255)]
        public string GoalDescription { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<CustomUserGoal> CustomUserGoals { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<UserGoal> UserGoals { get; set; }
    }
}
