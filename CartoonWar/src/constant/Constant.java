package constant;

import java.io.InputStream;
import java.util.Properties;

/**
* @ClassName: Constant
* @Description: 常量类(这里用一句话描述这个类的作用)
* @author smh

* @date 2019年8月17日 下午3:24:05
*
*/
public class Constant {
	 // 如何读取配置文件
	public static Properties prop = new Properties();
	static Integer Game_Width = null;
	static Integer Game_Height = null;
	static
	{   InputStream inStream = Constant.class.getResourceAsStream("/gameConfiguration.properties");
	try {
		prop.load(inStream);
		Game_Width = Integer.parseInt(prop.getProperty("Game_Width"));
		Game_Height = Integer.parseInt(prop.getProperty("Game_Height"));
	} catch (Exception e) {
		// TODO: handle exception
	}
		
	}
	// 定义宽高
	public static final int GAME_WIDTH = Game_Width;
	public static final int GAME_HEIGHT = Game_Height;
	
	
}
