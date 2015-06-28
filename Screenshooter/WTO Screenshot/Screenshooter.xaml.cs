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
using System.Windows.Forms;
using System.Diagnostics;
using WTO_Screenshot.Class;

namespace WTO_Screenshot
{
    /// <summary>
    /// Interaction logic for Screenshooter.xaml
    /// </summary>
    public partial class Screenshooter : Window
    {
        private String saveAddress;
        private String saveFile;
        private short saveType;

        public Screenshooter()
        {
            InitializeComponent();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            this.Title = "Screenshooter: " + GlobalVariables.theUser.username;
            lbl_username.Content = GlobalVariables.theUser.username.ToUpper();
            lbl_points.Content = "Points: " + GlobalVariables.theUser.points.ToString();
            lbl_followers.Content = "Followers: " + GlobalVariables.theUser.followers.ToString();
            saveType = 0;
        }

        private void Window_MouseDown(object sender, MouseButtonEventArgs e)
        {
            if (e.ChangedButton == MouseButton.Left)
                DragMove();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void btn_save_Click(object sender, RoutedEventArgs e)
        {
            saveType = 2;

            int posX = (int)this.Left;
            int posY = (int)this.Top;
            int width = (int)this.Width;
            int height = (int)this.Height;

            this.Hide();
            Screenshot scrs = new Screenshot(posX, posY, width, height);
            scrs.shoot();
            this.Show();

            SaveFileDialog saveDialog = new SaveFileDialog();
            saveDialog.Filter = "Portable Network Graphics  (*.png)|*.png";
            saveDialog.FileName = "Screenshot";
            saveDialog.Title = "Save As";
            saveDialog.RestoreDirectory = false;
            if (saveDialog.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
                scrs.save(saveDialog.FileName);
                saveAddress = System.IO.Path.GetDirectoryName(saveDialog.FileName);
                saveFile = saveDialog.FileName;
                btn_info.Content = "Click here to open image location.";
            }
        }

        private void btn_upload_Click(object sender, RoutedEventArgs e)
        {
            saveType = 1;

            TitleAndTags tat = new TitleAndTags();
            tat.ShowInTaskbar = false;
            tat.ShowDialog();
            if (GlobalVariables.title != null)
            {
                int posX = (int)this.Left;
                int posY = (int)this.Top;
                int width = (int)this.Width;
                int height = (int)this.Height;

                this.Hide();
                Screenshot scrs = new Screenshot(posX, posY, width, height);
                scrs.shoot();
                this.Show();

                saveAddress = scrs.upload();
                btn_info.Content = "Click here to copy image address to clipboard.";

                GlobalVariables.title = null;
                GlobalVariables.tags = null;
            }
        }

        private void btn_info_Click(object sender, RoutedEventArgs e)
        {
            if(saveType == 1)
            {
                System.Windows.Clipboard.SetText(saveAddress);
            }
            else if(saveType == 2)
            {
                Process.Start("explorer.exe", " /select, " + saveFile);
            }
        }

        private void btn_upload_MouseDown(object sender, MouseButtonEventArgs e)
        {
            if(e.ChangedButton == MouseButton.Left)
                btn_info.Content = "Uploading...";
        }
    }
}
