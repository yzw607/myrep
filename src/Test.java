import java.text.SimpleDateFormat;
import java.util.Date;


public class Test
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
//        System.out.println(200 / 60);
//        
//        String bDate = "2009-04-02 06:30"; 
//        String eDate = "2009-04-02 07:30";
//        
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//
//        try
//        {
//            Date bdate = formatter.parse(bDate);
//            Date edate = formatter.parse(eDate);
//            
//            long begin = (bdate.getTime() / 1000) ;   //此时单位为秒，bdate为上面的Date对象 
//            long end = (edate.getTime() / 1000) ;     //edate.getTime()获得的时间以毫秒为单位
//            
//            System.out.println((end - begin) / 60);
//        }
//        catch (ParseException e)
//        {
//            e.printStackTrace();
//        } 
//        
//        String dateString = formatter.format(new Date());
//        System.out.println(dateString);
//        
//        System.out.println(5 / 10);
//        System.out.println(13 % 10);
        
//        String a = "000杨峙凌";
//        System.out.println(a.startsWith("000"));
//        System.out.println(a.substring(3, a.length()));
        
//        System.out.println("yyyy-MM-dd HH:mm:ss".substring(11,13));
        
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date startDate;
//        Date endDate;
//        try
//        {
//            startDate = df.parse("2012-05-26 23:00:00");
//            endDate = df.parse("2012-05-26 09:00:00");
//            long l=endDate.getTime()-startDate.getTime()+86400000;
//            
//            System.out.println(String.valueOf(l));
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        
//        Date currentTime = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = formatter.format(currentTime);
//        System.out.println(dateString);
        
        
//        String str = null;;
//        try
//        {
//            BufferedReader br=new BufferedReader(new FileReader("D:\\workspace\\wmp\\WebRoot\\Mms-in\\052522385692700101251\\text_0001.txt"));
//            str = "";
//            String r=br.readLine();
//            
//            while(r!=null)
//            {
//                str+=r;
//                r=br.readLine();
//            }
//        }
//        catch (FileNotFoundException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//        System.out.println(str);


//        String test = "\\Mms-in\\052522344792700101133\\IMG_4560.jpg";
//        int t = test.indexOf("WebRoot");
//        System.out.println(test.replaceAll("\\\\", "/"));
//        System.out.println(test.substring(t + 7, test.length() - 1));
        
        
        String a  = "???ggeerer";
        System.out.println(a.replaceAll("//?", "？"));
        System.out.println(a.replaceAll("\\?", "？"));
        
        
    }

}
