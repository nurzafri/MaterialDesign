package com.example.tutorial.materialdesign.Config;

/**
 * Created by Home-PC on 18-Mar-17.
 */

public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_ADD="https://ukmandroidtutorial.000webhostapp.com/fcm/addEmp.php";
    public static final String URL_GET_ALL = "https://ukmandroidtutorial.000webhostapp.com/fcm/getAllEmp.php";
    public static final String URL_GET_EMP = "https://ukmandroidtutorial.000webhostapp.com/fcm/getEmp.php?id=";
    public static final String URL_UPDATE_EMP = "https://ukmandroidtutorial.000webhostapp.com/fcm/updateEmp.php";
    public static final String URL_DELETE_EMP = "https://ukmandroidtutorial.000webhostapp.com/fcm/deleteEmp.php?id=";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_DESG = "desg";
    public static final String KEY_EMP_SAL = "salary";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_DESG = "desg";
    public static final String TAG_SAL = "salary";

    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
}
