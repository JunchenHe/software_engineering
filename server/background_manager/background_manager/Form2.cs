using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using MySql.Data.MySqlClient;

namespace background_manager
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
             string connetStr = "server=192.168.52.131;port=3306;user=he;password=123; database=softengineer;";//database 待改
             MySqlConnection conn = new MySqlConnection(connetStr);

            string tmp="实验！";

            try
            {
                conn.Open();//打开通道，建立连接，可能出现异常,使用try catch语句
                //Console.WriteLine("已经建立连接");
                

                MySqlCommand mycmd = null;

                mycmd=new MySqlCommand("insert into info(level,note) values('1','" + tmp + "')", conn);
                
                
                if (mycmd.ExecuteNonQuery() > 0)
                {
                    label2.Text="发布成功！";
                }
                //在这里使用代码对数据库进行增删查改
            }
            catch (MySqlException ex)
            {
                Console.WriteLine(ex.Message);
            }
            finally
            {
                conn.Close();
            }

            
                

            
        }
    }
}
