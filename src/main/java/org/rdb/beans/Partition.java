package org.rdb.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLDate;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_PARTITIONS")
public class Partition {
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String PartType;
	@SQLDate
	private Date StartDate;
	@SQLDate
	private Date EndDate;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String Prefix;
	private List<Part> partlist;

	public Partition() {
		this.PartType = "NONE";// RANGE, LIST
		this.Prefix = "PART";
		partlist = new ArrayList<Part>();
	}

	public String getPartType() {
		return PartType;
	}

	public void setPartType(String partType) {
		this.PartType = partType;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date StartDate) {
		this.StartDate = StartDate;
	}

	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date EndDate) {
		this.EndDate = EndDate;
	}

	public String getPrefix() {
		return Prefix;
	}

	public void setPrefix(String prefix) {
		this.Prefix = prefix;
	}

	public List<Part> getPartlist() {
		partlist.clear();
		Calendar calendar1 = new GregorianCalendar(2016, 0, 31); //月份从零开始
		Calendar calendar2 = new GregorianCalendar(2016, 1, 31); //月份从零开始
		calendar1.setTime(this.StartDate);
		calendar2.setTime(this.EndDate);
		Date date1 = calendar1.getTime();
		Date date2 = calendar2.getTime();
//		Date date1 = StartDate;
//		Date date2 = EndDate;
		switch (this.PartType) {
		case "RANGE": {
			while (date1.getTime() < date2.getTime()) {
				final int lastDay = calendar1
						.getActualMaximum(Calendar.DAY_OF_MONTH);
				calendar1.set(Calendar.DAY_OF_MONTH, lastDay);
				date1 = calendar1.getTime();
				partlist.add(new Part(date1));
				calendar1.add(Calendar.MONTH, 1);
				date1 = calendar1.getTime();
			}
			break;
		}

		case "RANGE-DAY": {
			while (date1.getTime() < date2.getTime()) {
//				calendar1.set(Calendar.DAY_OF_MONTH,1);
				date1 = calendar1.getTime();
				partlist.add(new Part(date1, "DAY"));
				calendar1.add(Calendar.DATE, 1);
//				date1 = calendar1.getTime();
			}
			break;
		}
		case "LIST": {
			while (date1.getTime() < date2.getTime()) {
				date1 = calendar1.getTime();
				partlist.add(new Part(date1));
				calendar1.add(Calendar.MONTH, 1);
				date1 = calendar1.getTime();
			}
			break;
		}
		}
		return partlist;
	}

	public void setPartlist(List<Part> partlist) {
		this.partlist = partlist;
	}

}
