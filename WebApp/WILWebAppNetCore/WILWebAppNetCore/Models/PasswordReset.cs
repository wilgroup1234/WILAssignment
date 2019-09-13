using System;
using System.Collections.Generic;

namespace WILWebAppNetCore.Models
{
    public partial class PasswordReset
    {
        public int Id { get; set; }
        public string PasswordCode { get; set; }
    }
}
