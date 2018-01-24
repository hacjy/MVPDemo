package com.ha.cjy.mvpdemo.Model.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 电影实体类
 * Created by cjy on 18/1/22.
 */

public class MovieEntity {

    @SerializedName("count")
    public int count;
    @SerializedName("start")
    public int start;
    @SerializedName("total")
    public int total;
    @SerializedName("subjects")
    public List<Subjects> subjects;
    @SerializedName("title")
    public String title;

    public static class Rating {
        @SerializedName("max")
        public int max;
        @SerializedName("average")
        public double average;
        @SerializedName("stars")
        public String stars;
        @SerializedName("min")
        public int min;
    }

    public static class Avatars {
        @SerializedName("small")
        public String small;
        @SerializedName("large")
        public String large;
        @SerializedName("medium")
        public String medium;
    }

    public static class Casts {
        @SerializedName("alt")
        public String alt;
        @SerializedName("avatars")
        public Avatars avatars;
        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public String id;
    }

    public static class Directors {
        @SerializedName("alt")
        public String alt;
        @SerializedName("avatars")
        public Avatars avatars;
        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public String id;
    }

    public static class Images {
        @SerializedName("small")
        public String small;
        @SerializedName("large")
        public String large;
        @SerializedName("medium")
        public String medium;
    }

    public static class Subjects {
        @SerializedName("rating")
        public Rating rating;
        @SerializedName("title")
        public String title;
        @SerializedName("casts")
        public List<Casts> casts;
        @SerializedName("collect_count")
        public int collect_count;
        @SerializedName("original_title")
        public String original_title;
        @SerializedName("subtype")
        public String subtype;
        @SerializedName("directors")
        public List<Directors> directors;
        @SerializedName("year")
        public String year;
        @SerializedName("images")
        public Images images;
        @SerializedName("alt")
        public String alt;
        @SerializedName("id")
        public String id;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", subjects_size=" + subjects.size() +
                ", title='" + title + '\'' +
                '}';
    }
}
