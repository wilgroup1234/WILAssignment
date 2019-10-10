using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class LifeSkills
    {
        public LifeSkills()
        {
            UserLifeSkills = new HashSet<UserLifeSkills>();
        }

        public int LifeSkillId { get; set; }
        public string LifeSkillName { get; set; }

        public ICollection<UserLifeSkills> UserLifeSkills { get; set; }
    }
}
