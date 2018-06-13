package model;

public class Video {
    private Integer id;

    private Integer adress_id;

    private String detail;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdress_id() {
        return adress_id;
    }

    public void setAdress_id(Integer adress_id) {
        this.adress_id = adress_id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}