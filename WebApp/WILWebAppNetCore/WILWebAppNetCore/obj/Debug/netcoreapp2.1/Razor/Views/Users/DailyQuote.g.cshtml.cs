#pragma checksum "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "913fe92220497a697aad21eb0792f09961df8e8b"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Users_DailyQuote), @"mvc.1.0.view", @"/Views/Users/DailyQuote.cshtml")]
[assembly:global::Microsoft.AspNetCore.Mvc.Razor.Compilation.RazorViewAttribute(@"/Views/Users/DailyQuote.cshtml", typeof(AspNetCore.Views_Users_DailyQuote))]
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
#line 4 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
using WILWebAppNetCore.Classes;

#line default
#line hidden
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"913fe92220497a697aad21eb0792f09961df8e8b", @"/Views/Users/DailyQuote.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"9a6a4373b40b81283e18bb4404d66f416a3bd4e0", @"/Views/_ViewImports.cshtml")]
    public class Views_Users_DailyQuote : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<dynamic>
    {
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_0 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("asp-action", "DailyQuote", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
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
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            BeginContext(0, 2, true);
            WriteLiteral("\r\n");
            EndContext();
#line 2 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
  
    ViewData["Title"] = "DailyQuote";
    

#line default
#line hidden
            BeginContext(85, 42, true);
            WriteLiteral("\r\n\r\n<h2 align=\"center\">DailyQuote</h2>\r\n\r\n");
            EndContext();
#line 10 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
  
    if (!StaticClass.errorMessage.Equals("NO_ERROR"))
    {

#line default
#line hidden
            BeginContext(193, 32, true);
            WriteLiteral("        <h2 class=\"text-danger\">");
            EndContext();
            BeginContext(226, 15, false);
#line 13 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                           Write(ViewBag.Message);

#line default
#line hidden
            EndContext();
            BeginContext(241, 7, true);
            WriteLiteral("</h2>\r\n");
            EndContext();
#line 14 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
    }

#line default
#line hidden
            BeginContext(258, 22, true);
            WriteLiteral("\r\n<br>\r\n<br>\r\n<br>\r\n\r\n");
            EndContext();
#line 21 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
  
    List<String> numbers = new List<string>();

    for (int i = 0; i < 30; i++)
    {
        numbers.Add((i + 1) + "");
    }

#line default
#line hidden
            BeginContext(421, 120, true);
            WriteLiteral("\r\n<h3 align=\"center\"> Enter information for Daily Quote:</h3>\r\n\r\n<div class=\"row\">\r\n    <div class=\"col-md-4\">\r\n        ");
            EndContext();
            BeginContext(541, 841, false);
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("form", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "fa10694d236a47209746d8b47c1eb717", async() => {
                BeginContext(571, 104, true);
                WriteLiteral("\r\n\r\n            <div class=\"form-group\">\r\n                <label>Image Number:</label>\r\n                ");
                EndContext();
                BeginContext(676, 175, false);
#line 38 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
           Write(Html.DropDownList("ImageNumber", numbers.Select(m => new SelectListItem { Value = m.ToString(), Text = m.ToString() }), new { @class = "form-control", @name = "imageNumber" }));

#line default
#line hidden
                EndContext();
                BeginContext(851, 524, true);
                WriteLiteral(@"
            </div>
            <div class=""form-group"">
                <label>Quote:</label>
                <input type=""text"" name=""quote"" class="" form-control"" />
            </div>
            <div class=""form-group"">
                <label>Youtube Link:</label>
                <input type=""text"" name=""link"" class="" form-control"" />
            </div>
            <div class=""form-group"">
                <input type=""submit"" value=""Update Daily Quote"" class=""btnsubmit"" />
            </div>

        ");
                EndContext();
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Action = (string)__tagHelperAttribute_0.Value;
            __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_0);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            EndContext();
            BeginContext(1382, 117, true);
            WriteLiteral("\r\n    </div>\r\n</div>\r\n\r\n\r\n\r\n<h3 align=\"center\"> Background Images:</h3>\r\n\r\n<br>\r\n<table class=\"table\">\r\n    <tbody>\r\n");
            EndContext();
#line 63 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
         for (int i = 0; i < 5; i++)
        {

#line default
#line hidden
            BeginContext(1548, 50, true);
            WriteLiteral("        <tr style=\"width:50%\">\r\n            <td>\r\n");
            EndContext();
#line 67 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                  
                    string imageName = "Image Number: " + (i + 1);

                    string imageLocation = "/images/dailyquote/" + (i + 1) + ".jpg";

                

#line default
#line hidden
            BeginContext(1795, 23, true);
            WriteLiteral("                <label>");
            EndContext();
            BeginContext(1819, 9, false);
#line 73 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                  Write(imageName);

#line default
#line hidden
            EndContext();
            BeginContext(1828, 52, true);
            WriteLiteral("</label>\r\n                <br>\r\n                <img");
            EndContext();
            BeginWriteAttribute("src", " src=\"", 1880, "\"", 1900, 1);
#line 75 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
WriteAttributeValue("", 1886, imageLocation, 1886, 14, false);

#line default
#line hidden
            EndWriteAttribute();
            BeginContext(1901, 89, true);
            WriteLiteral(" alt=\"backgroundImage\" width=\"100\" height=\"100\">\r\n            </td>\r\n\r\n            <td>\r\n");
            EndContext();
#line 79 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                  
                    string imageName2 = "Image Number: " + (i + 6);

                    string imageLocation2 = "/images/dailyquote/" + (i + 6) + ".jpg";

                

#line default
#line hidden
            BeginContext(2189, 23, true);
            WriteLiteral("                <label>");
            EndContext();
            BeginContext(2213, 10, false);
#line 85 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                  Write(imageName2);

#line default
#line hidden
            EndContext();
            BeginContext(2223, 52, true);
            WriteLiteral("</label>\r\n                <br>\r\n                <img");
            EndContext();
            BeginWriteAttribute("src", " src=\"", 2275, "\"", 2296, 1);
#line 87 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
WriteAttributeValue("", 2281, imageLocation2, 2281, 15, false);

#line default
#line hidden
            EndWriteAttribute();
            BeginContext(2297, 89, true);
            WriteLiteral(" alt=\"backgroundImage\" width=\"100\" height=\"100\">\r\n            </td>\r\n\r\n            <td>\r\n");
            EndContext();
#line 91 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                  
                    string imageName3 = "Image Number: " + (i + 11);

                    string imageLocation3 = "/images/dailyquote/" + (i + 11) + ".jpg";

                

#line default
#line hidden
            BeginContext(2587, 23, true);
            WriteLiteral("                <label>");
            EndContext();
            BeginContext(2611, 10, false);
#line 97 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                  Write(imageName3);

#line default
#line hidden
            EndContext();
            BeginContext(2621, 52, true);
            WriteLiteral("</label>\r\n                <br>\r\n                <img");
            EndContext();
            BeginWriteAttribute("src", " src=\"", 2673, "\"", 2694, 1);
#line 99 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
WriteAttributeValue("", 2679, imageLocation3, 2679, 15, false);

#line default
#line hidden
            EndWriteAttribute();
            BeginContext(2695, 89, true);
            WriteLiteral(" alt=\"backgroundImage\" width=\"100\" height=\"100\">\r\n            </td>\r\n\r\n            <td>\r\n");
            EndContext();
#line 103 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                  
                    string imageName4 = "Image Number: " + (i + 16);

                    string imageLocation4 = "/images/dailyquote/" + (i + 16) + ".jpg";

                

#line default
#line hidden
            BeginContext(2985, 23, true);
            WriteLiteral("                <label>");
            EndContext();
            BeginContext(3009, 10, false);
#line 109 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                  Write(imageName4);

#line default
#line hidden
            EndContext();
            BeginContext(3019, 52, true);
            WriteLiteral("</label>\r\n                <br>\r\n                <img");
            EndContext();
            BeginWriteAttribute("src", " src=\"", 3071, "\"", 3092, 1);
#line 111 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
WriteAttributeValue("", 3077, imageLocation4, 3077, 15, false);

#line default
#line hidden
            EndWriteAttribute();
            BeginContext(3093, 89, true);
            WriteLiteral(" alt=\"backgroundImage\" width=\"100\" height=\"100\">\r\n            </td>\r\n\r\n            <td>\r\n");
            EndContext();
#line 115 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                  
                    string imageName5 = "Image Number: " + (i + 21);

                    string imageLocation5 = "/images/dailyquote/" + (i + 21) + ".jpg";

                

#line default
#line hidden
            BeginContext(3383, 23, true);
            WriteLiteral("                <label>");
            EndContext();
            BeginContext(3407, 10, false);
#line 121 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                  Write(imageName5);

#line default
#line hidden
            EndContext();
            BeginContext(3417, 52, true);
            WriteLiteral("</label>\r\n                <br>\r\n                <img");
            EndContext();
            BeginWriteAttribute("src", " src=\"", 3469, "\"", 3490, 1);
#line 123 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
WriteAttributeValue("", 3475, imageLocation5, 3475, 15, false);

#line default
#line hidden
            EndWriteAttribute();
            BeginContext(3491, 89, true);
            WriteLiteral(" alt=\"backgroundImage\" width=\"100\" height=\"100\">\r\n            </td>\r\n\r\n            <td>\r\n");
            EndContext();
#line 127 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                  
                    string imageName6 = "Image Number: " + (i + 26);

                    string imageLocation6 = "/images/dailyquote/" + (i + 26) + ".jpg";

                

#line default
#line hidden
            BeginContext(3781, 23, true);
            WriteLiteral("                <label>");
            EndContext();
            BeginContext(3805, 10, false);
#line 133 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                  Write(imageName6);

#line default
#line hidden
            EndContext();
            BeginContext(3815, 52, true);
            WriteLiteral("</label>\r\n                <br>\r\n                <img");
            EndContext();
            BeginWriteAttribute("src", " src=\"", 3867, "\"", 3888, 1);
#line 135 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
WriteAttributeValue("", 3873, imageLocation6, 3873, 15, false);

#line default
#line hidden
            EndWriteAttribute();
            BeginContext(3889, 86, true);
            WriteLiteral(" alt=\"backgroundImage\" width=\"100\" height=\"100\">\r\n            </td>\r\n\r\n        </tr>\r\n");
            EndContext();
#line 139 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
        }

#line default
#line hidden
            BeginContext(3986, 38, true);
            WriteLiteral("    </tbody>\r\n</table>\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
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
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<dynamic> Html { get; private set; }
    }
}
#pragma warning restore 1591