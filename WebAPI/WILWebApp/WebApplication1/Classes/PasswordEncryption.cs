using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebApplication1.Classes
{
    //Some code used in this class was derived from Youtube.com.
    //link: https://www.youtube.com/watch?v=AU-4oLUV5VU
    //Author: C D

    public class PasswordEncryption
    {
        private List<String> peppers = new List<String>();
        private static String SALT = "a@0(";

        private PasswordEncryption()
        {
            FillPeppersList();
        }

        private static readonly Lazy<PasswordEncryption> instance = new Lazy<PasswordEncryption>(() => new PasswordEncryption());

        public static PasswordEncryption GetInstance
        {
            get
            {
                return instance.Value;
            }
        }


        public void FillPeppersList()
        {
            peppers.Add("a");
            peppers.Add("r");
            peppers.Add("y");
            peppers.Add("o");
            peppers.Add("l");
            peppers.Add(",");
            peppers.Add("j");
            peppers.Add("'");
            peppers.Add("[");
            peppers.Add("!");
        }

        public String GetHashedPassword(String password)
        {
            Random random = new Random();
            int num = random.Next(0, 9);
            String pep = peppers[num];

            byte[] bytes = System.Text.Encoding.UTF8.GetBytes(password + SALT + pep);
            System.Security.Cryptography.SHA256Managed sHA256 = new System.Security.Cryptography.SHA256Managed();
            byte[] hash = sHA256.ComputeHash(bytes);

            string hashedPassword = ByteArrayToString(hash);

            return hashedPassword;
        }

        public static string ByteArrayToString(byte[] ba)
        {
            return BitConverter.ToString(ba).Replace("-", "");
        }

        public List<String> GetPossiblePasswords(String password)
        {
            List<String> possiblePasswords = new List<string>();

            foreach (String p in peppers)
            {
                byte[] bytes = System.Text.Encoding.UTF8.GetBytes(password + SALT + p);
                System.Security.Cryptography.SHA256Managed sHA256 = new System.Security.Cryptography.SHA256Managed();
                byte[] hash = sHA256.ComputeHash(bytes);

                string hashedPassword = ByteArrayToString(hash);
                possiblePasswords.Add(hashedPassword);
            }

            return possiblePasswords;
        }


    }

}