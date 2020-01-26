using System.Windows;

namespace DbWPF
{
    /// <summary>
    /// Interaction logic for Window1.xaml
    /// </summary>
    public partial class Extension : Window
    {
        public Extension()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            ((MainWindow)Owner).Extn = win1Txt.Text;
            this.Close();
        }
    }
}
