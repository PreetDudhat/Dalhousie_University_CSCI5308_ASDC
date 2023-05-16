import java.util.ArrayList;

//This is the concrete implementation of ComponentDecorator
//This is a Shield decorator which decorates Square
public class Shield extends ComponentDecorator
{
    private int shieldHealth;

    public Shield(BoardComponent decoratedSquare)
    {
        super(decoratedSquare);
        shieldHealth = 2;
    }

    @Override
    public void Update(int x, int y)
    {
        if(GetX()==x && GetY()==y)
        {
            shieldHealth--;
            if (shieldHealth == 0)
            {
                ArrayList<BoardComponent> row = GameBoard.Instance().GetBoard().get(y);
                row.set(x, decoratedComponent);
                Detach();
            }
        }
    }
    @Override
    public void Attach()
    {
        GameBoard.Instance().GetAsteroidImpact().Attach(this);
        decoratedComponent.Detach();
    }
    @Override
    public void Detach()
    {
        GameBoard.Instance().GetAsteroidImpact().Detach(this);
        decoratedComponent.Attach();
    }

}
