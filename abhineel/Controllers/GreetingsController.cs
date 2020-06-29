using System.Threading.Tasks;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace abhineel.Controllers
{
    [ApiController]
    [Route("/api/v1/greeting")]
    public class GreetingsController : ControllerBase
    {
        [AllowAnonymous]
        [HttpGet("public")]
        public IActionResult GetPublicGreeting()
        {
            var greeting = "Welcome to API.";
            return Ok(new
            {
                greeting = greeting
            });
        }

        [HttpGet("protected")]
        [AllowAnonymous]
        public async Task<IActionResult> GetProtectedPublicGreeting()
        {
            var token = await HttpContext.GetTokenAsync("access_token");
            if (token != null)
            {
                return GetLoggedInGreeting();
            }
            var greeting = "You need to be Authenticated first.";
            return Ok(new
            {
                greeting = greeting
            });
        }
        [Authorize]
        public IActionResult GetLoggedInGreeting()
        {
            var greeting = "Welcome to Authenticated API.";
            return Ok(new
            {
                greeting = greeting
            });
        }
    }
}