namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class CV
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public CV()
        {
            UserCVs = new HashSet<UserCV>();
        }

        public int CVID { get; set; }

        [Required]
        [StringLength(255)]
        public string LifeSkillName { get; set; }

        [Required]
        [StringLength(255)]
        public string IDNumber { get; set; }

        [Column(TypeName = "date")]
        public DateTime DateOfBIrth { get; set; }

        [Required]
        [StringLength(255)]
        public string HighSchoolName { get; set; }

        [Required]
        [StringLength(255)]
        public string PreviousWorkExperience { get; set; }

        [Required]
        [StringLength(255)]
        public string WorkReferences { get; set; }

        [Required]
        [StringLength(255)]
        public string Languages { get; set; }

        [Required]
        [StringLength(255)]
        public string Achievements { get; set; }

        [Required]
        [StringLength(255)]
        public string Nationality { get; set; }

        [Required]
        [StringLength(255)]
        public string Interests { get; set; }

        [Required]
        [StringLength(255)]
        public string Email { get; set; }

        [Required]
        [StringLength(255)]
        public string PhoneNumber { get; set; }

        [Required]
        [StringLength(255)]
        public string Address { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<UserCV> UserCVs { get; set; }
    }
}
