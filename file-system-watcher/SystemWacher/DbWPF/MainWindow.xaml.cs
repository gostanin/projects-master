/*
 * 
 * Author: Grigory Ostanin
 * 
 * System Watcher with GUI. Watch the system files by extensions with ability to save it to db or text file
*/
using System;
using System.Windows;
using WinForms = System.Windows.Forms;
using Microsoft.Win32; //FileDialog 
using System.Windows.Controls;
using System.Windows.Media;
using System.Security.Permissions;
using System.IO;
using System.Data.SQLite;
using System.Windows.Threading;

namespace DbWPF
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private static String sPath;
        private static SQLiteConnection m_dbConnection;
        private FileSystemWatcher watcher;
        private string extWatch;
        private Extension extWin;
        private SQLQuery sqlWin;

        private static string pathforfile = @"./logfile.txt";
        private static string db = @"./logdb.sqlite";
        private static string pathtofolder = "watcher\\bin\\Debug";

        public string Extn { get; set; }
        public MainWindow()
        {
            InitializeComponent();
            dmExt.Background = Brushes.Black;
        }

        
        private void Introduction()
        {
            MessageBox.Show("Choose a directory", "Frist steps 1 of 10", MessageBoxButton.OK);
        }
        private void btnBrowse_Click(object sender, RoutedEventArgs e)
        {
            WinForms.FolderBrowserDialog folderDialog = new WinForms.FolderBrowserDialog();
            folderDialog.ShowNewFolderButton = false;
            folderDialog.SelectedPath = System.AppDomain.CurrentDomain.BaseDirectory;
            WinForms.DialogResult result = folderDialog.ShowDialog();

            if (result == WinForms.DialogResult.OK)
            {
                //----< Selected Folder >---- 
                //< Selected Path > 
                sPath = folderDialog.SelectedPath;
                FilePath.Text = sPath;
                FilePath.Foreground = new SolidColorBrush(Colors.White);
            }

        }

        private void FilePath_GotFocus(object sender, RoutedEventArgs e)
        {
            if (FilePath.Text.CompareTo("DIRECTORY") == 0)
                FilePath.Text = "";
            FilePath.Foreground = new SolidColorBrush(Colors.White);
        }

        private void btnStart_Click(object sender, RoutedEventArgs e)
        {
            if (!Directory.Exists(FilePath.Text))
                MessageBox.Show("Incorect path", "Path Error");
            else
            {
                sPath = FilePath.Text;
                if (sPath != null)
                {
                    Watch();

                    btnStart.IsEnabled = false;
                    FilePath.IsEnabled = false;
                    btnBrowse.IsEnabled = false;
                    dmExt.IsEnabled = false;
                }
                else
                {
                    MessageBox.Show("Choose a path to the directory", "Cannot start watching");
                }

            }
        }
        [PermissionSet(SecurityAction.Demand, Name = "FullTrust")]
        public void Watch()
        {
            if (Directory.Exists(@sPath))
                watcher = new FileSystemWatcher(@sPath);
            else
                MessageBox.Show("Incorrect path", "Path error");
            watcher.IncludeSubdirectories = true;
            watcher.NotifyFilter = NotifyFilters.LastAccess | NotifyFilters.LastWrite
          | NotifyFilters.FileName | NotifyFilters.DirectoryName;
            watcher.EnableRaisingEvents = true;
            
                watcher.Filter = extWatch;

            watcher.Changed += OnChanged;
            watcher.Created += OnChanged;
            watcher.Deleted += OnChanged;
            watcher.Renamed += OnRenamed;

            SQLconnection();
        }
        public class MyData
        {
            public string Action { get; set; }
            public string File { get; set; }
            public string Ext { get; set; }
            public string Path { get; set; }
            public string Date { get; set; } 
            public string Time { get; set; }

        }
        private void OnRenamed(object sender, RenamedEventArgs e)
        {
            if (e.OldFullPath.Contains(pathforfile.Remove(0, 2)))
                pathforfile = e.FullPath;
            if (!e.FullPath.Contains(pathforfile.Remove(0, 2)) && !e.FullPath.Contains(db.Remove(0, 2)) && !e.FullPath.Contains(pathtofolder))
            {
                int InExt = e.FullPath.LastIndexOf(".");
                string ext;
                if (InExt >= 0)
                    ext = e.FullPath.Substring(InExt);
                else
                    ext = "Directory";
                int InTime = DateTime.Now.ToString().IndexOf(" ");
                string date = DateTime.Now.ToString().Substring(InTime + 1);
                string time = DateTime.Now.ToString().Substring(0, InTime);
                Dispatcher.BeginInvoke(
               (Action)(() =>
               {
                   this.lstData.Items.Add(new MyData{ Action = e.ChangeType.ToString(), File = e.Name, Ext = ext, Path = e.FullPath, Date = date, Time = time });
               }));

            }
        }

        private void OnChanged(object sender, FileSystemEventArgs e)
        {
            if (!e.FullPath.Contains(pathforfile.Remove(0, 2)) && !e.FullPath.Contains(db.Remove(0, 2)) && !e.FullPath.Contains(pathtofolder))
            {
                int InExt = e.FullPath.LastIndexOf(".");
                string ext;
                if (InExt >= 0)
                    ext = e.FullPath.Substring(InExt);
                else
                    ext = "Directory";
                int InTime = DateTime.Now.ToString().IndexOf(" ");
                string date = DateTime.Now.ToString().Substring(InTime + 1);
                string time = DateTime.Now.ToString().Substring(0, InTime);
                Dispatcher.BeginInvoke(
               (Action)(() =>
               {
                   this.lstData.Items.Add(new MyData { Action = e.ChangeType.ToString(), File = e.Name, Ext = ext, Path = e.FullPath, Date = date, Time = time });
               }));

            }
        }

        private static void SQLconnection()
        {
            if (!File.Exists("logdb.sqlite"))
                SQLiteConnection.CreateFile("logdb.sqlite");

            m_dbConnection = new SQLiteConnection("Data Source=logdb.sqlite; Version=3");
            m_dbConnection.Open();
            string sql = "CREATE TABLE if not exists logdata (id INTEGER PRIMARY KEY AUTOINCREMENT, action VARCHAR(20), file VARCHAR(100), ext VARCHAR(100), path VARCHAR(200), date VARCHAR(100), time VARCHAR(100))";
            SQLiteCommand command = new SQLiteCommand(sql, m_dbConnection);
            command.ExecuteNonQuery();
            

        }

        private static void SQLadd(string action, string file, string ext, string path, string date, string time)
        {
            string sql = "INSERT INTO logdata (action, file, ext, path, date, time) values ('" + action + "', '" + file + "', '" + ext + "', '" + path + "', '" + date + "', '" + time + "')";
            SQLiteCommand command = new SQLiteCommand(sql, m_dbConnection);
            command.ExecuteNonQuery();
        }

        private void btnStop_Click(object sender, RoutedEventArgs e)
        {
            if (watcher != null)
                watcher.EnableRaisingEvents = false;
            else
                MessageBox.Show("Watcher is not running\nStart watcher first", "Error");
            btnStart.IsEnabled = true;
            FilePath.IsEnabled = true;
            btnBrowse.IsEnabled = true;
            dmExt.IsEnabled = true;
        }

        private void MainWindow1_ContentRendered(object sender, EventArgs e)
        {
            //Introduction();
            dmExt.SelectedIndex = 0;
        }

        private void btnSql_Click(object sender, RoutedEventArgs e)
        {
           
            for (int i = 0; i < lstData.Items.Count; i++)
            {
                MyData temp = (MyData)lstData.Items[i];

                SQLadd(temp.Action, temp.File, temp.Ext, temp.Path, temp.Date, temp.Time);

            }

            MessageBox.Show("Added to logfile database", "Added");
            lstData.Items.Clear();
        }

        private void FilePath_DataContextChanged(object sender, DependencyPropertyChangedEventArgs e)
        {

            if (Directory.Exists(e.NewValue.ToString()))
                sPath = e.NewValue.ToString();
            else
                MessageBox.Show("Inncorect path", "Path error");
        }

        private void dmExt_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (dmExt.SelectedItem.ToString().Contains("Add"))
            {
                extWin = new Extension();
                extWin.Owner = this;
                extWin.ShowDialog();
                if (!Extn.Contains("."))
                    MessageBox.Show("Incompatble extension", "Extension error");
                else
                {
                    dmExt.Items.Insert(dmExt.Items.Count - 1, Extn.ToUpper());
                    dmExt.SelectedIndex = dmExt.Items.Count - 1;
                }
            }
            else if (dmExt.SelectedItem.ToString().Contains("Extension"))
                extWatch = "*";
            else
            {
                int extInt = dmExt.SelectedItem.ToString().LastIndexOf(".");
                if (extInt > 0)
                {
                    string exten = dmExt.SelectedItem.ToString().Substring(extInt);
                    extWatch = "*" + exten.ToLower();

                }
                else
                    MessageBox.Show("Incompatble extension", "Extension error");
            }
        }

        private void btnSave_Click(object sender, RoutedEventArgs e)
        {
            for (int i = 0; i < lstData.Items.Count; i++)
            {
                MyData temp = (MyData)lstData.Items[i];

                WriteLog(temp.Action, temp.File, temp.Ext, temp.Path, temp.Date, temp.Time);

            }
            MessageBox.Show("Saved to logfile.txt", "Saved");
            lstData.Items.Clear();
        }


        private static void WriteLog(string action, string file, string ext, string path, string date, string time)
        {
            if (!File.Exists(pathforfile))
            {
                using (StreamWriter sw = File.CreateText(pathforfile))
                {
                    //sw.WriteLine(data);
                }
            }
            using (StreamWriter sw = File.AppendText(pathforfile))
            {
                sw.WriteLine(action + " " + file + " " + ext + " " + path + " " + date + " " + time + " ");
            }
        }

        private void btnQuery_Click(object sender, RoutedEventArgs e)
        {
            sqlWin = new SQLQuery();
            sqlWin.ShowDialog();
        }

        private void MainWindow1_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            
        }

        private void MenuItem_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("System Watcher V 0.001\nWatch the system for changing by specified extention\n\nMicrosoft .Net Framework\nVersion 4.6.1\n© Grigory Ostanin, 2018\nAll rights reserved", "About System Watcher");
        }
    }
}
