package ro.ubb.Lab9JSP.model;

public class Comment {
    private Integer id;
    private String description;
    private Integer userId;
    private Integer topicId;

    public Comment() {}

    public Comment(String description, Integer userId, Integer topicId) {
        this.description = description;
        this.userId = userId;
        this.topicId = topicId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", topicId=" + topicId +
                '}';
    }
}
