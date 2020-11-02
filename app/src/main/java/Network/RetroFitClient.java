package Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroFitClient {
    private  static  final String url="http://newsapi.org/v2/";
    private static Retrofit retrofit;
    private static Gson gson;

    public static Retrofit getRetrofit(){
        if(retrofit==null){

            gson=new GsonBuilder().setLenient().create();

            retrofit=new Retrofit.Builder()
                    .baseUrl(url).addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return retrofit;
    }
}
