using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class CustomGoals
    {
        public CustomGoals()
        {
            CustomUserGoals = new HashSet<CustomUserGoals>();
        }

        public int GoalId { get; set; }
        public string GoalName { get; set; }
        public string GoalDescription { get; set; }
        public DateTime? FinishDate { get; set; }

        public ICollection<CustomUserGoals> CustomUserGoals { get; set; }
    }
}
