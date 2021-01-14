//
//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//            Класс  записи в  файл БД
//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//


package uptime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static uptime.MTime.*;




public class XLS
{

//******************************************************************************    
    
    private static FileReader fr ;
    private static FileWriter fw ;
    private static BufferedReader buf;
    
    
    // Файл - буфер для хранения даты и аптайма
    private static String name = "SaveDay.txt";
    // Файл БД 
    private static String name1 = "Report.txt";
    
    // Почему через полный путь в пакете - хз
    private static java.io.File f = new java.io.File(name1);
//******************************************************************************    
    
    
    
    
    
// Определение дня недели в текст формате
public static String WeekDay ()
{
        Calendar cal = new GregorianCalendar();

        String str ="";
        switch (cal.get(Calendar.DAY_OF_WEEK))
        {
            
            case 2:  str = "Понедельник"; break;
            case 3:  str = "Вторник"; break;
            case 4:  str = "Среда"; break;
            case 5:  str = "Четверг"; break;
            case 6:  str = "Пятница"; break;
            case 7:  str = "Суббота"; break;
            case 8:  str = "Воскресенье"; break;

        }
        return str;
}
//------------------------------------------------------------------------------        
    
    
    
    
    
    
    
   
    

   
    
    
    
    
    
// Формат записи в БД и сама запись    
    public static void saveFormDB(String uptime, boolean enter)
    {
    
try {
    
    
    
    
                
                fw = new FileWriter(f,true);
                if(enter)fw.write("\n --------------------------- \n");
                
                fw.write(GetDate()+"\t"+uptime+"\t"+WeekDay()+"\t");
                 
                fw.flush();
                fw.close();
                
                
                
         
                
        
        
    
                    
     } catch (IOException ex) {
       System.out.println("Error writte file");
     }
    
    
    
    }
//------------------------------------------------------------------------------        




    
    
// Контроллер с условиями записи в БД    
    public static void ControllerSave(long uptime1, Calendar lastDate  )

{
    // Перевод мсек в часы
        String uptime = ""+uptime1/1000/60/60;
        
        // Календари для удобного хранения дат и доступа к ним
        Calendar nextDate = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        
        
        
	String newDate = sdf.format(nextDate.getTime()); 
//	System.out.println(newDate); 

        
        String oldDate = sdf.format(lastDate.getTime()); 
//	System.out.println(oldDate); 

      

     
       
       
       
        
       if(nextDate.get(Calendar.YEAR)!=lastDate.get(Calendar.YEAR))
       {
           XLS.saveFormDB(uptime, true);
//           System.out.println("1");
       }
       
       
       if(nextDate.get(Calendar.YEAR)==lastDate.get(Calendar.YEAR)&&
          nextDate.get(Calendar.WEEK_OF_YEAR)==lastDate.get(Calendar.WEEK_OF_YEAR)
               && nextDate.get(Calendar.DAY_OF_MONTH)!=lastDate.get(Calendar.DAY_OF_MONTH)
         )  
       {
         XLS.saveFormDB(uptime, false);
//         System.out.println("2");
       }
       
       
           
        if(nextDate.get(Calendar.YEAR)==lastDate.get(Calendar.YEAR)&&
          nextDate.get(Calendar.WEEK_OF_YEAR)!=lastDate.get(Calendar.WEEK_OF_YEAR)
         )  
       {
         XLS.saveFormDB(uptime, true);
//         System.out.println("3");
       }  

}
//------------------------------------------------------------------------------        
    
     

//==============================================================================    
}
