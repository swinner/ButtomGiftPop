package com.akl.buttompop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * @author:高腾飞
 * @date: 2016/5/20 16:01
 * @description:
 * @version:1.0
 */
public class  GridViewAdapter extends BaseAdapter {

    private  List<EffectGiftBean> gift;
    private int index;
     private  Context ctx;
    public GridViewAdapter(Context ctx, List<EffectGiftBean> gift,int index){
        this.ctx = ctx;
        this.gift = gift;
        this.index = index;
    }
    @Override
    public int getCount() {
        return gift.size();
    }

    @Override
    public Object getItem(int position) {
        return gift.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("aaa","getView====>"+position+"index===>"+index);
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_grideview,null);
        ImageView iv_image = (ImageView)view.findViewById(R.id.iv_image);
        ImageView iv_gift_select_state = (ImageView)view.findViewById(R.id.iv_gift_select_state);
        iv_image.setBackgroundResource(gift.get(position).getResId());
        if(gift.get(position).isLianSong()){
            iv_gift_select_state.setBackgroundResource(R.drawable.bg_gift_send_select);
        }else{
            iv_gift_select_state.setBackgroundResource(R.drawable.bg_gift_single_send_select);
        }
        if(index == position){
            iv_gift_select_state.setSelected(true);
        }else{
            iv_gift_select_state.setSelected(false);
        }
        return view;
    }
}
