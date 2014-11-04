/**
 * 
 */
package com.healthslife.utils;

/**
 * @author Incredible
 *
 */
public class Converter {
	/**
	 * 计算跑步消耗的热量
	 * @param stepNumber:跑步的步数
	 * @return 消耗的卡路里
	 */
	public static float running(int stepNumber) {
		float sRet=(float) (stepNumber/24.8);
		return sRet;
	}
	
	/**
	 * 计算俯卧撑消耗的热量
	 * @param number：俯卧撑个数
	 * @return 消耗的卡路里
	 */
	public static float pushups(int number) {
		float sRet=0;
		sRet=(float)number;
		return sRet;
	}
	
	/**
	 * 计算仰卧起坐消耗的热量
	 * @param number：仰卧起坐的个数
	 * @return 消耗的卡路里
	 */
	public static float situps(int number) {
		float sRet=0;
		sRet=(float)(number*0.7);
		return sRet;
	}
	
	/**
	 * 计算下蹲消耗的热量
	 * @param number：下蹲的个数
	 * @return 下蹲消耗的卡路里的数量
	 */
	public static float squats(int number) {
		float sRet=0;
		sRet=(float)(number*0.5);
		return sRet;
	}
	
	/**
	 * 计算引体向上消耗的热量
	 * @param number：引体向上的个数
	 * @return 消耗的热量
	 */
	public static float pullups(int number) {
		float sRet=0;
		sRet=(float)(number*2.5);
		return sRet;
	}
	
	/**
	 * 计算瑜伽消耗的热量
	 * @param minutes：练习瑜伽的时间
	 * @return 消耗的热量
	 */
	public static float yoga(int minutes) {
		float sRet=0;
		sRet=(float)(minutes*0.3);
		return sRet;
	}
}
