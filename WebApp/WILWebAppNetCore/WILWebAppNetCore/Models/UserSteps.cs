using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class UserSteps
    {
        public int UserStepsId { get; set; }
        public DateTime UserStepsDate { get; set; }
        public int Steps { get; set; }
        public int UserId { get; set; }

        public Users User { get; set; }
    }
}
