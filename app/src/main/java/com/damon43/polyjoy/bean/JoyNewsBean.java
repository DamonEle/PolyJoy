package com.damon43.polyjoy.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author damonmasty
 *         Created on 下午2:45 17-2-7.
 */

public class JoyNewsBean implements Serializable {


    private String stat;
    private List<DataBean> data;
    public  DataBean one ;
    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{

        private String uniquekey;
        private String title;
        private String date;
        private String category;
        @SerializedName("author_name")
        private String authorName;
        private String url;
        @SerializedName("thumbnail_pic_s")
        private String thumbnailPicS;
        @SerializedName("thumbnail_pic_s02")
        private String thumbnailPicS02;
        @SerializedName("thumbnail_pic_s03")
        private String thumbnailPicS03;

        public String getUniquekey() {
            return uniquekey;
        }

        public void setUniquekey(String uniquekey) {
            this.uniquekey = uniquekey;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnailPicS() {
            return thumbnailPicS;
        }

        public void setThumbnailPicS(String thumbnailPicS) {
            this.thumbnailPicS = thumbnailPicS;
        }

        public String getThumbnailPicS02() {
            return thumbnailPicS02;
        }

        public void setThumbnailPicS02(String thumbnailPicS02) {
            this.thumbnailPicS02 = thumbnailPicS02;
        }

        public String getThumbnailPicS03() {
            return thumbnailPicS03;
        }

        public void setThumbnailPicS03(String thumbnailPicS03) {
            this.thumbnailPicS03 = thumbnailPicS03;
        }
    }
}
