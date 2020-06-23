using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Infrastructure;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Newtonsoft.Json.Serialization;
using MySql.Data.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using abhineel.Data;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.Text;

namespace abhineel
{
    public class Startup
    {
        public IConfiguration Configuration { get; }

        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public void ConfigureServices(IServiceCollection services)
        {
            // var server = Configuration["DBServer"] ?? "localhost";
            services.AddDbContextPool<DataContext>(options =>
            {
                options.UseMySQL(Configuration.GetConnectionString("DefaultConnection"));
            });

            services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
                .AddJwtBearer(options =>
                {
                    options.TokenValidationParameters = new TokenValidationParameters
                    {
                        ValidateIssuerSigningKey = true,
                        IssuerSigningKey = new SymmetricSecurityKey(
                                Encoding.UTF8.GetBytes(
                                    Configuration.GetSection("AppSettings:Token").Value
                                )
                            ),
                        ValidateIssuer = false,
                        ValidateAudience = false
                    };
                });

            services.AddScoped<IAuthRepository, AuthRepository>();
            services.AddCors(setupAction =>
            {
                setupAction.AddPolicy("AllowAll", p =>
                     p.AllowAnyOrigin()
                     .AllowAnyHeader()
                     .AllowAnyMethod());
            });
            // Configuring the Controller
            services.AddControllers(setupAction =>
            {
                setupAction.ReturnHttpNotAcceptable = true;
            }).AddNewtonsoftJson(setupAction =>
            {
                setupAction.SerializerSettings.ContractResolver = new CamelCasePropertyNamesContractResolver();
                setupAction.SerializerSettings.ReferenceLoopHandling = Newtonsoft.Json.ReferenceLoopHandling.Ignore;
            }).AddXmlDataContractSerializerFormatters()
            .ConfigureApiBehaviorOptions(setupAction =>
            {
                setupAction.InvalidModelStateResponseFactory = context =>
                {
                    var problemDetailsFactory = context.HttpContext.RequestServices.GetRequiredService<ProblemDetailsFactory>();

                    var problemDetails = problemDetailsFactory.CreateValidationProblemDetails(
                        context.HttpContext,
                        context.ModelState
                    );
                    // add additional details 
                    problemDetails.Detail = "See the error field for details";
                    problemDetails.Instance = context.HttpContext.Request.Path;

                    var actionExecutingContext = context as Microsoft.AspNetCore.Mvc.Filters.ActionExecutingContext;

                    if ((context.ModelState.ErrorCount > 0) &&
                        (context is ControllerContext
                        || actionExecutingContext?.ActionArguments.Count == context.ActionDescriptor.Parameters.Count))
                    {
                        problemDetails.Type = "http://abhineel.com/modelvalidationproblem";
                        problemDetails.Status = StatusCodes.Status422UnprocessableEntity;
                        problemDetails.Title = "One or more validation error occurred.";
                        return new UnprocessableEntityObjectResult(problemDetails)
                        {
                            ContentTypes = { "application/problem+json" }
                        };
                    }
                    // if input parameter is missing
                    problemDetails.Status = StatusCodes.Status400BadRequest;
                    problemDetails.Title = "One or more input errors occurred.";
                    return new BadRequestObjectResult(problemDetails)
                    {
                        ContentTypes = { "application/problem+json" }
                    };
                };
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseCors("AllowAll");

            app.UseRouting();

            app.UseAuthentication();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
