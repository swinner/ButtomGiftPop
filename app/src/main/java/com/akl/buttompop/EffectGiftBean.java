package com.akl.buttompop;

/**
 * @date: 2016/5/20 19:16
 * @description:
 * @version:1.0
 */
public class EffectGiftBean {
    private String giftId;
    private int resId;
    private boolean isLianSong;

    public EffectGiftBean(int resId, boolean isLianSong, String giftId) {
        this.resId = resId;
        this.isLianSong = isLianSong;
        this.giftId = giftId;
    }

    public int getResId() {
        return resId;
    }

    public boolean isLianSong() {
        return isLianSong;
    }

    public String getGiftId() {
        return giftId;
    }
}
