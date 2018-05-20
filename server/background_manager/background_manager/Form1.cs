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
        protected string connetStr = "server=120.79.73.175;port=3306;user=soft;password=123; database=data;";
        protected static string url = "";

        public Form1()
        {
            
            InitializeComponent();
            bindListCiew();
        }

        //生成窗口2
        private void button1_Click(object sender, EventArgs e)
        {//link_to_sql_server

            Form2 from2 = new Form2();
            from2.Show();

        }

        //生成二维码
        private void button2_Click(object sender, EventArgs e)
        {
            if (url == "")
            {
                label1.Text = "您未选中任何数据！";
            }
            else
            {

                string file1 = "./url1.png";

                QrEncoder qrEncoder = new QrEncoder(ErrorCorrectionLevel.M);
                QrCode qrCode = qrEncoder.Encode(url);
                //保存成png文件

                GraphicsRenderer render = new GraphicsRenderer(new FixedModuleSize(5, QuietZoneModules.Two), Brushes.Black, Brushes.White);

                FileStream stream = new FileStream(file1, FileMode.Create);

                using (stream)
                {
                    render.WriteToStream(qrCode.Matrix, ImageFormat.Png, stream);
                }
                stream.Close();

                FileStream pFileStream = new FileStream(file1, FileMode.Open, FileAccess.Read);
                pictureBox1.Image = Image.FromStream(pFileStream);
                pFileStream.Close();
                pFileStream.Dispose();

            }

        }

        //查看message表的信息
        private void button3_Click(object sender, EventArgs e)
        {
            MySqlConnection conn = new MySqlConnection(connetStr);

            try
            {
                conn.Open();//打开通道，建立连接，可能出现异常,使用try catch语句

                MySqlCommand mycmd = new MySqlCommand("select * from message", conn);
                mycmd.CommandType = CommandType.Text;
                MySqlDataReader sdr = mycmd.ExecuteReader();
                int i = 0;
                listView1.Items.Clear();//清空数据

                string pre_time = null;
                while (sdr.Read())
                {

                    if (i == 0)
                        pre_time = sdr[1].ToString();

                    if (pre_time != sdr[1].ToString())
                    {
                        listView1.Items.Add("");
                        i++;
                    }

                    listView1.Items.Add(sdr[0].ToString());
                    listView1.Items[i].SubItems.Add(sdr[1].ToString());
                    listView1.Items[i].SubItems.Add(sdr[2].ToString());
                    i++;

                    

                    pre_time = sdr[1].ToString();

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

        //选中新闻
        private void listView1_SelectedIndexChanged(object sender, EventArgs e)
        {
            int selectCount = listView1.SelectedItems.Count; //SelectedItems.Count就是：取得值，表示SelectedItems集合的物件数目。 
            if (selectCount > 0)//若selectCount大於0，说明用户有选中某列。
            {

                if (listView1.SelectedItems[0].SubItems[0].Text=="")
                {
                    label1.Text = "无效选择！";
                }
                else
                {
                    label1.Text = "已选中: " + listView1.SelectedItems[0].SubItems[1].Text + " 的新闻";
                    url = label1.Text;
                }
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }
    
    }


}
