package NetworkPojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsModel {
    @SerializedName("status")
    String status;
    @SerializedName("totalResults")
    String totalResults;
    @SerializedName("articles")
    List<NewsData> articles=null;

    public NewsModel(String status, String totalResults, List<NewsData> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsData> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsData> articles) {
        this.articles = articles;
    }
}
