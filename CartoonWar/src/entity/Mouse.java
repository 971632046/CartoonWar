package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

import action.ActionAble;
import client.GameClient;
import constant.Constant;
import util.GetImageUtil;
import util.SinglePlayer;

/**
* @ClassName: Mouse
* @Description: 主角类
* @author smh

* @date 2019年8月19日 上午9:03:39
*
*/
public class Mouse extends GameObj implements ActionAble{
	SinglePlayer singlePlayer = new SinglePlayer();
	
	// 速度
	private int speed  ;
	// 方向布尔变量
	private boolean left,up,right,down;
	// 客户端拿过来
	public GameClient gc;
	// 判断好坏
	public boolean isGood;
	// 子弹等级
	public int BulletLevel = 1;
	// 添加血值
	public int blood;
	public Mouse() {
		
	}
	public Mouse (int x, int y, String imgName ,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 10;
		this.gc = gc;
		this.isGood = isGood;  
		this.blood = 100;
	}
	// 移动的方法
	@Override
	public void move() {
		if (left) {
			x -=speed;
		} 
		if (right) {
			x +=speed;
		} 
		if (up) {
			y -=speed;
		} 
		if (down) {
			y +=speed;
		} 
		outOfBound();
	}
	// 画主角
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, null);
		move();
		g.drawString("当前血量为："+blood, 10, 200);
		
	}
	// 处理主角的边界问题
	public void outOfBound() {
		if(y<=15) {
			y=15;
		}
		if(x<=5) {
			x=0;
		}
		
		if(x>=Constant.GAME_WIDTH-img.getWidth(null)) {
			x=Constant.GAME_WIDTH-img.getWidth(null);
		}
		
		if(y>=Constant.GAME_HEIGHT-img.getHeight(null)) {
			y=Constant.GAME_HEIGHT-img.getHeight(null);
		}
		
		
	}
	// 键盘摁下
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_J:
			fire();
			break;
		default:
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
			
		default:
			break;
	}
	}
	// 飞机的开火
	public void fire() {
		 Bullet b = new Bullet(x+this.img.getWidth(null),y,"img/plane"+BulletLevel+".png",gc,true);
		 gc.bullets.add(b);
		 singlePlayer.play("sound/14.mp3");
	}
	//矩形获取
	public Rectangle getRec() {
		return new Rectangle(x,y, this.img.getWidth(null),this.img.getHeight(null));
	}
	// 检测我方角色碰撞到道具
	public void containItem(Prop prop) {
		if(this.getRec().intersects(prop.getRec())) {
			// 移除道具
			gc.props.remove(prop);
			// 更改子弹等级
				this.BulletLevel = 8;
			}
			this.BulletLevel += 1;
			
		}
	
	// 检测我方角色碰撞到多个道具
	public void containItems(List<Prop> props) {
		if(props == null) {
			return;
		}else {
			for(int i = 0;i<props.size();i++) {
				containItem(props.get(i));
			}
			 
		}
	}
}
