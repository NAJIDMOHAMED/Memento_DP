package ma.najid.mementodp;

import java.util.ArrayList;

public class CareTaker {

    private ArrayList<Memento>savedArticle=new ArrayList<>();
    public void addMemento(Memento memento){
        savedArticle.add(memento);
    }

    public Memento getMemento(int index){
        return savedArticle.get(index);
    }


    public int size() {
        return savedArticle.size();
    }
}
