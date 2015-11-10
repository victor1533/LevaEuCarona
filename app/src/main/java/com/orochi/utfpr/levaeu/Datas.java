package com.orochi.utfpr.levaeu;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Poisson on 10/11/2015.
 */
public class Datas {
    public static Date HHmmToDate(String data){
        Date date;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            date = sdf.parse(data);

        }catch(Exception ex){
            System.out.println("Erro: " + ex);
            return null;
        }
        return date;
    }

    public static String DateToHHmm(Date data){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(data);

        }catch(Exception ex){
            System.out.println("Erro: " + ex);
            return null;
        }
    }


    public static Date Agora(){
        Date date = new Date();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            date = sdf.parse(DateToHHmm(date));

        }catch(Exception ex){
            System.out.println("Erro: " + ex);
            return null;
        }
        return date   ;
    }
    public static Date ddMMyyyyToDate(String data){
        Date date;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            date = sdf.parse(data);

        }catch(Exception ex){
            System.out.println("Erro: " + ex);
            return null;
        }
        return date;
    }

    public static Date ddMMyyyyHHmmToDate(String data){
        Date date;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            date = sdf.parse(data);

        }catch(Exception ex){
            System.out.println("Erro: " + ex);
            return null;
        }
        return date;
    }


    public static String DateToddMMyyyy(Date data){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(data);
        }catch(Exception ex){
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public static String DateToyyyyMMdd(Date data){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(data);
        }catch(Exception ex){
            System.out.println("Erro: " + ex);
            return null;
        }
    }

    public static Date yyyyMMddToDate(String data){
        Date date;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(data);

        }catch(Exception ex){
            System.out.println("Erro: " + ex);
            return null;
        }
        return date;
    }

}
