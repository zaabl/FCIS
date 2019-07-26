package com.pharaohtech.fcis.models;

public class Post {
    private String caption;
    private String uid;
    private String name;
    private String profilePhoto;
    private Long timestamp;

    public Post(String name, String uid, String caption, String profilePhoto, Long timestamp) {
        this.caption = caption;
        this.uid = uid;
        this.name = name;
        this.profilePhoto = profilePhoto;
        this.timestamp = timestamp;
    }

    public Post() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Post{" +
                "caption='" + caption + '\'' +
                ", uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
