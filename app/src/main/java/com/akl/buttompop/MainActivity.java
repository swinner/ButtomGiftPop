package com.akl.buttompop;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * 展示popde mian
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private Button btn_click;
    private ViewPager vp_gift_viewpager;
    private MainActivity ctx;
    private PopupWindow giftPopwindow;

    public String sendGiftId;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        btn_click = (Button)findViewById(R.id.btn_click);

        intListView();

        btn_click.setOnClickListener(this);

    }
    private int i =1;
    private List<String> list = new ArrayList();
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                Toast.makeText(ctx,"赠送了礼物===>"+sendGiftId,Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_click:
                if(giftPopwindow == null){
                    initGiftPop();
                }
                giftPopwindow.showAtLocation(btn_click, Gravity.BOTTOM, 0, 0);
                GiftAdapter giftAdapter = new GiftAdapter();
                vp_gift_viewpager.setAdapter(giftAdapter);
                break;
        }
    }

    void initGiftPop(){
        View popView = ((LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_gift_pop, null);
        vp_gift_viewpager = (ViewPager)popView.findViewById(R.id.vp_gift_viewpager);
        btn_send = (Button)popView.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = 730;
        giftPopwindow = getPopWindow(ctx, popView, width, height, R.drawable.bg_pop);
    }
    public  PopupWindow getPopWindow(Context ctx, View targetView, int width, int height,int res) {
        PopupWindow popupWindow = new PopupWindow(targetView, width, height);
        popupWindow.setBackgroundDrawable(ctx.getResources().getDrawable(res));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.update();
        return popupWindow;
    }
    private List<View> imageViewList = new ArrayList();; // viewpager的数据
    private CustomGrideView customOne;
    private CustomGrideView customTwo;
    void intListView(){
        List<EffectGiftBean> gift1 = new ArrayList();
        gift1.add(new EffectGiftBean(R.mipmap.icon_5,true,"001"));
        gift1.add(new EffectGiftBean(R.mipmap.icon_5,true,"002"));
        gift1.add(new EffectGiftBean(R.mipmap.icon_5,true,"003"));
        gift1.add(new EffectGiftBean(R.mipmap.icon_5,true,"004"));
        gift1.add(new EffectGiftBean(R.mipmap.icon_1,true,"005"));
        gift1.add(new EffectGiftBean(R.mipmap.icon_1,true,"006"));
        gift1.add(new EffectGiftBean(R.mipmap.icon_1, true, "007"));
        gift1.add(new EffectGiftBean(R.mipmap.icon_1, true, "008"));
        customOne =  new CustomGrideView(ctx,gift1);

        List<EffectGiftBean> gift2 = new ArrayList();
        gift2.add(new EffectGiftBean(R.mipmap.icon_1, true, "009"));
        gift2.add(new EffectGiftBean(R.mipmap.icon_1,true,"0010"));
        gift2.add(new EffectGiftBean(R.mipmap.icon_1,true,"0011"));
        gift2.add(new EffectGiftBean(R.mipmap.icon_1,false,"0012"));
        gift2.add(new EffectGiftBean(R.mipmap.icon_6,false,"0013"));
        gift2.add(new EffectGiftBean(R.mipmap.icon_6, false, "0014"));
        customTwo = new CustomGrideView(ctx,gift2);
//回调，得到在adapter中点击的礼物的id
        customOne.setOnGiftSelectCallBack(new CustomGrideView.EffectGiftCallBack() {
            @Override
            public void effectGiftId(String giftId) {
                sendGiftId = giftId;
                customTwo.clearAdapter();//这样写是为了确保点击一个页面的礼物，另一个页面的选中状态取消
            }
        });
        customTwo.setOnGiftSelectCallBack(new CustomGrideView.EffectGiftCallBack() {
            @Override
            public void effectGiftId(String giftId) {
                sendGiftId = giftId;
                customOne.clearAdapter();
            }
        });
        imageViewList.add(customOne.getViews());
        imageViewList.add(customTwo.getViews());

    }
    class GiftAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View iv = imageViewList.get(position);
            // 1. 向ViewPager中添加一个view对象
            container.addView(iv);
            // 2. 返回当前添加的view对象
            return iv;
        }
    }

}
