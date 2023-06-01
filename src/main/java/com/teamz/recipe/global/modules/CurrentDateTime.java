package com.teamz.recipe.global.modules;

import org.apache.commons.logging.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateTime {
    public String getCurrentDateTime(){
        Date now = new Date();

        System.out.println(now);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(now);
    }
}
