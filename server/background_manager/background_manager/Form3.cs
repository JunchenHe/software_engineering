using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace background_manager
{
    public partial class Form3 : Form
    {
        private Form2 form_parent;
        public Form3(Form2 parent)
        {
            form_parent = parent;
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string str = textBox1.Text;
            form_parent.get_form3_info(str);
            this.Close();
        }

        private void Form3_Load(object sender, EventArgs e)
        {

        }
    }
}
