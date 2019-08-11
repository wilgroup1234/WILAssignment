﻿using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class Users
    {
        public Users()
        {
            CustomUserGoals = new HashSet<CustomUserGoals>();
            UserCvs = new HashSet<UserCvs>();
            UserGoals = new HashSet<UserGoals>();
            UserLifeSkills = new HashSet<UserLifeSkills>();
        }

        public int UserId { get; set; }
        public string LastName { get; set; }
        public string FirstName { get; set; }
        public int Age { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }

        public ICollection<CustomUserGoals> CustomUserGoals { get; set; }
        public ICollection<UserCvs> UserCvs { get; set; }
        public ICollection<UserGoals> UserGoals { get; set; }
        public ICollection<UserLifeSkills> UserLifeSkills { get; set; }
    }
}
