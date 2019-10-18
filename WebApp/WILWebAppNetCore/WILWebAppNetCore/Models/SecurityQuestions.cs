using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class SecurityQuestions
    {
        public int Id { get; set; }
        public string Question { get; set; }
        public string Answer { get; set; }
        public int UserId { get; set; }

        public Users User { get; set; }
    }
}
