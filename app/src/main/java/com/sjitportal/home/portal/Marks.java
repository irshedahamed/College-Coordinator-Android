package com.sjitportal.home.portal;
/**
 * Created by Home on 18-08-2015.
 */
public class Marks {


      private String rollno ;
    private String sem;
    private String subcode;
    private String cycle1;
    private String  model1;
    private String cycle2;
    private String  model2;
    private String  cycle3;
    private String  model3;


    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public void setCycle1(String cycle1) {
        this.cycle1 = cycle1;
    }

    public void setModel1(String model1) {
        this.model1 = model1;
    }

    public void setCycle2(String cycle2) {
        this.cycle2 = cycle2;
    }

    public void setModel2(String model2) {
        this.model2 = model2;
    }

    public void setCycle3(String cycle3) {
        this.cycle3 = cycle3;
    }

    public void setModel3(String model3) {
        this.model3 = model3;
    }

    public String getRollno() {
        return rollno;
    }

    public String getSem() {
        return sem;
    }

    public String getSubcode() {
        return subcode;
    }

    public String getCycle1() {
        return cycle1;
    }

    public String getModel1() {
        return model1;
    }

    public String getCycle2() {
        return cycle2;
    }

    public String getModel2() {
        return model2;
    }

    public String getCycle3() {
        return cycle3;
    }

    public String getModel3() {
        return model3;
    }

    public String mark(String exam)
    {
        if(exam.equals("cycle1"))
            return getCycle1();
        else if(exam.equals("cycle2"))
            return getCycle2();

        else if(exam.equals("cycle3"))
            return getCycle3();

        else if(exam.equals("model1"))
            return getModel1();

        else if(exam.equals("model2"))
            return getModel2();

        else if(exam.equals("model3"))
            return getModel3();

        return null;

    }
}
