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

        public static String connection = "Server=DESKTOP-6J4LFB4;Database=WILDatabase;Trusted_Connection=True;ConnectRetryCount=0";

        public static Boolean loggedIn = false;
    }
}