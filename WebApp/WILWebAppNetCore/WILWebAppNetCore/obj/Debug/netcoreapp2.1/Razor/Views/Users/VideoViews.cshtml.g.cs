#pragma checksum "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "48e65b31513286246e97c01c6ac3d9f052a9284b"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Users_VideoViews), @"mvc.1.0.view", @"/Views/Users/VideoViews.cshtml")]
[assembly:global::Microsoft.AspNetCore.Mvc.Razor.Compilation.RazorViewAttribute(@"/Views/Users/VideoViews.cshtml", typeof(AspNetCore.Views_Users_VideoViews))]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#line 1 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\_ViewImports.cshtml"
using WILWebAppNetCore;

#line default
#line hidden
#line 2 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\_ViewImports.cshtml"
using WILWebAppNetCore.Models;

#line default
#line hidden
#line 2 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml"
using WILWebAppNetCore.Classes;

#line default
#line hidden
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"48e65b31513286246e97c01c6ac3d9f052a9284b", @"/Views/Users/VideoViews.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"9a6a4373b40b81283e18bb4404d66f416a3bd4e0", @"/Views/_ViewImports.cshtml")]
    public class Views_Users_VideoViews : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<WILWebAppNetCore.Models.DailyQuote>
    {
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_0 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("rel", new global::Microsoft.AspNetCore.Html.HtmlString("stylesheet"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_1 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("href", new global::Microsoft.AspNetCore.Html.HtmlString("~/css/Style1.css"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        #line hidden
        #pragma warning disable 0169
        private string __tagHelperStringValueBuffer;
        #pragma warning restore 0169
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperExecutionContext __tagHelperExecutionContext;
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner __tagHelperRunner = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner();
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __backed__tagHelperScopeManager = null;
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __tagHelperScopeManager
        {
            get
            {
                if (__backed__tagHelperScopeManager == null)
                {
                    __backed__tagHelperScopeManager = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager(StartTagHelperWritingScope, EndTagHelperWritingScope);
                }
                return __backed__tagHelperScopeManager;
            }
        }
        private global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.HeadTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_HeadTagHelper;
        private global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper;
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            BeginContext(76, 2, true);
            WriteLiteral("\r\n");
            EndContext();
#line 4 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml"
  
    ViewData["Title"] = "Video Views";

#line default
#line hidden
            BeginContext(125, 6, true);
            WriteLiteral("\r\n    ");
            EndContext();
            BeginContext(131, 78, false);
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("head", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "fbcce9cb85fd4f34a625aca16dbfb7dd", async() => {
                BeginContext(137, 10, true);
                WriteLiteral("\r\n        ");
                EndContext();
                BeginContext(147, 49, false);
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("link", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "5859cca5e1c041c0985ac150849ee590", async() => {
                }
                );
                __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
                __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_1);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
                EndContext();
                BeginContext(196, 6, true);
                WriteLiteral("\r\n    ");
                EndContext();
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_HeadTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.HeadTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_HeadTagHelper);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            EndContext();
            BeginContext(209, 148, true);
            WriteLiteral("\r\n\r\n<h2 align=\"center\">Video Views</h2>\r\n\r\n\r\n<br>\r\n<br>\r\n\r\n<h3 align=\"center\"> These are the Views for the Dailyquote Videos:</h3>\r\n\r\n<br>\r\n<br>\r\n\r\n");
            EndContext();
            BeginContext(361, 104, true);
            WriteLiteral("    <table class=\"table\">\r\n        <thead>\r\n            <tr>\r\n                <th>\r\n                    ");
            EndContext();
            BeginContext(466, 45, false);
#line 28 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml"
               Write(Html.DisplayNameFor(model => model.QuoteDate));

#line default
#line hidden
            EndContext();
            BeginContext(511, 67, true);
            WriteLiteral("\r\n                </th>\r\n                <th>\r\n                    ");
            EndContext();
            BeginContext(579, 45, false);
#line 31 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml"
               Write(Html.DisplayNameFor(model => model.QuoteText));

#line default
#line hidden
            EndContext();
            BeginContext(624, 67, true);
            WriteLiteral("\r\n                </th>\r\n                <th>\r\n                    ");
            EndContext();
            BeginContext(692, 47, false);
#line 34 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml"
               Write(Html.DisplayNameFor(model => model.YoutubeLink));

#line default
#line hidden
            EndContext();
            BeginContext(739, 67, true);
            WriteLiteral("\r\n                </th>\r\n                <th>\r\n                    ");
            EndContext();
            BeginContext(807, 41, false);
#line 37 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml"
               Write(Html.DisplayNameFor(model => model.Views));

#line default
#line hidden
            EndContext();
            BeginContext(848, 106, true);
            WriteLiteral("\r\n                </th>\r\n                <th></th>\r\n            </tr>\r\n        </thead>\r\n        <tbody>\r\n");
            EndContext();
#line 43 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml"
             foreach (DailyQuote quote in ViewBag.ViewsList)
            {

#line default
#line hidden
            BeginContext(1031, 72, true);
            WriteLiteral("                <tr>\r\n                    <td>\r\n                        ");
            EndContext();
            BeginContext(1104, 45, false);
#line 47 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml"
                   Write(Html.DisplayFor(modelItem => quote.QuoteDate));

#line default
#line hidden
            EndContext();
            BeginContext(1149, 79, true);
            WriteLiteral("\r\n                    </td>\r\n                    <td>\r\n                        ");
            EndContext();
            BeginContext(1229, 45, false);
#line 50 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml"
                   Write(Html.DisplayFor(modelItem => quote.QuoteText));

#line default
#line hidden
            EndContext();
            BeginContext(1274, 79, true);
            WriteLiteral("\r\n                    </td>\r\n                    <td>\r\n                        ");
            EndContext();
            BeginContext(1354, 47, false);
#line 53 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml"
                   Write(Html.DisplayFor(modelItem => quote.YoutubeLink));

#line default
#line hidden
            EndContext();
            BeginContext(1401, 79, true);
            WriteLiteral("\r\n                    </td>\r\n                    <td>\r\n                        ");
            EndContext();
            BeginContext(1481, 41, false);
#line 56 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml"
                   Write(Html.DisplayFor(modelItem => quote.Views));

#line default
#line hidden
            EndContext();
            BeginContext(1522, 58, true);
            WriteLiteral("\r\n                    </td>\r\n\r\n\r\n\r\n                </tr>\r\n");
            EndContext();
#line 62 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\VideoViews.cshtml"
            }

#line default
#line hidden
            BeginContext(1595, 32, true);
            WriteLiteral("        </tbody>\r\n    </table>\r\n");
            EndContext();
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<WILWebAppNetCore.Models.DailyQuote> Html { get; private set; }
    }
}
#pragma warning restore 1591
