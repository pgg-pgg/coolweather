package com.bignerdranch.andriod.activitytest.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PDD on 2017/3/7.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {





    /**
     * Province建表语句，在model中有对应Province实体类
     */
    public static final String CREATE_PROVINCE="create table Province("
            +"id integer primary key autoincrement,"
            +"province_name text,"
            +"province_code text)";


    /**
     * City建表语句，在model中有对应City类
     */

    public static final String CREATE_CITY="create table City("
            +"id integer primary key autoincrement,"
            +"city_name text,"
            +"city_code text)";

    /**
     * County建表语句，在model中有对应County类
     */

    public static final String CREATE_COUNTY="create table County("
            +"id integer primary key autoincrement,"
            +"county_name text,"
            +"county_code text)";

    /**
     * 构造方法，
     */
    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 实现onCreate回调方法，在其中创建三个表
     */

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
