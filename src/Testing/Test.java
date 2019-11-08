package Testing;
import Model.*;

/**
 * 
 * @author Vale09
 * @author Jacic
 * @author Mattu
 * @author Bob
 *
 */
public class Test {
    public static void main(String []args){
        LV camioncino = new LV("Ford","Fiesta","CR984RT", 100, 1.10,800);
        HV5 camion = new HV5("IVECO","GH77","KR94RFY",8000,1.80,8000);

        System.out.println("CAMIONCINO: \n" + camioncino.prinfInfo());
        System.out.println("CAMION: \n" + camion.prinfInfo());
        System.out.println( camioncino.getBrand());





    }
}
