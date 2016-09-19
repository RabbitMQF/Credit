package com.example.credit.Activitys;

import android.os.Bundle;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.example.credit.R;
import com.example.credit.Utils.CreditSharePreferences;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * Created by alucard on 2016-08-09.
 */
public class SearchBidActivity extends BaseActivity implements GestureDetector.OnGestureListener {
    @ViewInject(R.id.search_et)
    EditText search_et;
    @ViewInject(R.id.search_et_cc)
    ImageView search_et_cc;
    @ViewInject(R.id.arrow_back)
    LinearLayout arrowBack;
    @ViewInject(R.id.his_nullbt)
    ImageView his_nullbt;
    @ViewInject(R.id.history_list_null)
    TextView history_list_null;
    private TextView mTextView1, mTextView2, mTextView3, mTextView4,
            mTextView5, mTextView6, mTextView7, mTextView8, mTextView9,
            mTextView10;
    private Animation animation1, animation2, animation3, animation4,
            animation6, animation7, animation8, animation9, animation11,
            animation12, animation13, animation2_1, animation2_2, animation2_3,
            animation2_4, animation2_5, animation2_6, animation2_8,
            animation2_9, animation2_10, animation2_11, animation2_12,
            animation2_13;
    CreditSharePreferences csp;
    private GestureDetector gDetector;
    LinearLayout searchContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_bid_activity);
        ViewUtils.inject(this);
        initView();




        initViews();
        initAnimations();
        initAnimations2();
        randomTanslate();
        gDetector = new GestureDetector(this);
    }

    private void initView() {
        csp = CreditSharePreferences.getLifeSharedPreferences();
        searchContent = (LinearLayout) findViewById(R.id.searchContent);
        if (csp.getHistory() == null || csp.getHistory().equals("")) {//给历史记录赋初始值
            String Tnameh = "余江县龙溪养蜂专业合作社,江西圆融医疗器械有限公司,景德镇市第一炉面包房,江西梦娜袜业有限公司,江西工商联合投资有限公司,江西智容科技有限公司,南昌和平大厦实业发展公司,贵溪市幸福树电器有限公司,德兴市华清汽车销售服务有限公司,江西新星建筑装饰工程有限公司,";//历史字备用
            csp.putHistory(Tnameh);
        }

        /**
         * 监听软键盘回车
         */
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    //GETsearch();
                    return true;
                }
                return false;
            }
        });


        /**
         * 动态判断输入框中的字数并显示隐藏图标
         */
        search_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 0) {
                    search_et_cc.setVisibility(View.VISIBLE);
                } else {
                    search_et_cc.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable t) {
                if (t.length() > 0) {
                    search_et_cc.setVisibility(View.VISIBLE);
                } else {
                    search_et_cc.setVisibility(View.GONE);
                }
            }
        });
        search_et_cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_et.setText("");
            }
        });





    }


    /**
     * 历史记录悬浮动画效果监听手势滑动如果滑动就随机乱序
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //if (listsea.size() <= 0) {
            return gDetector.onTouchEvent(event);
        //} else {
           // return super.onTouchEvent(event);
        //}
    }

    private void initViews() {
        mTextView1 = (TextView) findViewById(R.id.txt1);
        mTextView2 = (TextView) findViewById(R.id.txt2);
        mTextView3 = (TextView) findViewById(R.id.txt3);
        mTextView4 = (TextView) findViewById(R.id.txt4);
        mTextView5 = (TextView) findViewById(R.id.txt5);
        mTextView6 = (TextView) findViewById(R.id.txt6);
        mTextView7 = (TextView) findViewById(R.id.txt7);
        mTextView8 = (TextView) findViewById(R.id.txt8);
        mTextView9 = (TextView) findViewById(R.id.txt9);
        mTextView10 = (TextView) findViewById(R.id.txt10);
        mTextView1.setOnClickListener(textbt);
        mTextView2.setOnClickListener(textbt);
        mTextView3.setOnClickListener(textbt);
        mTextView4.setOnClickListener(textbt);
        mTextView5.setOnClickListener(textbt);
        mTextView6.setOnClickListener(textbt);
        mTextView7.setOnClickListener(textbt);
        mTextView8.setOnClickListener(textbt);
        mTextView9.setOnClickListener(textbt);
        mTextView10.setOnClickListener(textbt);
        his_nullbt = (ImageView) findViewById(R.id.his_nullbt);
        his_nullbt.setOnClickListener(textbt);
    }

    /**
     * 将历史记录赋给浮动控件
     */
    private void randomText() {
        ListView history_list = (ListView) findViewById(R.id.history_list);
        TextView history_list_null = (TextView) findViewById(R.id.history_list_null);
        if (csp.getHistory() != null && !(csp.getHistory()).equals("")) {
            String str = csp.getHistory();
            String[] strh = str.split(",");
            List<String> listh = new ArrayList<String>(Arrays.asList(strh));
            if (listh != null && listh.size() > 0) {
                his_nullbt.setVisibility(View.VISIBLE);
                for (int i = R.id.txt1; i <= R.id.txt10; i++) {
                    ((TextView) findViewById(i)).setText(getKeyword(listh));
                }
            }

        } else {
            his_nullbt.setVisibility(View.GONE);
            searchContent.setVisibility(View.GONE);
            history_list_null.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 随机乱序方法
     *
     * @param list 数据源
     * @return
     */
    private String getKeyword(List<String> list) {
        if (list != null && list.size() > 0) {
            int num = random.nextInt(list.size());
            String keyword = list.get(num);
            list.remove(num);
            return keyword;
        } else {
            return "";
        }
    }

    private void initAnimations() {
        animation1 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim1);
        animation2 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2);
        animation3 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim3);
        animation4 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim4);
        animation6 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim6);
        animation7 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim7);
        animation8 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim8);
        animation9 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim9);
        animation11 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim11);
        animation12 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim12);
        animation13 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim13);
    }

    private void initAnimations2() {
        animation2_1 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2_1);
        animation2_2 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2_2);
        animation2_3 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2_3);
        animation2_4 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2_4);
        animation2_5 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2_5);
        animation2_6 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2_6);
        animation2_8 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2_8);
        animation2_9 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2_9);
        animation2_10 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2_10);
        animation2_11 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2_11);
        animation2_12 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2_12);
        animation2_13 = AnimationUtils.loadAnimation(SearchBidActivity.this, R.anim.anim2_13);
    }

    private void startAnimations1() {
        mTextView1.startAnimation(animation1);
        mTextView2.startAnimation(animation2);
        mTextView3.startAnimation(animation3);
        mTextView4.startAnimation(animation4);
        mTextView5.startAnimation(animation3);
        mTextView6.startAnimation(animation7);
        mTextView7.startAnimation(animation8);
        mTextView8.startAnimation(animation9);
        mTextView9.startAnimation(animation11);
        mTextView10.startAnimation(animation12);
    }

    private void startAnimations2() {
        mTextView1.startAnimation(animation2_1);
        mTextView2.startAnimation(animation2_2);
        mTextView3.startAnimation(animation2_3);
        mTextView4.startAnimation(animation2_4);
        mTextView5.startAnimation(animation2_5);
        mTextView6.startAnimation(animation2_6);

        mTextView7.startAnimation(animation2_6);

        mTextView8.startAnimation(animation2_8);
        mTextView9.startAnimation(animation2_9);
        mTextView10.startAnimation(animation2_10);
    }

    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    private void randomTanslate() {
        int num = random.nextInt(2);
        randomText();
        switch (num) {
            case 0:
                startAnimations1();
                break;
            case 1:
                startAnimations2();
                break;
        }
    }

    private Random random = new Random();//随机数

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        float minMove = 120;         //最小滑动距离
        float minVelocity = 0;      //最小滑动速度
        float beginX = e1.getX();
        float endX = e2.getX();
        float beginY = e1.getY();
        float endY = e2.getY();
        if (beginX - endX > minMove && Math.abs(velocityX) > minVelocity) {   //左滑
            startAnimations1();
            randomText();
        } else if (endX - beginX > minMove && Math.abs(velocityX) > minVelocity) {   //右滑
            startAnimations1();
            randomText();
        } else if (beginY - endY > minMove && Math.abs(velocityY) > minVelocity) {   //上滑
            startAnimations2();
            randomText();
        } else if (endY - beginY > minMove && Math.abs(velocityY) > minVelocity) {   //下滑
            startAnimations2();
            randomText();
        }
//        randomTanslate();
        return false;
    }

    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // System.out.println("onScroll");
        return false;
    }

    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 点击浮动字体给搜索框赋值
     */
    View.OnClickListener textbt = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.txt1:
                    search_et.setText(mTextView1.getText().toString());
                    break;
                case R.id.txt2:
                    search_et.setText(mTextView2.getText().toString());
                    break;
                case R.id.txt3:
                    search_et.setText(mTextView3.getText().toString());
                    break;
                case R.id.txt4:
                    search_et.setText(mTextView4.getText().toString());
                    break;
                case R.id.txt5:
                    search_et.setText(mTextView5.getText().toString());
                    break;
                case R.id.txt6:
                    search_et.setText(mTextView6.getText().toString());
                    break;
                case R.id.txt7:
                    search_et.setText(mTextView7.getText().toString());
                    break;
                case R.id.txt8:
                    search_et.setText(mTextView8.getText().toString());
                    break;
                case R.id.txt9:
                    search_et.setText(mTextView9.getText().toString());
                    break;
                case R.id.txt10:
                    search_et.setText(mTextView10.getText().toString());
                    break;
                case R.id.his_nullbt:
                    csp.putHistory("");
                    history_list_null.setVisibility(View.VISIBLE);
                    his_nullbt.setVisibility(View.GONE);
                    searchContent.setVisibility(View.GONE);
                    break;
            }
        }
    };


    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        switch (v.getId()){
            case R.id.arrow_back:
                finish();
                overridePendingTransition(R.anim.finish_tran_one, R.anim.finish_tran_two);
                break;
            case R.id.search_bt:
                search();
                break;
        }
        }
    };

    private void search() {
        String Tname = search_et.getText().toString();
        //历史记录保存本地SP
        String Tnameh = Tname + ",";//历史字备用
        if (!Tname.equals("")) {
            if (csp.getHistory() != null && !(csp.getHistory()).equals("")) {
                String str1 = csp.getHistory();
                String[] strh = str1.split(",");
                List<String> listh = new ArrayList<String>(Arrays.asList(strh));
                if (listh != null && listh.size() < 10) {
                    String temp = "";
                    for (int i = 0; i < listh.size(); i++) {
                        if (Tname.equals(listh.get(i))) {
                            temp = listh.get(i);
                        }
                    }
                    if (temp.equals("")) {
                        csp.putHistory(str1 + Tnameh);
                    }
                } else {
                    String temp = "";
                    for (int i = 0; i < listh.size(); i++) {
                        if (Tname.equals(listh.get(i))) {
                            temp = listh.get(i);
                        }
                    }
                    if (temp.equals("")) {
                        listh.remove(0);
                        String strlists = "";
                        for (int i = 0; i < listh.size(); i++) {
                            strlists = strlists + listh.get(i) + ",";
                        }
                        csp.putHistory(strlists + Tnameh);
                    }
                }
            } else {
                csp.putHistory(Tnameh);
            }


        }else {
            android.widget.Toast.makeText(SearchBidActivity.this, "搜索关键词不能为空!", android.widget.Toast.LENGTH_SHORT).show();
        }


    }

}
