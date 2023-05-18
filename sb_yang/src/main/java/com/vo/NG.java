package com.vo;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.print.attribute.standard.MediaSize.NA;

import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.util.CollectionUtils;

/**
 *
 * 生成随机的图片
 *
 * @author zhangzhen
 * @date 2022年9月19日
 *
 */
public class NG {

	private static final double P = 1 - 0.7;
	private static final SecureRandom RANDOM = new SecureRandom();

	public static NN g() {
		final NN nn = new NN();

		final Set<N> liang = new HashSet<>();
		// 最下面的一层 一定是x * y个,每个图片正好放在最底层的格子里面
		for (int x = 1; x <= CenterPP.X; x++) {
			for (int y = 1; y <= CenterPP.Y; y++) {
				final String randomImageName = CenterPP.getRandomImageName();
				final N n = new N(randomImageName,(x -1) * CenterPP.PP_WIDTH, (y-1) * CenterPP.PP_HEIGTH, CenterPP.getRandomImage(randomImageName), null, null, 1, true, 0);
				liang.add(n);
			}
		}

		// 第2层，x和y各少一个,每个图片都会压在四个最底层给子的角上，各占四个最底层格子的四分之一分面积
		final int x2 = CenterPP.X - 1;
		final int y2 = CenterPP.Y - 1;
		final int index2 = 2;
		for (int x = 1; x <= x2; x++) {
			for (int y = 1; y <= y2; y++) {
				final double p = NG.RANDOM.nextDouble();
				if (p > NG.P) {
					final String name = CenterPP.getRandomImageName();
					final N n = new N(name,(x - 1) * CenterPP.PP_WIDTH + (CenterPP.PP_WIDTH / 2),
							(y - 1) * CenterPP.PP_HEIGTH + (CenterPP.PP_HEIGTH / 2), CenterPP.getRandomImage(name), null,
							null, index2, false, 1);

					final List<N> beifugai = index(liang, index2, n);
					if (!CollectionUtils.isEmpty(beifugai)) {
						n.setEnable(true);
						for (final N n2 : beifugai) {
							n2.setEnable(false);
						}
					}
					liang.add(n);

				}
			}
		}

		// 第3层，以此类推
		final int x3 = x2 - 1;
		final int y3 = y2 - 1;
		final int index3 = 3;
		for (int x = 1; x <= x3; x++) {
			for (int y = 1; y <= y3; y++) {
				final double p = NG.RANDOM.nextDouble();
				if (p > NG.P) {
					final String name = CenterPP.getRandomImageName();
					final N n = new N(name,(x -1) * CenterPP.PP_WIDTH + (CenterPP.PP_WIDTH ), (y-1) * CenterPP.PP_HEIGTH
							 + (CenterPP.PP_HEIGTH )
							,
							CenterPP.getRandomImage(name), null, null, index3, false, 2);
					final List<N> beifugai = index(liang, index3, n);
					if (!CollectionUtils.isEmpty(beifugai)) {
						n.setEnable(true);
						for (final N n2 : beifugai) {
							n2.setEnable(false);
						}
					}
					liang.add(n);
				}

			}
		}

		// 第4层，x和y各少一个,每个图片都会压在四个最底层给子的角上，各占四个最底层格子的四分之一分面积
		final int x4 =x3 - 1;
		final int y4 =y3 - 1;
		final int index4 = 4;
		for (int x = 1; x <= x4; x++) {
			for (int y = 1; y <= y4; y++) {
				final double p = NG.RANDOM.nextDouble();
				if (p > NG.P) {
					final String name = CenterPP.getRandomImageName();
					final N n = new N(name,(x - 1) * CenterPP.PP_WIDTH + CenterPP.PP_WIDTH + (CenterPP.PP_WIDTH / 2),
							(y - 1) * CenterPP.PP_HEIGTH + CenterPP.PP_HEIGTH + (CenterPP.PP_HEIGTH / 2),
							CenterPP.getRandomImage(name), null, null, index4, false, 3);
					final List<N> fugai = index(liang, index4, n);
					if(!CollectionUtils.isEmpty(fugai)) {
						for (final N n2 : fugai) {
							n2.setEnable(false);
						}
						n.setEnable(true);
					}
					liang.add(n);
				}
			}
		}


		// 第5层，x和y各少一个
		final int x5 =x4 - 1;
		final int y5 =y4 - 1;
		final int index5 = 5;
		for (int x = 1; x <= x5; x++) {
			for (int y = 1; y <= y5; y++) {
				final double p = NG.RANDOM.nextDouble();
				if (p > NG.P) {
					final String name = CenterPP.getRandomImageName();
					final N n = new N(name,(x - 1) * CenterPP.PP_WIDTH + CenterPP.PP_WIDTH + (CenterPP.PP_WIDTH ),
							(y - 1) * CenterPP.PP_HEIGTH + CenterPP.PP_HEIGTH + (CenterPP.PP_HEIGTH ),
							CenterPP.getRandomImage(name), null, null, index5, false, 4);
					final List<N> fugai = index(liang, index5, n);
					if(!CollectionUtils.isEmpty(fugai)) {
						for (final N n2 : fugai) {
							n2.setEnable(false);
						}
						n.setEnable(true);
					}
					liang.add(n);
				}
			}
		}


		// ok
		nn.setLiang(liang);
		return nn;
	}

	private static List<N> index(final Set<N> liang, final int index, final N n) {
		final List<N> beifugaide = liang.stream()
				.filter(n1 -> n1.getIndex() + 1 == index)
				.filter(
						n1 ->
							// 压右下角
							(n1.getX() + CenterPP.PP_WIDTH >= n.getX()
							&& n1.getX() <= n.getX()
							&&n1.getY() <= n.getY()
							&& n1.getY() + CenterPP.PP_HEIGTH >= n.getY())
							||
							// 压左下角
							(n1.getX() - CenterPP.PP_WIDTH < n.getX()
							&& n1.getX() >= n.getX()
							&&n1.getY() <= n.getY()
							&& n1.getY() + CenterPP.PP_HEIGTH >= n.getY())
							||
							// 压左上角
							(n1.getX() - CenterPP.PP_WIDTH <= n.getX()
							&& n1.getX() >= n.getX()
							&&n1.getY() >= n.getY()
							&& n1.getY() - CenterPP.PP_HEIGTH <= n.getY())
							||
							// 压右上角
							(n1.getX() + CenterPP.PP_WIDTH >= n.getX()
							&& n1.getX() <= n.getX()
							&&n1.getY() >= n.getY()
							&& n1.getY() - CenterPP.PP_HEIGTH <= n.getY())

						)
				.collect(Collectors.toList());
		return beifugaide;
	}

}
