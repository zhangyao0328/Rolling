package com.rolling.bean.other;

import com.rolling.bean.BaseDataBean;

/**
 * @author zhangyao
 * @date 12/4/20  3:07 PM
 * @E-mail android_n@163.com
 */
public class QiNiuTokenBean extends BaseDataBean {

    /**
     * token : DiOHtUGYKCwVcZUMaxgQGaSegKAUEkJSBR9-hRqF:8YWejM3WfIw33e_wiyZhgJmZQX4=:eyJzY29wZSI6InBvc3Ryb2NrLXZpZGVvLXB1YmxpYyIsImRlYWRsaW5lIjoxNjA3MTA0NDAwfQ==
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String token;
        private String host;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
