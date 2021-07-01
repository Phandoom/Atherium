package models;

import models.Enums.classes;
import models.Enums.races;

public class register {

// fields
    private String  _name;
    private String _password;
    private races _races;
    private classes _classes;
    private String     _UserId;

// getter/setter
    public String getName() {
        return this._name;
    }
    public void setName(String name) {
        this._name = name;
    }

    public String getPassword() {
        return this._password;
    }
    public void setPassword(String password) {
        this._password = password;
    }

    public races getRaces() {
        return this._races;
    }
    public void setRaces(races races) {
        this._races = races;
    }

    public classes getClasses() {
        return this._classes;
    }
    public void setClasses(classes classes) {
        this._classes = classes;
    }

    public String getUserId() {
        return this._UserId;
    }
    public void setUserId() {
        this._UserId = this._name + "_" + this._password;
    }

//creators
    public register(){
        this("", "", races.human, classes.knight);
    }
    public register(String name, String password, races races, classes classes){
        this.setName(name);
        this.setPassword(password);
        this.setRaces(races);
        this.setClasses(classes);
        this.setUserId();
    }


//other methods


    @Override
    public String toString() {
        return _name + "\n" + _password + "\n" + _races + "\n" +
               _classes + "\n" + _UserId;
    }
}
