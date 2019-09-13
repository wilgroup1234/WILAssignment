namespace WebApplication1.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class UserLifeSkill
    {
        public int UserLifeSkillID { get; set; }

        public int UserID { get; set; }

        public int LifeSKillID { get; set; }

        public int Completed { get; set; }

        public virtual LifeSkill LifeSkill { get; set; }

        public virtual User User { get; set; }
    }
}
