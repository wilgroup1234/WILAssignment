using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class UserLifeSkills
    {
        public int UserLifeSkillId { get; set; }
        public int UserId { get; set; }
        public int LifeSkillId { get; set; }
        public int Completed { get; set; }

        public LifeSkills LifeSkill { get; set; }
        public Users User { get; set; }
    }
}
