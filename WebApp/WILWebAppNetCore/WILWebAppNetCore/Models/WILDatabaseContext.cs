using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace WILWebAppNetCore.Models
{
    public partial class WILDatabaseContext : DbContext
    {
        public WILDatabaseContext()
        {
        }

        public WILDatabaseContext(DbContextOptions<WILDatabaseContext> options)
            : base(options)
        {
        }

        public virtual DbSet<CustomGoals> CustomGoals { get; set; }
        public virtual DbSet<CustomUserGoals> CustomUserGoals { get; set; }
        public virtual DbSet<DailyQuote> DailyQuote { get; set; }
        public virtual DbSet<Goals> Goals { get; set; }
        public virtual DbSet<Gratitude> Gratitude { get; set; }
        public virtual DbSet<Leaderboards> Leaderboards { get; set; }
        public virtual DbSet<LifeSkills> LifeSkills { get; set; }
        public virtual DbSet<PasswordReset> PasswordReset { get; set; }
        public virtual DbSet<SecurityQuestions> SecurityQuestions { get; set; }
        public virtual DbSet<Streaks> Streaks { get; set; }
        public virtual DbSet<Template> Template { get; set; }
        public virtual DbSet<UserGoals> UserGoals { get; set; }
        public virtual DbSet<UserLifeSkills> UserLifeSkills { get; set; }
        public virtual DbSet<UserLoginDates> UserLoginDates { get; set; }
        public virtual DbSet<Users> Users { get; set; }
        public virtual DbSet<UserSteps> UserSteps { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseSqlServer("Server=tcp:willgroup1234srv.database.windows.net,1433;Initial Catalog=willgroup1234db;Persist Security Info=False;User ID=willgroup1234admin;Password=Catherine44;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<CustomGoals>(entity =>
            {
                entity.HasKey(e => e.GoalId);

                entity.Property(e => e.GoalId).HasColumnName("GoalID");

                entity.Property(e => e.FinishDate).HasColumnType("date");

                entity.Property(e => e.GoalDescription)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.GoalName)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<CustomUserGoals>(entity =>
            {
                entity.HasKey(e => e.UserGoalId);

                entity.Property(e => e.UserGoalId).HasColumnName("UserGoalID");

                entity.Property(e => e.GoalId).HasColumnName("GoalID");

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.HasOne(d => d.Goal)
                    .WithMany(p => p.CustomUserGoals)
                    .HasForeignKey(d => d.GoalId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_GoalID2");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.CustomUserGoals)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UserID2");
            });

            modelBuilder.Entity<DailyQuote>(entity =>
            {
                entity.HasKey(e => e.QuoteId);

                entity.Property(e => e.QuoteId).HasColumnName("QuoteID");

                entity.Property(e => e.QuoteDate).HasColumnType("date");

                entity.Property(e => e.QuoteText)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.TemplateId).HasColumnName("TemplateID");

                entity.Property(e => e.YoutubeLink)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.HasOne(d => d.Template)
                    .WithMany(p => p.DailyQuote)
                    .HasForeignKey(d => d.TemplateId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_TemplateID");
            });

            modelBuilder.Entity<Goals>(entity =>
            {
                entity.HasKey(e => e.GoalId);

                entity.Property(e => e.GoalId).HasColumnName("GoalID");

                entity.Property(e => e.GoalDescription)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.GoalName)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<Gratitude>(entity =>
            {
                entity.Property(e => e.GratitudeId).HasColumnName("GratitudeID");

                entity.Property(e => e.GratitudeDate).HasColumnType("date");

                entity.Property(e => e.GratitudeItems)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.Gratitude)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UserID8");
            });

            modelBuilder.Entity<Leaderboards>(entity =>
            {
                entity.Property(e => e.Id).HasColumnName("ID");

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.Leaderboards)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UserID9");
            });

            modelBuilder.Entity<LifeSkills>(entity =>
            {
                entity.HasKey(e => e.LifeSkillId);

                entity.Property(e => e.LifeSkillId).HasColumnName("LifeSkillID");

                entity.Property(e => e.LifeSkillName)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<PasswordReset>(entity =>
            {
                entity.Property(e => e.Id).HasColumnName("id");

                entity.Property(e => e.PasswordCode)
                    .IsRequired()
                    .HasColumnName("passwordCode")
                    .HasMaxLength(255)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<SecurityQuestions>(entity =>
            {
                entity.Property(e => e.Id).HasColumnName("ID");

                entity.Property(e => e.Answer)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.Question)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.SecurityQuestions)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UserID10");
            });

            modelBuilder.Entity<Streaks>(entity =>
            {
                entity.HasKey(e => e.StreakId);

                entity.Property(e => e.StreakId).HasColumnName("StreakID");

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.Streaks)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UserID5");
            });

            modelBuilder.Entity<Template>(entity =>
            {
                entity.Property(e => e.TemplateId).HasColumnName("TemplateID");

                entity.Property(e => e.TemplateName)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<UserGoals>(entity =>
            {
                entity.HasKey(e => e.UserGoalId);

                entity.Property(e => e.UserGoalId).HasColumnName("UserGoalID");

                entity.Property(e => e.GoalId).HasColumnName("GoalID");

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.HasOne(d => d.Goal)
                    .WithMany(p => p.UserGoals)
                    .HasForeignKey(d => d.GoalId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_GoalID");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.UserGoals)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UserID");
            });

            modelBuilder.Entity<UserLifeSkills>(entity =>
            {
                entity.HasKey(e => e.UserLifeSkillId);

                entity.Property(e => e.UserLifeSkillId).HasColumnName("UserLifeSkillID");

                entity.Property(e => e.LifeSkillId).HasColumnName("LifeSKillID");

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.HasOne(d => d.LifeSkill)
                    .WithMany(p => p.UserLifeSkills)
                    .HasForeignKey(d => d.LifeSkillId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_LifeSkill3");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.UserLifeSkills)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UserID3");
            });

            modelBuilder.Entity<UserLoginDates>(entity =>
            {
                entity.Property(e => e.UserLoginDatesId).HasColumnName("UserLoginDatesID");

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.Property(e => e.UserLoginDate).HasColumnType("date");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.UserLoginDates)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UserID6");
            });

            modelBuilder.Entity<Users>(entity =>
            {
                entity.HasKey(e => e.UserId);

                entity.HasIndex(e => e.Email)
                    .HasName("UQ__Users__A9D10534E3C64732")
                    .IsUnique();

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.Property(e => e.Email)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.FirstName)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.LastName)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.Password)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<UserSteps>(entity =>
            {
                entity.HasIndex(e => e.Steps)
                    .HasName("UQ__UserStep__541AF9EB0D72C1D0")
                    .IsUnique();

                entity.Property(e => e.UserStepsId).HasColumnName("UserStepsID");

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.Property(e => e.UserStepsDate).HasColumnType("date");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.UserSteps)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UserID7");
            });
        }
    }
}
