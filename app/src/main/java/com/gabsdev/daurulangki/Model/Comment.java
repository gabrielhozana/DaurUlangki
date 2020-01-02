package com.gabsdev.daurulangki.Model;

public class Comment {

    private String comment;
    private String publisher;
    private String commentid;

    public Comment(String comment, String publihser, String commentid) {
        this.comment = comment;
        this.publisher = publihser;
        this.commentid = commentid;
    }


    public Comment() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }
}
