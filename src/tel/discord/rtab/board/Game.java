package tel.discord.rtab.board;

import tel.discord.rtab.games.*;

public enum Game implements WeightedSpace
{
	//Minigame Pool
	//Seasonal event is a minigame?? Guess it's a season of minigames
	COMMISSIONER(2)		{ public MiniGame getGame() { return new TheCommissioner(); } },
	//Minigames awarding weird things appear first
	OVERFLOW(1)			{ public MiniGame getGame() { return new Overflow(); } },		//Author: JerryEris
	//PvP games next so the opponent doesn't fall asleep waiting for them
	TIC_TAC_BOMB(1)		{ public MiniGame getGame() { return new TicTacBomb(); } },		//Author: Atia
	COLOUR_OF_MONEY(1)	{ public MiniGame getGame() { return new ColourOfMoney(); } },	//Author: Atia
	//Regular cash games
	DEAL_OR_NO_DEAL(1)	{ public MiniGame getGame() { return new DealOrNoDeal(); } },	//Author: Atia
	SAFE_CRACKER(1)		{ public MiniGame getGame() { return new SafeCracker(); } },	//Author: Atia
	STRIKE_IT_RICH(1)	{ public MiniGame getGame() { return new StrikeItRich(); } },	//Author: Atia
	TRIPLE_PLAY(1)		{ public MiniGame getGame() { return new TriplePlay(); } },		//Author: Atia
	CLOSE_SHAVE(1)		{ public MiniGame getGame() { return new CloseShave(); } },		//Author: JerryEris
	DOUBLE_ZEROES(1)	{ public MiniGame getGame() { return new DoubleZeroes(); } },	//Author: JerryEris
	MONEY_CARDS(1)		{ public MiniGame getGame() { return new MoneyCards(); } },		//Author: StrangerCoug
	PUNCH_A_BUNCH(1)	{ public MiniGame getGame() { return new PunchABunch(); } },    //Author: StrangerCoug
	SHUT_THE_BOX(1)		{ public MiniGame getGame() { return new ShutTheBox(); } },		//Author: StrangerCoug
	ZILCH(1)			{ public MiniGame getGame() { return new Zilch(); } },			//Author: Strangercoug
	BUMPER_GRAB(1)		{ public MiniGame getGame() { return new BumperGrab(); } },		//Author: Tara
	
	//Games rotated out
	TESTGAME(0)			{ public MiniGame getGame() { return new TestGame(); } },		//Author: The Triforce
	BOOSTER_SMASH(0)	{ public MiniGame getGame() { return new BoosterSmash(); } },	//Author: Atia
	FTROTS(0)			{ public MiniGame getGame() { return new FTROTS(); } },			//Author: Atia
	GAMBLE(0)			{ public MiniGame getGame() { return new Gamble(); } },			//Author: Atia
	MATH_TIME(0)		{ public MiniGame getGame() { return new MathTime(); } },		//Author: Atia
	OPTIMISE(0)			{ public MiniGame getGame() { return new Optimise(); } },		//Author: Atia
	COINFLIP(0)			{ public MiniGame getGame() { return new CoinFlip(); } },		//Author: Amy
	MINEFIELD_MULTI(0)	{ public MiniGame getGame() { return new MinefieldMulti(); } },	//Author: Amy
	THE_OFFER(0)		{ public MiniGame getGame() { return new TheOffer(); } },		//Author: Amy
	CALL_YOUR_SHOT(0)	{ public MiniGame getGame() { return new CallYourShot(); } },	//Author: JerryEris
	DOUBLE_TROUBLE(0)	{ public MiniGame getGame() { return new DoubleTrouble(); } },	//Author: JerryEris
	OPEN_PASS(0)		{ public MiniGame getGame() { return new OpenPass(); } },		//Author: JerryEris
	UP_AND_DOWN(0)		{ public MiniGame getGame() { return new UpAndDown(); } },		//Author: JerryEris
	BOMB_ROULETTE(0)	{ public MiniGame getGame() { return new BombRoulette(); } },	//Author: StrangerCoug
	DEUCES_WILD(0)		{ public MiniGame getGame() { return new DeucesWild(); } },		//Author: StrangerCoug
	HILO_DICE(0)		{ public MiniGame getGame() { return new HiLoDice(); } },		//Author: StrangerCoug
	SPLIT_WINNINGS(0)	{ public MiniGame getGame() { return new SplitWinnings(); } },	//Author: StrangerCoug
	
	//Bonus Games - not in pool but earned through other means
	SUPERCASH(0)		{ public MiniGame getGame() { return new Supercash(); } },
	DIGITAL_FORTRESS(0)	{ public MiniGame getGame() { return new DigitalFortress(); } },
	SPECTRUM(0)			{ public MiniGame getGame() { return new Spectrum(); } },
	HYPERCUBE(0)		{ public MiniGame getGame() { return new Hypercube(); } },
	RACE_DEAL(0)		{ public MiniGame getGame() { return new RaceDeal(); } },
	SUPERBONUSROUND(0)	{ public MiniGame getGame() { return new SuperBonusRound(); } };
	
	final String fullName;
	final String shortName;
	final String enhanceText;
	final boolean bonus;
	final int weight;
	Game(int valueWeight)
	{
		fullName = getGame().getName();
		shortName = getGame().getShortName();
		bonus = getGame().isBonus();
		enhanceText = getGame().getEnhanceText();
		weight = valueWeight; 
	}
	public String getName()
	{
		return fullName;
	}
	public String getShortName()
	{
		return shortName;
	}
	public boolean isBonus()
	{
		return bonus;
	}
	public String getEnhanceText()
	{
		return enhanceText;
	}
	//Returns a new instance of the requested minigame
	public abstract MiniGame getGame();
	
	@Override
	public int getWeight(int playerCount)
	{
		//Minigame types don't care about playercount
		return weight;
	}
}
