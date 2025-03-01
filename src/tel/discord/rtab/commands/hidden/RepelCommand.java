package tel.discord.rtab.commands.hidden;

import tel.discord.rtab.GameController;
import tel.discord.rtab.GameStatus;
import tel.discord.rtab.PlayerStatus;
import tel.discord.rtab.RaceToABillionBot;
import tel.discord.rtab.board.HiddenCommand;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class RepelCommand extends Command
{
	public RepelCommand()
	{
		this.name = "repel";
		this.help = "block a blammo that is currently active";
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
				//Check that it's valid (the game is running, they're alive, they have the command, and there's currently a blammo)
				if(game.gameStatus != GameStatus.IN_PROGRESS || player == -1 || !game.currentBlammo
						|| game.players.get(player).status != PlayerStatus.ALIVE ||
						(chosenCommand != HiddenCommand.REPEL && chosenCommand != HiddenCommand.WILD))
					event.reply("You can't do this right now.");
				else
					game.useRepel(player);
				return;
			}
		}
		//We aren't in a game channel? Uh...
		event.reply("This is not a game channel.");
	}
}