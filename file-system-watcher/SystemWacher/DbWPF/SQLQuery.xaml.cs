
using System.Windows;
using System.Data.SQLite;
using System.IO;

namespace DbWPF
{
    /// <summary>
    /// Interaction logic for SQLQuery.xaml
    /// </summary>
    public partial class SQLQuery : Window
    {
        private static SQLiteConnection m_dbConnection;
        public SQLQuery()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            lstData.Items.Clear();
            if (!File.Exists("logdb.sqlite"))
                MessageBox.Show("Data base doesn't exits");
            else
            {
                m_dbConnection = new SQLiteConnection("Data Source=logdb.sqlite; Version=3");
                m_dbConnection.Open();
                if (txtWin2.Text.Equals("Directory"))
                {
                    string sql = "SELECT * FROM logdata WHERE ext = 'Directory';";
                    SQLiteCommand command = new SQLiteCommand(sql, m_dbConnection);
                    SQLiteDataReader data = command.ExecuteReader();

                    while (data.Read())
                        lstData.Items.Add(new { Action = data["action"], File = data["file"], Ext = data["ext"], Path = data["path"], Date = data["date"], Time = data["time"] });

                }
                else if (txtWin2.Text.Equals("*"))
                {

                    MessageBox.Show(txtWin2.Text);
                    string sql = "SELECT * FROM logdata;";
                    SQLiteCommand command = new SQLiteCommand(sql, m_dbConnection);
                    SQLiteDataReader data = command.ExecuteReader();

                    while (data.Read())
                        lstData.Items.Add(new { Action = data["action"], File = data["file"], Ext = data["ext"], Path = data["path"], Date = data["date"], Time = data["time"] });
                }
                else if (txtWin2.Text != "" && txtWin2.Text.Contains("."))
                {


                    string sql = "SELECT * FROM logdata WHERE ext = '" + txtWin2.Text.ToLower() + "';";
                    SQLiteCommand command = new SQLiteCommand(sql, m_dbConnection);
                    SQLiteDataReader data = command.ExecuteReader();

                    while (data.Read())
                        lstData.Items.Add(new { Action = data["action"], File = data["file"], Ext = data["ext"], Path = data["path"], Date = data["date"], Time = data["time"] });
                }
                else
                    MessageBox.Show("Incompatable data", "Error");
            }
        }
    }
}
