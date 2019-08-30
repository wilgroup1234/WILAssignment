using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;
using System.Linq;
using System.Web.Http;
using WebApplication1.Classes;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    public class ValuesController : ApiController
    {

        //Login                           https://localhost:44317/api/values/PostLogin
        //Register                        https://localhost:44317/api/values/PostRegister         
        //Retrieve goals                  https://localhost:44317/api/values/PostRetrieveGoals    
        //Add normal  goal                https://localhost:44317/api/values/PostAddNormalGoal
        //Add custom goal                 https://localhost:44317/api/values/PostAddCustomGoal
        //Mark off normal goal            https://localhost:44317/api/values/PostMarkOffGoal
        //Mark off custom goal            https://localhost:44317/api/values/PostMarkOffCustomGoal
        //Get Daily Quote                 https://localhost:44317/api/values/GetDailyQuote
        //Update dailyquote youtube views https://localhost:44317/api/values/PostUpdateViews
        //Google sign-in Reg              https://localhost:44317/api/values/PostGoogleSignIn
        //Update Streak                   https://localhost:44317/api/values/PostUpdateStreak
        //Get User Streak                 https://localhost:44317/api/values/PostUserStreak

        private WILModel db = new WILModel();

        [Route("api/values/GetNumber")]
        [HttpGet]
        public int GetNumber()
        {
            return 5;
        }

        //This POST method allows users to Register (Create an account for the app)
        [Route("api/values/PostRegister")]
        [HttpPost]
        public ReturnMessageObject PostRegister(RegisterUserObject regUser)
        {
            ReturnMessageObject returnMessage = new ReturnMessageObject();
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

                        User user = new User
                        {
                            Email = regUser.Email.Trim(),
                            FirstName = regUser.FirstName.Trim(),
                            LastName = regUser.LastName.Trim(),
                            Password = regUser.Password.Trim()
                        };


                        


                        db.Users.Add(user);

                        db.SaveChanges();

                        int streakUserID = 0;

                        foreach(User sUser in db.Users)
                        {
                            streakUserID = sUser.UserID;
                        }

                        Streak streak = new Streak
                        {
                            UserID = streakUserID,
                            StreakLength = 0
                        };

                        db.Streaks.Add(streak);

                        db.SaveChanges();


                        foreach(LifeSkill lifeSkill in db.LifeSkills)
                        {
                            UserLifeSkill userLifeSkill = new UserLifeSkill
                            {
                                LifeSKillID = lifeSkill.LifeSkillID,
                                UserID = streakUserID,
                                Completed = 0

                            };

                            db.UserLifeSkills.Add(userLifeSkill);
                            
                        }

                        db.SaveChanges();

                        DateTime today = DateTime.Today;

                        UserLoginDate userLoginDate = new UserLoginDate
                        {
                            UserID = streakUserID,
                            UserLoginDate1 = today

                        };

                        db.UserLoginDates.Add(userLoginDate);

                        db.SaveChanges();


                        returnMessage.result = true;
                        returnMessage.errorMessage = "Success";

                        return returnMessage;


                    }
                    catch (Exception e)
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
        public ReturnMessageObject PostLogin(LoginUserObject loginUser)
        {
            ReturnMessageObject returnMessage = new ReturnMessageObject();
            returnMessage.result = false;
            returnMessage.errorMessage = "Invalid Details Entered";

            Debug.WriteLine("OUR USER: " + loginUser.Email + " " + loginUser.Password);

            List<User> usersList = db.Users.ToList();

            Boolean userFound = false;
            Boolean passFound = false;
            String userPass = "";
            int userID = 0;


            foreach (User searchUser in usersList)
            {;


                if (searchUser.Email.Trim().Equals(loginUser.Email))
                {
                    Debug.WriteLine("USER FOUND");
                    userFound = true;
                    userPass = searchUser.Password;
                    Debug.WriteLine("USER DETAILS: " + loginUser.Email + " " + userPass);
                    userID = searchUser.UserID;
                }
            }


            //Find password

            if (userFound)
            {
                PasswordEncryption obj = PasswordEncryption.GetInstance;
                List<String> possiblePasswords;
                possiblePasswords = obj.GetPossiblePasswords(loginUser.Password);

                foreach (String p in possiblePasswords)
                {
                    Debug.WriteLine("possible pass: " + p);
                    if (p.Equals(userPass))
                    {
                        passFound = true;
                    }
                }
            }



            if (userFound && passFound)
            {
                

                db.SaveChanges();

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
        public ReturnGoalObject PostRetrieveGoals(UserGoalObject userGoal)
        {
            ReturnGoalObject returnGoal = new ReturnGoalObject();
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
            foreach(UserGoal goal in db.UserGoals)
            {
                if (goal.UserID == userID)
                {
                    Goal searchGoal = db.Goals.FirstOrDefault(g => g.GoalID == goal.GoalID);

                    Goal go = new Goal
                    {
                        GoalID = searchGoal.GoalID,
                        GoalName = searchGoal.GoalName,
                        GoalDescription = searchGoal.GoalDescription
                    };

                    goalList.Add(go);
                }
            }

            //search and add custom goals for user
            foreach (CustomUserGoal goal in db.CustomUserGoals)
            {
                if (goal.UserID == userID)
                {
                    CustomGoal searchGoal = db.CustomGoals.FirstOrDefault(go => go.GoalID == goal.GoalID);

                    Goal g = new Goal
                    {
                        GoalID = searchGoal.GoalID,
                        GoalName = searchGoal.GoalName,
                        GoalDescription = searchGoal.GoalDescription
                    };

                    goalList.Add(g);
                }
            }

            returnGoal.goalList = goalList;


            return returnGoal;
        }



        //Custom POST
        [Route("api/values/PostAddNormalGoal")]
        [HttpPost]
        public ReturnMessageObject PostAddNormalGoal(UserGoalObject userGoal)
        {

            bool valid;
            String userEmail = userGoal.Email;
            int userID = 0;
            UserGoal newUserGoal = new UserGoal();
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            
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
        public ReturnMessageObject PostAddCustomGoal(CustomGoalObject customGoal)
        {
            bool valid;
            String userEmail = customGoal.Email;
            int userID = 0;
            CustomUserGoal newUserGoal = new CustomUserGoal();
            CustomGoal customGoal1 = new CustomGoal();
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            
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
            customGoal1.FinishDate = customGoal.finishDate;

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

                List<CustomGoal> cgList = new List<CustomGoal>();

                foreach(CustomGoal cg in db.CustomGoals)
                {
                    cgList.Add(cg);
                    count++;
                }

                CustomGoal tempgoal = cgList[(count-1)];

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
        public ReturnMessageObject PostMarkOffGoal(UserGoalObject userGoal)
        {
            bool valid;
            String userEmail;
            int userSearchID = 0;
            int goalSearchID = userGoal.GoalId;
            ReturnMessageObject returnMessage = new ReturnMessageObject();

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
            foreach (UserGoal usergoal in db.UserGoals)
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
        public ReturnMessageObject PostMarkOffCustomGoal(UserGoalObject userGoal)
        {
            bool valid;
            String userEmail;
            int userSearchID = 0;
            int goalSearchID = userGoal.GoalId;
            ReturnMessageObject returnMessage = new ReturnMessageObject();

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
            foreach (CustomUserGoal usergoal in db.CustomUserGoals)
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


        //Custom POST
        [Route("api/values/PostRetrieveLifeSkills")]
        [HttpPost]
        public ReturnLifeSkillsObject PostRetrieveLifeSkills(LifeSkillObject lifeSkillObject)
        {
            ReturnLifeSkillsObject returnlifeskills = new ReturnLifeSkillsObject();
            List<LifeSkill> lifeskillsList = new List<LifeSkill>();
            String userEmail = lifeSkillObject.Email;
            int userID = 0;

            //search for user and get userID
            foreach (User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userID = user.UserID;
                }
            }

            //search and add goals for user
            foreach (UserLifeSkill userlifeskill in db.UserLifeSkills)
            {
                if (userlifeskill.UserID == userID)
                {
                    
                    LifeSkill searchLifeSkill = db.LifeSkills.FirstOrDefault(l => l.LifeSkillID == userlifeskill.LifeSKillID);

                    LifeSkill lo = new LifeSkill
                    {
                        LifeSkillID = searchLifeSkill.LifeSkillID,
                        LifeSkillName = searchLifeSkill.LifeSkillName
                    };
                    
                    lifeskillsList.Add(lo);
                }
            }


            returnlifeskills.LifeSkillsList = lifeskillsList;


            return returnlifeskills;
        }



        //Custom POST
        [Route("api/values/PostMarkOffLifeSkill")]
        [HttpPost]
        public ReturnMessageObject PostMarkOffLifeSkill(LifeSkillObject lifeSkillObject)
        {
            bool valid;
            String userEmail;
            int userSearchID = 0;
            int lifeskillSearchID = lifeSkillObject.LifeSkillID;
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            userEmail = lifeSkillObject.Email;

            //search for user and get userID
            foreach (User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userSearchID = user.UserID;
                }
            }

            //Search list of userLifeSkills for lifeskill
            foreach (UserLifeSkill userLifeSkill in db.UserLifeSkills)
            {
                if (userLifeSkill.LifeSKillID == lifeskillSearchID && userLifeSkill.UserID == userSearchID)
                {
                    if (userLifeSkill.Completed == 0)
                    {
                        userLifeSkill.Completed = 1;
                    }
                    else
                    {
                        userLifeSkill.Completed = 0;
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
        [Route("api/values/PostUpdateViews")]
        [HttpPost]
        public ReturnMessageObject PostUpdateViews()
        {
            
            ReturnMessageObject returnMessageObject = new ReturnMessageObject();

            int count = 0;

            foreach(DailyQuote dailyQuote in db.DailyQuotes)
            {
                count++;
            }

            int count2 = 0;

            try
            {
                foreach (DailyQuote dailyQuote in db.DailyQuotes)
                {
                    count2++;

                    if (count2 == count)
                    {
                        dailyQuote.Views = dailyQuote.Views + 1;

                        
                        returnMessageObject.result = true;
                    }


                }
                db.SaveChanges();
            }
            catch (Exception e)
            {
                Debug.WriteLine("Exception: " + e.ToString());
                returnMessageObject.result = false;
                returnMessageObject.errorMessage = e.ToString();
            }

            return returnMessageObject;



            


        }


        //Custom POST
        [Route("api/values/PostGoogleSignIn")]
        [HttpPost]
        public ReturnMessageObject PostGoogleSignIn(GoogleSignInObject googleSignInObject)
        {

            ReturnMessageObject returnMessageObject = new ReturnMessageObject();

            Boolean exists = false;
            
            foreach(User user in db.Users)
            {
                if (user.Email.Equals(googleSignInObject.Email.Trim()))
                {
                    exists = true;
                }
            }

            if (exists)
            {
                returnMessageObject.result = true;
                returnMessageObject.errorMessage = "Account exists already";
            }
            else
            {
                

                //Create New account

                try
                {
                    

                    User user = new User
                    {
                        Email = googleSignInObject.Email.Trim(),
                        FirstName = googleSignInObject.FirstName.Trim(),
                        LastName = googleSignInObject.LastName.Trim(),
                        Password = googleSignInObject.Password.Trim()
                    };





                    db.Users.Add(user);

                    db.SaveChanges();

                    int streakUserID = 0;

                    foreach (User sUser in db.Users)
                    {
                        streakUserID = sUser.UserID;
                    }

                    Streak streak = new Streak
                    {
                        UserID = streakUserID,
                        StreakLength = 0
                    };

                    db.Streaks.Add(streak);

                    db.SaveChanges();


                    foreach (LifeSkill lifeSkill in db.LifeSkills)
                    {
                        UserLifeSkill userLifeSkill = new UserLifeSkill
                        {
                            LifeSKillID = lifeSkill.LifeSkillID,
                            UserID = streakUserID,
                            Completed = 0

                        };

                        db.UserLifeSkills.Add(userLifeSkill);

                    }

                    db.SaveChanges();

                    DateTime today = DateTime.Today;

                    UserLoginDate userLoginDate = new UserLoginDate
                    {
                        UserID = streakUserID,
                        UserLoginDate1 = today

                    };

                    db.UserLoginDates.Add(userLoginDate);

                    db.SaveChanges();


                    returnMessageObject.result = false;
                    returnMessageObject.errorMessage = "New Account created";


                }
                catch (Exception e)
                {
                    Debug.WriteLine("Concurrency Error: " + e.ToString());

                    returnMessageObject.result = true;
                    returnMessageObject.errorMessage = "Account exists already";
                }

            }



            

            return returnMessageObject;


        }


        //Custom POST
        [Route("api/values/PostUpdateStreak")]
        [HttpPost]
        public ReturnMessageObject PostUpdateStreak(LoginUserObject loginUserObject)
        {

            ReturnMessageObject returnMessageObject = new ReturnMessageObject();

            bool valid;
            String userEmail;
            int userSearchID = 0;
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            userEmail = loginUserObject.Email;

            //search for user and get userID
            foreach (User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userSearchID = user.UserID;
                }
            }

            //Update streak if first login for today

            try
            {
                Boolean updateStreak = false;

                DateTime today = DateTime.Today;

                foreach (UserLoginDate userLoginDate in db.UserLoginDates)
                {
                    if (userLoginDate.UserLoginDate1 != today)
                    {
                        userLoginDate.UserLoginDate1 = today;
                        updateStreak = true;
                    }
                }

                db.SaveChanges();

                if (updateStreak)
                {
                    foreach (Streak streak in db.Streaks)
                    {
                        if (streak.UserID == userSearchID)
                        {
                            streak.StreakLength = streak.StreakLength + 1;
                        }
                    }
                }

                returnMessage.result = true;

            }

            catch(Exception e)
            {
                Debug.WriteLine(e.ToString());
                returnMessage.result = false;
                returnMessage.errorMessage = e.ToString();
            }

            return returnMessage;




        }


        //Custom POST
        [Route("api/values/PostUserStreak")]
        [HttpPost]
        public Streak PostUserStreak(LoginUserObject loginUserObject)
        {

            Streak returnStreak = new Streak(); 
            
            String userEmail;
            int userSearchID = 0;

            userEmail = loginUserObject.Email;

            //search for user and get userID
            foreach (User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userSearchID = user.UserID;
                }
            }

            //Update streak if first login for today

            foreach(Streak streak in db.Streaks)
            {
                if (userSearchID == streak.UserID)
                {
                    returnStreak.StreakLength = streak.StreakLength;
                }
            }

            return returnStreak;

        }








    }






    
}
