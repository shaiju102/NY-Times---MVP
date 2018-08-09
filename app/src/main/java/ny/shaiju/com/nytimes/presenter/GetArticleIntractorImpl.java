package ny.shaiju.com.nytimes.presenter;

import android.util.Log;

import ny.shaiju.com.nytimes.network.NetworkService;
import ny.shaiju.com.nytimes.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shaiju MS on 09-08-2018.
 */

public class GetArticleIntractorImpl implements ArticlePresenter.GetArticleIntractor {

    @Override
    public void getArticleArrayList(final OnFinishedListener onFinishedListener) {


        /** Create handle for the RetrofitInstance interface*/
        NetworkService service = RetrofitInstance.getRetrofitInstance().create(NetworkService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<ny.shaiju.com.nytimes.model.Response> call = service.getArticles();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ny.shaiju.com.nytimes.model.Response>() {
            @Override
            public void onResponse(Call<ny.shaiju.com.nytimes.model.Response> call, Response<ny.shaiju.com.nytimes.model.Response> response) {
                onFinishedListener.onFinished(response.body().getResults());

            }

            @Override
            public void onFailure(Call<ny.shaiju.com.nytimes.model.Response> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }

}
