package ua.socialnetwork.Dto;

public class CommentDto {
    private String text;
    private String user;
    private int post_id;

    @Override
    public String toString() {
        return "CommentDto{" +
                "text='" + text + '\'' +
                ", user='" + user + '\'' +
                ", post_id=" + post_id +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPost_id() {
        return post_id;
    }
}
