using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class Leaderboards
    {
        public int Id { get; set; }
        public int Score { get; set; }
        public int UserId { get; set; }

        public Users User { get; set; }
    }
}
