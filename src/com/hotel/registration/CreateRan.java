package com.hotel.registration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CreateRan {

	/** 
	 * ������������ţ���ǰ������ʱ����+��λ����� 
	 *  
	 * @return 
	 */  
	public static int CreateOrderNum(){
		
	        SimpleDateFormat simpleDateFormat;  
	  
	        simpleDateFormat = new SimpleDateFormat("MMdd");  
	  
	        Date date = new Date();  
	  
	        String str = simpleDateFormat.format(date);  
	  
	        Random random = new Random();  
	  
	        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// ��ȡ5λ�����  
	  
	        return Integer.parseInt(rannum + str);// ��ǰʱ��  
		
	}
	
	public  static String CreateDate(){
		SimpleDateFormat simpleDateFormat;  
		 
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
  
        Date date = new Date();  
        
        String str = simpleDateFormat.format(date);  
        return str;
	}
	
}
