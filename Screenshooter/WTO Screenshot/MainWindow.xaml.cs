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
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Configuration;
using WTO_Screenshot.Class.Model;
using WTO_Screenshot.Class;

namespace WTO_Screenshot
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private Brush convertToBrush(String color)
        {
            BrushConverter converter = new System.Windows.Media.BrushConverter();
            Brush brush = (Brush)converter.ConvertFromString(color);
            return brush;
        }

        private void setInfoLabel()
        {
            lbl_error.Content = "Trying to log in...";
            lbl_error.Foreground = this.convertToBrush("#FFCCCCCC");
            lbl_error.Visibility = System.Windows.Visibility.Visible;
        }

        private void setErrorLabel()
        {
            lbl_error.Content = "Incorrect username / password combination.";
            lbl_error.Foreground = this.convertToBrush("#FFCC0000");
            lbl_error.Visibility = System.Windows.Visibility.Visible;
        }

        private void setSuccessLabel()
        {
            lbl_error.Content = "Successful login.";
            lbl_error.Foreground = this.convertToBrush("#FF00CC00");
            lbl_error.Visibility = System.Windows.Visibility.Visible;
        }

        private void checkInput()
        {
            GlobalVariables.theUser = new User(txt_username.Text, txt_password.Password);

            if (!GlobalVariables.theUser.loadUser())
            {
                this.setErrorLabel();
            }
            else
            {
                this.setErrorLabel();
                Screenshooter scsh = new Screenshooter();
                this.Close();
                scsh.Show();

                GlobalVariables.setSetting("username", GlobalVariables.theUser.username);
            }
        }

        public MainWindow()
        {
            InitializeComponent();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            if(ConfigurationManager.AppSettings["username"] != null)
            {
                Screenshooter scsh = new Screenshooter();
                this.Close();
                scsh.Show();
            }
            txt_password.Password = "password";
            txt_username.Text = "username";
            txt_username.Foreground = Brushes.LightGray;
            txt_password.Foreground = Brushes.LightGray;
            txt_username.Focus();
        }

        private void txt_username_GotFocus(object sender, RoutedEventArgs e)
        {
            if (txt_username.Text == "username")
            {
                txt_username.Text = "";
                txt_username.Foreground = Brushes.Black;
            }
        }

        private void txt_username_LostFocus(object sender, RoutedEventArgs e)
        {
            if (txt_username.Text == "" || txt_username.Text == "username")
            {
                txt_username.Text = "username";
                txt_username.Foreground = Brushes.LightGray;
            }
        }

        private void txt_password_GotFocus(object sender, RoutedEventArgs e)
        {
            if (txt_password.Password == "password")
            {
                txt_password.Password = "";
                txt_password.Foreground = Brushes.Black;
            }
        }

        private void txt_password_LostFocus(object sender, RoutedEventArgs e)
        {
            if (txt_password.Password == "" || txt_password.Password == "password")
            {
                txt_password.Password = "password";
                txt_password.Foreground = Brushes.LightGray;
            }
        }

        private void btn_login_Click(object sender, RoutedEventArgs e)
        {
            this.checkInput();
        }

        private void txt_username_TextChanged(object sender, TextChangedEventArgs e)
        {
            lbl_error.Visibility = System.Windows.Visibility.Hidden;
        }

        private void txt_password_PasswordChanged(object sender, RoutedEventArgs e)
        {
            lbl_error.Visibility = System.Windows.Visibility.Hidden;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void lbl_drag_MouseDown(object sender, MouseButtonEventArgs e)
        {
            if(e.ChangedButton == MouseButton.Left)
                DragMove();
        }

        private void txt_username_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Enter)
                this.checkInput();
        }

        private void txt_password_KeyUp(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Enter)
                this.checkInput();
        }

        private void txt_username_KeyDown(object sender, KeyEventArgs e)
        {
            if(e.Key == Key.Enter)
                this.setInfoLabel();
        }

        private void txt_password_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Enter)
                this.setInfoLabel();
        }

        private void btn_login_MouseDown(object sender, MouseButtonEventArgs e)
        {
            this.setInfoLabel();
        }
    }
}
