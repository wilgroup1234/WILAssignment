using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication1.Classes
{
    public class UpdateCVObject
    {
        public String Email { get; set; }

        public int CVID { get; set; }
        public String IDNumber{ get; set; }

        public DateTime DOB { get; set; }

        public String HighSchoolName { get; set; }

        public String PreviousWorkExperience { get; set; }

        public String WorkReferences{ get; set; }

        public String Languages { get; set; }

        public String Acheivements { get; set; }

        public String Nationality { get; set; }

        public String Interests { get; set; }

        public String PhoneNumber { get; set; }

        public String Address { get; set; }
    }
}