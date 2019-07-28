using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using WebApplication1.Classes;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    public class ValuesController : ApiController
    {

        private WILModel db = new WILModel();


        // GET api/values
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        // GET api/values/5
        public string Get(int id)
        {
            return "value";
        }

        // POST api/values
        public void Post([FromBody]string value)
        {
        }

        // PUT api/values/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/values/5
        public void Delete(int id)
        {
        }

        //Custom GET
        [Route("api/values/GetNumber")]
        [HttpGet]
        public int GetNumber()
        {
            return 5;
        }


        //Custom POST
        [Route("api/values/PostNumber")]
        [HttpPost]
        public void PostNumber(TestClass t)
        {

            Debug.WriteLine(t.Number.ToString());
        }


        //Custom POST
        [Route("api/values/PostRegister")]
        [HttpPost]
        public ReturnMessage PostRegister(RegisterUser regUser)
        {
            ReturnMessage returnMessage = new ReturnMessage();
            returnMessage.result = false;
            returnMessage.errorMessage = "Invalid Details Entered";

            if (ModelState.IsValid)
            {
                if (regUser.Password.Equals(regUser.ConfirmPassword))
                {
                    try
                    {
                        PasswordEncryption obj = PasswordEncryption.GetInstance;
                        regUser.Password = obj.GetHashedPassword(regUser.Password);

                        User user = new User();
                        user.Age = regUser.Age;
                        user.Email = regUser.Email;
                        user.FirstName = regUser.FirstName;
                        user.LastName = regUser.LastName;
                        user.Password = regUser.Password;
                        

                        db.Users.Add(user);
                        db.SaveChanges();

                        returnMessage.result = true;
                        returnMessage.errorMessage = "Success";

                        return returnMessage;


                    }
                    catch (DBConcurrencyException e)
                    {
                        Debug.WriteLine("Concurrency Error: " + e.ToString());

                        returnMessage.result = false;
                        returnMessage.errorMessage = "Concurrency Error";

                        return returnMessage;
                    }

                }
                else
                {
                    Debug.WriteLine("Passwords Don't Match");

                    returnMessage.result = false;
                    returnMessage.errorMessage = "Passwords Don't Match";

                    return returnMessage;
                }


            }
            else
            {

                Debug.WriteLine("ERROR: Invalid Information Entered");

                returnMessage.result = false;
                returnMessage.errorMessage = "Invalid Information Entered";

                return returnMessage;

            }

        }



        //Custom POST
        [Route("api/values/PostLogin")]
        [HttpPost]
        public ReturnMessage PostLogin(LoginUser loginUser)
        {
            ReturnMessage returnMessage = new ReturnMessage();
            returnMessage.result = false;
            returnMessage.errorMessage = "Invalid Details Entered";

            Debug.WriteLine("OUR USER: " + loginUser.Email + " " + loginUser.Password);

            List<User> usersList = db.Users.ToList();

            Boolean userFound = false;
            Boolean passFound = false;
            String userPass = "";


            foreach (User searchUser in usersList)
            {
                if (searchUser.Email.Equals(loginUser.Email))
                {
                    Debug.WriteLine("USER FOUND");
                    userFound = true;
                    userPass = searchUser.Password;
                }
            }


            //Find password

            if (userFound)
            {
                PasswordEncryption obj = PasswordEncryption.GetInstance;
                List<String> possiblePasswords = new List<string>();
                possiblePasswords = obj.GetPossiblePasswords(loginUser.Password);

                foreach (String p in possiblePasswords)
                {
                    if (p.Equals(userPass))
                    {
                        passFound = true;
                    }
                }
            }



            if (userFound && passFound)
            {
                Debug.WriteLine("SUCCESSSSSSSS!!!!!!!!!!!!!!");

                returnMessage.result = true;
                returnMessage.errorMessage = "Success";
                return returnMessage;
            }
            else
            {

                Debug.WriteLine("INVALID Details Entered, Please Try again...");
                returnMessage.result = false;
                returnMessage.errorMessage = "Invalid Details Entered";
                return returnMessage;

            }


        }





    }
}
