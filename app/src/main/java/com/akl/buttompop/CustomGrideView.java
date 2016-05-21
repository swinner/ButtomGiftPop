package com.akl.buttompop;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

/**
 * @author:高腾飞
 * @date: 2016/5/20 15:49
 * @description:
 * @version:1.0
 */
public class CustomGrideView implements AdapterView.OnItemClickListener {
    List<EffectGiftBean> gift;
    private  Context ctx;
    private  GridView layout_grideaa;
    private GridViewAdapter grideViewAdapter;
    public CustomGrideView(Context ctx, List<EffectGiftBean> gift){
        this.ctx = ctx;
        this.gift = gift;
    }

    public View getViews(){
        View view = View.inflate(ctx, R.layout.layout_grideaa, null);
        layout_grideaa =  (GridView)view.findViewById(R.id.gv_grideviews);
        grideViewAdapter = new GridViewAdapter(ctx,gift,100);
        layout_grideaa.setAdapter(grideViewAdapter);
        layout_grideaa.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EffectGiftBean giftBean = (EffectGiftBean)parent.getItemAtPosition(position);
        Toast.makeText(ctx,"点击了礼物===>"+giftBean.getGiftId(),Toast.LENGTH_SHORT).show();
        onGiftSelectCallBack.effectGiftId(giftBean.getGiftId());
        grideViewAdapter = new GridViewAdapter(ctx,gift,position);
        layout_grideaa.setAdapter(grideViewAdapter);
    }

    public void clearAdapter(){
        grideViewAdapter = new GridViewAdapter(ctx,gift,100);
        layout_grideaa.setAdapter(grideViewAdapter);
    }

    public interface EffectGiftCallBack{
        void effectGiftId(String giftId);
    }
    public EffectGiftCallBack onGiftSelectCallBack;

    public void setOnGiftSelectCallBack(EffectGiftCallBack onGiftSelectCallBack) {
        this.onGiftSelectCallBack = onGiftSelectCallBack;
    }
}
