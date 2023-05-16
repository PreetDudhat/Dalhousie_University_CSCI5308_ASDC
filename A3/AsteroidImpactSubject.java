import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

//Concrete Subject class for Asteroid impacts
public class AsteroidImpactSubject implements ISubject
{
    private List<BoardComponent> observers;
    public AsteroidImpactSubject()
    {
        observers = new ArrayList<BoardComponent>();
    }
    @Override
    public void Attach(BoardComponent observer)
    {
        observers.add(observer);
    }
    @Override
    public void Detach(BoardComponent observer)
    {
        observers.remove(observer);
    }
    @Override
    public void Notify(int x, int y)
    {
        List<BoardComponent> observersCopy = new ArrayList<BoardComponent>(observers);
        ListIterator<BoardComponent> iter = observersCopy.listIterator();
        while(iter.hasNext())
        {
            iter.next().Update(x,y);
        }
    }
}
