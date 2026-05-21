package com.example.unitestapplication.view.Subject.UpdateSubject.QuestionManagment.DeleteQuestion;

import android.util.Log;

import com.example.unitestapplication.DAO.QuestionDAO;
import com.example.unitestapplication.domain.Question;
import com.example.unitestapplication.domain.Subject;
import com.example.unitestapplication.view.Subject.Search.SubjectSearchView;

import java.util.HashSet;
import java.util.Set;

public class DeleteQuestionPresenter {
    private QuestionDAO questionDAO;
    private DeleteQuestionView view;
    private Set<Question> searchResult=new HashSet<>();
    public DeleteQuestionPresenter(){}
    public void setQuestionDao(QuestionDAO questionDAO){this.questionDAO=questionDAO; }

    public QuestionDAO getQuestionDAO(){return questionDAO;}
    public void setView(DeleteQuestionView view) {
        this.view = view;
    }
    public DeleteQuestionView getQuestionSearchView(){return view;}
    public DeleteQuestionView getview(){return view;}

    /**
     * finds all questions that belong to a specific subject
     * @param id
     * @return
     */

    public Set<Question> search(int id)

    {
        searchResult.clear();

        Set<Question> idSearchResult = new HashSet<>();
        if (id!=0){
            idSearchResult.addAll(questionDAO.findByid(id));
        }

        searchResult.addAll(idSearchResult);


        return searchResult;
    }

    public Set<Question> getSearchResult() {
        return searchResult;
    }

    public boolean isEmpty(String searchTerm){
        return searchTerm == null || searchTerm.isEmpty();
    }
}