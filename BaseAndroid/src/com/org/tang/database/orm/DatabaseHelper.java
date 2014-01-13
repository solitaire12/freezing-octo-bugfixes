package com.org.tang.database.orm;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.org.tang.database.entity.UserInfo;
//import android.database.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something
    // appropriate for your app
    private static final String DATABASE_NAME = "helloAndroid.db";
    // any time you make changes to your database objects, you may have to
    // increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    private Dao<UserInfo, Integer> userInfoDao = null;

    public DatabaseHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
	// TODO Auto-generated method stub
	try {
	    Log.i(DatabaseHelper.class.getName(), "onCreate");
	    TableUtils.createTable(connectionSource, UserInfo.class);
	} catch (SQLException e) {
	    Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
	    throw new RuntimeException(e);
	}

	// here we try inserting data in the on-create as a test
	// create some entries in the onCreate
	UserInfo simple = new UserInfo();
	simple.setId(0);
	simple.setPassword("123456");
	simple.setUserName("test001");
	try {
	    getUserInfoDao().create(simple);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2, int arg3) {
	try {
	    Log.i(DatabaseHelper.class.getName(), "onUpgrade");
	    TableUtils.dropTable(connectionSource, UserInfo.class, true);
	    // after we drop the old databases, we create the new ones
	    onCreate(arg0, connectionSource);
	} catch (SQLException e) {
	    Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
	    throw new RuntimeException(e);
	}
    }

    public Dao<UserInfo, Integer> getUserInfoDao() throws SQLException {
	if (userInfoDao == null) {
	    userInfoDao = getDao(UserInfo.class);
	}
	return userInfoDao;
    }

    @Override
    public void close() {
	super.close();
	userInfoDao = null;
    }

}
