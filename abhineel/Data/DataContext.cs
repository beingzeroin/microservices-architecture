using abhineel.Models;
using Microsoft.EntityFrameworkCore;

namespace abhineel.Data
{
    public class DataContext : DbContext
    {
        public DataContext(DbContextOptions<DataContext> options) : base(options)
        {
        }
        public DbSet<User> Users { get; set; }
    }
}