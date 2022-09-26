package tel.discord.rtab.commands.hidden;

import tel.discord.rtab.GameController;
import tel.discord.rtab.GameStatus;
import tel.discord.rtab.PlayerStatus;
import tel.discord.rtab.RaceToABillionBot;
import tel.discord.rtab.board.HiddenCommand;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class StarmanCommand extends Command
{
	public StarmanCommand()
	{
		this.name = "starman";
		this.help = "summons the starman and destroy all bombs currently on the board";
		this.hidden = true;
	}
	@Override
	protected void execute(CommandEvent event)
	{
		for(GameController game : RaceToABillionBot.game)
		{
			if(game.channel.equals(event.getChannel()))
			{
				int player = game.findPlayerInGame(event.getAuthor().getId());
				HiddenCommand chosenCommand = game.players.get(player).hiddenCommand;
				//Make sure the player is in the game and the game is mid-round but not mid-turn
				//Also, that the player is alive, and they have the thing to use
				if(game.gameStatus != GameStatus.IN_PROGRESS || player == -1 || game.resolvingTurn 
					|| game.players.get(player).status != PlayerStatus.ALIVE ||
					(chosenCommand != HiddenCommand.STARMAN))
					event.reply("https://niceme.me/");
				else
					game.useStarman(player);
				return;
			}
		}
		//We aren't in a game channel? Uh...
		event.reply("This is not a game channel.");
	}
}