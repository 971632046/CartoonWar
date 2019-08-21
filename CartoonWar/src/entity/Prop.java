package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import action.ActionAble;
import constant.Constant;
import util.GetImageUtil;

/**
* @ClassName: Prop
* @Description: 道具类
* @author smh
* @date 2019年8月20日 下午6:50:27
*
*/
public class Prop extends GameObj implements ActionAble{
	public double theta = Math.PI/4;
	private int speed = 5;
	
	public Prop() {
		// TODO Auto-generated constructor stub
	}
	public Prop(int x,int y,String imgName) {
		this.x = x;
		this.y = y;
		this.img =GetImageUtil.getImg(imgName);
	}
	
	
	
	@Override
	public void move() {
		x += speed*Math.cos(theta); 
		y += speed*Math.sin(theta); 
		if(x<0 ||x>Constant.GAME_WIDTH-img.getWidth(null)) {
			theta = Math.PI-theta;
		}
		if(y<35 || y>Constant.GAME_HEIGHT-img.getHeight(null)) {
			theta = -theta;
		}
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, null);
		move();
	}
	// 拿到当前道具矩形
	public Rectangle getRec() {
		return new Rectangle(x,y, this.img.getWidth(null),this.img.getHeight(null));
	}

}
