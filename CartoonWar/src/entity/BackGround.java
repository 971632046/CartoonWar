package entity;

import java.awt.Graphics;

import action.ActionAble;
import util.GetImageUtil;

/**
* @ClassName: BackGround
* @Description: 背景类
* @author smh
* @date 2019年8月19日 下午3:50:10
*
*/
public class BackGround extends GameObj implements ActionAble{
	 private Integer speed;

	public BackGround() {
		// TODO Auto-generated constructor stub
	}
	 public BackGround(int x, int y,String imgName) {
		 this.x = x;
		 this.y = y;
		 this.img = GetImageUtil.getImg(imgName);
		 this.speed = 2;
	 }
	
	@Override
	public void move() {
		x -= speed;
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		
	}

}
