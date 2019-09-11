package com.daselearn.admin.enums;

/**
 * 使用枚举表述常量数据字典
 * @author daniel
 * @date  2019年07月17日
 */
public enum AppointStateEnum {

	SUCCESS(1, "预约成功"), NO_NUMBER(0, "库存不足"), REPEAT_APPOINT(-1, "重复预约"), INNER_ERROR(-2, "系统异常"), NOTEXIST_BOOK(-3, "图书不存在");

	private int state;

	private String stateInfo;

	private AppointStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static AppointStateEnum stateOf(int index) {
		for (AppointStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println("AppointStateEnum.SUCCESS.stateInfo = " + AppointStateEnum.SUCCESS.stateInfo);
	}

}
