package com.semi.approval.document.service;

import static com.semi.common.JDBCTemplate.close;
import static com.semi.common.JDBCTemplate.commit;
import static com.semi.common.JDBCTemplate.getConnection;
import static com.semi.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.semi.admin.user.model.vo.Employee;
import com.semi.approval.approve.model.vo.ApprLine;
import com.semi.approval.document.dao.DocumentDao;
import com.semi.approval.document.vo.Document;
import com.semi.approval.document.vo.MyDocument;
import com.semi.approval.document.vo.SumEmpInfo;
import com.semi.common.dao.AddressDao;
import com.semi.common.service.CommonSeqService;
import com.semi.common.vo.Attachments;
public class DocumentService {

	//문서번호, 번호, 사원번호 불러옴
	public Document selectForm(int id) {
		Connection con = getConnection();
		Document document = new DocumentDao().selectForm(con, id);
		if(document != null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return document;
	}

	//주소록 불러오기
	public HashMap<Integer, ArrayList<SumEmpInfo>> selectDept() {
		Connection con = getConnection();
		HashMap<Integer, ArrayList<SumEmpInfo>> hmap = new DocumentDao().selectDept(con);
		if(hmap != null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return hmap;
	}
	//주소록 부서 불러오기
	public ArrayList<String> selectDeptList() {
		Connection con = getConnection();
		ArrayList<String> list = new AddressDao().selectDeptIdList(con);
		
		if(list != null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		
		return list;
	}
	//파일있는 문서삽입
	public int insertDocument(ArrayList<Object> list, ApprLine[] apprLine, ArrayList<Attachments> fileList) {
		Connection con = getConnection();
		int result = 0;
		int attachNo = new CommonSeqService(con).getFileSeq();
		int result1 = new DocumentDao().insertAttachments(con, attachNo, fileList);
		int result2 = new DocumentDao().insertAppr(con, apprLine, list);
		int result3 = new DocumentDao().insertDoc(con, list);
		int result4 = 0;
		Document document = (Document)list.get(0);
		if(document.getManageClass().equals("1")) {
			result4 = new DocumentDao().insertFOVODocument(con, attachNo, list);
		}else if(document.getManageClass().equals("2")) {
			result4 = new DocumentDao().insertFOEODocument(con, attachNo, list);
		}else {
			result4 = new DocumentDao().insertFOWODocument(con, attachNo, list);
		}
		if(result1 > 0 && result2 > 0 && result3 > 0 && result4 > 1) {
			commit(con);
			result = 1;
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	//파일없는 문서삽입
	public int insertDocument(ArrayList<Object> list, ApprLine[] apprLine) {
		Connection con = getConnection();
		int attachNo = 0;
		int result = 0;
		int result1 = new DocumentDao().insertAppr(con, apprLine, list);
		int result2 = new DocumentDao().insertDoc(con, list);
		int result3 = 0;
		Document document = (Document)list.get(0);
		if(document.getManageClass().equals("1")) {
			System.out.println("파일없는 휴가신청 실행");
			result3 = new DocumentDao().insertFXVODocument(con, attachNo, list);
		}else if(document.getManageClass().equals("2")) {
			System.out.println("파일없는 재직증명서 실행");
			result3 = new DocumentDao().insertFXEODocument(con, attachNo, list);
		}else {
			System.out.println("파일없는 업무계획서 실행");
			result3 = new DocumentDao().insertFXWODocument(con, attachNo, list);
		}
		if(result1 > 0 && result2 > 0 && result3 > 1) {
			commit(con);
			result = 1;
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	//내문서함 불러오기
	public ArrayList<MyDocument> selectList() {
		Connection con = getConnection();
		ArrayList<MyDocument> list = new DocumentDao().selectList(con);
		
		if(list != null) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return list;
	}
	//내문서함에서 결재할 문서 상신시 submission 하기
	public int updateDocumentList(String[] docNumList) {
		Connection con = getConnection();
		int result = new DocumentDao().updateDocumentList(con, docNumList);
		
		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	//결재할 문서 불러오기
	public ArrayList<MyDocument> selectSubmitList() {
		Connection con = getConnection();
		ArrayList<MyDocument> list = new DocumentDao().selectSubmitList(con);
		if(list != null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return list;
	}
	//결재할 결재자들 불러오기
	public ArrayList<ApprLine> selectSubmitApprList(int[] apprNum) {
		Connection con = getConnection();
		ArrayList<ApprLine> apprList = new DocumentDao().selectSubmitApprList(con, apprNum);
		
		if(apprList != null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return apprList;
	}
	//휴지통으로 보낼 문서들 변경
	public int sendTrashList(String[] docNumList) {
		Connection con = getConnection();
		int result = new DocumentDao().sendTrashList(con, docNumList);
		
		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	//반려함으로 보낼 문서들 변경
	public int sendReturn(String[] docNumList) {
		Connection con = getConnection();
		int result = new DocumentDao().sendReturn(con, docNumList);
		
		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}
	//반려함 불러오기
	public ArrayList<MyDocument> selectReturnDocumentList() {
		Connection con = getConnection();
		ArrayList<MyDocument> list = new DocumentDao().selectReturnDocumentList(con);
		
		if(list != null) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return list;
	}
	//상세페이지 선택된 문서 불러오기
	public Document selectOne(int num) {
		Connection con = getConnection();
		Document document = new DocumentDao().selectOne(con, num);
		
		if(document != null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return document;
	}
	//상세페이지 파일불러오기
	public Attachments selectFile(int num) {
		Connection con =getConnection();
		Attachments attachments = new DocumentDao().selectFile(con, num);
		
		if(attachments != null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return attachments;
	}
	//문서진행현황 불러오기
	public ArrayList<MyDocument> selectStatus(int empId) {
		Connection con = getConnection();
		ArrayList<MyDocument> list = new DocumentDao().selectStatus(con, empId);
		
		if(list != null) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return list;
	}
	//결재시 업데이트
	public int insertApprStatus(String[] docNumList, int apprNum, int apprOrder, int apprNo) {
		Connection con = getConnection();
		int tempOrder = new DocumentDao().selectApprNo(con, apprNo);
		int insertResult = 0;
		int updateResult = 0;
		
		if(apprOrder == 1 && tempOrder != 1) {
			insertResult = new DocumentDao().insertApprStatus(con, docNumList, apprNum, apprOrder, apprNo, tempOrder);
		}else if(apprOrder == 1 && tempOrder == 1) {
			insertResult = new DocumentDao().insertApprStatus(con, docNumList, apprNum, apprOrder, apprNo, tempOrder);			
			updateResult = new DocumentDao().updateApprDate(con, apprNo, apprOrder);
		}else if(apprOrder == 2 && tempOrder != 2) {
			insertResult = new DocumentDao().insertApprStatus(con, docNumList, apprNum, apprOrder, apprNo, tempOrder);
		}else if(apprOrder == 2 && tempOrder == 2) {
			insertResult = new DocumentDao().insertApprStatus(con, docNumList, apprNum, apprOrder, apprNo, tempOrder);
			updateResult = new DocumentDao().updateApprDate(con, apprNo, apprOrder);
		}else {
			insertResult = new DocumentDao().insertApprStatus(con, docNumList, apprNum, apprOrder, apprNo, tempOrder);			
			updateResult = new DocumentDao().updateApprDate(con, apprNo, apprOrder);
		}
		
		int result = 0;
		if(insertResult > 0) {
			commit(con);
			result += 1;
		}else {
			rollback(con);
		}
		if(updateResult > 0) {
			result += 1;
		}
		close(con);
		return result;
	}
	//결재 비밀번호 체크
	public boolean checkPassword(int empId, int password) {	
		Connection con = getConnection();
		boolean check = new DocumentDao().checkPassword(con, empId,  password);
		return check;
	}
}
