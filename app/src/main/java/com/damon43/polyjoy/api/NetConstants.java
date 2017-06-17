package com.damon43.polyjoy.api;

/**
 * @author damonmasty
 *         Created on 下午12:08 17-1-29.
 */

public class NetConstants {
    public static final String JUHE_URL = "http://v.juhe.cn";
    public static final String GANKIO_URL = "http://gank.io/api/data/";
    public static final String JUHE_DATA_NEWS_APP_KEY = "092267177428a8af509c5b934ffebfe5";
    public static final String JUHE_DATA_WECHAT_APP_KEY = "499e9ef398e8bbf3abce9151e596571d";

    public static final int HOST_COUNT = 2;
    public static final int HOST_JUHE = 1;
    public static final int HOST_GANKIO = 2;

    public static String getHostType(int hostType) {
        String type = "";
        switch (hostType) {
            case HOST_JUHE:
                type = JUHE_URL;
                break;
            case HOST_GANKIO:
                type = GANKIO_URL;
                break;
        }
        return type;
    }
}
