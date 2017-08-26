package com.sfkiller.interview.englishreader.contract;

import java.util.List;

/**
 * Presenter与View 的契约类
 *
 * Created by SFKiller on 2017/8/26.
 */
public interface IEnglishReaderContract {

    public interface IEnglishReaderPresenter {

        /**
         * 预取数据
         */
        void fetchDatas();

        /**
         * 获得数据
         */
        List<String> getDatas();
    }

    public interface IEnglishReaderView {

        /**
         * 设置Presenter
         *
         * @param presenter
         */
        void setPresenter(IEnglishReaderPresenter presenter);

    }
}
