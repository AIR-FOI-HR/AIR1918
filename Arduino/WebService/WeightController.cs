using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Pamtim.Controllers
{
    public class WeightController : Controller
    {
        // GET: Weight
        public void Insert(string data)
        {

            string[] splitData = data.Split('-');


            if (splitData[2] == "1341996")
            {
                using (var conn = new SqlConnection(ConfigurationManager.ConnectionStrings["2FIT4YOU"].ConnectionString))
                {
                    conn.Open();
                    if (conn.State == ConnectionState.Open)
                    {
                        var upit = "insert into Weight (ID_User, Weight) " +
                                   "select Nfc.ID_User, " +
                                   "'" + splitData[1] + "'" +
                                   " from Nfc where Nfc.NfcTag = " +
                                   "'" + splitData[0] + "'";
                        using (var cmd = new SqlCommand(upit, conn))
                        {
                            cmd.CommandType = CommandType.Text;
                            cmd.ExecuteNonQuery();
                        }
                    }
                }
            }

            

        }
    }
}