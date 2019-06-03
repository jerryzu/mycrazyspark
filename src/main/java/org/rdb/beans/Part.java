package org.rdb.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Part {
	private Date PartDate;
	private Date NextDay;
	private String timeUnit = "MON";

	public Part(Date date) {
		this.PartDate = date;
	}

	public Part(Date date, String TimeUnit) {
		this.PartDate = date;
		this.timeUnit = TimeUnit;
	}

	public Date getPartDate() {
		return PartDate;
	}

	public void setPartDate(Date partDate) {
		PartDate = partDate;
	}

	public Date getNextDay() {

		Calendar calendar1 = new GregorianCalendar(2016, 0, 31); // 月份从零开始

		calendar1.setTime(this.PartDate);
		if (this.timeUnit.equals("MON") == true) {
			calendar1.add(Calendar.MONTH, 1);
			calendar1.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (this.timeUnit.equals("DAY") == true) {
			calendar1.add(Calendar.DATE, 1);
		}
		NextDay = calendar1.getTime();
		return NextDay;
	}
}
