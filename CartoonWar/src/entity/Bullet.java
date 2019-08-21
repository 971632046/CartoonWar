package entity;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;
import java.awt.Rectangle;

import action.ActionAble;
import client.GameClient;
import constant.Constant;
import util.GetImageUtil;
import util.SinglePlayer;

/**
* @ClassName: Bullet
* @Description:子弹类
* @author smh

* @date 2019年8月19日 下午2:29:01
*
*/
public class Bullet extends GameObj implements ActionAble{
	// 单词播放音乐的对象
	SinglePlayer singlePlayer = new SinglePlayer();
	private Integer speed;
	public GameClient gc;
	public boolean isGood;
	public Bullet() {
		
	}
	public Bullet(int x,int y,String imgName,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 5;
		this.gc = gc;
		this.isGood = isGood;
	}
	@Override
	public void move() {
		if(isGood) {
			x += speed;	
		}else {
			x -= speed;	
		}
		
		
	}
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		OutOfBounds();
	}
	// 子弹越界销毁
	public void OutOfBounds() {
		if(x>Constant.GAME_WIDTH || x<0) {
			gc.bullets.remove(this);
		}
		
	}
	// 随机生成道具
	Random random = new Random();
	// 子弹打怪物 
	// 攻击一个
	public boolean hitMonster(Mouse mouse) {
		Boom boom = null;
		if(this.getRec().intersects(mouse.getRec())&& this.isGood != mouse.isGood) {
			 boom = new Boom(mouse.x,mouse.y,gc,true);
			
				
			// 销毁自身
			if(mouse.isGood) {
				mouse.blood -= 10;
				gc.bullets.remove(this);
				if(mouse.blood == 0) {
					gc.mouses.remove(mouse);
					singlePlayer.play("sound/474.mp3");
					
				}
				 
			}else {
				// 移除敌人 
			gc.enemys.remove(mouse);
			// 移除子弹 
			gc.bullets.remove(this);
			// 生成一个道具出来
			if(random.nextInt(500)>400) {
				if(mouse instanceof Enemy) {
					Enemy enemy = (Enemy)mouse;
				}
			Prop prop = new Prop(mouse.x,mouse.y+Enemy.imgs[0].getHeight(null)/2,"img/prop.png");
			gc.props.add(prop);
			}
			gc.booms.add(boom);
			singlePlayer.play("sound/474.mp3");
			if(boom.isLive() == false) {
				gc.booms.remove(boom);
			}
			return true;
		}
		
		}
		return false;
	}
	// 攻击多个
	public boolean hitMonsters(List<Mouse> monsters) {
		if (monsters == null) {
			return false;
		}
		for (int i = 0; i<monsters.size(); i++) {
			if(hitMonster(monsters.get(i))) {
				return true;
			}
		}
		 return false;
	}
	
	// 获取子弹的矩形大小
	public Rectangle getRec() {
		return new Rectangle(x,y, this.img.getWidth(null),this.img.getHeight(null));
	}
	
	}

