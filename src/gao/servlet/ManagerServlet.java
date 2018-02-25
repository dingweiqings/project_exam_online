package gao.servlet;

import java.io.IOException;
import gao.java.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManagerServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	//response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");//设置编码方式避免中文乱码
		request.setCharacterEncoding("utf-8");
		DBHandle dbh=new DBHandle();
		int pageSize=2;
		int pageCount=0;
		int pageNow=1;
		
		System.out.println("I am the value fo pageSize "+ pageSize);
		System.out.println("select  * from Tea_tb  Tea_ID limit "+pageSize*pageNow+","+pageSize);
		String limitSql=" limit "+pageSize*(pageNow-1)+","+pageSize;
		int rowCount=0;//声明四个局部变量,用于分页处理
		String flag=request.getParameter("flag");
		String pageNowStr=request.getParameter("pageNow");
		if(pageNowStr==null||pageNowStr=="") pageNowStr="1";//默认pageNow=1，避免一些不必要的异常，如null（空）对象异常
		pageNow=Integer.parseInt(pageNowStr);
		HttpSession sess=request.getSession();
		String user=(String)sess.getAttribute("user");
		if(flag.equals("teacher")){			
			String name=request.getParameter("name");//得到模糊查询中输入的名字
		  	ArrayList<Teacher> al=new ArrayList<Teacher>();
			if(name!=null){//用于判断是不是第一次显示，即通过理主页面的学生管理链接而来
				if(name!=""){//用于判断是不是需要按条件进行查询					
						al=dbh.getTeacher("select  * from Tea_tb where Tea_name like '%"+name+"%'  Tea_ID  limit  "+pageSize*(pageNow-1)+","+pageSize);
						rowCount=dbh.getRowCount("select count(*) from Tea_tb where Tea_name like '%"+name+"%'");
				}else{//无条件查询，显示所有教师信息
					al=dbh.getTeacher("select * from Tea_tb  Tea_ID  limit  "+pageSize*(pageNow-1)+","+pageSize);
				    rowCount=dbh.getRowCount("select count(*) from Tea_tb");
				}	
				request.setAttribute("name", name);
			}else{//第一次显示所有教师信息。
				System.out.println("select * from Tea_tb  Tea_ID limit "+pageSize*(pageNow-1)+","+pageSize);
				al=dbh.getTeacher("select  * from Tea_tb  Tea_ID limit "+pageSize*(pageNow-1)+","+pageSize);
			    rowCount=dbh.getRowCount("select count(*) from Tea_tb");
			    request.setAttribute("name", "");
			}			
		    if(rowCount%pageSize==0) 
		    	pageCount=rowCount/pageSize;
		    else 
		    	pageCount=rowCount/pageSize+1;
		    //通过rowCount和pageSize的关系来计算pageCount的值	   
		    request.setAttribute("Teacher", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");	
			System.out.print("select * from Tea_tb  Tea_ID limit "+pageSize*(pageNow-1)+","+pageSize);
			System.out.println("select * from Tea_tb  Tea_ID limit "+pageSize*(pageNow-1)+","+pageSize);
         			request.getRequestDispatcher("Manager_Teacher.jsp").forward(request,response );			
		}else if(flag.equals("student")){
			String class_name=request.getParameter("Class_name");
			int class_ID=dbh.getID("select Class_ID from Class_tb where Class_name='"+class_name+"'");
			//得到按班级查询的班级名并据此查询其ID号
			String name=request.getParameter("name");//得到模糊查询中输入的名字
		  	ArrayList<Student> al=new ArrayList<Student>();
			if(class_name!=null||name!=null){//用于判断是不是第一次显示，即通过管理主页面的学生管理链接而来
				if(class_name!=""||name!=""){//用于判断是不是需要按条件进行查询
					if(class_name==""){//按名字进行模糊查询
						al=dbh.getStudent("select  * from Stu_tb where Stu_name like '%"+name+"%' and Stu_ID limit "+pageSize*(pageNow-1)+","+pageSize);
						rowCount=dbh.getRowCount("select count(*) from Stu_tb where Stu_name like '%"+name+"%'");
					}else if(name==""){//按班级进行查询
						al=dbh.getStudent("select  * from Stu_tb where Stu_class= '"+class_ID+"' and Stu_ID limit "+pageSize*(pageNow-1)+","+pageSize);
						rowCount=dbh.getRowCount("select count(*) from Stu_tb where Stu_class='"+class_ID+"'"); 
					}else{//按班级和名字同时进行查询
						al=dbh.getStudent("select  * from Stu_tb where Stu_class='"+class_ID+"' and Stu_name like '%"+name+"%' and Stu_ID limit "+pageSize*(pageNow-1)+","+pageSize);
						rowCount=dbh.getRowCount("select count(*) from Stu_tb where Stu_class='"+class_ID+"' and Stu_name like '%"+name+"%'");
					}
				}else{//无条件查询，显示所有学生信息
					al=dbh.getStudent("select * from Stu_tb where Stu_ID limit  "+pageSize*(pageNow-1)+","+pageSize);
				    rowCount=dbh.getRowCount("select count(*) from Stu_tb");
				}
				request.setAttribute("name", name);
				request.setAttribute("class", class_name );
			}else{//第一次显示所有学生信息。
				al=dbh.getStudent("select * from Stu_tb  Stu_ID limit  "+pageSize*(pageNow-1)+","+pageSize);
			    rowCount=dbh.getRowCount("select count(*) from Stu_tb");
			    request.setAttribute("name", "");
				request.setAttribute("class", "");
			}			
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;
		    //通过rowCount和pageSize的关系来计算pageCount的值
		    ArrayList<Banji> Banji=dbh.getBanji("select distinct * from class_tb");
		    Iterator<Banji> it=Banji.iterator();
		    while(it.hasNext()){
		    	Banji banji=it.next();
		    	System.out.println(banji.getClass_name()+"  "+banji.getClass_ID());
		    	
		    }
	
		    ArrayList<String>className=dbh.getAttribute("select Class_name from class_tb");
		    //得到所有的班级的名称，并将他们放入一个集合中 		   
		    request.setAttribute("Student", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");
			request.setAttribute("Banji", Banji);	
			request.setAttribute("Class_name", className);
			request.getRequestDispatcher("Manager_Student.jsp").forward(request,response );
		}else if(flag.equals("course")){			
			String name=request.getParameter("name");//得到模糊查询中输入的名字
		  	ArrayList<Course> al=new ArrayList<Course>();
			if(name!=null){//用于判断是不是第一次显示，即通过管理主页面的学生管理链接而来
				if(name!=""){//用于判断是不是需要按条件进行查询					
						al=dbh.getCourse("select  * from Cou_tb where Cou_name like '%"+name+"%' and Cou_ID limit "+pageSize*(pageNow-1)+","+pageSize);
						rowCount=dbh.getRowCount("select count(*) from Cou_tb where Cou_name like '%"+name+"%'");
				}else{//无条件查询，显示所有学生信息
					al=dbh.getCourse("select  * from Cou_tb where Cou_ID limit "+pageSize*(pageNow-1)+","+pageSize);
				    rowCount=dbh.getRowCount("select count(*) from Cou_tb");
				}
				request.setAttribute("name", name);	
			}else{//第一次显示所有学生信息。
				al=dbh.getCourse("select  * from Cou_tb where Cou_ID limit "+pageSize*(pageNow-1)+","+pageSize);
			    rowCount=dbh.getRowCount("select count(*) from Cou_tb");
			    request.setAttribute("name", "");	
			}			
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;
		    //通过rowCount和pageSize的关系来计算pageCount的值	
		    request.setAttribute("Course", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");	
			//System.out.print("hah hehe");
			request.getRequestDispatcher("Manager_Course.jsp").forward(request,response );
			
		}else if(flag.equals("class")){			
			String name=request.getParameter("name");//得到模糊查询中输入的名字
			ArrayList<Banji> al=new ArrayList<Banji>();
			if(name!=null){//用于判断是不是第一次显示，即通过管理主页面的学生管理链接而来
				if(name!=""){//用于判断是不是需要按条件进行查询					
						al=dbh.getBanji("select  * from Class_tb where Class_name like '%"+name+"%' and Class_ID limit "+pageSize*(pageNow-1)+","+pageSize);
						rowCount=dbh.getRowCount("select count(*) from Class_tb where Class_name like '%"+name+"%'");
				}else{//无条件查询，显示所有学生信息
					al=dbh.getBanji("select  * from Class_tb where Class_ID limit "+pageSize*(pageNow-1)+","+pageSize);
				    rowCount=dbh.getRowCount("select count(*) from Class_tb");
				}
				request.setAttribute("name", name);				
			}else{//第一次显示所有学生信息。
				al=dbh.getBanji("select  * from Class_tb where Class_ID limit "+pageSize*(pageNow-1)+","+pageSize);
			    rowCount=dbh.getRowCount("select count(*) from Class_tb");
			    request.setAttribute("name", "");
			}			
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;
		    //通过rowCount和pageSize的关系来计算pageCount的值	 
		    request.setAttribute("Banji", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");	
			//System.out.print("hah hehe");
			request.getRequestDispatcher("Manager_Class.jsp").forward(request,response );
			
		}else if(flag.equals("query")){
			
			
			String 	class_name=request.getParameter("Class_name");

			System.out.println("175"+class_name);
			String cou_name=request.getParameter("Cou_name");
			String tea_name=request.getParameter("Tea_name");
			String stu_name=request.getParameter("Stu_name");
			response.setCharacterEncoding("UTF-8");//设置编码方式避免中文乱码
			int class_ID=dbh.getID("select Class_ID from Class_tb where Class_name='"+class_name+"'");
			int cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+cou_name+"'");
			int tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name='"+tea_name+"'");	
			int stu_ID=dbh.getID("select Stu_ID from stu_tb where Stu_name='"+stu_name+"'");
			///查询成绩
			String queryScore="select * from(select stu_tb.stu_ID  "+"序号"+ ",stu_tb.Stu_ID,class_tb.Class_ID,cou_tb.Cou_ID,tea_tb.Tea_ID,score_tb.Score_score from  stu_tb "+
					"left join class_tb  on stu_tb.stu_class=class_tb.Class_ID "+
					"LEFT JOIN  tea_cou_class_tb  on tea_cou_class_tb.class_ID=stu_tb.stu_class   "  +
					"LEFT JOIN  score_tb          on stu_tb.Stu_ID=score_tb.stu_ID,"+
					"tea_tb,cou_tb  "+
					"where  tea_tb.tea_ID=tea_cou_class_tb.tea_ID   and cou_tb.Cou_ID=tea_cou_class_tb.Cou_ID ";
			String queryScoreWhere="";
		 limitSql="Stu_ID limit "+pageSize*(pageNow-1)+","+pageSize;
		  	ArrayList<Score> al=new ArrayList<Score>();
			if(class_name!=null||cou_name!=null||tea_name!=null||stu_name!=null){//用于判断是不是第一次显示，即通过管理主页面的学生管理链接而来
				if(class_name!=""){//用于判断是不是需要按条件进行查询
					if(cou_name!=""){//按班级和课程查询
						if(stu_name==null)
							stu_name="";
						if(class_name==null)
							class_name="";
						if(cou_name==null)
							cou_name="";
						if(tea_name==null)
							tea_name="";
					queryScoreWhere="  and stu_tb.stu_name like '%"+stu_name+"%' AND  class_tb.Class_name='"+class_name+"'  and cou_tb.cou_name='"+cou_name+"'  and tea_tb.Tea_name like '%"+tea_name+"%'  )";
					System.out.println(queryScore+queryScoreWhere+limitSql);	
					al=dbh.getScore(queryScore+queryScoreWhere+limitSql);
			
					rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +" tmp )   tmp2 ");
					}
					else {
						if(tea_name!=""){//按班级和教师查询		
							if(stu_name==null)
								stu_name="";
							if(class_name==null)
								class_name="";
							if(cou_name==null)
								cou_name="";
							if(tea_name==null)
								tea_name="";
							queryScoreWhere="  and stu_tb.stu_name like '%"+stu_name+"%' AND  class_tb.Class_name='"+class_name+"'  and cou_tb.cou_name like '%"+cou_name+"%'  and tea_tb.Tea_name ='"+tea_name+"'  )";

							al=dbh.getScore(queryScore+queryScoreWhere+limitSql);
							rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +" tmp )   tmp2 ");
						}else{//按班级查询
							if(stu_name==null)
								stu_name="";
							if(class_name==null)
								class_name="";
							if(cou_name==null)
								cou_name="";
							if(tea_name==null)
								tea_name="";

						queryScoreWhere="  and stu_tb.stu_name like '%"+stu_name+"%' AND  class_tb.Class_name='"+class_name+"'  and cou_tb.cou_name like '%"+cou_name+"%'  and tea_tb.Tea_name like '%"+tea_name+"%'  )";

						al=dbh.getScore(queryScore+queryScoreWhere+limitSql);
						rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +"  tmp )   tmp2 ");
						}
					}
				}
			 else{
				 if(cou_name!=""){//按课程和教师查询
					 if(tea_name!=""){
							if(stu_name==null)
								stu_name="";
							if(class_name==null)
								class_name="";
							if(cou_name==null)
								cou_name="";
							if(tea_name==null)
								tea_name="";
							
						 queryScoreWhere="  and stu_tb.stu_name like '%"+stu_name+"%' AND  class_tb.Class_name  like '%"+class_name+"%'  and cou_tb.cou_name= '"+cou_name+"'  and tea_tb.Tea_name = '"+tea_name+"'  )";

							al=dbh.getScore(queryScore+queryScoreWhere+limitSql);
							rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +" tmp )   tmp2 ");
					 }else{//按课程查询
							if(stu_name==null)
								stu_name="";
							if(class_name==null)
								class_name="";
							if(cou_name==null)
								cou_name="";
							if(tea_name==null)
								tea_name="";
						 queryScoreWhere="  and stu_tb.stu_name like '%"+stu_name+"%' AND  class_tb.Class_name  like '%"+class_name+"%'  and cou_tb.cou_name= '"+cou_name+"'  and tea_tb.Tea_name like '%"+tea_name+"%'  )";
							rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +" tmp )   tmp2 ");

							al=dbh.getScore(queryScore+queryScoreWhere+limitSql);
					 }
				 }
				else{//按教师查询
					if(tea_name!=""){
						if(stu_name==null)
							stu_name="";
						if(class_name==null)
							class_name="";
						if(cou_name==null)
							cou_name="";
						if(tea_name==null)
							tea_name="";
						dbh.setString(stu_name,class_name,cou_name,tea_name);
						 queryScoreWhere="  and stu_tb.stu_name like '%"+stu_name+"%' AND  class_tb.Class_name  like '%"+class_name+"%'  and cou_tb.cou_name like '%"+cou_name+"%'  and tea_tb.Tea_name = '"+tea_name+"'  )";
						 System.out.println("236");
							al=dbh.getScore(queryScore+queryScoreWhere+limitSql);
							rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +" tmp )  tmp2 ");
					}
					else{
						if(stu_name==null)
							stu_name="";
						if(class_name==null)
							class_name="";
						if(cou_name==null)
							cou_name="";
						if(tea_name==null)
							tea_name="";
						 queryScoreWhere="  and stu_tb.stu_name like '%"+stu_name+"%' AND  class_tb.Class_name  like '%"+class_name+"%'  and cou_tb.cou_name like '%"+cou_name+"%'  and tea_tb.Tea_name like '%"+tea_name+"%'  )";
						 //  System.out.println(queryScore+" )  "+limitSql);
							al=dbh.getScore(queryScore+queryScoreWhere+limitSql);
							System.out.println("245"+queryScore+queryScoreWhere+limitSql);
							rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +" tmp  ) tmp2 ");
						//	System.out.println("246" +"  "+"select count(*)   from  ("+queryScore+"  "+ queryScoreWhere +"  ) tmp ) tmp2 ");
					}
				 }
				 
				}
				request.setAttribute("class_name", class_name);
				request.setAttribute("tea_name",tea_name);
				request.setAttribute("cou_name", cou_name);
			}else{//第一次显示所有信息。
				if(stu_name==null)
					stu_name="";
				if(class_name==null)
					class_name="";
				if(cou_name==null)
					cou_name="";
				if(tea_name==null)
					tea_name="";

			queryScoreWhere="  and stu_tb.stu_name like '%"+stu_name+"%' AND  class_tb.Class_name  like '%"+class_name+"%'  and cou_tb.cou_name like '%"+cou_name+"%'  and tea_tb.Tea_name like '%"+tea_name+"%'  )";
                  
					al=dbh.getScore(queryScore+queryScoreWhere+limitSql);		
					System.out.println(queryScore+queryScoreWhere+limitSql);
					rowCount=dbh.getRowCount("select count(*)   from  ("+queryScore+"  ) tmp ) tmp2 ");
			//	System.out.println("select count(*)   from  ("+queryScore+"  ) tmp ) tmp2 ");
				request.setAttribute("class_name", "");
				request.setAttribute("cou_name", "");
				request.setAttribute("tea_name","");
				//System.out.println("第一次显示所有信息a1.size:"+al.size());
				//System.out.println("264"+queryScore+queryScoreWhere+limitSql);
			}			
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;
		    //通过rowCount和pageSize的关系来计算pageCount的值
		    ArrayList<String> Class_name=dbh.getAttribute("select Class_name from Class_tb");
		    ArrayList<String> Tea_name=dbh.getAttribute("select Tea_name from Tea_tb");
		    ArrayList<String> Cou_name=dbh.getAttribute("select Cou_name from Cou_tb");
		    //得到所有的班级的名称，并将他们放入一个集合中 	
		
		    request.setAttribute("Score", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");			
			request.setAttribute("Class_name", Class_name);
			request.setAttribute("Tea_name",Tea_name);
			request.setAttribute("Cou_name", Cou_name);
			response.setCharacterEncoding("UTF-8");//设置编码方式避免中文乱码 
			request.getRequestDispatcher("Manager_Query.jsp").forward(request,response );
		}else if(flag.equals("assign")){
			String class_name=request.getParameter("Class_name");
			String cou_name=request.getParameter("Cou_name");
			String tea_name=request.getParameter("Tea_name");
			
			int class_ID=dbh.getID("select Class_ID from Class_tb where Class_name='"+class_name+"'");
			System.out.println("select Class_ID from Class_tb where Class_name='"+class_name+"'");
			int cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+cou_name+"'");
			int tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name='"+tea_name+"'");
			ArrayList<Tea_Cou_Cla> al=new ArrayList<Tea_Cou_Cla>();
			if(class_name!=null||cou_name!=null||tea_name!=null){//用于判断是不是有条件查询
				if(class_name!=""){//用于判断是不是需要按条件进行查询
					if(cou_name!=""){//按班级和课程查询
						class_ID=dbh.getID("select Class_ID from Class_tb where Class_name='"+class_name+"'");
						cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+cou_name+"'");
						tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name like '%"+tea_name+"%'");
						al=dbh.getTea_Cou_Cla("select *  from (select  * from Tea_Cou_Class_tb where Cou_ID='"+cou_ID+"' and Class_ID='"+class_ID+"'  ) Cou_ID   "+limitSql);
						rowCount=dbh.getRowCount("select count(*) from Tea_Cou_Class_tb where Cou_ID='"+cou_ID+"' and Class_ID='"+class_ID+"'");
					}
					else {
						if(tea_name!=""){//按班级和教师查询			
							class_ID=dbh.getID("select Class_ID from Class_tb where Class_name='"+class_name+"'");
							tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name='"+tea_name+"'");
							cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name  like  '%"+cou_name+"%'");
							al=dbh.getTea_Cou_Cla(" select *  from (select  * from Tea_Cou_Class_tb where Tea_ID='"+tea_ID+"' and Class_ID='"+class_ID+"' )  Tea_ID  "+limitSql);
							rowCount=dbh.getRowCount("select count(*) from Tea_Cou_Class_tb where Class_ID='"+class_ID+"' and Tea_ID='"+tea_ID+"'"); 
						}else{//按班级查询
							class_ID=dbh.getID("select Class_ID from Class_tb where Class_name='"+class_name+"'");
							tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name like '%"+tea_name+"%'");
							cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name  like  '%"+cou_name+"%'");
							al=dbh.getTea_Cou_Cla(" select  *  from  (select  * from Tea_Cou_Class_tb where Class_ID='"+class_ID+"' )  Tea_ID  "+limitSql);
							rowCount=dbh.getRowCount("select count(*) from Tea_Cou_Class_tb where Class_ID='"+class_ID+"'");
						}
					}
				}
			 else{
				 if(cou_name!=""){//按课程和教师查询
					 if(tea_name!=""){
							class_ID=dbh.getID("select Class_ID from Class_tb where Class_name like  '%"+class_name+"%'");
							tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name='"+tea_name+"'");
							cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+cou_name+"'");
						 al=dbh.getTea_Cou_Cla(" select * from (select  * from Tea_Cou_Class_tb where Cou_ID='"+cou_ID+"' and Tea_ID='"+tea_ID+"'  )  Tea_ID  "+limitSql);
						 rowCount=dbh.getRowCount("select count(*) from Tea_Cou_Class_tb where Cou_ID='"+cou_ID+"' and Tea_ID='"+tea_ID+"'"); 
					 }else{//按课程查询
							class_ID=dbh.getID("select Class_ID from Class_tb where Class_name like  '%"+class_name+"%'");
							tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name  like '%"+tea_name+"%'");
							cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+cou_name+"'");
						 al=dbh.getTea_Cou_Cla(" select *  from (select  * from Tea_Cou_Class_tb where Cou_ID='"+cou_ID+"' )  Tea_ID  "+limitSql);
						 rowCount=dbh.getRowCount("select count(*) from Tea_Cou_Class_tb where Cou_ID='"+cou_ID+"'");
					 }
				 }
				else{//按教师查询 
					if(tea_name!=""){
						class_ID=dbh.getID("select Class_ID from Class_tb where Class_name like  '%"+class_name+"%'");
						tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name='"+tea_name+"'");
						cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name  like '%"+cou_name+"%'");
						al=dbh.getTea_Cou_Cla("select * from (select  * from Tea_Cou_Class_tb where Tea_ID='"+tea_ID+"'  ) Tea_ID  "+limitSql);
						System.out.println("select  * from Tea_Cou_Class_tb where Tea_ID='"+tea_ID+"'   Tea_ID  "+limitSql);
						rowCount=dbh.getRowCount("select count(*) from Tea_Cou_Class_tb where Tea_ID='"+tea_ID+"'");
					}
					else{
						class_ID=dbh.getID("select Class_ID from Class_tb where Class_name like  '%"+class_name+"%'");
						tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name  like '%"+tea_name+"%'");
						cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name   like  '%"+cou_name+"%'");
						al=dbh.getTea_Cou_Cla("select * from Tea_Cou_Class_tb  Tea_ID   "+limitSql);
						rowCount=dbh.getRowCount("select count(*) from Tea_Cou_Class_tb ");
					}
				 }
				 
				}
				request.setAttribute("class_name", class_name);
				request.setAttribute("tea_name",tea_name);
				request.setAttribute("cou_name", cou_name);
			}else{//无条件,显示所有教师-班级-课程信息。
				al=dbh.getTea_Cou_Cla("select * from Tea_Cou_Class_tb   Tea_ID   "+limitSql);
				rowCount=dbh.getRowCount("select count(*) from Tea_Cou_Class_tb ");
				request.setAttribute("class_name", "");
				request.setAttribute("cou_name", "");
				request.setAttribute("tea_name","");
			}			
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;
		    //通过rowCount和pageSize的关系来计算pageCount的值
		    ArrayList<String> Class_name=dbh.getAttribute("select Class_name from Class_tb");
		    ArrayList<String> Tea_name=dbh.getAttribute("select Tea_name from Tea_tb");
		    ArrayList<String> Cou_name=dbh.getAttribute("select Cou_name from Cou_tb");
		    //得到所有的班级的名称，并将他们放入一个集合中 		   
		    request.setAttribute("Tea_Cou_Cla", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");
			request.setAttribute("Class_name", Class_name);
			request.setAttribute("Tea_name",Tea_name);
			request.setAttribute("Cou_name", Cou_name);
			request.getRequestDispatcher("Manager_Assign.jsp").forward(request,response );
		}
		else if(flag.equals("psdAlter")){
			 String old_psd=request.getParameter("old_psd");
			 String new_psd=request.getParameter("new_psd");			 
			 ArrayList<String> psd=dbh.getPassword("select Man_psd from Man_tb where Man_name='"+user+"'");
			 boolean b=false;
			 for(int i=0;i<psd.size();i++){
				 if(old_psd.equals(psd.get(i))){
					 b=true;break;
				 }
			 }
			 if(b){
				 String sql="update Man_tb set Man_psd='"+new_psd+"' where Man_name='"+user+"' and Man_psd='"+old_psd+"'";
				 if(dbh.Update(sql)){
					 sess.setAttribute("message", "密码修改成功！");
					 request.getRequestDispatcher("Success.jsp").forward(request, response);
				 }
			 }else{
				 request.getRequestDispatcher("Manager_psdAlter.jsp?flag=error").forward(request, response);
			 }
			 
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	
	}

}
