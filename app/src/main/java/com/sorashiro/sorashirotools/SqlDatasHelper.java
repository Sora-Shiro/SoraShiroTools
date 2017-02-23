package com.sorashiro.sorashirotools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GameKing on 2016/5/27.
 */
public class SqlDatasHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "highscore.sqlite";
    public static final int VERSION = 1;

    private static final String TABLE_HIGHSCORE_LOCAL = "highscore_local";
    private static final String COLUMN_LOCAL_ID = "_id";
    private static final String COLUMN_LOCAL_SCORE = "score";
    private static final String COLUMN_LOCAL_NAME = "name";


    public SqlDatasHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库
        //一定要记得每个COLUMN后面的下一个字符串【首尾】都要加空格！
        //每列数据类型，多的话可以考虑跟列名一样，用String常量代替
        //id是主键，自增长
        db.execSQL(
                "create table " +
                TABLE_HIGHSCORE_LOCAL + " (" +
                COLUMN_LOCAL_ID + " integer not null primary key auto_increment, " +
                COLUMN_LOCAL_SCORE + " integer, " +
                COLUMN_LOCAL_NAME + " varchar(100))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 将数据插入某个Table
     * @param sqlDatas 封装好的数据
     * @return 新添数据列的行号，来自SQLiteDatabase.insert()方法
     */
    public long insertLocalHighScore(SqlDatas sqlDatas) {
        long id;
        //通过ContentValues插入数据
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOCAL_NAME, sqlDatas.getName());
        contentValues.put(COLUMN_LOCAL_SCORE,sqlDatas.getScore());
        id = getWritableDatabase().insert(TABLE_HIGHSCORE_LOCAL, null, contentValues);
        //每次插入完成便重新排序，可【自定义】
        orderLocalHighScore();
        return id;
    }

    //如果需要其他扩展函数也可以继续添加
    //注意SqlDatasManager类也需要添加相关方法

    /**
     * 排序Table的数据
     */
    public void orderLocalHighScore(){
        getWritableDatabase().
                execSQL("SELECT score, name FROM highscore_local ORDER BY score DESC");
    }

    /**
     * 通过新建一个Cursor并调用这个函数实例化Cursor得到整个Table的数据
     *
     * 通过调用moveToFirst()moveToNext()方法移动Cursor
     * 再用getColumnIndex()和getString()类似的方法
     * 可以查询每一行的每一列的数据
     *
     * 或者，通过调用moveToFirst()moveToNext()方法移动Cursor
     * 再通过调用下面的getDatas获取每一行的整体数据
     *
     * 具体如何设计要根据实际情况安排
     * 但很多时候更推荐使用后一种方法
     * 因为外界使用getColumnIndex()时还要标明SqlDatasHelper.COLUMN_LOCAL_ID，显得很麻烦
     *
     * @return 关联某个Table的Cursor
     */
    public DataBaseCursor queryDataBase(){
        Cursor wrapped = getReadableDatabase().query(TABLE_HIGHSCORE_LOCAL,
                null, null, null, null, null, COLUMN_LOCAL_SCORE + " desc");
        return new DataBaseCursor(wrapped);
    }

    //内置DataBaseCursor类
    public static class DataBaseCursor extends CursorWrapper {

        /**
         * Creates a cursor wrapper.
         *
         * @param cursor The underlying cursor to wrap.
         */
        public DataBaseCursor(Cursor cursor) {
            super(cursor);
        }

        /**
         * 获取每一行的【整体】数据，数据封装在SqlDatas类里
         *
         * @return 封装好的数据
         */
        public SqlDatas getDatas(){
            if (isBeforeFirst() || isAfterLast()) {
                return null;
            }
            SqlDatas sqlDatas = new SqlDatas();
            long lHSId = getLong(getColumnIndex(COLUMN_LOCAL_ID));
            sqlDatas.setId(lHSId);
            Integer score = getInt(getColumnIndex(COLUMN_LOCAL_SCORE));
            sqlDatas.setScore(score);
            String name = getString(getColumnIndex(COLUMN_LOCAL_NAME));
            sqlDatas.setName(name);
            return sqlDatas;
        }

    }

}
