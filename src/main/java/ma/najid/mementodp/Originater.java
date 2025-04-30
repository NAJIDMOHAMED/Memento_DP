package ma.najid.mementodp;

public class Originater {
    private String article;
    public void set(String newAricle){
        System.out.println("From The Originator :Current Version of Article \n"+newAricle+"\n");
        article = newAricle;
    }
    public Memento storeInMemento(){
        System.out.println("From The Originator :Saving to Memento  \n");
        return  new Memento(article);
    }

    public String restoreFromMemento(Memento memento){
             article=memento.getSavedArticle();
        System.out.println("From The Originator :Previous Article Saved in Memento\n"+article+"\n");
             return  article;
    }


}
