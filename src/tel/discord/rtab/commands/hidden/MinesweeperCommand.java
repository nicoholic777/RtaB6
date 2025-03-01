package tel.discord.rtab.commands.hidden;

import tel.discord.rtab.GameController;
import tel.discord.rtab.GameStatus;
import tel.discord.rtab.PlayerStatus;
import tel.discord.rtab.RaceToABillionBot;
import tel.discord.rtab.board.HiddenCommand;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class MinesweeperCommand extends Command
{
	public MinesweeperCommand()
	{
		this.name = "minesweeper";
		this.aliases = new String[] {"minesweep","sweep"};
		this.help = "look at how many bombs are adjacent to a space";
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
				String rawSpace = event.getArgs();
				//Check that it's valid (the game is running, the space is legit, they're alive, and they have the command)
				if(game.gameStatus != GameStatus.IN_PROGRESS || player == -1 || !game.checkValidNumber(rawSpace)
						|| game.players.get(player).status != PlayerStatus.ALIVE ||
						(chosenCommand != HiddenCommand.MINESWEEP && chosenCommand != HiddenCommand.WILD))
					event.reply("You can't do this right now.");
				//Cool, we're good, pass it over
				else
				{
					int space = Integer.parseInt(rawSpace) - 1;
					if(game.pickedSpaces[space])
						event.reply("That space has already been picked.");
					else
						game.useMinesweeper(player, space);
				}
				return;
			}
		}
		//We aren't in a game channel? Uh...
		event.reply("This is not a game channel.");
	}
}