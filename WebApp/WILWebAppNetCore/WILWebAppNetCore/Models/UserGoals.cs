using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class UserGoals
    {
        public int UserGoalId { get; set; }
        public int UserId { get; set; }
        public int GoalId { get; set; }
        public int Completed { get; set; }

        public Goals Goal { get; set; }
        public Users User { get; set; }
    }
}
