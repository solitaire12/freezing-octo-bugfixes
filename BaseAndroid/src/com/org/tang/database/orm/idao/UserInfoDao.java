package com.org.tang.database.orm.idao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.org.tang.database.entity.UserInfo;
import com.org.tang.database.orm.DatabaseHelper;

public class UserInfoDao extends OpenHelperManager implements IUserInfoDao {

    // private DatabaseHelper helper;
    private Dao<UserInfo, Integer> userDao;

    public UserInfoDao(Context context) {
	DatabaseHelper helper = getHelper(context, DatabaseHelper.class);
	try {
	    userDao = helper.getUserInfoDao();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public UserInfo findUserInfoById(int id) {
	// TODO Auto-generated method stub
	UserInfo userInfo = null;
	try {
	    if (userDao != null) {
		userInfo = userDao.queryForId(id);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return userInfo;
    }

    @Override
    public List<UserInfo> findUserInfoByUserName(String userName) {
	// TODO Auto-generated method stub
	List<UserInfo> infos = new ArrayList<UserInfo>();
	try {
	    if (userDao != null) {
//		infos = userDao.queryForFieldValuesArgs(userName);
		infos = userDao.queryForAll();
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

}
