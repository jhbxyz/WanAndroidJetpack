package com.jhb.wanandroidjetpack.login.model;

import com.jhb.wanandroidjetpack.bean.BaseBean;

import java.util.List;

/**
 * Created by jhb on 2020-01-16.
 */
public class UserLoginBean extends BaseBean {

    public DataBean data;

    public static class DataBean {
        public boolean admin;
        public String email;
        public String icon;
        public int id;
        public String nickname;
        public String password;
        public String publicName;
        public String token;
        public int type;
        public String username;
        public List<?> chapterTops;
        public List<?> collectIds;
    }

}
