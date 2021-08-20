package com.example.onetickboltenchant;

import com.example.onetickboltenchant.ScriptCommand.ScriptCommand;
import com.google.inject.Provides;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import javax.inject.Inject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.MenuEntry;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.pf4j.Extension;

@Extension
@PluginDescriptor(
	name = "One Tick Bolt Enchant",
	description = "Plugin to enchant bolts for you ",
	enabledByDefault = false,
	tags = {"One", "Tick", "Bolt", "Willemmmo"}
)
@Slf4j
public class OneTickBoltEnchantPlugin extends Plugin
{
	public Queue<ScriptCommand> commandList = new ConcurrentLinkedDeque<>();
	public Queue<MenuEntry> entryList = new ConcurrentLinkedDeque<>();
	public boolean runboltenchanting = false;
	@Inject
	private Client client;
	//@Getter(AccessLevel.PACKAGE);
	@Inject
	private OneTickBoltEnchantConfig config;
	@Inject
	private ConfigManager configManager;
	@Inject
	private KeyManager keyManager;
	@Inject
	private OneTickBoltEnchantHotkeyListener hotkeyListener;

	// Provides our config
	@Provides
	OneTickBoltEnchantConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(OneTickBoltEnchantConfig.class);
	}

	@Override
	protected void startUp()
	{
		keyManager.registerKeyListener(hotkeyListener);
	}

	@Override
	protected void shutDown()
	{
		runboltenchanting = false;
		keyManager.unregisterKeyListener(hotkeyListener);
	}

	@Subscribe
	private void onGameTick(GameTick gameTick)
	{
		// runs every gametick
		log.info("Gametick");
	}
	@Subscribe
	public void onClientTick(ClientTick event)
	{
		if (client.getGameState() == GameState.LOGGED_IN)
		{
			return;
		}
		processCommands();
	}



	private void processCommands()
	{
		while (commandList.peek() != null){
			commandList.poll().execute(client,config,this,configManager);
		}
	}
}