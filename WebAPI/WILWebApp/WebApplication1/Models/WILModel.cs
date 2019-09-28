namespace WebApplication1.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class WILModel : DbContext
    {
        public WILModel()
            : base("name=WILModel")
        {
        }

        public virtual DbSet<CustomGoal> CustomGoals { get; set; }
        public virtual DbSet<CustomUserGoal> CustomUserGoals { get; set; }
        public virtual DbSet<DailyQuote> DailyQuotes { get; set; }
        public virtual DbSet<Goal> Goals { get; set; }
        public virtual DbSet<Gratitude> Gratitudes { get; set; }
        public virtual DbSet<LifeSkill> LifeSkills { get; set; }
        public virtual DbSet<PasswordReset> PasswordResets { get; set; }
        public virtual DbSet<Streak> Streaks { get; set; }
        public virtual DbSet<Template> Templates { get; set; }
        public virtual DbSet<UserGoal> UserGoals { get; set; }
        public virtual DbSet<UserLifeSkill> UserLifeSkills { get; set; }
        public virtual DbSet<UserLoginDate> UserLoginDates { get; set; }
        public virtual DbSet<User> Users { get; set; }
        public virtual DbSet<UserStep> UserSteps { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<CustomGoal>()
                .Property(e => e.GoalName)
                .IsUnicode(false);

            modelBuilder.Entity<CustomGoal>()
                .Property(e => e.GoalDescription)
                .IsUnicode(false);

            modelBuilder.Entity<CustomGoal>()
                .HasMany(e => e.CustomUserGoals)
                .WithRequired(e => e.CustomGoal)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<DailyQuote>()
                .Property(e => e.QuoteText)
                .IsUnicode(false);

            modelBuilder.Entity<DailyQuote>()
                .Property(e => e.YoutubeLink)
                .IsUnicode(false);

            modelBuilder.Entity<Goal>()
                .Property(e => e.GoalName)
                .IsUnicode(false);

            modelBuilder.Entity<Goal>()
                .Property(e => e.GoalDescription)
                .IsUnicode(false);

            modelBuilder.Entity<Goal>()
                .HasMany(e => e.UserGoals)
                .WithRequired(e => e.Goal)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<Gratitude>()
                .Property(e => e.GratitudeItems)
                .IsUnicode(false);

            modelBuilder.Entity<LifeSkill>()
                .Property(e => e.LifeSkillName)
                .IsUnicode(false);

            modelBuilder.Entity<LifeSkill>()
                .HasMany(e => e.UserLifeSkills)
                .WithRequired(e => e.LifeSkill)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<PasswordReset>()
                .Property(e => e.passwordCode)
                .IsUnicode(false);

            modelBuilder.Entity<Template>()
                .Property(e => e.TemplateName)
                .IsUnicode(false);

            modelBuilder.Entity<User>()
                .Property(e => e.LastName)
                .IsUnicode(false);

            modelBuilder.Entity<User>()
                .Property(e => e.FirstName)
                .IsUnicode(false);

            modelBuilder.Entity<User>()
                .Property(e => e.Email)
                .IsUnicode(false);

            modelBuilder.Entity<User>()
                .Property(e => e.Password)
                .IsUnicode(false);

            modelBuilder.Entity<User>()
                .HasMany(e => e.CustomUserGoals)
                .WithRequired(e => e.User)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<User>()
                .HasMany(e => e.Gratitudes)
                .WithRequired(e => e.User)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<User>()
                .HasMany(e => e.Streaks)
                .WithRequired(e => e.User)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<User>()
                .HasMany(e => e.UserGoals)
                .WithRequired(e => e.User)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<User>()
                .HasMany(e => e.UserLifeSkills)
                .WithRequired(e => e.User)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<User>()
                .HasMany(e => e.UserLoginDates)
                .WithRequired(e => e.User)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<User>()
                .HasMany(e => e.UserSteps)
                .WithRequired(e => e.User)
                .WillCascadeOnDelete(false);
        }
    }
}
