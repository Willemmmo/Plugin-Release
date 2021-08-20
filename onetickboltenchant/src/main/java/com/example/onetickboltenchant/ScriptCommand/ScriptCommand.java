package com.example.onetickboltenchant.ScriptCommand;

import com.example.onetickboltenchant.OneTickBoltEnchantConfig;
import com.example.onetickboltenchant.OneTickBoltEnchantPlugin;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.Point;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;

public interface ScriptCommand
{
	@Inject
	void execute(Client client, OneTickBoltEnchantConfig config, OneTickBoltEnchantPlugin plugin, ConfigManager configManager);
	//functie om spells te one casten
	default void castSpell(WidgetInfo widgetInfo, Client client, OneTickBoltEnchantPlugin plugin)
	{
		try
		{
			Widget spellbook_widget = client.getWidget(widgetInfo);
			if (spellbook_widget == null)
			{
				return;
			}
			plugin.entryList.add(new MenuEntry(spellbook_widget.getTargetVerb(), spellbook_widget.getName(), 1, MenuAction.CC_OP.getId(), spellbook_widget.getItemId(), spellbook_widget.getId(), false));
			clickfunction(client);
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
			Widget tab_widget = client.getWidget(widgetInfo);

			if (tab_widget == null)
			{
				return;
			}
			plugin.entryList.add(new MenuEntry("Magic", "", 1, MenuAction.CC_OP.getId(), tab_widget.getItemId(), tab_widget.getId(), false));
			clickfunction(client);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	default void clickfunction(Client client)
	{
		Point pos = client.getMouseCanvasPosition();

		if (client.isStretchedEnabled())
		{
			final Dimension stretched = client.getStretchedDimensions();
			final Dimension real = client.getRealDimensions();
			final double width = (stretched.width / real.getWidth());
			final double height = (stretched.height / real.getHeight());
			final Point point = new Point((int) (pos.getX() * width), (int) (pos.getY() * height));
			client.getCanvas().dispatchEvent(new MouseEvent(client.getCanvas(), 501, System.currentTimeMillis(), 0, point.getX(), point.getY(), 1, false, 1));
			client.getCanvas().dispatchEvent(new MouseEvent(client.getCanvas(), 502, System.currentTimeMillis(), 0, point.getX(), point.getY(), 1, false, 1));
			client.getCanvas().dispatchEvent(new MouseEvent(client.getCanvas(), 500, System.currentTimeMillis(), 0, point.getX(), point.getY(), 1, false, 1));
			return;
		}

		client.getCanvas().dispatchEvent(new MouseEvent(client.getCanvas(), 501, System.currentTimeMillis(), 0, pos.getX(), pos.getY(), 1, false, 1));
		client.getCanvas().dispatchEvent(new MouseEvent(client.getCanvas(), 502, System.currentTimeMillis(), 0, pos.getX(), pos.getY(), 1, false, 1));
		client.getCanvas().dispatchEvent(new MouseEvent(client.getCanvas(), 500, System.currentTimeMillis(), 0, pos.getX(), pos.getY(), 1, false, 1));
	}
}
class EnchantBoltCommand implements ScriptCommand
{
	public void execute(Client client, OneTickBoltEnchantConfig config, OneTickBoltEnchantPlugin plugin, ConfigManager configManager)
	{
		try
		{
			castSpell(WidgetInfo.SPELL_ENCHANT_CROSSBOW_BOLT,client,plugin);
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
			WidgetInfo view;
			if (client.getWidget(WidgetInfo.FIXED_VIEWPORT_MAGIC_TAB).isHidden())
			{
				if (client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_MAGIC_TAB).isHidden())
				{
					if (client.getWidget(WidgetInfo.RESIZABLE_VIEWPORT_MAGIC_TAB).isHidden())
					{
						return;
					}
					else
					{
						view = WidgetInfo.RESIZABLE_VIEWPORT_MAGIC_TAB;
					}
				}
				else
				{
					view = WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_MAGIC_TAB;
				}
			}
			else
			{
				view = WidgetInfo.FIXED_VIEWPORT_MAGIC_TAB;
			}
			Widget spellbook = client.getWidget(WidgetInfo.SPELLBOOK);
			if (spellbook.isHidden())
			{
				OpenTab(view, client, plugin);

			}
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