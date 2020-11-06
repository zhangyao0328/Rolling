package com.rolling.view.search;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.SearchView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.rolling.R;
import com.rolling.view.TextViewIcon;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zhangyao
 * @date 2020/11/6  09:39
 * @E-mail android_n@163.com
 */
public class SearchLayout extends RelativeLayout {

    @BindView(R.id.edSearch)
    public SearchView edSearch;

    @BindView(R.id.tvSearchCancel)
    public TextViewIcon tvSearchCancel;

    public SearchLayout(Context context) {
        super(context);
        initView();
    }

    public SearchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();

        if(attrs != null){
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.layout_search);
            String edHint = typedArray.getString(R.styleable.layout_search_ed_hint);
            edSearch.setQueryHint(edHint);
        }
    }

    private void initView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_search, this, true);
        ButterKnife.bind(this, view);
        edSearch.onActionViewExpanded();
    }

    @OnClick({R.id.tvSearchCancel})
    public void onClicks(View view){
        switch (view.getId()){
            case R.id.searchLayout:

                break;
        }
    }

//    private void cityFindByEn(){
//        findByEN();
//    }
//
//    private static void findByEN(String inputStr, List<CityBean> mBodyDatas, List<CityBean> searchResult) {
//        //把输入的内容变为大写
//        String searPinyin = PinYinUtil.transformPinYin(inputStr);
//        //搜索字符串的长度
//        int searLength = searPinyin.length();
//        //搜索的第一个大写字母
//        for (int i = 0; i < mBodyDatas.size(); i++) {
//            CityBean cityBean = mBodyDatas.get(i);
//            //如果输入的每一个字母都和名字的首字母一样，那就可以匹配比如：武汉，WH
//            if (cityBean.getName().contains(searPinyin)) {
//                searchResult.add(cityBean);
//            } else {
//                boolean isMatch = false;
//                //先去匹配单个字，比如武汉WU,HAN.输入WU，肯定匹配第一个
//                for (int j = 0; j < cityBean.getNamePinyinList().size(); j++) {
//                    String namePinyinPer = cityBean.getNamePinyinList().get(j);
//                    if (!TextUtils.isEmpty(namePinyinPer) && namePinyinPer.startsWith(searPinyin)) {
//                        //符合的话就是当前字匹配成功
//                        searchResult.add(cityBean);
//                        isMatch = true;
//                        break;
//                    }
//                }
//                if (isMatch) {
//                    continue;
//                }
////                根据拼音包含来实现，比如武汉：WUHAN,输入WUHA或者WUHAN。
//                if (!TextUtils.isEmpty(cityBean.getNamePinYin()) && cityBean.getNamePinYin().contains(searPinyin)) {
//                    //这样的话就要从每个字的拼音开始匹配起
//                    for (int j = 0; j < cityBean.getNamePinyinList().size(); j++) {
//                        StringBuilder sbMatch = new StringBuilder();
//                        for (int k = j; k < cityBean.getNamePinyinList().size(); k++) {
//                            sbMatch.append(cityBean.getNamePinyinList().get(k));
//                        }
//                        if (sbMatch.toString().startsWith(searPinyin)) {
//                            //匹配成功
//                            int length = 0;
//                            //比如输入是WUH，或者WUHA,或者WUHAN,这些都可以匹配上
//                            for (int k = j; k < cityBean.getNamePinyinList().size(); k++) {
//                                length = length + cityBean.getNamePinyinList().get(k).length();
//                                if (length >= searLength) {
//                                    break;
//                                }
//                            }
//                            //有可能重复匹配
//                            if (!searchResult.contains(cityBean))
//                                searchResult.add(cityBean);
//                        }
//                    }
//                }
//
//            }
//        }
//    }


}

