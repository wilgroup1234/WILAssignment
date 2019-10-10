using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class UserCvs
    {
        public int UserCvid { get; set; }
        public int UserId { get; set; }
        public int Cvid { get; set; }

        public Cvs Cv { get; set; }
        public Users User { get; set; }
    }
}
