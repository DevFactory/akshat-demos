package trilogy;

import java.util.ArrayList;

class Response{
	int DifficultyValue;
	boolean isCorrect;
	
	Response(int d, boolean i)
	{
		this.DifficultyValue = d;
		this.isCorrect = i;
	}
}
public class SkillEstimator {
	
	ArrayList<Response> sortedResponses;

	public void addResponse(Response resp)
	{
		sortedResponses.add(resp);
	}
	public SkillEstimator()
	{
		sortedResponses = new ArrayList<Response>();
	}
	
	/* The first argument is the new response submitted by student.
	 * The second argument is the past responses of the student sorted by difficulty level.
	 * This will be retrieved from the database
	 */
	public int getUpdateSkillLevel(Response response, ArrayList<Response> sortedResponses)
	{
		int questionSkillLevel = response.DifficultyValue;
		int startIndex = 0;
		int currentIndex = sortedResponses.size()/2;
		int endIndex = sortedResponses.size()-1;
		
		/* Find the index that matches the difficulty level of the new response */
		while(endIndex-startIndex>1)
		{
			if(sortedResponses.get(currentIndex).DifficultyValue < questionSkillLevel)
			{
				startIndex = currentIndex;
				currentIndex = (currentIndex + endIndex)/2;
			}
			else
			{
				endIndex = currentIndex;
				currentIndex = (currentIndex+startIndex)/2;
			}
		}
		if(response.isCorrect) /* boundary should move to the right */
		{
			while(sortedResponses.get(currentIndex).isCorrect)
				currentIndex++;
		}
		else /* boundary should move to the left side */
		{
			while(!sortedResponses.get(currentIndex).isCorrect)
				currentIndex--;		
		}
		return (sortedResponses.get(currentIndex).DifficultyValue+questionSkillLevel)/2;
	}
	
	public static void main(String [] args)
	{
		SkillEstimator se = new SkillEstimator();
		se.addResponse(new Response(1,true));
		se.addResponse(new Response(3,true));
		se.addResponse(new Response(4,true));
		se.addResponse(new Response(6,true));
		se.addResponse(new Response(8,false));
		se.addResponse(new Response(9,false));
		se.addResponse(new Response(12,false));
		se.addResponse(new Response(14,false));
		
		System.out.println(se.getUpdateSkillLevel(new Response(7,true), se.sortedResponses));
		System.out.println(se.getUpdateSkillLevel(new Response(7,false), se.sortedResponses));
		
	}
}
