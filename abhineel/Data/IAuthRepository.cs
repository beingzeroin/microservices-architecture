using System.Threading.Tasks;
using abhineel.Models;

namespace abhineel.Data
{
    public interface IAuthRepository
    {
        Task<User> Register(User user, string password);
        Task<User> Login(string username, string password);
        Task<bool> UserExists(string username, string email);
    }
}