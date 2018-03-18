package com.sjitportal.home.portal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Process;
import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 20-08-2015.
 */




public class Query_execute extends Activity{


    private String dbname;
    public String abc="start";
    static final String HEADER="Content-Type=application/json&api-key="+Dbdetails.getPass();
    Localdb ldb;

    Query_execute() throws Internet_Exception {
//        if(Internet_Exception.isNetworkAvailable(getApplicationContext())==false)
  //          throw new Internet_Exception();

    }
    Query_execute(String s,Context context) throws Internet_Exception
    {
        ldb=new Localdb(this,dbname,null,1);

        dbname=s;

        Network_State n=new Network_State();
        if(n.isNetworkAvailable(context)==false)
//       if(Internet_Exception.isNetworkAvailable(getApplicationContext())==false)
            throw new Internet_Exception();

    }






    private class Dbconnectfordeptuploads
    {
        // public String ret=new String();
        public Dept_Uploads[] s=new Dept_Uploads[100];


        protected Void execute(String... params) {
            Student b[];
            Localdb ldb=new Localdb(StaticApp.getContext(),"login",null,1);
            b=ldb.outstudent("select * from student_personal");

            Dbdetails.dept=Find.dept(b[0].getRollno());
            Dbdetails db=new Dbdetails();


            Connection conn=null;
            try
            {
                Class.forName(db.getDriver());


                conn= DriverManager.getConnection(db.getUrl(), db.getUserName(), db.getPass());
                Statement stmt=null;
                stmt = conn.createStatement();
                String sql=params[0];
                ResultSet rs;

                rs=stmt.executeQuery(sql);


                if(rs.next()) {

                    int i=0;
                    try {
                        rs.afterLast();
                        while(rs.previous())
                        {
                            s[i]=new Dept_Uploads();
                            s[i].setName(rs.getString("name"));
                            s[i].setDesc(rs.getString("desc"));
                            s[i].setPath(rs.getString("path"));
                            s[i].setType(rs.getString("type"));
                            s[i].setSno(rs.getInt("sno"));



                            i++;
                        }
                        s[i]=new Dept_Uploads();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    abc = "found";
                }
                else
                    abc="not";
                stmt.close();

            }catch(SQLException se)
            {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                try{
                    if(conn!=null)
                    conn.close();
                }catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }


            return null;
        }





    }




    private class Dbconnectforsjitportal
    {
        // public String ret=new String();
        public int dummy=0;
        public Dept_Uploads[] s=new Dept_Uploads[100];


        protected Void execute(String... params) {
            Dbdetails db=new Dbdetails("sjitportal");


            Connection conn=null;
            try
            {
                Class.forName(db.getDriver());


                conn= DriverManager.getConnection(db.getUrl(), db.getUserName(), db.getPass());
                Statement stmt=null;
                stmt = conn.createStatement();
                String sql=null;
                ResultSet rs;

                sql="select * from circular where type = 'circular'";
                rs=stmt.executeQuery(sql);


                if(rs.next())
                    dummy=1;

                    int i=0;
                    try {
                        rs.afterLast();
                        while(rs.previous())
                        {
                            s[i]=new Dept_Uploads();
                            s[i].setName(rs.getString("name"));
                            s[i].setDesc(rs.getString("des"));
                            s[i].setPath(rs.getString("path"));
                            s[i].setType(rs.getString("type"));
                            s[i].setSno(rs.getInt("sno"));

                            Log.i("Upload","circular");

                            i++;
                        }

                        sql="select * from exam_circular";
                        rs=stmt.executeQuery(sql);



                        if(rs.next())
                            dummy=1;


                            rs.afterLast();
                            while(rs.previous())
                            {
                                s[i]=new Dept_Uploads();
                                s[i].setName(rs.getString("file_name"));
                                s[i].setDesc(rs.getString("descp"));
                                s[i].setPath(rs.getString("location"));
                                s[i].setSno(rs.getInt("sno"));


                                Log.i("Upload", "exam_circular");


                                i++;
                            }

                        sql="select * from forms";
                        rs=stmt.executeQuery(sql);



                        if(rs.next())
                            dummy=1;



                        rs.afterLast();
                        while(rs.previous())
                        {
                            s[i]=new Dept_Uploads();
                            s[i].setName(rs.getString("filename"));
                            s[i].setDesc(rs.getString("descp"));
                            s[i].setPath(rs.getString("location"));
                            s[i].setSno(rs.getInt("sno"));

                            Log.i("Upload", "form");



                            i++;
                        }





                        s[i]=new Dept_Uploads();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }




                    if(dummy==1)
                        abc="found";

                if(!abc.equals("found"))
                    abc="not";
                stmt.close();

            }catch(SQLException se)
            {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }


            return null;
        }





    }



    private class Dbconnectforcommonevents
    {
        // public String ret=new String();
        public int dummy=0;
        public Dept_Uploads[] s=new Dept_Uploads[100];


        protected Void execute(String... params) {
            Dbdetails db=new Dbdetails("sjitportal");


            Connection conn=null;
            try
            {
                Class.forName(db.getDriver());


                conn= DriverManager.getConnection(db.getUrl(), db.getUserName(), db.getPass());
                Statement stmt=null;
                stmt = conn.createStatement();
                String sql=null;
                ResultSet rs;

                sql="select * from circular where type = 'event'";
                rs=stmt.executeQuery(sql);


                if(rs.next())
                    dummy=1;

                int i=0;
                try {
                    rs.afterLast();
                    while(rs.previous())
                    {
                        s[i]=new Dept_Uploads();
                        s[i].setName(rs.getString("name"));
                        s[i].setDesc(rs.getString("des"));
                        s[i].setPath(rs.getString("path"));
                        s[i].setType(rs.getString("type"));
                        s[i].setSno(rs.getInt("sno"));

                        Log.i("Upload","circular");

                        i++;
                    }


                    s[i]=new Dept_Uploads();
                } catch (SQLException e) {
                    e.printStackTrace();
                }




                if(dummy==1)
                    abc="found";

                if(!abc.equals("found"))
                    abc="not";
                stmt.close();

            }catch(SQLException se)
            {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }


            return null;
        }





    }






    private class Dbconnectforattendance
    {
        // public String ret=new String();
        public Attendance[] s=new Attendance[100];


        protected Void execute(String... params) {


            Dbdetails db=new Dbdetails();


            Connection conn=null;
            try
            {
                Class.forName(db.getDriver());


                conn= DriverManager.getConnection(db.getUrl(), db.getUserName(), db.getPass());
                Statement stmt=null;
                stmt = conn.createStatement();
                String sql=params[0];
                ResultSet rs;

                rs=stmt.executeQuery(sql);


                if(rs.next()) {

                    int i=0;
                    try {
                       // rs.afterLast();
                        rs.beforeFirst();
                        while(rs.next())
                        {

                            s[i]=new Attendance();
                            s[i].setDate(rs.getDate("date"));
                            s[i].setSem(rs.getString("sem"));
                          //  s[i].setReason(rs.getString("reason"));
                            s[i].setRollno(rs.getString("rollno"));


                            i++;
                        }
                        s[i]=new Attendance();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    abc = "found";
                }
                else
                    abc="not";
                stmt.close();

            }catch(SQLException se)
            {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                try{
                    if(conn!=null)
                    conn.close();
                }catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }


            return null;
        }





    }







    private class Dbconnectfornotes
    {
        // public String ret=new String();
        public Notes[] s=new Notes[100];

        protected Void execute(String... params) {
            Dbdetails db=new Dbdetails();


            Connection conn=null;
            try
            {
                Class.forName(db.getDriver());


                conn= DriverManager.getConnection(db.getUrl(), db.getUserName(), db.getPass());
                Statement stmt=null;
                stmt = conn.createStatement();
                String sql=params[0];
                ResultSet rs;

                rs=stmt.executeQuery(sql);


                if(rs.next()) {

                    int i=0;
                    try {
                        rs.beforeFirst();
                        while(rs.next())
                        {
                            s[i]=new Notes();
                            s[i].setSubcode(rs.getString("subcode"));
                            s[i].setSem(rs.getString("sem"));
                            s[i].setFilename(rs.getString("filename"));
                            s[i].setNotes_type(rs.getString("notes_type"));
                            s[i].setAcadamic_yr(rs.getString("acadamic_yr"));
                            s[i].setPath(rs.getString("path"));
                            s[i].setDescp(rs.getString("descp"));

                            i++;
                        }
                        s[i]=new Notes();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    abc = "found";
                }
                else
                    abc="not";
                stmt.close();

            }catch(SQLException se)
            {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                try{
                    if(conn!=null)
                    conn.close();
                }catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }


            return null;
        }





    }




    private class Dbconnectforstudent extends AsyncTask<String,Void,Void>
    {
       // public String ret=new String();
       public Student[] s=new Student[100];

        @Override
        protected Void doInBackground(String... params) {
            Dbdetails db=new Dbdetails();


            Connection conn=null;
            try
            {
                Class.forName(db.getDriver());


                conn= DriverManager.getConnection(db.getUrl(), db.getUserName(), db.getPass());
                Statement stmt=null;
                stmt = conn.createStatement();
                String sql=params[0];
                ResultSet rs;

                rs=stmt.executeQuery(sql);


                if(rs.next()) {

                    int i=0;
                    try {
                        rs.beforeFirst();
                        while(rs.next())
                        {
                            s[i]=new Student();
                            s[i].setBatch(rs.getString("batch"));
                            s[i].setCourse(rs.getString("course"));
                            s[i].setDept(rs.getString("dept"));
                            s[i].setName(rs.getString("name"));
                            s[i].setRegno(rs.getString("regno"));
                            s[i].setRollno(rs.getString("rollno"));
                            s[i].setSec(rs.getString("sec"));

                            i++;
                        }
                        s[i]=new Student();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    abc = "found";
                }
                else
                    abc="not";
                stmt.close();

            }catch(SQLException se)
            {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }


            return null;
        }





    }

    private class Dbconnectformarks extends AsyncTask<String,Void,Void>
    {
        // public String ret=new String();
        public Marks[] s=new Marks[100];
        List<Marks> list=new ArrayList<>();

        @Override
        protected Void doInBackground(String... params) {
            Process.setThreadPriority(Process.THREAD_PRIORITY_MORE_FAVORABLE);
            Dbdetails db=new Dbdetails();

            Connection conn=null;
            try
            {
                Class.forName(db.getDriver());


                conn= DriverManager.getConnection(db.getUrl(), db.getUserName(), db.getPass());
                Statement stmt=null;
                stmt = conn.createStatement();
                String sql=params[0];
                //ResultSet rs;
                //"select * from marks_table where rollno='" + rn + "' and sem='" + semgiven + "' and " + examgiven + "<>'null';";
                String sem=sql.substring(sql.indexOf("sem=")+5,sql.indexOf("sem=")+7);
                String rollno=sql.substring(sql.indexOf("rollno=")+8, sql.indexOf("and")-2);
                sql=sql.replace("_table", "").substring(0,sql.indexOf("and sem")-6)+"and subcode in (SELECT subcode FROM subject_sem_table where sem='"+sem+"')";

                //rs=stmt.executeQuery(sql);
                Log.i("Rest Request","sent");
                String rs=HTTPClient.post(ServerPath.path+"/RESTful/mark",HEADER,"{" +
                        " \"rollno\" : \""+rollno+ "\","
                        +" \"sem\" : \""+sem+"\" "
                        +"}",null);
                Log.i("rest response","received");
                JSONParser parser=new JSONParser();
                JSONArray jsonArr=  (JSONArray) parser.parse(rs);
                if(jsonArr.size()!=0) {


                    try {

                        String subcode="";
                        for(Object obj:jsonArr)
                        {
                            Marks m = new Marks();

                            JSONObject json=(JSONObject)obj;
                            System.out.println("Server "+ json.get("subcode"));


                                m.setSubcode((String)json.get("subcode"));
                                m.setSem(sem);
                                m.setRollno((String)json.get("rollno"));



                            String type=(String)json.get("type");
                            System.out.println(type+" "+(String)json.get("mark"));

                            switch (type){
                                case  "cycle1":
                                    m.setCycle1((String)json.get("mark"));
                                    break;
                                case "cycle2":
                                    m.setCycle2((String)json.get("mark"));
                                    break;
                                case "cycle3":
                                    m.setCycle3((String)json.get("mark"));
                                    break;
                                case  "model1":
                                    m.setModel1((String)json.get("mark"));
                                    break;
                                case "model2":
                                    m.setModel2((String)json.get("mark"));
                                    break;
                                case "model3":
                                    m.setModel3((String)json.get("mark"));
                                    break;
                                case  "unit1":
                                    m.setUnit1((String)json.get("mark"));
                                    break;
                                case "unit2":
                                    m.setUnit2((String)json.get("mark"));
                                    break;
                                case "unit3":
                                    m.setUnit3((String)json.get("mark"));
                                    break;
                                case "unit4":
                                    m.setUnit4((String)json.get("mark"));
                                    break;

                            }

                            list.add(m);

                        }

                        if(list.isEmpty())
                            s[0]=new Marks();
                        else {
                            list.toArray(s);
                            s[list.size()] = new Marks();
                            for(Marks a:s)
                                if(a!=null)
                                    System.out.println("Return "+a.getSubcode());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    abc = "found";
                }
                else
                {
                    abc="not";

                }
                stmt.close();

            }catch(SQLException se)
            {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                try{
                    if(conn!=null)
                   conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }





    }





    private class Dbconnectforsubject_sem extends AsyncTask<String,Void,Void>
    {
        // public String ret=new String();
        public Subject_sem[] s=new Subject_sem[100];

        @Override
        protected Void doInBackground(String... params) {
            Dbdetails db=new Dbdetails();


            Connection conn=null;
            try
            {
                Class.forName(db.getDriver());


                conn= DriverManager.getConnection(db.getUrl(), db.getUserName(), db.getPass());
                Statement stmt=null;
                stmt = conn.createStatement();
                String sql=params[0];
                ResultSet rs;

                rs=stmt.executeQuery(sql);


                if(rs.next()) {

                    int i=0;
                    try {
                        rs.beforeFirst();
                        while(rs.next())
                        {
                            s[i]=new Subject_sem();
                            s[i].setSubname(rs.getString("subname"));
                            s[i].setSem(rs.getString("sem"));
                            s[i].setSubtype(rs.getString("subtype"));
                            s[i].setRegulation(rs.getString("regulation"));
                            s[i].setSubcode(rs.getString("subcode"));

                            i++;
                        }
                        s[i]=new Subject_sem();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    abc = "found";
                }
                else
                    abc="not";
                stmt.close();

            }catch(SQLException se)
            {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                try{
                    if(conn!=null)
                    conn.close();
                }catch(SQLException se)
                {
                    se.printStackTrace();
                }
            }


            return null;
        }





    }









    public Subject_sem [] onsubject_sem(String sql)
    {
        Dbconnectforsubject_sem dbs=new Dbconnectforsubject_sem();

        dbs.execute(sql);
        while(abc.equals("start")){}
        if(abc.equals("found"))
        {
            abc="start";

            return dbs.s;


        }
        else {
            abc="start";
            dbs.s[0]=new Subject_sem();
            return dbs.s;

        }


    }






    public Student [] onstudent(String sql)
    {
            Dbconnectforstudent dbs=new Dbconnectforstudent();

            dbs.execute(sql);
            while(abc.equals("start")){}
            if(abc.equals("found"))
            {
                abc="start";

                  return dbs.s;


            }
              else {
                abc="start";
                 dbs.s[0]=new Student();
                 return dbs.s;

              }


    }


    public Marks [] onmarks(String sql)
    {
        Dbconnectformarks dbs=new Dbconnectformarks();

        dbs.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,sql);
        try {
            Process.setThreadPriority(Process.THREAD_PRIORITY_LOWEST);
            while(abc.equals("start")) {

            Thread.sleep(100);
        }
        } catch (InterruptedException e) {
                e.printStackTrace();
            }

        if(abc.equals("found"))
        {
            abc="start";
            if(dbs.s[0]==null)
                dbs.s[0]=new Marks();
            return dbs.s;


        }
        else {
            Log.i("Mark","Not found");
            abc="start";
            dbs.s[0]=new Marks();
            return dbs.s;

        }


    }





    public Notes[] onnotes(String sql)
    {
        Dbconnectfornotes dbs=new Dbconnectfornotes();

        dbs.execute(sql);
        while(abc.equals("start")){}
        if(abc.equals("found"))
        {
            abc="start";

            return dbs.s;


        }
        else {
            abc="start";
            dbs.s[0]=new Notes();
            return dbs.s;

        }


    }




    public Attendance[] onattendance(String sql)
    {
        Dbconnectforattendance dbs=new Dbconnectforattendance();

        dbs.execute(sql);
        while(abc.equals("start")){}

        if(abc.equals("found"))
        {
            abc="start";

            return dbs.s;


        }
        else {
            abc="start";
            dbs.s[0]=new Attendance();
            return dbs.s;

        }


    }


    public Dept_Uploads[] onsjitportal()
    {
        Dbconnectforsjitportal dbs=new Dbconnectforsjitportal();

        dbs.execute();
        while(abc.equals("start")){}
        if(abc.equals("found"))
        {
            abc="start";

            return dbs.s;


        }
        else {
            abc="start";
            dbs.s[0]=new Dept_Uploads();
            return dbs.s;

        }


    }


    public Dept_Uploads[] onsjitcommonevents()
    {
        Dbconnectforcommonevents dbs=new Dbconnectforcommonevents();

        dbs.execute();
        while(abc.equals("start")){}
        if(abc.equals("found"))
        {
            abc="start";

            return dbs.s;


        }
        else {
            abc="start";
            dbs.s[0]=new Dept_Uploads();
            return dbs.s;

        }


    }

    public Dept_Uploads[] ondeptuploads(String sql)
    {
        Dbconnectfordeptuploads dbs=new Dbconnectfordeptuploads();

        dbs.execute(sql);
        while(abc.equals("start")){}
        if(abc.equals("found"))
        {
            abc="start";

            return dbs.s;


        }
        else {
            abc="start";
            dbs.s[0]=new Dept_Uploads();
            return dbs.s;

        }


    }










}
