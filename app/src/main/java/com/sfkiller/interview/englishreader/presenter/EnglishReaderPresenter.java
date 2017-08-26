package com.sfkiller.interview.englishreader.presenter;

import com.sfkiller.interview.englishreader.contract.IEnglishReaderContract;
import com.sfkiller.interview.englishreader.model.DataSources;
import com.sfkiller.interview.englishreader.ui.MainActivity;

import java.util.List;

/**
 * Presenter
 *
 * Created by SFKiller on 2017/8/26.
 */
public class EnglishReaderPresenter implements IEnglishReaderContract.IEnglishReaderPresenter {

    private MainActivity mainActivity;

    public EnglishReaderPresenter(MainActivity container) {
        this.mainActivity = container;
        this.mainActivity.setPresenter(this);
    }

    @Override
    public void fetchDatas() {
        DataSources.getInstance().fetchDatas();
    }

    @Override
    public List<String> getDatas() {
        return DataSources.getInstance().getDatas();
    }
}
