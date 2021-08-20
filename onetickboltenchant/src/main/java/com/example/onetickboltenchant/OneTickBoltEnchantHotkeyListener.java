package com.example.onetickboltenchant;

import com.example.onetickboltenchant.ScriptCommand.ScriptCommandFactory;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.time.Duration;
import java.time.Instant;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.input.KeyListener;

public class OneTickBoltEnchantHotkeyListener extends MouseAdapter implements KeyListener
{
	private Client client;
	private Instant lastPress;

	@Inject
	private OneTickBoltEnchantPlugin plugin;
	@Inject
	private OneTickBoltEnchantConfig config;
	@Inject
	private ConfigManager configManager;

	@Inject
	private OneTickBoltEnchantHotkeyListener(Client client, OneTickBoltEnchantConfig config, OneTickBoltEnchantPlugin plugin)
	{
		this.client = client;
		this.config = config;
		this.plugin = plugin;
	}

	private void addCommands(String command, OneTickBoltEnchantPlugin plugin)
	{
		for (String c : command.split("\\s*\n\\s*"))
		{
			System.out.println("Creating Command");
			plugin.commandList.add(ScriptCommandFactory.builder(c));
		}
	}

	public void addTickCommand(String command, OneTickBoltEnchantPlugin plugin)
	{
		for (String c : command.split("\\s*\n\\s*"))
		{
			System.out.println("Creating Command");
			plugin.commandList.add(ScriptCommandFactory.builder(c));
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			System.out.println("Not logged in");
			return;
		}
		try
		{
			if (lastPress != null && Duration.between(lastPress, Instant.now()).getNano() > 1000)
			{
				lastPress = null;
			}
			if (lastPress != null)
			{
				return;
			}
			int key_code = e.getKeyCode();
			if (key_code == config.key1().getKeyCode())
			{
				addCommands("openmage", plugin);
				//plugin.runboltenchanting = true;
			}
			if (key_code == config.key2().getKeyCode())
			{
				addCommands("enchantbolt", plugin);
				//plugin.runboltenchanting = false;
			}

		}
		catch (Throwable ex)
		{
			System.out.print(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}
}
