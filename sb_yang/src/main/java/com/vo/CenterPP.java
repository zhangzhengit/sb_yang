
package com.vo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 *
 * @author zhangzhen
 * @date 2022年9月19日
 *
 */
public class CenterPP extends JPanel {

	public static final int Y = 12;

	public static final int X = 12;

	private static final long serialVersionUID = 1L;

	public static final int FROM_Y = 10;
	public static final int FROM_X = 100;
	public static final int PP_HEIGTH = 50;
	public static final int PP_WIDTH = 50;
	public static final int BORDER = 2;

	private final NN nn;

	public NN getNn() {
		return this.nn;
	}

	public CenterPP() {
		this.nn = NG.g();
	}

	@Override
	public void paint(final Graphics g) {

		super.paint(g);

		// 画出来的底部的格子
		final int x = CenterPP.X;
		final int y = CenterPP.Y;
		for (int yy = 1; yy <= y; yy++) {
			final int yyy = CenterPP.FROM_Y + (yy) * CenterPP.PP_HEIGTH;
			for (int xx = 1; xx <= x; xx++) {
				g.setColor(Color.BLACK);
				g.drawRect(CenterPP.FROM_X + (xx - 1) * CenterPP.PP_WIDTH, yyy, CenterPP.PP_WIDTH, CenterPP.PP_HEIGTH);
			}
		}

		final Set<N> liang = this.nn.getLiang();
		// 按照index层数来绘制
		final int maxIndex = liang.stream().mapToInt(N::getIndex).max().getAsInt();
		// 从index = 1 到maxIndex 分层绘制，最上面的一层设置为可用亮色显示
		for (int i = 1; i <= maxIndex; i++) {
			final int k = i;
			final Set<N> ceng = liang.stream().filter(n -> n.getIndex() == k).collect(Collectors.toSet());
			for (final N n : ceng) {
//				g.setColor(Color.BLACK);
				g.drawRect(CenterPP.FROM_X + n.getX(), CenterPP.FROM_Y + CenterPP.PP_WIDTH + n.getY(), CenterPP.PP_WIDTH, CenterPP.PP_HEIGTH);

				final BufferedImage image = n.getEnable() ? n.getImage() : aaa(n.getImage());
				g.drawImage(image,
							CenterPP.BORDER + CenterPP.FROM_X + n.getX(),
							CenterPP.BORDER + CenterPP.FROM_Y + CenterPP.PP_WIDTH  + n.getY(),
							CenterPP.PP_WIDTH - CenterPP.BORDER * 2,
							CenterPP.PP_HEIGTH - CenterPP.BORDER * 2,
							null);


				g.setColor(Color.BLACK);
				final int under = n.getUnder();
				g.drawString(String.valueOf(under), CenterPP.FROM_X +CenterPP.PP_WIDTH + n.getX() - 10,
						CenterPP.FROM_Y + CenterPP.PP_WIDTH + n.getY() + 45);
			}
		}

	}

	public static String getRandomImageName()   {
		final String[] a = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		final Random random = new Random();
		final int i = random.nextInt(a.length);

		final String name = a[i];

		return name;
	}

	public static BufferedImage getRandomImage(final String name)   {

		final BufferedImage bufferedImage = CenterPP.c1.get(name);
		if (bufferedImage != null) {
			return bufferedImage;
		}


		final URL url = CenterPP.class.getClassLoader().getResource("icon/" + name + ".jpg");
		final File file = new File(url.getFile());
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (final IOException e) {
			// FIXME 2022年9月19日 下午7:03:56 zhangzhen: 记得处理这里 TODO Auto-generated catch block
			e.printStackTrace();
		}

		aaa(image);
		CenterPP.c1.put(name, image);
		return image;
	}

	private static BufferedImage aaa(final BufferedImage image) {

		final BufferedImage i2 = CenterPP.c.get(image);
		if (i2 != null) {
			return i2;
		}

		final ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		final ColorConvertOp op = new ColorConvertOp(cs, null);
		final BufferedImage i3 = op.filter(image, null);
		CenterPP.c.put(image, i3);
		return i3;

	}

	private static final Map<String, BufferedImage> c1 = new WeakHashMap<>();
	private static final Map<BufferedImage, BufferedImage> c = new WeakHashMap<>();

}
