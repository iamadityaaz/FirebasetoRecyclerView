package pro.ap.com.firebasetorecyclerview;

public class ModelClass {

    private String title, imageurl;

    public ModelClass() {
    }

    public ModelClass(String title, String imageurl) {
        this.title = title;
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
