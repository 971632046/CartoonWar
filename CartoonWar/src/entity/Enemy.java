package entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import action.ActionAble;
import client.GameClient;
import util.GetImageUtil;

public class Enemy extends Mouse implements ActionAble{
	private Integer enemyType;
	private Integer speed;
	private GameClient gc;
	public static Image[] imgs = {
			GetImageUtil.getImg("img/plane1.png"),
			GetImageUtil.getImg("img/plane1.png"),
			GetImageUtil.getImg("img/plane1.png"),
			GetImageUtil.getImg("img/plane1.png")
			
	}; 
	public Enemy() {
		
	}
	public Enemy(int x,int y, int enemyType,GameClient gc,boolean isGood){
		this.x = x;
		this.y = y;
		this.enemyType = enemyType;
		this.speed =1;
		this.gc = gc;
		this.isGood = isGood; 
	}
	public void move() {
		x -= speed;
	}
	int count =0;

	public void draw(Graphics g) {
		if(count > 3) {
			count = 0;
		}
		g.drawImage(imgs[count++], x, y, null);
		move();
		if(random.nextInt(500)>490) {
			fire();
		}
		
	}
	// 随机数
	Random  random = new Random();
	
	// 敌军开火
	public void fire () {
		Bullet bullet = new Bullet(x,y+imgs[0].getHeight(null)/2,"img/bullet.jpg",gc,false);
		gc.bullets.add(bullet);
		singlePlayer.play("sound/14.mp3");
	}
	// 获取怪物的矩形大小
	public Rectangle getRec() {
		return new Rectangle(x,y, this.imgs[0].getWidth(null),this.imgs[0].getHeight(null));
	}
	
}
