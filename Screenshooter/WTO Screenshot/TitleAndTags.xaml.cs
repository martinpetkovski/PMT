using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using WTO_Screenshot.Class;

namespace WTO_Screenshot
{
    /// <summary>
    /// Interaction logic for TitleAndTags.xaml
    /// </summary>
    public partial class TitleAndTags : Window
    {
        public TitleAndTags()
        {
            InitializeComponent();
        }

        private void btn_OK_Click(object sender, RoutedEventArgs e)
        {
            GlobalVariables.title = txt_title.Text;
            GlobalVariables.tags = txt_tags.Text;
            this.Close();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            txt_title.Focus();
        }
    }
}
