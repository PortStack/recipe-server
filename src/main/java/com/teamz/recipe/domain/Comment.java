package com.teamz.recipe.domain;

public class Comment {

    private String bno; //댓글이 속한 게시글 번호
    private String cno; //댓글 번호
    private String writter; //댓글 작성자
    private String content; //댓글 내용
    private String wdate; //업데이트 날짜
    private int cdepth; //댓글 깊이
    private int cgroup; //댓글 그룹
    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }


    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public String getWritter() {
        return writter;
    }

    public void setWritter(String writter) {
        this.writter = writter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWdate() {
        return wdate;
    }

    public void setWdate(String wdate) {
        this.wdate = wdate;
    }

    public int getCdepth() {
        return cdepth;
    }

    public void setCdepth(int cdepth) {
        this.cdepth = cdepth;
    }

    public int getCgroup() {
        return cgroup;
    }

    public void setCgroup(int cgroup) {
        this.cgroup = cgroup;
    }

}
