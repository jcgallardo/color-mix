package es.jcgallardo.colormix.models;

import android.graphics.Color;

/**
 * Created by jcgallardo on 24/08/2017.
 */

class MiColor {
    private int color;
    private int colorTextColor;
    private String labelColor;
    private int r;
    private int g;
    private int b;
    private int alpha;

    // CONSTRUCTORS

    /**
     *
     * @param r
     * @param g
     * @param b
     * @param colorTextColor
     * @param labelColor
     */
    public MiColor(int r, int g, int b, int colorTextColor, String labelColor){
        this.r = r;
        this.g = g;
        this.b = b;
        this.alpha = 255;
        this.color = Color.argb(this.alpha,this.r, this.g, this.b);
        this.colorTextColor = colorTextColor;
        this.labelColor = labelColor;
    }

    /**
     *
     * @param alpha
     * @param r
     * @param g
     * @param b
     * @param colorTextColor
     * @param labelColor
     */
    public MiColor(int alpha, int r, int g, int b, int colorTextColor, String labelColor){
        this.r = r;
        this.g = g;
        this.b = b;
        this.alpha = alpha;
        this.color = Color.argb(this.alpha,this.r, this.g, this.b);
        this.colorTextColor = colorTextColor;
        this.labelColor = labelColor;
    }

    /**
     *
     * @param color
     * @param colorTextColor
     * @param labelColor
     */
    public MiColor(int color, int colorTextColor, String labelColor){
        this.color = color;
        this.r = Color.red(this.color);
        this.g = Color.green(this.color);
        this.b = Color.blue(this.color);
        this.alpha = Color.alpha(this.color);
        this.colorTextColor = colorTextColor;
        this.labelColor = labelColor;
    }

    /**
     *
     * @return
     */
    public int getColor(){
        return this.color;
    }

    /**
     *
     * @return
     */
    public int getColorTextColor(){
        return this.colorTextColor;
    }

    /**
     *
     * @return
     */
    public String getLabelColor(){
        return this.labelColor;
    }

    // GETTERS

    /**
     *
     * @return
     */
    public int getR(){
        return this.r;
    }

    /**
     *
     * @return
     */
    public int getG(){
        return this.g;
    }

    /**
     *
     * @return
     */
    public int getB(){
        return this.b;
    }

    /**
     *
     * @return
     */
    public int getAlpha(){
        return this.alpha;
    }

    // SETTERS

    /**
     *
     * @param r
     */
    public void setR(int r){
        this.r = r;
    }

    /**
     *
     * @param g
     */
    public void setG(int g){
        this.g = g;
    }

    /**
     *
     * @param b
     */
    public void setB(int b){
        this.b = b;
    }

    /**
     *
     * @param alpha
     */
    public void setAlpha(int alpha){
        this.alpha = alpha;
    }

    // OTROS MÉTODOS

    /**
     *
     * @param c
     */
    public void mix(Color c){
        // TODO
    }

    /**
     *
     * @param c
     * @return Color
     */
    public Color getMix(Color c){
        // TODO
        return null;
    }


}
