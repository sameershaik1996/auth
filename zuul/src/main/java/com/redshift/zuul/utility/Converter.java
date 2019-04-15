package com.redshift.zuul.utility;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public  class Converter {

    public static <E> E convertToObject(E res,String s){
        try {
            byte b[] = s.getBytes();
            ByteArrayInputStream bi = new ByteArrayInputStream(b);
            ObjectInputStream si = new ObjectInputStream(bi);
             res = (E) si.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }
         return res;
    }


}
