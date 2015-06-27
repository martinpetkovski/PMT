using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using MySql.Data.MySqlClient;
using WTO_Screenshot.Class.Connection;

namespace WTO_Screenshot.Class.Model
{
    class User
    {
        public int iduser { get; set; }
        public String username { get; set; }
        private String password;
        private String hashedPassword;
        public int points { get; set; }
        public int followers { get; set; }
        DBConnect connection = new DBConnect();

        public User(String username, String password)
        {
            this.username = username;
            this.password = password;
            connection.init();
        }

        public bool loadUser()
        {
            if (connection.open())
            {
                string query = "SELECT * FROM user WHERE username = @username";

                MySqlCommand cmd = new MySqlCommand(query, connection.mysqlconnection);
                cmd.Parameters.AddWithValue("@username", this.username);
                MySqlDataReader dataReader = cmd.ExecuteReader();

                //Read the data and store them in the list
                while (dataReader.Read())
                {
                    this.iduser = (int)dataReader["iduser"];
                    this.username = (String)dataReader["username"];
                    this.hashedPassword = (String)dataReader["password"];
                    this.points = (int)dataReader["points"];
                    this.followers = (int)dataReader["followers"];
                }

                connection.close();

                if (hashedPassword != null)
                {
                    if (BCrypt.Net.BCrypt.Verify(this.password, this.hashedPassword))
                        return true;
                    else
                        return false;
                }
                else
                    return false;
            }
            else return false;
        }
    }
}
