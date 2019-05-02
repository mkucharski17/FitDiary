package com.example.android.fitdiary;

public class RegistrationPresenter {
    private User user;
    private IView iview;

    public RegistrationPresenter(IView iview) {
        this.iview = iview;
        user = new User();
    }

    public void updateUser(String email, String password, String login){
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
    }

    public interface IView{

    }

}
