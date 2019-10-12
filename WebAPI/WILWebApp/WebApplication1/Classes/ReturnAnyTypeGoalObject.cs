using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication1.Classes
{
    public class ReturnAnyTypeGoalObject
    {
        public int GoalID { get; set; }
        public string GoalName { get; set; }
        public string GoalDescription { get; set; }

        public int Completed { get; set; }

        public String finishDate { get; set; }

        public String currentDate { get; set; }

        public Boolean isNormalGoal { get; set; }
    }
}