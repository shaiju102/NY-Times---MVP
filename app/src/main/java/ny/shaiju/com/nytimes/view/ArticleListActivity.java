package ny.shaiju.com.nytimes.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import ny.shaiju.com.nytimes.R;
import ny.shaiju.com.nytimes.view.adapter.ArticlesAdapter;
import ny.shaiju.com.nytimes.model.Result;
import ny.shaiju.com.nytimes.presenter.ArticlePresenter;
import ny.shaiju.com.nytimes.presenter.ArticlePresenterImpl;
import ny.shaiju.com.nytimes.presenter.GetArticleIntractorImpl;
import ny.shaiju.com.nytimes.utils.CommonProgressDialog;

/**
 * Created by Shaiju MS on 09-08-2018.
 */
public class ArticleListActivity extends AppCompatActivity implements ArticlePresenter.MainView,
        NavigationView.OnNavigationItemSelectedListener {

//Feature1 Comments added.
// Feature 1 changes added.

//Feature1 Comments added.
// Feature1 changes added.
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private CommonProgressDialog mCommonProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        setupToolbar();

        setupNavigationView();


        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }


        setPresenter();


    }

    private void setPresenter() {
        ArticlePresenter.presenter presenter = new ArticlePresenterImpl(this, new
                GetArticleIntractorImpl());
        presenter.requestDataFromServer();
    }

    private void setupNavigationView() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<Result> resultList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArticlesAdapter articlesAdapter = new ArticlesAdapter(resultList, this,
                getFragmentManager(), mTwoPane);
        recyclerView.setAdapter(articlesAdapter);
    }

    @Override
    public void showProgress() {
        createProgressDialog();

        if (!mCommonProgressDialog.isShowing())
            mCommonProgressDialog.showDialog();
    }

    private void createProgressDialog() {
        try{
        if (null == mCommonProgressDialog)
            mCommonProgressDialog = new CommonProgressDialog(this);
        }catch (Exception er){

        }
    }

    @Override
    public void hideProgress() {
        try{
        if (null != mCommonProgressDialog)
            mCommonProgressDialog.dismissDialog();
        }catch (Exception er){

        }
    }

    @Override
    public void setDataToRecyclerView(List<Result> resultList) {
        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView, resultList);
    }


    @Override
    public void onResponseFailure(Throwable throwable) {

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

}
