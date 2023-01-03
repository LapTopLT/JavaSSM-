import org.junit.Test;
import sun.util.BuddhistCalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class test {

    @Test
    public void test(){
        Date time = new Date();
        System.out.println(time);
        Calendar c = new GregorianCalendar();
        System.out.println(c.get(Calendar.DAY_OF_WEEK));
        System.out.println(c.get(Calendar.HOUR_OF_DAY));
    }
}
