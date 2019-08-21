package client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import constant.Constant;
import entity.Mouse;
import entity.Prop;
import entity.BackGround;
import entity.Boom;
import entity.Boss;
import entity.Bullet;
import entity.Enemy;
import util.GetImageUtil;
import util.SoundPlayer;

/**
* @ClassName: GameClient
* @Description: ��Ϸ�ͻ���
* @author smh
* @date 2019��8��17�� ����3:20:45
*/
public class GameClient extends Frame {
	
	// ����һ��mouse
	Mouse mouse = new Mouse(100,200,"img/plane1.jpg",this,true);
	// ����һ���ӵ�����
	public List<Bullet> bullets = new ArrayList<>();
	// ����һ��mouse
	public List<Mouse> mouses = new ArrayList<>();
	// ����һ������ͼ
	BackGround backImg = new BackGround(0,0,"img/BG.jpg");
	// ����һ���з�
	Enemy enemy1 = new Enemy(1300,50,1,this,false);
	Enemy enemy2 = new Enemy(1300,400,1,this,false);
	Enemy enemy3 = new Enemy(1300,300,1,this,false);
	Enemy enemy4 = new Enemy(1300,200,1,this,false);
	Enemy enemy5 = new Enemy(1300,100,1,this,false);
	// �����з�����
	public List<Mouse> enemys= new ArrayList<>();
	// ����һ����ը����
	public List<Boom> booms = new ArrayList<>();
	// ����һ�����߳���
	public List<Prop> props = new ArrayList<Prop>();
	// ����һ��boss ����
	public List<Boss> bosss = new ArrayList<>();
	// ���ͼƬ��˸������
	@Override
	public void update(Graphics g) {
		Image backImg = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		Graphics backg = backImg.getGraphics();
		Color color = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		backg.setColor(color);
		paint(backg);
		g.drawImage(backImg, 0,0, null);
	}
	
	// ���ɿͻ���
	public void launchFrame() {
		SoundPlayer soundPlayer = new SoundPlayer("sound/SovietMarch.mp3");
		soundPlayer.start();
		
		// ���ڴ�С
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		// ����
		this.setTitle("�ɻ���ս");
		// �����Ƿ��ܹ���ʾ
		this.setVisible(true);
		Image img = GetImageUtil.getImg("img/plane1.png");
		this.setIconImage(img);
		// ��ֹ���
		this.setResizable(false);
		// ���ھ���
		this.setLocationRelativeTo(null);
		// �رմ���
		this.addWindowListener(new WindowAdapter() {
			 @Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// ���������
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			}
		});
		// ��Ӽ��̼���
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				mouse.keyPressed(e);	
			}	
			public void keyReleased(KeyEvent e) {
				mouse.keyReleased(e);
			}	
			});
		// �����߳�
		new paintThread().start();
		mouses.add(mouse);
		enemys.add(enemy1);
		enemys.add(enemy2);
		enemys.add(enemy3);
		enemys.add(enemy4);
		enemys.add(enemy5);
		// ���boss
		bosss.add(new Boss(1300,350,this));
	}
	// ��дpaint����
	@Override
	public void paint(Graphics g) {
		backImg.draw(g);
		for(int i = 0;i<mouses.size();i++) {
			Mouse mouse2 = mouses.get(i);
			mouse2.draw(g);
			mouse2.containItems(props);
		}
		
		// ѭ�������ӵ�
		for(int i=0; i<bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
			bullet.hitMonsters(enemys);
			bullet.hitMonsters(mouses);
		}
		g.drawString("��ǰ�ӵ���"+bullets.size(),50,50);
		g.drawString("��ǰ������"+enemys.size(),50,100);
		g.drawString("��ǰ��ը��"+booms.size(),50,150);
		// ѭ��������
		for(int i=0;i<enemys.size();i++){
			enemys.get(i).draw(g);
		}
		// ѭ����ը
		for (int i = 0; i<booms.size(); i++) {
			if(booms.get(i).isLive() == true) {
				booms.get(i).draw(g);
			}
		}
		// ѭ�������� 
		for (int i = 0; i<props.size(); i++) {

				props.get(i).draw(g);
		}
		if(enemys.size() == 0) {
			// ѭ��boss����
			for (int i = 0; i<bosss.size(); i++) {

				bosss.get(i).draw(g);
			
		}
		}
		
		
		}
	// �ڲ���
	class paintThread extends Thread{
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}
	
}
