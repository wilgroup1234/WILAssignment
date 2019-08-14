﻿using System;
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





        //REGISTER AND LOGIN METHODS!!!


        // GET: Users/Register
        public ActionResult Register()
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
        public ActionResult Register([Bind("FirstName,LastName,Age,Email,Password")] Users user, String confirmPass)
        {

            //Hash and Salt Password

            if (ModelState.IsValid)
            {
                if (user.Password.Equals(confirmPass))
                {
                    try
                    {
                        PasswordEncryption obj = PasswordEncryption.GetInstance;
                        user.Password = obj.GetHashedPassword(user.Password);

                        _context.Users.Add(user);
                        _context.SaveChanges();
                        StaticClass.errorMessage = "NO_ERROR";
                        StaticClass.currentUser = user.FirstName + " " + user.LastName;

                        return RedirectToAction("Index", "Home");

                    }
                    catch (DBConcurrencyException e)
                    {

                        Debug.WriteLine("Concurrency Error: " + e.ToString());
                        StaticClass.errorMessage = "Concurrency Error";
                        return RedirectToAction("Register", "Users");
                    }

                }
                else
                {
                    StaticClass.errorMessage = "Passwords Don't Match";
                    return RedirectToAction("Register", "Users");
                }


            }
            else
            {
                StaticClass.errorMessage = "ERROR: Invalid Information Entered";

                Debug.WriteLine("DEBUG!!!    " + StaticClass.errorMessage);

                return RedirectToAction("Register", "Users");

            }

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



        // GET: Users/AdminPortal
        public ActionResult AdminPortal()
        {

            return View();
        }

        // GET: Users/DailyQuote
        public ActionResult DailyQuote()
        {
            
            return View();
        }

    }
}
