package com.example.onetickboltenchant.ScriptCommand;

import com.example.onetickboltenchant.OneTickBoltEnchantConfig;
import com.example.onetickboltenchant.OneTickBoltEnchantPlugin;
import java.util.Objects;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;

public interface ScriptCommand
{
	void execute(Client client, OneTickBoltEnchantConfig config, OneTickBoltEnchantPlugin plugin, ConfigManager configManager);

	default void castSpell(WidgetInfo widgetInfo, Client client, OneTickBoltEnchantPlugin plugin)
	{
		try
		{
			Widget spell_widget = client.getWidget(widgetInfo);

			if (spell_widget == null)
			{
				return;
			}
			plugin.clientThread.invoke(() -> client.invokeMenuAction(spell_widget.getTargetVerb(), spell_widget.getName(), 1, MenuAction.CC_OP.getId(), spell_widget.getItemId(), spell_widget.getId()));
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	default void OpenTab(WidgetInfo widgetInfo, Client client, OneTickBoltEnchantPlugin plugin)
	{
		try
		{
			if (widgetInfo == null)
			{
				return;
			}
			Widget tab = client.getWidget(widgetInfo);
			if (!Objects.requireNonNull(tab).isHidden() && tab.getSpriteId() < 0)
			{
				System.out.println("Tab found. . . Opening tab with ID: " + widgetInfo.getId());
				plugin.clientThread.invoke(() -> client.invokeMenuAction(tab.getTargetVerb(), tab.getName(), 1, MenuAction.CC_OP.getId(), tab.getItemId(), tab.getId()));
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}

class EnchantBoltCommand implements ScriptCommand
{
	public void execute(Client client, OneTickBoltEnchantConfig config, OneTickBoltEnchantPlugin plugin, ConfigManager configManager)
	{
		if(config.forcemagetab())
		{
			WidgetHook widgetHook = new WidgetHook();
			boolean checkForOpen = widgetHook.isTabOpen(client,widgetHook.MageTab(client));
			if(checkForOpen){
				System.out.println("Forcing to open tab");
				OpenTab(widgetHook.MageTab(client), client, plugin);
			}
		}
		try
		{
			castSpell(WidgetInfo.SPELL_ENCHANT_CROSSBOW_BOLT, client, plugin);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}

class OpenMageTabCommand implements ScriptCommand
{
	public void execute(Client client, OneTickBoltEnchantConfig config, OneTickBoltEnchantPlugin plugin, ConfigManager configManager)
	{
		try
		{
			WidgetHook widgetHook = new WidgetHook();
			WidgetInfo tabinfo = widgetHook.MageTab(client);
			OpenTab(tabinfo, client, plugin);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
class OpenInventoryTabCommand implements ScriptCommand
{
	public void execute(Client client, OneTickBoltEnchantConfig config, OneTickBoltEnchantPlugin plugin, ConfigManager configManager)
	{
		try
		{
			WidgetHook widgetHook = new WidgetHook();
			WidgetInfo tabinfo = widgetHook.InventoryTab(client);
			OpenTab(tabinfo, client, plugin);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
class ExceptionCommand implements ScriptCommand
{
	public void execute(Client client, OneTickBoltEnchantConfig config, OneTickBoltEnchantPlugin plugin, ConfigManager configManager)
	{
		System.out.println("Command could not be read.");
	}
}
