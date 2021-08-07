package com.itest.baseapplication.Auth;

import java.util.Base64;
import java.util.Random;


public class RandomSalt {
    private static String s = genrateSalt();
    private static String genrateSalt(){
        int length = new Random().nextInt(40)+10;
        byte[] byt = new byte[length];

        new Random().nextBytes(byt);

        byte[] encoded = Base64.getEncoder().encode(byt);

        return new String(encoded);
    }

    public static void refreshSalt(){
        s=null;
        s=genrateSalt();
    }

    public static String getSalt(){

        return s;
    }
}
