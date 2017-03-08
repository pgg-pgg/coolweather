package com.bignerdranch.andriod.activitytest.coolweather.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import com.bignerdranch.andriod.activitytest.coolweather.db.CoolWeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PDD on 2017/3/8.
 *用于对数据库的操作的集合
 */
public class CoolWeatherDb {

    /**
     * 数据库名称
     */
    public static final String DB_NAME="cool_weather";

    /**
     * 数据库版本
     */

    public static final int VERSION=1;

    private static CoolWeatherDb coolWeatherDb;
    private SQLiteDatabase db;

    /**
     * 将构造方法私有化
     */

    private CoolWeatherDb(Context context){
        CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(context,DB_NAME,null, VERSION);
        db=dbHelper.getWritableDatabase();//取得一个写数据库的实例
    }

    /**
     * 获取CoolWeather的实例
     */

    public synchronized static CoolWeatherDb getInstance(Context context){
        if (coolWeatherDb==null){
            coolWeatherDb=new CoolWeatherDb(context);
        }
        return coolWeatherDb;
    }

    /**
     * 将Province实例存储到数据库
     */

    public void saveProvince(Province province){
        if (province!=null){
            //contentValues和Hashtable很像，都是都是存储一个键值对，不过这个键值对一个是String类型，一个是值类型
            ContentValues values=new ContentValues();//实例化一个ContentValues对象，用于存储数据库数据
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }

    /**
     * 从数据库中读取全国所有省份的信息
     */

    public List<Province> loadProvince(){
        List<Province> list=new ArrayList<Province>();
        Cursor cursor=db.query("Province",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                Province province=new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将City存储到数据库中
     */

    public void saveCity(City city){
        if (city!=null){
            ContentValues values=new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }

    /**
     * 从数据库中读取某省的所有城市
     */

    public List<City> loadCities(int provinceId){
        List<City> list=new ArrayList<City>();
        Cursor cursor=db.query("City",null,"province_id=?",new String[]{String.valueOf(provinceId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                City city=new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * 将County实例存储到数据库
     */

    public void saveCounty(County county){
        if (county!=null){
            ContentValues values=new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            values.put("city_id",county.getCityId());
            db.insert("County",null,values);
        }
    }

    /**
     * 从数据库中读取某城市下的所有县信息
     */

    public List<County> loadCounties(int cityId){
        List<County> list=new ArrayList<County>();
        Cursor cursor=db.query("County",null,"city_id=?",new String[]{String.valueOf(cityId)},null,null,null);
        if (cursor.moveToFirst()){
            do{
                County county=new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCityId(cityId);
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
