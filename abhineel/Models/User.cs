using System.ComponentModel.DataAnnotations;

namespace abhineel.Models
{
    public class User
    {
        public int Id { get; set; }
        [Required]
        public string Username { get; set; }
        public byte[] PasswordHash { get; set; }
        public byte[] PasswordSalt { get; set; }
        [Required]
        [EmailAddress]
        public string Email { get; set; }
    }
}