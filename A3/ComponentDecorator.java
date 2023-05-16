public abstract class ComponentDecorator extends BoardComponent
{

    //the component being decorated
    protected BoardComponent decoratedComponent;
    public ComponentDecorator(BoardComponent decoratedComponent)
    {
        super(decoratedComponent.GetX(),decoratedComponent.GetY());
        this.decoratedComponent = decoratedComponent;
    }
    @Override
    public void Operation()
    {
        decoratedComponent.Operation();
    }
    @Override
    public void Update(int x, int y)
    {
        decoratedComponent.Update(x, y);
    }
    @Override
    public void Add(BoardComponent child)
    {
        decoratedComponent.Add(child);
    }
    @Override
    public void Remove(BoardComponent child)
    {
        decoratedComponent.Remove(child);
    }

    @Override
    public void Attach()
    {
        decoratedComponent.Attach();
    }

    @Override
    public void Detach()
    {
        decoratedComponent.Detach();
    }
}
