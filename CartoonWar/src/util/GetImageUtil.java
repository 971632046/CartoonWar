package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


/**
* @ClassName: GetImageUtil
* @Description: ��ȡͼƬ�Ĺ�����
* @author smh
* @date 2019��8��17�� ����3:49:39
*
*/
public class GetImageUtil {
	//��ȡͼƬ�ķ���
	public static Image getImg(String imgName)  {
		// ����
		URL resource = GetImageUtil.class.getClassLoader().getResource(imgName);
		BufferedImage bufferedImage	= null;
		try {
			bufferedImage = ImageIO.read(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufferedImage;
	}

}
