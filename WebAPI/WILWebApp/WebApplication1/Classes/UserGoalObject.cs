using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication1.Classes
{
    public class UserGoalObject
    {
        public String Email { get; set; }

        public int GoalId { get; set; }

        public DateTime finishDate { get; set; }
    }
}