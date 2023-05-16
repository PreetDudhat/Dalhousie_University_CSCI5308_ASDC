// Asteroid is a leaf component in the composite structure
public class Asteroid extends BoardComponent
{
	private int height;
	
	public Asteroid(int x,int y, int height)
	{
		super(x,y);
		this.height = height;
	}
	
	@Override
	public void Operation()
	{
		// The operation performed by Asteroid objects is to fall from the sky
		// one level at a time, when they hit the ground (height == 0) they impact
		// and destroy whatever buildings are in the square!
		height -= 1;
		if (0 == height)
		{
			// When an Asteroid impacts the ground it needs to send an event to the
			// observer to tell it that it impacted the ground in the square it belongs
			// to.
			// <-- Send event to observer.
			GameBoard.Instance().GetAsteroidImpact().Notify(GetX(),GetY());
			// It should then remove itself from its parent, it no longer exists in the
			// hierarchy and should not receive any more operations.
			parent.Remove(this);
			Detach();
		}
	}

	@Override
	public void Update(int x, int y)
	{
		//Asteroid don't do anything with Update
	}

	@Override
	public void Add(BoardComponent child)
	{
		// I'm a leaf!
	}

	@Override
	public void Remove(BoardComponent child)
	{
		// I'm a leaf!
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