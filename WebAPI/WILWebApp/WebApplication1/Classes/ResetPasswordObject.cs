using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication1.Classes
{
    public class ResetPasswordObject
    {
        public String Question { get; set; }

        public String Answer { get; set; }

        public String Email { get; set; }

        public String NewPassword { get; set; }
    }
}