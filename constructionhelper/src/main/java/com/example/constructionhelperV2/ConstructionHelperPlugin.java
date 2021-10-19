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

		status = "Amount of planks : " + PlankOnButler;
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
						if (butlerwidget.getText().length() < config.PlankID_MAX())
						{
							PlankOnButler = 0;
						}
						if (butlerwidget.getText().length() >= config.PlankID_MAX())
						{
							String amount_planks = butlerwidget.getText().substring(config.PlankID_MIN(), config.PlankID_MAX()).trim();
							PlankOnButler = Integer.parseInt(amount_planks);
						}
					}
				}
			}

			//for (int i = 0; i < command2split.length; i++)
			//{
			//	if (!butlerwidget.getText().isEmpty() && butlerwidget.getText().contains(command2split[i]))
			//	{
					/*if(client.getWidget(config.ButlerWidgetID(), config.ButlerWidgetChildID() +1) != null && Objects.requireNonNull(client.getWidget(config.ButlerWidgetID(), config.ButlerWidgetChildID() + 1).isHidden()) == false)
					{
						Widget butlerwidget2 = client.getWidget(config.ButlerWidgetID(), config.ButlerWidgetChildID()+1);
						System.out.println(butlerwidget2.getText());
						if (butlerwidget2.getText().length() < config.PlankID_MAX()){
							PlankOnButler = 0;
							System.out.println(PlankOnButler);
						}
						else
						{
							String planks = butlerwidget2.getText().substring(config.PlankID_MIN(), config.PlankID_MAX());
							String result = planks.trim();
							PlankOnButler = Integer.parseInt(result);
							System.out.println(PlankOnButler);
						}
					}*/


					/*try
					{
						Robot key1Press = new Robot();
						key1Press.keyPress(config.ButlerHotkey().getKeyCode());
						key1Press.keyRelease(config.ButlerHotkey().getKeyCode());
					}
					catch (AWTException e)
					{
						System.out.println(e.getMessage());
						e.printStackTrace();
					}*/
			//	}
			//}
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
				/*for (String c : command1.split("\n\\s"))
				{
					System.out.println("Checking if statement. . .");
					System.out.println("Comparing children[i] = " + children[i].getText());
					System.out.println("With the value of split c = " + c);
					if (children[i].getText().contains(c))
					{
						try
						{
							Robot key1Press = new Robot();
							key1Press.keyPress(config.DialogHotkey1().getKeyCode());
							wait(random_int(100, 300));
							key1Press.keyRelease(config.DialogHotkey1().getKeyCode());
						}
						catch (AWTException | InterruptedException e)
						{
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}
				}
			}
		}
		/*String command1 = config.Key1_Script();
		for (String c: command1.split("\n\\s"))
		{
			System.out.println(c);
		}
		if (client.getWidget(Construction_Screen_ID, 0) != null)
		{
			if (Objects.requireNonNull(client.getWidget(Construction_Screen_ID, 0).isHidden()) == false)
			{
				System.out.println("Widget = visable");
				try
				{
					System.out.println("Trying to press a key");
					Robot keypress = new Robot();
					keypress.keyPress(KeyEvent.VK_NUMPAD3);
					wait(random_int(100, 300));
					keypress.keyRelease(KeyEvent.VK_NUMPAD3);
				}
				catch (AWTException | InterruptedException e)
				{
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		if (client.getWidget(219, 1) != null)
		{
			Widget[] children = client.getWidget(219, 1).getChildren().clone();
			for (int i = 0; i < children.length; i++)
			{
				System.out.println(children[i].getText());
				if (children[i].getText().contains("Fetch from bank")
					|| children[i].getText().contains("Repeat last task?")
					|| children[i].getText().contains("Really remove it?"))
				{
					try
					{
						Robot cancelpress = new Robot();
						cancelpress.keyPress(KeyEvent.VK_NUMPAD1);
						wait(random_int(100, 300));
						cancelpress.keyRelease(KeyEvent.VK_NUMPAD1);
					}
					catch (AWTException | InterruptedException e)
					{
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}

			}
		}
		//Fetch from bank: 24 x Oak plank
		/*Widget temp = client.getWidget(Construction_Screen_ID, 0);
			Widget tempparent = client.getWidget(Construction_Screen_ID,0).getParent();
			System.out.println(tempparent.getId());
			if (Objects.requireNonNull(temp).isHidden())
			{
				System.out.println("Ishidden");
			}
			if (!Objects.requireNonNull(temp).isHidden())
			{
				System.out.println("IsNotHidden");
			}
		if (Objects.requireNonNull(client.getWidget(Construction_Screen_ID, 0).getParentId() != -1))
		{
			Widget temp = client.getWidget(Construction_Screen_ID, 1);
			Widget[] childs = temp.getChildren().clone();
			for (int i = 0; i < childs.length; i++)
			{
				String text = childs[i].getText();
				if (text != "")
				{
					System.out.println(text);
				}
			}
		}
		else
		{
			return;
		}
		//Widget use = client.getWidget(Construction_Screen_ID,0);
		//System.out.println(use.getId());
		/*if (client.getWidget(Construction_Screen_ID) == null)
		{
			Widget temp1 = client.getWidget(Main_Screen_ID_Resizeable_Modern);
			//if(client.getWidget(Main_Screen_ID_Resizeable_Modern).ish == null) //&& Objects.requireNonNull(temp1).isHidden())
			System.out.println(temp1);
			if (Objects.requireNonNull(temp1).isHidden())
			{
				System.out.println("Main View not found");
			}
			else if (Objects.requireNonNull(temp1).isHidden() == false)
			{
				System.out.println("Main View Visable");
			}
			System.out.println("Rerun");
		}
		else
		{
			System.out.println("Construction Visable");
		}*/
	}

	@Subscribe
	private void onGameTick(GameTick gameTick)
	{
		// runs every gametick
		//log.info("Gametick");
	}
}