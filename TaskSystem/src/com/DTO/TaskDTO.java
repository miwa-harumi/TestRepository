package com.DTO;

//import java.sql.Date;

/**
 * Ordersテーブル用DTOクラス
 * @author ts0818
 *
 */
public class TaskDTO{
  /**
   * フィールド変数
   */
  private int id;
  private String title;
  private String content;
  private String time;
  
  /**
   * getter id
   * @return int id
   */
  public int getId() {
    return id;
  }
  
  /**
   * setter id
   * @param int id
   */
  public void setId(int id) {
    this.id = id;
  }
  
  /**
   * getter title
   * @return String title
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * setter title
   * @param String title
   */
  public void setTitle(String title) {
    this.title = title;
  }
  
  /**
   * getter content
   * @return String content
   */
  public String getContent() {
    return content;
  }
  
  /**
   * setter content
   * @param String content
   */
  public void setContent(String content) {
    this.content = content;
  }
  
  /**
   * getter order_count
   * @return Date time
   */
  public String getTime() {
    return time;
  }
  
  /**
   * setter time
   * @param Date time
   */
  public void setTime(String time) {
    this.time = time;
  }
}
