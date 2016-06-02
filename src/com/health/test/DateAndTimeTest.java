package com.health.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import com.health.util.FileUtil;

public class DateAndTimeTest {
	public  static void main(String[] args) throws FileNotFoundException, ParseException{
		//Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		//String str = sdf.format(date);
		//long d = Long.parseLong(str);
		//java.sql.Date date2 = new java.sql.Date(2011);
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		//System.out.println(date);
		//System.out.println(date2)
		Date date2 = (Date) sdf.parse("2015-12-12");
		System.out.println(date2);
		System.out.println(stamp);
		//System.out.println(sdf.format(new Date()));
		String date = new String("2015-12-12 06:30:00");
		Timestamp time = Timestamp.valueOf(date);
		System.out.println(sdf.format(new Date()));
		System.out.println(time);
	}
}
