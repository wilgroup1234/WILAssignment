using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class Users
    {
        public Users()
        {
            CustomUserGoals = new HashSet<CustomUserGoals>();
            Gratitude = new HashSet<Gratitude>();
            Leaderboards = new HashSet<Leaderboards>();
            SecurityQuestions = new HashSet<SecurityQuestions>();
            Streaks = new HashSet<Streaks>();
            UserGoals = new HashSet<UserGoals>();
            UserLifeSkills = new HashSet<UserLifeSkills>();
            UserLoginDates = new HashSet<UserLoginDates>();
            UserSteps = new HashSet<UserSteps>();
        }

        public int UserId { get; set; }
        public string LastName { get; set; }
        public string FirstName { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }

        public ICollection<CustomUserGoals> CustomUserGoals { get; set; }
        public ICollection<Gratitude> Gratitude { get; set; }
        public ICollection<Leaderboards> Leaderboards { get; set; }
        public ICollection<SecurityQuestions> SecurityQuestions { get; set; }
        public ICollection<Streaks> Streaks { get; set; }
        public ICollection<UserGoals> UserGoals { get; set; }
        public ICollection<UserLifeSkills> UserLifeSkills { get; set; }
        public ICollection<UserLoginDates> UserLoginDates { get; set; }
        public ICollection<UserSteps> UserSteps { get; set; }
    }
}
