package ny.shaiju.com.nytimes.presenter;


import java.util.List;

import ny.shaiju.com.nytimes.EspressoTestingIdlingResource;
import ny.shaiju.com.nytimes.model.Result;

/**
 * Created by Shaiju MS on 09-08-2018.
 */
public class ArticlePresenterImpl implements ArticlePresenter.presenter, ArticlePresenter.GetArticleIntractor
        .OnFinishedListener {

    private ArticlePresenter.MainView mainView;
    private ArticlePresenter.GetArticleIntractor getArticleIntractor;

    public ArticlePresenterImpl(ArticlePresenter.MainView mainView, ArticlePresenter.GetArticleIntractor

            getArticleIntractor) {
        this.mainView = mainView;
        this.getArticleIntractor = getArticleIntractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {

        showProgress();
        getArticleIntractor.getArticleArrayList(this);

    }

    private void showProgress() {
        if (mainView != null) {
            mainView.showProgress();
        }
    }

    @Override
    public void requestDataFromServer() {
        EspressoTestingIdlingResource.increment();
        showProgress();

        getArticleIntractor.getArticleArrayList(this);
    }


    @Override
    public void onFinished(List<Result> resultList) {
        if (mainView != null) {
            mainView.setDataToRecyclerView(resultList);
            mainView.hideProgress();
        }

        EspressoTestingIdlingResource.decrement();
    }

    @Override
    public void onFailure(Throwable t) {
        if (mainView != null) {
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }

        EspressoTestingIdlingResource.decrement();
    }
}
