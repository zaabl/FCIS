package com.pharaohtech.fcis.models;

public class Post {
    private String displayName;
    private String profilePhoto;
    private String caption;
    private String uid;
    private Long timestamp;

    public Post(String displayName, String profilePhoto, String caption, String uid, Long timestamp) {
        this.displayName = displayName;
        this.profilePhoto = profilePhoto;
        this.caption = caption;
        this.uid = uid;
        this.timestamp = timestamp;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
                "displayName='" + displayName + '\'' +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", caption='" + caption + '\'' +
                ", uid='" + uid + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
