//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//       
//        Класс работы с файлом для хранения перерывов
//        Содержит методы чтения/записи и очистки файла
//
//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::




package uptime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;




import static uptime.MTime.*;
import static uptime.UpTime.*;
import static uptime.XLS.ControllerSave;


 




public class File {
  
    
//******************************************************************************    
    
    private static java.io.File saveDay = new java.io.File("SaveDay.txt");
    private static FileReader fr ;
    private static FileWriter fw ;
    private static BufferedReader buf;
    
    
   public static  SimpleDateFormat format = new SimpleDateFormat("yyy/MM/dd");
//******************************************************************************    
    
    
    
    
    // Erase file
     private static void EraseFile()
    {
    
    try {
         fw = new FileWriter(saveDay);
         
         
         fw.write("");
         fw.flush();
         
         fw.close();
         
     } catch (IOException ex) {
                  System.out.println("Error writte file");

     }
    }
//------------------------------------------------------------------------------        
    
    
    
    
    
    
 // Save uptime   
    public static void Save()
    {
             try {
         fw = new FileWriter(saveDay);
         
         
         fw.write(GetDate()+"\n"+(bufTime + ManagementFactory.getRuntimeMXBean().getUptime()));
        
         fw.flush();
         
         fw.close();
         
         ControllerSave(lastUpTime,lastDate);
         
         
     } catch (IOException ex) {
                  System.out.println("Error writte file");

     }
    }    
//------------------------------------------------------------------------------        
    
    
    
    
    
    
    
// Reed uptime    и запись прошлого аптайма в файл, если есть разница по дате
     public static void Reed()
    {
        
        long time = 0;
        try {
         fr = new FileReader(saveDay);        // Потоки файла и буферизация
         buf = new BufferedReader(fr);
         
        String[] str= {"",""};
         
             str[0] = buf.readLine();
             str[1] = buf.readLine();
     
           
    if(str[0]==null) 
    {
        str[0] = "1971/01/01";
        str[1] = "0";
    
    }
   
    if(str[0].equals(GetDate()))
        {      
        
        
        bufTime =  Long.parseLong(str[1]);
        
        
        EraseFile();
            
        }
      
    
    
    
        else 
        {
             try {
                 
                 
                 lastDate.setTime(format.parse(str[0]));
                 lastUpTime = Long.parseLong(str[1]); 
                 
                 
                 
                 
             } catch (ParseException ex) {
                 System.out.println("Error parse last date");
             }
       
        }
        
       
     } catch (IOException ex) {
                  System.out.println("Файл или папка не найденны или не могут быть открыты");

     }
    
     
    }
//------------------------------------------------------------------------------        
    
    
    
    
}
