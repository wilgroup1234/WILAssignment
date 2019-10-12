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
        //Update Steps                    https://localhost:44317/api/values/PostUpdateSteps
        //Get User Steps                  https://localhost:44317/api/values/PostUserSteps
        //Update Gratitude                https://localhost:44317/api/values/PostUpdateGratitude
        //Get User Gratitude              https://localhost:44317/api/values/PostUserGratitude
        //Get All Goals                   https://localhost:44317/api/values/GetAllGoals
        //Get user lifeskills             https://localhost:44317/api/values/PostRetrieveLifeSkills
        //Mark off life skill             https://localhost:44317/api/values/PostMarkOffLifeSkill
        //Get All Goals                   https://localhost:44317/api/values/GetAllGoals
        //Delete Goal                     https://localhost:44317/api/values/PostDeleteGoal
        //Get Top 8 Scores                https://localhost:44317/api/values/PostGetTopEight
        //Add Score To Leaderboard        https://localhost:44317/api/values/PostUploadScore

        //Create instance of database
        private WILModel db = new WILModel();


        //This POST method allows users to Register (Create an account for the app)
        [Route("api/values/PostRegister")]
        [HttpPost]
        public ReturnMessageObject PostRegister(RegisterUserObject regUser)
        {
            //declare return object
            ReturnMessageObject returnMessage = new ReturnMessageObject();
            
            try
            {
                returnMessage.result = false;
                returnMessage.errorMessage = "Invalid Details Entered";

                if (ModelState.IsValid)
                {
                    //if password and confirm password values match, proceed
                    if (regUser.Password.Equals(regUser.ConfirmPassword))
                    {
                        try
                        {
                            //Hash and salt password
                            PasswordEncryption obj = PasswordEncryption.GetInstance;
                            regUser.Password = obj.GetHashedPassword(regUser.Password);

                            //Create new user object
                            User user = new User
                            {
                                Email = regUser.Email.Trim(),
                                FirstName = regUser.FirstName.Trim(),
                                LastName = regUser.LastName.Trim(),
                                Password = regUser.Password.Trim()
                            };

                            //Add new user to db and save changes
                            try
                            {
                                db.Users.Add(user);

                                db.SaveChanges();
                            }
                            catch(Exception e)
                            {
                                Debug.WriteLine(e);
                            }

                            //Create streak for new user
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

                            //add new streak item to db and save changes
                            try
                            {
                                db.Streaks.Add(streak);

                                db.SaveChanges();
                            }
                            catch (Exception e)
                            {
                                Debug.WriteLine(e);
                            }
                           


                            try
                            {
                                //add all lifeskills for the new user to the userlifeskills table and save changes
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

                            }
                            catch (Exception e)
                            {
                                Debug.WriteLine(e);
                            }



                            //Create userLoginDate object for new user
                            DateTime today = DateTime.Today;

                            UserLoginDate userLoginDate = new UserLoginDate
                            {
                                UserID = streakUserID,
                                UserLoginDate1 = today

                            };

                            //add new userLoginDate to db and save changes
                            try
                            {
                                db.UserLoginDates.Add(userLoginDate);

                                db.SaveChanges();

                            }
                            catch (Exception e)
                            {
                                Debug.WriteLine(e);
                            }
                            

                            //return success message
                            returnMessage.result = true;
                            returnMessage.errorMessage = "Success";

                            return returnMessage;


                        }
                        catch (Exception e)
                        {
                            Debug.WriteLine("Error: " + e.ToString());

                            returnMessage.result = false;
                            returnMessage.errorMessage = "Error";

                            return returnMessage;
                        }

                    }// if not, return error
                    else
                    {
                        Debug.WriteLine("Passwords Don't Match");

                        returnMessage.result = false;
                        returnMessage.errorMessage = "Passwords Don't Match";

                        return returnMessage;
                    }


                } // if the register object sent to th api from the android app is not valid, return error
                else
                {

                    Debug.WriteLine("ERROR: Invalid Information Entered");
                    returnMessage.result = false;
                    returnMessage.errorMessage = "Invalid Information Entered";
                    return returnMessage;

                }

            }
            catch(Exception e)
            {
                //catch any error message and return error
                returnMessage.result = false;
                returnMessage.errorMessage = e.Message;
                return returnMessage;
            }

        }





        //This POST method allows users to login to their account
        [Route("api/values/PostLogin")]
        [HttpPost]
        public ReturnMessageObject PostLogin(LoginUserObject loginUser)
        {
            //declare return object
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            try
            {
                //Declarations
                returnMessage.result = false;
                returnMessage.errorMessage = "Invalid Details Entered";
                Debug.WriteLine("OUR USER: " + loginUser.Email + " " + loginUser.Password);
                List<User> usersList = db.Users.ToList();
                Boolean userFound = false;
                Boolean passFound = false;
                String userPass = "";
                int userID = 0;

                //Search for user email using user ID
                foreach (User searchUser in usersList)
                {
                    if (searchUser.Email.Trim().Equals(loginUser.Email))
                    {
                        Debug.WriteLine("USER FOUND");
                        userFound = true;
                        userPass = searchUser.Password;
                        Debug.WriteLine("USER DETAILS: " + loginUser.Email + " " + userPass);
                        userID = searchUser.UserID;
                    }
                }


                //Hash and salt entered password and check if it matches password in db
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


                //if email and password match, return success
                if (userFound && passFound)
                {
                    Debug.WriteLine("SUCCESSSSSSSS!!!!!!!!!!!!!!");
                    returnMessage.result = true;
                    returnMessage.errorMessage = "Success";
                    return returnMessage;
                }
                else
                //if email and password do not match, return error
                {

                    Debug.WriteLine("INVALID Details Entered, Please Try again...");
                    returnMessage.result = false;
                    returnMessage.errorMessage = "Invalid Details Entered";
                    return returnMessage;

                }

            }
            catch (Exception e)
            {
                //catch any error message and return error
                returnMessage.result = false;
                returnMessage.errorMessage = e.Message;
                return returnMessage;
            }

            


        }



        //This POST method allows users to retrieve their goals
        [Route("api/values/PostRetrieveGoals")]
        [HttpPost]
        public ReturnAllGoalObject PostRetrieveGoals(UserGoalObject userGoal)
        {
            //Declarations

            ReturnAllGoalObject returnGoal = new ReturnAllGoalObject();
            DateTime today = DateTime.Today;

            try
            {
                //Declarations
                List<ReturnAnyTypeGoalObject> goalList = new List<ReturnAnyTypeGoalObject>();
                String userEmail = "";
                if(userGoal.Email != null)
                {
                    userEmail = userGoal.Email;
                }
                int userID = 0;

                //search for user and get userID
                foreach (User user in db.Users)
                {
                    if (user.Email.Equals(userEmail))
                    {
                        userID = user.UserID;
                    }
                }

                //search for and add goals for user
                foreach (UserGoal goal in db.UserGoals)
                {
                    if (goal.UserID == userID)
                    {
                        Boolean completed = false;

                        if (goal.Completed == 1)
                        {
                            completed = true;
                        }


                        Goal searchGoal = db.Goals.FirstOrDefault(g => g.GoalID == goal.GoalID);

                        ReturnAnyTypeGoalObject go = new ReturnAnyTypeGoalObject
                        {
                            GoalID = searchGoal.GoalID,
                            GoalName = searchGoal.GoalName,
                            GoalDescription = searchGoal.GoalDescription,
                            isNormalGoal = true,
                            finishDate = today.ToString()
                            
                        };


                        if (completed)
                        {
                            go.Completed = 1;
                        }
                        else
                        {
                            go.Completed = 0;
                        }

                        goalList.Add(go);
                    }
                }

                //search for and add custom goals for user
                foreach (CustomUserGoal goal in db.CustomUserGoals)
                {
                    if (goal.UserID == userID)
                    {
                        Boolean completed = false;

                        if (goal.Completed == 1)
                        {
                            completed = true;
                        }

                        CustomGoal searchGoal = db.CustomGoals.FirstOrDefault(go => go.GoalID == goal.GoalID);

                        String stringDate, stringTodaysDate;
                        int day = 0, month = 0, year = 0;
                        String sDay = "", sMonth = "";

                        day = searchGoal.FinishDate.Value.Day;
                        month = searchGoal.FinishDate.Value.Month;
                        year = searchGoal.FinishDate.Value.Year;

                        if (day < 10)
                        {
                            sDay = "0" + day;
                        }
                        else
                        {
                            sDay = day + "";
                        }


                        if (month < 10)
                        {
                            sMonth = "0" + month;
                        }
                        else
                        {
                            sMonth = month + "";
                        }

                        stringDate = year + "-" + sMonth + "-" + sDay;

                        day = today.Day;
                        month = today.Month;
                        year = today.Year;

                        if (day < 10)
                        {
                            sDay = "0" + day;
                        }
                        else
                        {
                            sDay = day + "";
                        }


                        if (month < 10)
                        {
                            sMonth = "0" + month;
                        }
                        else
                        {
                            sMonth = month + "";
                        }

                        stringTodaysDate = year + "-" + sMonth + "-" + sDay;

                        ReturnAnyTypeGoalObject g = new ReturnAnyTypeGoalObject
                        {
                            GoalID = searchGoal.GoalID,
                            GoalName = searchGoal.GoalName,
                            GoalDescription = searchGoal.GoalDescription,
                            isNormalGoal = false,
                            finishDate = stringDate,
                            currentDate = stringTodaysDate
                        };

                        if (completed)
                        {
                            g.Completed = 1;
                        }
                        else
                        {
                            g.Completed = 0;
                        }

                        goalList.Add(g);
                    }
                }

                //return object containing all user goals
                returnGoal.goalList = goalList;
                return returnGoal;

            }
            catch (Exception e)
            {
                //catch any error message and return error
                returnGoal.goalList = null;
                Debug.WriteLine(e.Message);
                return returnGoal;

            }

            
        }



        //This POST method allows users to add a normal goal to their list of goals
        [Route("api/values/PostAddNormalGoal")]
        [HttpPost]
        public ReturnMessageObject PostAddNormalGoal(UserGoalObject userGoal)
        {
            //declare return object
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            try
            {
                //Declarations
                bool valid;
                String userEmail = "";
                if (userGoal.Email != null)
                {
                    userEmail = userGoal.Email;
                }
                int userID = 0;
                UserGoal newUserGoal = new UserGoal();



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

                //check if user already has goal, if not procced, if they do, don't allow them to add it again.
                Boolean hasGoal = false;

                foreach(UserGoal userGoal1 in db.UserGoals)
                {
                    if(userGoal1.GoalID == userGoal.GoalId && userGoal1.UserID == userID)
                    {
                        hasGoal = true;
                    }
                }

                if(hasGoal)
                {
                    returnMessage.result = false;
                    returnMessage.errorMessage = "duplicate";

                    return returnMessage;
                }
                else
                {
                    //add new goal to db and save changes
                    try
                    {

                        db.UserGoals.Add(newUserGoal);
                        db.SaveChanges();
                        valid = true;
                    }
                    catch (Exception e)
                    {
                        Debug.WriteLine("Error: " + e.ToString());
                        returnMessage.errorMessage = "Error: " + e.ToString();
                        valid = false;
                    }

                    //return success
                    returnMessage.result = valid;
                    return returnMessage;
                }

            }
            catch (Exception e)
            {
                //catch any error message and return error
                returnMessage.result = false;
                returnMessage.errorMessage = e.Message;
                return returnMessage;
            }


        }




        //This POST method allows users to add a custom goal to their list of goals
        [Route("api/values/PostAddCustomGoal")]
        [HttpPost]
        public ReturnMessageObject PostAddCustomGoal(CustomGoalObject customGoal)
        {
            //declare return object
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            try
            {
                //Declarations
                bool valid;
                String userEmail = "";
                if (customGoal.Email != null)
                {
                    userEmail = customGoal.Email;
                }
                int userID = 0;
                CustomUserGoal newUserGoal = new CustomUserGoal();
                CustomGoal customGoal1 = new CustomGoal();

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

                //add and save custom goal to db
                try
                {
                    db.CustomGoals.Add(customGoal1);
                    db.SaveChanges();
                    valid = true;
                }
                catch (Exception e)
                {
                    Debug.WriteLine("Error: " + e.ToString());
                    returnMessage.errorMessage = "Error: " + e.ToString();
                    valid = false;
                }


                //if custom goal was added successfully, the custom goal must now be added to the custom user goals table
                if (valid == true)
                {
                    //search for custom goal id
                    List<CustomGoal> cgList = new List<CustomGoal>();
                    int customGoalID = 0;

                    foreach (CustomGoal cg in db.CustomGoals)
                    {
                        if(cg.GoalDescription.Equals(customGoal.goalDescription) && cg.GoalName.Equals(customGoal.goalName) && cg.FinishDate == customGoal.finishDate)
                        {
                            customGoalID = cg.GoalID;
                        }
                    }

                    newUserGoal.UserID = userID;
                    newUserGoal.Completed = 0;
                    newUserGoal.GoalID = customGoalID;

                    //save custom user goal to db
                    try
                    {
                        db.CustomUserGoals.Add(newUserGoal);
                        db.SaveChanges();
                        valid = true;
                    }
                    catch (Exception e)
                    {
                        //catch any error message and return error
                        Debug.WriteLine("Error: " + e.ToString());
                        returnMessage.errorMessage = "Error: " + e.ToString();
                        valid = false;
                    }


                }

                //return success
                returnMessage.result = valid;
                return returnMessage;

            }
            catch (Exception e)
            {
                //catch any error message and return error
                returnMessage.result = false;
                returnMessage.errorMessage = e.Message;
                return returnMessage;
            }

            
        }




        //This POST method allows users to mark off goals
        [Route("api/values/PostMarkOffGoal")]
        [HttpPost]
        public ReturnMessageObject PostMarkOffGoal(UserGoalObject userGoal)
        {
            //declare return object
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            try
            {
                //Declarations
                bool valid;
                int userSearchID = 0;
                int goalSearchID = userGoal.GoalId;
                String userEmail = "";
                if (userGoal.Email != null)
                {
                    userEmail = userGoal.Email;
                }

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

                //save changes to db
                try
                {
                    db.SaveChanges();
                    valid = true;
                }
                catch (Exception e)
                {
                    //catch any error message and return error
                    Debug.WriteLine("Error: " + e.ToString());
                    returnMessage.errorMessage = "Error: " + e.ToString();
                    valid = false;
                }

                //return success
                returnMessage.result = valid;
                return returnMessage;

            }
            catch (Exception e)
            {
                //catch any error message and return error
                returnMessage.result = false;
                returnMessage.errorMessage = e.Message;
                return returnMessage;
            }

        }


        //This POST method allows users to mark off custom goals
        [Route("api/values/PostMarkOffCustomGoal")]
        [HttpPost]
        public ReturnMessageObject PostMarkOffCustomGoal(UserGoalObject userGoal)
        {
            //declare return object
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            try
            {
                //Declarations
                bool valid;
                int userSearchID = 0;
                int goalSearchID = userGoal.GoalId;
                String userEmail = "";
                if (userGoal.Email != null)
                {
                    userEmail = userGoal.Email;
                }

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

                //save changes to db
                try
                {
                    db.SaveChanges();
                    valid = true;
                }
                catch (Exception e)
                {
                    //catch any error message and return error
                    Debug.WriteLine("Error: " + e.ToString());
                    returnMessage.errorMessage = "Error: " + e.ToString();
                    valid = false;
                }

                //return success
                returnMessage.result = valid;
                return returnMessage;

            }
            catch (Exception e)
            {
                //catch any error message and return error
                returnMessage.result = false;
                returnMessage.errorMessage = e.Message;
                return returnMessage;
            }

            
        }



        //This GET method returns the latest daily quote
        [Route("api/values/GetDailyQuote")]
        [HttpGet]
        public DailyQuote GetDailyQuote()
        {
            //declare return object

            DailyQuote dailyQuote = new DailyQuote();

            //get and return dailyquote from db
            try
            {
                foreach (DailyQuote quote in db.DailyQuotes)
                {
                    dailyQuote.QuoteDate = quote.QuoteDate;
                    dailyQuote.QuoteText = quote.QuoteText;
                    dailyQuote.TemplateID = quote.TemplateID;
                    dailyQuote.YoutubeLink = quote.YoutubeLink;
                }

                return dailyQuote;

            }
            catch (Exception e)
            {
                //catch any error message and return error
                Debug.WriteLine(e.Message);
                dailyQuote.QuoteText = null;
                return dailyQuote;
            }
           
        }


        //This POST method returns all user life skills
        [Route("api/values/PostRetrieveLifeSkills")]
        [HttpPost]
        public ReturnLifeSkillsObject PostRetrieveLifeSkills(LifeSkillObject lifeSkillObject)
        {
            //declare return object
            ReturnLifeSkillsObject returnlifeskills = new ReturnLifeSkillsObject();

            try
            {

                //Declarations
                List<LifeSkillObject> lifeskillsList = new List<LifeSkillObject>();
                String userEmail = "";
                if (lifeSkillObject.Email != null)
                {
                    userEmail = lifeSkillObject.Email;
                }
                int userID = 0;

                //search for user and get userID
                foreach (User user in db.Users)
                {
                    if (user.Email.Equals(userEmail))
                    {
                        userID = user.UserID;
                    }
                }

                //search and add life skills for user to return list
                foreach (UserLifeSkill userlifeskill in db.UserLifeSkills)
                {
                    if (userlifeskill.UserID == userID)
                    {
                        Boolean completed = false;

                       
                        if (userlifeskill.Completed == 1)
                        {
                           completed = true;
                        }
                        

                        LifeSkill searchLifeSkill = db.LifeSkills.FirstOrDefault(l => l.LifeSkillID == userlifeskill.LifeSKillID);

                        LifeSkillObject lo = new LifeSkillObject
                        {
                            LifeSkillID = searchLifeSkill.LifeSkillID,
                            LifeSkillName = searchLifeSkill.LifeSkillName
                        };

                        if (completed)
                        {
                            lo.Completed = 1;
                        }
                        else
                        {
                            lo.Completed = 0;
                        }

                        lifeskillsList.Add(lo);
                    }
                }

                //return success
                returnlifeskills.LifeSkillsList = lifeskillsList;
                return returnlifeskills;

            }
            catch (Exception e)
            {
                //catch any error message and return error
                Debug.WriteLine(e.Message);
                returnlifeskills.LifeSkillsList = null;
                return returnlifeskills;
            }

        }



        //This POST method allows users to mark off lifeskills
        [Route("api/values/PostMarkOffLifeSkill")]
        [HttpPost]
        public ReturnMessageObject PostMarkOffLifeSkill(LifeSkillObject lifeSkillObject)
        {
            //declare return object
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            try
            {
                //Declarations
                bool valid;
                int userSearchID = 0;
                int lifeskillSearchID = 0;

                try
                {
                    lifeskillSearchID = lifeSkillObject.LifeSkillID;
                }
                catch(Exception e)
                {
                    // in case of null exception
                    Debug.WriteLine(e.Message);
                }
                String userEmail = "";
                if (lifeSkillObject.Email != null)
                {
                    userEmail = lifeSkillObject.Email;
                }


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

                //save changes to db
                try
                {
                    db.SaveChanges();
                    valid = true;
                }
                catch (Exception e)
                {
                    //catch any error message and return error
                    Debug.WriteLine("Error: " + e.ToString());
                    returnMessage.errorMessage = "Error: " + e.ToString();
                    valid = false;
                }

                //return success
                returnMessage.result = valid;
                return returnMessage;
            }
            catch (Exception e)
            {
                //catch any error message and return error
                returnMessage.result = false;
                returnMessage.errorMessage = e.Message;
                return returnMessage;
            }


        }



        //This POST method updates the view number for dailyquote videos
        [Route("api/values/PostUpdateViews")]
        [HttpPost]
        public ReturnMessageObject PostUpdateViews()
        {
            //declare return object
            ReturnMessageObject returnMessageObject = new ReturnMessageObject();

            try
            {
                //get last dailyquote in table
                int count = 0;

                foreach (DailyQuote dailyQuote in db.DailyQuotes)
                {
                    count++;
                }

                int count2 = 0;

                //update views value for latest dailyquote
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

                    //save changes to db
                    db.SaveChanges();
                }
                catch (Exception e)
                {
                    //catch any error message and return error
                    Debug.WriteLine("Exception: " + e.ToString());
                    returnMessageObject.result = false;
                    returnMessageObject.errorMessage = e.ToString();
                }

                //return success
                return returnMessageObject;

            }
            catch (Exception e)
            {
                //catch any error message and return error
                returnMessageObject.result = false;
                returnMessageObject.errorMessage = e.Message;
                return returnMessageObject;
            }


        }


        //This POST method creates a new account for a google user
        [Route("api/values/PostGoogleSignIn")]
        [HttpPost]
        public ReturnMessageObject PostGoogleSignIn(GoogleSignInObject googleSignInObject)
        {
            //declare return object
            ReturnMessageObject returnMessageObject = new ReturnMessageObject();

            try
            {
                //check if user already exists
                Boolean exists = false;
                foreach (User user in db.Users)
                {
                    if (user.Email.Equals(googleSignInObject.Email.Trim()))
                    {
                        exists = true;
                    }
                }

                //if they do, proceed
                if (exists)
                {
                    returnMessageObject.result = true;
                    returnMessageObject.errorMessage = "Account exists already";
                }
                //if not, add new user to db.
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

                        //add new user to db
                        try
                        {
                            db.Users.Add(user);

                            db.SaveChanges();
                        }
                        catch(Exception e)
                        {
                            Debug.WriteLine(e.Message);
                        }
                        

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

                        //add new user streak item to db
                        try
                        {
                            db.Streaks.Add(streak);

                            db.SaveChanges();
                        }
                        catch (Exception e)
                        {
                            Debug.WriteLine(e.Message);
                        }


                        //add all lifeskills for user in db and save
                        try
                        {
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
                        }
                        catch (Exception e)
                        {
                            Debug.WriteLine(e.Message);
                        }

                        DateTime today = DateTime.Today;

                        UserLoginDate userLoginDate = new UserLoginDate
                        {
                            UserID = streakUserID,
                            UserLoginDate1 = today

                        };

                        //add user login date to db
                        try
                        {
                            db.UserLoginDates.Add(userLoginDate);
                            db.SaveChanges();

                        }
                        catch (Exception e)
                        {
                            Debug.WriteLine(e.Message);
                        }

                        
                        //return success
                        returnMessageObject.result = false;
                        returnMessageObject.errorMessage = "New Account created";


                    }
                    catch (Exception e)
                    {
                        //catch any error message and return error
                        Debug.WriteLine("Error: " + e.ToString());
                        returnMessageObject.result = true;
                        returnMessageObject.errorMessage = "Account exists already";
                    }

                }

                //return success
                return returnMessageObject;

            }
            catch (Exception e)
            {
                //catch any error message and return error
                returnMessageObject.result = false;
                returnMessageObject.errorMessage = e.Message;
                return returnMessageObject;
            }


        }


        //This POST method updates a user's streak
        [Route("api/values/PostUpdateStreak")]
        [HttpPost]
        public ReturnMessageObject PostUpdateStreak(LoginUserObject loginUserObject)
        {
            //declare return object
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            try
            {
                int userSearchID = 0;
                String userEmail = "";
                if (loginUserObject.Email != null)
                {
                    userEmail = loginUserObject.Email;
                }

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

                    //save changes to db
                    try
                    {
                        db.SaveChanges();
                    }
                    catch(Exception e)
                    {
                        Debug.WriteLine(e.Message);
                    }
                    
                    //update streak if it needs to be updated
                    if (updateStreak)
                    {
                        foreach (Streak streak in db.Streaks)
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

                    //save changes to db
                    try
                    {
                        db.SaveChanges();
                    }
                    catch (Exception e)
                    {
                        Debug.WriteLine(e.Message);
                    }

                    //return success
                    returnMessage.result = true;


                }

                catch (Exception e)
                {
                    //catch any error message and return error
                    Debug.WriteLine(e.ToString());
                    returnMessage.result = false;
                    returnMessage.errorMessage = e.ToString();
                }

                //return success
                return returnMessage;

            }
            catch (Exception e)
            {
                //catch any error message and return error
                returnMessage.result = false;
                returnMessage.errorMessage = e.Message;
                return returnMessage;
            }

        }


        //This POST method returns a user's current streak
        [Route("api/values/PostUserStreak")]
        [HttpPost]
        public Streak PostUserStreak(LoginUserObject loginUserObject)
        {
            //declare return object
            Streak returnStreak = new Streak(); 

            try
            {
                //Declarations
                int userSearchID = 0;
                String userEmail = "";
                if (loginUserObject.Email != null)
                {
                    userEmail = loginUserObject.Email;
                }

                //search for user and get userID
                foreach (User user in db.Users)
                {
                    if (user.Email.Equals(userEmail))
                    {
                        userSearchID = user.UserID;
                    }
                }

                //Get streak for user from db

                foreach (Streak streak in db.Streaks)
                {
                    if (userSearchID == streak.UserID)
                    {
                        returnStreak.StreakLength = streak.StreakLength;
                    }
                }

                //return success
                return returnStreak;

            }
            catch(Exception e)
            {
                //catch any error message and return error
                Debug.WriteLine(e.Message);
                returnStreak.StreakLength = 0;
                return returnStreak;
            }
            

        }



        //This POST method returns a users step count for the day
        [Route("api/values/PostUserSteps")]
        [HttpPost]
        public UserStepsObject PostUserSteps(UserStepsObject userStepsObject)
        {

            //declare return object
            UserStepsObject returnSteps = new UserStepsObject();

            try
            {
                //Declarations
                int userSearchID = 0;
                DateTime today = DateTime.Today;
                String userEmail = "";
                if (userStepsObject.Email != null)
                {
                    userEmail = userStepsObject.Email;
                }

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

                foreach (UserStep userSteps in db.UserSteps)
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


                //return success
                return returnSteps;

            }
            catch(Exception e)
            {
                //catch any error message and return error
                Debug.WriteLine(e.Message);
                returnSteps.numSteps = 0;
                return returnSteps;
            }

        }





        //This POST method updates a user's steps for today
        [Route("api/values/PostUpdateSteps")]
        [HttpPost]
        public ReturnMessageObject PostUpdateSteps(UserStepsObject userStepsObject)
        {
            //declare return object
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            try
            {
                //Declarations
                int userSearchID = 0;
                DateTime today = DateTime.Today;
                String userEmail = "";
                if (userStepsObject.Email != null)
                {
                    userEmail = userStepsObject.Email;
                }

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

                        try
                        {
                            //delete old steps
                            if (userSteps.UserID == userSearchID && userSteps.UserStepsDate != today)
                            {
                                db.UserSteps.Remove(userSteps);
                            }
                        }
                        catch(Exception e)
                        {
                            Debug.WriteLine(e.Message);
                        }
                        
                    }

                    //save changes to db
                    try
                    {
                        db.SaveChanges();
                    }
                    catch(Exception e)
                    {
                        Debug.WriteLine(e.Message);
                    }
                    

                    //create steps object for today for user is one doesn't already exist
                    if (!hasSteps)
                    {
                        UserStep userStep = new UserStep
                        {
                            UserID = userSearchID,
                            Steps = userStepsObject.numSteps,
                            UserStepsDate = today
                        };

                        //add steps object and save to db
                        try
                        {
                            db.UserSteps.Add(userStep);
                            db.SaveChanges();
                        }
                        catch(Exception e)
                        {
                            Debug.WriteLine(e.Message);
                        }
                       

                    }


                    //return success
                    returnMessage.result = true;



                }
                catch (Exception e)
                {
                    //catch any error message and return error
                    returnMessage.result = false;
                    returnMessage.errorMessage = e.ToString();
                }

                //return success
                return returnMessage;

            }
            catch (Exception e)
            {
                //catch any error message and return error
                returnMessage.result = false;
                returnMessage.errorMessage = e.Message;
                return returnMessage;
            }


        }


        //This POST method returns user gratitude items for today
        [Route("api/values/PostUserGratitude")]
        [HttpPost]
        public GratitudeObject PostUserGratitude(GratitudeObject gratitudeObject)
        {
            //declare return object
            GratitudeObject returnGratitude = new GratitudeObject();

            try
            {
                //Declarations
                int userSearchID = 0;
                DateTime today = DateTime.Today;
                String userEmail = "";
                if (gratitudeObject.Email != null)
                {
                    userEmail = gratitudeObject.Email;
                }

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

                //return success
                return returnGratitude;

            }
            catch(Exception e)
            {
                //catch any error message and return error
                Debug.WriteLine(e.Message);
                returnGratitude.Items = null;
                return returnGratitude;
            }

        }


        //This POST method updates user gratitude items for today
        [Route("api/values/PostUpdateGratitude")]
        [HttpPost]
        public ReturnMessageObject PostUpdateGratitude(GratitudeObject gratitudeObject)
        {
            //declare return object
            ReturnMessageObject returnMessage = new ReturnMessageObject();

            try
            {
                //Declarations
                int userSearchID = 0;
                DateTime today = DateTime.Today;
                String userEmail = "";
                if (gratitudeObject.Email != null)
                {
                    userEmail = gratitudeObject.Email;
                }

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
                        try
                        {
                            if (gratitude.UserID == userSearchID && gratitude.GratitudeDate != today)
                            {
                                db.Gratitudes.Remove(gratitude);
                            }
                        }
                        catch(Exception e)
                        {
                            Debug.WriteLine(e.Message);
                        }

                        
                    }

                    //save changes to db
                    try
                    {
                        db.SaveChanges();
                    }
                    catch (Exception e)
                    {
                        Debug.WriteLine(e.Message);
                    }
                   

                    //create new gratitude item and save to db if one doesn't exist for today
                    if (!hasGratitude)
                    {
                        Gratitude userGratitude = new Gratitude
                        {
                            UserID = userSearchID,
                            GratitudeItems = gratitudeObject.Items,
                            GratitudeDate = today
                        };

                        try
                        {
                            db.Gratitudes.Add(userGratitude);
                            db.SaveChanges();
                        }
                        catch (Exception e)
                        {
                            Debug.WriteLine(e.Message);
                        }


                    }

                    //return success
                    returnMessage.result = true;



                }
                catch (Exception e)
                {
                    //catch any error message and return error
                    returnMessage.result = false;
                    returnMessage.errorMessage = e.ToString();
                }

                //return success
                return returnMessage;

            }
            catch(Exception e)
            {
                //catch any error message and return error
                returnMessage.result = false;
                returnMessage.errorMessage = e.Message;
                return returnMessage;
            }

            


        }


        //This POST method returns all goals in the goals table that users can add
        [Route("api/values/GetAllGoals")]
        [HttpPost]
        public ReturnGoalObject GetAllGoals()
        {
            //declare return object
            ReturnGoalObject returnGoalObject = new ReturnGoalObject();

            try
            {

                //Declarations
                List<Goal> goalList = new List<Goal>();
                foreach (Goal goal in db.Goals)
                {
                    Goal goal1 = new Goal
                    {
                        GoalID = goal.GoalID,
                        GoalName = goal.GoalName,
                        GoalDescription = goal.GoalDescription
                    };
                    goalList.Add(goal1);
                }


                //return success
                returnGoalObject.goalList = goalList;
                return returnGoalObject;

            }
            //catch any error message and return error
            catch (Exception e)
            {
                Debug.WriteLine(e.Message);
                returnGoalObject.goalList = null;
                return returnGoalObject;
            }
            
        }



        //This POST method deletes a goal
        [Route("api/values/PostDeleteGoal")]
        [HttpPost]
        public ReturnMessageObject PostDeleteGoal(DeleteGoalObject deleteGoalObject)
        {
            //declare return object
            ReturnMessageObject returnobject = new ReturnMessageObject();
            int deleteUserGoalID = 0;

            try
            {
                //Declarations
                int userSearchID = 0;
                String userEmail = "";
                if (deleteGoalObject.email != null)
                {
                    userEmail = deleteGoalObject.email;
                }

                //search for user and get userID
                foreach (User user in db.Users)
                {
                    if (user.Email.Equals(userEmail))
                    {
                        userSearchID = user.UserID;
                    }
                }

                //check if goal is custom or normal and delete accordingly
                if (deleteGoalObject.isNormal)
                {
                    //Delete normal goal
                    try
                    {
                        foreach (UserGoal userGoal in db.UserGoals)
                        {
                            if (userGoal.GoalID == deleteGoalObject.goalID && userGoal.UserID == userSearchID)
                            {
                                db.UserGoals.Remove(userGoal);
                                
                                //Success in deleting normal goal
                                returnobject.errorMessage = "";
                                returnobject.result = true;
                            }
                        }
                        db.SaveChanges();

                    }
                    catch (Exception e)
                    {
                        returnobject.result = false;
                        returnobject.errorMessage = e.Message;
                    }
                }
                else
                {
                    //Delete custom goal
                    try
                    {
                        foreach (CustomUserGoal userGoal in db.CustomUserGoals)
                        {
                            if (userGoal.GoalID == deleteGoalObject.goalID && userGoal.UserID == userSearchID)
                            {

                                db.CustomUserGoals.Remove(userGoal);
                                
                                //Success in deleting custom goal
                                returnobject.errorMessage = "";
                                returnobject.result = true;
                            }
                        }
                        db.SaveChanges();
                    }
                    catch (Exception e)
                    {
                        returnobject.result = false;
                        returnobject.errorMessage = e.Message;
                    }
                }

                return returnobject;


            }
            catch (Exception e)
            {
                //catch any error message and return error
                Debug.WriteLine(e.Message);
                returnobject.errorMessage = e.Message;
                returnobject.result = false;
                return returnobject;
            }


        }




        //This POST method returns the top 8 users in the leaderboards
        [Route("api/values/PostGetTopEight")]
        [HttpPost]
        public ReturnTopEight PostGetTopEight()
        {
            //declare return object
            ReturnTopEight returnObject = new ReturnTopEight();
            returnObject.TopEight = "";
            try
            {

                //Declarations
                List<String> returnList = new List<String>();
                List<Leaderboard> leaderboards = db.Leaderboards.OrderByDescending(x => x.Score).ToList();

                for (int i = 0; i < leaderboards.Count; i++)
                {
                    int searchUserID = leaderboards[i].UserID;


                    User searchUser = db.Users.FirstOrDefault(x => x.UserID == searchUserID);
                    int userScore = leaderboards[i].Score;
                    String FullName = searchUser.FirstName + " " + searchUser.LastName;

                    String line = (i + 1) + "@" + FullName + "@" + userScore;
                    returnList.Add(line);
                }


                foreach(String item in returnList)
                {
                    if(returnObject.TopEight.Length <= 2)
                    {
                        returnObject.TopEight = item;
                    }
                    else
                    {
                        returnObject.TopEight = returnObject.TopEight + "#" + item;
                    }
                    
                }



                //return success
                return returnObject;

            }
            //catch any error message and return error
            catch (Exception e)
            {
                Debug.WriteLine(e.Message);
                returnObject.TopEight = "";
                return returnObject;
            }

        }




        //This POST method allows users to upload their score to the leaderboards
        [Route("api/values/PostUploadScore")]
        [HttpPost]
        public ReturnMessageObject PostUploadScore(LeaderboardObject leaderboardObject)
        {
            //declare return object
            ReturnMessageObject returnObject = new ReturnMessageObject();
            try
            {
                //Declarations
                int userSearchID = 0;
                String userEmail = "";
                int numscores = 0;
                Boolean userInList = false;
                int userinListScore = 0;
                int userinListID = 0;
                Boolean proceed = true;

                if (leaderboardObject.Email != null)
                {
                    userEmail = leaderboardObject.Email;
                }

                //search for user and get userID
                foreach (User user in db.Users)
                {
                    if (user.Email.Equals(userEmail))
                    {
                        userSearchID = user.UserID;
                    }
                }

                Boolean addScore = false;

                foreach (Leaderboard leaderboard in db.Leaderboards)
                {
                    if(leaderboard.UserID == userSearchID)
                    {
                        userInList = true;
                        userinListScore = leaderboard.Score;
                        userinListID = leaderboard.ID;
                    }
                    numscores++;

                    if(leaderboardObject.Score > leaderboard.Score)
                    {
                        addScore = true;
                    }
                }
                
                if(numscores < 8)
                {
                    addScore = true;
                }

                //delete preious score by this user if it is less than new score and is in the top 8
                if(userInList)
                {
                    if(userinListScore < leaderboardObject.Score)
                    {
                        try
                        {
                            //Leaderboard leaderboard = db.Leaderboards.FirstOrDefault(x => x.UserID == userinListID);
                            Leaderboard leaderboard = new Leaderboard();
                            foreach(Leaderboard l in db.Leaderboards)
                            {
                                if(l.UserID == userSearchID)
                                {
                                    leaderboard = l;
                                }
                            }

                            db.Leaderboards.Remove(leaderboard);
                            db.SaveChanges();

                            //add new score
                            Leaderboard newLeaderboardObject = new Leaderboard
                            {
                                Score = leaderboardObject.Score,
                                UserID = userSearchID
                            };

                            db.Leaderboards.Add(newLeaderboardObject);

                            db.SaveChanges();
                            proceed = false;
                        }
                        catch(Exception e)
                        {
                            Debug.WriteLine(e.Message);
                        }
                    }
                    else
                    {
                        proceed = false;
                    }
                }
                

                if(addScore && proceed)
                {
                    if (db.Leaderboards.ToList().Count >= 8)
                    {
                        //remove lowest score
                        List<Leaderboard> leaderboards = db.Leaderboards.OrderBy(x => x.Score).ToList();
                        Leaderboard removeObject = leaderboards[0];
                        db.Leaderboards.Remove(removeObject);
                    }

                    //add new score
                    Leaderboard newLeaderboardObject = new Leaderboard
                    {
                        Score = leaderboardObject.Score,
                        UserID = userSearchID
                    };

                    db.Leaderboards.Add(newLeaderboardObject);

                    db.SaveChanges();
                }



                //return success
                returnObject.result = true;
                return returnObject;

            }
            //catch any error message and return error
            catch (Exception e)
            {
                Debug.WriteLine(e.Message);
                returnObject.errorMessage = e.Message;
                returnObject.result = false;
                return returnObject;
            }

        }




    }
 
}
