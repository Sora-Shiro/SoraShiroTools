package com.sorashiro.sorashirotools;

import android.content.Context;

/**
 * @author Sora
 * @date 2016.5.27
 *
 * Sql util.
 * Should be improved, not recommended for use.
 * SQL工具类。
 * 不推荐使用，今后会完善。
 *
 */

/**
 * 这个类是SqlDatasHelper类的抽象封装
 * 其他类要操作数据的时候最好调用这个类的getSqlDatasManager方法
 * 查询单行数据的时候推荐用queryDatas()后
 * 再用其返回的SqlDatasHelper.DataBaseCursor的getDatas()方法
 *
 * 具体实现交给SqlDatasHelper做
 */
public class SqlDatasManager {

    private static SqlDatasManager sDataBaseManager;

    private SqlDatasHelper mSqlDatasHelper;

    public SqlDatasManager(Context context) {
        mSqlDatasHelper = new SqlDatasHelper(context);
        mSqlDatasHelper.getWritableDatabase();
    }

    public long insertDatas(SqlDatas sqlDatas) {
        return mSqlDatasHelper.insertLocalHighScore(sqlDatas);
    }


    public SqlDatasHelper.DataBaseCursor queryDatas() {
        return mSqlDatasHelper.queryDataBase();
    }


    public static SqlDatasManager getSqlDatasManager(Context c) {
        if (sDataBaseManager == null) {
            // we use the application context to avoid leaking activities
            sDataBaseManager = new SqlDatasManager(c.getApplicationContext());
        }
        return sDataBaseManager;
    }
}
