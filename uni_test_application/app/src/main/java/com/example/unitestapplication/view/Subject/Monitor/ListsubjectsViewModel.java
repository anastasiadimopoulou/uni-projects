package com.example.unitestapplication.view.Subject.Monitor;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.unitestapplication.DAO.SubjectDAO ;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory ;

public class ListsubjectsViewModel extends ViewModel {
    private ListsubjectsPresenter presenter;

    public ListsubjectsViewModel() {
        presenter = new ListsubjectsPresenter();
        SubjectDAO subjectDAO = new SubjectDAOMemory();  // Ή το κατάλληλο DAO
        presenter.setsubjectDAO(subjectDAO);
    }

    public ListsubjectsPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
