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
        public virtual DbSet<CV> CVs { get; set; }
        public virtual DbSet<DailyQuote> DailyQuotes { get; set; }
        public virtual DbSet<Goal> Goals { get; set; }
        public virtual DbSet<LifeSkill> LifeSkills { get; set; }
        public virtual DbSet<Template> Templates { get; set; }
        public virtual DbSet<UserCV> UserCVs { get; set; }
        public virtual DbSet<UserGoal> UserGoals { get; set; }
        public virtual DbSet<UserLifeSkill> UserLifeSkills { get; set; }
        public virtual DbSet<User> Users { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<CustomGoal>()
                .Property(e => e.GoalName)
                .IsUnicode(false);

            modelBuilder.Entity<CustomGoal>()
                .Property(e => e.GoalDescription)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .Property(e => e.LifeSkillName)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .Property(e => e.IDNumber)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .Property(e => e.HighSchoolName)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .Property(e => e.PreviousWorkExperience)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .Property(e => e.WorkReferences)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .Property(e => e.Languages)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .Property(e => e.Achievements)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .Property(e => e.Nationality)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .Property(e => e.Interests)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .Property(e => e.Email)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .Property(e => e.PhoneNumber)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .Property(e => e.Address)
                .IsUnicode(false);

            modelBuilder.Entity<CV>()
                .HasMany(e => e.UserCVs)
                .WithRequired(e => e.CV)
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
                .HasMany(e => e.CustomUserGoals)
                .WithRequired(e => e.Goal)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<Goal>()
                .HasMany(e => e.UserGoals)
                .WithRequired(e => e.Goal)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<LifeSkill>()
                .Property(e => e.LifeSkillName)
                .IsUnicode(false);

            modelBuilder.Entity<LifeSkill>()
                .HasMany(e => e.UserLifeSkills)
                .WithRequired(e => e.LifeSkill)
                .WillCascadeOnDelete(false);

            modelBuilder.Entity<Template>()
                .Property(e => e.TemplateName)
                .IsUnicode(false);

            modelBuilder.Entity<Template>()
                .HasMany(e => e.DailyQuotes)
                .WithRequired(e => e.Template)
                .WillCascadeOnDelete(false);

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
                .HasMany(e => e.UserCVs)
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
        }
    }
}
