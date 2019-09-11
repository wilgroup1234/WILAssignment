﻿using System;
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
        //Update Steps                    https://localhost:44317/api/values/PostUpdateSteps
        //Get User Steps                  https://localhost:44317/api/values/PostUserSteps
        //Update Gratitude                https://localhost:44317/api/values/PostUpdateGratitude
        //Get User Gratitude              https://localhost:44317/api/values/PostUserGratitude
        //Get All Goals                   https://localhost:44317/api/values/GetAllGoals
        //Get user lifeskills             https://localhost:44317/api/values/PostRetrieveLifeSkills
        //Mark off life skill             https://localhost:44317/api/values/PostMarkOffLifeSkill


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
                Boolean setToZero = false;

                DateTime today = DateTime.Today;

                //Check if user streak must be updated or set to 0;

                foreach (UserLoginDate userLoginDate in db.UserLoginDates)
                {
                    if (userLoginDate.UserID == userSearchID && userLoginDate.UserLoginDate1 != today)
                    {

                        if ((today - userLoginDate.UserLoginDate1).TotalDays < 2)
                        {

                        }
                        else
                        {
                            setToZero = true;
                        }

                        userLoginDate.UserLoginDate1 = today;
                        updateStreak = true;
                    }

                   
                }




                db.SaveChanges();

                if (updateStreak)
                {
                    foreach(Streak streak in db.Streaks)
                    {
                        if (streak.UserID == userSearchID)
                        {
                            if (setToZero)
                            {
                                streak.StreakLength = 0;
                            }
                            else
                            {
                                streak.StreakLength = streak.StreakLength + 1;
                            }
                            
                        }
                    }
                }

                db.SaveChanges();

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

            //Get streak for user

            foreach(Streak streak in db.Streaks)
            {
                if (userSearchID == streak.UserID)
                {
                    returnStreak.StreakLength = streak.StreakLength;
                }
            }

            return returnStreak;

        }



        //Custom POST
        [Route("api/values/PostUserSteps")]
        [HttpPost]
        public UserStepsObject PostUserSteps(UserStepsObject userStepsObject)
        {

            UserStepsObject returnSteps = new UserStepsObject();

            String userEmail;
            int userSearchID = 0;
            DateTime today = DateTime.Today;

            userEmail = userStepsObject.Email;

            //search for user and get userID
            foreach (User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userSearchID = user.UserID;
                }
            }

            //Get userSteps for today

            Boolean hasSteps = false;

            foreach(UserStep userSteps in db.UserSteps)
            {
                if (userSteps.UserID == userSearchID && userSteps.UserStepsDate == today)
                {
                    returnSteps.numSteps = userSteps.Steps;
                    hasSteps = true;
                }
            }

            if (!hasSteps)
            {
                returnSteps.numSteps = 0;
            }

            return returnSteps;

        }





        //Custom POST
        [Route("api/values/PostUpdateSteps")]
        [HttpPost]
        public ReturnMessageObject PostUpdateSteps(UserStepsObject userStepsObject)
        {

            String userEmail;
            int userSearchID = 0;
            DateTime today = DateTime.Today;
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            userEmail = userStepsObject.Email;

            //search for user and get userID
            foreach (User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userSearchID = user.UserID;
                }
            }

            //Check if user has steps for today


            try
            {
                Boolean hasSteps = false;

                foreach (UserStep userSteps in db.UserSteps)
                {
                    if (userSteps.UserID == userSearchID && userSteps.UserStepsDate == today)
                    {
                        userSteps.Steps = userSteps.Steps + userStepsObject.numSteps;
                        hasSteps = true;
                    }

                    //delete old steps
                    if (userSteps.UserID == userSearchID && userSteps.UserStepsDate != today)
                    {
                        db.UserSteps.Remove(userSteps);
                    }
                }

                db.SaveChanges();


                if (!hasSteps)
                {
                    UserStep userStep = new UserStep
                    {
                        UserID = userSearchID,
                        Steps = userStepsObject.numSteps,
                        UserStepsDate = today
                    };

                    db.UserSteps.Add(userStep);
                    db.SaveChanges();

                }

                returnMessage.result = true;



            }
            catch(Exception e)
            {
                returnMessage.result = false;
                returnMessage.errorMessage = e.ToString();
            }


            return returnMessage;
            

        }


        //Custom POST
        [Route("api/values/PostUserGratitude")]
        [HttpPost]
        public GratitudeObject PostUserGratitude(GratitudeObject gratitudeObject)
        {

            GratitudeObject returnGratitude = new GratitudeObject();

            String userEmail;
            int userSearchID = 0;
            DateTime today = DateTime.Today;

            userEmail = gratitudeObject.Email;

            //search for user and get userID
            foreach (User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userSearchID = user.UserID;
                }
            }

            //Get user Gratitude for today

            Boolean hasGratitude = false;

            foreach (Gratitude gratitude in db.Gratitudes)
            {
                if (gratitude.UserID == userSearchID && gratitude.GratitudeDate == today)
                {
                    returnGratitude.Items = gratitude.GratitudeItems;
                    hasGratitude = true;
                }
            }

            if (!hasGratitude)
            {
                returnGratitude.Items = "No_Items";
            }

            return returnGratitude;

        }


        //Custom POST
        [Route("api/values/PostUpdateGratitude")]
        [HttpPost]
        public ReturnMessageObject PostUpdateGratitude(GratitudeObject gratitudeObject)
        {

            String userEmail;
            int userSearchID = 0;
            DateTime today = DateTime.Today;
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            userEmail = gratitudeObject.Email;

            //search for user and get userID
            foreach (User user in db.Users)
            {
                if (user.Email.Equals(userEmail))
                {
                    userSearchID = user.UserID;
                }
            }

            //Check if user has gratitude for today

            try
            {
                Boolean hasGratitude = false;

                foreach (Gratitude gratitude in db.Gratitudes)
                {
                    if (gratitude.UserID == userSearchID && gratitude.GratitudeDate == today)
                    {
                        gratitude.GratitudeItems = gratitudeObject.Items;
                        hasGratitude = true;
                    }

                    //delete old steps
                    if (gratitude.UserID == userSearchID && gratitude.GratitudeDate != today)
                    {
                        db.Gratitudes.Remove(gratitude);
                    }
                }

                db.SaveChanges();


                if (!hasGratitude)
                {
                    Gratitude userGratitude = new Gratitude
                    {
                        UserID = userSearchID,
                        GratitudeItems = gratitudeObject.Items,
                        GratitudeDate = today
                    };

                    db.Gratitudes.Add(userGratitude);
                    db.SaveChanges();

                }

                returnMessage.result = true;



            }
            catch (Exception e)
            {
                returnMessage.result = false;
                returnMessage.errorMessage = e.ToString();
            }


            return returnMessage;


        }


        [Route("api/values/GetAllGoals")]
        [HttpGet]
        public List<Goal> GetAllGoals()
        {
            List<Goal> goalList = new List<Goal>();

            foreach(Goal goal in db.Goals)
            {
                Goal goal1 = new Goal
                {
                    GoalID = goal.GoalID,
                    GoalName = goal.GoalName,
                    GoalDescription = goal.GoalDescription
                };
                goalList.Add(goal);
            }

            return goalList;
        }






    }
 
}
