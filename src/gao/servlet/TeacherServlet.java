package gao.servlet;

import java.io.IOException;

import gao.java.*;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TeacherServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");//设置编码方式避免中文乱码
		DBHandle dbh=new DBHandle();
		int pageSize=2;
		int pageCount=0;
		int pageNow=1;
		int rowCount=0;//声明四个局部变量,用于分页处理
		String limitSql="";
		String flag=request.getParameter("flag");
		String pageNowStr=request.getParameter("pageNow");
		if(pageNowStr==null||pageNowStr=="") pageNowStr="1";//默认pageNow=1，避免一些不必要的异常，如null（空）对象异常
		pageNow=Integer.parseInt(pageNowStr);
		HttpSession sess=request.getSession();
		String user=(String)sess.getAttribute("user");
		if(flag.equals("query")){
			String class_name=request.getParameter("Class_name");
			String cou_name=request.getParameter("Cou_name");			
			int class_ID=dbh.getID("select Class_ID from Class_tb where Class_name='"+class_name+"'");
			int cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+cou_name+"'");
			int tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name='"+user+"'");
			String queryScore="select * from(select stu_tb.stu_ID  "+"序号"+ ",stu_tb.Stu_ID,class_tb.Class_ID,cou_tb.Cou_ID,tea_tb.Tea_ID,score_tb.Score_score from  stu_tb "+
					"left join class_tb  on stu_tb.stu_class=class_tb.Class_ID "+
					"LEFT JOIN  tea_cou_class_tb  on tea_cou_class_tb.class_ID=stu_tb.stu_class   "  +
					"LEFT JOIN  score_tb          on stu_tb.Stu_ID=score_tb.stu_ID,"+
					"tea_tb,cou_tb  "+
					"where  tea_tb.tea_ID=tea_cou_class_tb.tea_ID   and cou_tb.Cou_ID=tea_cou_class_tb.Cou_ID ";
			String queryScoreWhere="";
			 limitSql="Stu_ID limit "+pageSize*(pageNow-1)+","+pageSize;

		  	ArrayList<Score> al=new ArrayList<Score>();
			if(class_name!=null||cou_name!=null){//用于判断是不是第一次显示
				if(class_name!=""){//用于判断是不是需要按条件进行查询
					if(cou_name!=""){//按班级和课程查询
					  if(class_name==null)
						  class_name="";
					  if(cou_name==null)
						  cou_name="";
						
					  queryScoreWhere="   AND  class_tb.Class_name = '"+class_name+"'  and cou_tb.cou_name= '"+cou_name+"'  and tea_tb.Tea_name= '"+user+"'  )";
					  al=dbh.getScore(queryScore+queryScoreWhere+limitSql);
		       		rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +" tmp )   tmp2 ");
					}else {//按班级和教师来查询						
						  if(class_name==null)
							  class_name="";
						  if(cou_name==null)
							  cou_name="";
							queryScoreWhere="   AND  class_tb.Class_name ='"+class_name+"'  and cou_tb.cou_name like '%"+cou_name+"%'  and tea_tb.Tea_name= '"+user+"'  )";
							  al=dbh.getScore(queryScore+queryScoreWhere+limitSql);
				       		rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +" tmp )   tmp2 ");	
					}					
				}
				else{
				 if(cou_name!=""){//按课程和教师查询
					  if(class_name==null)
						  class_name="";
					  if(cou_name==null)
						  cou_name="";
					 queryScoreWhere="   AND  class_tb.Class_name like '%"+class_name+"%'  and cou_tb.cou_name= '"+cou_name+"'  and tea_tb.Tea_name= '"+user+"'  )";
					  al=dbh.getScore(queryScore+queryScoreWhere+limitSql);
		       		rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +" tmp )   tmp2 ");	
				}else{//按教师查询	
					  if(class_name==null)
						  class_name="";
					  if(cou_name==null)
						  cou_name="";
						
						queryScoreWhere="   AND  class_tb.Class_name like '%"+class_name+"%'  and cou_tb.cou_name like '%"+cou_name+"%'  and tea_tb.Tea_name= '"+user+"'  )";
					  al=dbh.getScore(queryScore+queryScoreWhere+limitSql);
		       		rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +" tmp )   tmp2 ");	
					}				 
				}	
				request.setAttribute("class_name", class_name);
				request.setAttribute("cou_name", cou_name);
			}else{
				  if(class_name==null)
					  class_name="";
				  if(cou_name==null)
					  cou_name="";
				
				queryScoreWhere="   AND  class_tb.Class_name like '%"+class_name+"%'  and cou_tb.cou_name like '%"+cou_name+"%'  and tea_tb.Tea_name= '"+user+"'  )";
			  

				al=dbh.getScore(queryScore+queryScoreWhere+limitSql);
				
				rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +" tmp )   tmp2 ");	
				System.out.println("91"+"select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +" tmp )   tmp2 ");
				request.setAttribute("class_name", "");
				request.setAttribute("cou_name", "");				
			}			
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;
		    //通过rowCount和pageSize的关系来计算pageCount的值
		    
		    //得到所有的班级的名称，并将他们放入一个集合中 	
		    ArrayList<String> Cou_name=dbh.getAttribute("select distinct Cou_tb.Cou_name from Tea_tb,Cou_tb,Tea_Cou_Class_tb where Tea_tb.Tea_ID='"+tea_ID+
		    "' and Tea_tb.Tea_ID=Tea_Cou_Class_tb.Tea_ID and Tea_Cou_Class_tb.Cou_ID=Cou_tb.Cou_ID");
		    ArrayList<String> Class_name=dbh.getAttribute("select distinct Class_tb.Class_name from Tea_tb,Class_tb,Tea_Cou_Class_tb where Tea_tb.Tea_ID='"+tea_ID+
		    "' and Tea_tb.Tea_ID=Tea_Cou_Class_tb.Tea_ID and Tea_Cou_Class_tb.Class_ID=Class_tb.Class_ID");		    
		    request.setAttribute("Score", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");			
			request.setAttribute("Class_name", Class_name);			
			request.setAttribute("Cou_name", Cou_name);
			request.getRequestDispatcher("Teacher_Query.jsp").forward(request,response );
		}else if(flag.equals("title")){
			String cou_name=request.getParameter("Cou_name");			
			int cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+cou_name+"'");
			int tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name='"+user+"'");
			ArrayList<Title> al=new ArrayList<Title>();
			if(cou_name!=null){//用于判断是不是第一次显示				
					if(cou_name!=""){
						cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+cou_name+"'");
						al=dbh.getTitle("select * from (select  * from Title_tb where Cou_ID='"+cou_ID+"' and Tea_ID='"+tea_ID+"' )Title_ID limit "+pageSize*(pageNow-1)+","+pageSize);
						rowCount=dbh.getRowCount("select count(*) from Title_tb where Cou_ID='"+cou_ID+"' and Tea_ID='"+tea_ID+"'");
					}else {		
						cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name like '%"+cou_name+"%'");
						al=dbh.getTitle("select * from (select  * from Title_tb where Tea_ID='"+tea_ID+"') Title_ID  limit "+pageSize*(pageNow-1)+","+pageSize);
						rowCount=dbh.getRowCount("select count(*) from Title_tb where Tea_ID='"+tea_ID+"'");
					}					
					request.setAttribute("cou_name", cou_name);
				}				
			else{
				al=dbh.getTitle("select * from(select  * from Title_tb where Tea_ID='"+tea_ID+"' ) Title_ID  limit "+pageSize*(pageNow-1)+","+pageSize);
				rowCount=dbh.getRowCount("select count(*) from Title_tb where Tea_ID='"+tea_ID+"'");
				request.setAttribute("cou_name", "");				
			}			
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;
		    //通过rowCount和pageSize的关系来计算pageCount的值
		    
		    //得到所有的班级的名称，并将他们放入一个集合中 	
		    ArrayList<String> Cou_name=dbh.getAttribute("select distinct Cou_tb.Cou_name from Tea_tb,Cou_tb,Tea_Cou_Class_tb where Tea_tb.Tea_ID='"+tea_ID+
		    "' and Tea_tb.Tea_ID=Tea_Cou_Class_tb.Tea_ID and Tea_Cou_Class_tb.Cou_ID=Cou_tb.Cou_ID");
		    	    
		    request.setAttribute("Title", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");						
			request.setAttribute("Cou_name", Cou_name);
			request.getRequestDispatcher("Teacher_Title.jsp").forward(request,response );
		}
		else if(flag.equals("psdAlter")){
			 String old_psd=request.getParameter("old_psd");
			 String new_psd=request.getParameter("new_psd");			 
			 ArrayList<String> psd=dbh.getPassword("select Tea_psd from Tea_tb where Tea_name='"+user+"'");
			 boolean b=false;
			 for(int i=0;i<psd.size();i++){
				 if(old_psd.equals(psd.get(i))){
					 b=true;break;
				 }
			 }
			 if(b){
				 String sql="update Tea_tb set Tea_psd='"+new_psd+"' where Tea_name='"+user+"' and Tea_psd='"+old_psd+"'";
				 if(dbh.Update(sql)){
					 sess.setAttribute("message", "密码修改成功！");
					 request.getRequestDispatcher("T_Success.jsp").forward(request, response);
				 }
			 }else{
				 request.getRequestDispatcher("Teacher_psdAlter.jsp?flag=error").forward(request, response);
			 }
			 
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	
	}

}

