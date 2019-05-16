package com.example.android.fitdiary.Authentication;


import com.example.android.fitdiary.Dao;
import com.google.android.gms.tasks.Task;

public class AuthenticationPresenter {
    private Dao dao;
    private IView iview;

    public AuthenticationPresenter(IView iview) {
        this.iview = iview;
        dao =  new Dao();
    }

    public void  registerUser(String email, String password){
         dao.signUp(email,password);
    }

    public Task signInUser(String email, String password){

        return dao.signIn(email,password);
    }
    public interface IView{
        void signingSuccesfull();
    }

}
