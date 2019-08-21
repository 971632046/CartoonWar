package entity;

import java.awt.Graphics;
import java.awt.Image;

import action.ActionAble;
import client.GameClient;
import util.GetImageUtil;

public class Boom extends GameObj implements ActionAble{
	private boolean isLive;
	private GameClient gc;
	
	public boolean isLive() {
		return isLive;
	}
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
	// ¶¯Ì¬³õÊ¼»¯±¬Õ¨Í¼
    private static Image[] boomImgs = new Image[9];
    static {
    	for (int i =0;i<7;i++) {
    		boomImgs [i] = GetImageUtil.getImg("img/boom.png");
    	}
    }
	public Boom() {
		
	}
	public Boom(int x,int y,GameClient gc,boolean isLive) {
		this.x = x;
		this.y = y;
		this.isLive = isLive;
		this.gc = gc;
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	int count = 0;
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if (isLive) {
			if(count>6) {
				this.setLive(false);
				gc.booms.remove(this);
				return;
			}
			g.drawImage(boomImgs[count++], x, y, null);
		}
	}

}
