//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//       Класс выполнения Tray - menu
//            Содержит обработку 2 кнопок : открыть БД и Выход
//       
//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


package uptime;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;
import static uptime.File.Save;
import static uptime.UpTime.frame;
import static uptime.UpTime.initFrame;


public class Tray {
    
   public Tray(){ 
     // Проверка поддержики трея в ОС  
    if(!SystemTray.isSupported()){
        System.out.println("System tray is not supported !!! ");
        return ;
    }
//______________________________________________________________________________    

    SystemTray systemTray = SystemTray.getSystemTray(); 
    Image image = Toolkit.getDefaultToolkit().getImage("UpT.gif");

    //popupmenu
    PopupMenu trayPopupMenu = new PopupMenu();
       
    //1t menuitem for popupmenu
    MenuItem action = new MenuItem("Открыть меню");
    
    action.addActionListener(new ActionListener() {
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            initFrame();
        }
    });    
//______________________________________________________________________________    
    
    trayPopupMenu.add(action);

    //Кнопка выход инит+ листенер
    MenuItem close = new MenuItem("Выход");
    close.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                     
            System.out.println("Exit.............");
            
            Save();
            System.exit(0);   
         
        }
    });
//______________________________________________________________________________    
    

   Runtime.getRuntime().addShutdownHook(new Thread()
{
    @Override
    public void run()
    {
        Save();
    }
});

    trayPopupMenu.add(close);

    //setting tray icon
    TrayIcon trayIcon = new TrayIcon(image, "Uptime Widget", trayPopupMenu);
    //adjust to default size as per system recommendation 
    trayIcon.setImageAutoSize(true);

    try{
        systemTray.add(trayIcon);
    }catch(AWTException awtException){
        awtException.printStackTrace();
    }
    System.out.println("end of main");
  
   } 
//------------------------------------------------------------------------------
    
    
//==============================================================================    
    
}
