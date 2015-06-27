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
using WTO_Screenshot.Class;

namespace WTO_Screenshot
{
    /// <summary>
    /// Interaction logic for Screenshooter.xaml
    /// </summary>
    public partial class Screenshooter : Window
    {
        public Screenshooter()
        {
            InitializeComponent();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            this.Title = "Screenshooter: " + GlobalVariables.theUser.username;
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
            if (saveDialog.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
                scrs.save(saveDialog.FileName);
            }
        }

        private void btn_upload_Click(object sender, RoutedEventArgs e)
        {
            int posX = (int)this.Left;
            int posY = (int)this.Top;
            int width = (int)this.Width;
            int height = (int)this.Height;

            this.Hide();
            Screenshot scrs = new Screenshot(posX, posY, width, height);
            scrs.shoot();
            this.Show();

            scrs.upload();
        }
    }
}
