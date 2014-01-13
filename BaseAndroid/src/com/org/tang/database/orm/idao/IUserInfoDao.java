package com.org.tang.database.orm.idao;

import java.util.List;

import com.org.tang.database.entity.UserInfo;

public interface IUserInfoDao {
    UserInfo findUserInfoById(int id);

    List<UserInfo> findUserInfoByUserName(String userName);

}
