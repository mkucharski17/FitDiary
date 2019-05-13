package com.example.android.fitdiary.Registration;


public class RegistrationPresenter {
    private UserDao userDao;
    private IView iview;

    public RegistrationPresenter(IView iview) {
        this.iview = iview;
        userDao =  new UserDao();
    }

    public void registerUser(String email, String password, String login){
        userDao.saveUser(new User(email,password,login));

    }
    public interface IView{
        void savedSuccesfull();
    }

}
