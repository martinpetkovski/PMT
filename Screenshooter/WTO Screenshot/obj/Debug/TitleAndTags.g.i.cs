﻿#pragma checksum "..\..\TitleAndTags.xaml" "{406ea660-64cf-4c82-b6f0-42d48172a799}" "D911441CBFAEAD28C4E7F9494BA4C014"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.33440
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Media.Media3D;
using System.Windows.Media.TextFormatting;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Shell;


namespace WTO_Screenshot {
    
    
    /// <summary>
    /// TitleAndTags
    /// </summary>
    public partial class TitleAndTags : System.Windows.Window, System.Windows.Markup.IComponentConnector {
        
        
        #line 23 "..\..\TitleAndTags.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox txt_title;
        
        #line default
        #line hidden
        
        
        #line 24 "..\..\TitleAndTags.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.TextBox txt_tags;
        
        #line default
        #line hidden
        
        
        #line 25 "..\..\TitleAndTags.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button btn_OK;
        
        #line default
        #line hidden
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Uri resourceLocater = new System.Uri("/WTO Screenshot;component/titleandtags.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\TitleAndTags.xaml"
            System.Windows.Application.LoadComponent(this, resourceLocater);
            
            #line default
            #line hidden
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "4.0.0.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        void System.Windows.Markup.IComponentConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 1:
            
            #line 5 "..\..\TitleAndTags.xaml"
            ((WTO_Screenshot.TitleAndTags)(target)).Loaded += new System.Windows.RoutedEventHandler(this.Window_Loaded);
            
            #line default
            #line hidden
            return;
            case 2:
            this.txt_title = ((System.Windows.Controls.TextBox)(target));
            
            #line 23 "..\..\TitleAndTags.xaml"
            this.txt_title.GotFocus += new System.Windows.RoutedEventHandler(this.txt_title_GotFocus);
            
            #line default
            #line hidden
            
            #line 23 "..\..\TitleAndTags.xaml"
            this.txt_title.LostFocus += new System.Windows.RoutedEventHandler(this.txt_title_LostFocus);
            
            #line default
            #line hidden
            return;
            case 3:
            this.txt_tags = ((System.Windows.Controls.TextBox)(target));
            
            #line 24 "..\..\TitleAndTags.xaml"
            this.txt_tags.GotFocus += new System.Windows.RoutedEventHandler(this.txt_tags_GotFocus);
            
            #line default
            #line hidden
            
            #line 24 "..\..\TitleAndTags.xaml"
            this.txt_tags.LostFocus += new System.Windows.RoutedEventHandler(this.txt_tags_LostFocus);
            
            #line default
            #line hidden
            return;
            case 4:
            this.btn_OK = ((System.Windows.Controls.Button)(target));
            
            #line 25 "..\..\TitleAndTags.xaml"
            this.btn_OK.Click += new System.Windows.RoutedEventHandler(this.btn_OK_Click);
            
            #line default
            #line hidden
            return;
            }
            this._contentLoaded = true;
        }
    }
}

