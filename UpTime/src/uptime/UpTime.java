//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//       Мэйн класс
//            Содержит методы инициализации фрайма программы
//            Метоы Run и обработки кнопок фрайма
//
//          http://java-online.ru/java-listener.xhtml
//       
//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
package uptime;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static uptime.MTime.*;
import static uptime.File.*;


public class UpTime {
  
//******************************************************************************   
    
     // последняя дата  и время из saveDay
    public static Calendar lastDate = new GregorianCalendar(); 
    public static long lastUpTime =0 ; 
    public static JFrame frame= new JFrame("UpTime widget");
    public static JButton btn = new JButton("Open DB file");
    public static JTextField tf = new JTextField("  UpTime:");
//******************************************************************************   
   

    // Общий инит программы
    public static void init()
    {
         InitTime();    // Установки времени и аптайма         
         initFrame();        
    }

  
    public static void initFrame()
    {
        ImageIcon icon = new ImageIcon("UpT.gif");
        frame.setIconImage(icon.getImage());
        frame.setSize(300, 100);
        tf.setColumns(20);
        frame.setLayout(new FlowLayout());
        frame.add(btn);
        frame.add(tf);
       
 // Кнопка открытия файла БД через CMD консоль       
 btn.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e) 
            {
                  try {      
            Process run = Runtime.getRuntime().exec("notepad "  +  new java.io.File("").getAbsolutePath()+"\\Report.txt");
                      
        } catch (IOException ex) {
            System.out.println("Err open file with notepad");
        }
            }
 }); 
        

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
  
    // Циклическое выполнение программы
    public static void run()
    {
        Tray run = new Tray();
            while(true)
            {
                
            try {
                
                // Обновление времени на экране
                tf.setText("       UpTime:    "+GetUptime());
           
               Thread.sleep(500); // Задержка
               
            } catch (InterruptedException ex) {
                System.out.println("err with sleep");
            }   
            }
    }

    public static void main(String[] args) {  
       init();
       run();        
    }

//==============================================================================    
}
