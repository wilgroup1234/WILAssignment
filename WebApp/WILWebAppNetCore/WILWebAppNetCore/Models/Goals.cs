using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class Goals
    {
        public Goals()
        {
            CustomUserGoals = new HashSet<CustomUserGoals>();
            UserGoals = new HashSet<UserGoals>();
        }

        public int GoalId { get; set; }
        public string GoalName { get; set; }
        public string GoalDescription { get; set; }

        public ICollection<CustomUserGoals> CustomUserGoals { get; set; }
        public ICollection<UserGoals> UserGoals { get; set; }
    }
}
