using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class UserLoginDates
    {
        public int UserLoginDatesId { get; set; }
        public int UserId { get; set; }
        public DateTime UserLoginDate { get; set; }

        public Users User { get; set; }
    }
}
