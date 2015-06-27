using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Configuration;
using WTO_Screenshot.Class.Model;

namespace WTO_Screenshot.Class
{
    class GlobalVariables
    {
        public static User theUser {get; set;}

        public static bool setSetting(string pstrKey, string pstrValue)
        {
            Configuration objConfigFile = ConfigurationManager.OpenExeConfiguration(ConfigurationUserLevel.None);
            bool blnKeyExists = false;

            foreach (string strKey in objConfigFile.AppSettings.Settings.AllKeys)
            {
                if (strKey == pstrKey)
                {
                    blnKeyExists = true;
                    objConfigFile.AppSettings.Settings[pstrKey].Value = pstrValue;
                    break;
                }
            }
            if (!blnKeyExists)
            {
                objConfigFile.AppSettings.Settings.Add(pstrKey, pstrValue);
            }
            objConfigFile.Save(ConfigurationSaveMode.Full);
            ConfigurationManager.RefreshSection("appSettings");
            return true;
        }
    }
}
