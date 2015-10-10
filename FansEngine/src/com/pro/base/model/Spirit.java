package com.pro.base.model;

import com.pro.base.World;
import com.pro.base.graphics.Bitmap;
import com.pro.base.graphics.Canvas;
import com.pro.base.graphics.Matrix;
import com.pro.base.graphics.Paint;
import com.pro.base.java.componet.JMatrix;
/**
 * ������
 * @author Xiloer
 *
 */
public abstract class Spirit implements Drawable{
	private Bitmap[] animation;//����
	private int interval_ms = 0;//֡���
	private long pre_time = System.currentTimeMillis();//�ϴβ���ʱ��ʱ��
	private int animation_index = 0;//֡����
	private boolean playAnimation;
	private Matrix matrix = new JMatrix();
	@Override
	public void onLoop(Canvas canvas, Paint paint, World world) {
		onLoop(world);
		if(playAnimation){
			doAnimation();	
		}
		canvas.drawBitmap(animation[animation_index], matrix, paint);
	}

	@Override
	public void onPause(Canvas canvas, Paint paint, World world) {
		onPause(world);
		if(playAnimation){
			doAnimation();	
		}
		canvas.drawBitmap(animation[animation_index], matrix, paint);
	}
	/**
	 * ����ƽ��λ��
	 * @param x
	 * @param y
	 */
	public void setLocal(float x,float y){
		matrix.setTranslate(x, y);
	}
	/**
	 * ������ת
	 * @param angle
	 * @param x
	 * @param y
	 */
	public void setRotate(float angle, float x, float y){
		matrix.preRotate(angle, x, y);
	}
	/**
	 * ��������
	 * @param x
	 * @param y
	 */
	public void setScale(float x, float y) {
		matrix.preScale(x, y);	
	}
	
	public abstract void onLoop(World world);
	public abstract void onPause(World world);
	/**
	 * ���ö�����Ϣ
	 * @param Animation
	 */
	public void setAnimation(Bitmap[] animation,int interval_ms){
		this.animation = animation;
		this.interval_ms = interval_ms;
		
	}
	/**
	 * ���Ŷ���
	 * @param isPlay
	 */
	public void playAnimation(boolean isPlay){
		playAnimation = isPlay;
	}
	/**
	 * ���㶯��
	 */
	private void doAnimation(){
		if(System.currentTimeMillis()-pre_time>=interval_ms){
			pre_time = System.currentTimeMillis();
			animation_index=animation_index+2;
			if(animation_index==animation.length){
				animation_index=0;
			}
		}
	}
}
