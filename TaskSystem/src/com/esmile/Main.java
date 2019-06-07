package com.esmile;

import java.util.List;
import com.DAO.TaskDAO;
import com.DTO.TaskDTO;

public class Main {
	public static void main(String[] args) {

		// DAOクラスのインスタンスの生成
		TaskDAO dao = new TaskDAO();

		// findAll()メソッドの戻り値Db_DTOクラスのインスタンスが格納されたList
		List<TaskDTO> orders = dao.findAll(null);
		
		// Listの中のOrdersDTOクラスのインスタンスをループで処理
		for (TaskDTO order : orders) {
			System.out.println("id:" + order.getId());
			System.out.println("title:" + order.getTitle());
			System.out.println("content:" + order.getContent());
			System.out.println("time:" + order.getTime());
		}		
	}
}