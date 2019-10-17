using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebApplication1.Classes
{
    public class RegisterUserObject
    {
            public int UserID { get; set; }

            [Required]
            [StringLength(255)]
            public string LastName { get; set; }

            [Required]
            [StringLength(255)]
            public string FirstName { get; set; }

            [Required]
            [StringLength(255)]
            [EmailAddress]
            public string Email { get; set; }

            [Required]
            [StringLength(255)]
            public string Password { get; set; }

            [Required]
            [StringLength(255)]
            public string ConfirmPassword { get; set; }

            public string SecurityQuestion{ get; set; }

            public string Answer { get; set; }




    }
}