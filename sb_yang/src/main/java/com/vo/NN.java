package com.vo;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *	表示整个多层的图片
 * @author zhangzhen
 * @date 2022年9月19日
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NN {

	/**
	 * 	当前可点击的/启用的/亮色显示的/不被任何其他图片覆盖的
	 */
	private Set<N> liang;
//	private Set<N> buliang;

}
