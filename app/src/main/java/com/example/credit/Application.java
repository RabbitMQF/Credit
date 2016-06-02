package com.example.credit;

import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import java.io.File;

/**
 * 系统的Applicationg的继承
 * Created by alucard on 2016/5/14.
 */
public class Application extends android.app.Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        NoHttp.initialize(this);
        //打开Nohttp的调试模式
        Logger.setDebug(true);
        //设置Nohttp的日志tag
        Logger.setTag("forNoHttp");
    }


    /**
     * 得到应用程序的application
     *
     * @return {@link Application}
     */
    public static Application getInstance() {
        return instance;
    }
}
