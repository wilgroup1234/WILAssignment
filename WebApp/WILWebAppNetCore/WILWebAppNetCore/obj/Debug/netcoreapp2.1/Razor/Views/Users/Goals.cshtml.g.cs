#pragma checksum "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "c639f0c949db8e0ded7babf52c1f3f6135a10815"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Users_Goals), @"mvc.1.0.view", @"/Views/Users/Goals.cshtml")]
[assembly:global::Microsoft.AspNetCore.Mvc.Razor.Compilation.RazorViewAttribute(@"/Views/Users/Goals.cshtml", typeof(AspNetCore.Views_Users_Goals))]
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
#line 2 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
using WILWebAppNetCore.Classes;

#line default
#line hidden
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"c639f0c949db8e0ded7babf52c1f3f6135a10815", @"/Views/Users/Goals.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"9a6a4373b40b81283e18bb4404d66f416a3bd4e0", @"/Views/_ViewImports.cshtml")]
    public class Views_Users_Goals : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<WILWebAppNetCore.Models.Goals>
    {
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_0 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("rel", new global::Microsoft.AspNetCore.Html.HtmlString("stylesheet"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_1 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("href", new global::Microsoft.AspNetCore.Html.HtmlString("~/css/Style3.css"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_2 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("asp-action", "Goals", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
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
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            BeginContext(71, 2, true);
            WriteLiteral("\r\n");
            EndContext();
#line 4 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
  
    ViewData["Title"] = "Goals";

#line default
#line hidden
            BeginContext(114, 6, true);
            WriteLiteral("\r\n    ");
            EndContext();
            BeginContext(120, 78, false);
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("head", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "0ab5608294b34028a253245726800465", async() => {
                BeginContext(126, 10, true);
                WriteLiteral("\r\n        ");
                EndContext();
                BeginContext(136, 49, false);
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("link", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "803e0de72aa2433d90c663d07ca52a16", async() => {
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
                BeginContext(185, 6, true);
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
            BeginContext(198, 37, true);
            WriteLiteral("\r\n\r\n<h2 align=\"center\">Goals</h2>\r\n\r\n");
            EndContext();
#line 14 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
  
    if (!StaticClass.errorMessage.Equals("NO_ERROR"))
    {

#line default
#line hidden
            BeginContext(301, 32, true);
            WriteLiteral("        <h2 class=\"text-danger\">");
            EndContext();
            BeginContext(334, 15, false);
#line 17 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
                           Write(ViewBag.Message);

#line default
#line hidden
            EndContext();
            BeginContext(349, 7, true);
            WriteLiteral("</h2>\r\n");
            EndContext();
#line 18 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
    }

#line default
#line hidden
            BeginContext(366, 149, true);
            WriteLiteral("\r\n<br>\r\n<br>\r\n\r\n<h3 align=\"center\"> Enter information for the new Goal:</h3>\r\n\r\n<br>\r\n<br>\r\n\r\n<div class=\"row\">\r\n    <div class=\"col-md-4\">\r\n        ");
            EndContext();
            BeginContext(515, 550, false);
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("form", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "de77c0ddcd2b4c3b8546403375348e15", async() => {
                BeginContext(540, 518, true);
                WriteLiteral(@"

            <div class=""form-group"">
                <label>Goal Name:</label>
                <input type=""text"" name=""goalName"" class="" form-control"" />
            </div>
            <div class=""form-group"">
                <label>Goal Description:</label>
                <input type=""text"" name=""goalDescription"" class="" form-control"" />
            </div>
            <div class=""form-group"">
                <input type=""submit"" value=""Add Goal"" class=""btnsubmit"" />
            </div>

        ");
                EndContext();
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Action = (string)__tagHelperAttribute_2.Value;
            __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_2);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            EndContext();
            BeginContext(1065, 38, true);
            WriteLiteral("\r\n    </div>\r\n</div>\r\n\r\n<br>\r\n<br>\r\n\r\n");
            EndContext();
            BeginContext(1107, 104, true);
            WriteLiteral("    <table class=\"table\">\r\n        <thead>\r\n            <tr>\r\n                <th>\r\n                    ");
            EndContext();
            BeginContext(1212, 42, false);
#line 57 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
               Write(Html.DisplayNameFor(model => model.GoalId));

#line default
#line hidden
            EndContext();
            BeginContext(1254, 67, true);
            WriteLiteral("\r\n                </th>\r\n                <th>\r\n                    ");
            EndContext();
            BeginContext(1322, 44, false);
#line 60 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
               Write(Html.DisplayNameFor(model => model.GoalName));

#line default
#line hidden
            EndContext();
            BeginContext(1366, 67, true);
            WriteLiteral("\r\n                </th>\r\n                <th>\r\n                    ");
            EndContext();
            BeginContext(1434, 51, false);
#line 63 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
               Write(Html.DisplayNameFor(model => model.GoalDescription));

#line default
#line hidden
            EndContext();
            BeginContext(1485, 106, true);
            WriteLiteral("\r\n                </th>\r\n                <th></th>\r\n            </tr>\r\n        </thead>\r\n        <tbody>\r\n");
            EndContext();
#line 69 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
             foreach (Goals goal in ViewBag.GoalList)
            {

#line default
#line hidden
            BeginContext(1661, 72, true);
            WriteLiteral("                <tr>\r\n                    <td>\r\n                        ");
            EndContext();
            BeginContext(1734, 41, false);
#line 73 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
                   Write(Html.DisplayFor(modelItem => goal.GoalId));

#line default
#line hidden
            EndContext();
            BeginContext(1775, 79, true);
            WriteLiteral("\r\n                    </td>\r\n                    <td>\r\n                        ");
            EndContext();
            BeginContext(1855, 43, false);
#line 76 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
                   Write(Html.DisplayFor(modelItem => goal.GoalName));

#line default
#line hidden
            EndContext();
            BeginContext(1898, 79, true);
            WriteLiteral("\r\n                    </td>\r\n                    <td>\r\n                        ");
            EndContext();
            BeginContext(1978, 50, false);
#line 79 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
                   Write(Html.DisplayFor(modelItem => goal.GoalDescription));

#line default
#line hidden
            EndContext();
            BeginContext(2028, 56, true);
            WriteLiteral("\r\n                    </td>\r\n\r\n\r\n                </tr>\r\n");
            EndContext();
#line 84 "C:\Users\Jarrod\source\repos\WILWebAppNetCore\WILWebAppNetCore\Views\Users\Goals.cshtml"
            }

#line default
#line hidden
            BeginContext(2099, 32, true);
            WriteLiteral("        </tbody>\r\n    </table>\r\n");
            EndContext();
            BeginContext(2138, 4, true);
            WriteLiteral("\r\n\r\n");
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
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<WILWebAppNetCore.Models.Goals> Html { get; private set; }
    }
}
#pragma warning restore 1591
