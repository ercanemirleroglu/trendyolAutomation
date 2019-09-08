package users;

import base.MainPage;


public class User {
    public String username;
    public String password;
    public final String ercanUser = "youMustTypeYourAccountNameHere";
    public final String ercanPass = "Password";
    private MainPage mainPage = new MainPage();

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User getUser(String _userName, String _password) {

        return new User(_userName, _password);
    }


}
