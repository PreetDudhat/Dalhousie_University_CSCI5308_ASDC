// Building is the leaf node for the composite pattern, Square's can have MULTIPLE Buildings.
// Buildings cannot have children.
public class Building extends BoardComponent
{
	private int buildingHealth;

	//now requires x, y
	public Building(int x, int y)
	{
		super(x, y);
		buildingHealth = 2;
	}

	@Override
	public void Operation()
	{
	}
	@Override
	public void Update(int x, int y)
	{
		if(GetX()==x && GetY()==y)
		{
			buildingHealth--;
			if(buildingHealth==0)
			{
				parent.Remove(this);
				Detach();
				GameBoard.Instance().DecrementBuildingCount();
			}
		}
	}

	@Override
	public void Add(BoardComponent child)
	{
		// Do nothing, I'm a leaf.
	}

	@Override
	public void Remove(BoardComponent child)
	{
		// Do nothing, I'm a leaf.
	}

	@Override
	public void Attach()
	{
		GameBoard.Instance().GetAsteroidImpact().Attach(this);
	}

	@Override
	public void Detach()
	{
		GameBoard.Instance().GetAsteroidImpact().Detach(this);
	}
}