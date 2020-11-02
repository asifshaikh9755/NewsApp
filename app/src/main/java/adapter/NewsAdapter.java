package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asifshaikh.newsapi.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import NetworkPojo.NewsData;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
        List<NewsData> newsDataList;
    Context context;
        public NewsAdapter(List<NewsData> newsDataList){
            this.newsDataList=newsDataList;
        }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lyt_newslist, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        context = holder.itemView.getContext();
        holder.txtTitle.setText(newsDataList.get(position).getTitle());
        Glide.with(context).load(newsDataList.get(position).getUrlToImage()).apply(RequestOptions.centerCropTransform().
                apply(RequestOptions.placeholderOf(R.drawable.ic_imagenotfound).error(R.drawable.ic_imagenotfound))).into(holder.imgNews);


    }

    @Override
    public int getItemCount() {
        return newsDataList.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView imgNews;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle=itemView.findViewById(R.id.idTitle);
            imgNews=itemView.findViewById(R.id.imgNews);
        }
    }
}
