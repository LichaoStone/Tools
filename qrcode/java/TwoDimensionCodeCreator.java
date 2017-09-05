import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
/**
 * 二维码生成工具类
 * @作者 lichao
 * @时间 2017年9月5日 上午10:11:33
 * @说明
 */
public class TwoDimensionCodeCreator {

	public static final int width = 300;
	public static final int height = 300;

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	/**
	 * 
	 * @param content
	 * @param imagePath
	 * @param logoPath
	 * @return
	 */
	private static String encode(String content, String imagePath,String... logoPath) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");// 设置编码类型为utf-8
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);// 设置二维码纠错能力级别为H（最高）

		BitMatrix byteMatrix = null;
		try {
			// 生成二维码
			byteMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width, height,hints);
			byteMatrix = updateBit(byteMatrix, 10);
			File file = new File(imagePath);
			if (logoPath != null && logoPath.length > 0) {//存在logo
				File img = new File(logoPath[0]);
				if (img.exists()) {
					writeToFile(content, byteMatrix, "png", file, logoPath[0]);
				} else {
					MatrixToImageWriter.writeToFile(byteMatrix, "png", file);
				}
			} else {
				MatrixToImageWriter.writeToFile(byteMatrix, "png", file);
			}
		} catch (Exception e) {
			e.printStackTrace();
			imagePath = "";
		}
		return imagePath;
	}

	
	public static void writeToFile(String content, BitMatrix matrix,
			String format, File file, String logoPath) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		Graphics2D gs = image.createGraphics();

		Image img = ImageIO.read(new File(logoPath));

		if (content.length() >= 70) {
			gs.setColor(Color.white);
			gs.fillRect(100, 100, 55, 55);
			gs.drawImage(img, 101, 101, 53, 53, null);
		} else {
			gs.setColor(Color.white);
			gs.fillRect(90, 90, 55, 55);
			gs.drawImage(img, 91, 91, 53, 53, null);
		}
		gs.dispose();
		img.flush();
		
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format "
					+ format + " to " + file);
		}
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}
	
	
	/**
	 * 二维码去白边
	 * @param matrix
	 * @param margin
	 * @return
	 */
	private static BitMatrix updateBit(BitMatrix matrix, int margin) {
		int tempM = margin * 2;
		int[] rec = matrix.getEnclosingRectangle();// 获取二维码图案的属性
		int resWidth = rec[2] + tempM;
		int resHeight = rec[3] + tempM;
		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);//按照自定义边框生成新的BitMatrix
		resMatrix.clear();
		for (int i = margin; i < resWidth - margin; i++) { //循环，将二维码图案绘制到新的bitMatrix中
			for (int j = margin; j < resHeight - margin; j++) {
				if (matrix.get(i - margin + rec[0], j - margin + rec[1])) {
					resMatrix.set(i, j);
				}
			}
		}
		return resMatrix;
	}

	/**
	 * main方法
	 * @param args
	 */
	public static void main(String[] args) {
		String content="http://z.qingk.cc/information-info/z/231fbda74b504a16977f280346a82507/aoduawtvvuqcspcsbdcupprxxpprqbcu/c9d5599b570fa12b23b13e9881062905/8526b68ddbc04f229a368780c92e312c";
		String qrcodeName="E:/image/twodimension/qrcode_2.0.png";
		String logPath="E:/image/twodimension/20170821084809.jpg";
		
		//无中心logo
		//encode(content,qrcodeName);
		//有中心logo
		encode(content,qrcodeName,logPath);
	}
}
