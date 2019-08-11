using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;
using WILWebAppNetCore.Classes;

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
        public virtual DbSet<Cvs> Cvs { get; set; }
        public virtual DbSet<DailyQuote> DailyQuote { get; set; }
        public virtual DbSet<Goals> Goals { get; set; }
        public virtual DbSet<LifeSkills> LifeSkills { get; set; }
        public virtual DbSet<PasswordReset> PasswordReset { get; set; }
        public virtual DbSet<Template> Template { get; set; }
        public virtual DbSet<UserCvs> UserCvs { get; set; }
        public virtual DbSet<UserGoals> UserGoals { get; set; }
        public virtual DbSet<UserLifeSkills> UserLifeSkills { get; set; }
        public virtual DbSet<Users> Users { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {

                optionsBuilder.UseSqlServer(StaticClass.connection);
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<CustomGoals>(entity =>
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

            modelBuilder.Entity<Cvs>(entity =>
            {
                entity.HasKey(e => e.Cvid);

                entity.ToTable("CVs");

                entity.Property(e => e.Cvid).HasColumnName("CVID");

                entity.Property(e => e.Achievements)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.Address)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.DateOfBirth)
                    .HasColumnName("DateOfBIrth")
                    .HasColumnType("date");

                entity.Property(e => e.Email)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.HighSchoolName)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.Idnumber)
                    .IsRequired()
                    .HasColumnName("IDNumber")
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.Interests)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.Languages)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.LifeSkillName)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.Nationality)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.PhoneNumber)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.PreviousWorkExperience)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);

                entity.Property(e => e.WorkReferences)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);
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

            modelBuilder.Entity<Template>(entity =>
            {
                entity.Property(e => e.TemplateId).HasColumnName("TemplateID");

                entity.Property(e => e.TemplateName)
                    .IsRequired()
                    .HasMaxLength(255)
                    .IsUnicode(false);
            });

            modelBuilder.Entity<UserCvs>(entity =>
            {
                entity.HasKey(e => e.UserCvid);

                entity.ToTable("UserCVs");

                entity.Property(e => e.UserCvid).HasColumnName("UserCVID");

                entity.Property(e => e.Cvid).HasColumnName("CVID");

                entity.Property(e => e.UserId).HasColumnName("UserID");

                entity.HasOne(d => d.Cv)
                    .WithMany(p => p.UserCvs)
                    .HasForeignKey(d => d.Cvid)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_CVID");

                entity.HasOne(d => d.User)
                    .WithMany(p => p.UserCvs)
                    .HasForeignKey(d => d.UserId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_UserID4");
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

            modelBuilder.Entity<Users>(entity =>
            {
                entity.HasKey(e => e.UserId);

                entity.HasIndex(e => e.Email)
                    .HasName("UQ__Users__A9D10534B1BE2814")
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
        }
    }
}
