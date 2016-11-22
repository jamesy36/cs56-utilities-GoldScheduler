package Search;

public class ItemGolder
{
	private String id;
	private String description;
	
	public ItemGolder(String id, String description)
	{
		this.id = id;
		this.description = description;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String toString()
	{
		return description;
	}
}
