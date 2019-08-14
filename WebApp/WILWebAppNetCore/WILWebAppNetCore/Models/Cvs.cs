using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class Cvs
    {
        public Cvs()
        {
            UserCvs = new HashSet<UserCvs>();
        }

        public int Cvid { get; set; }
        public string LifeSkillName { get; set; }
        public string Idnumber { get; set; }
        public DateTime DateOfBirth { get; set; }
        public string HighSchoolName { get; set; }
        public string PreviousWorkExperience { get; set; }
        public string WorkReferences { get; set; }
        public string Languages { get; set; }
        public string Achievements { get; set; }
        public string Nationality { get; set; }
        public string Interests { get; set; }
        public string Email { get; set; }
        public string PhoneNumber { get; set; }
        public string Address { get; set; }

        public ICollection<UserCvs> UserCvs { get; set; }
    }
}
