package Controller;
import org.jetbrains.annotations.Contract;

import java.sql.*;

/**
 *  VarGetter recover variables from DB
 */
public class VarGetter {

    /**
     * getSurcharge recover surgcharge from DB
     * @param vclass
     * @return double
     */
    @Contract(pure = true)
    public static double getSurcharge(String vclass){
        String var="SUR"+vclass;
        return Constant.getVar(var);

    }

    /**
     * getIVA recover IVA tax from DB
     *
     * @return double
     */
    @Contract(pure = true)
    public static double getIVA(){

        return Constant.getVar("IVA");

    }



}
