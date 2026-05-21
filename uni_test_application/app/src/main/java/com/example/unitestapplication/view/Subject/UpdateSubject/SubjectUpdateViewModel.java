package com.example.unitestapplication.view.Subject.UpdateSubject;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.unitestapplication.DAO.SubjectDAO ;
import com.example.unitestapplication.memoryDAO.SubjectDAOMemory ;

public class SubjectUpdateViewModel extends ViewModel {
    private SubjectUpdatePresenter presenter;

    public SubjectUpdateViewModel() {
        presenter = new SubjectUpdatePresenter();
        SubjectDAO subjectDAO = new SubjectDAOMemory();  // Ή το κατάλληλο DAO
        presenter.setBookDAO(subjectDAO);
    }

    public SubjectUpdatePresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("SubjectUpdateVM", "onCleared");
    }
}
