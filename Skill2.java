public static void getSkill(){	
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
		return (sortedResponses);
}
            
