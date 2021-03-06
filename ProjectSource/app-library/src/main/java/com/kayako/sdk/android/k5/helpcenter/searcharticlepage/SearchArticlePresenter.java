package com.kayako.sdk.android.k5.helpcenter.searcharticlepage;

import com.kayako.sdk.android.k5.common.adapter.BaseListItem;
import com.kayako.sdk.android.k5.common.adapter.searchlist.SearchListItem;
import com.kayako.sdk.helpcenter.search.SearchArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Mathew <neil.mathew@kayako.com>
 */
public class SearchArticlePresenter implements SearchArticleContract.Presenter {

    private static final int REQUEST_LIMIT = 20;
    private int mOffset = 0;
    private SearchArticleContract.View mView;
    private SearchArticleContract.Data mData;
    private String mQuery;
    private List<BaseListItem> mListItems;
    private List<BaseListItem> mMoreItems;

    public SearchArticlePresenter(SearchArticleContract.View view, SearchArticleContract.Data data) {
        this.mView = view;
        this.mData = data;
    }

    @Override
    public void initPage() {
        mView.showBlankView();
    }

    @Override
    public void searchArticles(String query) {
        // Only perform search if valid query string (at least 3 characters)
        if (query == null || query.length() < 3) {
            mView.cancelBackgroundTasks();
            mView.showBlankView();
            return;
        }

        // Clear existing tasks
        mView.cancelBackgroundTasks();
        mView.showOnlyLoadingView();

        // Start new search task
        mOffset = 0;
        mQuery = query;
        mView.startSearchTask();
    }

    @Override
    public void clearSearchResults() {
        mView.showBlankView();
    }

    @Override
    public boolean loadDataInBackground() {
        try {
            List<SearchArticle> searchArticleList = mData.searchArticles(mQuery, 0, REQUEST_LIMIT);
            mListItems = convertToListItems(searchArticleList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onDataLoaded(boolean isSuccessful) {
        if (isSuccessful) {
            if (mListItems.size() == 0) {
                mView.showOnlyEmptyView();
            } else if (mListItems.size() < REQUEST_LIMIT) {
                mView.showOnlyListView();
                mView.setUpList(mListItems);
                mView.setListHasMoreItems(false);
            } else {
                mView.setUpList(mListItems);
                mView.showOnlyListView();
                mView.setListHasMoreItems(true);
            }
        } else {
            mView.showOnlyErrorView();
        }
    }

    @Override
    public boolean loadMoreDataInBackground() {
        try {
            List<SearchArticle> searchArticleList = mData.searchArticles(mQuery, mOffset + REQUEST_LIMIT, REQUEST_LIMIT);
            mMoreItems = convertToListItems(searchArticleList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onMoreDataLoaded(boolean isSuccessful) {
        if (isSuccessful) {
            mOffset += REQUEST_LIMIT;
            if (mMoreItems.size() == 0) {
                mView.setListHasMoreItems(false);
            } else if (mMoreItems.size() < REQUEST_LIMIT) {
                mView.addItemsToList(mMoreItems);
                mView.setListHasMoreItems(false);
            } else {
                mView.addItemsToList(mMoreItems);
            }
        } else {
            mView.showErrorToLoadMoreMessage();
        }

        mView.hideLoadingMoreItemsProgress();
    }

    @Override
    public void onClickListItem(BaseListItem listItem) {
        SearchListItem item = (SearchListItem) listItem;
        SearchArticle searchArticle = (SearchArticle) item.getResource();
        mView.openArticleActivity(searchArticle.getOriginalArticle());
    }

    @Override
    public void reloadPage() {
        mView.cancelBackgroundTasks();
        mView.showBlankView();
        mOffset = 0;
        mMoreItems = null;
        mListItems = null;
    }

    @Override
    public void loadMoreData() {
        mView.showLoadingMoreItemsProgress();
        mView.startLoadMoreTask();
    }

    @Override
    public void setView(SearchArticleContract.View view) {
        mView = view;
    }

    private List<BaseListItem> convertToListItems(List<SearchArticle> searchArticleList) {
        if (searchArticleList.size() == 0) {
            return new ArrayList<>();
        } else {
            List<BaseListItem> items = new ArrayList<>();
            for (SearchArticle searchArticle : searchArticleList) {
                String subtitle = String.format("%s > %s", searchArticle.getCategoryName(), searchArticle.getSectionName());
                items.add(new SearchListItem(searchArticle.getTitle(), subtitle, searchArticle));
            }
            return items;
        }
    }
}
