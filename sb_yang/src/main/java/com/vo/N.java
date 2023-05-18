package com.vo;

import java.awt.image.BufferedImage;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *	一个图片节点
 *
 * @author zhangzhen
 * @date 2022年9月19日
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class N {

	private String name;

	private int x;
	private int y;


	private BufferedImage image;

	private Set<N> shang;

	private Set<N> xia;



	/**
	 * 此图片的上面没有任何图片覆盖的情况下，才亮
	 *
	 * @return
	 *
	 */
	public boolean liang() {
		final Set<N> shang2 = this.getShang();
		return shang2.isEmpty();
	}

	/**
	 * 	在第几层，最下面的为1
	 */
	private int index;

	/**
	 * 	此图片是否启用，没启用为灰色显示
	 */
	private Boolean enable = true;

	public void setEnable(final Boolean enable) {
		this.enable = enable;
	}

	public Boolean getEnable() {
		return this.enable;
	}

	/**
	 * 此图片下面还有几层
	 */
	private int under;

}
