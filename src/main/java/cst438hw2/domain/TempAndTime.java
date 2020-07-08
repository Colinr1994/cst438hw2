package cst438hw2.domain;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class TempAndTime {
	public double temp;
	public long time;
	public int timezone;
	
	public TempAndTime(double temp, long time, int timezone){
		this.temp = temp;
		this.time = time;
		this.timezone = timezone;
	}
	
	public double getTemp() { return temp; }
	public void setTemp(double temp) { this.temp = temp; }
	
	public double getFormTemp() {
		Double tempF = (temp-273.15) * (9.0/5.0) + 32;
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.valueOf(df.format(tempF));
	}
	
	
	public long getTime() { return time; }
	public void setTime(long time) { this.time = time; }
	
	public String getFormTime() {
		Instant instant = Instant.ofEpochSecond(time);
		ZoneOffset offset = ZoneOffset.ofTotalSeconds(timezone);
		OffsetDateTime offsetDate = instant.atOffset(offset);
		String timeFormat = "hh:mm a";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(timeFormat);
		return offsetDate.format(dtf);
	}
	
	
	public int getTimezone() { return timezone; } 
	public void seTimezone(int timezone) { this.timezone = timezone; }
	
 }
