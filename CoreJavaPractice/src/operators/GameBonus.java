package operators;

//10.In a game, a player earns bonus points if their score is greater than 100 and they complete the level within the given time, 
//but not if they used cheats. Use a conditional operator to decide bonus eligibility 
//and a compound assignment operator to update the player’s score.

class GameBonus {
  public static void main(String[] args) {
      int score = 120;
      boolean onTime = true;
      boolean cheated = false;

      boolean bonus = (score > 100 && onTime && !cheated);
      score += bonus ? 50 : 0;

      System.out.println("Final Score: " + score);
  }
}
