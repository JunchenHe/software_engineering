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
        private string id2_info="";
        private string id1_info ="";
        private string id0_info = "";

        //MySqlCommand mycmd1 = new MySqlCommand("insert into message(level,note) values(0,\"" + info + "\")", conn);
        private void button1_Click(object sender, EventArgs e)
        {
            int[] marks = new int[] { 0, 0, 0 };
            if (checkBox1.Checked == true)
            {
                marks[0] = 1;
            }
            if (checkBox2.Checked == true)
            {
                marks[1] = 1;
            }
            if (checkBox3.Checked == true)
            {
                marks[2] = 1;
            }
            int result = marks[0] * 4 + marks[1] * 2 + marks[2] * 1;
             
            string connetStr = "server=120.79.73.175;port=3306;user=soft;password=123; database=data;";//database 待改
            MySqlConnection conn = new MySqlConnection(connetStr);

            string info = textBox1.Text;

            try
            {
                conn.Open();//打开通道，建立连接，可能出现异常,使用try catch语句
                
                switch(result)
                {
                    case 1:
                        MySqlCommand mycmd = new MySqlCommand("insert into message(level,note) values(2,\"" + info +id2_info+ "\")", conn);
                        if (mycmd.ExecuteNonQuery() > 0)
                        {
                            label3.Text = "成功！";
                        }
                        break;
                    case 2:
                        MySqlCommand mycmd2 = new MySqlCommand("insert into message(level,note) values(1,\"" + info+id1_info + "\")", conn);
                        if (mycmd2.ExecuteNonQuery() > 0)
                        {
                            label3.Text = "成功！";
                        }
                        break;
                    case 3:
                        MySqlCommand mycmd3 = new MySqlCommand("insert into message(level,note) values(1,\"" + info +id1_info+ "\"),(2,\""+info+id2_info+"\")", conn);
                        if (mycmd3.ExecuteNonQuery() > 0)
                        {
                            label3.Text = "成功！";
                        }
                        break;
                    case 4:
                        MySqlCommand mycmd4 = new MySqlCommand("insert into message(level,note) values(0,\"" + info +id0_info+ "\")", conn);
                        if (mycmd4.ExecuteNonQuery() > 0)
                        {
                            label3.Text = "成功！";
                        }
                        break;
                    case 5:
                        MySqlCommand mycmd5 = new MySqlCommand("insert into message(level,note) values(0,\"" + info +id0_info+ "\"),(2,\"" + info +id2_info+ "\")", conn);
                        if (mycmd5.ExecuteNonQuery() > 0)
                        {
                            label3.Text = "成功！";
                        }
                        break;
                    case 6:
                        MySqlCommand mycmd6 = new MySqlCommand("insert into message(level,note) values(0,\"" + info +id0_info+ "\"),(1,\"" + info +id1_info+ "\")", conn);
                        if (mycmd6.ExecuteNonQuery() > 0)
                        {
                            label3.Text = "成功！";
                        }
                        break;
                    case 7:
                        MySqlCommand mycmd7 = new MySqlCommand("insert into message(level,note) values(0,\"" + info+id0_info + "\"),(1,\"" + info+id1_info+"\"),(2,\""+info+id2_info+"\")", conn);
                        if (mycmd7.ExecuteNonQuery() > 0)
                        {
                            label3.Text = "成功！";
                        }
                        break;
                    default:
                        label3.Text = "你没有选中用户权限！";
                        break;
                    
                }
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

        public void show_info()
        {
            string str0 = "", str1 = "", str2 = "";
            if (id2_info != "")
            {
                str2 = "用户权限2可见: " + id2_info + "\r\n";
            }
            if (id1_info != "")
            {
                str1 = "用户权限1可见: " + id1_info + "\r\n";
            }
            if (id0_info != "")
            {
                str0 = "用户权限0可见: " + id0_info + "\r\n";
            }
            textBox2.Text = str0 + str1 + str2;

        }

        private void checkBox3_CheckedChanged(object sender, EventArgs e)
        {
            if (checkBox3.Checked == true)
            {
                Form3 form3 = new Form3(this);
                form3.Show();
            }
            else
            {
                id2_info = "";
                show_info();
            }
        }
        public void get_form3_info(string form3_info)
        {
            id2_info = form3_info;
            show_info();
        }

        private void checkBox2_CheckedChanged(object sender, EventArgs e)
        {
            if (checkBox2.Checked == true)
            {
                Form4 form4 = new Form4(this);
                form4.Show();
            }
            else
            {
                id1_info = "";
                show_info();
            }

        }
        public void get_form4_info(string form4_info)
        {
            id1_info = form4_info;
            show_info();
        }

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {
            if (checkBox1.Checked == true)
            {
                Form5 form5 = new Form5(this);
                form5.Show();
            }
            else
            {
                id0_info = "";
                show_info();
            }
        }
        public void get_form5_info(string form5_info)
        {
            id0_info = form5_info;
            show_info();
        }

    }
}
