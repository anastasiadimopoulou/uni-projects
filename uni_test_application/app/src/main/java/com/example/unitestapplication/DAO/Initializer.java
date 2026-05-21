package com.example.unitestapplication.DAO;
import com.example.unitestapplication.domain.*;
public  abstract class Initializer {
    public static final int GIAKOUMAKIS_ID = 2;
    public static final int DIAMANTIDIS_ID = 8;
    public static final int UML_USER_GUIDE_ID1 = 3;
    public static final int UML_DISTILLED_ID1 = 1;
    public static final int UML_REFACTORING_ID = 5;
    public static final int UML_USER_GUIDE_ID2 = 4;
    public static final int UML_DISTILLED_ID2 = 2;
    /**
     * Διαγράφει τα αποθηκευμένα δεδομένα.
     */
    protected abstract void eraseData();
    /**
     * Προσθέτει ένα subject .

     * @return Το subject που προστέθηκε
     */
    /*
    private Subject addOneSubjectItem(Subject sub)
    {
        SubjectDAO item = getSubjectDAO();
        Subject itemTmp = new Subject(sub.getname(), sub.getid(), sub.getsemester());
        item.save(itemTmp);
        return itemTmp;
    }*/


    /**
     * Εισάγει τα δοκιμαστικά δεδομένα.
     */
    /*
    public void prepareData() {
        // πριν εισάγουμε τα δεδομένα διαγράφουμε ότι υπάρχει
        eraseData();

        ProfessorDAO professorDAO = getProfessorDAO();
        professorDAO.save(new Professor("nikos", "diamantidis", "nikdia", "123456", "cs", "opa", "1980-6-5", 1, "pe"));
        professorDAO.save(new Professor("pavlos", "kollias", "nikdia", "123456", "cs", "opa", "1980-6-5", 1, "pe"));


        SubjectDAO subjectDao = getSubjectDAO();

        subjectDao.save(new Subject("logiki", 1, 5));
        subjectDao.save(new Subject("c", 2, 3));
        subjectDao.save(new Subject("osy", 3, 4));

    }*/


    public abstract ProfessorDAO getProfessorDAO();
    public abstract SubjectDAO getSubjectDAO();

    /////////////////////
    public void clearAndInitializeData() {
        eraseData1();  // Διαγράφει τα δεδομένα
        prepareData1(); // Εισάγει τα δεδομένα του Initializer
    }

    protected abstract void eraseData1();
    public abstract void prepareData1();
    //////////////////////
}



