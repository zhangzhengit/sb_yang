package com.vo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *	启动类
 *
 * @author zhangzhen
 * @date 2022年9月19日
 *
 */
// FIXME 2022年9月19日 下午11:34:47 zhanghen: 生成时判断必须每种图片的个数都能被QueueP.chongfu整数
// FIXME 2022年9月19日 下午11:36:38 zhanghen: 生成时判断必须每一层和压着的下一层或者下一层....的相同类型的图片个数能被QueueP.chongfu整数
public class F extends JFrame{

	private static final int QUEUE_LENGTH = 10;
	private static final int CP_HEIGHT = 750;
	private static final int CP_WIDTH = 700;
	private static final String TITLE = "sb_yang";
	private static final int HEIGHT = 980;
	private static final int WIDTH = 800;

	private static final long serialVersionUID = 1L;

	public static void main(final String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(() -> {
			final F f = new F();
			f.setVisible(true);
		});
	}

	public F() {
		System.out.println(java.time.LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t" + "F.F()");

		this.setTitle(F.TITLE);
		this.setSize(F.WIDTH, F.HEIGHT);
		this.setResizable(false);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		final JPanel aaaaaaaaaaa = new JPanel();
		aaaaaaaaaaa.setLayout(new BorderLayout());


		final CenterPP cPp = new CenterPP();
		cPp.setPreferredSize(new Dimension(F.CP_WIDTH, F.CP_HEIGHT));
		cPp.setBackground(Color.CYAN);
		aaaaaaaaaaa.add(cPp,BorderLayout.CENTER);


		// 底部panel
		final QueueP qp = new QueueP(F.QUEUE_LENGTH);
		qp.setBackground(Color.GRAY);
		qp.setPreferredSize(new Dimension(F.CP_WIDTH, 200));
		aaaaaaaaaaa.add(qp,BorderLayout.SOUTH);

		final Container cp = this.getContentPane();
		cp.add(aaaaaaaaaaa);

		this.cppclick(cPp, qp);
	}

	private void cppclick(final CenterPP cPp, final QueueP qp) {
		cPp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				final int x = e.getX();
				final int y = e.getY();


				final NN nn = cPp.getNn();
				final Set<N> liang = nn.getLiang();

				final Optional<N> findAny = liang.stream()
						.filter(n -> n.getEnable())
						.filter(n ->{

					final boolean ok = n.getX() <= x - CenterPP.FROM_X
								&& n.getX() + CenterPP.PP_WIDTH >= x - CenterPP.FROM_X
								&& n.getY() <= y - CenterPP.FROM_Y - CenterPP.PP_HEIGTH
								&& n.getY() + CenterPP.PP_HEIGTH >= y - CenterPP.FROM_Y - CenterPP.PP_HEIGTH
							;

							return ok;
				}).findAny();

				if (findAny.isPresent()) {

					liang.remove(findAny.get());

					// 点击了一个后，判断下面压着的要不要亮
					final N dianji = findAny.get();
					final Set<N> beiyade = liang.stream()
						.filter(n1 -> !n1.getEnable())
						.filter(beiya ->

							// 压右下角
							(beiya.getX() + CenterPP.PP_WIDTH > dianji.getX()
							&& beiya.getX() < dianji.getX()
							&&beiya.getY() < dianji.getY()
							&& beiya.getY() + CenterPP.PP_HEIGTH > dianji.getY())
							||
							// 压左下角
							(beiya.getX() - CenterPP.PP_WIDTH < dianji.getX()
							&& beiya.getX() > dianji.getX()
							&&beiya.getY() < dianji.getY()
							&& beiya.getY() + CenterPP.PP_HEIGTH > dianji.getY())
							||
							// 压左上角
							(beiya.getX() - CenterPP.PP_WIDTH < dianji.getX()
							&& beiya.getX() > dianji.getX()
							&&beiya.getY() > dianji.getY()
							&& beiya.getY() - CenterPP.PP_HEIGTH < dianji.getY())
							||
							// 压右上角
							(beiya.getX() + CenterPP.PP_WIDTH > dianji.getX()
							&& beiya.getX() < dianji.getX()
							&&beiya.getY() > dianji.getY()
							&& beiya.getY() - CenterPP.PP_HEIGTH < dianji.getY())

						).collect(Collectors.toSet());

					// 看被压的上面有没有其他图片，除了点击的这张以外的
					for (final N beiya : beiyade) {
						final Set<N> ss = liang.stream().filter(ll -> ll.getIndex() > beiya.getIndex()).collect(Collectors.toSet());
						final Set<N> yazhebeiyade = ss.stream().filter(dddd -> 	// 压右下角
								(beiya.getX() + CenterPP.PP_WIDTH > dddd.getX()
								&& beiya.getX() < dddd.getX()
								&&beiya.getY() < dddd.getY()
								&& beiya.getY() + CenterPP.PP_HEIGTH > dddd.getY())
								||
								// 压左下角
								(beiya.getX() - CenterPP.PP_WIDTH < dddd.getX()
								&& beiya.getX() > dddd.getX()
								&&beiya.getY() < dddd.getY()
								&& beiya.getY() + CenterPP.PP_HEIGTH > dddd.getY())
								||
								// 压左上角
								(beiya.getX() - CenterPP.PP_WIDTH < dddd.getX()
								&& beiya.getX() > dddd.getX()
								&&beiya.getY() > dddd.getY()
								&& beiya.getY() - CenterPP.PP_HEIGTH < dddd.getY())
								||
								// 压右上角
								(beiya.getX() + CenterPP.PP_WIDTH > dddd.getX()
								&& beiya.getX() < dddd.getX()
								&&beiya.getY() > dddd.getY()
								&& beiya.getY() - CenterPP.PP_HEIGTH < dddd.getY()))
							.collect(Collectors.toSet());
						if(yazhebeiyade.isEmpty()) {
							beiya.setEnable(true);
						}
					}

					cPp.repaint();

					qp.addn(findAny.get());
					qp.repaint();

					if (qp.getN().size() >= F.QUEUE_LENGTH) {
						JOptionPane.showMessageDialog(null, "失败");
						System.exit(0);
					}

					if (qp.getN().isEmpty() && liang.isEmpty()) {
						JOptionPane.showMessageDialog(null, "恭喜过关");
					}


				}

				super.mouseClicked(e);
			}
		});
	}


}
