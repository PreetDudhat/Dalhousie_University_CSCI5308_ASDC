import java.util.ArrayList;

// Square is a composite, making up the Composite pattern (contains components)
public class Square extends BoardComponent
{
	private final ArrayList<BoardComponent> children;
	private BoardComponent parent;
	
	public Square(int x, int y)
	{
		super(x,y);
		children = new ArrayList<BoardComponent>();
	}

	@Override
	public void Operation()
	{
		ArrayList<BoardComponent> childrenCopy = new ArrayList<BoardComponent>(children);
		for (int i = 0; i < childrenCopy.size(); i++)
		{
			BoardComponent child = childrenCopy.get(i);
			child.Operation();
		}
	}
	@Override
	public void Update(int x, int y)
	{
		//Update on Square do nothing because Square don't have health
		// Buildings are Updated separately by Subject
	}

	@Override
	public void Add(BoardComponent child)
	{
		// I am now this child's parent.
		children.add(child);
		child.SetParent(this);
	}

	@Override
	public void Remove(BoardComponent child)
	{
		children.remove(child);
	}

	//Square attach all its children and itself to Subject
	// It works because in init phase, when Squares are created, they are attached to Subject, and they have
	//	no children, so children aren't attached twice.
	@Override
	public void Attach()
	{
		GameBoard.Instance().GetAsteroidImpact().Attach(this);
		for(BoardComponent child : children)
		{
			child.Attach();
		}
	}

	//Square detach all its children and itself to Subject
	@Override
	public void Detach()
	{
		GameBoard.Instance().GetAsteroidImpact().Detach(this);
		for(BoardComponent child : children)
		{
			child.Detach();
		}
	}
}