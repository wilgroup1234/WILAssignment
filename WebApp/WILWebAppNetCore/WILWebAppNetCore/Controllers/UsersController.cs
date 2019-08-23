using System;
using System.Collections.Generic;
using System.Data;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WILWebAppNetCore.Classes;
using WILWebAppNetCore.Models;

namespace WILWebAppNetCore.Controllers
{
    public class UsersController : Controller
    {
        private readonly WILDatabaseContext _context;

        public UsersController(WILDatabaseContext context)
        {
            _context = context;
        }

        // GET: Users
        public async Task<IActionResult> Index()
        {
            return View(await _context.Users.ToListAsync());
        }

        // GET: Users/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var users = await _context.Users
                .FirstOrDefaultAsync(m => m.UserId == id);
            if (users == null)
            {
                return NotFound();
            }

            return View(users);
        }

        // GET: Users/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Users/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("UserID,LastName,FirstName,Age,Email,Password")] Users users)
        {
            if (ModelState.IsValid)
            {
                _context.Add(users);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(users);
        }

        // GET: Users/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var users = await _context.Users.FindAsync(id);
            if (users == null)
            {
                return NotFound();
            }
            return View(users);
        }

        // POST: Users/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("UserID,LastName,FirstName,Age,Email,Password")] Users users)
        {
            if (id != users.UserId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(users);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!UsersExists(users.UserId))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            return View(users);
        }

        // GET: Users/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var users = await _context.Users
                .FirstOrDefaultAsync(m => m.UserId == id);
            if (users == null)
            {
                return NotFound();
            }

            return View(users);
        }

        // POST: Users/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var users = await _context.Users.FindAsync(id);
            _context.Users.Remove(users);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool UsersExists(int id)
        {
            return _context.Users.Any(e => e.UserId == id);
        }





        // GET: Users/Login
        public ActionResult Login()
        {
            if (!StaticClass.errorMessage.Equals("NO_ERROR"))
            {
                ViewBag.Message = StaticClass.errorMessage;
            }
            

            return View();
        }




        // POST: Users/Login
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Login([Bind("PlayerID,Username,Email,Password")] Users user)
        {

            Debug.WriteLine("OUR USER: " + user.Email + " " + user.Password);

            List<Users> usersList = _context.Users.ToList();

            Boolean userFound = false;
            Boolean passFound = false;
            String userPass = "";
            String username = "";


            foreach (Users searchUser in usersList)
            {
                if (searchUser.Email.Equals(user.Email))
                {
                    Debug.WriteLine("USER FOUND");
                    userFound = true;
                    userPass = searchUser.Password;
                    username = searchUser.FirstName + " " + searchUser.LastName;
                }
            }


            //Find password

            if (userFound)
            {
                PasswordEncryption obj = PasswordEncryption.GetInstance;
                List<String> possiblePasswords = new List<String>();
                possiblePasswords = obj.GetPossiblePasswords(user.Password);

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
                StaticClass.errorMessage = "SUCCESSSSSSSS!!!!!!!!!!!!!!";

                Debug.WriteLine("DEBUG!!!    " + StaticClass.errorMessage);
                StaticClass.errorMessage = "NO_ERROR";
                StaticClass.currentUser = username;
                return RedirectToAction("Index", "Home");

            }
            else
            {
                StaticClass.errorMessage = "INVALID Details Entered, Please Try again...";

                Debug.WriteLine("DEBUG!!!    " + StaticClass.errorMessage);

                return RedirectToAction("Login", "Users");

            }

            

        }


        // GET: Users/Logout
        public ActionResult Logout()
        {
            StaticClass.currentUser = "NO_USER";
            return RedirectToAction("Index", "Home");
        }



        // GET: Users/ResetPassword
        public ActionResult ResetPassword()
        {
            if (!StaticClass.errorMessage.Equals("NO_ERROR"))
            {
                ViewBag.Message = StaticClass.errorMessage;
            }

            return View();
        }

        
        // POST: Users/Register
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult ResetPassword(String resetCode, String password, String confirmPass)
        {
            List<Users> userList = _context.Users.ToList();
            List<PasswordReset> passwordResets = _context.PasswordReset.ToList();
            PasswordReset passwordReset = new PasswordReset();
            String hashedpasswordCode = "";
            PasswordEncryption obj = PasswordEncryption.GetInstance;

            foreach (PasswordReset item in passwordResets)
            {
                passwordReset = item;
            }
            hashedpasswordCode = passwordReset.PasswordCode;

            Users user = new Users();
            user = _context.Users.FirstOrDefault(u => u.Email == "info@goalpro.co.za");
            Boolean valid = false;
            String error;

            if (password.Equals(confirmPass))
            {
                List<String> possiblePasswords = obj.GetPossiblePasswords(resetCode);


                foreach(String p in possiblePasswords)
                {
                    if (p.Equals(hashedpasswordCode))
                    {
                        valid = true;
                    }
                }

                if (valid)
                {
                    error = "NO_ERROR";
                }
                else
                {
                    error = "Invalid reset code entered :(";
                }
            }
            else
            {
                error = "Passwords do not match :(";
            }

            if (valid)
            {
                //Reset password for admin
                String newuserPassword = obj.GetHashedPassword(password);
                user.Password = newuserPassword;

                //set in db and save

                try
                {
                    foreach (Users u in _context.Users)
                    {
                        if (u.Email.Equals("info@goalpro.co.za"))
                        {
                            u.Password = newuserPassword;
                        }
                    }

                    _context.SaveChanges();
                }
                catch(DBConcurrencyException e)
                {
                    error = "Unable to update password :( " + e.ToString();
                    valid = false;
                }

               


            }

            if (valid == false)
            {
                StaticClass.errorMessage = error;

                Debug.WriteLine("ERROR: " + error);
                return RedirectToAction("ResetPassword", "Users");
            }
            else
            {
                StaticClass.errorMessage = "NO_ERROR";

                Debug.WriteLine("PASSWORD UPDATED SUCCESSFULLY!!: " + error);
                return RedirectToAction("Index", "Home");
            }
           

        }



        // GET: Users/DailyQuote
        public ActionResult DailyQuote()
        {
            if (!StaticClass.errorMessage.Equals("NO_ERROR"))
            {
                ViewBag.Message = StaticClass.errorMessage;
            }

            return View();
        }


        // POST: Users/DailyQuote
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult DailyQuote(int imageNumber, String quote, String link)
        {
            DailyQuote obj = new DailyQuote();
            Boolean valid = false;
            String error = "";
            String oldLink = "";

            try
            {
                foreach (DailyQuote dailyQuote in _context.DailyQuote)
                {
                    oldLink = dailyQuote.YoutubeLink;
                }

            }
            catch(Exception e)
            {
                Debug.WriteLine("get old link error: " + e.ToString());
            }

            

            if (imageNumber == null || quote == null)
            {
                error = "Please choose an image number and a quote";
                StaticClass.errorMessage = error;
                Debug.WriteLine("Daily Quote update Failed :( " + error);
                return RedirectToAction("DailyQuote", "Users");

            }

            
            try
            {
                obj.YoutubeLink = oldLink;
                DateTime today = DateTime.Today;
                obj.QuoteDate = today;
                obj.QuoteText = quote;
                obj.TemplateId = imageNumber;
                obj.Views = 0;

                if (link != null)
                {
                    obj.YoutubeLink = link;

                }

                _context.DailyQuote.Add(obj);
                _context.SaveChanges();

                valid = true;
                StaticClass.errorMessage = "NO_ERROR";
                Debug.WriteLine("Daily Quote updated");
                return RedirectToAction("Index", "Home");
                

            }
            catch(Exception e)
            {
                valid = false;
                error = e.ToString();
                StaticClass.errorMessage = error;
                Debug.WriteLine("Daily Quote update Failed :( " + error);
                return RedirectToAction("DailyQuote", "Users");

            }

            

        }


        // GET: Users/Goal
        public ActionResult Goals()
        {
            if (!StaticClass.errorMessage.Equals("NO_ERROR"))
            {
                ViewBag.Message = StaticClass.errorMessage;
            }

            List<Goals> goalList = new List<Goals>();
            goalList = _context.Goals.ToList();

            ViewBag.GoalList = goalList;

            return View();
        }


        // POST: Users/Goal
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Goals(String goalName, String goalDescription)
        {
            Boolean valid = false;
            String error = "";
            Goals goal = new Goals();
            

            if (goalName == null || goalDescription == null)
            {
                error = "Please choose an goal name and a goal description";
                StaticClass.errorMessage = error;
                Debug.WriteLine("Goal addition Failed :( " + error);
                return RedirectToAction("Goals", "Users");
            }

            try
            {
                goal.GoalName = goalName;
                goal.GoalDescription = goalDescription;


                _context.Goals.Add(goal);
                _context.SaveChanges();

                valid = true;
                StaticClass.errorMessage = "NO_ERROR";
                Debug.WriteLine("Goal added");
                return RedirectToAction("Index", "Home");


            }
            catch (Exception e)
            {
                valid = false;
                error = e.ToString();
                StaticClass.errorMessage = error;
                Debug.WriteLine("goal addition Failed :( " + error);
                return RedirectToAction("Goals", "Users");

            }



        }



        // GET: Users/SkillsList
        public ActionResult SkillsList()
        {
            if (!StaticClass.errorMessage.Equals("NO_ERROR"))
            {
                ViewBag.Message = StaticClass.errorMessage;
            }

            List<LifeSkills> lifeSkillsList = new List<LifeSkills>();
            lifeSkillsList = _context.LifeSkills.ToList();

            ViewBag.LifeSkillsList = lifeSkillsList;

            return View();
        }


        // POST: Users/SkillsList
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult SkillsList(String lifeSkillName)
        {
            Boolean valid = false;
            String error = "";
            LifeSkills lifeSkills = new LifeSkills();


            if (lifeSkillName == null)
            {
                error = "Please enter a Life Skill name";
                StaticClass.errorMessage = error;
                Debug.WriteLine("Life Skill addition Failed :( " + error);
                return RedirectToAction("SkillsList", "Users");
            }

            try
            {
                lifeSkills.LifeSkillName = lifeSkillName;


                _context.LifeSkills.Add(lifeSkills);
                _context.SaveChanges();

                int sLifeSkillID = 0;

                foreach(LifeSkills lifeSkills1 in _context.LifeSkills)
                {
                    sLifeSkillID = lifeSkills1.LifeSkillId;
                }

                

                foreach(Users user in _context.Users)
                {
                    if (user.UserId !=1 )
                    {
                        UserLifeSkills userLifeSkills = new UserLifeSkills
                        {
                            LifeSkillId = sLifeSkillID,
                            UserId = user.UserId,
                            Completed = 0
                        };

                        _context.UserLifeSkills.Add(userLifeSkills);
                        _context.SaveChanges();


                    };
                }
                  

                valid = true;
                StaticClass.errorMessage = "NO_ERROR";
                Debug.WriteLine("Life Skill added");
                return RedirectToAction("Index", "Home");


            }
            catch (Exception e)
            {
                valid = false;
                error = e.ToString();
                StaticClass.errorMessage = error;
                Debug.WriteLine("life Skill addition Failed :( " + error);
                return RedirectToAction("SkillsList", "Users");

            }



        }


        // GET: Users/Streaks
        public ActionResult Streaks()
        {
            List<Streaks> streaksList = new List<Streaks>();
            streaksList = _context.Streaks.ToList();

            List<UserStreak> streaks = new List<UserStreak>();


            foreach (Streaks streaks1 in streaksList)
            {
                if (streaks1.UserId != 1)
                {
                    UserStreak userStreak = new UserStreak
                    {
                        Email = streaks1.User.Email,
                        Streak = streaks1.StreakLength

                    };
                }

            }

            ViewBag.StreaksList = streaks;

            return View();
        }



    }
}
