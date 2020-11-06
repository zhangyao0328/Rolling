package com.rolling.bean.home;

import com.rolling.bean.BaseDataBean;

import java.util.List;

/**
 * @author zhangyao
 * @date 2020/11/4  12:24
 * @E-mail android_n@163.com
 */
public class HomeDataBean extends BaseDataBean {



    /**
     * totalCount : 25
     * userList : [{"Id":1,"Name":"2020 凯乐石莫干山跑山赛","Cover":"https://pic3.iranshao.com/photo/image/WechatIMG72-28486792b2c15ef7db694805a3bc3283.jpeg!80x80","Link":"http://iranshao.com/races/9272#banner_id=pr0210100","Address":"浙江省 湖州市 德清县 莫干山镇 庾村","Follow":"1021 人关注","Type":"报名中","StartDate":1604102400,"DeadLine":"2020-03-18 ~ 2020-07-31","CreatedAt":"2020-03-22T12:32:50+08:00","UpdatedAt":"2020-03-22T12:33:33+08:00"}]
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int totalCount;
        /**
         * Id : 1
         * Name : 2020 凯乐石莫干山跑山赛
         * Cover : https://pic3.iranshao.com/photo/image/WechatIMG72-28486792b2c15ef7db694805a3bc3283.jpeg!80x80
         * Link : http://iranshao.com/races/9272#banner_id=pr0210100
         * Address : 浙江省 湖州市 德清县 莫干山镇 庾村
         * Follow : 1021 人关注
         * Type : 报名中
         * StartDate : 1604102400
         * DeadLine : 2020-03-18 ~ 2020-07-31
         * CreatedAt : 2020-03-22T12:32:50+08:00
         * UpdatedAt : 2020-03-22T12:33:33+08:00
         */

        private List<UserListBean> userList;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<UserListBean> getUserList() {
            return userList;
        }

        public void setUserList(List<UserListBean> userList) {
            this.userList = userList;
        }

        public static class UserListBean {
            private int Id;
            private String Name;
            private String Cover;
            private String Link;
            private String Address;
            private String Follow;
            private String Type;
            private int StartDate;
            private String DeadLine;
            private String CreatedAt;
            private String UpdatedAt;
            private int viewType;

            public int getViewType() {
                return viewType;
            }

            public void setViewType(int viewType) {
                this.viewType = viewType;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getCover() {
                return Cover;
            }

            public void setCover(String Cover) {
                this.Cover = Cover;
            }

            public String getLink() {
                return Link;
            }

            public void setLink(String Link) {
                this.Link = Link;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public String getFollow() {
                return Follow;
            }

            public void setFollow(String Follow) {
                this.Follow = Follow;
            }

            public String getType() {
                return Type;
            }

            public void setType(String Type) {
                this.Type = Type;
            }

            public int getStartDate() {
                return StartDate;
            }

            public void setStartDate(int StartDate) {
                this.StartDate = StartDate;
            }

            public String getDeadLine() {
                return DeadLine;
            }

            public void setDeadLine(String DeadLine) {
                this.DeadLine = DeadLine;
            }

            public String getCreatedAt() {
                return CreatedAt;
            }

            public void setCreatedAt(String CreatedAt) {
                this.CreatedAt = CreatedAt;
            }

            public String getUpdatedAt() {
                return UpdatedAt;
            }

            public void setUpdatedAt(String UpdatedAt) {
                this.UpdatedAt = UpdatedAt;
            }
        }
    }
}
