package com.learn.entity;

public class PostInfo {
    private int id;
    private String title;
    private String postTime;
    private  int clickNum;
    private String content;
    private int topicId;
    private String pic;

    public PostInfo() {
    }

    public PostInfo(int id) {
        this.id = id;
    }

    public PostInfo(String title, String postTime, int clickNum, String content, int topicId, String pic) {
        this.title = title;
        this.postTime = postTime;
        this.clickNum = clickNum;
        this.content = content;
        this.topicId = topicId;
        this.pic = pic;
    }

    public PostInfo(int id, String title, String postTime, int clickNum, String content, int topicId, String pic) {
        this.id = id;
        this.title = title;
        this.postTime = postTime;
        this.clickNum = clickNum;
        this.content = content;
        this.topicId = topicId;
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "PostInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", postTime='" + postTime + '\'' +
                ", clickNum=" + clickNum +
                ", content='" + content + '\'' +
                ", topicId=" + topicId +
                ", pic='" + pic + '\'' +
                '}';
    }
}
