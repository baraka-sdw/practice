package com;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.pro.base.World;
import com.pro.base.graphics.Bitmap;
import com.pro.base.graphics.Canvas;
import com.pro.base.graphics.Paint;
import com.pro.base.model.Drawable;
import com.pro.base.model.Spirit;

public class TestWorld {
	public static void main(String[] args)throws Exception {
		final World world = World.getWorld(800, 480);
		world.start();
		Drawable d1 = new MyDrawable();
		world.putDrawablePic(1, d1);
		world.setFps(60);
		new Thread(){
			public void run(){
				for(int i=0;i<1000;i++){
					world.setWorldCenter(300+i, 240);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
class MyDrawable implements Drawable{
	Bitmap[] img ;
	Bitmap pause;
	public MyDrawable() throws IOException{
		for(int i=1;i<img.length;i++){
			System.out.println("fish/fish13_0"+i+".png");
			img[i-1] = new Bitmap(ImageIO.read(new File("fish/fish13_0"+i+".png")));
		}
		
		pause = new Bitmap(ImageIO.read(new File("bg/setting.jpg")));
	}
	@Override
	public void onLoop(Canvas canvas, Paint paint, World world) {
		canvas.drawBitmap(img, 0, 0, paint);
		
	}

	@Override
	public void onPause(Canvas canvas, Paint paint, World world) {
		canvas.drawBitmap(pause, 0, 0, paint);
		
	}
	
}

class Fish extends Spirit{
	private int x = 800;
	private int angle = 0;
	public Fish() throws IOException{
		Bitmap[] img = new Bitmap[10];
		for(int i=1;i<img.length;i++){
			System.out.println("fish/fish13_0"+i+".png");
			img[i-1] = new Bitmap(ImageIO.read(new File("fish/fish13_0"+i+".png")));
		}
		
		setAnimation(img, 100);
		setLocal(800, 240);
		setScale(200, 200);
	}

	@Override
	public void onLoop(World world) {
		playAnimation(true);
		setLocal(x--,240);
		if(x<-303){
			x = 800;
		}
		setRotate(angle++, 15, 53);
	}

	@Override
	public void onPause(World world) {
		playAnimation(false);
		
	}

	
	
}