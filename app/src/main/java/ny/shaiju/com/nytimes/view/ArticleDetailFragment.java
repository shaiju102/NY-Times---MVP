package ny.shaiju.com.nytimes.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ny.shaiju.com.nytimes.R;
import ny.shaiju.com.nytimes.utils.CommonProgressDialog;

/**
 * Created by Shaiju MS on 09-08-2018.
 */
public class ArticleDetailFragment extends Fragment {
    public static final String ARG_ITEM_URL = "url";
    public static final String ARG_ITEM_TITLE = "title";
    private String mUrl;
    private CommonProgressDialog mCommonProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.item_detail, null);
        mCommonProgressDialog = new CommonProgressDialog(getActivity());

        mUrl = getArguments().getString(ARG_ITEM_URL);


        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(getArguments().getString(ARG_ITEM_TITLE));
        }

        initView(view);
        return view;
    }

    //initialize view objects.
    private void initView(View view) {
        WebView webview = view.findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewController());

        webview.loadUrl(mUrl);
    }

    public class WebViewController extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            try{
            mCommonProgressDialog.showDialog();
            }catch (Exception er){

            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            try {
                mCommonProgressDialog.dismissDialog();
            }catch (Exception er){

            }
            super.onPageFinished(view, url);

        }


    }
}
