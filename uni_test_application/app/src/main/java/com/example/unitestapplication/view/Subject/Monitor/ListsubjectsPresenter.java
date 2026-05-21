package com.example.unitestapplication.view.Subject.Monitor;
import com.example.unitestapplication.DAO.SubjectDAO ;
import com.example.unitestapplication.domain.Subject ;
import java.util.HashSet;
import java.util.Set;
public class ListsubjectsPresenter
{
    private SubjectDAO subjectDAO;
    private ListsubjectsView view;
    private Set<Subject> searchResult = new HashSet<>();
    public ListsubjectsPresenter() {}
    public SubjectDAO getBookDAO() {
        return subjectDAO;
    }
    public void setsubjectDAO(SubjectDAO bookDAO) {
        this.subjectDAO = bookDAO;
    }
    public void setView(ListsubjectsView view) {
        this.view = (ListsubjectsView) view;
    }
    public ListsubjectsView getView(){return view;}
    /**
     * finds subject using its name and id
     * @param name
     * @param id
     */
    public void search(String name, int id)
    {

        searchResult.clear();
        if (isEmpty(name) && id==0){
            searchResult.addAll(subjectDAO.findAll());
        }
        Set<Subject> nameSearchResult = new HashSet<>();
        Set<Subject> idSearchResult = new HashSet<>();
        if (!isEmpty(name)){
            nameSearchResult.addAll(subjectDAO.findByname(name));
        }
        if (id!=0){
            idSearchResult.addAll(subjectDAO.findByid(id));
        }
        if (!isEmpty(name) && id!=0){
            searchResult.addAll(nameSearchResult);
            searchResult.retainAll(idSearchResult);
            return;
        }

        searchResult.addAll(nameSearchResult);
        searchResult.addAll(idSearchResult);

    }

    public Set<Subject> getSearchResult() {
        return searchResult;
    }

    private boolean isEmpty(String searchTerm){
        return searchTerm == null || searchTerm.isEmpty();
    }
}
