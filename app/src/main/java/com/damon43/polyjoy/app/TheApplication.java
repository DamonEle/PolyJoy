package com.damon43.polyjoy.app;

import android.database.sqlite.SQLiteDatabase;

import com.damon43.common.baseapp.BaseApplication;
import com.damon43.common.commonutils.LogUtils;
import com.damon43.polyjoy.db.DaoMaster;
import com.damon43.polyjoy.db.DaoSession;

/**
 * @author damonmasty
 *         Created on 下午4:04 17-1-29.
 */

public class TheApplication extends BaseApplication {

    private static DaoSession mSession;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.init(TheConstants.DEBUG_OPEN);
        setUpDatabase();
    }

    private void setUpDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "history", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        mSession = master.newSession();
    }

    public static DaoSession getDBSession() {
        return mSession;
    }
}
