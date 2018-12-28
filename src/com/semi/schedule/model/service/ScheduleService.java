package com.semi.schedule.model.service;

import static com.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import com.semi.schedule.model.dao.ScheduleDao;
import com.semi.schedule.model.vo.Schedule;

public class ScheduleService {

	//전체 캘린더에 일정 가져오는 메소드
	public ArrayList<HashMap<String, Object>> selectAllSchedule(int empId) {
		Connection con=getConnection();
		ArrayList<HashMap<String, Object>> list=null;
		ArrayList<HashMap<String, Object>> list1=new ScheduleDao().selectMySchedule(con, empId);
		list=new ArrayList<HashMap<String, Object>>();
		if(list1!=null) {
			for(int i=0;i<list1.size();i++) {
				list.add(list1.get(i));
			}
			System.out.println("list1 : "+list1);
		}
		ArrayList<HashMap<String, Object>> list2=new ScheduleDao().selectTeamSchedule(con, empId);
		if(list2!=null) {
			for(int i=0;i<list2.size();i++) {
				list.add(list2.get(i));
			}
			System.out.println("list2 : "+list2.size());
		}
		ArrayList<HashMap<String, Object>> list3=new ScheduleDao().selectCompanySchedule(con, empId);
		if(list3!=null) {
			for(int i=0;i<list3.size();i++) {
				list.add(list3.get(i));
			}
			System.out.println("list3 : "+list3.size());
		}
		
		//System.out.println("list1/list2/list3 : "+list1.size()+'/'+list2.size()+'/'+list3.size());
		System.out.println("서비스 list : "+list);
		
		close(con);
		return list;
	}

	//내 일정 insert
	public int insertMySchedule(Schedule reqSche) {
		Connection con=getConnection();
		int result=new ScheduleDao().insertMySchedule(con, reqSche);
		
		close(con);
		return result;
	}

	//팀 일정 insert
	public int insertTeamSchedule(Schedule reqSche) {
		Connection con=getConnection();
		int result=new ScheduleDao().insertTeamSchedule(con, reqSche);		
		
		close(con);
		return result;
	}

	//회사 일정 insert
	public int insertCompanySchedule(Schedule reqSche) {
		Connection con=getConnection();
		int result=new ScheduleDao().insertCompanySchedule(con, reqSche);		
		
		close(con);
		return result;
	}

	//daySchedule 라벨 표시
	public Schedule selectDaySchedule(int calendarNo) {
		Connection con=getConnection();
		
		Schedule sche=new ScheduleDao().selectDaySchedule(con, calendarNo);
		close(con);
		return sche;
	}


	public int updateMyDaySchedule(Schedule reqSche) {
		Connection con=getConnection();
		int result=new ScheduleDao().updateMyDaySchedule(con, reqSche);
		return result;
	}

	public int updatTeamDaySchedule(Schedule reqSche) {
		Connection con=getConnection();
		int result=new ScheduleDao().updatTeamDaySchedule(con, reqSche);
		return result;
	}
	

	public int updateCompanyDaySchedule(Schedule reqSche) {
		Connection con=getConnection();
		int result=new ScheduleDao().updateCompanyDaySchedule(con, reqSche);
		return result;
	}

}