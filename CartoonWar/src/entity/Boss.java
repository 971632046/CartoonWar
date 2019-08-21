package entity;

import java.awt.Graphics;
import java.awt.Image;

import action.ActionAble;
import client.GameClient;
import constant.Constant;
import util.GetImageUtil;

public class Boss extends Mouse implements ActionAble {
	private boolean up =true;
   // 定义一个图片数组
	public Boss() {
		// TODO Auto-generated constructor stub
	}
	public Boss(int x, int y,GameClient gc) {
		this.x =x;
		this.y = y;
		this.gc =gc;
	}
	private static Image[] imgs = new Image[8];
	static {
		for(int i = 0;i<imgs.length;i++) {
			imgs[i] = GetImageUtil.getImg("img/boss1.jpg");
		}
	}	
	private int speed = 5;
public void move() {
	x -= speed;

	if(x<1000) {
		if(up) {
    	y -= speed;
    }
    if(!up) {
    	y += speed;
    }
		x = 1000;
		if(y>Constant.GAME_HEIGHT-imgs[0].getHeight(null)) {
			up =true;
		}
		if(y<30) {
			up =false;
		}
	}
}
	int count = 0;
	public void draw(Graphics g) {
		if(count >7) {
			count = 0;
		}
		g.drawImage(imgs[count++], x, y, null);
		move();
		
	}
}
