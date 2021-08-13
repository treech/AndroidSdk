

package io.github.treech.ui.widget.imageview.crop;


/**
 * 图片裁剪类型
 *

 * @since 2019-10-15 11:32
 */
public class CropImageType {
    /**
     * 图片剪切网格显示关闭
     */
    public static final int CROPIMAGE_GRID_OFF = 0;

    /**
     * 图片剪切触摸时网格显示
     */
    public static final int CROPIMAGE_GRID_ON_TOUCH = 1;

    /**
     * 图片剪切网格显示开启
     */
    public static final int CROPIMAGE_GRID_ON = 2;


    /**
     * 旋转角度
     */
    public static int ROTATE_NINETY_DEGREES = 90;

    /**
     * 翻转
     */

    public enum REVERSE_TYPE {
        UP_DOWN, LEFT_RIGHT
    }


}
