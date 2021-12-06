package sample.util;


import sample.model.Vare;

import java.util.ArrayList;

public class AntikkUtil {
    public static int getTrueVareArrayStartIndex(int side, int numberOfElements){
        return (side - 1) * numberOfElements;
    }

    public static ArrayList<Vare> GetVareMedButikkNavn(ArrayList<Vare> vareListe, String butikkNavn){

        for (int i = 0; i < vareListe.size(); i++) {
            if (!vareListe.get(i).getButikk().equals(butikkNavn)) {
                vareListe.remove(i);
                i--;
            }
        }
        return vareListe;
    }
}
