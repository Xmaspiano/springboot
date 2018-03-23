import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author XmasPiano
 * @date 2018/3/14 - 下午4:02
 * Created by IntelliJ IDEA.
 */
public class intex {

    public static void main(String[] args){
        List<vo> a = new ArrayList();
        a.add(new vo(1,2));
        a.add(new vo(3,4));
        a.add(new vo(5,6));

        Map b = new HashMap();
        for(vo at:a){
            b.put(at.ab,at);
        }
        a.get(0).ab=99;
        System.out.println(b);
    }
}
class vo{
    int ab;
    int cd;

    vo(int a, int b){
        ab = a;
        cd = b;
    }

    public String toString(){
        return "[ab="+ab+"; cd="+cd+"]";
    }
}
