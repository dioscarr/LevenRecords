package info.androidhive.loginandregistration.activity;

/**
 * Created by dioscar on 8/21/2016.
 */
public class sessionModel {
    private int _id;
    private String _BookedDate;
    private String _DateCreated;
    public sessionModel(){
        //Empty
    }
    public sessionModel(int id, String BookedDate, String DateCreated){
        this._id = id;
        this._BookedDate = BookedDate;
        this._DateCreated = DateCreated;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public String get_BookedDate() {
        return _BookedDate;
    }

    public void set_BookedDate(String BookedDate) {
        this._BookedDate = BookedDate;
    }

    public String get_DateCreated() {
        return _DateCreated;
    }

    public void set_DateCreated(String DateCreated) {
        this._DateCreated = DateCreated;
    }
}
