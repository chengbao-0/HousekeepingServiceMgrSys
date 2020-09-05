/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：实体类
 * @Package: entity 
 * @author: chengbao_0  
 * @date: 2020-7-30 9:23:11 
 */
package entity;

/**
 * @ClassName HistoricalEvaluation
 * @Desc 历史评价记录类，只用于之间数据的传递不存在于数据库
 * @author chengbao_0
 * @Date 2020-7-30 9:23:11
 */
public class HistoricalEvaluation {
	private int clientID;//会员编号
	private double score;//评分
	private String evaluation;//评价内容
	//getter & setter
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
}
