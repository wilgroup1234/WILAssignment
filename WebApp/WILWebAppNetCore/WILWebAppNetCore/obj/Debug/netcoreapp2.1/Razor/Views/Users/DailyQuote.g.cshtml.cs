#pragma checksum "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "73bfaac267acce701b612986bd10ca33d6a54275"
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
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"73bfaac267acce701b612986bd10ca33d6a54275", @"/Views/Users/DailyQuote.cshtml")]
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
            BeginContext(48, 60, true);
            WriteLiteral("\r\n<h2 align=\"center\">DailyQuote</h2>\r\n\r\n<br>\r\n<br>\r\n<br>\r\n\r\n");
            EndContext();
#line 12 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
   
    List<String> numbers = new List<string>();

    for (int i = 0; i < 20; i++)
    {
        numbers.Add((i + 1) + "");
    }

#line default
#line hidden
            BeginContext(250, 120, true);
            WriteLiteral("\r\n<h3 align=\"center\"> Enter information for Daily Quote:</h3>\r\n\r\n<div class=\"row\">\r\n    <div class=\"col-md-4\">\r\n        ");
            EndContext();
            BeginContext(370, 838, false);
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("form", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "4310098612a14015bc24a002d1a1af15", async() => {
                BeginContext(400, 104, true);
                WriteLiteral("\r\n\r\n            <div class=\"form-group\">\r\n                <label>Image Number:</label>\r\n                ");
                EndContext();
                BeginContext(505, 174, false);
#line 29 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
           Write(Html.DropDownList("ImageNumber", numbers.Select(m => new SelectListItem { Value = m.ToString(), Text = m.ToString() }), new { @class = "form-control", @name = "imageNumber"}));

#line default
#line hidden
                EndContext();
                BeginContext(679, 522, true);
                WriteLiteral(@"
            </div>
            <div class=""form-group"">
                <label>Quote:</label>
                <input type=""text"" name=""quote"" class="" form-control""/>
            </div>
            <div class=""form-group"">
                <label>Youtube Link:</label>
                <input type=""text"" name=""link"" class="" form-control""/>
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
            BeginContext(1208, 121, true);
            WriteLiteral("\r\n    </div>\r\n</div>\r\n\r\n    \r\n\r\n<h3 align=\"center\"> Background Images:</h3>\r\n\r\n<br>\r\n<table class=\"table\">\r\n    <tbody>\r\n");
            EndContext();
#line 54 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
         for (int i = 0; i < 5; i++)
        {

#line default
#line hidden
            BeginContext(1378, 58, true);
            WriteLiteral("            <tr style=\"width:50%\">\r\n                <td>\r\n");
            EndContext();
#line 58 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                      
                        string imageName = "Image Number: " + (i + 1);

                        string imageLocation = "/images/dailyquote/" + (i + 1) + ".jpg";

                    

#line default
#line hidden
            BeginContext(1649, 27, true);
            WriteLiteral("                    <label>");
            EndContext();
            BeginContext(1677, 9, false);
#line 64 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                      Write(imageName);

#line default
#line hidden
            EndContext();
            BeginContext(1686, 60, true);
            WriteLiteral("</label>\r\n                    <br>\r\n                    <img");
            EndContext();
            BeginWriteAttribute("src", " src=\"", 1746, "\"", 1766, 1);
#line 66 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
WriteAttributeValue("", 1752, imageLocation, 1752, 14, false);

#line default
#line hidden
            EndWriteAttribute();
            BeginContext(1767, 97, true);
            WriteLiteral(" alt=\"backgroundImage\" width=\"100\" height=\"100\">\r\n                </td>\r\n\r\n                <td>\r\n");
            EndContext();
#line 70 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                      
                        string imageName2 = "Image Number: " + (i + 6);

                        string imageLocation2 = "/images/dailyquote/" + (i + 6) + ".jpg";

                    

#line default
#line hidden
            BeginContext(2079, 27, true);
            WriteLiteral("                    <label>");
            EndContext();
            BeginContext(2107, 10, false);
#line 76 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                      Write(imageName2);

#line default
#line hidden
            EndContext();
            BeginContext(2117, 60, true);
            WriteLiteral("</label>\r\n                    <br>\r\n                    <img");
            EndContext();
            BeginWriteAttribute("src", " src=\"", 2177, "\"", 2198, 1);
#line 78 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
WriteAttributeValue("", 2183, imageLocation2, 2183, 15, false);

#line default
#line hidden
            EndWriteAttribute();
            BeginContext(2199, 97, true);
            WriteLiteral(" alt=\"backgroundImage\" width=\"100\" height=\"100\">\r\n                </td>\r\n\r\n                <td>\r\n");
            EndContext();
#line 82 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                      
                        string imageName3 = "Image Number: " + (i + 11);

                        string imageLocation3 = "/images/dailyquote/" + (i + 11) + ".jpg";

                    

#line default
#line hidden
            BeginContext(2513, 27, true);
            WriteLiteral("                    <label>");
            EndContext();
            BeginContext(2541, 10, false);
#line 88 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                      Write(imageName3);

#line default
#line hidden
            EndContext();
            BeginContext(2551, 60, true);
            WriteLiteral("</label>\r\n                    <br>\r\n                    <img");
            EndContext();
            BeginWriteAttribute("src", " src=\"", 2611, "\"", 2632, 1);
#line 90 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
WriteAttributeValue("", 2617, imageLocation3, 2617, 15, false);

#line default
#line hidden
            EndWriteAttribute();
            BeginContext(2633, 97, true);
            WriteLiteral(" alt=\"backgroundImage\" width=\"100\" height=\"100\">\r\n                </td>\r\n\r\n                <td>\r\n");
            EndContext();
#line 94 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                      
                        string imageName4 = "Image Number: " + (i + 16);

                        string imageLocation4 = "/images/dailyquote/" + (i + 16) + ".jpg";

                    

#line default
#line hidden
            BeginContext(2947, 27, true);
            WriteLiteral("                    <label>");
            EndContext();
            BeginContext(2975, 10, false);
#line 100 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
                      Write(imageName4);

#line default
#line hidden
            EndContext();
            BeginContext(2985, 60, true);
            WriteLiteral("</label>\r\n                    <br>\r\n                    <img");
            EndContext();
            BeginWriteAttribute("src", " src=\"", 3045, "\"", 3066, 1);
#line 102 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
WriteAttributeValue("", 3051, imageLocation4, 3051, 15, false);

#line default
#line hidden
            EndWriteAttribute();
            BeginContext(3067, 94, true);
            WriteLiteral(" alt=\"backgroundImage\" width=\"100\" height=\"100\">\r\n                </td>\r\n\r\n            </tr>\r\n");
            EndContext();
#line 106 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\DailyQuote.cshtml"
        }

#line default
#line hidden
            BeginContext(3172, 38, true);
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