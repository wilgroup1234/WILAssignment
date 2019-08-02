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

        //Login                    https://localhost:44317/api/values/PostLogin
        //Register                 https://localhost:44317/api/values/PostRegister         
        //Retrieve goals           https://localhost:44317/api/values/PostRetrieveGoals    
        //Add normal  goal         https://localhost:44317/api/values/PostAddNormalGoal
        //Add custom goal          https://localhost:44317/api/values/PostAddCustomGoal
        //Mark off normal goal     https://localhost:44317/api/values/PostMarkOffGoal
        //Mark off custom goal     https://localhost:44317/api/values/PostMarkOffCustomGoal
        //Get Daily Quote          https://localhost:44317/api/values/GetDailyQuote


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



        //Custom POST
        [Route("api/values/PostRetrieveGoals")]
        [HttpPost]
        public ReturnGoal PostRetrieveGoals(Classes.UserGoal userGoal)
        {
            ReturnGoal returnGoal = new ReturnGoal();
            List<Goal> goalList = new List<Goal>();
            String userEmail = userGoal.Email;
            int userID = 0;

            //search for user and get userID
            foreach(User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userID = user.UserID;
                }
            }

            //search and add goals for user
            foreach(Models.UserGoal goal in db.UserGoals)
            {
                if (goal.UserID == userID)
                {
                    Goal searchGoal = db.Goals.FirstOrDefault(g => g.GoalID == goal.GoalID);

                    Goal go = new Goal();
                    go.GoalID = searchGoal.GoalID;
                    go.GoalName = searchGoal.GoalName;
                    go.GoalDescription = searchGoal.GoalDescription;

                    goalList.Add(go);
                }
            }

            //search and add custom goals for user
            foreach (Models.CustomUserGoal goal in db.CustomUserGoals)
            {
                if (goal.UserID == userID)
                {
                    Models.CustomGoal searchGoal = db.CustomGoals.FirstOrDefault(go => go.GoalID == goal.GoalID);

                    Goal g = new Goal();
                    g.GoalID = searchGoal.GoalID;
                    g.GoalName = searchGoal.GoalName;
                    g.GoalDescription = searchGoal.GoalDescription;

                    goalList.Add(g);
                }
            }

            returnGoal.goalList = goalList;


            return returnGoal;
        }



        //Custom POST
        [Route("api/values/PostAddNormalGoal")]
        [HttpPost]
        public ReturnMessage PostAddNormalGoal(Classes.UserGoal userGoal)
        {

            bool valid = false;
            String userEmail = userGoal.Email;
            int userID = 0;
            Models.UserGoal newUserGoal = new Models.UserGoal();
            ReturnMessage returnMessage = new ReturnMessage();

            
            //search for user and get userID
            foreach (User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userID = user.UserID;
                }
            }

            newUserGoal.UserID = userID;
            newUserGoal.GoalID = userGoal.GoalId;
            newUserGoal.Completed = 0;

            try
            {
                
                db.UserGoals.Add(newUserGoal);
                db.SaveChanges();
                valid = true;
            }
            catch(DBConcurrencyException e)
            {
                Debug.WriteLine("Concurrency Error: " + e.ToString());
                returnMessage.errorMessage = "Concurrency Error: " + e.ToString();
                valid = false;
            }

            returnMessage.result = valid;

            return returnMessage;

        }




        //Custom POST
        [Route("api/values/PostAddCustomGoal")]
        [HttpPost]
        public ReturnMessage PostAddCustomGoal(Classes.CustomGoal customGoal)
        {
            bool valid = false;
            String userEmail = customGoal.Email;
            int userID = 0;
            CustomUserGoal newUserGoal = new CustomUserGoal();
            Models.CustomGoal customGoal1 = new Models.CustomGoal();
            ReturnMessage returnMessage = new ReturnMessage();

            
            //search for user and get userID
            foreach (User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userID = user.UserID;
                }
            }

            customGoal1.GoalDescription = customGoal.goalDescription;
            customGoal1.GoalName = customGoal.goalName;

            //save custom goal
            try
            {

                db.CustomGoals.Add(customGoal1);
                db.SaveChanges();
                valid = true;
            }
            catch (DBConcurrencyException e)
            {
                Debug.WriteLine("Concurrency Error: " + e.ToString());
                returnMessage.errorMessage = "Concurrency Error: " + e.ToString();
                valid = false;
            }

            if (valid == true)
            {
                //search for custom goal id
                int count = 0;

                List<Models.CustomGoal> cgList = new List<Models.CustomGoal>();

                foreach(Models.CustomGoal cg in db.CustomGoals)
                {
                    cgList.Add(cg);
                    count++;
                }

                Models.CustomGoal tempgoal = cgList[(count-1)];

                int customGoalID = tempgoal.GoalID;

                newUserGoal.UserID = userID;
                newUserGoal.Completed = 0;
                newUserGoal.GoalID = customGoalID;


                //save custom user goal
                try
                {

                    db.CustomUserGoals.Add(newUserGoal);
                    db.SaveChanges();
                    valid = true;
                }
                catch (DBConcurrencyException e)
                {
                    Debug.WriteLine("Concurrency Error: " + e.ToString());
                    returnMessage.errorMessage = "Concurrency Error: " + e.ToString();
                    valid = false;
                }


            }



            returnMessage.result = valid;

            return returnMessage;
        }




        //Custom POST
        [Route("api/values/PostMarkOffGoal")]
        [HttpPost]
        public ReturnMessage PostMarkOffGoal(Classes.UserGoal userGoal)
        {
            bool valid = false;
            String userEmail = "";
            int userSearchID = 0;
            int goalSearchID = userGoal.GoalId;
            ReturnMessage returnMessage = new ReturnMessage();

            userEmail = userGoal.Email;

            //search for user and get userID
            foreach (User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userSearchID = user.UserID;
                }
            }

            //Search list of userGoals for goal
            foreach (Models.UserGoal usergoal in db.UserGoals)
            {
                if (usergoal.GoalID == goalSearchID && usergoal.UserID == userSearchID)
                {
                    if (usergoal.Completed == 0)
                    {
                        usergoal.Completed = 1;
                    }
                    else
                    {
                        usergoal.Completed = 0;
                    }
                }
            }


            try
            {
                db.SaveChanges();
                valid = true;
            }
            catch (DBConcurrencyException e)
            {
                Debug.WriteLine("Concurrency Error: " + e.ToString());
                returnMessage.errorMessage = "Concurrency Error: " + e.ToString();
                valid = false;
            }

            returnMessage.result = valid;

            return returnMessage;


        }


        //Custom POST
        [Route("api/values/PostMarkOffCustomGoal")]
        [HttpPost]
        public ReturnMessage PostMarkOffCustomGoal(Classes.UserGoal userGoal)
        {
            bool valid = false;
            String userEmail = "";
            int userSearchID = 0;
            int goalSearchID = userGoal.GoalId;
            ReturnMessage returnMessage = new ReturnMessage();

            userEmail = userGoal.Email;

            //search for user and get userID
            foreach (User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userSearchID = user.UserID;
                }
            }

            //Search list of userGoals for goal
            foreach (Models.CustomUserGoal usergoal in db.CustomUserGoals)
            {
                if (usergoal.GoalID == goalSearchID && usergoal.UserID == userSearchID)
                {
                    if (usergoal.Completed == 0)
                    {
                        usergoal.Completed = 1;
                    }
                    else
                    {
                        usergoal.Completed = 0;
                    }
                }
            }


            try
            {
                db.SaveChanges();
                valid = true;
            }
            catch (DBConcurrencyException e)
            {
                Debug.WriteLine("Concurrency Error: " + e.ToString());
                returnMessage.errorMessage = "Concurrency Error: " + e.ToString();
                valid = false;
            }

            returnMessage.result = valid;

            return returnMessage;
        }



        //Custom GET
        [Route("api/values/GetDailyQuote")]
        [HttpGet]
        public DailyQuote GetDailyQuote()
        {
            DailyQuote dailyQuote = new DailyQuote();

            foreach(DailyQuote quote in db.DailyQuotes)
            {
                dailyQuote.QuoteDate = quote.QuoteDate;
                dailyQuote.QuoteText = quote.QuoteText;
                dailyQuote.TemplateID = quote.TemplateID;
                dailyQuote.YoutubeLink = quote.YoutubeLink;
            }

            return dailyQuote;
        }




    }
}
