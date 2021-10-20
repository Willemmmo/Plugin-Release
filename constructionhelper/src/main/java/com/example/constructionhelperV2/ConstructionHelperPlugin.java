package com.example.constructionhelperV2;

import com.google.inject.Provides;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.Objects;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.GameTick;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import org.pf4j.Extension;

@Extension
@PluginDescriptor(
	name = "Construction Helper",
	description = "Construction Helper by Willemmmo"
)
@Slf4j
public class ConstructionHelperPlugin extends Plugin
{
	public int PlankOnButler = -1;
	public String status = "Setting-Up..";
	// Injects our config
	@Inject
	private ConstructionHelperConfig config;
	@Inject
	private Client client;
	@Inject
	private ConstructionHelperOverlay overlay;
	@Inject
	private OverlayManager overlayManager;

	@Provides
	ConstructionHelperConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ConstructionHelperConfig.class);
	}

	@Override
	protected void startUp()
	{
		// runs on plugin startup
		log.info("Plugin started");
		overlayManager.add(overlay);
		// example how to use config items
		//if (config.example())
		//{
		// do stuff
		//	log.info("The value of 'config.example()' is ${config.example()}");
		//}
	}

	@Override
	protected void shutDown()
	{
		log.info("Plugin stopped");
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onClientTick(ClientTick event)
	{

		if(config.EnableDeveloper()){
			status = "DEVELOPER MODE + Planks:" + PlankOnButler;
		}
		if(!config.EnableDeveloper())
		{
			status = "Butler amount of planks : " + PlankOnButler;
		}
		if (client.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}
		if (client.getWidget(config.DialogWidgetID(), config.DialogWidgetChildID()) != null && Objects.requireNonNull(client.getWidget(config.DialogWidgetID(), config.DialogWidgetChildID()).isHidden()) == false)
		{
			Widget[] children = client.getWidget(config.DialogWidgetID(), config.DialogWidgetChildID()).getChildren().clone();
			for (Widget child : children)
			{
				String command1 = config.Key1_Script();
				if (command1.isEmpty())
				{
					System.out.println("command1 = empty, returning");
					return;
				}
				String[] command1split = command1.split("\n");
				for (String s : command1split)
				{
					if (child.getText().contains(s) && config.EnableDialogKey())
					{
						try
						{
							Robot key1Press = new Robot();
							key1Press.keyPress(config.DialogHotkey1().getKeyCode());
							key1Press.keyRelease(config.DialogHotkey1().getKeyCode());
						}
						catch (AWTException e)
						{
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}
				}
			}
		}
		if (client.getWidget(config.ButlerWidgetID(), config.ButlerWidgetChildID()) != null && Objects.requireNonNull(client.getWidget(config.ButlerWidgetID(), config.ButlerWidgetChildID()).isHidden()) == false)
		{
			Widget butlerwidget = client.getWidget(config.ButlerWidgetID(), config.ButlerWidgetChildID());
			if (!butlerwidget.getText().isEmpty())
			{
				//decides to press a key
				if (config.Key2_Script().isEmpty())
				{
					return;
				}
				String[] command2split = config.Key2_Script().split("\n");
				for (String s : command2split)
				{
					if (butlerwidget.getText().contains(s) && config.EnableButlerKey())
					{
						try
						{
							Robot ButlerKeyPress = new Robot();
							ButlerKeyPress.keyPress(config.ButlerHotkey().getKeyCode());
							ButlerKeyPress.keyRelease(config.ButlerHotkey().getKeyCode());
						}
						catch (AWTException e)
						{
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}
				}

				//decides to not press a key
				if (config.Key3_Script().isEmpty())
				{
					return;
				}
				String[] command3split = config.Key3_Script().split("\n");
				for (String s : command3split)
				{
					if (butlerwidget.getText().contains(s))
					{
						for (int i = 1; i < 27 ; i++)
						{
							String PlankBuilder = "these " +i + " items";
							if(butlerwidget.getText().contains(PlankBuilder))
							{
								PlankOnButler = i;
								break;
							}
							else if(i == 26)
							{
								PlankOnButler = 0;
							}
						}
					}
				}
			}
		}
		if (config.EnableConstructionKey() && client.getWidget(config.ConstructionWidgetID(), config.ConstructionWidgetChildID()) != null && Objects.requireNonNull(client.getWidget(config.ConstructionWidgetID(), config.ConstructionWidgetChildID()).isHidden()) == false)
		{
			try
			{
				Robot keypress = new Robot();
				keypress.keyPress(config.ConstructionHotkey().getKeyCode());
				keypress.keyRelease(config.ConstructionHotkey().getKeyCode());
			}
			catch (AWTException e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@Subscribe
	private void onGameTick(GameTick gameTick)
	{
		// runs every gametick
		//log.info("Gametick");
	}
}