using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WILWebAppNetCore.Classes
{
    public class StaticClass
    {
        public static String errorMessage = "NO_ERROR";

        public static String currentUser = "NO_USER";

        public static String connection = "Server=tcp:willgroup1234srv.database.windows.net,1433;Initial Catalog=willgroup1234db;Persist Security Info=False;User ID=willgroup1234admin;Password=Catherine44;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;";

        public static Boolean loggedIn = false;
    }
}