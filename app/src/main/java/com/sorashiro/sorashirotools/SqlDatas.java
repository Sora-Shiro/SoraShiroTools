package com.sorashiro.sorashirotools;

/**
 * @author Sora
 * @date 2016.5.27
 * <p>
 * Sql util.
 * Should be improved, not recommended for use.
 * SQL工具类。
 * 不推荐使用，今后会完善。
 */

public class SqlDatas {

    //在这里设定SQL里每一行的基本数据
    //没什么好说的，尽量跟SqlDatasHelper里的列名保持一致即可

    private long mId;
    private Integer Score;
    private String  Name;

    public SqlDatas(){

    }

    public SqlDatas(Integer score, String name){
        mId = -1;
        this.Score = score;
        this.Name = name;
    }

    //设置getter和setter

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
