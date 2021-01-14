//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//       Класс для работы со временем 
//        Содержит методы инициализации времени,
//          Обновление аптайма,
//          Получение аптайма и даты
//       
//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::






package uptime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;


import static uptime.File.*;
import static uptime.XLS.ControllerSave;
import static uptime.UpTime.*;

public class MTime {
    
    
    
    
//******************************************************************************   
    
   // Переменные для работы со временем 
    private static long UpT = ManagementFactory.getRuntimeMXBean().getUptime();
    private static Date date = new Date(UpT);
    private static DateFormat Form = new SimpleDateFormat("HH:mm:ss");
  
    private static String Time;
    
    public static long bufTime;
//******************************************************************************   
    
   
    
    
    
    
     
    
    // Ини программы и установка часового пояса
    public static void InitTime()
    {
       Reed();
       
       Form.setTimeZone(TimeZone.getTimeZone("UTC"));
       
    }
//------------------------------------------------------------------------------
    
    
    
    
    
  // Обновление аптайма . Должен работать постоянно
    public  static void   UpdateTime()
    {
        
        UpT = bufTime + ManagementFactory.getRuntimeMXBean().getUptime();
        date = new Date(UpT);
        
        Time = Form.format(date);
        
        }
//------------------------------------------------------------------------------


    
    
    // Получить текущий аптайм с учетом перерывов
    public static String  GetUptime()
    {
     
        UpT = bufTime + ManagementFactory.getRuntimeMXBean().getUptime();
        date = new Date(UpT);
        Time = Form.format(date);
        return Time;
    }
//------------------------------------------------------------------------------


    
    
    
    // Получить текущую дату
    public static String GetDate()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
        String str = dtf.format(localDate);
	
    
    
    return str; 
    }
//------------------------------------------------------------------------------
    
    
    
    
    
//==============================================================================    
    
}
