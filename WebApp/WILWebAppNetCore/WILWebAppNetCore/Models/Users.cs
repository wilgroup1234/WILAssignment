using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WILWebAppNetCore.Models
{
    public partial class Users
    {
        public int UserID { get; set; }

        [Required]
        [StringLength(255)]
        public string LastName { get; set; }

        [Required]
        [StringLength(255)]
        public string FirstName { get; set; }

        public int Age { get; set; }

        [Required]
        [StringLength(255)]
        [EmailAddress]
        public string Email { get; set; }

        [Required]
        [StringLength(255)]
        public string Password { get; set; }
    }
}
