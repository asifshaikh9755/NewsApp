package Network;

import java.util.List;

import NetworkPojo.NewsData;
import NetworkPojo.NewsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {

@GET("top-headlines")
Call<NewsModel> getLatestNews(@Query("country") String country, @Query("category") String category,
                              @Query("apiKey") String apiKey);
}
