using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Drawing.Imaging;
using System.Net;
using System.Collections.Specialized;
using System.IO;
using System.Diagnostics;
using MySql.Data.MySqlClient;
using WTO_Screenshot.Class.Connection;

namespace WTO_Screenshot.Class
{
    class Screenshot
    {
        private int offset = 90;
        public Bitmap bitmap {get; set;}
        private int positionX;
        private int positionY;
        private int width;
        private int height;
        private String savePath;
        private String title;
        private String tags;
        DBConnect connection = new DBConnect();

        public Screenshot(int positionX, int positionY, int width, int height)
        {
            this.positionX = positionX;
            this.positionY = positionY + this.offset;
            this.width = width;
            this.height = height - this.offset;
        }

        public Screenshot(int positionX, int positionY, int width, int height, String savePath)
        {
            this.positionX = positionX;
            this.positionY = positionY + this.offset;
            this.width = width;
            this.height = height - this.offset;
            this.savePath = savePath;
        }

        public Screenshot(int positionX, int positionY, int width, int height, String title, String tags)
        {
            this.positionX = positionX;
            this.positionY = positionY + this.offset;
            this.width = width;
            this.height = height - this.offset;
            this.title = title;
            this.tags = tags;
            connection.init();
        }

        public void shoot()
        {
            bitmap = new Bitmap(this.width, this.height, PixelFormat.Format32bppArgb);
            Graphics gfxScreenshot = Graphics.FromImage(bitmap);
            gfxScreenshot.CopyFromScreen(this.positionX, this.positionY, 0, 0, new Size(this.width, this.height));
        }

        public void save()
        {
            bitmap.Save(this.savePath, ImageFormat.Png);
        }

        public void save(String path)
        {
            bitmap.Save(path, ImageFormat.Png);
        }

        public String upload()
        {
            bitmap.Save("temp.png",ImageFormat.Png);
            NameValueCollection nvc = new NameValueCollection();
            nvc.Add("iduser", GlobalVariables.theUser.iduser.ToString());
            nvc.Add("title", this.title);
            nvc.Add("tags", this.tags);

            String uid = Guid.NewGuid().ToString("N");

            nvc.Add("uid", uid);

            connection.open();
            string query = "INSERT INTO tokens VALUES(NULL, @uid)";
            MySqlCommand cmd = new MySqlCommand(query, connection.mysqlconnection);
            cmd.Parameters.AddWithValue("@uid", uid);
            cmd.ExecuteNonQuery();
            connection.close();

            return this.HttpUploadFile("http://localhost:8080/WTO/uploadScreenshot", "temp.png" , "file", "image/png", nvc);
        }

        private String HttpUploadFile(string url, string file, string paramName, string contentType, NameValueCollection nvc)
        {
            String log;
            log = string.Format("Uploading {0} to {1}", file, url);
            string boundary = "---------------------------" + DateTime.Now.Ticks.ToString("x");
            byte[] boundarybytes = System.Text.Encoding.ASCII.GetBytes("\r\n--" + boundary + "\r\n");

            HttpWebRequest wr = (HttpWebRequest)WebRequest.Create(url);
            wr.ContentType = "multipart/form-data; boundary=" + boundary;
            wr.Method = "POST";
            wr.KeepAlive = true;
            wr.Credentials = System.Net.CredentialCache.DefaultCredentials;

            Stream rs = wr.GetRequestStream();

            string formdataTemplate = "Content-Disposition: form-data; name=\"{0}\"\r\n\r\n{1}";
            foreach (string key in nvc.Keys)
            {
                rs.Write(boundarybytes, 0, boundarybytes.Length);
                string formitem = string.Format(formdataTemplate, key, nvc[key]);
                byte[] formitembytes = System.Text.Encoding.UTF8.GetBytes(formitem);
                rs.Write(formitembytes, 0, formitembytes.Length);
            }
            rs.Write(boundarybytes, 0, boundarybytes.Length);

            string headerTemplate = "Content-Disposition: form-data; name=\"{0}\"; filename=\"{1}\"\r\nContent-Type: {2}\r\n\r\n";
            string header = string.Format(headerTemplate, paramName, file, contentType);
            byte[] headerbytes = System.Text.Encoding.UTF8.GetBytes(header);
            rs.Write(headerbytes, 0, headerbytes.Length);

            FileStream fileStream = new FileStream(file, FileMode.Open, FileAccess.Read);
            byte[] buffer = new byte[4096];
            int bytesRead = 0;
            while ((bytesRead = fileStream.Read(buffer, 0, buffer.Length)) != 0)
            {
                rs.Write(buffer, 0, bytesRead);
            }
            fileStream.Close();

            byte[] trailer = System.Text.Encoding.ASCII.GetBytes("\r\n--" + boundary + "--\r\n");
            rs.Write(trailer, 0, trailer.Length);
            rs.Close();

            WebResponse wresp = null;
            try
            {
                wresp = wr.GetResponse();
                return wresp.Headers["url"];

            }
            catch (Exception ex)
            {
                log = "Error uploading file" + ex.StackTrace;
                if (wresp != null)
                {
                    wresp.Close();
                    wresp = null;
                }
                return "error";
            }
            finally
            {
                wr = null;
            }
        }
    }
}
