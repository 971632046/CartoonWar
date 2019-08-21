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
* @Description: ������
* @author smh

* @date 2019��8��19�� ����9:03:39
*
*/
public class Mouse extends GameObj implements ActionAble{
	SinglePlayer singlePlayer = new SinglePlayer();
	
	// �ٶ�
	private int speed  ;
	// ���򲼶�����
	private boolean left,up,right,down;
	// �ͻ����ù���
	public GameClient gc;
	// �жϺû�
	public boolean isGood;
	// �ӵ��ȼ�
	public int BulletLevel = 1;
	// ���Ѫֵ
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
	// �ƶ��ķ���
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
	// ������
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y, null);
		move();
		g.drawString("��ǰѪ��Ϊ��"+blood, 10, 200);
		
	}
	// �������ǵı߽�����
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
	// ��������
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
	// �ɻ��Ŀ���
	public void fire() {
		 Bullet b = new Bullet(x+this.img.getWidth(null),y,"img/plane"+BulletLevel+".png",gc,true);
		 gc.bullets.add(b);
		 singlePlayer.play("sound/14.mp3");
	}
	//���λ�ȡ
	public Rectangle getRec() {
		return new Rectangle(x,y, this.img.getWidth(null),this.img.getHeight(null));
	}
	// ����ҷ���ɫ��ײ������
	public void containItem(Prop prop) {
		if(this.getRec().intersects(prop.getRec())) {
			// �Ƴ�����
			gc.props.remove(prop);
			// �����ӵ��ȼ�
				this.BulletLevel = 8;
			}
			this.BulletLevel += 1;
			
		}
	
	// ����ҷ���ɫ��ײ���������
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
