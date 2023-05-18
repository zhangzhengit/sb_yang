package com.vo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.springframework.cache.interceptor.CacheInterceptor;

/**
 * 显示已点击的图片的队列
 *
 * @author zhangzhen
 * @date 2022年9月19日
 *
 */
public class QueueP extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int chongfu = 3;


	private final int length ;

	private final List<N> n;

	public List<N> getN() {
		return this.n;
	}

	public QueueP(final int length) {
		super();
		this.length = length;
		this.n = new ArrayList<>(length);
	}

	public void addn(final N n1) {
		this.n.add(n1);

		final long count = this.n.stream().filter(n2 -> n2.getName() == n1.getName()).count();
		if (count >= QueueP.chongfu) {
			this.n.removeIf(n -> n.getName() == n1.getName());
		}
	}

	@Override
	public void paint(final Graphics g) {
		super.paint(g);
		g.setColor(Color.YELLOW);

		for (int x = 1; x <= this.length; x++) {
			g.draw3DRect(CenterPP.FROM_X + (x - 1) * CenterPP.PP_WIDTH, CenterPP.FROM_Y + 1 * CenterPP.PP_HEIGTH, CenterPP.PP_WIDTH,
					CenterPP.PP_HEIGTH, true);
		}

		// 绘制选中的图片
		for (int i = 0; i < this.n.size(); i++) {
			g.drawImage(this.n.get(i).getImage(),
					CenterPP.FROM_X + (i) * CenterPP.PP_WIDTH,
					CenterPP.FROM_Y + 1 * CenterPP.PP_HEIGTH ,
					CenterPP.PP_WIDTH,
					CenterPP.PP_HEIGTH,
					Color.PINK, null);
		}

	}

}
