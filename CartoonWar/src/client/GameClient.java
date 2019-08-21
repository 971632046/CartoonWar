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
* @Description: 游戏客户端
* @author smh
* @date 2019年8月17日 下午3:20:45
*/
public class GameClient extends Frame {
	
	// 创建一个mouse
	Mouse mouse = new Mouse(100,200,"img/plane1.jpg",this,true);
	// 创建一个子弹集合
	public List<Bullet> bullets = new ArrayList<>();
	// 创建一个mouse
	public List<Mouse> mouses = new ArrayList<>();
	// 创建一个背景图
	BackGround backImg = new BackGround(0,0,"img/BG.jpg");
	// 创建一个敌方
	Enemy enemy1 = new Enemy(1300,50,1,this,false);
	Enemy enemy2 = new Enemy(1300,400,1,this,false);
	Enemy enemy3 = new Enemy(1300,300,1,this,false);
	Enemy enemy4 = new Enemy(1300,200,1,this,false);
	Enemy enemy5 = new Enemy(1300,100,1,this,false);
	// 创建敌方集合
	public List<Mouse> enemys= new ArrayList<>();
	// 创建一个爆炸集合
	public List<Boom> booms = new ArrayList<>();
	// 创建一个道具出来
	public List<Prop> props = new ArrayList<Prop>();
	// 创建一个boss 集合
	public List<Boss> bosss = new ArrayList<>();
	// 解决图片闪烁的问题
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
	
	// 生成客户端
	public void launchFrame() {
		SoundPlayer soundPlayer = new SoundPlayer("sound/SovietMarch.mp3");
		soundPlayer.start();
		
		// 窗口大小
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		// 标题
		this.setTitle("飞机大战");
		// 设置是否能够显示
		this.setVisible(true);
		Image img = GetImageUtil.getImg("img/plane1.png");
		this.setIconImage(img);
		// 禁止最大化
		this.setResizable(false);
		// 窗口居中
		this.setLocationRelativeTo(null);
		// 关闭窗口
		this.addWindowListener(new WindowAdapter() {
			 @Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// 添加鼠标监听
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			}
		});
		// 添加键盘监听
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				mouse.keyPressed(e);	
			}	
			public void keyReleased(KeyEvent e) {
				mouse.keyReleased(e);
			}	
			});
		// 启动线程
		new paintThread().start();
		mouses.add(mouse);
		enemys.add(enemy1);
		enemys.add(enemy2);
		enemys.add(enemy3);
		enemys.add(enemy4);
		enemys.add(enemy5);
		// 添加boss
		bosss.add(new Boss(1300,350,this));
	}
	// 重写paint方法
	@Override
	public void paint(Graphics g) {
		backImg.draw(g);
		for(int i = 0;i<mouses.size();i++) {
			Mouse mouse2 = mouses.get(i);
			mouse2.draw(g);
			mouse2.containItems(props);
		}
		
		// 循环画出子弹
		for(int i=0; i<bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
			bullet.hitMonsters(enemys);
			bullet.hitMonsters(mouses);
		}
		g.drawString("当前子弹数"+bullets.size(),50,50);
		g.drawString("当前怪物数"+enemys.size(),50,100);
		g.drawString("当前爆炸数"+booms.size(),50,150);
		// 循环画敌人
		for(int i=0;i<enemys.size();i++){
			enemys.get(i).draw(g);
		}
		// 循环爆炸
		for (int i = 0; i<booms.size(); i++) {
			if(booms.get(i).isLive() == true) {
				booms.get(i).draw(g);
			}
		}
		// 循环画道具 
		for (int i = 0; i<props.size(); i++) {

				props.get(i).draw(g);
		}
		if(enemys.size() == 0) {
			// 循环boss集合
			for (int i = 0; i<bosss.size(); i++) {

				bosss.get(i).draw(g);
			
		}
		}
		
		
		}
	// 内部类
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
