using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MySql.Data.MySqlClient;

namespace WTO_Screenshot.Class.Connection
{
    class DBConnect
    {
        private String server = "localhost";
        private String database = "wto";
        private String uid = "root";
        private String password = "space2001";
        private String connectionString;
        public MySqlConnection mysqlconnection { get; set; }

        public void init() 
        {
            connectionString = "SERVER=" + server + ";DATABASE=" + database+ ";UID=" + uid + ";PASSWORD=" + password;

            mysqlconnection = new MySqlConnection(connectionString);
        }
        
        public bool open() 
        {
            try 
            {
                mysqlconnection.Open();
                return true;
            }
            catch (Exception)
            {
                throw;
            }
            
        }

        public void close()
        {
            mysqlconnection.Close();
        }
    }
}
