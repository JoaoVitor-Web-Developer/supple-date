import br.com.dld.suppledate.Shape;
import br.com.dld.suppledate.SuppleDate;

/**
 * @author David Duarte
 * @created 17/08/2022
 * @project supple-date
 */
public class MainTest {

    public static void main(String[] args) {

        String date1 = SuppleDate
                .of(Long.toString(20220815L), Shape.BASIC_D4_ISO)
                .format("dd/MM/yyyy HH:mm:ss")
                .toString();

        System.out.println(date1);

        String date2 = SuppleDate
                .of(Long.toString(20220815232157L), "yyyyMMddHHmmss")
                .format(Shape.BAR_D4HMS)
                .toString();

        System.out.println(date2);
    }
}
