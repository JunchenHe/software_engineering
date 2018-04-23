using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using MySql.Data.MySqlClient;
using Gma.QrCodeNet.Encoding;
using Gma.QrCodeNet.Encoding.Windows.Render;
using System.IO;
using System.Drawing.Imaging;



namespace background_manager
{
    public partial class Form1 : Form
    {
        protected string connetStr = "server=192.168.52.131;port=3306;user=he;password=123; database=softengineer;";//database 待改
        protected static string url = "https://www.baidu.com";

        public Form1()
        {
            
            InitializeComponent();
            bindListCiew();
        }

        private void button1_Click(object sender, EventArgs e)
        {//link_to_sql_server

            Form2 from2 = new Form2();
            from2.Show();
            //this.Hide();

            //MySqlConnection conn = new MySqlConnection(connetStr);

            //try
            //{
            //    conn.Open();//打开通道，建立连接，可能出现异常,使用try catch语句
            //    Console.WriteLine("已经建立连接");
            //    MySqlCommand mycmd = new MySqlCommand("insert into info(firstname,secondname) values('he','1234')", conn);
            //    if (mycmd.ExecuteNonQuery() > 0)
            //    {
            //        Console.WriteLine("数据插入成功！");
            //    }
            //    //在这里使用代码对数据库进行增删查改
            //}
            //catch (MySqlException ex)
            //{
            //    Console.WriteLine(ex.Message);
            //}
            //finally
            //{
            //    conn.Close();
            //}


        }

        private void button2_Click(object sender, EventArgs e)
        {
            QrEncoder qrEncoder = new QrEncoder(ErrorCorrectionLevel.M);
            QrCode qrCode = qrEncoder.Encode(url);
            //保存成png文件
            string filename = @"D:\WorkBench\url.png";
            GraphicsRenderer render = new GraphicsRenderer(new FixedModuleSize(5, QuietZoneModules.Two), Brushes.Black, Brushes.White);
            using (FileStream stream = new FileStream(filename, FileMode.Create))
            {
                render.WriteToStream(qrCode.Matrix, ImageFormat.Png, stream);
            }

            
           
            pictureBox1.Image = System.Drawing.Image.FromFile(@"D:\WorkBench\url.png");
           
           
        }

        private void button3_Click(object sender, EventArgs e)
        {
            MySqlConnection conn = new MySqlConnection(connetStr);

            try
            {
                conn.Open();//打开通道，建立连接，可能出现异常,使用try catch语句
                
                MySqlCommand mycmd = new MySqlCommand("select * from info", conn);
                mycmd.CommandType = CommandType.Text;
                MySqlDataReader sdr = mycmd.ExecuteReader();
                int i = 0;
                while (sdr.Read())
                {
                    listView1.Items.Add(sdr[0].ToString());
                    listView1.Items[i].SubItems.Add(sdr[1].ToString());
                    listView1.Items[i].SubItems.Add(sdr[2].ToString());
                    i++;
                }


            }
            catch (MySqlException ex)
            {
                //Console.WriteLine(ex.Message);
            }
            finally
            {
                conn.Close();
            }
        }

       
        private void bindListCiew()
        {
            this.listView1.Columns.Add("level");
            this.listView1.Columns.Add("time");
            this.listView1.Columns.Add("note");

            double totalColumnWidth = 6.0;  //1.0+2.0+1.0 = 4.0

            //设置第一列所占百分比
            double colPercentage0 = 0.8 / totalColumnWidth;
            listView1.Columns[0].Width = (int)(colPercentage0 * listView1.ClientRectangle.Width);

            //设置第二列所占百分比
            double colPercentage1 = 2.0 / totalColumnWidth;
            listView1.Columns[1].Width = (int)(colPercentage1 * listView1.ClientRectangle.Width);

            //设置第三列所占百分比
            double colPercentage2 = 3.0 / totalColumnWidth;
            listView1.Columns[2].Width = (int)(colPercentage2 * listView1.ClientRectangle.Width);   


            this.listView1.View = System.Windows.Forms.View.Details;

        }
    }


}
