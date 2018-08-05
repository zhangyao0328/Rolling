package com.rollling.base.prsenter;

import android.content.Context;

import com.rollling.base.model.BaseModel;
import com.rollling.base.model.BaseModellmpl;
import com.rollling.base.view.BaseView;
import com.rollling.bean.BaseBean;

/**
 * @author zhangyao
 * @date 2017/11/19:11:43
 * @E-mail android_n@163.com
 */

public class BasePresenterImpl implements BasePresenter {

    BaseView baseView;
    BaseModel baseModel;

    public BasePresenterImpl(Context mContext, BaseView baseViews){

        baseView = baseViews;
        baseModel = new BaseModellmpl(mContext, baseViews);
    }

    @Override
    public void saveData(Context context, BaseBean baseBean) {
        baseModel.saveData(context, baseBean);
    }
}
